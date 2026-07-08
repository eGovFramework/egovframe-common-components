package egovframework.com.sym.sym.bak.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * {@code BackupJob}의 TAR 엔트리 이름 한글 처리(charset)를 검증한다.
 *
 * <p>이전 구현은 엔트리 이름을 {@code new String(path.getBytes(기본charset), "UTF-8")}로
 * 만들었는데, 기본 charset이 UTF-8이 아닌 환경(예: 한국 Windows의 MS949)에서는 한글 경로가
 * 깨졌다(mojibake). 본 테스트는 그 라운드트립이 실제로 이름을 손상시킴을 보이고, 수정 방식
 * (ArchiveStreamFactory에 UTF-8 인코딩 지정 + 원본 경로 사용)이 한글 엔트리 이름을 보존함을
 * 검증한다.</p>
 */
class BackupJobFileNameCharsetTest {

    @Test
    @DisplayName("기본 charset이 UTF-8이 아니면 기존 라운드트립이 한글 이름을 손상시킨다")
    void legacyRoundTripCorruptsKoreanName() {
        String name = "백업한글파일";

        // 한국 Windows 기본 charset(MS949)을 가정한 기존 코드의 라운드트립.
        String roundTripped = new String(name.getBytes(Charset.forName("MS949")), StandardCharsets.UTF_8);

        assertNotEquals(name, roundTripped, "기본 charset이 UTF-8이 아니면 한글 이름이 손상되어야 한다(버그 재현)");
    }

    @Test
    @DisplayName("UTF-8 인코딩과 원본 경로를 쓰면 한글 TAR 엔트리 이름이 보존된다")
    void utf8TarPreservesKoreanEntryName() throws Exception {
        String name = "백업/한글-파일.txt";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ArchiveOutputStream<TarArchiveEntry> aos = new ArchiveStreamFactory(StandardCharsets.UTF_8.name())
                .createArchiveOutputStream(ArchiveStreamFactory.TAR, bos)) {
            TarArchiveEntry entry = new TarArchiveEntry(name);
            entry.setSize(0);
            aos.putArchiveEntry(entry);
            aos.closeArchiveEntry();
        }

        try (TarArchiveInputStream tis = new TarArchiveInputStream(
                new ByteArrayInputStream(bos.toByteArray()), StandardCharsets.UTF_8.name())) {
            TarArchiveEntry read = tis.getNextEntry();
            assertEquals(name, read.getName(), "UTF-8로 기록한 한글 엔트리 이름이 그대로 복원되어야 한다");
        }
    }
}
