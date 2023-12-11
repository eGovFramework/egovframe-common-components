package egovframework.com.cop.stf.service.impl;

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
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Satisfaction;
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
	@Autowired
	private BBSSatisfactionDAO bbsSatisfactionDAO;

	/**
	 * 만족도 ID
	 */
	@Autowired
	@Qualifier("egovStsfdgNoGnrService")
	private EgovIdGnrService egovStsfdgNoGnrService;

	/**
	 * 만족도조사를 등록한다. 테스트
	 */
	@Test
	@Commit
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

		try {
			satisfaction.setStsfdgPassword(EgovFileScrty.encryptPassword("rhdxhd12", satisfaction.getStsfdgNo()));
		} catch (Exception e) {
			throw new BaseRuntimeException(
					"Exception encryptPassword " + egovMessageSource.getMessage("fail.common.msg"), e);
		}
		satisfaction.setStsfdg(9);
		satisfaction.setStsfdgCn("test 이백행 만족도내용 " + LocalDateTime.now());
		satisfaction.setUseAt("Y");

		// when
		final int result = bbsSatisfactionDAO.insertSatisfaction(satisfaction);

		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
		}

		// then
		assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
	}

}
