package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * EgovFormBasedFileUtil 단위 테스트 (순수 메서드만 대상)
 *
 * 제외 메서드: saveFile, downloadFile, viewFile (외부 IO 의존)
 *
 * @author 공통컴포넌트
 * @since 2026.05.28
 */
public class EgovFormBasedFileUtilTest {

    @Test
    void getContentTypeWL_null이_아님() {
        Map<String, String> wl = EgovFormBasedFileUtil.getContentTypeWL();
        assertNotNull(wl);
    }

    @Test
    void getContentTypeWL_항목수_4개() {
        Map<String, String> wl = EgovFormBasedFileUtil.getContentTypeWL();
        assertEquals(4, wl.size());
    }

    @Test
    void getContentTypeWL_gif_확인() {
        Map<String, String> wl = EgovFormBasedFileUtil.getContentTypeWL();
        assertEquals("image/gif", wl.get("gif"));
    }

    @Test
    void getContentTypeWL_jpg_확인() {
        Map<String, String> wl = EgovFormBasedFileUtil.getContentTypeWL();
        assertEquals("image/jpg", wl.get("jpg"));
    }

    @Test
    void getContentTypeWL_jpeg_확인() {
        Map<String, String> wl = EgovFormBasedFileUtil.getContentTypeWL();
        assertEquals("image/jpeg", wl.get("jpeg"));
    }

    @Test
    void getContentTypeWL_png_확인() {
        Map<String, String> wl = EgovFormBasedFileUtil.getContentTypeWL();
        assertEquals("image/png", wl.get("png"));
    }

    @Test
    void getPhysicalFileName_null이_아님() {
        String name = EgovFormBasedFileUtil.getPhysicalFileName();
        assertNotNull(name);
    }

    @Test
    void getPhysicalFileName_길이_32() {
        // UUID에서 하이픈 제거 후 대문자 → 32자
        String name = EgovFormBasedFileUtil.getPhysicalFileName();
        assertEquals(32, name.length(), "물리파일명 길이가 32가 아님: " + name);
    }

    @Test
    void getPhysicalFileName_대문자_16진수만_포함() {
        String name = EgovFormBasedFileUtil.getPhysicalFileName();
        assertTrue(name.matches("[0-9A-F]{32}"), "물리파일명 형식이 맞지 않음: " + name);
    }

    @Test
    void getPhysicalFileName_연속_호출시_서로_다름() {
        String name1 = EgovFormBasedFileUtil.getPhysicalFileName();
        String name2 = EgovFormBasedFileUtil.getPhysicalFileName();
        assertTrue(!name1.equals(name2), "연속 호출 시 동일 물리파일명 생성됨");
    }
}
