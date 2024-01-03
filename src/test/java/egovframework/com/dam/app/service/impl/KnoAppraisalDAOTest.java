package egovframework.com.dam.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.app.service.KnoAppraisalVO;
import egovframework.com.dam.map.mat.service.MapMaterial;
import egovframework.com.dam.map.mat.service.impl.MapMaterialDAO;
import egovframework.com.dam.map.tea.service.MapTeam;
import egovframework.com.dam.map.tea.service.impl.MapTeamDAO;
import egovframework.com.dam.per.service.KnoPersonal;
import egovframework.com.dam.per.service.impl.KnoPersonalDAO;
import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.impl.KnoSpecialistDAO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 지식평가관리 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-12-21
 *
 */

@ContextConfiguration(classes = { KnoAppraisalDAOTest.class, EgovTestAbstractDAO.class, })

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/idgn/context-idgn-DamManage.xml",

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.dam.app.service.impl",

				"egovframework.com.dam.per.service.impl",

				"egovframework.com.dam.map.mat.service.impl",

				"egovframework.com.dam.map.tea.service.impl",

				"egovframework.com.dam.spe.spe.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								KnoAppraisalDAO.class,

								KnoPersonalDAO.class,

								MapMaterialDAO.class,

								MapTeamDAO.class,

								KnoSpecialistDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class KnoAppraisalDAOTest extends EgovTestAbstractDAO {

	/**
	 * 템플릿 정보관리를 위한 데이터 접근 클래스
	 */
	@Resource
	private KnoAppraisalDAO knoAppraisalDAO;

	/**
	 * 개인지식정보에 대한 DAO 클래스를 정의한다.
	 */
	@Resource
	private KnoPersonalDAO knoPersonalDAO;

	/**
	 * 지식맵(지식유형)에 대한 DAO 클래스를 정의한다.
	 */
	@Resource
	private MapMaterialDAO mapMaterialDAO;

	/**
	 * 지식맵(조직별)에 대한 DAO 클래스를 정의한다.
	 */
	@Resource
	private MapTeamDAO mapTeamDAO;

	/**
	 * 지식전문가에 대한 DAO 클래스를 정의한다.
	 */
	@Resource
	private KnoSpecialistDAO knoSpecialistDAO;

	/**
	 * `KNWLDG_ID` char(20) NOT NULL COMMENT '지식ID',
	 */
	@Resource(name = "egovDamManageIdGnrService")
	private EgovIdGnrService egovDamManageIdGnrService;

	/**
	 * Debug Result
	 */
	public static final String DEBUG_RESULT = "result={}";

	/**
	 * Debug Result
	 */
	public static final String FAIL_COMMON_INSERT = "fail.common.insert";

	private void testDataKnoPersonal(final KnoPersonal testDataKnoPersonal, final MapMaterial testDataMapMaterial,
			final MapTeam testDataMapTeam) {
		// given
		try {
			testDataKnoPersonal.setKnoId(egovDamManageIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(
					"FdlException egovDamManageIdGnrService " + egovMessageSource.getMessage("fail.common.msg"), e);
		}

		testDataKnoPersonal.setKnoTypeCd(testDataMapMaterial.getKnoTypeCd());

		testDataKnoPersonal.setOrgnztId(testDataMapTeam.getOrgnztId());

		testDataKnoPersonal.setKnoNm("test 이백행 지식명 " + LocalDateTime.now());

		testDataKnoPersonal.setOthbcAt("Y");

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testDataKnoPersonal.setEmplyrId(loginVO.getUniqId());
			testDataKnoPersonal.setFrstRegisterId(loginVO.getUniqId());
			testDataKnoPersonal.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = knoPersonalDAO.insertKnoPersonal(testDataKnoPersonal);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_INSERT), 1, result);
	}

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

	private void testDataMapMaterial(final MapMaterial testDataMapMaterial, final MapTeam testDataMapTeam) {
		// given
		testDataMapMaterial.setKnoTypeCd("001");
		testDataMapMaterial.setOrgnztId(testDataMapTeam.getOrgnztId());

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testDataMapMaterial.setFrstRegisterId(loginVO.getUniqId());
			testDataMapMaterial.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = mapMaterialDAO.insertMapMaterial(testDataMapMaterial);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_INSERT), 1, result);
	}

	private void testDataKnoSpecialist(final KnoSpecialist testDataKnoSpecialist,
			final MapMaterial testDataMapMaterial) {
		// given
		testDataKnoSpecialist.setKnoTypeCd(testDataMapMaterial.getKnoTypeCd());

		testDataKnoSpecialist.setAppTypeCd("1"); // 수석
//		testDataKnoSpecialist.setAppTypeCd("2"); // 책임
//		testDataKnoSpecialist.setAppTypeCd("3"); // 선임

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testDataKnoSpecialist.setSpeId(loginVO.getUniqId());
			testDataKnoSpecialist.setFrstRegisterId(loginVO.getUniqId());
			testDataKnoSpecialist.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = knoSpecialistDAO.insertKnoSpecialist(testDataKnoSpecialist);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_INSERT), 1, result);
	}

	/**
	 * 등록된 지식정보평가 정보를 조회 한다. 테스트
	 */
	@Test
	public void a01selectKnoAppraisalList() {
		// given
		final MapTeam testDataMapTeam = new MapTeam();
		testDataMapTeam(testDataMapTeam);
		final MapMaterial testDataMapMaterial = new MapMaterial();
		testDataMapMaterial(testDataMapMaterial, testDataMapTeam);
		final KnoPersonal testDataKnoPersonal = new KnoPersonal();
		testDataKnoPersonal(testDataKnoPersonal, testDataMapMaterial, testDataMapTeam);
		final KnoSpecialist testDataKnoSpecialist = new KnoSpecialist();
		testDataKnoSpecialist(testDataKnoSpecialist, testDataMapMaterial);

		final KnoAppraisalVO searchVO = new KnoAppraisalVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			searchVO.setEmplyrId(loginVO.getUniqId());
		}

		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		// when
		final List<EgovMap> resultList = knoAppraisalDAO.selectKnoAppraisalList(searchVO);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 등록된 지식정보평가 정보를 조회 한다. 테스트: 지식명
	 */
	@Test
	public void a01selectKnoAppraisalListSearchCondition1() {
		// given
		final MapTeam testDataMapTeam = new MapTeam();
		testDataMapTeam(testDataMapTeam);
		final MapMaterial testDataMapMaterial = new MapMaterial();
		testDataMapMaterial(testDataMapMaterial, testDataMapTeam);
		final KnoPersonal testDataKnoPersonal = new KnoPersonal();
		testDataKnoPersonal(testDataKnoPersonal, testDataMapMaterial, testDataMapTeam);
		final KnoSpecialist testDataKnoSpecialist = new KnoSpecialist();
		testDataKnoSpecialist(testDataKnoSpecialist, testDataMapMaterial);

		final KnoAppraisalVO searchVO = new KnoAppraisalVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			searchVO.setEmplyrId(loginVO.getUniqId());
		}

		searchVO.setSearchCondition("1");
		searchVO.setSearchKeyword(testDataKnoPersonal.getKnoNm());

		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		// when
		final List<EgovMap> resultList = knoAppraisalDAO.selectKnoAppraisalList(searchVO);

		for (final EgovMap result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("knoNm={}, {}", testDataKnoPersonal.getKnoNm(), result.get("knoNm"));
			}
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataKnoPersonal.getKnoNm(),
				resultList.get(0).get("knoNm"));
	}

	/**
	 * 등록된 지식정보평가 정보를 조회 한다. 테스트: 사용자명
	 */
	@Test
	public void a01selectKnoAppraisalListSearchCondition2() {
		// given
		final MapTeam testDataMapTeam = new MapTeam();
		testDataMapTeam(testDataMapTeam);
		final MapMaterial testDataMapMaterial = new MapMaterial();
		testDataMapMaterial(testDataMapMaterial, testDataMapTeam);
		final KnoPersonal testDataKnoPersonal = new KnoPersonal();
		testDataKnoPersonal(testDataKnoPersonal, testDataMapMaterial, testDataMapTeam);
		final KnoSpecialist testDataKnoSpecialist = new KnoSpecialist();
		testDataKnoSpecialist(testDataKnoSpecialist, testDataMapMaterial);

		final KnoAppraisalVO searchVO = new KnoAppraisalVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
//			searchVO.setSearchKeyword(loginVO.getName());
			searchVO.setEmplyrId(loginVO.getUniqId());
		}

		searchVO.setSearchCondition("2");
		searchVO.setSearchKeyword("테스트1");

		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(10);

		// when
		final List<EgovMap> resultList = knoAppraisalDAO.selectKnoAppraisalList(searchVO);

		for (final EgovMap result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("userNm={}, {}", loginVO.getName(), result.get("userNm"));
			}
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

}
