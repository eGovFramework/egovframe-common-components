package egovframework.com.cop.tpl.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.impl.EgovBBSMasterDAO;
import egovframework.com.cop.tpl.service.TemplateInf;
import egovframework.com.cop.tpl.service.TemplateInfVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-12-21
 *
 */

@ContextConfiguration(classes = { TemplateManageDAOTest.class, EgovTestAbstractDAO.class, })

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/idgn/context-idgn-Tmplat.xml",

		"classpath*:egovframework/spring/com/idgn/context-idgn-bbs.xml",

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.cop.tpl.service.impl",

				"egovframework.com.cop.bbs.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								TemplateManageDAO.class,

								EgovBBSMasterDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class TemplateManageDAOTest extends EgovTestAbstractDAO {

	/**
	 * 템플릿 정보관리를 위한 데이터 접근 클래스
	 */
	@Resource
	private TemplateManageDAO templateManageDAO;

	/**
	 * 템플릿 ID
	 */
	@Resource(name = "egovTmplatIdGnrService")
	private EgovIdGnrService egovTmplatIdGnrService;

	/**
	 * EgovBBSMasterDAO
	 */
	@Resource
	private EgovBBSMasterDAO egovBBSMasterDAO;

	/**
	 * 게시판마스터 ID
	 */
	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService egovBBSMstrIdGnrService;

	/**
	 * Debug Result
	 */
	public static final String DEBUG_RESULT = "result={}";

	/**
	 * Debug totCnt
	 */
	public static final String DEBUG_TOT_CNT = "totCnt={}";

	/**
	 * 템플릿 정보를 삭제한다. 테스트
	 */
	@Test
	public void a01deleteTemplateInf() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final BoardMaster testDataBoardMaster = new BoardMaster();
		testDataBoardMaster(testDataBoardMaster, testData);

		final TemplateInf tmplatInf = new TemplateInf();

		tmplatInf.setTmplatId(testData.getTmplatId());

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			tmplatInf.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = templateManageDAO.deleteTemplateInf(tmplatInf);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertTrue(egovMessageSource.getMessage("fail.common.delete"), result > 0);
	}

	private void testData(final TemplateInf testData) {
		// given
		setTmplatId(testData);

		testData.setTmplatNm("test 이백행 템플릿명 " + LocalDateTime.now());
		testData.setTmplatSeCode("TMPT01");
		testData.setTmplatCours("/test/css/egovframework/com/cop/tpl/egovbbsTemplate.css");
		testData.setUseAt("Y");

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testData.setFrstRegisterId(loginVO.getUniqId());
		}

		// when
		final int result = templateManageDAO.insertTemplateInf(testData);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

	private void setTmplatId(final TemplateInf testData) {
		try {
			testData.setTmplatId(egovTmplatIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(
					"FdlException egovTmplatIdGnrService " + egovMessageSource.getMessage("fail.common.msg"), e);
		}
	}

	private void testDataBoardMaster(final BoardMaster testDataBoardMaster, final TemplateInf testData) {
		// given
		try {
			testDataBoardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(
					"FdlException egovBBSMstrIdGnrService " + egovMessageSource.getMessage("fail.common.msg"), e);
		}

		testDataBoardMaster.setBbsTyCode("BBST01"); // COM101 BBST01 통합게시판
		testDataBoardMaster.setBbsNm("");
		testDataBoardMaster.setBbsIntrcn("");
		testDataBoardMaster.setReplyPosblAt("");
		testDataBoardMaster.setFileAtchPosblAt("");
		testDataBoardMaster.setAtchPosblFileNumber(0);
		testDataBoardMaster.setTmplatId(testData.getTmplatId());
		testDataBoardMaster.setUseAt("");
		testDataBoardMaster.setCmmntyId("");
		testDataBoardMaster.setFrstRegisterId("");
		testDataBoardMaster.setBlogId("");
		testDataBoardMaster.setBlogAt("");

		// when
		egovBBSMasterDAO.insertBBSMasterInf(testDataBoardMaster);

		// then
	}

	/**
	 * 템플릿 정보를 등록한다. 테스트
	 */
	@Test
	public void a02insertTemplateInf() {
		// given
		final TemplateInf templateInf = new TemplateInf();
		setTmplatId(templateInf);
		templateInf.setTmplatNm("test 이백행 템플릿명 " + LocalDateTime.now());
		templateInf.setTmplatSeCode("TMPT01");
		templateInf.setTmplatCours("/test/css/egovframework/com/cop/tpl/egovbbsTemplate.css");
		templateInf.setUseAt("Y");

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			templateInf.setFrstRegisterId(loginVO.getUniqId());
		}

		// when
		final int result = templateManageDAO.insertTemplateInf(templateInf);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

	/**
	 * 템플릿 정보를 수정한다. 테스트
	 */
	@Test
	public void a03updateTemplateInf() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInf tmplatInf = new TemplateInf();
		tmplatInf.setTmplatSeCode("TMPT02");
		tmplatInf.setTmplatCours("/test2/css/egovframework/com/cop/tpl/egovbbsTemplate.css");
		tmplatInf.setUseAt("N");

		tmplatInf.setTmplatId(testData.getTmplatId());

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			tmplatInf.setLastUpdusrId(loginVO.getUniqId());
		}

		// when
		final int result = templateManageDAO.updateTemplateInf(tmplatInf);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.update"), result, 1);
	}

	/**
	 * 템플릿에 대한 화이트리스트 목록를 조회한다. 테스트
	 */
	@Test
	public void a04selectTemplateWhiteList() {
		// given

		// when
		final List<TemplateInfVO> resultList = templateManageDAO.selectTemplateWhiteList();

		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
			for (final TemplateInfVO result : resultList) {
				log.debug("result={}", result);
				log.debug("getTmplatCours={}", result.getTmplatCours());
			}
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 템플릿에 대한 목록를 조회한다. 테스트
	 */
	@Test
	public void a05selectTemplateInfs() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInfVO tmplatInfVO = new TemplateInfVO();
		tmplatInfVO.setFirstIndex(0);
		tmplatInfVO.setRecordCountPerPage(10);

		// when
		final List<TemplateInfVO> resultList = templateManageDAO.selectTemplateInfs(tmplatInfVO);

		// then
		asserta05selectTemplateInfsassert(resultList, testData);
	}

	private void asserta05selectTemplateInfsassert(final List<TemplateInfVO> resultList, final TemplateInf testData) {
		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
			for (final TemplateInfVO result : resultList) {
				log.debug("result={}", result);
				log.debug("getTmplatId={}, {}", testData.getTmplatId(), result.getTmplatId());
				log.debug("getTmplatNm={}, {}", testData.getTmplatNm(), result.getTmplatNm());
				log.debug("getTmplatSeCode={}, {}", testData.getTmplatSeCode(), result.getTmplatSeCode());
				log.debug("getTmplatCours={}", testData.getTmplatCours(), result.getTmplatCours());
				log.debug("getUseAt={}", testData.getUseAt(), result.getUseAt());
				log.debug("getFrstRegisterId={}", testData.getFrstRegisterId(), result.getFrstRegisterId());
			}
		}

		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTmplatId(),
				resultList.get(0).getTmplatId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTmplatNm(),
				resultList.get(0).getTmplatNm());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTmplatSeCode(),
				resultList.get(0).getTmplatSeCode());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTmplatCours(),
				resultList.get(0).getTmplatCours());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getUseAt(),
				resultList.get(0).getUseAt());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getFrstRegisterId(),
				resultList.get(0).getFrstRegisterId());
	}

	/**
	 * 템플릿에 대한 목록를 조회한다. 테스트
	 */
	@Test
	public void a05selectTemplateInfstTypeFlag() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInfVO tmplatInfVO = new TemplateInfVO();
		tmplatInfVO.setFirstIndex(0);
		tmplatInfVO.setRecordCountPerPage(10);

		tmplatInfVO.setTypeFlag(testData.getTmplatSeCode());
		tmplatInfVO.setTmplatSeCode(testData.getTmplatSeCode());

		// when
		final List<TemplateInfVO> resultList = templateManageDAO.selectTemplateInfs(tmplatInfVO);

		// then
		asserta05selectTemplateInfsassert(resultList, testData);
	}

	/**
	 * 템플릿에 대한 목록를 조회한다. 테스트
	 */
	@Test
	public void a05selectTemplateInfsSearchCnd0() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInfVO tmplatInfVO = new TemplateInfVO();
		tmplatInfVO.setFirstIndex(0);
		tmplatInfVO.setRecordCountPerPage(10);

		tmplatInfVO.setSearchCnd("0");
		tmplatInfVO.setSearchWrd(testData.getTmplatNm());

		// when
		final List<TemplateInfVO> resultList = templateManageDAO.selectTemplateInfs(tmplatInfVO);

		// then
		asserta05selectTemplateInfsassert(resultList, testData);
	}

	/**
	 * 템플릿에 대한 목록를 조회한다. 테스트
	 */
	@Test
	public void a05selectTemplateInfsSearchCnd1() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInfVO tmplatInfVO = new TemplateInfVO();
		tmplatInfVO.setFirstIndex(0);
		tmplatInfVO.setRecordCountPerPage(10);

		tmplatInfVO.setSearchCnd("1");
		tmplatInfVO.setSearchWrd("게시판템플릿");

		// when
		final List<TemplateInfVO> resultList = templateManageDAO.selectTemplateInfs(tmplatInfVO);

		// then
		asserta05selectTemplateInfsassert(resultList, testData);
	}

	/**
	 * 템플릿에 대한 목록 전체 건수를 조회한다. 테스트
	 */
	@Test
	public void a06selectTemplateInfsCnt() {
		// given
		final TemplateInfVO tmplatInfVO = new TemplateInfVO();

		// when
		final int totCnt = templateManageDAO.selectTemplateInfsCnt(tmplatInfVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_TOT_CNT, totCnt);
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 템플릿에 대한 목록 전체 건수를 조회한다. 테스트
	 */
	@Test
	public void a06selectTemplateInfsCntTypeFlag() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInfVO tmplatInfVO = new TemplateInfVO();

		tmplatInfVO.setTypeFlag(testData.getTmplatSeCode());
		tmplatInfVO.setTmplatSeCode(testData.getTmplatSeCode());

		// when
		final int totCnt = templateManageDAO.selectTemplateInfsCnt(tmplatInfVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_TOT_CNT, totCnt);
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 템플릿에 대한 목록 전체 건수를 조회한다. 테스트
	 */
	@Test
	public void a06selectTemplateInfsCntSearchCnd0() {
		// given
		final TemplateInf testData = new TemplateInf();
		testData(testData);

		final TemplateInfVO tmplatInfVO = new TemplateInfVO();

		tmplatInfVO.setSearchCnd("0");
		tmplatInfVO.setSearchWrd(testData.getTmplatNm());

		// when
		final int totCnt = templateManageDAO.selectTemplateInfsCnt(tmplatInfVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_TOT_CNT, totCnt);
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 템플릿에 대한 목록 전체 건수를 조회한다. 테스트
	 */
	@Test
	public void a06selectTemplateInfsCntSearchCnd1() {
		// given
		final TemplateInfVO tmplatInfVO = new TemplateInfVO();

		tmplatInfVO.setSearchCnd("1");
		tmplatInfVO.setSearchWrd("게시판템플릿");

		// when
		final int totCnt = templateManageDAO.selectTemplateInfsCnt(tmplatInfVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_TOT_CNT, totCnt);
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

}
