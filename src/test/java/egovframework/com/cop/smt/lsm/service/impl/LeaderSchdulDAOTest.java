package egovframework.com.cop.smt.lsm.service.impl;

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
import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
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

    /*
     * testLeasderScheduleVO
     */
    private LeaderSchdulVO testLeaderScheduleVO;

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void testData() {

        /*
         * 테스트 간부일정 정보 생성
         */
        testLeaderScheduleVO = new LeaderSchdulVO();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        /*
         * 일정ID
         */
        testLeaderScheduleVO.setSchdulId("LDSCHDUL_99999999001");
        /*
         * 일정구분
         */
        testLeaderScheduleVO.setSchdulSe("1");
        /*
         * 일정명
         */
        testLeaderScheduleVO.setSchdulNm("간부일정 테스트");
        /*
         * 일정내용
         */
        testLeaderScheduleVO.setSchdulCn("간부일정 테스트 내용입니다.");
        /*
         * 일정장소
         */
        testLeaderScheduleVO.setSchdulPlace("표준프레임워크센터");
        /*
         * 간부ID
         * 테스트1, USRCNFRM_00000000000
         */
        testLeaderScheduleVO.setLeaderId(loginVO.getUniqId());
        /*
         * 반복구분코드
         */
        testLeaderScheduleVO.setReptitSeCode("1");
        /*
         * 일정시작일자
         */
        testLeaderScheduleVO.setSchdulBgnDe("202311010000");
        /*
         * 일정종료일자
         */
        testLeaderScheduleVO.setSchdulEndDe("202311010000");
        /*
         * 일정일자
         */
        testLeaderScheduleVO.setSchdulDe("20231101");
        /*
         * 일정담당자ID
         * 테스트1, USRCNFRM_00000000000
         */
        testLeaderScheduleVO.setSchdulChargerId(loginVO.getUniqId());
        /*
         * 최초등록자ID
         */
        testLeaderScheduleVO.setFrstRegisterId(loginVO.getUniqId());
        /*
         * 최종수정자ID
         */
        testLeaderScheduleVO.setLastUpdusrId(loginVO.getUniqId());

        /*
         * 간부일정 등록
         */
        leaderSchdulDAO.insertLeaderSchdul(testLeaderScheduleVO);
        /*
         * 간부일정 일자 정보 등록
         */
        leaderSchdulDAO.insertLeaderSchdulDe(testLeaderScheduleVO);
    }

    /**
     * 주어진 조건에 맞는 간부명을 가져온다.
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
     * 주어진 조건에 맞는 간부일정 목록을 가져온다.
     */
    @Test
    public void testSelectLeaderSchdulList() {
        // given
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        log.info("loginVO = {} {}", loginVO, loginVO.getName());

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
     * 주어진 조건에 맞는 간부일정 상세정보를 가져온다.
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
}
