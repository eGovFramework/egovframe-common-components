package egovframework.com.sym.sym.bak.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.utl.sim.service.EgovFileTool;
import lombok.extern.slf4j.Slf4j;

/**
 * 백업작업을 실행하는 Quartz Job 클래스를 정의한다.
 *
 * @author 김진만
 * @since 2010.09.06
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.09.06  김진만          최초 생성
 *   2017.02.08  이정은          시큐어코딩(ES) - 시큐어코딩  부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2022.11.16  신용호          시큐어코딩 조치
 *   2025.07.22  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-SimplifyBooleanExpressions(boolean 사용 시 불필요한 비교 연산을 피하도록 함)
 *   2025.07.22  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *
 *      </pre>
 */
@Slf4j
public class BackupJob implements Job {

	/** logger */
	private static final String SOURCE_BASE_DIRECTORY = EgovProperties.getProperty("Globals.SynchrnServerPath");
	private static final String TARGET_BASE_DIRECTORY = EgovProperties.getProperty("Globals.SynchrnServerPath");

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {

		boolean result = false;
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();

		if (log.isDebugEnabled()) {
			log.debug("job[{}] Trigger이름 : {}", jobContext.getJobDetail().getKey().getName(),
					jobContext.getTrigger().getKey().getName());
			log.debug("job[{}] BackupOpert ID : {}", jobContext.getJobDetail().getKey().getName(),
					dataMap.getString("backupOpertId"));
			log.debug("job[{}] 백업원본디렉토리 : {}", jobContext.getJobDetail().getKey().getName(),
					dataMap.getString("backupOrginlDrctry"));
			log.debug("job[{}] 백업저장디렉토리 : {}", jobContext.getJobDetail().getKey().getName(),
					dataMap.getString("backupStreDrctry"));
			log.debug("job[{}] 압축구분 : {}", jobContext.getJobDetail().getKey().getName(), dataMap.getString("cmprsSe"));
		}

		String backupOpertId = dataMap.getString("backupOpertId");
		String backupOrginlDrctry = dataMap.getString("backupOrginlDrctry");
		String backupStreDrctry = dataMap.getString("backupStreDrctry");
		String cmprsSe = dataMap.getString("cmprsSe");

		String backupFileNm = null;
		if ("01".equals(cmprsSe)) {
			backupFileNm = File.separator + generateBackupFileNm(backupOpertId) + "." + "tar";
		} else if ("02".equals(cmprsSe)) {
			backupFileNm = File.separator + generateBackupFileNm(backupOpertId) + "." + "zip";
		} else {
			String msg = "압축구분값[" + cmprsSe + "]이 잘못지정되었습니다.";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new JobExecutionException(msg);
		}
		if (log.isDebugEnabled()) {
			log.debug("백업화일명 : {}", backupFileNm);
		}
		dataMap.put("backupFile", backupFileNm);

		if ("01".equals(cmprsSe)) {
			result = excuteBackup(backupOrginlDrctry, backupStreDrctry, backupFileNm, ArchiveStreamFactory.TAR);
		} else {
			result = excuteBackup(backupOrginlDrctry, backupStreDrctry, backupFileNm, ArchiveStreamFactory.ZIP);
		}

		// jobContext에 결과값을 저장한다.
		jobContext.setResult(result);
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
			String msg = "백업원본디렉토리[" + srcFile.getAbsolutePath() + "]가 존재하지 않습니다.";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new JobExecutionException(msg);
		}

		// 1. 파일인 경우
		if (srcFile.isFile()) {
			// 에러처리할 것 ...
			String msg = "백업원본디렉토리[" + srcFile.getAbsolutePath() + "]가 파일입니다. 디렉토리명을 지정해야 합니다.";
			if (log.isErrorEnabled()) {
				log.error(msg);
			}
			throw new JobExecutionException(msg);
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
