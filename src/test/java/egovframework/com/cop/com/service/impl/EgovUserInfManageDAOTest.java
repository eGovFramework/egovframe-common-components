package egovframework.com.cop.com.service.impl;

import static org.junit.Assert.assertEquals;

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
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.impl.EgovCommuManageDAO;
import egovframework.com.cop.cmy.service.impl.EgovCommuMasterDAO;
import egovframework.com.cop.com.service.UserInfVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 협업 활용 사용자 정보 조회 DAO 단위 테스트
 *
 * @author 주레피
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, EgovUserInfManageDAOTest.class, })

@Configuration

@ImportResource({

        "classpath*:egovframework/spring/com/idgn/context-idgn-Cmmnty.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.cmy.service.impl",

                "egovframework.com.cop.com.service.impl"

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                EgovCommuMasterDAO.class,

                                EgovCommuManageDAO.class,

                                EgovUserInfManageDAO.class

                        }

                )

        }

)

@NoArgsConstructor
@Slf4j
// @Commit
public class EgovUserInfManageDAOTest extends EgovTestAbstractDAO {

    /**
     * EgovCommuBBSMasterDAO
     */
    @Autowired
    private EgovCommuMasterDAO egovCommuMasterDAO;

    /**
     * EgovCommuBBSMasterDAO
     */
    @Autowired
    private EgovCommuManageDAO egovCommuManageDAO;

    /**
     * EgovUserInfManageDAO
     */
    @Autowired
    private EgovUserInfManageDAO egovUserInfManageDAO;

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
     * testCommunity
     */
    private CommunityUser testCommunityUser;

    /**
     * 테스트 사용자 생성
     *
     */
    private void testCommunityUserAdd(final CommunityUser cmmntyUser, final LoginVO loginVO) {
        cmmntyUser.setCmmntyId(testCommunity.getCmmntyId());

        if (loginVO != null) {
            cmmntyUser.setEmplyrId(loginVO.getUniqId());
            cmmntyUser.setFrstRegisterId(loginVO.getUniqId());
            cmmntyUser.setLastUpdusrId(loginVO.getUniqId());
        }

        cmmntyUser.setMngrAt("Y");
        cmmntyUser.setUseAt("Y");

        egovCommuManageDAO.insertCommuUserRqst(cmmntyUser);
    }

    /**
     * 테스트 커뮤니티 생성
     *
     */
    private void testCommunityAdd(final Community community, final LoginVO loginVO) {
        // 커뮤니티명 설정
        community.setCmmntyNm("테스트 커뮤니티");

        // 커뮤니티소개 설정
        community.setCmmntyIntrcn("테스트 커뮤니티입니다.");

        // 사용여부 설정
        community.setUseAt("Y");

        // 등록구분코드 설정
        community.setRegistSeCode("REGC02"); // 커뮤니티 등록

        // 템플릿ID
        // community.setTmplatId("TMPT02"); // 커뮤니티 템플릿

        if (loginVO != null) {
            // 최초등록자ID 설정
            community.setFrstRegisterId(loginVO.getUniqId());
            community.setLastUpdusrId(loginVO.getUniqId());
        }

        // 커뮤니티 등록
        egovCommuMasterDAO.insertCommuMaster(community);
    }

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void testData() {
        /*
         * 테스트 커뮤니티 생성
         */
        testCommunity = new Community();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        // 커뮤니티ID 설정
        testCommunity.setCmmntyId("TEST_CMMNTY_99999000");

        /**
         * egovCmmntyIdGnrService 사용
         */
        /*
         * try {
         *     testCommunity.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
         * } catch (FdlException e) {
         *     log.error("FdlException egovCmmntyIdGnrService");
         *     fail("FdlException egovCmmntyIdGnrService");
         * }
         *
         */

        testCommunityAdd(testCommunity, loginVO);

        /*
         * 테스트 사용자 생성
         */
        testCommunityUser = new CommunityUser();

        // 커뮤니티ID 설정
        testCommunityUser.setCmmntyId(testCommunity.getCmmntyId());

        testCommunityUserAdd(testCommunityUser, loginVO);
    }

    /**
     * 사용자 정보 목록 조회 테스트
     *
     */
    @Test
    public void testSelectUserList() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setRecordCountPerPage(10);
        userInfVO.setFirstIndex(0);
        userInfVO.setSearchCnd("0");
        userInfVO.setSearchWrd("테스트1");

        // when
        final List<UserInfVO> resultList = egovUserInfManageDAO.selectUserList(userInfVO);

        // log.debug("resultList=[{}]", resultList);
        for (final UserInfVO result : resultList) {
            if (log.isDebugEnabled()) {
                // log.debug("result={}", result);
                log.debug("getUserNm={}, {}", userInfVO.getSearchWrd(), result.getUserNm());
            }

            // then
            assertSelectUserList(userInfVO, result);
        }
    }

    /**
     * 사용자 정보 assert
     */
    private void assertSelectUserList(final UserInfVO userInfVO, final UserInfVO result) {
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), userInfVO.getSearchWrd(), result.getUserNm());
    }

    /**
     * 사용자 정보 목록 개수 조회 테스트
     *
     */
    @Test
    public void testSelectUserListCnt() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setRecordCountPerPage(10);
        userInfVO.setFirstIndex(0);
        userInfVO.setSearchCnd("0");
        userInfVO.setSearchWrd("테스트1");

        // when
        final int result = egovUserInfManageDAO.selectUserListCnt(userInfVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 커뮤니티 사용자 정보 목록 조회 테스트
     *
     */
    @Test
    public void testSelectCmmntyUserList() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setTrgetId(testCommunityUser.getCmmntyId());
        userInfVO.setRecordCountPerPage(10);
        userInfVO.setFirstIndex(0);
        userInfVO.setSearchCnd("0");
        userInfVO.setSearchWrd("테스트1");

        // when
        final List<UserInfVO> resultList = egovUserInfManageDAO.selectCmmntyUserList(userInfVO);

        // log.debug("resultList=[{}]", resultList);
        for (final UserInfVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getUserNm={}, {}", userInfVO.getSearchWrd(), result.getUserNm());
            }

            // then
            assertSelectUserList(userInfVO, result);
        }
    }

    /**
     * 커뮤니티 사용자 정보 목록 개수 조회 테스트
     *
     */
    @Test
    public void testSelectCmmntyUserListCnt() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setTrgetId(testCommunityUser.getCmmntyId());
        userInfVO.setRecordCountPerPage(10);
        userInfVO.setFirstIndex(0);
        userInfVO.setSearchCnd("0");
        userInfVO.setSearchWrd("테스트1");

        // when
        final int result = egovUserInfManageDAO.selectCmmntyUserListCnt(userInfVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 커뮤니티 관리자 정보 목록 조회 테스트
     *
     */
    @Test
    public void testSelectCmmntyMngrList() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setTrgetId(testCommunityUser.getCmmntyId());
        userInfVO.setRecordCountPerPage(10);
        userInfVO.setFirstIndex(0);
        userInfVO.setSearchCnd("0");
        userInfVO.setSearchWrd("테스트1");

        // when
        final List<UserInfVO> resultList = egovUserInfManageDAO.selectCmmntyMngrList(userInfVO);

        // log.debug("resultList=[{}]", resultList);
        for (final UserInfVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getUserNm={}, {}", userInfVO.getSearchWrd(), result.getUserNm());
            }

            // then
            assertSelectUserList(userInfVO, result);
        }
    }


    /**
     * 커뮤니티 관리자 정보 목록 개수 조회 테스트
     *
     */
    @Test
    public void testSelectCmmntyMngrListCnt() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setTrgetId(testCommunityUser.getCmmntyId());
        userInfVO.setRecordCountPerPage(10);
        userInfVO.setFirstIndex(0);
        userInfVO.setSearchCnd("0");
        userInfVO.setSearchWrd("테스트1");

        // when
        final int result = egovUserInfManageDAO.selectCmmntyMngrListCnt(userInfVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 커뮤니티 전체 사용자 목록 조회 테스트
     *
     */
    @Test
    public void testSelectAllCmmntyUser() {
        // given
        /*
         * 기본 사용자 목록
         * ESNTL_ID             USER_ID     USER_NM
         * USRCNFRM_00000000000 TEST1       테스트1
         * USRCNFRM_00000000001 USER        일반회원
         * USRCNFRM_00000000002 ENTERPRISE  NIA
         * USRCNFRM_99999999999 webmaster   웹마스터
         */
        final UserInfVO userInfVO = new UserInfVO();
        userInfVO.setTrgetId(testCommunityUser.getCmmntyId());

        // when
        final List<UserInfVO> resultList = egovUserInfManageDAO.selectCmmntyMngrList(userInfVO);

        // log.debug("resultList=[{}]", resultList);
        for (final UserInfVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getUserNm={}, {}", userInfVO.getSearchWrd(), result.getUserNm());
            }

            // then
            assertSelectUserList(userInfVO, result);
        }
    }

}
