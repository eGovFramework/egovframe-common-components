package egovframework.com.uss.olp.cns.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;
import egovframework.com.uss.olp.cns.service.EgovCnsltManageService;

/**
 * 상담 수정({@link EgovCnsltManageController#updateCnsltDtls})과 답변 수정
 * ({@code updateCnsltDtlsAnswer})의 검증 실패 분기가, 정상 표시 경로처럼 재표시 JSP가 참조하는
 * {@code result}(수정 대상 식별자 포함)를 model에 복원하는지 검증한다.
 *
 * <p>재표시 JSP는 hidden {@code cnsltId}와 onSubmit 스크립트 인자를 모두 {@code ${result.cnsltId}}에서
 * 읽는다. 검증 실패 분기가 {@code result}를 복원하지 않으면 식별자가 비어, 사용자가 오류를 고쳐
 * 재제출할 때 빈 식별자로 {@code WHERE CNSLT_ID=''} 갱신이 실행되어 수정이 조용히 사라진다.</p>
 *
 * <p>수정 전 코드에서는 {@code result}가 담기지 않아 두 테스트가 모두 실패한다.</p>
 */
class EgovCnsltManageControllerValidationRestoreTest {

	/** selectCnsltListDetail은 요청 식별자를 그대로 되돌려주고, selectCmmCodeDetail은 빈 목록을 돌려주는 스텁. */
	private final InvocationHandler stub = (proxy, method, args) -> {
		switch (method.getName()) {
		case "selectCnsltListDetail":
			CnsltManageVO vo = new CnsltManageVO();
			vo.setCnsltId(((CnsltManageVO) args[0]).getCnsltId());
			return vo;
		case "selectCmmCodeDetail":
			return Collections.emptyList();
		default:
			return null;
		}
	};

	private EgovCnsltManageController newControllerWithStubs() {
		EgovCnsltManageController controller = new EgovCnsltManageController();
		ClassLoader cl = getClass().getClassLoader();
		EgovCnsltManageService cnsltSvc = (EgovCnsltManageService) Proxy.newProxyInstance(cl,
				new Class[] { EgovCnsltManageService.class }, stub);
		EgovCmmUseService cmmSvc = (EgovCmmUseService) Proxy.newProxyInstance(cl,
				new Class[] { EgovCmmUseService.class }, stub);
		ReflectionTestUtils.setField(controller, "cnsltManageService", cnsltSvc);
		ReflectionTestUtils.setField(controller, "cmmUseService", cmmSvc);
		return controller;
	}

	private BindingResult failingBindingResult(CnsltManageVO target) {
		BindingResult bindingResult = new BeanPropertyBindingResult(target, "cnsltManageVO");
		bindingResult.reject("validation.error");
		return bindingResult;
	}

	@Test
	@DisplayName("상담 수정 검증 실패 시 재표시 폼이 참조하는 result(대상 식별자)를 복원한다")
	void updateCnsltDtls_restoresResultOnValidationError() throws Exception {
		EgovCnsltManageController controller = newControllerWithStubs();
		CnsltManageVO submitted = new CnsltManageVO();
		submitted.setCnsltId("CNSLT_X");
		ExtendedModelMap model = new ExtendedModelMap();

		String view = controller.updateCnsltDtls("N", null, new CnsltManageDefaultVO(), submitted,
				failingBindingResult(submitted), model);

		assertEquals("egovframework/com/uss/olp/cns/EgovCnsltDtlsUpdt", view);
		assertNotNull(model.get("result"), "검증 실패 재표시 시 result가 model에 있어야 cnsltId가 보존된다");
		assertEquals("CNSLT_X", ((CnsltManageVO) model.get("result")).getCnsltId());
	}

	@Test
	@DisplayName("답변 수정 검증 실패 시 재표시 폼이 참조하는 result(대상 식별자)를 복원한다")
	void updateCnsltDtlsAnswer_restoresResultOnValidationError() throws Exception {
		EgovCnsltManageController controller = newControllerWithStubs();
		CnsltManageVO submitted = new CnsltManageVO();
		submitted.setCnsltId("CNSLT_Y");
		ExtendedModelMap model = new ExtendedModelMap();

		String view = controller.updateCnsltDtlsAnswer(submitted, failingBindingResult(submitted),
				new CnsltManageDefaultVO(), model);

		assertEquals("egovframework/com/uss/olp/cns/EgovCnsltDtlsAnswerUpdt", view);
		assertNotNull(model.get("result"), "검증 실패 재표시 시 result가 model에 있어야 cnsltId가 보존된다");
		assertEquals("CNSLT_Y", ((CnsltManageVO) model.get("result")).getCnsltId());
	}
}
