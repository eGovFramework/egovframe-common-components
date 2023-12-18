package egovframework.com.cop.stf.service.impl;

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
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;
import egovframework.com.test.EgovTestAbstractDAO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 만족도 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-12-12
 *
 */

@ContextConfiguration(classes = { BBSSatisfactionDAOTest.class, EgovTestAbstractDAO.class, })

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/idgn/context-idgn-StsfdgNo.xml",

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.cop.stf.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								BBSSatisfactionDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class BBSSatisfactionDAOTest extends EgovTestAbstractDAO {

	/**
	 * 만족도조사를 위한 데이터 접근 클래스
	 */
	@Resource
	private BBSSatisfactionDAO bbsSatisfactionDAO;

	/**
	 * 만족도 ID
	 */
	@Resource(name = "egovStsfdgNoGnrService")
	private EgovIdGnrService egovStsfdgNoGnrService;

	/**
	 * Debug Result
	 */
	public static final String DEBUG_RESULT = "result={}";

	/**
	 * 만족도조사에 대한 목록을 조회 한다. 테스트
	 */
	@Test
	public void a01selectSatisfactionList() {
		// given
		final Satisfaction testData = new Satisfaction();
		testData(testData);

		final SatisfactionVO satisfactionVO = new SatisfactionVO();

		satisfactionVO.setBbsId("");
		satisfactionVO.setNttId(testData.getNttId());
		satisfactionVO.setSubFirstIndex(0);
		satisfactionVO.setSubRecordCountPerPage(10);

		// when
		final List<SatisfactionVO> resultList = bbsSatisfactionDAO.selectSatisfactionList(satisfactionVO);
		final int resultListSize = resultList.size();
		final int resultListSize2 = resultList.size() - 1;

		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("resultListSize={}", resultListSize);
			for (final SatisfactionVO result : resultList) {
				log.debug("getStsfdgNo={}, {}", testData.getStsfdgNo(), result.getStsfdgNo());
				log.debug("getNttId={}, {}", testData.getNttId(), result.getNttId());
				log.debug("getWrterId={}, {}", testData.getWrterId(), result.getWrterId());
				log.debug("getWrterNm={}, {}", testData.getWrterNm(), result.getWrterNm());
				log.debug("getStsfdgPassword={}, {}", testData.getStsfdgPassword(), result.getStsfdgPassword());
				log.debug("getStsfdgCn={}, {}", testData.getStsfdgCn(), result.getStsfdgCn());
				log.debug("getStsfdg={}, {}", testData.getStsfdg(), result.getStsfdg());
				log.debug("getUseAt={}, {}", testData.getUseAt(), result.getUseAt());
				log.debug("getFrstRegisterPnttm={}, {}", testData.getFrstRegisterPnttm(),
						result.getFrstRegisterPnttm());
				log.debug("getFrstRegisterNm={}, {}", testData.getFrstRegisterNm(), result.getFrstRegisterNm());
				log.debug("");
			}
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultListSize > -1);
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgNo(),
				resultList.get(resultListSize2).getStsfdgNo());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getNttId(),
				resultList.get(resultListSize2).getNttId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getWrterId(),
				resultList.get(resultListSize2).getWrterId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getWrterNm(),
				resultList.get(resultListSize2).getWrterNm());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgPassword(),
				resultList.get(resultListSize2).getStsfdgPassword());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgCn(),
				resultList.get(resultListSize2).getStsfdgCn());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdg(),
				resultList.get(resultListSize2).getStsfdg());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getUseAt(),
				resultList.get(resultListSize2).getUseAt());
	}

	/**
	 * 만족도조사에 대한 목록 건수를 조회 한다. 테스트
	 */
	@Test
	public void a02selectSatisfactionListCnt() {
		// given
		final Satisfaction testData = new Satisfaction();
		testData(testData);

		final SatisfactionVO satisfactionVO = new SatisfactionVO();

		satisfactionVO.setBbsId("");
		satisfactionVO.setNttId(testData.getNttId());

		// when
		final int totCnt = bbsSatisfactionDAO.selectSatisfactionListCnt(satisfactionVO);

		if (log.isDebugEnabled()) {
			log.debug("totCnt={}", totCnt);
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 만족도조사를 등록한다. 테스트
	 */
	@Test
	public void a03insertSatisfaction() {
		// given
		final Satisfaction satisfaction = new Satisfaction();

		try {
			satisfaction.setStsfdgNo(String.valueOf(egovStsfdgNoGnrService.getNextLongId()));
		} catch (FdlException e) {
			throw new BaseRuntimeException(
					"FdlException egovStsfdgNoGnrService " + egovMessageSource.getMessage("fail.common.msg"), e);
		}

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			satisfaction.setWrterId(loginVO.getUniqId());
			satisfaction.setWrterNm(loginVO.getName());
			satisfaction.setFrstRegisterId(loginVO.getUniqId());
		}

		satisfaction.setStsfdgPassword(EgovFileScrty.encryptPassword("rhdxhd12", satisfaction.getStsfdgNo()));

		satisfaction.setStsfdg(9);
		satisfaction.setStsfdgCn("test 이백행 만족도내용 " + LocalDateTime.now());
		satisfaction.setUseAt("Y");

		// when
		final int result = bbsSatisfactionDAO.insertSatisfaction(satisfaction);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

	private void testData(final Satisfaction testData) {
		// given
		try {
			testData.setStsfdgNo(String.valueOf(egovStsfdgNoGnrService.getNextLongId()));
		} catch (FdlException e) {
			throw new BaseRuntimeException(
					"FdlException egovStsfdgNoGnrService " + egovMessageSource.getMessage("fail.common.msg"), e);
		}

		testData.setNttId(Long.valueOf(EgovDateUtil.toString(new Date(), "yyyyMMddHHmmss", null)));

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testData.setWrterId(loginVO.getUniqId());
			testData.setWrterNm(loginVO.getName());
			testData.setFrstRegisterId(loginVO.getUniqId());
		}

		testData.setStsfdgPassword(EgovFileScrty.encryptPassword("rhdxhd12", testData.getStsfdgNo()));

		testData.setStsfdg(9);
		testData.setStsfdgCn("test 이백행 만족도내용 " + LocalDateTime.now());
		testData.setUseAt("Y");

		// when
		final int result = bbsSatisfactionDAO.insertSatisfaction(testData);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

	/**
	 * 만족도조사에 대한 내용을 조회한다. 테스트
	 */
	@Test
	public void a05selectSatisfaction() {
		// given
		final Satisfaction testData = new Satisfaction();
		testData(testData);

		final SatisfactionVO satisfactionVO = new SatisfactionVO();

		satisfactionVO.setStsfdgNo(testData.getStsfdgNo());

		// when
		final Satisfaction result = bbsSatisfactionDAO.selectSatisfaction(satisfactionVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
			log.debug("getStsfdgNo={}, {}", testData.getStsfdgNo(), result.getStsfdgNo());
			log.debug("getWrterId={}, {}", testData.getWrterId(), result.getWrterId());
			log.debug("getWrterNm={}, {}", testData.getWrterNm(), result.getWrterNm());
			log.debug("getStsfdgPassword={}, {}", testData.getStsfdgPassword(), result.getStsfdgPassword());
			log.debug("getStsfdgCn={}, {}", testData.getStsfdgCn(), result.getStsfdgCn());
			log.debug("getStsfdg={}, {}", testData.getStsfdg(), result.getStsfdg());
			log.debug("getUseAt={}, {}", testData.getUseAt(), result.getUseAt());
			log.debug("getFrstRegisterPnttm={}, {}", testData.getFrstRegisterPnttm(), result.getFrstRegisterPnttm());
			log.debug("getFrstRegisterNm={}, {}", testData.getFrstRegisterNm(), result.getFrstRegisterNm());
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgNo(), result.getStsfdgNo());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getWrterId(), result.getWrterId());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getWrterNm(), result.getWrterNm());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgPassword(),
				result.getStsfdgPassword());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgCn(), result.getStsfdgCn());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdg(), result.getStsfdg());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getUseAt(), result.getUseAt());
	}

	/**
	 * 만족도조사에 대한 내용을 수정한다. 테스트
	 */
	@Test
	public void a06updateSatisfaction() {
		// given
		final Satisfaction testData = new Satisfaction();
		testData(testData);

		final Satisfaction satisfaction = new Satisfaction();

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			satisfaction.setWrterNm(loginVO.getName());
			satisfaction.setLastUpdusrId(loginVO.getUniqId());
		}

		satisfaction.setStsfdgPassword(EgovFileScrty.encryptPassword("rhdxhd12#", satisfaction.getStsfdgNo()));

		satisfaction.setStsfdgCn("test 이백행 만족도내용 수정 " + LocalDateTime.now());
		satisfaction.setStsfdg(7);

		satisfaction.setStsfdgNo(testData.getStsfdgNo());

		// when
		final int result = bbsSatisfactionDAO.updateSatisfaction(satisfaction);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
	}

	/**
	 * 만족도조사에 대한 패스워드를 조회 한다. 테스트
	 */
	@Test
	public void a07getSatisfactionPassword() {
		// given
		final Satisfaction testData = new Satisfaction();
		testData(testData);

		final Satisfaction satisfaction = new Satisfaction();

		satisfaction.setStsfdgNo(testData.getStsfdgNo());

		// when
		final String result = bbsSatisfactionDAO.getSatisfactionPassword(satisfaction);

		if (log.isDebugEnabled()) {
			log.debug("result={}, {}", testData.getStsfdgPassword(), result);
		}

		// then
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getStsfdgPassword(), result);
	}

	/**
	 * 만족도 전체 점수를 제공한다. 테스트
	 */
	@Test
	public void a08getSummary() {
		// given
		final Satisfaction testData = new Satisfaction();
		testData(testData);

		final SatisfactionVO satisfactionVO = new SatisfactionVO();

		satisfactionVO.setStsfdgNo(testData.getStsfdgNo());

		// when
		final Float result = bbsSatisfactionDAO.getSummary(satisfactionVO);

		if (log.isDebugEnabled()) {
			log.debug(DEBUG_RESULT, result);
		}

		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), result > -1);
	}

}
