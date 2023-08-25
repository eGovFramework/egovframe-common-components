package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

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
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.impl.EgovBBSMasterDAO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 게시판 마스터 DAO 단위 테스트
 *
 * @author 이백행
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, EgovCommuBBSMasterDAOTest.class, })

@Configuration

@ImportResource({

    "classpath*:egovframework/spring/com/idgn/context-idgn-Cmmnty.xml",

    "classpath*:egovframework/spring/com/idgn/context-idgn-bbs.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.cmy.service.impl",

                "egovframework.com.cop.bbs.service.impl",

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                EgovCommuBBSMasterDAO.class,

                                EgovBBSMasterDAO.class,

                        }

                        )

        }

        )

@RequiredArgsConstructor
@Slf4j
// @Commit
public class EgovCommuBBSMasterDAOTest extends EgovTestAbstractDAO {

    /**
     * EgovCommuBBSMasterDAO
     */
    @Autowired
    private EgovCommuBBSMasterDAO egovCommuBBSMasterDAO;

    /**
     * EgovBBSMasterDAO
     */
    @Autowired
    private EgovBBSMasterDAO egovBBSMasterDAO;

    /**
     * egovBBSMstrIdGnrService
     */
    @Autowired
    @Qualifier("egovBBSMstrIdGnrService")
    private EgovIdGnrService egovBBSMstrIdGnrService;

    /**
     * egovCmmntyIdGnrService
     */
    @Autowired
    @Qualifier("egovCmmntyIdGnrService")
    private EgovIdGnrService egovCmmntyIdGnrService;

    private void testData(final BoardMaster boardMaster) {
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        if (loginVO != null) {
            boardMaster.setFrstRegisterId(loginVO.getUniqId());
            boardMaster.setLastUpdusrId(loginVO.getUniqId());
        }

        try {
            boardMaster.setBbsId(egovBBSMstrIdGnrService.getNextStringId());
            boardMaster.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());

        } catch (FdlException e) {
            log.error("FdlException egovBBSMstrIdGnrService");
            // e.printStackTrace();
        }

        boardMaster.setBbsTyCode("BBST02");
        boardMaster.setBbsNm("test 게시판마스터 " + LocalDateTime.now());

        boardMaster.setUseAt("Y");

        egovBBSMasterDAO.insertBBSMasterInf(boardMaster);
    }

    @Test
    public void testSelectCommuBBSMasterListMain() {
        // given
        BoardMaster testData = new BoardMaster();
        testData(testData);

        BoardMasterVO boardMasterVO = new BoardMasterVO();
        boardMasterVO.setCmmntyId(testData.getCmmntyId());

        // when
        final List<BoardMasterVO> resultList = egovCommuBBSMasterDAO.selectCommuBBSMasterListMain(boardMasterVO);
        for (final BoardMasterVO result : resultList) {
            debug(result);
        }
        // then
        if(resultList != null) {
            assertAll(testData, resultList.get(0));
        }
    }

    private void assertAll(final BoardMaster testData, final BoardMasterVO result) {
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getBbsId(), result.getBbsId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getBbsTyCode(), result.getBbsTyCode());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testData.getBbsNm(), result.getBbsNm());
    }

    private void debug(final BoardMasterVO result) {
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
            log.debug("getBbsId={}", result.getBbsId());
            log.debug("getBbsTyCode={}", result.getBbsTyCode());
            log.debug("getBbsNm={}", result.getBbsNm());
        }
    }
}
