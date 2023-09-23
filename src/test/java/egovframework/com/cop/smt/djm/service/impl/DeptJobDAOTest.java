package egovframework.com.cop.smt.djm.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import egovframework.com.cop.smt.djm.service.DeptJobBxVO;
import egovframework.com.cop.smt.djm.service.DeptJobVO;
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

    private void testDataDeptJobBx(final DeptJobBx testDataDeptJobBx) {
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            testDataDeptJobBx.setDeptJobBxId(egovDeptJobBxIdGnrService.getNextStringId());
        } catch (FdlException e) {
//            e.printStackTrace();
            log.error("FdlException egovDeptJobBxIdGnrService");
            fail("FdlException egovDeptJobBxIdGnrService");
        }

        testDataDeptJobBx.setDeptJobBxNm("test 이백행 부서업무함명 " + LocalDateTime.now());

        if (loginVO != null) {
            testDataDeptJobBx.setDeptId(loginVO.getOrgnztId());

            testDataDeptJobBx.setFrstRegisterId(loginVO.getUniqId());
            testDataDeptJobBx.setLastUpdusrId(loginVO.getUniqId());
        }

        deptJobDAO.insertDeptJobBx(testDataDeptJobBx);
    }

    private void testDataDeptJob(final DeptJobBx testDataDeptJobBx, final DeptJob testDataDeptJob) {
        // given
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            testDataDeptJob.setDeptJobId(egovDeptJobIdGnrService.getNextStringId());
            testDataDeptJob.setDeptJobBxId(testDataDeptJobBx.getDeptJobBxId());
        } catch (FdlException e) {
//            e.printStackTrace();
            log.error("FdlException egovDeptJobBxIdGnrService");
            fail("FdlException egovDeptJobBxIdGnrService");
        }

        testDataDeptJob.setDeptJobNm("test 이백행 부서업무명 " + LocalDateTime.now());
        testDataDeptJob.setDeptJobCn("test 이백행 부서업무내용 " + LocalDateTime.now());
        testDataDeptJob.setPriort("1"); // 우선순위

        if (loginVO != null) {
            testDataDeptJob.setChargerId(loginVO.getUniqId());

            testDataDeptJob.setFrstRegisterId(loginVO.getUniqId());
            testDataDeptJob.setLastUpdusrId(loginVO.getUniqId());
        }

        deptJobDAO.insertDeptJob(testDataDeptJob);
    }

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

//        deptVO.setSearchCnd("1");
//        deptVO.setSearchWrd("기본조직");

        String orgnztId = null;
        if (loginVO != null) {
            orgnztId = loginVO.getOrgnztId();
        }

        // when
        final List<DeptVO> resultList = deptJobDAO.selectDeptList(deptVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("resultList={}", resultList);
            for (final DeptVO result : resultList) {
                log.debug("result={}", result);
                log.debug("getOrgnztId={}, {}", orgnztId, result.getOrgnztId());
                if ("0".equals(deptVO.getSearchCnd())) {
                    log.debug("getOrgnztId={}, {}", deptVO.getSearchWrd(), result.getOrgnztNm());
                } else if ("1".equals(deptVO.getSearchCnd())) {
                    log.debug("getOrgnztNm={}, {}", deptVO.getSearchWrd(), result.getOrgnztDc());
                }
            }
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), orgnztId, resultList.get(0).getOrgnztId());
        if ("0".equals(deptVO.getSearchCnd())) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), deptVO.getSearchWrd(),
                    resultList.get(0).getOrgnztNm());
        } else if ("1".equals(deptVO.getSearchCnd())) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), deptVO.getSearchWrd(),
                    resultList.get(0).getOrgnztDc());
        }
    }

    /**
     * 부서 목록에 대한 전체 건수를 조회한다.
     */
    @Test
    public void selectDeptListCnt() {
        // given
        final DeptVO deptVO = new DeptVO();

        deptVO.setSearchCnd("0");
        deptVO.setSearchWrd("기본조직");

//        deptVO.setSearchCnd("1");
//        deptVO.setSearchWrd("기본조직");

        // when
        final int totCnt = deptJobDAO.selectDeptListCnt(deptVO);

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
    public void selectDept() {
        // given
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        String orgnztId = null;
        if (loginVO != null) {
            orgnztId = loginVO.getOrgnztId();
        }

        // when
        final String result = deptJobDAO.selectDept(orgnztId);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "기본조직", result);
    }

    /**
     * 주어진 조건에 따른 부서업무함 목록을 불러온다.
     */
    @Test
    public void selectDeptJobBxList() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBxVO deptJobBxVO = new DeptJobBxVO();
//        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        deptJobBxVO.setFirstIndex(0);
        deptJobBxVO.setRecordCountPerPage(10);

//        if (loginVO != null) {
//            deptJobBxVO.setPopupCnd(loginVO.getOrgnztId());
//            deptJobBxVO.setDeptId(loginVO.getOrgnztId());
//        }

//        deptJobBxVO.setSearchCnd("0");
//        deptJobBxVO.setSearchWrd("기본조직");

        deptJobBxVO.setSearchCnd("1");
        deptJobBxVO.setSearchWrd(testDataDeptJobBx.getDeptJobBxNm());

        // when
        final List<DeptJobBxVO> resultList = deptJobDAO.selectDeptJobBxList(deptJobBxVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("resultList={}", resultList);
            for (final DeptJobBxVO result : resultList) {
                log.debug("result={}", result);
                if ("0".equals(deptJobBxVO.getSearchCnd())) {
                    log.debug("getDeptNm={}, {}", deptJobBxVO.getSearchWrd(), result.getDeptNm());
                } else if ("1".equals(deptJobBxVO.getSearchCnd())) {
                    log.debug("getDeptJobBxNm={}, {}", deptJobBxVO.getSearchWrd(), result.getDeptJobBxNm());
                }
            }
        }

        if ("0".equals(deptJobBxVO.getSearchCnd())) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), deptJobBxVO.getSearchWrd(),
                    resultList.get(0).getDeptNm());
        } else if ("1".equals(deptJobBxVO.getSearchCnd())) {
            assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), deptJobBxVO.getSearchWrd(),
                    resultList.get(0).getDeptJobBxNm());
        }
    }

    /**
     * 주어진 조건에 따른 부서업무함 목록 전체를 불러온다.
     */
    @Test
    public void selectDeptJobBxListAll() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        // when
        final List<DeptJobBxVO> resultList = deptJobDAO.selectDeptJobBxListAll();

        // then
        if (log.isDebugEnabled()) {
            log.debug("resultList={}", resultList);
            log.debug("size={}", resultList.size());
            for (final DeptJobBxVO result : resultList) {
                log.debug("result={}", result);
                log.debug("getDeptId={}", result.getDeptId());
                log.debug("getDeptNm={}", result.getDeptNm());
                log.debug("getDeptJobBxId={}", result.getDeptJobBxId());
                log.debug("getDeptJobBxNm={}", result.getDeptJobBxNm());
            }
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, resultList != null);
    }

    /**
     * 부서업무함 목록에 대한 전체 건수를 조회한다.
     */
    @Test
    public void selectDeptJobBxListCnt() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBxVO deptJobBxVO = new DeptJobBxVO();
//        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

//        if (loginVO != null) {
//            deptJobBxVO.setPopupCnd(loginVO.getOrgnztId());
//            deptJobBxVO.setDeptId(loginVO.getOrgnztId());
//        }

//        deptJobBxVO.setSearchCnd("0");
//        deptJobBxVO.setSearchWrd("기본조직");

        deptJobBxVO.setSearchCnd("1");
        deptJobBxVO.setSearchWrd(testDataDeptJobBx.getDeptJobBxNm());

        // when
        final int result = deptJobDAO.selectDeptJobBxListCnt(deptJobBxVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, result > -1);
    }

    /**
     * 주어진 조건에 맞는 부서업무함을 불러온다.
     */
    @Test
    public void selectDeptJobBx() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBxVO deptJobBxVO = new DeptJobBxVO();
        deptJobBxVO.setDeptJobBxId(testDataDeptJobBx.getDeptJobBxId());

        // when
        final DeptJobBxVO result = deptJobDAO.selectDeptJobBx(deptJobBxVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
            log.debug("getDeptJobBxId={}, {}", testDataDeptJobBx.getDeptJobBxId(), result.getDeptJobBxId());
            log.debug("getDeptId={}, {}", testDataDeptJobBx.getDeptId(), result.getDeptId());
            log.debug("getDeptJobBxNm={}, {}", testDataDeptJobBx.getDeptJobBxNm(), result.getDeptJobBxNm());
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataDeptJobBx.getDeptJobBxId(),
                result.getDeptJobBxId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataDeptJobBx.getDeptId(),
                result.getDeptId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataDeptJobBx.getDeptJobBxNm(),
                result.getDeptJobBxNm());
    }

    /**
     * 부서업무함 정보를 수정한다.
     */
    @Test
    public void updateDeptJobBx() {
        // given
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBx deptJobBx = new DeptJobBx();
        deptJobBx.setDeptJobBxId(testDataDeptJobBx.getDeptJobBxId());
        deptJobBx.setDeptId(testDataDeptJobBx.getDeptId());
        deptJobBx.setDeptJobBxNm("test 이백행 부서업무함명 수정 " + LocalDateTime.now());
        deptJobBx.setIndictOrdr(10);

        if (loginVO != null) {
            deptJobBx.setLastUpdusrId(loginVO.getUniqId());
        }

        // when
        final int result = deptJobDAO.updateDeptJobBx(deptJobBx);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 부서업무함의 표시순서가 중복되는지를 조회한다.
     */
    @Test
    public void selectDeptJobBxOrdr() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBxVO deptJobBxVO = new DeptJobBxVO();
        deptJobBxVO.setDeptJobBxId(testDataDeptJobBx.getDeptJobBxId());
        deptJobBxVO.setDeptId(testDataDeptJobBx.getDeptId());
        deptJobBxVO.setIndictOrdr(testDataDeptJobBx.getIndictOrdr());
//        deptJobBxVO.setOrdrCnd("up");
//        deptJobBxVO.setOrdrCnd("down");

        // when
        final int result = deptJobDAO.selectDeptJobBxOrdr(deptJobBxVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, result > -1);
    }

    /**
     * 부서업무함 정보의 표시순서를 수정한다. (표시순서 증가)
     */
    @Test
    public void updateDeptJobBxOrdrUp() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBx deptJobBx = new DeptJobBx();
        deptJobBx.setDeptId(testDataDeptJobBx.getDeptId());
        deptJobBx.setIndictOrdr(testDataDeptJobBx.getIndictOrdr());

        // when
        final int result = deptJobDAO.updateDeptJobBxOrdrUp(deptJobBx);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage("fail.common.update"), true, result > -1);
    }

    /**
     * 부서업무함 정보의 표시순서를 수정한다. (표시순서 감소)
     */
    @Test
    public void updateDeptJobBxOrdrDown() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBx deptJobBx = new DeptJobBx();
        deptJobBx.setDeptId(testDataDeptJobBx.getDeptId());
        deptJobBx.setIndictOrdr(testDataDeptJobBx.getIndictOrdr());

        // when
        final int result = deptJobDAO.updateDeptJobBxOrdrDown(deptJobBx);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage("fail.common.update"), true, result > -1);
    }

    /**
     * 부서업무함 정보의 표시순서를 수정한다.
     */
    @Test
    public void updateDeptJobBxOrdr() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBx deptJobBx = new DeptJobBx();
        deptJobBx.setDeptJobBxId(testDataDeptJobBx.getDeptJobBxId());
        deptJobBx.setIndictOrdr(1);

        // when
        final int result = deptJobDAO.updateDeptJobBxOrdr(deptJobBx);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage("fail.common.update"), true, result > 0);
    }

    /**
     * 주어진 조건에 만족하는 전체 부서업무함 정보의 표시순서를 수정한다.
     */
    @Test
    public void updateDeptJobBxOrdrAll() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBxVO deptJobBxVO = new DeptJobBxVO();
        deptJobBxVO.setDeptId(testDataDeptJobBx.getDeptId());
        deptJobBxVO.setIndictOrdr(testDataDeptJobBx.getIndictOrdr());

        // when
        final int result = deptJobDAO.updateDeptJobBxOrdrAll(deptJobBxVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage("fail.common.update"), true, result > 0);
    }

    /**
     * 등록시 부서업무함의 표시순서를 조회한다.
     */
    @Test
    public void selectMaxDeptJobBxOrdr() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final String deptId = testDataDeptJobBx.getDeptId();

        // when
        final int result = deptJobDAO.selectMaxDeptJobBxOrdr(deptId);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, result > -1);
    }

    /**
     * 부서내 부서업무함명의 건수를 조회한다.
     */
    @Test
    public void selectDeptJobBxCheck() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBx deptJobBx = new DeptJobBx();
        deptJobBx.setDeptId(testDataDeptJobBx.getDeptId());
        deptJobBx.setDeptJobBxNm(testDataDeptJobBx.getDeptJobBxNm());

        // when
        final int result = deptJobDAO.selectDeptJobBxCheck(deptJobBx);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, result > -1);
    }

    /**
     * 부서업무함 정보를 삭제한다.
     */
    @Test
    public void deleteDeptJobBx() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);

        final DeptJobBx deptJobBx = new DeptJobBx();
        deptJobBx.setDeptJobBxId(testDataDeptJobBx.getDeptJobBxId());

        // when
        final int result = deptJobDAO.deleteDeptJobBx(deptJobBx);

        // then
        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }

        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 주어진 조건에 따른 부서업무 목록을 불러온다.
     */
    @Test
    public void selectDeptJobList() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);
        final DeptJob testDataDeptJob = new DeptJob();
        testDataDeptJob(testDataDeptJobBx, testDataDeptJob);

        final DeptJobVO deptJobVO = new DeptJobVO();
        deptJobVO.setFirstIndex(0);
        deptJobVO.setRecordCountPerPage(10);
//        deptJobVO.setSearchDeptJobBxId(FAIL_COMMON_SELECT);
        deptJobVO.setSearchDeptId(testDataDeptJobBx.getDeptId());

        // when
        final List<DeptJobVO> resultList = deptJobDAO.selectDeptJobList(deptJobVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("resultList={}", resultList);
            for (final DeptJobVO result : resultList) {
                log.debug("result={}", result);
                log.debug("result={}, {}", testDataDeptJobBx.getDeptId(), result.getDeptId());
            }
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataDeptJobBx.getDeptId(),
                resultList.get(0).getDeptId());
    }

    /**
     * 부서업무 목록에 대한 전체 건수를 조회한다.
     */
    @Test
    public void selectDeptJobListCnt() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);
        final DeptJob testDataDeptJob = new DeptJob();
        testDataDeptJob(testDataDeptJobBx, testDataDeptJob);

        final DeptJobVO deptJobVO = new DeptJobVO();
//        deptJobVO.setSearchDeptJobBxId(FAIL_COMMON_SELECT);
        deptJobVO.setSearchDeptId(testDataDeptJobBx.getDeptId());

        // when
        final int totCnt = deptJobDAO.selectDeptJobListCnt(deptJobVO);

        // then
        log.debug("totCnt={}", totCnt);

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), true, totCnt > -1);
    }

    /**
     * 주어진 조건에 맞는 부서업무를 불러온다.
     */
    @Test
    public void selectDeptJob() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);
        final DeptJob testDataDeptJob = new DeptJob();
        testDataDeptJob(testDataDeptJobBx, testDataDeptJob);

        final DeptJobVO deptJobVO = new DeptJobVO();
        deptJobVO.setDeptJobId(testDataDeptJob.getDeptJobId());

        // when
        final DeptJobVO result = deptJobDAO.selectDeptJob(deptJobVO);

        // then
        log.debug("result={}", result);
        log.debug("getDeptId={}, {}", testDataDeptJobBx.getDeptJobBxId(), result.getDeptJobId());

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataDeptJob.getDeptJobId(),
                result.getDeptJobId());
    }

    /**
     * 부서업무 정보를 수정한다.
     */
    @Test
    public void updateDeptJob() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);
        final DeptJob testDataDeptJob = new DeptJob();
        testDataDeptJob(testDataDeptJobBx, testDataDeptJob);

        final DeptJob deptJob = new DeptJob();
        deptJob.setDeptJobBxId(testDataDeptJob.getDeptJobBxId());
        deptJob.setDeptJobNm(testDataDeptJob.getDeptJobNm());
        deptJob.setDeptJobCn(testDataDeptJob.getDeptJobCn());
        deptJob.setChargerId(testDataDeptJob.getChargerId());
        deptJob.setPriort(testDataDeptJob.getPriort());

        deptJob.setDeptJobId(testDataDeptJob.getDeptJobId());

        // when
        final int result = deptJobDAO.updateDeptJob(deptJob);

        // then
        log.debug("result={}", result);

        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);
    }

    /**
     * 부서업무 정보를 삭제한다.
     */
    @Test
    public void deleteDeptJob() {
        // given
        final DeptJobBx testDataDeptJobBx = new DeptJobBx();
        testDataDeptJobBx(testDataDeptJobBx);
        final DeptJob testDataDeptJob = new DeptJob();
        testDataDeptJob(testDataDeptJobBx, testDataDeptJob);

        final DeptJob deptJob = new DeptJob();
        deptJob.setDeptJobId(testDataDeptJob.getDeptJobId());

        // when
        final int result = deptJobDAO.deleteDeptJob(deptJob);

        // then
        log.debug("result={}", result);

        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }

}
