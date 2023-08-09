package egovframework.com.cop.sms.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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

}
