package egovframework.com.cop.sms.service.impl;

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
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;
import egovframework.com.test.EgovTestAbstractDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 문자메시지를 위한 데이터 접근 클래스 DAO 단위 테스트
 * 
 * @author 이백행
 * @since 2023-08-10
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, SmsDAOTest.class, })

@Configuration

@ImportResource({

        "classpath*:egovframework/spring/com/idgn/context-idgn-Sms.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.sms.service.impl",

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                SmsDAO.class,

                        }

                )

        }

)

//@Commit

@NoArgsConstructor
@Slf4j
public class SmsDAOTest extends EgovTestAbstractDAO {

    /**
     * 문자메시지를 위한 데이터 접근 클래스
     */
    @Autowired
    private SmsDAO smsDAO;

    /**
     * egovSmsIdGnrService
     */
    @Autowired
    @Qualifier("egovSmsIdGnrService")
    private EgovIdGnrService egovSmsIdGnrService;

    /**
     * testDataSms
     * 
     * @param testDataSms
     */
    private void testDataSms(final Sms testDataSms) {
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            testDataSms.setSmsId(egovSmsIdGnrService.getNextStringId()); // 문자메시지ID
        } catch (FdlException e) {
//            e.printStackTrace();
            log.error("FdlException egovSmsIdGnrService");
//            fail("FdlException egovSmsIdGnrService");
        }

        testDataSms.setTrnsmitTelno("전송전화번호"); // 전송전화번호
        testDataSms.setTrnsmitCn("test 이백행 전송내용 " + LocalDateTime.now()); // 전송내용

        if (loginVO != null) {
            testDataSms.setFrstRegisterId(loginVO.getUniqId());
        }

        smsDAO.insertSmsInf(testDataSms);
    }

    /**
     * testDataSmsRecptn
     * 
     * @param testDataSmsRecptn
     */
    private void testDataSmsRecptn(final SmsRecptn testDataSmsRecptn) {
        final Sms testDataSms = new Sms();
        testDataSms(testDataSms);

        testDataSmsRecptn.setSmsId(testDataSms.getSmsId()); // 문자메시지ID
        testDataSmsRecptn.setRecptnTelno("수신전화번호"); // 수신전화번호
        testDataSmsRecptn.setResultCode("결과코드"); // 결과코드
        testDataSmsRecptn.setResultMssage("test 이백행 결과메시지 " + LocalDateTime.now()); // 결과메시지

        smsDAO.insertSmsRecptnInf(testDataSmsRecptn);
    }

    /**
     * 문자메시지 목록을 조회한다.
     */
    @Test
    public void selectSmsInfs() {
        // given
        final SmsVO testDataSms = new SmsVO();
        testDataSms(testDataSms);

        final SmsVO smsVO = new SmsVO();

        smsVO.setFirstIndex(0);
        smsVO.setRecordCountPerPage(1);

        smsVO.setSearchCnd(null);

//        smsVO.setSearchCnd("0");
//        smsVO.setSearchWrd("");

        smsVO.setSearchCnd("1");
        smsVO.setSearchWrd(testDataSms.getTrnsmitCn());

        // when
        final List<SmsVO> resultList = smsDAO.selectSmsInfs(smsVO);

        // then
        if (log.isDebugEnabled()) {
            for (final SmsVO result : resultList) {
                log.debug("getSmsId={}, {}", testDataSms.getSmsId(), result.getSmsId());
                log.debug("getTrnsmitTelno={}, {}", testDataSms.getTrnsmitTelno(), result.getTrnsmitTelno());
//                log.debug("getTrnsmitCn={}, {}", testDataSms.getTrnsmitCn(), result.getTrnsmitCn());
            }
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getSmsId(),
                resultList.get(0).getSmsId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getTrnsmitTelno(),
                resultList.get(0).getTrnsmitTelno());
//        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getTrnsmitCn(),
//                resultList.get(0).getTrnsmitCn());
    }

    /**
     * 문자메시지 목록 숫자를 조회한다
     */
    @Test
    public void selectSmsInfsCnt() {
        // given
        final SmsVO testDataSms = new SmsVO();
        testDataSms(testDataSms);

        final SmsVO smsVO = new SmsVO();

        smsVO.setSearchCnd(null);

//        smsVO.setSearchCnd("0");
//        smsVO.setSearchWrd("");

        smsVO.setSearchCnd("1");
        smsVO.setSearchWrd(testDataSms.getTrnsmitCn());

        // when
        final int totCnt = smsDAO.selectSmsInfsCnt(smsVO);

        // then
        if (log.isDebugEnabled()) {
            log.debug("totCnt={}", totCnt);
        }

        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), 1, totCnt);
    }

    /**
     * 문자메시지 정보를 등록한다.
     */
    @Test
    public void insertSmsInf() {
        // given
        final Sms sms = new Sms();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            sms.setSmsId(egovSmsIdGnrService.getNextStringId());
        } catch (FdlException e) {
//            e.printStackTrace();
            log.error("FdlException egovSmsIdGnrService");
//            fail("FdlException egovSmsIdGnrService");
        }

        if (loginVO != null) {
            sms.setFrstRegisterId(loginVO.getUniqId());
        }

        sms.setTrnsmitTelno("1566-3598"); // 전송전화번호
        sms.setTrnsmitCn("test 이백행 전송내용 " + LocalDateTime.now()); // 전송내용

        // when
        final int result = smsDAO.insertSmsInf(sms);

        if (log.isDebugEnabled()) {
            log.debug("sms={}", sms);
            log.debug("result={}", result);
        }

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 문자메시지 수신정보 및 결과 정보를 등록한다.
     */
    @Test
    public void insertSmsRecptnInf() {
        // given
        final Sms testDataSms = new Sms();
        testDataSms(testDataSms);

        final SmsRecptn smsRecptn = new SmsRecptn();
        smsRecptn.setSmsId(testDataSms.getSmsId()); // 문자메시지ID
        smsRecptn.setRecptnTelno("수신전화번호"); // 수신전화번호
        smsRecptn.setResultCode("결과코드"); // 결과코드
        smsRecptn.setResultMssage("test 이백행 결과메시지 " + LocalDateTime.now()); // 결과메시지

        // when
        final int result = smsDAO.insertSmsRecptnInf(smsRecptn);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);
    }

    /**
     * 문자메시지에 대한 상세정보를 조회한다.
     */
    @Test
    public void selectSmsInf() {
        // given
        final SmsVO testDataSms = new SmsVO();
        testDataSms(testDataSms);

        final SmsVO smsVO = new SmsVO();
        smsVO.setSmsId(testDataSms.getSmsId());

        // when
        final SmsVO result = smsDAO.selectSmsInf(smsVO);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getSmsId(), result.getSmsId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getTrnsmitTelno(),
                result.getTrnsmitTelno());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getTrnsmitCn(),
                result.getTrnsmitCn());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSms.getFrstRegisterId(),
                result.getFrstRegisterId());

        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
            log.debug("getSmsId={}, {}", testDataSms.getSmsId(), result.getSmsId());
            log.debug("getTrnsmitTelno={}, {}", testDataSms.getTrnsmitTelno(), result.getTrnsmitTelno());
            log.debug("getTrnsmitCn={}, {}", testDataSms.getTrnsmitCn(), result.getTrnsmitCn());
            log.debug("getFrstRegisterId={}, {}", testDataSms.getFrstRegisterId(), result.getFrstRegisterId());
        }
    }

    /**
     * 문자메시지 수신 및 결과 목록을 조회한다.
     */
    @Test
    public void selectSmsRecptnInfs() {
        // given
        final SmsRecptn testDataSmsRecptn = new SmsRecptn();
        testDataSmsRecptn(testDataSmsRecptn);

        final SmsRecptn smsRecptn = new SmsRecptn();
        smsRecptn.setSmsId(testDataSmsRecptn.getSmsId());

        // when
        final List<SmsRecptn> resultList = smsDAO.selectSmsRecptnInfs(smsRecptn);

        // then
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSmsRecptn.getSmsId(),
                resultList.get(0).getSmsId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSmsRecptn.getRecptnTelno(),
                resultList.get(0).getRecptnTelno());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSmsRecptn.getResultCode(),
                resultList.get(0).getResultCode());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), testDataSmsRecptn.getResultMssage(),
                resultList.get(0).getResultMssage());

        if (log.isDebugEnabled()) {
            log.debug("resultList={}", resultList);
            for (SmsRecptn result : resultList) {
                log.debug("getSmsId={}, {}", testDataSmsRecptn.getSmsId(), result.getSmsId());
                log.debug("getRecptnTelno={}, {}", testDataSmsRecptn.getRecptnTelno(), result.getRecptnTelno());
                log.debug("getResultCode={}, {}", testDataSmsRecptn.getResultCode(), result.getResultCode());
                log.debug("getResultMssage={}, {}", testDataSmsRecptn.getResultMssage(), result.getResultMssage());
            }
        }
    }

    /**
     * 문자메시지 전송 결과 수신을 처리한다. EgovSmsInfoReceiver(Schedule job)에 의해 호출된다.
     */
    @Test
    public void updateSmsRecptnInf() {
        // given
        final SmsRecptn testDataSmsRecptn = new SmsRecptn();
        testDataSmsRecptn(testDataSmsRecptn);

        final SmsRecptn smsRecptn = new SmsRecptn();
        smsRecptn.setSmsId(testDataSmsRecptn.getSmsId());
        smsRecptn.setRecptnTelno(testDataSmsRecptn.getRecptnTelno());
        smsRecptn.setResultCode("수정"); // 결과코드
        smsRecptn.setResultMssage("test 이백행 결과메시지 수정 " + LocalDateTime.now()); // 결과메시지

        // when
        final int result = smsDAO.updateSmsRecptnInf(smsRecptn);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.update"), 1, result);

        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }
    }

}
