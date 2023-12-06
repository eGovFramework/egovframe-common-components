package egovframework.com.cop.smt.lsm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.test.context.ContextConfiguration;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import egovframework.com.cop.smt.lsm.service.LeaderSttus;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 간부일정에 대한 DAO 클래스 단위 테스트
 * @author 주레피
 * @created 2023-11-07 19:04
 *
 */
@ContextConfiguration(classes = { EgovTestAbstractDAO.class, LeaderSchdulDAOTest.class, })
@Configuration
@ImportResource({
    "classpath*:egovframework/spring/com/idgn/context-idgn-LeaderSchdu.xml",
})
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {
                "egovframework.com.cop.smt.lsm.service.impl;"
        },
        includeFilters = {
                @Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                LeaderSchdulDAO.class
                        }
                        )
        }
        )
@NoArgsConstructor
@Slf4j
// @Commit
public class LeaderSchdulDAOTest extends EgovTestAbstractDAO {
    /**
     * egovLeaderSchdulIdGnrService
     */
    @Autowired
    @Qualifier("egovLeaderSchdulIdGnrService")
    private EgovIdGnrService egovLeaderSchdulIdGnrService;

    /**
     * LeaderSchdulDAO
     */
    @Autowired
    private LeaderSchdulDAO leaderSchdulDAO;

    /**
     * testLeasderScheduleVO
     */
    private LeaderSchdulVO testLeaderScheduleVO;

    /**
     * testLeasderSttusVO
     */
    private LeaderSttus testLeaderSttus;

    /**
     * default testUserVO
     */
    private LoginVO testUserVO;


    /**
     * 간부일정 더미데이터 생성
     * @param scheduleId: 간부일정 ID
     * @param scheduleSep: 간부일정 구분
     * @param scheduleName: 간부일정명
     * @param scheduleCont: 간부일정 내용
     * @param SchedulePlace: 간부일정 장소
     * @param repetSepCode: 반복구분
     * @param beginYYYYMMDDHHMM: 일정시작
     * @param endYYYYMMDDHHMM: 일정종료
     * @return
     */
    private LeaderSchdulVO makeLeaderSchedule(final String scheduleId, final String scheduleSep, final String scheduleName, final String scheduleCont, final String SchedulePlace, final String repetSepCode, final String beginYYYYMMDDHHMM, final String endYYYYMMDDHHMM) {
        /*
         * 테스트 간부 정보를 가져옴
         */
        testUserVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        final LeaderSchdulVO leaderScheduleVO = new LeaderSchdulVO();

        /*
         * 일정ID
         */
        leaderScheduleVO.setSchdulId(scheduleId);
        /*
         * 일정구분
         */
        leaderScheduleVO.setSchdulSe(scheduleSep);
        /*
         * 일정명
         */
        leaderScheduleVO.setSchdulNm(scheduleName);
        /*
         * 일정내용
         */
        leaderScheduleVO.setSchdulCn(scheduleCont);
        /*
         * 일정장소
         */
        leaderScheduleVO.setSchdulPlace(SchedulePlace);
        /*
         * 간부ID
         * 테스트1, USRCNFRM_00000000000
         */
        leaderScheduleVO.setLeaderId(testUserVO.getUniqId());
        /*
         * 반복구분코드
         */
        leaderScheduleVO.setReptitSeCode(repetSepCode);
        /*
         * 일정시작일자
         */
        leaderScheduleVO.setSchdulBgnDe(beginYYYYMMDDHHMM);
        /*
         * 일정종료일자
         */
        leaderScheduleVO.setSchdulEndDe(endYYYYMMDDHHMM);
        /*
         * 일정일자
         */
        if(StringUtils.isNotBlank(beginYYYYMMDDHHMM) && beginYYYYMMDDHHMM.length()>=8) {
            leaderScheduleVO.setSchdulDe(beginYYYYMMDDHHMM.substring(0, 8));
        }
        /*
         * 일정담당자ID
         * 테스트1, USRCNFRM_00000000000
         */
        leaderScheduleVO.setSchdulChargerId(testUserVO.getUniqId());
        /*
         * 최초등록자ID
         */
        leaderScheduleVO.setFrstRegisterId(testUserVO.getUniqId());
        /*
         * 최종수정자ID
         */
        leaderScheduleVO.setLastUpdusrId(testUserVO.getUniqId());

        return leaderScheduleVO;
    }


    /**
     * 간부상태 정보 더미데이터 생성
     * @param leaderSttus: 간부상태
     * @return
     */
    private LeaderSttus makeLeaderSttus(String leaderSttus) {
        /*
         * 테스트 간부 정보를 가져옴
         */
        testUserVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        final LeaderSttus leaderSttusVO = new LeaderSttus();
        /*
         * 간부ID
         * 테스트1, USRCNFRM_00000000000
         */
        leaderSttusVO.setLeaderId(testUserVO.getUniqId());
        /*
         * 간부상태
         * 1(재실), 2(자리비움), 3(회의중), 4(출장중), 5(휴가중)
         */
        leaderSttusVO.setLeaderSttus(leaderSttus);
        /*
         * 최초등록자ID
         */
        leaderSttusVO.setFrstRegisterId(testUserVO.getUniqId());
        /*
         * 최종수정자ID
         */
        leaderSttusVO.setFrstRegisterId(testUserVO.getUniqId());

        return leaderSttusVO;
    }

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void testData() {
        /*
         * 테스트 간부일정 정보 생성
         */
        testLeaderScheduleVO = makeLeaderSchedule("LDSCHDUL_99999999001",
                "1",
                "간부일정 테스트",
                "간부일정 테스트 내용입니다",
                "표준프레임워크센터",
                "1",
                "202311010930",
                "202311011030"
                );

        /*
         * 간부일정 등록
         *
         */
        leaderSchdulDAO.insertLeaderSchdul(testLeaderScheduleVO);

        /*
         * 간부일정 일자 정보 등록
         */
        leaderSchdulDAO.insertLeaderSchdulDe(testLeaderScheduleVO);

        /*
         * 간부상태 정보 등록
         */
        testLeaderSttus = makeLeaderSttus("1");

        leaderSchdulDAO.insertLeaderSttus(testLeaderSttus);
    }

    /**
     * 주어진 조건에 맞는 간부명 조회 테스트 코드
     */
    @Test
    public void testSelectEmplyrList() {
        // given
        final EmplyrVO emplyrVO = new EmplyrVO();
        emplyrVO.setRecordCountPerPage(10);
        emplyrVO.setFirstIndex(0);
        emplyrVO.setSearchCnd("1");
        emplyrVO.setSearchWrd("테스트1");

        /*
         * 로그인 사용자 정보
         * ESNTL_ID(고유ID)         EMPLYR_ID    USER_NM
         * USRCNFRM_00000000000    TEST1        테스트1
         * USRCNFRM_99999999999    webmaster    웹마스터
         */

        // when
        final List<EmplyrVO> resultList = leaderSchdulDAO.selectEmplyrList(emplyrVO);

        for (final EmplyrVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("UserNm={}, {}", result.getSearchWrd(), result.getEmplyrNm());
            }

            // then
            assertSelectEmplyr(emplyrVO.getSearchWrd(), result.getEmplyrNm());
        }
    }

    /**
     * 업무사용자 정보 assert
     */
    private void assertSelectEmplyr(final Object expected, final Object actual) {
        if (expected instanceof EmplyrVO) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), ((EmplyrVO) expected).getEmplNo(), ((EmplyrVO) actual).getEmplNo());
        } else {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), expected, actual);
        }
    }

    /**
     * 주어진 조건에 맞는 간부명 전체 개수 조회 테스트 코드
     */
    @Test
    public void testSelectEmplyrListCnt() {
        // given
        final EmplyrVO emplyrVO = new EmplyrVO();
        emplyrVO.setSearchCnd("1");
        emplyrVO.setSearchWrd("테스트1");

        // when
        final int resultAll = leaderSchdulDAO.selectEmplyrListCnt(new EmplyrVO());
        final int result = leaderSchdulDAO.selectEmplyrListCnt(emplyrVO);

        // then
        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultAll > 0);
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 주어진 조건에 맞는 간부일정 목록 조회 테스트 코드
     */
    @Test
    public void testSelectLeaderSchdulList() {
        // given
        final LeaderSchdulVO leaderSchdulVO = new LeaderSchdulVO();
        leaderSchdulVO.setSearchMode("MONTH");
        leaderSchdulVO.setMonth("10");
        leaderSchdulVO.setSearchCondition("1");
        leaderSchdulVO.setSearchKeyword(testLeaderScheduleVO.getSchdulSe());
        leaderSchdulVO.setSearchKeywordEx("테스트1");

        // when
        final List<LeaderSchdulVO> resultList = leaderSchdulDAO.selectLeaderSchdulList(leaderSchdulVO);

        for (final LeaderSchdulVO result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getSchdulNm={}, {}", leaderSchdulVO.getSearchKeywordEx(), result.getLeaderName());
            }

            // then
            assertSelectLeaderSchedule( leaderSchdulVO.getSearchKeywordEx(), result.getLeaderName());
        }
    }

    /**
     * 간부일정 정보 assert
     */
    private void assertSelectLeaderSchedule(final Object expected, final Object actual) {
        if (expected instanceof LeaderSchdulVO) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), ((LeaderSchdulVO) expected).getSchdulId(), ((LeaderSchdulVO) actual).getSchdulId());
        } else {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), expected, actual);
        }
    }

    /**
     * 간부상태 정보 assert
     */
    private void assertSelectLeaderSttus(final Object expected, final Object actual) {
        if (expected instanceof LeaderSttusVO) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), ((LeaderSttusVO) expected).getLeaderId(), ((LeaderSttusVO) actual).getLeaderId());
        } else {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), expected, actual);
        }
    }

    /**
     * 주어진 조건에 맞는 간부일정 상세정보 조회 테스트 코드
     */
    @Test
    public void testSelectLeaderSchdul() {
        // given
        final LeaderSchdulVO leaderSchdulVO = new LeaderSchdulVO();
        leaderSchdulVO.setSchdulId(testLeaderScheduleVO.getSchdulId());
        leaderSchdulVO.setSchdulDe(testLeaderScheduleVO.getSchdulDe());

        // when
        final LeaderSchdulVO result = leaderSchdulDAO.selectLeaderSchdul(leaderSchdulVO);
        // log.debug("result = {}", result);

        // then
        assertSelectLeaderSchedule( leaderSchdulVO, result);
    }

    /**
     * 간부일정 상세정보 수정 테스트 코드
     */
    @Test
    public void testUpdateLeaderSchdul() {
        // given
        testLeaderScheduleVO.setSchdulNm(testLeaderScheduleVO.getSchdulNm() + " (수정)");

        // when
        final int result = leaderSchdulDAO.updateLeaderSchdul(testLeaderScheduleVO);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 간부일정 등록 테스트 코드
     */
    @Test
    public void testInsertLeaderSchdul() {
        // given
        final LeaderSchdulVO leaderScheduleVO = makeLeaderSchedule("LDSCHDUL_99999999002",
                "2",
                "간부일정 테스트2",
                "간부일정 테스트2 내용입니다",
                "표준프레임워크센터",
                "3",
                "202311070930",
                "202311071030"
                );
        // log.debug("scheduleId = {}", leaderScheduleVO.getSchdulId());

        // when
        final int result = leaderSchdulDAO.insertLeaderSchdul(leaderScheduleVO);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 간부일정 일자 정보 등록 테스트 코드
     */
    @Test
    public void testInsertLeaderSchdulDe() {
        // given
        final LeaderSchdulVO leaderScheduleVO = makeLeaderSchedule("LDSCHDUL_99999999002",
                "2",
                "간부일정 테스트2",
                "간부일정 테스트2 내용입니다",
                "표준프레임워크센터",
                "3",
                "202311070930",
                "202311071030"
                );
        // log.debug("scheduleId = {}", leaderScheduleVO.getSchdulId());
        // 간부일정을 먼저 등록해야 됨.
        leaderSchdulDAO.insertLeaderSchdul(leaderScheduleVO);

        // when
        final int result = leaderSchdulDAO.insertLeaderSchdulDe(leaderScheduleVO);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 간부일정 삭제 테스트 코드
     */
    @Test
    public void testDeleteLeaderSchdul() {
        // given
        final LeaderSchdulVO leaderScheduleVO = new LeaderSchdulVO();
        leaderScheduleVO.setSchdulId(testLeaderScheduleVO.getSchdulId());

        // foreign key constraint로 일정 정보부터 삭제해야 함
        leaderSchdulDAO.deleteLeaderSchdulDe(leaderScheduleVO);

        // when
        final int result = leaderSchdulDAO.deleteLeaderSchdul(leaderScheduleVO);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }

    /**
     * 간부일정 일자 정보 삭제 테스트 코드
     */
    @Test
    public void testDeleteLeaderSchdulDe() {
        // given
        final LeaderSchdulVO leaderScheduleVO = new LeaderSchdulVO();
        leaderScheduleVO.setSchdulId(testLeaderScheduleVO.getSchdulId());

        // when
        final int result = leaderSchdulDAO.deleteLeaderSchdulDe(leaderScheduleVO);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }


    /**
     * 간부상태정보 목록 조회 테스트 코드
     */
    @Test
    public void testSelectLeaderSttusList() {
        // given
        final LeaderSttusVO leaderSttusVO = new LeaderSttusVO();
        leaderSttusVO.setFirstIndex(0);
        leaderSttusVO.setSearchCnd("1");
        leaderSttusVO.setSearchWrd("테스트1");

        // when
        final List<LeaderSttusVO> resultList = leaderSchdulDAO.selectLeaderSttusList(leaderSttusVO);

        for (final LeaderSttusVO result : resultList) {
            log.debug("result={}", result);
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("getLeaderNm={}, {}", leaderSttusVO.getSearchWrd(), result.getLeaderNm());
            }

            // then
            assertSelectLeaderSttus( leaderSttusVO.getSearchWrd(), result.getLeaderNm());
        }
    }

    /**
     * 주어진 조건에 맞는 간부상태 전체 개수 조회 테스트 코드
     */
    @Test
    public void testSelectLeaderSttusListCnt() {
        // given
        final LeaderSttusVO leaderSttusVO = new LeaderSttusVO();
        leaderSttusVO.setSearchCnd("1");
        leaderSttusVO.setSearchWrd("테스트1");

        // when
        final int resultAll = leaderSchdulDAO.selectLeaderSttusListCnt(new LeaderSttusVO());
        final int result = leaderSchdulDAO.selectLeaderSttusListCnt(leaderSttusVO);

        // then
        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultAll > 0);
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 주어진 조건에 맞는 간부상태 상세정보 조회 테스트 코드
     */
    @Test
    public void testSelectLeaderSttus() {
        // given
        final LeaderSttusVO leaderSttusVO = new LeaderSttusVO();
        leaderSttusVO.setLeaderId(testLeaderSttus.getLeaderId());

        // when
        final LeaderSttusVO result = leaderSchdulDAO.selectLeaderSttus(leaderSttusVO);
        // log.debug("result = {}", result);

        // then
        assertSelectLeaderSttus(leaderSttusVO, result);
    }

    /**
     * 간부상태 상세정보 수정 테스트 코드
     */
    @Test
    public void testUpdateLeaderSttus() {
        // given
        testLeaderSttus.setLeaderSttus("2");

        // when
        final int result = leaderSchdulDAO.updateLeaderSttus(testLeaderSttus);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }
}
