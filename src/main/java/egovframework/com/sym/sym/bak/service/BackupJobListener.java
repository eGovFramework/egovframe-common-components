package egovframework.com.sym.sym.bak.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 백업작업의 실행시작, 완료를 저장하는 Quartz JobListener 클래스를 정의한다.
 *
 * @author 김진만
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일              수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2010.09.06   김진만            최초 생성
 *  2017-02-13   이정은            시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2019.12.09   신용호            KISA 보안약점 조치 (부적절한 예외처리)
 * </pre>
 */

public class BackupJobListener implements JobListener {

	/** egovBackupOpertService */
	private EgovBackupOpertService egovBackupOpertService;

	/** ID Generation */
	private EgovIdGnrService idgenService;

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(BackupJobListener.class);

	/**
	 * 백업작업 서비스를 설정한다.
	 *
	 * @param egovBackupOpertService the egovBackupOpertService to set
	 */
	public void setEgovBackupOpertService(EgovBackupOpertService egovBackupOpertService) {
		this.egovBackupOpertService = egovBackupOpertService;
	}

	/**
	 * 백업결과ID 생성서비스
	 * @param idgenService the idgenService to set
	 */
	public void setIdgenService(EgovIdGnrService idgenService) {
		this.idgenService = idgenService;
	}

	/**
	 * Job Listener 이름을 리턴한다.
	 * @see org.quartz.JobListener#getName()
	 */
	public String getName() {
		return this.getClass().getName();
	}

	/**
	 * 백업 작업을 실행하기전에 백업결과 '수행중'상태로 저장한다.
	 *
	 * @param jobContext JobExecutionContext
	 * @see org.quartz.JobListener#jobToBeExecuted(JobExecutionContext jobContext)
	 */
	public void jobToBeExecuted(JobExecutionContext jobContext) {
		LOGGER.debug("job[{}] jobToBeExecuted", jobContext.getJobDetail().getKey().getName());
		BackupResult backupResult = new BackupResult();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			backupResult.setBackupResultId(idgenService.getNextStringId());
			backupResult.setBackupOpertId(dataMap.getString("backupOpertId"));
			backupResult.setBackupFile(dataMap.getString("backupFile"));
			backupResult.setSttus("03"); // 상태는 수행중
			backupResult.setErrorInfo("");

			String executBeginTimeStr = null;
			Date executBeginTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executBeginTimeStr = formatter.format(executBeginTime);
			backupResult.setExecutBeginTime(executBeginTimeStr);

			backupResult.setLastUpdusrId("SYSTEM");
			backupResult.setFrstRegisterId("SYSTEM");
			
			if (backupResult.getBackupOpertId() != null && !backupResult.getBackupOpertId().equals("")) {	// TODO
				egovBackupOpertService.insertBackupResult(backupResult);
			} else {
				LOGGER.debug("Backup Result's Backup Operation ID is null...");
			}

			// 저장이 이상없이 완료되면  datamap에 배치결과ID를 저장한다.
			dataMap.put("backupResultId", backupResult.getBackupResultId());
		} catch (FdlException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			LOGGER.error("(Ko)백업작업ID : {}, idgenService.getNextStringId() 에러 : {}", backupResult.getBackupOpertId(), e.getMessage());
			LOGGER.error("(En)["+ e.getClass() + "] BackupJobID : {}, BatchResult(insert) Error : {}", backupResult.getBackupOpertId(), e.getMessage());
		} catch (Exception e) {
			//2017.02.13 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("(Ko)백업작업ID : {}, 배치결과저장(insert) 에러 : {}", backupResult.getBackupOpertId(), e.getMessage());
			LOGGER.error("(En)["+ e.getClass() + "] BackupJobID : {}, BatchResult(insert) Error : {}", backupResult.getBackupOpertId(), e.getMessage());
		}

	}

	/**
	 * 백업 작업을 완료한후 백업결과 '완료'상태로 저장한다.
	 *
	 * @param jobContext JobExecutionContext
	 * @see org.quartz.JobListener#jobWasExecuted(JobExecutionContext jobContext)
	 */
	public void jobWasExecuted(JobExecutionContext jobContext, JobExecutionException jee) {
		LOGGER.debug("job[{}] jobWasExecuted", jobContext.getJobDetail().getKey().getName());
		LOGGER.debug("job[{}] 수행시간 : {}, {}", jobContext.getJobDetail().getKey().getName(), jobContext.getFireTime(), jobContext.getJobRunTime());

		boolean jobResult = false;
		BackupResult backupResult = new BackupResult();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			backupResult.setBackupResultId(dataMap.getString("backupResultId"));
			backupResult.setBackupOpertId(dataMap.getString("backupOpertId"));
			if (jobContext.getResult() != null) {
				jobResult = (Boolean) jobContext.getResult();
			}
			if (jobResult) {
				// 백업작업 성공.
				backupResult.setSttus("01");
				backupResult.setErrorInfo("");
				backupResult.setBackupFile(dataMap.getString("backupFile"));
			} else {
				// 백업작업이 true가 아닌값을 리턴하면 에러 상황임.
				backupResult.setSttus("02");
				backupResult.setErrorInfo("백업작업이 실패했습니다. \n" + "백업작업 [" + dataMap.getString("backupOpertId") + "]의 로그를 확인하세요");
			}
			// 수행중 exception이 발생한 경우
			if (jee != null) {
				LOGGER.error("JobExecutionException 발생 : {}", jee);
				backupResult.setSttus("02");
				String errorInfo = backupResult.getErrorInfo();
				backupResult.setErrorInfo(errorInfo + "\n" + "JobExecutionException 발생 : " + jee);
			}

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			backupResult.setExecutEndTime(executEndTimeStr);

			backupResult.setLastUpdusrId("SYSTEM");

			LOGGER.debug("insert BackupResult Data : {}", backupResult);
			LOGGER.debug("backupFile : {}", dataMap.getString("backupFile"));
			egovBackupOpertService.updateBackupResult(backupResult);

			// 저장이 이상없이 완료되면  datamap에 배치결과ID를 저장한다.
			dataMap.put("backupResultId", backupResult.getBackupResultId());
		} catch (SQLException e) {
			LOGGER.error("(Ko)백업결과ID : {}, 백업작업ID : {}, 배치결과저장(update) 에러 : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
			LOGGER.error("(En) ["+ e.getClass() + "] BackupResultID : {}, BackupJobID : {}, BatchResult(update) Error : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
		} catch (Exception e) {
			//2017.02.13 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("(Ko)백업결과ID : {}, 백업작업ID : {}, 배치결과저장(update) 에러 : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
			LOGGER.error("(En) ["+ e.getClass() + "] BackupResultID : {}, BackupJobID : {}, BatchResult(update) Error : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
		}
	}

	/**
	 * Batch 작업을 실행한 후에 Batch결과 '에러'상태로 저장한다.
	 *
	 * @param jobContext JobExecutionContext
	 *
	 * @see org.quartz.JobListener#jobExecutionVetoed(JobExecutionContext jobContext)
	 */
	public void jobExecutionVetoed(JobExecutionContext jobContext) {
		LOGGER.debug("job[{}] jobExecutionVetoed", jobContext.getJobDetail().getKey().getName());

		BackupResult backupResult = new BackupResult();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			backupResult.setBackupResultId(dataMap.getString("backupResultId"));
			backupResult.setBackupOpertId(dataMap.getString("backupOpertId"));
			backupResult.setBackupFile(dataMap.getString("backupFile"));
			// 스케줄러가 배치작업을 실행하지 않음.
			backupResult.setSttus("02");
			backupResult.setErrorInfo("스케줄러가 배치작업을 실행하지 않았습니다(jobExecutionVetoed 이벤트). 스케줄러 로그를 확인하세요");

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			backupResult.setExecutEndTime(executEndTimeStr);

			backupResult.setLastUpdusrId("SYSTEM");

			egovBackupOpertService.updateBackupResult(backupResult);

			// 저장이 이상없이 완료되면  datamap에 배치결과ID를 저장한다.
			dataMap.put("backupResultId", backupResult.getBackupResultId());
		} catch (SQLException e) {
			LOGGER.error("(Ko) 백업결과ID : {}, 백업작업ID : {}, 배치결과저장(update) 에러 : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
			LOGGER.error("(En) ["+ e.getClass() + "] BackupResultID : {}, BackupJobID : {}, BatchResult(update) Error : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
		} catch (Exception e) {
			//2017.02.13 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("(Ko) 백업결과ID : {}, 백업작업ID : {}, 배치결과저장(update) 에러 : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
			LOGGER.error("(En) ["+ e.getClass() + "] BackupResultID : {}, BackupJobID : {}, BatchResult(update) Error : {}", backupResult.getBackupResultId(), backupResult.getBackupOpertId(), e.getMessage());
		}

	}

}
