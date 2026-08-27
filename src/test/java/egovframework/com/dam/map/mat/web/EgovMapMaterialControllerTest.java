package egovframework.com.dam.map.mat.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.dam.map.mat.service.EgovMapMaterialService;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;

class EgovMapMaterialControllerTest {

	private static final String UNIQ_ID = "USRCNFRM_00000000000";

	@Test
	void updateMapMaterialSetsLastUpdusrIdBoundByUpdateStatement() throws Exception {
		AtomicReference<MapMaterial> updated = new AtomicReference<>();

		EgovMapMaterialController controller = new EgovMapMaterialController();
		ReflectionTestUtils.setField(controller, "mapMaterialService", mapMaterialServiceCapturingUpdate(updated));

		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(UNIQ_ID);

		MapMaterial mapMaterial = new MapMaterial();
		mapMaterial.setKnoTypeCd("KNWLDG_TY_0000000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(mapMaterial, "mapMaterial");

		controller.updateMapMaterial(loginVO, new MapMaterialVO(), mapMaterial, bindingResult, new ModelMap());

		assertEquals(UNIQ_ID, updated.get().getLastUpdusrId());
	}

	private EgovMapMaterialService mapMaterialServiceCapturingUpdate(AtomicReference<MapMaterial> updated) {
		return (EgovMapMaterialService) Proxy.newProxyInstance(EgovMapMaterialService.class.getClassLoader(),
				new Class<?>[] { EgovMapMaterialService.class }, (proxy, method, args) -> {
					if ("updateMapMaterial".equals(method.getName())) {
						updated.set((MapMaterial) args[0]);
						return null;
					}

					throw new UnsupportedOperationException(method.toString());
				});
	}
}
