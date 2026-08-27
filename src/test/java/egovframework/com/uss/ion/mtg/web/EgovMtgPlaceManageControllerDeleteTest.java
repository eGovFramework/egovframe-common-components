package egovframework.com.uss.ion.mtg.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.support.SimpleSessionStatus;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.uss.ion.mtg.service.EgovMtgPlaceManageService;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;

/**
 * 회의실 삭제가 그 회의실의 예약을 남겨두지 않는지 확인한다.
 *
 * <p>예약 조회는 회의실을 기준 테이블로 삼고(EgovMtgPlaceManage_SQL_mysql.xml 매퍼의
 * selectMtgPlaceResveManageList), 예약 삭제 SQL 은 예약ID 를 반드시 요구한다. 회의실이 먼저 없어지면
 * 그 예약은 목록에도 상세에도 나오지 않아 화면에서 지울 방법이 없다.</p>
 */
class EgovMtgPlaceManageControllerDeleteTest {

	private EgovMtgPlaceManageController controller;
	private final AtomicBoolean deleted = new AtomicBoolean(false);
	private int resveCnt;

	@BeforeEach
	void setUp() {
		deleted.set(false);

		EgovMtgPlaceManageService service = (EgovMtgPlaceManageService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovMtgPlaceManageService.class },
				(proxy, method, args) -> {
					switch (method.getName()) {
					case "selectMtgPlaceResveCnt":
						return Integer.valueOf(resveCnt);
					case "deleteMtgPlaceManage":
						deleted.set(true);
						return null;
					default:
						return null;
					}
				});

		EgovFileMngService fileMngService = (EgovFileMngService) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class<?>[] { EgovFileMngService.class },
				(proxy, method, args) -> null);

		EgovMessageSource messageSource = new EgovMessageSource();
		ReloadableResourceBundleMessageSource messages = new ReloadableResourceBundleMessageSource();
		messages.setUseCodeAsDefaultMessage(true);
		messageSource.setReloadableResourceBundleMessageSource(messages);

		controller = new EgovMtgPlaceManageController();
		ReflectionTestUtils.setField(controller, "egovMtgPlaceManageService", service);
		ReflectionTestUtils.setField(controller, "fileMngService", fileMngService);
		ReflectionTestUtils.setField(controller, "egovMessageSource", messageSource);
	}

	@Test
	void deleteMtgPlaceIsRefusedWhenRoomStillHasReservation() throws Exception {
		resveCnt = 1;
		ModelMap model = new ModelMap();

		controller.deleteMtgPlaceManage(mtgPlace(), new SimpleSessionStatus(), model);

		assertFalse(deleted.get(), "예약이 남아 있으면 회의실을 지우지 않아야 한다");
		assertEquals("true", model.get("mtgPlaceResveExist"), "화면에 알릴 표시가 있어야 한다");
	}

	@Test
	void deleteMtgPlaceProceedsWhenRoomHasNoReservation() throws Exception {
		resveCnt = 0;
		ModelMap model = new ModelMap();

		controller.deleteMtgPlaceManage(mtgPlace(), new SimpleSessionStatus(), model);

		assertTrue(deleted.get(), "예약이 없는 회의실은 지울 수 있어야 한다");
	}

	private MtgPlaceManageVO mtgPlace() {
		MtgPlaceManageVO mtgPlaceManageVO = new MtgPlaceManageVO();
		mtgPlaceManageVO.setMtgPlaceId("MTGRUM_00000000000001");
		mtgPlaceManageVO.setAtchFileId("MTG_0000000000000001");
		return mtgPlaceManageVO;
	}
}
