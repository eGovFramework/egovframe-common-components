package egovframework.com.dam.map.mat.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
//		LocalDateTime now = LocalDateTime.now();
		testData.setOrgnztId(testDataMapTeam.getOrgnztId());
//		testData.setOrgnztId("test 이백행 조직ID " + now);

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

		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (final MapMaterialVO result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getOrgnztNm={}", result.getOrgnztNm());
				log.debug("getKnoTypeCd={}", result.getKnoTypeCd());
			}
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

}
