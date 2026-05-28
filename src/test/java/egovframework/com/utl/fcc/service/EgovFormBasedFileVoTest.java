package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * EgovFormBasedFileVo 단위 테스트
 *
 * @author 공통컴포넌트
 * @since 2026.05.28
 */
public class EgovFormBasedFileVoTest {

    @Test
    void 기본생성자_초기값_확인() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        assertNotNull(vo);
        assertEquals("", vo.getFileName());
        assertEquals("", vo.getContentType());
        assertEquals("", vo.getServerSubPath());
        assertEquals("", vo.getPhysicalName());
        assertEquals(0L, vo.getSize());
    }

    @Test
    void setFileName_getFileName() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setFileName("테스트파일.txt");
        assertEquals("테스트파일.txt", vo.getFileName());
    }

    @Test
    void setContentType_getContentType() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setContentType("application/pdf");
        assertEquals("application/pdf", vo.getContentType());
    }

    @Test
    void setServerSubPath_getServerSubPath() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setServerSubPath("20260528");
        assertEquals("20260528", vo.getServerSubPath());
    }

    @Test
    void setPhysicalName_getPhysicalName() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setPhysicalName("ABC123.pdf");
        assertEquals("ABC123.pdf", vo.getPhysicalName());
    }

    @Test
    void setSize_getSize() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setSize(1024L);
        assertEquals(1024L, vo.getSize());
    }

    @Test
    void 모든_필드_설정후_조회() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setFileName("원본명.jpg");
        vo.setContentType("image/jpeg");
        vo.setServerSubPath("20260528");
        vo.setPhysicalName("ABCDEF123456.jpg");
        vo.setSize(2048L);

        assertEquals("원본명.jpg", vo.getFileName());
        assertEquals("image/jpeg", vo.getContentType());
        assertEquals("20260528", vo.getServerSubPath());
        assertEquals("ABCDEF123456.jpg", vo.getPhysicalName());
        assertEquals(2048L, vo.getSize());
    }

    @Test
    void setFileName_빈문자열() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setFileName("");
        assertEquals("", vo.getFileName());
    }

    @Test
    void setSize_영값() {
        EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
        vo.setSize(0L);
        assertEquals(0L, vo.getSize());
    }
}
