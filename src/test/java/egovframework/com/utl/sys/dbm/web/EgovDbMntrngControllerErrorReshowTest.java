package egovframework.com.utl.sys.dbm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.dbm.service.DbMntrngLog;
import egovframework.com.utl.sys.dbm.service.EgovDbMntrngService;

/**
 * DB서비스모니터링 등록({@link EgovDbMntrngController#insertDbMntrng})의 검증 실패 분기가,
 * 형제 진입 경로({@code selectDbMntrngForRegist})·형제 수정 경로({@code updateDbMntrng})처럼
 * 재표시 폼이 참조하는 {@code searchVO}(목록 검색조건 유지용)를 model에 복원하는지 검증한다.
 *
 * <p>재표시 JSP는 {@code searchCondition}·{@code searchKeyword} hidden 필드를 모두
 * {@code ${searchVO.searchCondition}}·{@code ${searchVO.searchKeyword}}에서 읽는다.
 * 검증 실패 분기가 {@code searchVO}를 복원하지 않으면 목록에서 검색해 들어온 등록 화면이
 * 검증 오류로 재표시될 때 원래 검색조건이 사라진다.</p>
 */
class EgovDbMntrngControllerErrorReshowTest {

	private static final class StubService implements EgovDbMntrngService {
		@Override
		public void deleteDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void insertDbMntrngLog(DbMntrngLog dbMntrngLog) {
			throw new UnsupportedOperationException();
		}

		/** 중복체크(checkDuplication)가 이 호출로 추가 오류를 얹지 않도록 null(중복없음)을 돌려준다. */
		@Override
		public DbMntrng selectDbMntrng(DbMntrng dbMntrng) {
			return null;
		}

		@Override
		public DbMntrngLog selectDbMntrngLog(DbMntrngLog dbMntrngLog) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<DbMntrng> selectDbMntrngList(DbMntrng searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectDbMntrngListCnt(DbMntrng searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public List<DbMntrngLog> selectDbMntrngLogList(DbMntrngLog searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int selectDbMntrngLogListCnt(DbMntrngLog searchVO) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void updateDbMntrng(DbMntrng dbMntrng) {
			throw new UnsupportedOperationException();
		}
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

	/** getInt(...)에 10을 돌려주는 것 외엔 페이징 계산에 관여하지 않는 최소 프록시. */
	private static org.egovframe.rte.fdl.property.EgovPropertyService noopPropertiesService() {
		return (org.egovframe.rte.fdl.property.EgovPropertyService) java.lang.reflect.Proxy.newProxyInstance(
				EgovDbMntrngControllerErrorReshowTest.class.getClassLoader(),
				new Class<?>[] { org.egovframe.rte.fdl.property.EgovPropertyService.class },
				(proxy, method, args) -> {
					Class<?> returnType = method.getReturnType();
					if (returnType == int.class) {
						return 10;
					}
					return null;
				});
	}

	/** referenceData(model)가 부르는 selectCmmCodeDetail만 빈 목록으로 응답하는 최소 프록시. */
	private static egovframework.com.cmm.service.EgovCmmUseService noopCmmUseService() {
		return (egovframework.com.cmm.service.EgovCmmUseService) java.lang.reflect.Proxy.newProxyInstance(
				EgovDbMntrngControllerErrorReshowTest.class.getClassLoader(),
				new Class<?>[] { egovframework.com.cmm.service.EgovCmmUseService.class },
				(proxy, method, args) -> List.of());
	}

	private EgovDbMntrngController newControllerWithStub() {
		EgovDbMntrngController controller = new EgovDbMntrngController();
		setPrivateField(controller, "egovDbMntrngService", new StubService());
		setPrivateField(controller, "propertyService", noopPropertiesService());
		setPrivateField(controller, "cmmUseService", noopCmmUseService());
		return controller;
	}

	private BindingResult failingBindingResult(DbMntrng target) {
		BindingResult bindingResult = new BeanPropertyBindingResult(target, "dbMntrng");
		bindingResult.reject("validation.error");
		return bindingResult;
	}

	private static void bindLoginUser() {
		LoginVO login = new LoginVO();
		login.setUniqId("USRCNFRM_00000000001");
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

	@Test
	void insertDbMntrng_restoresSearchVOOnValidationError() throws Exception {
		bindLoginUser();
		EgovDbMntrngController controller = newControllerWithStub();
		DbMntrng searchVO = new DbMntrng();
		searchVO.setSearchCondition("dataSourcNm");
		searchVO.setSearchKeyword("mysql");
		DbMntrng submitted = new DbMntrng();
		ExtendedModelMap model = new ExtendedModelMap();

		String view = controller.insertDbMntrng(searchVO, submitted, failingBindingResult(submitted), model,
				new RedirectAttributesModelMap());

		assertEquals("egovframework/com/utl/sys/dbm/EgovDbMntrngRegist", view);
		assertNotNull(model.get("searchVO"),
				"검증 실패 재표시 시 searchVO가 model에 있어야 목록 검색조건(searchCondition·searchKeyword)이 보존된다");
		assertEquals("mysql", ((DbMntrng) model.get("searchVO")).getSearchKeyword());
	}
}
