package egovframework.com.security.userdetails;

import org.egovframe.rte.fdl.security.userdetails.util.CamelCaseUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 원본: org.egovframe.rte.fdl.security.userdetails.CamelCaseUtilTest
 * (4.3.2 : 스프링 컨텍스트 없이 정적 유틸리티만 검증)
 */
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
