package egovframework.com.dam.mgm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.sql.DataSource;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 지식정보에 대한 DAO 클래스 단위 테스트
 * @author 주레피
 * @created 2024-02-20 11:16
 *
 */
@ContextConfiguration(classes = { EgovTestAbstractDAO.class, KnoManagementDAOTest.class })
@Configuration
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {
                "egovframework.com.dam.mgm.service.impl"
        },
        includeFilters = {
                @Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                KnoManagementDAO.class
                        }
                        )
        }
        )
@NoArgsConstructor
@Slf4j
// @Commit // 주석을 없애면 테이블에 입력됨
public class KnoManagementDAOTest extends EgovTestAbstractDAO{

    /**
     * DataSource
     */
    @Autowired
    private DataSource dataSource;

    /**
     * JdbcTemplate
     * Mybatis 지식관리 Mapper(EgovKnoManagement_SQL_*.xml)내에 insert 구문이 없음
     * 임시 방편으로 추가하기 위해 jdbcTemplate를 사용하고자 함.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * KnoManagementDAO
     */
    @Autowired
    private KnoManagementDAO knoManagementDAO;

    /**
     * default testUserVO
     */
    private LoginVO testUserVO;

    /**
     *
     */
    private String knoId = "COMTNDAMKNOIFM_00001"; // 지식 ID

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void testData() {
        jdbcTemplate = new JdbcTemplate(dataSource); // 데이터소스로부터 jdbcTemplate를 가져옴
        testUserVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser(); // 사용자 정보
        final String organId = "COMTNDAMMAPTEAM_0001"; // 지색맵 조직 ID
        final String knoTypeId = "001"; // 지식 유형 ID
        final String knoId = "COMTNDAMKNOIFM_00001"; // 지식 ID

        // 지식맵(조직별) 추가
        String insertSql = "INSERT INTO COMTNDAMMAPTEAM(ORGNZT_ID, ORGNZT_NM, CL_DE, KNWLDG_URL, LAST_UPDUSR_ID, LAST_UPDT_PNTTM) "
                + "VALUES('" + organId + "', '기획팀', '2024-02-01'"
                + ", 'https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:%EC%A7%80%EC%8B%9D%EC%A0%95%EB%B3%B4%EA%B4%80%EB%A6%AC'"
                + ", '" + testUserVO.getUniqId() + "', NOW())";
        jdbcTemplate.execute(insertSql);

        // 지식맵(유형별) 추가
        insertSql = "INSERT INTO com.COMTNDAMMAPKNO(KNWLDG_TY_CODE, ORGNZT_ID, EXPERT_ID, KNWLDG_TY_NM, CL_DE, KNWLDG_URL"
                + ", FRST_REGISTER_ID, FRST_REGIST_PNTTM, LAST_UPDUSR_ID, LAST_UPDT_PNTTM) "
                + "VALUES('" + knoTypeId + "', '" + organId + "', '" + testUserVO.getUniqId() + "', '회계', '2024-02-01'"
                + ", 'https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:%EC%A7%80%EC%8B%9D%EC%A0%95%EB%B3%B4%EA%B4%80%EB%A6%AC'"
                + ", '" + testUserVO.getUniqId() + "', NOW(), '" + testUserVO.getUniqId() + "', NOW())";
        jdbcTemplate.execute(insertSql);

        // 지식정보 추가
        insertSql = "INSERT INTO com.COMTNDAMKNOIFM(KNWLDG_ID, KNWLDG_TY_CODE, ORGNZT_ID, EXPERT_ID, EMPLYR_ID"
                + ", KNWLDG_NM, KNWLDG_CN, KWRD, OTHBC_AT, KNWLDG_EVL, COLCT_DE, EVL_DE, ATCH_FILE_ID"
                + ", FRST_REGISTER_ID, FRST_REGIST_PNTTM, LAST_UPDUSR_ID, LAST_UPDT_PNTTM, DSUSE_DE) "
                + "VALUES('" + knoId + "', '" + knoTypeId + "', '" + organId + "', '" + testUserVO.getUniqId() + "', '" + testUserVO.getUniqId() + "'"
                + ", '세금계산서', '세금계산서 작성법', '세금계산서', 'Y', '1', '2024-02-01', '2024-02-20', NULL"
                + ", '" + testUserVO.getUniqId() + "', NOW(), '" + testUserVO.getUniqId() + "', NOW(), '2024-02-28')";
        this.knoId = knoId;
        jdbcTemplate.execute(insertSql);
    }

    /**
     * 지식정보 assert
     */
    private void assertSelectKnowInfo(final Object expected, final Object actual) {
        if (expected instanceof KnoManagementVO) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), ((KnoManagementVO) expected).getKnoId(), ((KnoManagementVO) actual).getKnoId());
        } else {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), expected, actual);
        }
    }

    /**
     * 주어진 조건에 맞는 지식정보 목록 조회 테스트 코드
     */
    @Test
    public void a01selectKnoManagementList() {
        // given
        final KnoManagementVO searchVO = new KnoManagementVO();
        searchVO.setRecordCountPerPage(10);
        searchVO.setFirstIndex(0);
        searchVO.setSearchCondition("2"); // KNWLDG_NM(1), USER_NM(2)
        searchVO.setSearchKeyword("테스트1");

        /**
         * 로그인 사용자 정보
         * ESNTL_ID(고유ID)         EMPLYR_ID    USER_NM
         * USRCNFRM_00000000000    TEST1        테스트1
         * USRCNFRM_99999999999    webmaster    웹마스터
         */

        // when
        final List<EgovMap> resultList = knoManagementDAO.selectKnoManagementList(searchVO);

        for (final EgovMap result : resultList) {
            if (log.isDebugEnabled()) {
                log.debug("result={}", result);
                log.debug("UserNm={}, {}", searchVO.getSearchKeyword(), result.get("userNm"));
            }

            // then
            assertSelectKnowInfo(searchVO.getSearchKeyword(), result.get("userNm"));
        }
    }

    /**
     * 지식정보 목록 전체 개수 조회 테스트 코드
     */
    @Test
    public void a02selectKnoManagementTotCnt() {
        // given
        final KnoManagementVO searchVO = new KnoManagementVO();
        searchVO.setSearchCondition("2");
        searchVO.setSearchKeyword("테스트1");

        // when
        final int resultAll = knoManagementDAO.selectKnoManagementTotCnt(new KnoManagementVO());
        final int result = knoManagementDAO.selectKnoManagementTotCnt(searchVO);

        // then
        assertTrue(egovMessageSource.getMessage(FAIL_COMMON_SELECT), resultAll > 0);
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, result);
    }

    /**
     * 주어진 조건에 맞는 지식정보 조회 테스트 코드
     */
    @Test
    public void a03selectKnoManagement() {
        // given
        final KnoManagement knoManagement = new KnoManagement(); // 관리자
        /**
         * 로그인 사용자 정보
         * ESNTL_ID(고유ID)         EMPLYR_ID    USER_NM    OFCPS_NM
         * USRCNFRM_00000000000    TEST1        테스트1     관리자
         * USRCNFRM_99999999999    webmaster    웹마스터     웹관리자
         */
        knoManagement.setEmplyrId(testUserVO.getUniqId());
        knoManagement.setKnoId(knoId);

        // when
        final KnoManagement result = knoManagementDAO.selectKnoManagement(knoManagement);

        // then
        assertSelectKnowInfo(knoId, result.getKnoId());
    }
}
