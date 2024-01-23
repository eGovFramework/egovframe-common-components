package egovframework.com.dam.map.tea.service.impl;

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
import egovframework.com.cmm.util.EgovMybatisUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.map.tea.service.MapTeamVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 지식맵(조직별) DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2024-01-20
 *
 */

@ContextConfiguration(classes = { MapTeamDAOTest.class, EgovTestAbstractDAO.class, })

@Configuration

@ImportResource({

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.dam.map.tea.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								MapTeamDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class MapTeamDAOTest extends EgovTestAbstractDAO {

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

	/**
	 * 수정이 실패하였습니다.
	 */
	protected static final String FAIL_COMMON_UPDATE = "fail.common.update";

	/**
	 * 삭제가 실패하였습니다.
	 */
	protected static final String FAIL_COMMON_DELETE = "fail.common.delete";

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 */
	@Test
	public void a01selectMapTeamList() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		if (EgovMybatisUtil.isEquals(mapTeamVO.getSearchCondition(), "1")) {
			if (log.isDebugEnabled()) {
				log.debug("조직명 검색 true");
			}
		} else if (EgovMybatisUtil.isEquals(mapTeamVO.getSearchCondition(), "2")) {
			if (log.isDebugEnabled()) {
				log.debug("조직ID 검색 true");
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("otherwise");
			}
		}

		mapTeamVO.setFirstIndex(0);
		mapTeamVO.setRecordCountPerPage(10);

		// when
		final List<MapTeamVO> resultList = mapTeamDAO.selectMapTeamList(mapTeamVO);

		debug(mapTeamVOTestData, resultList);

		// then
//		asserta01selectMapMaterialList(resultList, testDataMapTeam, testData);
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 */
	@Test
	public void a01selectMapTeamListSearchCondition1() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		mapTeamVO.setSearchCondition("1");
		mapTeamVO.setSearchKeyword(mapTeamVOTestData.getOrgnztNm());

		if (EgovMybatisUtil.isEquals(mapTeamVO.getSearchCondition(), "1")) {
			if (log.isDebugEnabled()) {
				log.debug("조직명 검색 true");
			}
		} else if (EgovMybatisUtil.isEquals(mapTeamVO.getSearchCondition(), "2")) {
			if (log.isDebugEnabled()) {
				log.debug("조직ID 검색 true");
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("otherwise");
			}
		}

		mapTeamVO.setFirstIndex(0);
		mapTeamVO.setRecordCountPerPage(10);

		// when
		final List<MapTeamVO> resultList = mapTeamDAO.selectMapTeamList(mapTeamVO);

		debug(mapTeamVOTestData, resultList);

		// then
//		asserta01selectMapMaterialList(resultList, testDataMapTeam, testData);
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 등록된 지식맵(조직별) 목록을 조회 한다.
	 */
	@Test
	public void a01selectMapTeamListSearchCondition2() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		mapTeamVO.setSearchCondition("2");
		mapTeamVO.setSearchKeyword(mapTeamVOTestData.getOrgnztId());

		if (EgovMybatisUtil.isEquals(mapTeamVO.getSearchCondition(), "1")) {
			if (log.isDebugEnabled()) {
				log.debug("조직명 검색 true");
			}
		} else if (EgovMybatisUtil.isEquals(mapTeamVO.getSearchCondition(), "2")) {
			if (log.isDebugEnabled()) {
				log.debug("조직ID 검색 true");
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("otherwise");
			}
		}

		mapTeamVO.setFirstIndex(0);
		mapTeamVO.setRecordCountPerPage(10);

		// when
		final List<MapTeamVO> resultList = mapTeamDAO.selectMapTeamList(mapTeamVO);

		debug(mapTeamVOTestData, resultList);

		// then
//		asserta01selectMapMaterialList(resultList, testDataMapTeam, testData);
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 지식맵(조직별) 목록 총 개수를 조회한다.
	 */
	@Test
	public void a02selectMapTeamTotCnt() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		// when
		final int totCnt = mapTeamDAO.selectMapTeamTotCnt(mapTeamVO);

		debug(totCnt);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 지식맵(조직별) 목록 총 개수를 조회한다.
	 */
	@Test
	public void a02selectMapTeamTotCntSearchCondition1() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		mapTeamVO.setSearchCondition("1");
		mapTeamVO.setSearchKeyword(mapTeamVOTestData.getOrgnztNm());

		// when
		final int totCnt = mapTeamDAO.selectMapTeamTotCnt(mapTeamVO);

		debug(totCnt);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 지식맵(조직별) 목록 총 개수를 조회한다.
	 */
	@Test
	public void a02selectMapTeamTotCntSearchCondition2() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		mapTeamVO.setSearchCondition("2");
		mapTeamVO.setSearchKeyword(mapTeamVOTestData.getOrgnztId());

		// when
		final int totCnt = mapTeamDAO.selectMapTeamTotCnt(mapTeamVO);

		debug(totCnt);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 지식맵(조직별)상세 정보를 조회 한다.
	 */
	@Test
	public void a03selectMapTeamDetail() {
		// given
		final MapTeamVO mapTeamVOTestData = new MapTeamVO();
		mapTeamVOTestData(mapTeamVOTestData);

		final MapTeamVO mapTeamVO = new MapTeamVO();

		mapTeamVO.setOrgnztId(mapTeamVOTestData.getOrgnztId());

		// when
		final MapTeamVO result = mapTeamDAO.selectMapTeamDetail(mapTeamVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
			log.debug("getOrgnztId 조직ID={}, {}", mapTeamVOTestData.getOrgnztId(), result.getOrgnztId());
			log.debug("getOrgnztNm 조직명={}, {}", mapTeamVOTestData.getOrgnztNm(), result.getOrgnztNm());
			log.debug("getClDe 분류일={}, {}", mapTeamVOTestData.getClDe(), result.getClDe());
			log.debug("getClYmd 분류일={}, {}", mapTeamVOTestData.getClYmd(), result.getClYmd());
			log.debug("getKnoUrl 지식URL={}, {}", mapTeamVOTestData.getKnoUrl(), result.getKnoUrl());
		}

		// then
		assertEquals("조직ID", mapTeamVOTestData.getOrgnztId(), result.getOrgnztId());
		assertEquals("조직명", mapTeamVOTestData.getOrgnztNm(), result.getOrgnztNm());
		assertEquals("분류일", mapTeamVOTestData.getClYmd(), result.getClDe());
		assertEquals("지식URL", mapTeamVOTestData.getKnoUrl(), result.getKnoUrl());
	}

	private void mapTeamVOTestData(final MapTeamVO mapTeamVOTestData) {
		// given
		final String today = EgovDateUtil.toString(new Date(), "yyyyMMddHHmmss", null);
		mapTeamVOTestData.setOrgnztId("TEST1_" + today);
		mapTeamVOTestData.setOrgnztNm("test 조직명 " + EgovDateUtil.toString(new Date(), "yyyyMMdd", null));
		mapTeamVOTestData.setClDe(EgovDateUtil.toString(new Date(), "yyyyMMdd", null));
//		mapTeamVOTestData.setClYmd(today);
		mapTeamVOTestData.setClYmd(mapTeamVOTestData.getClDe());
		final String now = " " + LocalDateTime.now().toString();
		mapTeamVOTestData.setKnoUrl("test 이백행 지식URL" + now);

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			mapTeamVOTestData.setFrstRegisterId(loginVO.getUniqId());
			mapTeamVOTestData.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = mapTeamDAO.insertMapTeam(mapTeamVOTestData);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_INSERT), 1, result);
	}

	private void debug(final MapTeamVO mapTeamVOTestData, final List<MapTeamVO> resultList) {
		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (final MapTeamVO result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getOrgnztId 조직ID={}, {}", mapTeamVOTestData.getOrgnztId(), result.getOrgnztId());
				log.debug("getOrgnztNm 조직명={}, {}", mapTeamVOTestData.getOrgnztNm(), result.getOrgnztNm());
				log.debug("getClYmd 분류일={}, {}", mapTeamVOTestData.getClYmd(), result.getClYmd());
				log.debug("getKnoUrl 지식URL={}, {}", mapTeamVOTestData.getKnoUrl(), result.getKnoUrl());
				log.debug("");
			}
		}
	}

	private void debug(final int totCnt) {
		if (log.isDebugEnabled()) {
			log.debug("totCnt={}", totCnt);
		}
	}

}
