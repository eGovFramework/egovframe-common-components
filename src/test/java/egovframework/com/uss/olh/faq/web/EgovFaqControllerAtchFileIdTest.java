package egovframework.com.uss.olh.faq.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olh.faq.service.EgovFaqService;
import egovframework.com.uss.olh.faq.service.FaqVO;

/**
 * FAQ 수정 저장이 요청으로 받은 첨부 ID 대신 원본의 첨부 ID 를 저장하는지 검증한다.
 *
 * <p>요청값을 그대로 저장하면 남의 첨부 ID 를 자기 글에 심은 뒤 수정 화면을 다시 열어
 * 그 첨부의 삭제 허가(EgovAttachmentGrants)를 받을 수 있다. 저장 패턴이 같은 업무 14곳의 대표 검사다.</p>
 */
class EgovFaqControllerAtchFileIdTest {

	@Test
	void 수정_저장은_요청의_첨부_ID가_아니라_원본의_첨부_ID를_저장한다() throws Exception {
		LoginVO login = new LoginVO();
		login.setUniqId("OWNER");
		new EgovUserDetailsHelper().setEgovUserDetailsService((EgovUserDetailsService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovUserDetailsService.class },
				(proxy, m, a) -> "getAuthenticatedUser".equals(m.getName()) ? login
						: "isAuthenticated".equals(m.getName()) ? Boolean.TRUE : List.of()));

		List<FaqVO> saved = new ArrayList<>();
		EgovFaqController controller = new EgovFaqController();
		ReflectionTestUtils.setField(controller, "egovFaqService", Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovFaqService.class }, (proxy, m, a) -> {
					if ("updateFaq".equals(m.getName())) {
						saved.add((FaqVO) a[0]);
						return null;
					}
					FaqVO stored = new FaqVO();
					stored.setFrstRegisterId("OWNER");
					stored.setAtchFileId("FILE_MINE");
					return stored;
				}));

		FaqVO request = new FaqVO();
		request.setAtchFileId("FILE_OTHERS");
		controller.updateFaqCn(new MockMultipartHttpServletRequest(), new FaqVO(), request,
				new BeanPropertyBindingResult(request, "faqVO"), new ExtendedModelMap());

		assertEquals(1, saved.size());
		assertEquals("FILE_MINE", saved.get(0).getAtchFileId());
	}
}
