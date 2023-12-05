package egovframework.com.cop.smt.mtm.service.impl;

import static org.junit.Assert.assertEquals;
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
import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 메모할일정보 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-12-04
 *
 */

@ContextConfiguration(classes = { MemoTodoDAOTest.class, EgovTestAbstractDAO.class, })

@Configuration

@ImportResource({

		"classpath*:egovframework/spring/com/idgn/context-idgn-MemoTodo.xml",

})

@ComponentScan(

		useDefaultFilters = false,

		basePackages = {

				"egovframework.com.cop.smt.mtm.service.impl",

		},

		includeFilters = {

				@Filter(

						type = FilterType.ASSIGNABLE_TYPE,

						classes = {

								MemoTodoDAO.class,

						}

				)

		}

)

@NoArgsConstructor
@Slf4j
public class MemoTodoDAOTest extends EgovTestAbstractDAO {

	/**
	 * 메모할일에 대한 DAO 클래스를 정의한다.
	 */
	@Autowired
	private MemoTodoDAO memoTodoDAO;

	/**
	 * 메모할일정보 아이디
	 */
	@Autowired
	@Qualifier("egovMemoTodoIdGnrService")
	private EgovIdGnrService egovMemoTodoIdGnrService;

	/**
	 * 주어진 조건에 맞는 메모할일 목록을 불러온다.
	 */
	@Test
	public void selectMemoTodoListSearchDe1() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);

		final MemoTodoVO memoTodoVO = new MemoTodoVO();

		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);

		memoTodoVO.setSearchId(testData.getWrterId());

		memoTodoVO.setSearchDe("1");
		memoTodoVO.setSearchBgnDe(EgovDateUtil.toString(new Date(), "", null));
		memoTodoVO.setSearchEndDe(EgovDateUtil.toString(new Date(), "", null));

		// when
		final List<MemoTodoVO> resultList = memoTodoDAO.selectMemoTodoList(memoTodoVO);

		debug(resultList, testData);

		// then
		assertSelectMemoTodoList(resultList, testData);
	}

	/**
	 * 주어진 조건에 맞는 메모할일 목록을 불러온다.
	 */
	@Test
	public void selectMemoTodoListSearchDe0() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);

		final MemoTodoVO memoTodoVO = new MemoTodoVO();

		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);

		memoTodoVO.setSearchId(testData.getWrterId());

		memoTodoVO.setSearchDe("0");
		memoTodoVO.setSearchBgnDe(testData.getTodoBeginTime().substring(0, 10));
		memoTodoVO.setSearchEndDe(testData.getTodoEndTime().substring(0, 10));

		// when
		final List<MemoTodoVO> resultList = memoTodoDAO.selectMemoTodoList(memoTodoVO);

		debug(resultList, testData);

		// then
		assertSelectMemoTodoList(resultList, testData);
	}

	/**
	 * 주어진 조건에 맞는 메모할일 목록을 불러온다.
	 */
	@Test
	public void selectMemoTodoListSearchCnd0() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);

		final MemoTodoVO memoTodoVO = new MemoTodoVO();

		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);

		memoTodoVO.setSearchId(testData.getWrterId());

		memoTodoVO.setSearchCnd("0");
		memoTodoVO.setSearchWrd(testData.getTodoNm());

		// when
		final List<MemoTodoVO> resultList = memoTodoDAO.selectMemoTodoList(memoTodoVO);

		debug(resultList, testData);

		// then
		assertSelectMemoTodoList(resultList, testData);
	}

	/**
	 * 주어진 조건에 맞는 메모할일 목록을 불러온다.
	 */
	@Test
	public void selectMemoTodoListSearchCnd1() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);

		final MemoTodoVO memoTodoVO = new MemoTodoVO();

		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);

		memoTodoVO.setSearchId(testData.getWrterId());

		memoTodoVO.setSearchCnd("1");
		memoTodoVO.setSearchWrd(testData.getTodoCn());

		// when
		final List<MemoTodoVO> resultList = memoTodoDAO.selectMemoTodoList(memoTodoVO);

		debug(resultList, testData);

		// then
		assertSelectMemoTodoList(resultList, testData);
	}

	private void testData(final MemoTodo testData) {
		// given
		try {
			testData.setTodoId(egovMemoTodoIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.msg"), e);
		}

		testData.setTodoNm("test 이백행 할일제목 " + LocalDateTime.now());

		final String today = EgovDateUtil.toString(new Date(), "yyyyMMdd", null);
		testData.setTodoBeginTime(today + "090000");
		testData.setTodoEndTime(today + "180000");

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			testData.setWrterId(loginVO.getUniqId());
			testData.setFrstRegisterId(loginVO.getUniqId());
			testData.setLastUpdusrId(loginVO.getUniqId());
		}

		testData.setTodoCn("test 이백행 할일내용 " + LocalDateTime.now());

		// when
		final int result = memoTodoDAO.insertMemoTodo(testData);

		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

	private void debug(final List<MemoTodoVO> resultList, final MemoTodo testData) {
		if (log.isDebugEnabled()) {
			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());

			for (final MemoTodoVO result : resultList) {
				log.debug("result={}", result);
				log.debug("getWrterId={}, {}", testData.getWrterId(), result.getWrterId());
				log.debug("getTodoBeginTime={}, {}", testData.getTodoBeginTime(), result.getTodoBeginTime());
				log.debug("getTodoBeginHour={}", result.getTodoBeginHour());
				log.debug("getTodoBeginMin={}", result.getTodoBeginMin());
				log.debug("getTodoEndTime={}, {}", testData.getTodoEndTime(), result.getTodoEndTime());
				log.debug("getTodoNm={}, {}", testData.getTodoNm(), result.getTodoNm());
				log.debug("getTodoCn={}, {}", testData.getTodoCn(), result.getTodoCn());
			}
		}
	}

	private void assertSelectMemoTodoList(final List<MemoTodoVO> resultList, final MemoTodo testData) {
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.size() > -1);
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTodoBeginTime(),
				resultList.get(0).getTodoBeginTime());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTodoEndTime(),
				resultList.get(0).getTodoEndTime());
		assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getTodoNm(),
				resultList.get(0).getTodoNm());
	}

	/**
	 * 메모할일 정보를 등록한다.
	 */
	@Test
	public void insertMemoTodo() {
		// given
		final MemoTodo memoTodo = new MemoTodo();

		try {
			memoTodo.setTodoId(egovMemoTodoIdGnrService.getNextStringId());
		} catch (FdlException e) {
			throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.msg"), e);
		}

		memoTodo.setTodoNm("test 이백행 할일제목 " + LocalDateTime.now());

		final String today = EgovDateUtil.toString(new Date(), "yyyyMMdd", null);
		memoTodo.setTodoBeginTime(today + "090000");
		memoTodo.setTodoEndTime(today + "180000");

		final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			memoTodo.setWrterId(loginVO.getUniqId());
			memoTodo.setFrstRegisterId(loginVO.getUniqId());
			memoTodo.setLastUpdusrId(loginVO.getUniqId());
		}

		memoTodo.setTodoCn("test 이백행 할일내용 " + LocalDateTime.now());

		// when
		final int result = memoTodoDAO.insertMemoTodo(memoTodo);

		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

	/**
	 * 메모할일 목록에 대한 전체 건수를 조회한다.
	 */
	@Test
	public void selectMemoTodoListCntSearchDe1() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);
	
		final MemoTodoVO memoTodoVO = new MemoTodoVO();
	
		memoTodoVO.setSearchId(testData.getWrterId());
	
		memoTodoVO.setSearchDe("1");
		memoTodoVO.setSearchBgnDe(EgovDateUtil.toString(new Date(), "", null));
		memoTodoVO.setSearchEndDe(EgovDateUtil.toString(new Date(), "", null));
	
		// when
		final int totCnt = memoTodoDAO.selectMemoTodoListCnt(memoTodoVO);
	
		debugTotCnt(totCnt);
	
		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 메모할일 목록에 대한 전체 건수를 조회한다.
	 */
	@Test
	public void selectMemoTodoListCntSearchDe0() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);
	
		final MemoTodoVO memoTodoVO = new MemoTodoVO();
	
		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);
	
		memoTodoVO.setSearchId(testData.getWrterId());
	
		memoTodoVO.setSearchDe("0");
		memoTodoVO.setSearchBgnDe(testData.getTodoBeginTime().substring(0, 10));
		memoTodoVO.setSearchEndDe(testData.getTodoEndTime().substring(0, 10));
	
		// when
		final int totCnt = memoTodoDAO.selectMemoTodoListCnt(memoTodoVO);
	
		debugTotCnt(totCnt);
	
		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 메모할일 목록에 대한 전체 건수를 조회한다.
	 */
	@Test
	public void selectMemoTodoListCntSearchCnd0() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);
	
		final MemoTodoVO memoTodoVO = new MemoTodoVO();
	
		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);
	
		memoTodoVO.setSearchId(testData.getWrterId());
	
		memoTodoVO.setSearchCnd("0");
		memoTodoVO.setSearchWrd(testData.getTodoNm());
	
		// when
		final int totCnt = memoTodoDAO.selectMemoTodoListCnt(memoTodoVO);
	
		debugTotCnt(totCnt);
	
		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

	/**
	 * 메모할일 목록에 대한 전체 건수를 조회한다.
	 */
	@Test
	public void selectMemoTodoListCntSearchCnd1() {
		// given
		final MemoTodo testData = new MemoTodo();
		testData(testData);
	
		final MemoTodoVO memoTodoVO = new MemoTodoVO();
	
		memoTodoVO.setFirstIndex(0);
		memoTodoVO.setRecordCountPerPage(10);
	
		memoTodoVO.setSearchId(testData.getWrterId());
	
		memoTodoVO.setSearchCnd("1");
		memoTodoVO.setSearchWrd(testData.getTodoCn());
	
		// when
		final int totCnt = memoTodoDAO.selectMemoTodoListCnt(memoTodoVO);
	
		debugTotCnt(totCnt);
	
		// then
		assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), totCnt > -1);
	}

}
