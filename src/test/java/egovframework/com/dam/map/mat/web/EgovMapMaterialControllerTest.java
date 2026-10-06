package egovframework.com.dam.map.mat.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;

class EgovMapMaterialControllerTest {

	private static final String UNIQ_ID = "USRCNFRM_00000000000";

	@AfterEach
	void clearLoginUser() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	@Test
	void updateMapMaterialSetsLastUpdusrIdBoundByUpdateStatement() throws Exception {
		AtomicReference<MapMaterial> updated = new AtomicReference<>();

		EgovMapMaterialController controller = new EgovMapMaterialController();
		ReflectionTestUtils.setField(controller, "mapMaterialService", mapMaterialServiceCapturingUpdate(updated));

		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(UNIQ_ID);
		// 수정 처리는 저장된 지도자료의 등록자가 로그인 사용자인지 먼저 확인한다.
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {
			@Override
			public Object getAuthenticatedUser() {
				return loginVO;
			}

			@Override
			public List<String> getAuthorities() {
				return List.of("ROLE_USER");
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		});

		MapMaterial mapMaterial = new MapMaterial();
		mapMaterial.setKnoTypeCd("KNWLDG_TY_0000000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(mapMaterial, "mapMaterial");

		controller.updateMapMaterial(new MapMaterialVO(), mapMaterial, bindingResult, new ModelMap());

		assertEquals(UNIQ_ID, updated.get().getLastUpdusrId());
	}

	private EgovMapMaterialService mapMaterialServiceCapturingUpdate(AtomicReference<MapMaterial> updated) {
		return (EgovMapMaterialService) Proxy.newProxyInstance(EgovMapMaterialService.class.getClassLoader(),
				new Class<?>[] { EgovMapMaterialService.class }, (proxy, method, args) -> {
					if ("updateMapMaterial".equals(method.getName())) {
						updated.set((MapMaterial) args[0]);
						return null;
					}
					if ("selectMapMaterial".equals(method.getName())) {
						MapMaterial stored = new MapMaterial();
						stored.setFrstRegisterId(UNIQ_ID);
						return stored;
					}

					throw new UnsupportedOperationException(method.toString());
				});
	}
}
