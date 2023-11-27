package egovframework.com.cop.smt.mrm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovDateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.mrm.service.MemoReprt;
import egovframework.com.cop.smt.mrm.service.MemoReprtVO;
import egovframework.com.cop.smt.mrm.service.ReportrVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 부서업무 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-08-21
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, MemoReprtDAOTest.class, })

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/idgn/context-idgn-MemoReprt.xml",

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.cop.smt.mrm.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								MemoReprtDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class MemoReprtDAOTest extends EgovTestAbstractDAO {

	/**
	 * 메모보고에 대한 DAO 클래스를 정의한다.
	 */
	@Autowired
	private MemoReprtDAO memoReprtDAO;

	/**
	 * 메모 보고
	 */
	@Autowired
	@Qualifier("egovMemoReprtIdGnrService")
	private EgovIdGnrService egovMemoReprtIdGnrService;

	/**
	 * result={}
	 */
	public static final String RESULT = "result={}";

	/**
	 * 보고일 값읽기
	 * 
	 * @return
	 */
	private String getReprtDe() {
		return EgovDateUtil.toString(new Date(), "yyyy-MM-dd", null);
	}

	private void testData(final MemoReprt testData) {
		try {
			testData.setReprtId(egovMemoReprtIdGnrService.getNextStringId()); // 보고서ID
		} catch (FdlException e) {
			log.error("FdlException egovMemoReprtIdGnrService");
			throw new BaseRuntimeException(e);
		}

		testData.setReprtSj("test 이백행 보고서제목 " + LocalDateTime.now()); // 보고서제목

		testData.setReprtDe(getReprtDe());// 보고일

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testData.setWrterId(loginVO.getUniqId()); // 작성자ID
			testData.setReportrId(loginVO.getUniqId()); // 보고자ID

			testData.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
			testData.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}

		testData.setReprtCn("test 이백행 보고내용 " + LocalDateTime.now()); // 보고내용

		final int result = memoReprtDAO.insertMemoReprt(testData);

		if (result == 0) {
			throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.insert"));
		}
	}

	/**
	 * 주어진 조건에 맞는 보고자를 불러온다.
	 */
	@Test
	public void selectReportrList() {
		// given
		final ReportrVO reportrVO = new ReportrVO();
		reportrVO.setFirstIndex(0);
		reportrVO.setRecordCountPerPage(10);

//		reportrVO.setSearchCnd("0");
//		reportrVO.setSearchWrd("기본조직");

//		reportrVO.setSearchCnd("1");
//		reportrVO.setSearchWrd("테스트1");

		// when
		final List<ReportrVO> resultList = memoReprtDAO.selectReportrList(reportrVO);

		// then
		assertFalse(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.isEmpty());

		log.debug("resultList={}", resultList);

		for (final ReportrVO result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug(RESULT, result);
				log.debug("orgnztNm 조직정보.조직명={}", result.getOrgnztNm());
				log.debug("uniqId 업무사용자정보.고유ID={}", result.getUniqId());
				log.debug("emplyrNm 업무사용자정보.사용자명={}", result.getEmplyrNm());
				log.debug("emplNo 업무사용자정보.사원번호={}", result.getEmplNo());
				log.debug("ofcpsNm 업무사용자정보.직위명={}", result.getOfcpsNm());
			}
		}
	}

	/**
	 * 보고자 목록에 대한 전체 건수를 조회한다.
	 */
	@Test
	public void selectReportrListCnt() {
		// given
		final ReportrVO reportrVO = new ReportrVO();

//		reportrVO.setSearchCnd("0");
//		reportrVO.setSearchWrd("기본조직");

//		reportrVO.setSearchCnd("1");
//		reportrVO.setSearchWrd("테스트1");

		// when
		final int totCnt = memoReprtDAO.selectReportrListCnt(reportrVO);

		log.debug("totCnt={}", totCnt);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 주어진 조건에 맞는 직위명을 불러온다.
	 */
	@Test
	public void selectWrterClsfNm() {
		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		// given
		final String wrterId = loginVO.getUniqId();

		// when
		final String result = memoReprtDAO.selectWrterClsfNm(wrterId);

		log.debug(RESULT, result);

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "관리자", result);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtList() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	private void debug(final List<MemoReprtVO> resultList) {
		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
			log.debug("isEmpty={}", resultList.isEmpty());
		}

		for (final MemoReprtVO resultVO : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("resultVO={}", resultVO);
				log.debug("getReprtId={}", resultVO.getReprtId());
				log.debug("getReprtSj={}", resultVO.getReprtSj());
				log.debug("getWrterNm={}", resultVO.getWrterNm());
				log.debug("getDrctMatterRegistDt={}", resultVO.getDrctMatterRegistDt());
				log.debug("getReportrInqireDt={}", resultVO.getReportrInqireDt());
			}
		}
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchBgnDe() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchCnd0() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

		memoReprtVO.setSearchCnd("0");
		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchCnd1() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

		memoReprtVO.setSearchCnd("1");
		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchCnd2() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

		memoReprtVO.setSearchCnd("2");
		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchDrctMatter0() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListsearchDrctMatter1() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchSttus0() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

		memoReprtVO.setSearchSttus("0");
//		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 따른 메모보고 목록을 불러온다.
	 */
	@Test
	public void selectMemoReprtListSearchSttus1() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprtVO.setSearchId(loginVO.getUniqId());
		}

		memoReprtVO.setFirstIndex(0);
		memoReprtVO.setRecordCountPerPage(10);

//		memoReprtVO.setSearchBgnDe(testData.getReprtDe());
//		memoReprtVO.setSearchEndDe(testData.getReprtDe());

//		memoReprtVO.setSearchCnd("0");
//		memoReprtVO.setSearchWrd(testData.getReprtSj());

//		memoReprtVO.setSearchCnd("1");
//		memoReprtVO.setSearchWrd(testData.getReprtCn());

//		memoReprtVO.setSearchCnd("2");
//		memoReprtVO.setSearchWrd(loginVO.getName());

//		memoReprtVO.setSearchDrctMatter("0");
//		memoReprtVO.setSearchDrctMatter("1");

//		memoReprtVO.setSearchSttus("0");
		memoReprtVO.setSearchSttus("1");

		// when
		final List<MemoReprtVO> resultList = memoReprtDAO.selectMemoReprtList(memoReprtVO);

		debug(resultList);

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
	}

	/**
	 * 주어진 조건에 맞는 메모보고를 불러온다.
	 */
	@Test
	public void selectMemoReprt() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprtVO memoReprtVO = new MemoReprtVO();
		memoReprtVO.setReprtId(testData.getReprtId());

		// when
		final MemoReprtVO resultVO = memoReprtDAO.selectMemoReprt(memoReprtVO);

		if (log.isDebugEnabled()) {
			log.debug("resultVO={}", resultVO);
			log.debug("getReprtId={}, {}", testData.getReprtId(), resultVO.getReprtId());
			log.debug("getReprtSj={}, {}", testData.getReprtSj(), resultVO.getReprtSj());
			log.debug("getReprtDe={}, {}", egovframework.com.utl.fcc.service.EgovDateUtil
					.convertDate(testData.getReprtDe(), "0000", "yyyy-MM-dd"), resultVO.getReprtDe());
			log.debug("getWrterId={}, {}", testData.getWrterId(), resultVO.getWrterId());
			log.debug("getReportrId={}, {}", testData.getReportrId(), resultVO.getReportrId());
			log.debug("getFrstRegisterId={}, {}", testData.getFrstRegisterId(), resultVO.getFrstRegisterId());
			log.debug("getLastUpdusrId={}, {}", testData.getLastUpdusrId(), resultVO.getLastUpdusrId());
			log.debug("getReprtCn={}, {}", testData.getReprtCn(), resultVO.getReprtCn());
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getReprtId(), resultVO.getReprtId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getReprtSj(), resultVO.getReprtSj());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT),
				egovframework.com.utl.fcc.service.EgovDateUtil.convertDate(testData.getReprtDe(), "0000", "yyyy-MM-dd"),
				resultVO.getReprtDe());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getWrterId(), resultVO.getWrterId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getReportrId(),
				resultVO.getReportrId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getFrstRegisterId(),
				resultVO.getFrstRegisterId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getLastUpdusrId(),
				resultVO.getLastUpdusrId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getReprtCn(), resultVO.getReprtCn());
	}

	/**
	 * 메모보고 정보의 보고자 조회일시를 수정한다.
	 */
	@Test
	public void readMemoReprt() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprt memoReprt = new MemoReprt();
		memoReprt.setReprtId(testData.getReprtId());
		memoReprt.setReportrInqireDt(EgovDateUtil.toString(new Date(), "yyyyMMddHHmmss", null)); // 보고자조회일시

		// when
		final int result = memoReprtDAO.readMemoReprt(memoReprt);

		if (log.isDebugEnabled()) {
			log.debug(RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
	}

	/**
	 * 메모보고 정보를 수정한다.
	 */
	@Test
	public void updateMemoReprt() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprt memoReprt = new MemoReprt();
		memoReprt.setReprtId(testData.getReprtId());

		memoReprt.setReprtSj("test 이백행 보고서제목 수정 " + LocalDateTime.now()); // 보고서제목

		memoReprt.setReprtDe(getReprtDe());// 보고일

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprt.setWrterId(loginVO.getUniqId()); // 작성자ID
			memoReprt.setReportrId(loginVO.getUniqId()); // 보고자ID

			memoReprt.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}

		memoReprt.setReprtCn("test 이백행 보고내용 수정 " + LocalDateTime.now()); // 보고내용

		memoReprt.setAtchFileId("FILE_000000000000001");

		// when
		final int result = memoReprtDAO.updateMemoReprt(memoReprt);

		if (log.isDebugEnabled()) {
			log.debug(RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
	}

	/**
	 * 메모보고 정보를 수정한다.
	 */
	@Test
	public void updateMemoReprtDrctMatter() {
		// given
		final MemoReprt testData = new MemoReprt();
		testData(testData);

		final MemoReprt memoReprt = new MemoReprt();
		memoReprt.setReprtId(testData.getReprtId());

		memoReprt.setDrctMatter("test 이백행 지시사항 " + LocalDateTime.now());
		memoReprt.setDrctMatterRegistDt(EgovDateUtil.toString(new Date(), "yyyyMMddHHmmss", null));

		// when
		final int result = memoReprtDAO.updateMemoReprtDrctMatter(memoReprt);

		if (log.isDebugEnabled()) {
			log.debug(RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
	}

	/**
	 * 메모보고 정보를 등록한다.
	 */
	@Test
	public void insertMemoReprt() {
		// given
		final MemoReprt memoReprt = new MemoReprt();

		try {
			memoReprt.setReprtId(egovMemoReprtIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException egovMemoReprtIdGnrService");
			throw new BaseRuntimeException(e);
		}

		memoReprt.setReprtSj("test 이백행 보고서제목 " + LocalDateTime.now());

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoReprt.setWrterId(loginVO.getUniqId()); // 작성자ID
			memoReprt.setReportrId(loginVO.getUniqId()); // 보고자ID

			memoReprt.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
			memoReprt.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}

		memoReprt.setReprtCn("test 이백행 보고내용 " + LocalDateTime.now());

		// when
		final int result = memoReprtDAO.insertMemoReprt(memoReprt);

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);

		log.debug(RESULT, result);
	}

}
