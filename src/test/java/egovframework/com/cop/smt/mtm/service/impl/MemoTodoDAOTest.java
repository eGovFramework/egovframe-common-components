package egovframework.com.cop.smt.mtm.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Date;

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
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 메모할일정보 DAO 단위 테스트
 * 
 * @author 이백행
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, MemoTodoDAOTest.class, })

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
	 * 메모할일 정보를 등록한다.
	 * 
	 * @throws FdlException, EgovBizException
	 */
	@Test
	public void insertMemoTodo() {
		// given
		final MemoTodo memoTodo = new MemoTodo();

		try {
			memoTodo.setTodoId(String.valueOf(egovMemoTodoIdGnrService.getNextStringId()));
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

		log.debug("result={}", result);

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

}
