package egovframework.com.cop.smt.lsm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

import egovframework.com.cop.smt.lsm.service.EmplyrVO;
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
     * 주어진 조건에 맞는 간부명 전체 개수를 가져온다.
     */
    @Test
    public void testSelectLeaderSchdulList() {
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
}
