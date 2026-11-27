package egovframework.com.dam.map.tea.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import egovframework.com.cmm.LoginVO;
import egovframework.com.dam.map.tea.service.EgovMapTeamService;
import egovframework.com.dam.map.tea.service.MapTeam;

class EgovMapTeamControllerTest {

	private static final String UNIQ_ID = "USRCNFRM_00000000000";

	@Test
	void updateMapTeamSetsLastUpdusrIdBoundByUpdateStatement() throws Exception {
		AtomicReference<MapTeam> updated = new AtomicReference<>();

		EgovMapTeamController controller = new EgovMapTeamController();
		ReflectionTestUtils.setField(controller, "mapTeamService", mapTeamServiceCapturingUpdate(updated));

		LoginVO loginVO = new LoginVO();
		loginVO.setUniqId(UNIQ_ID);

		MapTeam mapTeam = new MapTeam();
		mapTeam.setOrgnztId("ORGNZT_0000000000001");
		BindingResult bindingResult = new BeanPropertyBindingResult(mapTeam, "mapTeam");

		controller.updateMapTeam(loginVO, mapTeam, bindingResult, new ModelMap());

		assertEquals(UNIQ_ID, updated.get().getLastUpdusrId());
	}

	private EgovMapTeamService mapTeamServiceCapturingUpdate(AtomicReference<MapTeam> updated) {
		return (EgovMapTeamService) Proxy.newProxyInstance(EgovMapTeamService.class.getClassLoader(),
				new Class<?>[] { EgovMapTeamService.class }, (proxy, method, args) -> {
					if ("updateMapTeam".equals(method.getName())) {
						updated.set((MapTeam) args[0]);
						return null;
					}

					throw new UnsupportedOperationException(method.toString());
				});
	}
}
