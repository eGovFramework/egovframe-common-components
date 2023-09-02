package egovframework.com.cop.ncm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.cop.ncm.service.NameCardVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 명함정보를 관리하기 위한 DAO 단위 테스트
 * @author 주레피
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, NcrdManageDAOTest.class, })

@Configuration

@ImportResource({
    "classpath*:egovframework/spring/com/idgn/context-idgn-Ncrd.xml",
})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.ncm.service.impl"

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                NcrdManageDAO.class

                        }

                )

        }

)

@NoArgsConstructor
@Slf4j
// @Commit
public class NcrdManageDAOTest extends EgovTestAbstractDAO {
    /**
     * egovNcrdIdGnrService
     */
    @Autowired
    @Qualifier("egovNcrdIdGnrService")
    private EgovIdGnrService egovNcrdIdgenService;

    /**
     * NcrdManageDAO
     */
    @Autowired
    private NcrdManageDAO ncrdManageDAO;

    /**
     * testNcrd
     */
    private NameCard testNameCard;

    /**
     * testNcrdUser;
     */
    private NameCardUser testNameCardUser;


    /**
     * 명함 정보 등록
     *
     */
    private void testNcrdItemAdd(final NameCard nameCard, final LoginVO loginVO) {
        /*
         * 로그인 사용자 정보
         * ESNTL_ID                USER_ID    USER_NM
         * USRCNFRM_00000000000    TEST1      테스트1
         */
        if (loginVO != null) {
            // 최초등록자ID 설정
            nameCard.setFrstRegisterId(loginVO.getUniqId());
            nameCard.setLastUpdusrId(loginVO.getUniqId());
            nameCard.setNcrdTrgterId(loginVO.getUniqId()); // 명함대상자아이디
        }

        // 명함 정보 등록
        ncrdManageDAO.insertNcrdItem(nameCard);
    }

    /**
     * 명함사용자 등록
     *
     */
    private void testNameCardUserAdd(final NameCard nameCard, final NameCardUser nameCardUser) {
        nameCardUser.setNcrdId(nameCard.getNcrdId()); // 명함아이디
        nameCardUser.setEmplyrId(nameCard.getFrstRegisterId()); // 사용자아이디
        nameCardUser.setRegistSeCode("REGC04"); // 등록구분코드
        nameCardUser.setUseAt("Y"); // 사용여부

        ncrdManageDAO.insertNcrdUseInf(nameCardUser);
    }

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void testData() {
        /*
         * 테스트 명함 정보 생성
         */
        testNameCard = new NameCard();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        testNameCard.setNationNo("82"); // 국가번호
        testNameCard.setAreaNo("070"); // 지역번호
        testNameCard.setMiddleTelNo("4448"); // 중간전화번호
        testNameCard.setEndTelNo("2678"); // 끝전화번호
        testNameCard.setTelNo(testNameCard.getNationNo() + testNameCard.getAreaNo() + testNameCard.getMiddleTelNo() + testNameCard.getEndTelNo());

        testNameCard.setIdntfcNo("070"); // 식별번호
        testNameCard.setMiddleMbtlNum("4448"); // 중간휴대폰번호
        testNameCard.setEndMbtlNum("2678"); // 끝휴대폰번호
        testNameCard.setMbtlNum(testNameCard.getIdntfcNo() + testNameCard.getMiddleMbtlNum() + testNameCard.getEndMbtlNum());

        testNameCard.setNcrdId("TEST_NCRD_9999999000");
        /**
         * egovNcrdIdGnrService 사용
         */
        /*
         * try {
         *     nameCard.setNcrdId(egovNcrdIdgenService.getNextStringId());
         * } catch (FdlException e) {
         *     log.error("FdlException egovNcrdIdGnrService");
         *     fail("FdlException egovNcrdIdGnrService");
         * }
         *
         */

        testNameCard.setExtrlUserAt("N"); // 외부사용자여부
        testNameCard.setNcrdNm("테스터"); // 명함이름
        testNameCard.setCmpnyNm("표준프레임워크센터"); // 회사명
        testNameCard.setDeptNm("연구개발"); // 부서명
        testNameCard.setOfcpsNm("대리"); // 직위명
        testNameCard.setEmailAdres("egovframesupport@gmail.com"); // 이메일주소

        testNameCard.setOthbcAt("Y"); // 공개여부

        testNcrdItemAdd(testNameCard, loginVO);

        /*
         * 테스트 명함사용자 생성
         */
        testNameCardUser = new NameCardUser();

        testNameCardUserAdd(testNameCard, testNameCardUser);
    }

    /**
     * 명함 정보 등록 테스트
     */
    @Test
    public void testInsertNcrdItem() {
        // given
        final NameCard nameCard = new NameCard();
        nameCard.setNcrdId("TEST_NCRD_9999999001");
        nameCard.setNcrdNm("TEST2");
        nameCard.setOthbcAt("Y"); // 공개여부

        // when
        final int result = ncrdManageDAO.insertNcrdItem(nameCard);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 명함 정보 assert
     */
    private void assertSelectNcrdItem(final Object expected, final Object actual) {
        if (expected instanceof NameCardVO) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), ((NameCardVO) expected).getNcrdId(), ((NameCardVO) actual).getNcrdId());
        } else {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), expected, actual);
        }
    }

    /**
     * 명함 정보 상제정보 조회 테스트: 긍정
     */
    @Test
    public void testSelectNcrdItem() {
        // given
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setNcrdId(testNameCard.getNcrdId());

        // when
        final NameCardVO result = ncrdManageDAO.selectNcrdItem(nameCardVO);

        // then
        assertSelectNcrdItem(nameCardVO, result);
    }

    /**
     * 명함 정보 상제정보 조회 테스트: 부정
     */
    @Test
    public void testSelectNcrdItem_Empty() {
        // given
        final NameCardVO nameCardVO = new NameCardVO();

        // when
        final NameCardVO result = ncrdManageDAO.selectNcrdItem(nameCardVO);

        // then
        assertNull(egovMessageSource.getMessage(FAIL_COMMON_SELECT), result);
    }

    /**
     * 명함 정보 목록 조회 테스트
     */
    @Test
    public void testSelectNcrdItemList() {
        // given
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setRecordCountPerPage(10);
        nameCardVO.setFirstIndex(0);
        nameCardVO.setSearchCnd("1"); // 회사명
        nameCardVO.setSearchWrd("표준프레임워크센터");

        // when
        final List<NameCardVO> resultList = ncrdManageDAO.selectNcrdItemList(nameCardVO);

        // log.debug("resultList=[{}]", resultList);
        for (final NameCardVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getCmpnyNm={}, {}", nameCardVO.getSearchWrd(), result.getCmpnyNm());
            }

            // then
            assertSelectNcrdItem( nameCardVO.getSearchWrd(), result.getCmpnyNm());
        }
    }

    /**
     * 명함 정보 목록 개수 조회 테스트 (로그인한 사람은 제외)
     */
    @Test
    public void testSelectNcrdItemListCnt() {
        // given
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setSearchCnd("1"); // 회사명
        nameCardVO.setSearchWrd("표준프레임워크센터");

        // when
        final int result = ncrdManageDAO.selectNcrdItemListCnt(nameCardVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 내 명함 정보 목록 조회 테스트
     */
    @Test
    public void testSelectMyNcrdItemList() {
        // given
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setRecordCountPerPage(10);
        nameCardVO.setFirstIndex(0);
        nameCardVO.setSearchCnd("0"); // 명함 이름
        nameCardVO.setSearchWrd("테스터");

        if (loginVO != null) {
            nameCardVO.setFrstRegisterId(loginVO.getUniqId()); // 내 정보 설정
        }

        // when
        final List<NameCardVO> resultList = ncrdManageDAO.selectMyNcrdItemList(nameCardVO);

        // log.debug("resultList=[{}]", resultList);
        for (final NameCardVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getNcrdNm={}, {}", nameCardVO.getSearchWrd(), result.getNcrdNm());
            }

            // then
            assertSelectNcrdItem( nameCardVO.getSearchWrd(), result.getNcrdNm());
        }
    }

    /**
     * 내 명함 정보 목록 개수 조회 테스트
     */
    @Test
    public void testSelectMyNcrdItemListCnt() {
        // given
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setRecordCountPerPage(10);
        nameCardVO.setFirstIndex(0);
        nameCardVO.setSearchCnd("0"); // 명함 이름
        nameCardVO.setSearchWrd("테스터");

        if (loginVO != null) {
            nameCardVO.setFrstRegisterId(loginVO.getUniqId()); // 내 정보 설정
        }

        // when
        final int result = ncrdManageDAO.selectMyNcrdItemListCnt(nameCardVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 명함 정보 수정 테스트
     */
    @Test
    public void testUpdateNcrdItem() {
        // given
        testNameCard.setNcrdNm("QA");

        // when
        final int result = ncrdManageDAO.updateNcrdItem(testNameCard);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 명함 정보 삭제 테스트
     * 명함사용자를 먼저 삭제해야 한다.
     */
    @Test
    public void testDeleteNcrdItem() {
        // given
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setNcrdId(testNameCard.getNcrdId());
        nameCardVO.setEmplyrId(testNameCardUser.getEmplyrId());

        ncrdManageDAO.deleteNcrdItemUser(nameCardVO); // 1. foreign key NaceCardUser delete

        // when
        final int result = ncrdManageDAO.deleteNcrdItem(nameCardVO); // 2. NameCardItem delete

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }

    /**
     * 명함사용자 정보 등록 테스트
     * 명함을 먼저 등록해야 명함사용자를 등록할 수 있다.
     */
    @Test
    public void testInsertNcrdUseInf() {
        // given
        final NameCard nameCard = new NameCard();
        nameCard.setNcrdId("TEST_NCRD_9999999001");
        nameCard.setNcrdNm("TEST2");
        nameCard.setOthbcAt("Y"); // 공개여부

        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        if (loginVO != null) {
            nameCard.setFrstRegisterId(loginVO.getUniqId());
            nameCard.setLastUpdusrId(loginVO.getUniqId());
            nameCard.setNcrdTrgterId(loginVO.getUniqId()); // 명함대상자아이디
        }

        ncrdManageDAO.insertNcrdItem(nameCard);

        final NameCardUser nameCardUser = new NameCardUser();
        nameCardUser.setNcrdId("TEST_NCRD_9999999001"); // 명함아이디
        nameCardUser.setEmplyrId(nameCard.getFrstRegisterId()); // 사용자아이디
        nameCardUser.setRegistSeCode("REGC04"); // 등록구분코드
        nameCardUser.setUseAt("Y"); // 사용여부

        // when
        final int result = ncrdManageDAO.insertNcrdUseInf(nameCardUser);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 명함사용자 정보 assert
     */
    private void assertSelectNcrdUser(final Object expected, final Object actual) {
        if (expected instanceof NameCardUser) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), ((NameCardUser) expected).getEmplyrId(), ((NameCardVO) actual).getEmplyrId());
        } else {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), expected, actual);
        }
    }


    /**
     * 명함사용자 목록 조회 테스트
     */
    @Test
    public void testSelectNcrdUseInfs() {
        // given
        final NameCardUser nameCardUser = new NameCardUser();
        nameCardUser.setEmplyrId(testNameCardUser.getEmplyrId());
        nameCardUser.setSearchCnd("0");
        nameCardUser.setSearchWrd(testNameCardUser.getNcrdNm());

        // when
        final List<NameCardUser> resultList = ncrdManageDAO.selectNcrdUseInfs(nameCardUser);

        // log.debug("resultList=[{}]", resultList);
        for (final NameCardUser result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getCmpnyNm={}, {}", nameCardUser.getSearchWrd(), result.getNcrdNm());
            }

            // then
            assertSelectNcrdUser(nameCardUser.getSearchWrd(), result.getNcrdNm());
        }
    }

    /**
     * 명함사용자 정보 목록 개수 조회 테스트
     */
    @Test
    public void testSelectNcrdUseInfsCnt() {
        // given
        final NameCardUser nameCardUser = new NameCardUser();
        nameCardUser.setEmplyrId(testNameCardUser.getEmplyrId());
        nameCardUser.setSearchCnd("0");
        nameCardUser.setSearchWrd(testNameCardUser.getNcrdNm());


        // when
        final int result = ncrdManageDAO.selectNcrdUseInfsCnt(nameCardUser);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 명함사용자 정보 수정(등록구분코드) 테스트
     */
    @Test
    public void testUpdateNcrdUseInf() {
        // given
        testNameCardUser.setRegistSeCode("REGC05");

        // when
        final int result = ncrdManageDAO.updateNcrdUseInf(testNameCardUser);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 명함사용자 정보 삭제 테스트
     */
    @Test
    public void testDeleteNcrdItemUser() {
        // given
        final NameCardVO nameCardVO = new NameCardVO();
        nameCardVO.setNcrdId(testNameCard.getNcrdId());
        nameCardVO.setEmplyrId(testNameCardUser.getEmplyrId());

        // when
        final int result = ncrdManageDAO.deleteNcrdItemUser(nameCardVO);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }
}
