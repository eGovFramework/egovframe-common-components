package egovframework.com.dam.map.mat.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.MapMaterialVO;
import egovframework.com.dam.map.tea.service.MapTeam;
import egovframework.com.dam.map.tea.service.impl.MapTeamDAO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 지식맵(유형별) DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-01-08
 *
 */

@ContextConfiguration(classes = { MapMaterialDAOTest.class, EgovTestAbstractDAO.class, })

@Configuration

@ImportResource({

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.dam.map.mat.service.impl",

				"egovframework.com.dam.map.tea.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								MapMaterialDAO.class,

								MapTeamDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class MapMaterialDAOTest extends EgovTestAbstractDAO {

	/**
	 * 템플릿 정보관리를 위한 데이터 접근 클래스
	 */
	@Resource
	private MapMaterialDAO dao;

	/**
	 * 지식맵(조직별)에 대한 DAO 클래스를 정의한다.
	 */
	@Resource
	private MapTeamDAO mapTeamDAO;

	/**
	 * Debug Result
	 */
	public static final String DEBUG_RESULT = "result={}";

	/**
	 * Debug totCnt
	 */
	public static final String DEBUG_TOT_CNT = "totCnt={}";

	/**
	 * 생성이 실패하였습니다.
	 */
	protected static final String FAIL_COMMON_INSERT = "fail.common.insert";

	private void testDataMapTeam(final MapTeam testDataMapTeam) {
		// given
		testDataMapTeam.setOrgnztId("TEST1_" + EgovDateUtil.toString(new Date(), "yyyyMMddHHmmss", null));
		testDataMapTeam.setOrgnztNm("test 조직명 " + EgovDateUtil.toString(new Date(), "yyyyMMdd", null));

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testDataMapTeam.setFrstRegisterId(loginVO.getUniqId());
			testDataMapTeam.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = mapTeamDAO.insertMapTeam(testDataMapTeam);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_INSERT), 1, result);
	}

	private void testData(final MapMaterial testData, final MapTeam testDataMapTeam) {
		// given
		testData.setKnoTypeCd("000");
		final LocalDateTime now = LocalDateTime.now();
		testData.setOrgnztId(testDataMapTeam.getOrgnztId());
//		testData.setOrgnztId("test 이백행 조직ID " + now);
		testData.setSpeId("test 이백행 전문가ID");
		testData.setKnoTypeNm("test 이백행 지식유형명 " + now);
//		testData.setClYmd(EgovDateUtil.toString(new Date(), "yyyyMMddHHmmss", null));
		testData.setClYmd(EgovDateUtil.toString(new Date(), "yyyy-MM-dd", null));
		testData.setKnoUrl("test 이백행 지식URL " + now);

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testData.setFrstRegisterId(loginVO.getUniqId());
			testData.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = dao.insertMapMaterial(testData);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_INSERT), 1, result);
	}

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다. 테스트
	 */
	@Test
	public void a01selectMapMaterialList() {
		// given
		final MapTeam testDataMapTeam = new MapTeam();
		testDataMapTeam(testDataMapTeam);

		final MapMaterial testData = new MapMaterial();
		testData(testData, testDataMapTeam);

		final MapMaterialVO searchVO = new MapMaterialVO();
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		// when
		final List<MapMaterialVO> resultList = dao.selectMapMaterialList(searchVO);

		debug(resultList, testDataMapTeam, testData);

		// then
		asserta01selectMapMaterialList(resultList, testDataMapTeam, testData);
	}

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다. 테스트
	 */
	@Test
	public void a01selectMapMaterialListSearchCondition1() {
		// given
		final MapTeam testDataMapTeam = new MapTeam();
		testDataMapTeam(testDataMapTeam);

		final MapMaterial testData = new MapMaterial();
		testData(testData, testDataMapTeam);

		final MapMaterialVO searchVO = new MapMaterialVO();
		searchVO.setSearchCondition("1");
		searchVO.setSearchKeyword(testDataMapTeam.getOrgnztNm());

		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		// when
		final List<MapMaterialVO> resultList = dao.selectMapMaterialList(searchVO);

		debug(resultList, testDataMapTeam, testData);

		// then
		asserta01selectMapMaterialList(resultList, testDataMapTeam, testData);
	}

	/**
	 * 등록된 지식맵(지식유형) 정보를 조회 한다. 테스트
	 */
	@Test
	public void a01selectMapMaterialListSearchCondition2() {
		// given
		final MapTeam testDataMapTeam = new MapTeam();
		testDataMapTeam(testDataMapTeam);

		final MapMaterial testData = new MapMaterial();
		testData(testData, testDataMapTeam);

		final MapMaterialVO searchVO = new MapMaterialVO();
		searchVO.setSearchCondition("2");
		searchVO.setSearchKeyword(testData.getKnoTypeNm());

		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		// when
		final List<MapMaterialVO> resultList = dao.selectMapMaterialList(searchVO);

		debug(resultList, testDataMapTeam, testData);

		// then
		asserta01selectMapMaterialList(resultList, testDataMapTeam, testData);
	}

	private void debug(final List<MapMaterialVO> resultList, final MapTeam testDataMapTeam,
			final MapMaterial testData) {
		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (final MapMaterialVO result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getOrgnztNm 조직명={}, {}", testDataMapTeam.getOrgnztNm(), result.getOrgnztNm());
				log.debug("getKnoTypeCd 지식유형코드={}, {}", testData.getKnoTypeCd(), result.getKnoTypeCd());
				log.debug("getSpeId 전문가ID={}, {}", testData.getSpeId(), result.getSpeId());
				log.debug("getKnoTypeNm 지식유형명={}, {}", testData.getKnoTypeNm(), result.getKnoTypeNm());
				log.debug("getClYmd 분류일={}, {}", testData.getClYmd(), result.getClYmd());
				log.debug("getKnoUrl 지식URL={}, {}", testData.getKnoUrl(), result.getKnoUrl());
				log.debug("getFrstRegisterId 최초등록자ID={}, {}", testData.getFrstRegisterId(), result.getFrstRegisterId());
				log.debug("getFrstRegisterPnttm 최초등록시점={}, {}", testData.getFrstRegisterPnttm(),
						result.getFrstRegisterPnttm());
				log.debug("getLastUpdusrId 최종수정자ID={}, {}", testData.getLastUpdusrId(), result.getLastUpdusrId());
				log.debug("getLastUpdusrPnttm 최종수정시점={}, {}", testData.getLastUpdusrPnttm(),
						result.getLastUpdusrPnttm());
			}
		}
	}

	private void asserta01selectMapMaterialList(final List<MapMaterialVO> resultList, final MapTeam testDataMapTeam,
			final MapMaterial testData) {
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 조직명", testDataMapTeam.getOrgnztNm(),
				resultList.get(0).getOrgnztNm());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 지식유형코드", testData.getKnoTypeCd(),
				resultList.get(0).getKnoTypeCd());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 전문가ID", testData.getSpeId(),
				resultList.get(0).getSpeId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 지식유형명", testData.getKnoTypeNm(),
				resultList.get(0).getKnoTypeNm());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 분류일", testData.getClYmd(),
				resultList.get(0).getClYmd());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 지식URL", testData.getKnoUrl(),
				resultList.get(0).getKnoUrl());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 최초등록자ID", testData.getFrstRegisterId(),
				resultList.get(0).getFrstRegisterId());
//		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 최초등록시점", testData.getFrstRegisterPnttm(),
//				resultList.get(0).getFrstRegisterPnttm());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 최종수정자ID", testData.getLastUpdusrId(),
				resultList.get(0).getLastUpdusrId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT) + " 최종수정시점", testData.getLastUpdusrPnttm(),
				resultList.get(0).getLastUpdusrPnttm());
	}

}
