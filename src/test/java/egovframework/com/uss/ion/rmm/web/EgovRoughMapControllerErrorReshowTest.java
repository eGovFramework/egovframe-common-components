package egovframework.com.uss.ion.rmm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.rmm.service.RoughMapVO;

/**
 * 약도 수정 검증실패 재표시 회귀 테스트.
 *
 * 형제 진입 경로 goRoughMapUpdt는 EgovRoughMapUpdt 뷰를 그리기 전에 model에 result를
 * 담는데, 검증 실패 시 같은 뷰로 되돌아가는 updateRoughMap은 ModelMap 파라미터 자체가
 * 없어 아무것도 담지 못한다. JSP는 &lt;input name="roughMapId" value="${result.roughMapId}"&gt;
 * 처럼 결과 PK·지도 좌표(la·lo·markerLa·markerLo)·주소·확대수준을 전부 result에서 읽으므로,
 * 재표시된 화면에서 이 값들이 통째로 빈 문자열이 된다.
 */
class EgovRoughMapControllerErrorReshowTest {

	private static void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (ReflectiveOperationException e) {
			throw new IllegalStateException(e);
		}
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

	@Test
	void updateWithValidationErrorsRestoresResultForReshow() throws Exception {
		EgovRoughMapController controller = new EgovRoughMapController();
		setPrivateField(controller, "egovRoughMapService", null);
		bindLoginUser("USRCNFRM_00000000001");

		RoughMapVO roughMap = new RoughMapVO();
		roughMap.setRoughMapId("1");
		roughMap.setRoughMapAddress("서울특별시 종로구 세종대로 209");
		roughMap.setLa("37.5665");
		roughMap.setLo("126.9780");
		roughMap.setMarkerLa("37.5665");
		roughMap.setMarkerLo("126.9780");
		roughMap.setZoomLevel("3");
		BindingResult bindingResult = new BeanPropertyBindingResult(roughMap, "roughMap");
		bindingResult.reject("roughMapSj", "필수 입력값입니다.");
		ModelMap model = new ModelMap();

		String view = controller.updateRoughMap(roughMap, bindingResult, model);

		assertEquals("egovframework/com/uss/ion/rmm/EgovRoughMapUpdt", view);
		RoughMapVO result = (RoughMapVO) model.get("result");
		assertEquals("1", result.getRoughMapId(),
				"검증실패 재표시 화면의 hidden 필드가 참조하는 result.roughMapId가 제출값 그대로 있어야 한다.");
		assertEquals("서울특별시 종로구 세종대로 209", result.getRoughMapAddress());
		assertEquals("37.5665", result.getLa());
		assertEquals("126.9780", result.getLo());
	}
}
