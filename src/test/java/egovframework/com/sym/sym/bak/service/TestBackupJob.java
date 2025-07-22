package egovframework.com.sym.sym.bak.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.quartz.JobExecutionException;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.sim.service.EgovFileTool;
import lombok.extern.slf4j.Slf4j;

/**
 * 백업작업을 실행하는 Quartz Job 클래스를 정의한다. 단위 테스트 Test
 * 
 * @author 공통컴포넌트 컨트리뷰션팀 이백행
 * @since 2025.07.22
 * @version 4.3.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.22  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j
public class TestBackupJob {

	/** logger */
	private static final String SOURCE_BASE_DIRECTORY = EgovProperties.getProperty("Globals.SynchrnServerPath");
	private static final String TARGET_BASE_DIRECTORY = EgovProperties.getProperty("Globals.SynchrnServerPath");

	@Test
	public void test1tar() throws JobExecutionException {
		execute("script", "01");
	}

	@Test
	public void test2zip() throws JobExecutionException {
		execute("src/main/java/egovframework/com/cmm", "02");
//		execute("src", "02"); // 매우 올래 걸림
	}

	public void execute(String pathname, String cmprsSe) throws JobExecutionException {
//		String pathname = "src";
		File srcDir = new File(pathname);
		try {
			FileUtils.copyDirectory(srcDir, new File(SOURCE_BASE_DIRECTORY + "/" + pathname));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		String backupOpertId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSnnnnnnnnn"));
		String backupOrginlDrctry = pathname; // 백업원본디렉토리
		String backupStreDrctry = pathname.replaceAll("/", "-") + "_backup_"; // 백업저장디렉토리
//		String cmprsSe = "02"; // 압축구분: 01 tar, 02 zip
//		String cmprsSe = ""; // 압축구분: 01 tar, 02 zip

		String backupFileNm = null;
		if ("01".equals(cmprsSe)) {
			backupFileNm = File.separator + generateBackupFileNm(backupOpertId) + "." + "tar";
		} else if ("02".equals(cmprsSe)) {
			backupFileNm = File.separator + generateBackupFileNm(backupOpertId) + "." + "zip";
		} else {
			if (log.isErrorEnabled()) {
				log.error("압축구분값[{}]이 잘못지정되었습니다.", cmprsSe);
			}
			throw new JobExecutionException("압축구분값[" + cmprsSe + "]이 잘못지정되었습니다.");
		}
		if (log.isDebugEnabled()) {
			log.debug("백업화일명 : {}", backupFileNm);
		}

		boolean result = excuteBackup(backupOrginlDrctry, backupStreDrctry, backupFileNm, ArchiveStreamFactory.TAR);

		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
		}
	}

	/**
	 * 백업화일명을 생성한다. 백업화일명 : 백업작업ID_현재시각()
	 * 
	 * @param backupOpertId 백업작업ID
	 * @return 백업화일명.
	 */
	private String generateBackupFileNm(String backupOpertId) {
		String backupFileNm = null;
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		backupFileNm = backupOpertId + "_" + formatter.format(currentTime);

		return backupFileNm;
	}

	/**
	 * 디렉토리를 백업화일(tar,zip)으로 백업하는 기능
	 * 
	 * @param backupOrginlDrctry 백업원본디렉토리명
	 * @param targetFileNm       백업파일명
	 * @param archiveFormat      저장포맷 (tar, zip)
	 * @return result 백업성공여부 True / False
	 */
	@SuppressWarnings("unchecked")
	public boolean excuteBackup(String backupOrginlDrctry, String backupStreDrctry, String targetFileNm,
			String archiveFormat) throws JobExecutionException {

		File srcFile = new File(SOURCE_BASE_DIRECTORY + EgovWebUtil.filePathBlackList(backupOrginlDrctry));
		// 화일명 생성.
		File targetFile = new File(TARGET_BASE_DIRECTORY + EgovWebUtil.filePathBlackList(backupStreDrctry)
				+ FilenameUtils.getName(targetFileNm));

		if (!srcFile.exists()) {
			if (log.isErrorEnabled()) {
				log.error("백업원본디렉토리[{}]가 존재하지 않습니다.", srcFile.getAbsolutePath());
			}
			throw new JobExecutionException("백업원본디렉토리[" + srcFile.getAbsolutePath() + "]가 존재하지 않습니다.");
		}

		// 1. 파일인 경우
		if (srcFile.isFile()) {
			// 에러처리할 것 ...
			if (log.isErrorEnabled()) {
				log.error("백업원본디렉토리[{}]가 파일입니다. 디렉토리명을 지정해야 합니다.", srcFile.getAbsolutePath());
			}
			throw new JobExecutionException("백업원본디렉토리[" + srcFile.getAbsolutePath() + "]가 파일입니다. 디렉토리명을 지정해야 합니다. ");
		}

		// 압축성공여부
		boolean result = false;

		@SuppressWarnings("rawtypes")
		ArchiveOutputStream aosOutput = null;
		ArchiveEntry entry = null;

		// 2. 디렉토리인 경우 만 처리한다.
		try (FileOutputStream fosOutput = new FileOutputStream(targetFile);) {
			if (log.isDebugEnabled()) {
				log.debug("charter set : {}", Charset.defaultCharset().name());
			}
			aosOutput = new ArchiveStreamFactory().createArchiveOutputStream(archiveFormat, fosOutput);

			// Zip에서는 처리안해도 한글안깨져서 주석처리함.
			// if (ArchiveStreamFactory.ZIP.equals(archiveFormat)) {
			// // 파일이름 한글처리 ~~~ ,
			// ((ZipArchiveOutputStream)
			// aosOutput).setEncoding(Charset.defaultCharset().name());
			// }

			if (ArchiveStreamFactory.TAR.equals(archiveFormat)) {
				((TarArchiveOutputStream) aosOutput).setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			}
			File[] fileArr = srcFile.listFiles();
			// KISA 보안약점 조치 (2018-10-29, 윤창원)
			if (fileArr == null) {
				fileArr = new File[0];
			}

			List<String> list = EgovFileTool.getSubFilesByAll(fileArr);

			for (int i = 0; i < list.size(); i++) {
				File sfile = new File(list.get(i));
				try (FileInputStream finput = new FileInputStream(sfile);) {

					if (ArchiveStreamFactory.TAR.equals(archiveFormat)) {
						// 파일이름 한글처리 ~~~
						entry = new TarArchiveEntry(sfile,
								new String(sfile.getAbsolutePath().getBytes(Charset.defaultCharset().name()), "UTF-8"));
						((TarArchiveEntry) entry).setSize(sfile.length());
					} else {
						entry = new ZipArchiveEntry(sfile.getAbsolutePath());
						((ZipArchiveEntry) entry).setSize(sfile.length());
					}
					aosOutput.putArchiveEntry(entry);
					IOUtils.copy(finput, aosOutput);
					aosOutput.closeArchiveEntry();
					finput.close();
					result = true;
				}
			}
			aosOutput.close();
		} catch (FileNotFoundException e) {// KISA 보안약점 조치 (2018-10-29, 윤창원)
			String msg = "파일이 존재하지 않습니다.";
			if (log.isErrorEnabled()) {
				log.error(msg, e);
			}
			throw new JobExecutionException(msg, e);
		} catch (Exception e) {
			// LOGGER.error("백업화일생성중 에러가 발생했습니다. 에러 : {}", e.getMessage());
			// LOGGER.debug(e.getMessage());

			String msg = "백업화일생성중 에러가 발생했습니다.";
			if (log.isErrorEnabled()) {
				log.error(msg, e);
			}
			// result = false;
			throw new JobExecutionException(msg, e);
		} finally {
			EgovResourceCloseHelper.close(aosOutput);

			if (!result) {
				// 2017.02.08 이정은 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				if (targetFile.delete()) {
					if (log.isDebugEnabled()) {
						log.debug("[file.delete] targetFile : File Deletion Success");
					}
				} else {
					if (log.isErrorEnabled()) {
						log.error("[file.delete] targetFile : File Deletion Fail");
					}
				}
			}
		}

		return result;
	}

}
