package egovframework.com.sym.bat.service;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 배치쉘스크립트를 실행하는 Quartz Job 클래스를 정의한다.
 *
 * @author 김진만
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.08.30   김진만     최초 생성
 * </pre>
 */

public class BatchShellScriptJob implements Job {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchShellScriptJob.class);

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
		try {
			Process p = null;
			String cmdStr = batchProgrm + " " + paramtr;
			p = Runtime.getRuntime().exec(cmdStr);
			p.waitFor();
			result = p.exitValue();
			LOGGER.debug("배치실행화일 - {} 실행완료, 결과값: {}", cmdStr, result);

		} catch (IOException e) {
			LOGGER.error("배치스크립트 실행 에러 : {}", e.getMessage());
			LOGGER.debug(e.getMessage(), e);
		} catch (InterruptedException e) {
			LOGGER.error("배치스크립트 실행 에러 : {}", e.getMessage());
			LOGGER.debug(e.getMessage(), e);
		}

		return result;
	}

}
