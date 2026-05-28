package egovframework.com.cmm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EgovWebUtil 단위 테스트
 *
 * @see EgovWebUtil
 */
public class EgovWebUtilTest {

    // clearXSSMinimum

    @Test
    void clearXSSMinimum_null_반환_빈문자열() {
        assertEquals("", EgovWebUtil.clearXSSMinimum(null));
    }

    @Test
    void clearXSSMinimum_빈문자열_반환_빈문자열() {
        assertEquals("", EgovWebUtil.clearXSSMinimum(""));
    }

    @Test
    void clearXSSMinimum_공백만_반환_빈문자열() {
        assertEquals("", EgovWebUtil.clearXSSMinimum("   "));
    }

    @Test
    void clearXSSMinimum_꺽쇠_이스케이프() {
        String result = EgovWebUtil.clearXSSMinimum("<script>alert('xss')</script>");
        assertTrue(result.contains("&lt;"));
        assertTrue(result.contains("&gt;"));
        assertFalse(result.contains("<"));
        assertFalse(result.contains(">"));
    }

    @Test
    void clearXSSMinimum_앰퍼샌드_이스케이프() {
        String result = EgovWebUtil.clearXSSMinimum("a&b");
        assertEquals("&amp;b", result.replaceFirst("a", ""));
        assertTrue(result.contains("&amp;"));
        assertFalse(result.contains("a&b"));
    }

    @Test
    void clearXSSMinimum_따옴표_이스케이프() {
        String result = EgovWebUtil.clearXSSMinimum("\"hello\" 'world'");
        assertTrue(result.contains("&#34;"));
        assertTrue(result.contains("&#39;"));
        assertFalse(result.contains("\""));
        assertFalse(result.contains("'"));
    }

    @Test
    void clearXSSMinimum_URL인코딩_이스케이프() {
        String result = EgovWebUtil.clearXSSMinimum("%2E%2F");
        assertTrue(result.contains("&#46;"));
        assertTrue(result.contains("&#47;"));
    }

    // clearXSS

    @Test
    void clearXSS_null_반환_빈문자열() {
        assertEquals("", EgovWebUtil.clearXSS(null));
    }

    @Test
    void clearXSS_꺽쇠태그_이스케이프() {
        String result = EgovWebUtil.clearXSS("<div>");
        assertEquals("&lt;div&gt;", result);
    }

    @Test
    void clearXSS_퍼센트인코딩_꺽쇠_이스케이프() {
        String result = EgovWebUtil.clearXSS("%3Cscript%3E");
        assertEquals("&lt;script&gt;", result);
    }

    @Test
    void clearXSS_앰퍼샌드_이스케이프() {
        String result = EgovWebUtil.clearXSS("a&b");
        assertTrue(result.contains("&amp;"));
        assertFalse(result.contains("a&b"));
    }

    // filePathBlackList(String)

    @Test
    void filePathBlackList_null_반환_빈문자열() {
        assertEquals("", EgovWebUtil.filePathBlackList(null));
    }

    @Test
    void filePathBlackList_빈문자열_반환_빈문자열() {
        assertEquals("", EgovWebUtil.filePathBlackList(""));
    }

    @Test
    void filePathBlackList_디렉토리순회_제거() {
        // ".." 패턴만 제거하므로 "/upload/../file.txt" -> "/upload//file.txt"
        assertEquals("/upload//file.txt", EgovWebUtil.filePathBlackList("/upload/../file.txt"));
    }

    @Test
    void filePathBlackList_정상경로_그대로() {
        assertEquals("/upload/file.txt", EgovWebUtil.filePathBlackList("/upload/file.txt"));
    }

    // filePathBlackList(String, String)

    @Test
    void filePathBlackList_basePath_null_예외발생() {
        assertThrows(SecurityException.class,
            () -> EgovWebUtil.filePathBlackList("/file.txt", null));
    }

    @Test
    void filePathBlackList_basePath_빈문자열_예외발생() {
        assertThrows(SecurityException.class,
            () -> EgovWebUtil.filePathBlackList("/file.txt", ""));
    }

    @Test
    void filePathBlackList_basePath_루트_예외발생() {
        assertThrows(SecurityException.class,
            () -> EgovWebUtil.filePathBlackList("/file.txt", "/"));
    }

    @Test
    void filePathBlackList_basePath_정상경로_결합() {
        // filePathBlackList(value, basePath) = filePathBlackList(basePath + value)
        String result = EgovWebUtil.filePathBlackList("file.txt", "/upload/");
        assertEquals("/upload/file.txt", result);
    }

    // isIPAddress

    @Test
    void isIPAddress_유효한_IPv4_true() {
        assertTrue(EgovWebUtil.isIPAddress("192.168.1.1"));
        assertTrue(EgovWebUtil.isIPAddress("0.0.0.0"));
        assertTrue(EgovWebUtil.isIPAddress("255.255.255.255"));
    }

    @Test
    void isIPAddress_유효하지_않은_형식_false() {
        assertFalse(EgovWebUtil.isIPAddress("not.an.ip"));
        assertFalse(EgovWebUtil.isIPAddress("192.168.1"));
        assertFalse(EgovWebUtil.isIPAddress("192.168.1.1.1"));
    }

    // removeCRLF

    @Test
    void removeCRLF_CRLF_제거() {
        assertEquals("helloworld", EgovWebUtil.removeCRLF("hello\r\nworld"));
    }

    @Test
    void removeCRLF_CR만_제거() {
        assertEquals("helloworld", EgovWebUtil.removeCRLF("hello\rworld"));
    }

    @Test
    void removeCRLF_LF만_제거() {
        assertEquals("helloworld", EgovWebUtil.removeCRLF("hello\nworld"));
    }

    @Test
    void removeCRLF_줄바꿈없음_그대로() {
        assertEquals("hello world", EgovWebUtil.removeCRLF("hello world"));
    }

    // removeSQLInjectionRisk

    @Test
    void removeSQLInjectionRisk_위험문자_제거() {
        String result = EgovWebUtil.removeSQLInjectionRisk("SELECT * FROM user WHERE id=1; --");
        assertFalse(result.contains("*"));
        assertFalse(result.contains(";"));
        assertFalse(result.contains("-"));
        assertFalse(result.contains(" "));
    }

    @Test
    void removeSQLInjectionRisk_일반문자_유지() {
        String result = EgovWebUtil.removeSQLInjectionRisk("hello");
        assertEquals("hello", result);
    }

    // removeOSCmdRisk

    @Test
    void removeOSCmdRisk_위험문자_제거() {
        String result = EgovWebUtil.removeOSCmdRisk("ls | grep foo; rm -rf /");
        assertFalse(result.contains("|"));
        assertFalse(result.contains(";"));
        assertFalse(result.contains("&"));
        assertFalse(result.contains(" "));
    }

    @Test
    void removeOSCmdRisk_일반문자_유지() {
        String result = EgovWebUtil.removeOSCmdRisk("hello");
        assertEquals("hello", result);
    }

    // filePathReplaceAll

    @Test
    void filePathReplaceAll_null_반환_빈문자열() {
        assertEquals("", EgovWebUtil.filePathReplaceAll(null));
    }

    @Test
    void filePathReplaceAll_경로구분자_및_순회문자_제거() {
        String result = EgovWebUtil.filePathReplaceAll("../etc/passwd");
        assertFalse(result.contains(".."));
        assertFalse(result.contains("/"));
    }

    @Test
    void filePathReplaceAll_앰퍼샌드_제거() {
        String result = EgovWebUtil.filePathReplaceAll("file&name");
        assertFalse(result.contains("&"));
    }
}
