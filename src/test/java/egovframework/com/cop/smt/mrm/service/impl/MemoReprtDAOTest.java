package egovframework.com.cop.smt.mrm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
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

	private void testDataMemoReprt(final MemoReprt testDataMemoReprt) {
		try {
			testDataMemoReprt.setReprtId(egovMemoReprtIdGnrService.getNextStringId());
		} catch (FdlException e) {
			log.error("FdlException egovMemoReprtIdGnrService");
			throw new BaseRuntimeException(e);
		}

		testDataMemoReprt.setReprtSj("test 이백행 보고서제목 " + LocalDateTime.now());

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testDataMemoReprt.setWrterId(loginVO.getUniqId()); // 작성자ID
			testDataMemoReprt.setReportrId(loginVO.getUniqId()); // 보고자ID

			testDataMemoReprt.setFrstRegisterId(loginVO.getUniqId()); // 최초등록자ID
			testDataMemoReprt.setLastUpdusrId(loginVO.getUniqId()); // 최종수정자ID
		}

		testDataMemoReprt.setReprtCn("test 이백행 보고내용 " + LocalDateTime.now());

		final int result = memoReprtDAO.insertMemoReprt(testDataMemoReprt);

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
				log.debug("result={}", result);
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
		assertTrue(FAIL_COMMON_SELECT, totCnt > -1);
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

		log.debug("result={}", result);
	}

}
