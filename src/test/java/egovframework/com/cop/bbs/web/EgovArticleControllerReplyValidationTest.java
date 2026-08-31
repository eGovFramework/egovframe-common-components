package egovframework.com.cop.bbs.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;

/**
 * 답변글 등록의 검증 실패 재표시 회귀 테스트.
 *
 * 답변 화면(EgovArticleReply.jsp)은 부모 게시물의 parnts·sortOrdr·replyLc·nttId를
 * ${result.*}로 hidden에 렌더링한다. result는 커맨드 객체가 아니라서 검증 실패 때
 * 스프링이 다시 채워 주지 않는다. 정상 진입 경로(replyArticleView.do)는 이를 알고
 * result를 모델에 담지만, 같은 화면을 반환하는 검증 실패 분기는 담지 않아 hidden 네
 * 개가 빈 값으로 렌더링됐다. 사용자가 오류만 고쳐 재제출하면 빈 replyLc가
 * Integer.parseInt로 넘어가 NumberFormatException이 난다.
 */
class EgovArticleControllerReplyValidationTest {

	private static final long PARENT_NTT_ID = 1L;

	private static BoardVO parentArticle() {
		BoardVO parent = new BoardVO();
		parent.setNttId(PARENT_NTT_ID);
		parent.setBbsId("BBSMSTR_TEST00001");
		parent.setNttSj("원본 게시물");
		parent.setParnts("0");
		parent.setSortOrdr(0L);
		parent.setReplyLc("0");
		parent.setBlogAt("N");
		return parent;
	}

	private static EgovArticleService articleService(BoardVO detail) {
		return (EgovArticleService) Proxy.newProxyInstance(
				EgovArticleService.class.getClassLoader(),
				new Class<?>[] { EgovArticleService.class },
				(proxy, method, args) -> {
					if ("selectArticleDetail".equals(method.getName())) {
						// 검증 실패 재표시는 이 메서드(내부에서 updateInqireCo 로 조회수 증가)를
						// 부르면 안 된다. 부르면 테스트가 실패하도록 예외를 던진다.
						throw new AssertionError(
								"검증 실패 재표시가 selectArticleDetail 을 호출해 조회수를 올렸다");
					}
					throw new UnsupportedOperationException(method.getName());
				});
	}

	private static EgovBBSMasterService bbsMasterService() {
		BoardMasterVO master = new BoardMasterVO();
		master.setBbsId("BBSMSTR_TEST00001");
		master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		return (EgovBBSMasterService) Proxy.newProxyInstance(
				EgovBBSMasterService.class.getClassLoader(),
				new Class<?>[] { EgovBBSMasterService.class },
				(proxy, method, args) -> {
					if ("selectBBSMasterInf".equals(method.getName())) {
						return master;
					}
					throw new UnsupportedOperationException(method.getName());
				});
	}

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
	}

	private static void bindLoginUser() {
		LoginVO login = new LoginVO();
		login.setUniqId("USRCNFRM_00000000000");
		login.setId("TEST1");
		login.setName("관리자");
		EgovUserDetailsService stub = new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return login;
			}

			@Override
			public List<String> getAuthorities() {
				return Collections.singletonList("ROLE_ADMIN");
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		};
		new EgovUserDetailsHelper().setEgovUserDetailsService(stub);
	}

	private static EgovArticleController controller(BoardVO parent) {
		EgovArticleController controller = new EgovArticleController();
		setPrivateField(controller, "egovArticleService", articleService(parent));
		setPrivateField(controller, "egovBBSMasterService", bbsMasterService());
		return controller;
	}

	private static BoardVO searchVO() {
		// 답변 화면이 hidden 으로 제출하는 부모 라우팅 값들. 재표시는 이것으로 복원한다.
		BoardVO boardVO = new BoardVO();
		boardVO.setBbsId("BBSMSTR_TEST00001");
		boardVO.setNttId(PARENT_NTT_ID);
		boardVO.setParnts("0");
		boardVO.setSortOrdr(0L);
		boardVO.setReplyLc("0");
		return boardVO;
	}

	@Test
	void 검증실패_재표시가_조회수를_올리지_않고_요청값으로_hidden을_복원한다() throws Exception {
		bindLoginUser();
		BoardVO parent = parentArticle();
		ModelMap model = new ModelMap();

		BoardVO submitted = new BoardVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(submitted, "articleVO");
		bindingResult.rejectValue("nttSj", "Size", "제목이 너무 깁니다");

		String view = controller(parent).replyBoardArticle(null, searchVO(), new BoardMasterVO(), submitted,
				bindingResult, model);

		assertEquals("egovframework/com/cop/bbs/EgovArticleReply", view);
		BoardVO result = (BoardVO) model.get("result");
		assertNotNull(result, "재표시 화면의 hidden 네 개가 참조하는 result가 모델에 없다");
		assertEquals(PARENT_NTT_ID, result.getNttId().longValue());
		assertEquals("0", result.getReplyLc());
		assertEquals(0L, result.getSortOrdr().longValue());
		assertEquals("0", result.getParnts());
	}

}
