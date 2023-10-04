package egovframework.com.cop.cmy.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmy.service.Community;
import egovframework.com.cop.cmy.service.CommunityUser;
import egovframework.com.cop.cmy.service.CommunityUserVO;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.test.EgovTestAbstractDAO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 커뮤니티 사용자관리 DAO 단위 테스트
 *
 * @author 주레피
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, EgovCommuManageDAOTest.class, })

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

                                EgovCommuManageDAO.class,

                        }

                )

        }

)

@RequiredArgsConstructor
@Slf4j
// @Commit
public class EgovCommuManageDAOTest extends EgovTestAbstractDAO {

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
     * egovCmmntyIdGnrService
     */
    @Autowired
    @Qualifier("egovCmmntyIdGnrService")
    private EgovIdGnrService egovCmmntyIdGnrService;

    /**
     * 테스트 사용자 생성
     *
     */
    private void testUser(CommunityUser communityUser) {
        final LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        // 커뮤니티ID 설정
        if (communityUser.getCmmntyId().isEmpty()) {
            fail("테스트 사용자 생성 실패(커뮤니티ID 누락)");
        }

        // 업무사용자ID 설정
        communityUser.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

        // 관리자여부 설정
        if (communityUser.getMngrAt().isEmpty()) {
            communityUser.setMngrAt("N");
        }

        // 사용자상태 설정
        // A 가입신청 회원가입신청상태
        // P 가입승인 회원가입승인상태
        // D 가입삭제 회원가입삭제상태
        if (communityUser.getMberSttus().isEmpty()) {
            communityUser.setMberSttus("A");
        }

        // 사용여부 설정
        communityUser.setUseAt("Y");

        // 최초사용자ID 설정
        communityUser.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

        egovCommuManageDAO.insertCommuUserRqst(communityUser);
    }

    /**
     * 테스트 커뮤니티, 테스트 사용자 생성
     *
     */
    private void testData(final CommunityUser communityUser) {
        final LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        Community community = new Community();

        // 커뮤니티ID 설정
        try {
            community.setCmmntyId(egovCmmntyIdGnrService.getNextStringId());
        } catch (FdlException e) {
            log.error("FdlException egovCmmntyIdGnrService");
            fail("FdlException egovCmmntyIdGnrService");
        }

        // 커뮤니티명 설정
        community.setCmmntyNm("테스트 커뮤니티");

        // 커뮤니티소개 설정
        community.setCmmntyIntrcn("테스트 커뮤니티입니다.");

        // 사용여부 설정
        community.setUseAt("Y");

        // 등록구분코드 설정
        community.setRegistSeCode("REGC02"); // 커뮤니티 등록

        // 템플릿ID
        community.setTmplatId("TMPT02"); // 커뮤니티 템플릿

        // 최초등록자ID 설정
        community.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));

        // 커뮤니티 등록
        egovCommuMasterDAO.insertCommuMaster(community);

        // 커뮤니티ID 설정
        communityUser.setCmmntyId(community.getCmmntyId());

        // 테스트 사용자 생성
        testUser(communityUser);
    }

    @Test
    public void testSelectSingleCommuUserDetail() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);
        // log.debug("communityUser = {}, {}", communityUser.getEmplyrId(), communityUser.getCmmntyId());

        // when
        CommunityUser resultCommunityUser = egovCommuManageDAO.selectSingleCommuUserDetail(communityUser);

        // then
        assertNotNull(resultCommunityUser);

        // given
        CommunityUser communityNoUser = new CommunityUser();
        communityNoUser.setCmmntyId(communityUser.getCmmntyId());
        communityNoUser.setEmplyrId("00000000000"); // 존재하지 않는 사용자ID

        // when
        resultCommunityUser = egovCommuManageDAO.selectSingleCommuUserDetail(communityNoUser);

        // then
        assertNull(resultCommunityUser);
    }

    @Test
    public void testSelectCommuManagerList() {
        // given
        CommunityUser communityUser = new CommunityUser();
        communityUser.setMngrAt("Y"); // 관리자권한을 가진 사용자 생성
        testData(communityUser);

        CommunityVO communityVO = new CommunityVO();
        communityVO.setCmmntyId(communityUser.getCmmntyId());

        // when
        List<CommunityUser> resultList = egovCommuManageDAO.selectCommuManagerList(communityVO);
        // log.info("resultList=[{}]", resultList);
        for (final CommunityUser result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
            }
        }

        // then
        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0 < resultList.size());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), communityUser.getEmplyrId(), resultList.get(0).getEmplyrId());

        // given
        CommunityVO noCommunityVO = new CommunityVO();
        noCommunityVO.setCmmntyId("CMMNTY_00000000000000");

        // when
        resultList = egovCommuManageDAO.selectCommuManagerList(noCommunityVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0, resultList.size());
    }

    @Test
    public void testCheckExistUser() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);

        // when
        int result = egovCommuManageDAO.checkExistUser(communityUser);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);

        // given
        CommunityUser communityNoUser = new CommunityUser();
        communityNoUser.setCmmntyId(communityUser.getCmmntyId());
        communityNoUser.setEmplyrId("00000000000"); // 존재하지 않는 사용자ID

        // when
        result = egovCommuManageDAO.checkExistUser(communityNoUser);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0, result);
    }

    @Test(expected = DuplicateKeyException.class)
    public void testInsertCommuUserRqst() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);

        // when
        // testData()에서 사용자가 생성되었으므로 생성이 되지 않는다.
        egovCommuManageDAO.insertCommuUserRqst(communityUser);

        // then
        // DuplicateKeyException 발생
    }

    @Test
    public void testSelectCommuUserList() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityUser.getCmmntyId());
        communityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        List<CommunityUser> resultList = egovCommuManageDAO.selectCommuUserList(communityUserVO);
        log.info("resultList=[{}]", resultList);
        for (final CommunityUser result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
            }
        }

        // then
        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0 < resultList.size());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), communityUser.getEmplyrId(), resultList.get(0).getEmplyrId());

        // given
        CommunityUserVO noCommunityUserVO = new CommunityUserVO();
        noCommunityUserVO.setCmmntyId("CMMNTY_00000000000000");
        noCommunityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        resultList = egovCommuManageDAO.selectCommuUserList(noCommunityUserVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0, resultList.size());
    }

    @Test
    public void testSelectCommuUserListCnt() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityUser.getCmmntyId());
        communityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        int result = egovCommuManageDAO.selectCommuUserListCnt(communityUserVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);

        // given
        CommunityUserVO noCommunityUserVO = new CommunityUserVO();
        noCommunityUserVO.setCmmntyId("CMMNTY_00000000000000");
        noCommunityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        result = egovCommuManageDAO.selectCommuUserListCnt(noCommunityUserVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0, result);
    }

    @Test
    public void testInsertCommuUser() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityUser.getCmmntyId());
        communityUserVO.setEmplyrId(communityUser.getEmplyrId());
        communityUserVO.setMberSttus(communityUser.getMberSttus());
        communityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        egovCommuManageDAO.insertCommuUser(communityUserVO);

        // then
        List<CommunityUser> resultList = egovCommuManageDAO.selectCommuUserList(communityUserVO);
        // log.debug("resultList = {}", resultList);

        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0 < resultList.size());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "A", communityUserVO.getMberSttus());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "P", resultList.get(0).getMberSttus());
    }

    @Test
    public void testDeleteCommuUser() {
        // given
        CommunityUser communityUser = new CommunityUser();
        testData(communityUser);

        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT),
                0 < egovCommuManageDAO.checkExistUser(communityUser));

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityUser.getCmmntyId());
        communityUserVO.setEmplyrId(communityUser.getEmplyrId());
        communityUserVO.setMberSttus(communityUser.getMberSttus());
        communityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        egovCommuManageDAO.deleteCommuUser(communityUserVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0, egovCommuManageDAO.checkExistUser(communityUser));
    }

    @Test
    public void testInsertCommuUserAdmin() {
        // given
        CommunityUser communityUser = new CommunityUser();
        communityUser.setMberSttus("P"); // 가입 승인 상태
        testData(communityUser);

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityUser.getCmmntyId());
        communityUserVO.setEmplyrId(communityUser.getEmplyrId());
        communityUserVO.setMngrAt(communityUser.getMngrAt());
        communityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        egovCommuManageDAO.insertCommuUserAdmin(communityUserVO);

        // then
        List<CommunityUser> resultList = egovCommuManageDAO.selectCommuUserList(communityUserVO);
        // log.debug("resultList = {}", resultList);

        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0 < resultList.size());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "N", communityUserVO.getMngrAt());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "Y", resultList.get(0).getMngrAt());
    }

    @Test
    public void testDeleteCommuUserAdmin() {
        // given
        CommunityUser communityUser = new CommunityUser();
        communityUser.setMngrAt("Y");    // 관리자
        communityUser.setMberSttus("P"); // 가입 승인 상태
        testData(communityUser);

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityUser.getCmmntyId());
        communityUserVO.setEmplyrId(communityUser.getEmplyrId());
        communityUserVO.setMngrAt(communityUser.getMngrAt());
        communityUserVO.setFirstIndex(0); // LIMIT 0부터 가져 오기

        // when
        egovCommuManageDAO.deleteCommuUserAdmin(communityUserVO);

        // then
        List<CommunityUser> resultList = egovCommuManageDAO.selectCommuUserList(communityUserVO);
        // log.debug("resultList = {}", resultList);

        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 0 < resultList.size());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "Y", communityUserVO.getMngrAt());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "N", resultList.get(0).getMngrAt());
    }
}
