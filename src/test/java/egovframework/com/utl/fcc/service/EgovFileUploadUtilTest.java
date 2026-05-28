package egovframework.com.utl.fcc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EgovFileUploadUtil 순수 로직 단위 테스트
 * (getFileExtension, checkFileExtension)
 *
 * @see EgovFileUploadUtil
 */
public class EgovFileUploadUtilTest {

    // getFileExtension

    @Test
    void getFileExtension_null_반환_빈문자열() {
        assertEquals("", EgovFileUploadUtil.getFileExtension(null));
    }

    @Test
    void getFileExtension_일반확장자_추출() {
        assertEquals("pdf", EgovFileUploadUtil.getFileExtension("document.pdf"));
    }

    @Test
    void getFileExtension_대문자확장자_추출() {
        assertEquals("PNG", EgovFileUploadUtil.getFileExtension("image.PNG"));
    }

    @Test
    void getFileExtension_경로포함_확장자_추출() {
        assertEquals("txt", EgovFileUploadUtil.getFileExtension("/upload/2024/01/readme.txt"));
    }

    @Test
    void getFileExtension_점이_여러개_마지막_확장자() {
        assertEquals("tar", EgovFileUploadUtil.getFileExtension("archive.tar.gz.tar"));
    }

    // checkFileExtension

    @Test
    void checkFileExtension_허용확장자_true() {
        assertTrue(EgovFileUploadUtil.checkFileExtension("report.pdf", ".pdf.hwp.doc"));
    }

    @Test
    void checkFileExtension_미허용확장자_false() {
        assertFalse(EgovFileUploadUtil.checkFileExtension("script.exe", ".pdf.hwp.doc"));
    }

    @Test
    void checkFileExtension_null_파일명_false() {
        assertFalse(EgovFileUploadUtil.checkFileExtension(null, ".pdf.hwp"));
    }

    @Test
    void checkFileExtension_확장자없는_파일명_false() {
        assertFalse(EgovFileUploadUtil.checkFileExtension("noextension", ".pdf.hwp"));
    }

    @Test
    void checkFileExtension_null_화이트리스트_false() {
        assertFalse(EgovFileUploadUtil.checkFileExtension("file.pdf", null));
    }

    @Test
    void checkFileExtension_빈_화이트리스트_false() {
        assertFalse(EgovFileUploadUtil.checkFileExtension("file.pdf", ""));
    }

    @Test
    void checkFileExtension_대소문자_구분_false() {
        // getFileExtension은 대소문자 그대로 반환; 화이트리스트에 없으면 false
        assertFalse(EgovFileUploadUtil.checkFileExtension("file.PDF", ".pdf"));
    }

    @Test
    void checkFileExtension_점으로_시작하는_화이트리스트_매칭() {
        assertTrue(EgovFileUploadUtil.checkFileExtension("photo.jpg", ".jpg.png.gif"));
    }
}
