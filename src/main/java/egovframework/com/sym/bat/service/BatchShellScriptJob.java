package egovframework.com.sym.bat.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileSystemUtils;
import egovframework.com.cmm.service.Globals;

/**
 * 배치쉘스크립트를 실행하는 Quartz Job 클래스를 정의한다.
 *
 * @author 김진만
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2010.08.30   김진만            최초 생성
 *  2020.11.05   신용호            KISA 보안약점 조치 - WhiteList처리
 *  2022.11.11   김혜준            시큐어코딩 처리
 *  
 * </pre>
 */

public class BatchShellScriptJob implements Job {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchShellScriptJob.class);

	@Resource(name = "egovNextUrlWhitelist")
    protected List<String> nextUrlWhitelist;

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {

		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();

		LOGGER.debug("job[{}] Trigger이름 : ", jobContext.getJobDetail().getKey().getName(), jobContext.getTrigger().getKey().getName());
		LOGGER.debug("job[{}] BatchOpert이름 : ", jobContext.getJobDetail().getKey().getName(), dataMap.getString("batchOpertId"));
		LOGGER.debug("job[{}] BatchProgram이름 : ", jobContext.getJobDetail().getKey().getName(), dataMap.getString("batchProgrm"));
		LOGGER.debug("job[{}] Parameter이름 : ", jobContext.getJobDetail().getKey().getName(), dataMap.getString("paramtr"));

		int result = executeProgram(dataMap.getString("batchProgrm"), dataMap.getString("paramtr"));

		// jobContext에 결과값을 저장한다.
		jobContext.setResult(result);
	}

	/**
	 * 시스템에서 특정 쉘프로그램을 실행한다.
	 * @param batchProgrm 배치실행화일
	 * @param paramtr 배치실행화일에 전달될 파라미터
	 * @return 배치실행화일리턴값(integer)
	 * @exception Exception
	*/
	private int executeProgram(String batchProgrm, String paramtr) {

		int result = 0;

		String propertyValue = EgovProperties.getProperty("SHELL." + Globals.OS_TYPE + ".batchShellFiles");

		if (ObjectUtils.isEmpty(propertyValue) || propertyValue.length() == 0) {

			LOGGER.debug("SHELL.Globals.OSTYPE.batchShellFiles OK");
			LOGGER.debug("SHELL.UNIX/WINDOWS.batchShellFiles properties not defined");
			throw new SecurityException("SHELL.UNIX/WINDOWS.batchShellFiles WhiteList Blocked!");

		} else {

			LOGGER.debug("SHELL.UNIX/WINDOWS.batchShellFiles properties = "+propertyValue);
			List<String> cmdShell = Arrays.asList(propertyValue.split(","));
			LOGGER.debug("SHELL.UNIX/WINDOWS.batchShellFiles size() = "+cmdShell.size());
			
			for(String item : cmdShell) {
				boolean whiteListStatus = batchProgrm.contains(item);
				LOGGER.debug("SHELL.UNIX/WINDOWS.batchShellFiles WhiteList item = "+item+", status = "+whiteListStatus);
				if (whiteListStatus) {
					try {
						// 2022.11.11 시큐어코딩 처리
						FileSystemUtils util = new FileSystemUtils();
						Process process = util.processOperate("BatchShellScriptJob", item);
						process.waitFor();
						result = process.exitValue();
						LOGGER.debug("배치실행화일 - {} 실행완료, 결과값: {}", item, result);
					} catch (IOException e) {
						LOGGER.error("배치스크립트 실행 에러 : {}", e.getMessage());
					} catch (InterruptedException e) {
						LOGGER.error("배치스크립트 실행 에러 : {}", e.getMessage());
					}
				}
			}
		}

		return result;
	}

}
