package egovframework.com.cop.tpl.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

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

}
