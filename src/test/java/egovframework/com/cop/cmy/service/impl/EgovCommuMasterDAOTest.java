package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 커뮤니티정보 관리 DAO 단위 테스트
 *
 * @author 주레피
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, EgovCommuMasterDAOTest.class, })

@Configuration

@ImportResource({

        "classpath*:egovframework/spring/com/idgn/context-idgn-Cmmnty.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.cmy.service.impl",

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                EgovCommuMasterDAO.class,

                        }

                )

        }

)

@NoArgsConstructor
@Slf4j
// @Commit
public class EgovCommuMasterDAOTest extends EgovTestAbstractDAO {

    /**
     * EgovCommuBBSMasterDAO
     */
    @Autowired
    private EgovCommuMasterDAO egovCommuMasterDAO;

    /**
     * egovCmmntyIdGnrService
     */
    @Autowired
    @Qualifier("egovCmmntyIdGnrService")
    private EgovIdGnrService egovCmmntyIdGnrService;

    /**
     * testCommunity
     */
    private Community testCommunity;

    /**
     * 테스트 커뮤니티 생성
     *
     */
    private int testCommunityAdd(final Community community, final LoginVO loginVO)
    {
        // 커뮤니티명 설정
        community.setCmmntyNm("테스트 커뮤니티");

        // 커뮤니티소개 설정
        community.setCmmntyIntrcn("테스트 커뮤니티입니다.");

        // 사용여부 설정
        community.setUseAt("Y");

        // 등록구분코드 설정
        community.setRegistSeCode("REGC02"); // 커뮤니티 등록
//
//        // 템플릿ID
//        community.setTmplatId("TMPT02"); // 커뮤니티 템플릿

        if (loginVO != null) {
            // 최초등록자ID 설정
            testCommunity.setFrstRegisterId(loginVO.getUniqId());
            testCommunity.setLastUpdusrId(loginVO.getUniqId());
        }

        // 커뮤니티 등록
        return egovCommuMasterDAO.insertCommuMaster(testCommunity);

//        // 커뮤니티ID 설정
//        communityUser.setCmmntyId(community.getCmmntyId());
//
//        // 테스트 사용자 생성
//        testUser(communityUser);
    }

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void testData() {
        testCommunity = new Community();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        // 커뮤니티ID 설정
        final String cmmntyId = "TEST_CMMNTY_99999000";
        testCommunity.setCmmntyId(cmmntyId);
        /**
         * egovCmmntyIdGnrService 사용
         */
        /*        try {
            community.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
        } catch (FdlException e) {
            log.error("FdlException egovCmmntyIdGnrService");
            fail("FdlException egovCmmntyIdGnrService");
        }
        community.setCmmntyId(cmmntyId);
        */

        testCommunityAdd(testCommunity, loginVO);

    }

    /**
     * 커뮤니티 등록 테스트
     */
    @Test
    public void testInsertCommuMaster() {
        // given
        final Community cmmntyUser = new Community();
        // 'TEST_CMMNTY_99999001' 커뮤니티 등록
        final String cmmntyId = "TEST_CMMNTY_99999001";
        cmmntyUser.setCmmntyId(cmmntyId);

        // when
        final int result = egovCommuMasterDAO.insertCommuMaster(cmmntyUser);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 커뮤니티 목록 개수 조회 테스트
     */
    @Test
    public void testSelectCommuMasterListCnt() {
        // given
        final CommunityVO cmmntyVO = new CommunityVO();
        cmmntyVO.setSearchCnd("0");
        cmmntyVO.setSearchWrd(testCommunity.getCmmntyNm());

        // when
        final int result = egovCommuMasterDAO.selectCommuMasterListCnt(cmmntyVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 커뮤니티 목록 조회 테스트
     */
    @Test
    public void testSelectCommuMasterList() {
        // given
        final CommunityVO cmmntyVO = new CommunityVO();
        cmmntyVO.setCmmntyId(testCommunity.getCmmntyId());
        cmmntyVO.setSearchCnd("0");
        cmmntyVO.setSearchWrd(testCommunity.getCmmntyNm());

        cmmntyVO.setFirstIndex(0);
        cmmntyVO.setRecordCountPerPage(10);


        // when
        final List<CommunityVO> resultList = egovCommuMasterDAO.selectCommuMasterList(cmmntyVO);
        // log.info("resultList=[{}]", resultList);
        for (final CommunityVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getCmmntyId={}, {}", cmmntyVO.getCmmntyId(), result.getCmmntyId());
            }

            // then
            assertSelectCommuMaster(cmmntyVO, result);
        }
    }

    /**
     * 커뮤니티 정보 assert
     */
    private void assertSelectCommuMaster(final CommunityVO cmmntyUser, final CommunityVO result) {
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), cmmntyUser.getCmmntyId(), result.getCmmntyId());
    }


    /**
     * 커뮤니티 상세 정보 조회 테스트
     */
    @Test
    public void testSelectCommuMasterDetail() {
        // given
        final CommunityVO cmmntyVO = new CommunityVO();
        cmmntyVO.setCmmntyId(testCommunity.getCmmntyId());

        // when
        final CommunityVO result = egovCommuMasterDAO.selectCommuMasterDetail(cmmntyVO);
        // log.info("result=[{}]", result);

        // then
        assertSelectCommuMaster(cmmntyVO, result);
    }

    /**
     * 커뮤니터 정보 업데이트 테스트
     */
    @Test
    public void testUpdateCommuMaster() {
        // given
        final CommunityVO cmmntyVO = new CommunityVO();
        cmmntyVO.setCmmntyId(testCommunity.getCmmntyId());
        cmmntyVO.setCmmntyNm(testCommunity.getCmmntyNm() + " - 수정");
        cmmntyVO.setCmmntyIntrcn(testCommunity.getCmmntyIntrcn());
        cmmntyVO.setTmplatId(testCommunity.getTmplatId());
        cmmntyVO.setLastUpdusrId(testCommunity.getLastUpdusrId());
        cmmntyVO.setUseAt(testCommunity.getUseAt());

        // when
        final int result = egovCommuMasterDAO.updateCommuMaster(cmmntyVO);

        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 커뮤니티 정보 삭제 테스트
     */
    @Test
    public void testDeleteCommuMaster() {
        // given
        final CommunityVO cmmntyVO = new CommunityVO();
        cmmntyVO.setCmmntyId(testCommunity.getCmmntyId());
        cmmntyVO.setLastUpdusrId(testCommunity.getLastUpdusrId());

        // when
        final int result = egovCommuMasterDAO.updateCommuMaster(cmmntyVO);

        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }

    /**
     * 포트릿을 위한 커뮤니티 정보 목록 조회 테스트
     */
    @Test
    public void testSelectCommuMasterListPortlet() {
        // given
        final CommunityVO cmmntyVO = new CommunityVO();

        // when
        List<CommunityVO> resultList = null;
        try {
            resultList = egovCommuMasterDAO.selectCommuMasterListPortlet(cmmntyVO);
        } catch (DataAccessException eDAE) {
            // eDAE.printStackTrace();
            log.error("DataAccessException selectCommuMasterListPortlet");
            error(eDAE);
            fail("DataAccessException selectCommuMasterListPortlet");
        }

        // log.info("resultList=[{}]", resultList);
        for (final CommunityVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getCmmntyId={}, {}", cmmntyVO.getCmmntyId(), result.getCmmntyId());
            }
        }

        // then
        assertSelectCommuMasterListPortlet(resultList);
    }

    private void assertSelectCommuMasterListPortlet(final List<CommunityVO> resultList) {
        if (resultList != null) {
            assertFalse(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultList.isEmpty());
        }
    }
}
