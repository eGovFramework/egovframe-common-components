package egovframework.com.cop.bbs.web;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.exception.EgovXssException;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;

/**
 * 존재하지 않는 게시물에 대한 XSS 권한체크 회귀 테스트.
 *
 * EgovArticleServiceImpl.selectArticleDetail은 형제 서비스들과 달리 조회 결과가 없을 때 null을
 * 그대로 돌려준다. 권한체크는 그 결과를 역참조해 EgovXssChecker에 넘기므로, 이미 삭제된 글 번호로
 * 요청하면 XSS00001 대신 NullPointerException이 났다.
 */
class EgovArticleControllerXssCheckTest {

	@Test
	void deleteBoardArticle_missingArticle_throwsXssExceptionInsteadOfNpe() throws Exception {
		bindLoginUser("USRCNFRM_00000000001");

		EgovArticleController controller = new EgovArticleController();
		setField(controller, "egovArticleService", nullReturningArticleService());

		assertThrows(EgovXssException.class,
				() -> controller.deleteBoardArticle(stubRequest(), new BoardVO(), new Board(), new BoardMaster(),
						new ModelMap()),
				"존재하지 않는 게시물은 NPE가 아니라 권한 오류로 끝나야 한다");
	}

	/** 조회 결과가 없어 selectArticleDetail이 null을 돌려주는 상황을 재현한다. */
	private static EgovArticleService nullReturningArticleService() {
		return (EgovArticleService) Proxy.newProxyInstance(EgovArticleService.class.getClassLoader(),
				new Class<?>[] { EgovArticleService.class }, (proxy, method, args) -> null);
	}

	private static HttpServletRequest stubRequest() {
		return (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(),
				new Class<?>[] { HttpServletRequest.class }, (proxy, method, args) -> null);
	}

	private static void bindLoginUser(String uniqId) {
		LoginVO login = new LoginVO();
		login.setUniqId(uniqId);
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static void setField(Object target, String name, Object value) throws Exception {
		Field field = EgovArticleController.class.getDeclaredField(name);
		field.setAccessible(true);
		field.set(target, value);
	}
}
