package egovframework.com.security.userdetails;

import egovframework.com.security.config.EgovSecurityTestConfig;
import egovframework.com.security.config.EgovSecurityTestDatasource;
import org.egovframe.rte.fdl.security.config.EgovSecurityConfiguration;
import org.egovframe.rte.fdl.security.userdetails.util.CamelCaseUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 원본: org.egovframe.rte.fdl.security.userdetails.CamelCaseUtilTest
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EgovSecurityTestDatasource.class, EgovSecurityConfiguration.class, EgovSecurityTestConfig.class })
public class CamelCaseUtilTest {

    @Test
    public void convert2CamelCase() {
        assertEquals("repoNameEgovframeRuntime", CamelCaseUtil.convert2CamelCase("REPO_NAME_egovframe_Runtime"));
        assertEquals("column1", CamelCaseUtil.convert2CamelCase("column1"));
        assertEquals("column1", CamelCaseUtil.convert2CamelCase("column_1"));
        assertEquals("column1Test", CamelCaseUtil.convert2CamelCase("column_1_test"));
        assertEquals("Column1Test", CamelCaseUtil.convert2CamelCase("_column_1_test_"));
    }

}
