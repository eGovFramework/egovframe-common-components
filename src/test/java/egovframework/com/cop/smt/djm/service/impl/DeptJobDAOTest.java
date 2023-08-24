package egovframework.com.cop.smt.djm.service.impl;

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
import egovframework.com.cop.smt.djm.service.ChargerVO;
import egovframework.com.cop.smt.djm.service.DeptJob;
import egovframework.com.cop.smt.djm.service.DeptJobBx;
import egovframework.com.cop.smt.djm.service.DeptVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 부서업무 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-08-21
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, DeptJobDAOTest.class, })

@Configuration

@ImportResource({

        "classpath*:egovframework/spring/com/idgn/context-idgn-DeptJob.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.smt.djm.service.impl",

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                DeptJobDAO.class,

                        }

                )

        }

)

@NoArgsConstructor
@Slf4j
public class DeptJobDAOTest extends EgovTestAbstractDAO {

    /**
     * 부서업무 DAO
     */
    @Autowired
    private DeptJobDAO deptJobDAO;

    /**
     * 부서업무함
     */
    @Autowired
    @Qualifier("egovDeptJobBxIdGnrService")
    private EgovIdGnrService egovDeptJobBxIdGnrService;

    /**
     * 부서업무
     */
    @Autowired
    @Qualifier("egovDeptJobIdGnrService")
    private EgovIdGnrService egovDeptJobIdGnrService;

    /**
     * 부서업무함 정보를 등록한다.
     */
    @Test
    public void insertDeptJobBx() {
        // given
        final DeptJobBx deptJobBx = new DeptJobBx();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            deptJobBx.setDeptJobBxId(egovDeptJobBxIdGnrService.getNextStringId());
        } catch (FdlException e) {
//            e.printStackTrace();
            log.error("FdlException egovDeptJobBxIdGnrService");
        }

        deptJobBx.setDeptJobBxNm("test 이백행 부서업무함명 " + LocalDateTime.now());

        if (loginVO != null) {
            deptJobBx.setDeptId(loginVO.getOrgnztId());

            deptJobBx.setFrstRegisterId(loginVO.getUniqId());
            deptJobBx.setLastUpdusrId(loginVO.getUniqId());
        }

        // when
        final int result = deptJobDAO.insertDeptJobBx(deptJobBx);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);

        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }
    }

    /**
     * 부서업무 정보를 등록한다.
     */
    @Test
    public void insertDeptJob() {
        // given
        final DeptJob deptJob = new DeptJob();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            deptJob.setDeptJobId(egovDeptJobIdGnrService.getNextStringId());
            deptJob.setDeptJobBxId(egovDeptJobBxIdGnrService.getNextStringId());
        } catch (FdlException e) {
//            e.printStackTrace();
            log.error("FdlException egovDeptJobBxIdGnrService");
        }

        deptJob.setDeptJobNm("test 이백행 부서업무명 " + LocalDateTime.now());
        deptJob.setDeptJobCn("test 이백행 부서업무내용 " + LocalDateTime.now());
        deptJob.setPriort("1"); // 우선순위

        if (loginVO != null) {
            deptJob.setChargerId(loginVO.getUniqId());

            deptJob.setFrstRegisterId(loginVO.getUniqId());
            deptJob.setLastUpdusrId(loginVO.getUniqId());
        }

        // when
        final int result = deptJobDAO.insertDeptJob(deptJob);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);

        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }
    }

    /**
     * 주어진 조건에 맞는 담당자를 불러온다.
     */
    @Test
    public void selectChargerList() {
        // given
        final ChargerVO chargerVO = new ChargerVO();
//        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        chargerVO.setFirstIndex(0);
        chargerVO.setRecordCountPerPage(10);

        chargerVO.setSearchCnd("0");
        chargerVO.setSearchWrd("기본조직");

//        if (loginVO != null) {
//            chargerVO.setSearchCnd("1");
//            chargerVO.setSearchWrd(loginVO.getName());
//        }

        // when
        final List<ChargerVO> resultList = deptJobDAO.selectChargerList(chargerVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), chargerVO.getSearchWrd(),
                resultList.get(0).getOrgnztNm());

        if (log.isDebugEnabled()) {
            for (final ChargerVO result : resultList) {
                log.debug("result={}", result);
                log.debug("getUniqId={}", result.getUniqId());
                log.debug("getOrgnztNm={}, {}", chargerVO.getSearchWrd(), result.getOrgnztNm());
            }
        }
    }

    /**
     * 담당자 목록에 대한 전체 건수를 조회한다.
     */
    @Test
    public void selectChargerListCnt() {
        // given
        final ChargerVO chargerVO = new ChargerVO();
//        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        chargerVO.setSearchCnd("0");
        chargerVO.setSearchWrd("기본조직");

//        if (loginVO != null) {
//            chargerVO.setSearchCnd("1");
//            chargerVO.setSearchWrd(loginVO.getName());
//        }

        // when
        final int totCnt = deptJobDAO.selectChargerListCnt(chargerVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("totCnt={}", totCnt);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, totCnt > -1);
    }

    /**
     * 주어진 조건에 맞는 부서를 불러온다.
     */
    @Test
    public void selectDeptList() {
        // given
        final DeptVO deptVO = new DeptVO();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        deptVO.setFirstIndex(0);
        deptVO.setRecordCountPerPage(10);

        deptVO.setSearchCnd("0");
        deptVO.setSearchWrd("기본조직");

//        if (loginVO != null) {
//            deptVO.setSearchCnd("1");
//            deptVO.setSearchWrd("기본조직");
//        }

        // when
        final List<DeptVO> resultList = deptJobDAO.selectDeptList(deptVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("totCnt={}", resultList);
            for (final DeptVO result : resultList) {
                log.debug("result={}", result);
                log.debug("getOrgnztId={}, {}", loginVO.getOrgnztId(), result.getOrgnztId());
                if ("0".equals(deptVO.getSearchCnd())) {
                    log.debug("getOrgnztId={}, {}", deptVO.getSearchWrd(), result.getOrgnztNm());
                } else if ("1".equals(deptVO.getSearchCnd())) {
                    log.debug("getOrgnztNm={}, {}", deptVO.getSearchWrd(), result.getOrgnztDc());
                }
            }
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), loginVO.getOrgnztId(),
                resultList.get(0).getOrgnztId());
        if ("0".equals(deptVO.getSearchCnd())) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), deptVO.getSearchWrd(),
                    resultList.get(0).getOrgnztNm());
        } else if ("1".equals(deptVO.getSearchCnd())) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), deptVO.getSearchWrd(),
                    resultList.get(0).getOrgnztDc());
        }

    }

}
