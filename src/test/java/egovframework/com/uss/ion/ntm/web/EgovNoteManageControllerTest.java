package egovframework.com.uss.ion.ntm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.ntm.service.NoteManageVO;

class EgovNoteManageControllerTest {

	private final AtomicInteger parsedFileCount = new AtomicInteger();
	private final AtomicInteger insertedFileCount = new AtomicInteger();
	private EgovUserDetailsService originalUserDetailsService;

	@BeforeEach
	void setUpAuthenticatedUser() {
		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId("USRCNFRM_00000000000");

		EgovUserDetailsHelper helper = new EgovUserDetailsHelper();
		originalUserDetailsService = helper.getEgovUserDetailsService();
		helper.setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return loginVO;
			}

			@Override
			public List<String> getAuthorities() {
				return Collections.emptyList();
			}

			@Override
			public Boolean isAuthenticated() {
				return true;
			}
		});
	}

	@AfterEach
	void restoreUserDetailsService() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(originalUserDetailsService);
	}

	@Test
	void validationErrorsDoNotPersistAttachments() throws Exception {
		EgovNoteManageController controller = new EgovNoteManageController();
		ReflectionTestUtils.setField(controller, "fileUtil", new CountingFileMngUtil());
		ReflectionTestUtils.setField(controller, "fileMngService", fileMngService());
		ReflectionTestUtils.setField(controller, "cmmUseService", cmmUseService());

		MultipartFile multipartFile = new MockMultipartFile("file_1", "note.txt", "text/plain",
				"content".getBytes(StandardCharsets.UTF_8));
		Map<String, MultipartFile> files = Collections.singletonMap("file_1", multipartFile);
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) Proxy.newProxyInstance(
				MultipartHttpServletRequest.class.getClassLoader(), new Class<?>[] { MultipartHttpServletRequest.class },
				(proxy, method, args) -> "getFileMap".equals(method.getName()) ? files : null);

		NoteManageVO noteManage = new NoteManageVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(noteManage, "noteManage");
		bindingResult.rejectValue("noteSj", "required");

		Map<String, Object> commandMap = new HashMap<>();
		String viewName = controller.EgovNoteRecptnRegist(request, commandMap, noteManage, bindingResult,
				new ModelMap());

		assertEquals("egovframework/com/uss/ion/ntm/EgovNoteManage", viewName);
		assertEquals(0, parsedFileCount.get());
		assertEquals(0, insertedFileCount.get());
	}

	private EgovFileMngService fileMngService() {
		return (EgovFileMngService) Proxy.newProxyInstance(EgovFileMngService.class.getClassLoader(),
				new Class<?>[] { EgovFileMngService.class }, (proxy, method, args) -> {
					if ("insertFileInfs".equals(method.getName())) {
						insertedFileCount.incrementAndGet();
						return "FILE_1";
					}
					return null;
				});
	}

	private EgovCmmUseService cmmUseService() {
		return new EgovCmmUseService() {
			@Override
			public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.emptyList();
			}

			@Override
			public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<ComDefaultCodeVO> comDefaultCodeVOs) {
				return Collections.emptyMap();
			}

			@Override
			public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.emptyList();
			}

			@Override
			public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.emptyList();
			}
		};
	}

	private class CountingFileMngUtil extends EgovFileMngUtil {
		@Override
		public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String keyStr, int fileKeyParam,
				String atchFileId, String storePath) {
			parsedFileCount.addAndGet(files.size());
			return Collections.singletonList(new FileVO());
		}
	}
}
