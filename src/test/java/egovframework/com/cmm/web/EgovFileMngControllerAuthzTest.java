package egovframework.com.cmm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovAttachmentGrants;
import jakarta.servlet.ServletException;

/**
 * 첨부 목록·삭제 엔드포인트의 인가 회귀 테스트.
 *
 * <p>이 엔드포인트들은 첨부 그룹 식별자(ATCH_FILE_ID)만으로 동작한다. 파일 테이블에는
 * 소유자 컬럼이 없으므로(COMTNFILE = ATCH_FILE_ID, CREAT_DT, USE_AT) 여기서 소유권을
 * 판단할 수 없고, 인가는 첨부를 포함하는 업무 화면이 이미 수행한 것에 위임한다.
 * 그래서 삭제용 식별자는 수정 화면(selectFileInfsForUpdate)에서만 발급하고,
 * 읽기 전용 상세 화면(selectFileInfs)에는 내려보내지 않는다. 삭제는 발급한 세션에서만 받는다.
 * 기본 암호화 키가 공개라 식별자는 위조될 수 있으므로, 삭제 허가는 업무 컨트롤러가 소유권 확인 후
 * 세션에 발급한 것(EgovAttachmentGrants)으로 확인한다.</p>
 */
class EgovFileMngControllerAuthzTest {

	private static final String LIST_URL = "/cmm/fms/selectFileInfs.do";
	private static final String UPDATE_LIST_URL = "/cmm/fms/selectFileInfsForUpdate.do";
	private static final String DELETE_URL = "/cmm/fms/deleteFileInfs.do";


	private static final String PLAIN_FILE_ID = "FILE_000000000000123";
	private static final String CIPHER_FILE_ID = "ENC(FILE_000000000000123)";

	private MockMvc mockMvc;
	private final MockHttpSession session = new MockHttpSession();
	private List<FileVO> deleted;
	private boolean authenticated;
	private LoginVO currentUser;

	@BeforeEach
	void setUp() {
		deleted = new ArrayList<>();
		authenticated = true;
		currentUser = user("USRCNFRM_A");

		EgovFileMngService fileService = (EgovFileMngService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovFileMngService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectFileInfs":
						return List.of(file(0), file(1));
					case "deleteFileInf":
						deleted.add((FileVO) args[0]);
						return 1;
					default:
						throw new AssertionError("예상하지 못한 서비스 호출: " + method.getName());
					}
				});

		EgovEnvCryptoService crypto = (EgovEnvCryptoService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovEnvCryptoService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "encrypt":
						return "ENC(" + args[0] + ")";
					case "decrypt":
						String text = (String) args[0];
						return text.startsWith("ENC(") ? text.substring(4, text.length() - 1)
								: "FILE_ID_DECRIPT_EXCEPTION_02";
					default:
						throw new AssertionError("예상하지 못한 암호화 호출: " + method.getName());
					}
				});

		EgovUserDetailsService userDetails = (EgovUserDetailsService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, method, args) -> {
					if ("isAuthenticated".equals(method.getName())) {
						return authenticated;
					}
					if ("getAuthenticatedUser".equals(method.getName())) {
						return currentUser;
					}
					return null;
				});
		new egovframework.com.cmm.util.EgovUserDetailsHelper().setEgovUserDetailsService(userDetails);

		EgovFileMngController controller = new EgovFileMngController();
		ReflectionTestUtils.setField(controller, "fileService", fileService);
		controller.setEgovEnvCryptoService(crypto);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static LoginVO user(String uniqId) {
		LoginVO vo = new LoginVO();
		vo.setUniqId(uniqId);
		return vo;
	}

	private static FileVO file(int fileSn) {
		FileVO vo = new FileVO();
		vo.setAtchFileId(PLAIN_FILE_ID);
		vo.setFileSn(String.valueOf(fileSn));
		return vo;
	}

	// --- 목록: 삭제용 식별자 발급 범위 ---

	@Test
	void 읽기전용_목록은_삭제용_식별자를_내려보내지_않는다() throws Exception {
		MvcResult result = mockMvc.perform(get(LIST_URL)
				.param("param_atchFileId", CIPHER_FILE_ID))
				.andExpect(status().isOk())
				.andReturn();

		assertEquals("N", result.getModelAndView().getModel().get("updateFlag"));
		assertFalse(result.getModelAndView().getModel().containsKey("atchFileId"),
				"읽기 전용 화면에 삭제 가능한 첨부 그룹 식별자가 노출되었다");
	}

	@Test
	void 수정목록은_삭제용_식별자를_내려보낸다() throws Exception {
		MvcResult result = mockMvc.perform(get(UPDATE_LIST_URL)
				.param("param_atchFileId", CIPHER_FILE_ID))
				.andExpect(status().isOk())
				.andReturn();

		assertEquals("Y", result.getModelAndView().getModel().get("updateFlag"));
		assertEquals(CIPHER_FILE_ID, result.getModelAndView().getModel().get("atchFileId"));
	}

	@Test
	void 목록의_개별_파일_식별자는_세션에_묶인다() throws Exception {
		MvcResult result = mockMvc.perform(get(LIST_URL)
				.param("param_atchFileId", CIPHER_FILE_ID))
				.andReturn();

		@SuppressWarnings("unchecked")
		List<FileVO> fileList = (List<FileVO>) result.getModelAndView().getModel().get("fileList");
		String sessionId = result.getRequest().getSession().getId();
		for (FileVO vo : fileList) {
			String decoded = new String(java.util.Base64.getDecoder().decode(vo.getAtchFileId()));
			assertEquals("ENC(" + sessionId + "|" + PLAIN_FILE_ID + ")", decoded);
		}
	}

	// --- 삭제 ---

	@Test
	void 삭제는_GET으로_호출할_수_없다() throws Exception {
		mockMvc.perform(get(DELETE_URL).param("atchFileId", PLAIN_FILE_ID).param("fileSn", "0"))
				.andExpect(status().isMethodNotAllowed());
		assertTrue(deleted.isEmpty());
	}

	@Test
	void 인증되지_않으면_삭제할_수_없다() {
		authenticated = false;
		assertThrows(ServletException.class,
				() -> mockMvc.perform(post(DELETE_URL).session(session).param("atchFileId", token(session.getId())).param("fileSn", "0")));
		assertTrue(deleted.isEmpty());
	}

	@Test
	void 같은_세션이_발급한_식별자로만_요청한_파일을_지운다() throws Exception {
		grantDelete();
		mockMvc.perform(post(DELETE_URL).session(session).param("atchFileId", token(session.getId())).param("fileSn", "1"))
				.andExpect(status().isOk());

		assertEquals(1, deleted.size());
		assertEquals(PLAIN_FILE_ID, deleted.get(0).getAtchFileId());
		assertEquals("1", deleted.get(0).getFileSn());
	}

	@Test
	void 다른_세션이_발급한_식별자로는_삭제할_수_없다() {
		grantDelete();
		assertThrows(ServletException.class,
				() -> mockMvc.perform(post(DELETE_URL).session(session).param("atchFileId", token("OTHER_SESSION")).param("fileSn", "1")));
		assertTrue(deleted.isEmpty());
	}

	@Test
	void 세션에_묶이지_않은_구_형식_식별자로는_삭제할_수_없다() {
		assertThrows(ServletException.class,
				() -> mockMvc.perform(post(DELETE_URL).session(session).param("atchFileId", CIPHER_FILE_ID).param("fileSn", "1")));
		assertTrue(deleted.isEmpty());
	}

	@Test
	void 업무_컨트롤러가_허가하지_않은_첨부는_위조한_식별자로도_삭제할_수_없다() {
		// 공개 키로 "세션ID|FILE_xxx" 를 직접 암호화한 경우 — 형식·세션은 맞지만 소유권 확인을 거치지 않았다
		assertThrows(ServletException.class,
				() -> mockMvc.perform(post(DELETE_URL).session(session).param("atchFileId", token(session.getId())).param("fileSn", "1")));
		assertTrue(deleted.isEmpty());
	}

	@Test
	void 로그아웃_없이_다른_계정으로_바뀌면_이전_사용자의_허가로_삭제할_수_없다() {
		grantDelete();
		currentUser = user("USRCNFRM_B");
		assertThrows(ServletException.class,
				() -> mockMvc.perform(post(DELETE_URL).session(session).param("atchFileId", token(session.getId())).param("fileSn", "1")));
		assertTrue(deleted.isEmpty());
	}

	/** 업무 수정 화면이 소유권 확인 후 발급하는 것과 같다 */
	private void grantDelete() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		EgovAttachmentGrants.allowDelete(request, PLAIN_FILE_ID);
	}

	/** 수정 목록(selectFileInfsForUpdate)이 개별 파일에 붙여 내려보내는 형식 */
	private static String token(String sessionId) {
		return java.util.Base64.getEncoder().encodeToString(("ENC(" + sessionId + "|" + PLAIN_FILE_ID + ")").getBytes());
	}
}
