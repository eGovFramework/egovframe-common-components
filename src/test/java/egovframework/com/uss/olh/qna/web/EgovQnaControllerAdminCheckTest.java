package egovframework.com.uss.olh.qna.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.cmm.annotation.RequireAdmin;

/**
 * updateQnaAnswerView·updateQnaAnswer에 @RequireAdmin이 붙어있는지 확인한다.
 *
 * Q&A답변관리(@IncludedInfo gid=50)는 관리자가 사용자 문의에 공식 답변을 다는 화면이다.
 * 같은 gid=50 메뉴군의 FAQ(EgovFaqController)는 등록/수정/삭제 다섯 메서드가 모두
 * @RequireAdmin으로 보호되고 행정전문용어사전(EgovAdministrationWordController)도 마찬가지인데,
 * Q&A답변관리는 답변 저장 경로에 관리자 확인이 없었다. 매퍼 updateQnaAnswer도
 * WHERE QA_ID=#{qaId}뿐이라 대상 문의를 제한하지 않는다.
 *
 * @RequireAdmin은 Spring AOP(`@annotation(RequireAdmin)` 포인트컷)로 위빙되므로 컨트롤러를
 * 직접 new해서 부르는 단위 테스트로는 실제 차단 동작을 재현할 수 없다(프록시를 거치지 않음).
 * 이 테스트는 이미 병합된 다른 관리자 검증 테스트들과 같이 "애노테이션이 실제로 붙어있는가"라는
 * 구조적 사실만 고정한다.
 */
class EgovQnaControllerAdminCheckTest {

	private static Method method(String name, Class<?>... paramTypes) throws NoSuchMethodException {
		return EgovQnaController.class.getDeclaredMethod(name, paramTypes);
	}

	@Test
	void answerUpdateViewRequiresAdmin() throws Exception {
		Method m = method("updateQnaAnswerView", egovframework.com.uss.olh.qna.service.QnaVO.class,
				egovframework.com.uss.olh.qna.service.QnaVO.class, org.springframework.ui.ModelMap.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "Q&A 답변 수정화면은 관리자만 열 수 있어야 한다.");
	}

	@Test
	void answerUpdateRequiresAdmin() throws Exception {
		Method m = method("updateQnaAnswer", egovframework.com.uss.olh.qna.service.QnaVO.class,
				org.springframework.validation.BindingResult.class,
				egovframework.com.uss.olh.qna.service.QnaVO.class, org.springframework.ui.Model.class);
		assertTrue(m.isAnnotationPresent(RequireAdmin.class), "Q&A 답변 저장은 관리자만 가능해야 한다.");
	}
}
