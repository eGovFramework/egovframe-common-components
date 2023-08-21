package egovframework.com.cop.smt.djm.service.impl;

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
import egovframework.com.cop.smt.djm.service.DeptJobBx;
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
     * 부서업무함 정보를 등록한다.
     */
    @Test
    public void test_a10_insertDeptJobBx() {
        // given
        final DeptJobBx deptJobBx = new DeptJobBx();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        try {
            deptJobBx.setDeptJobBxId(String.valueOf(egovDeptJobBxIdGnrService.getNextLongId()));
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
        int result = deptJobDAO.insertDeptJobBx(deptJobBx);

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.insert"), 1, result);

        if (log.isDebugEnabled()) {
            log.debug("result={}", result);
        }
    }

}
