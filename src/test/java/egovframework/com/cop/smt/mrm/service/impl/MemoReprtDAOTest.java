package egovframework.com.cop.smt.mrm.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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
//			e.printStackTrace();
			throw new BaseRuntimeException("FdlException egovMemoReprtIdGnrService");
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
	 * 메모보고 정보를 등록한다.
	 */
	@Test
	public void insertMemoReprt() {
		// given
		final MemoReprt memoReprt = new MemoReprt();

		try {
			memoReprt.setReprtId(egovMemoReprtIdGnrService.getNextStringId());
		} catch (FdlException e) {
//			e.printStackTrace();
			throw new BaseRuntimeException("FdlException egovMemoReprtIdGnrService");
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
