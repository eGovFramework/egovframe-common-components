package egovframework.com.sym.bat.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 배치작업의 실행시작, 완료를 저장하는 Quartz JobListener 클래스를 정의한다.
 *
 * @author 김진만
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010-08-30   김진만     최초 생성
 *  2017-02-06   이정은     시큐어코딩(ES) - 시큐어코딩  부적절한 예외 처리[CWE-253, CWE-440, CWE-756]
 * </pre>
 */

public class BatchJobListener implements JobListener {

	/** egovBatchSchdulService */
	private EgovBatchSchdulService egovBatchSchdulService;

	/** ID Generation */
	private EgovIdGnrService idgenService;

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJobListener.class);

	/**
	 * 배치스케줄 서비스를 설정한다.
	 *
	 * @param egovBatchSchdulService the egovBatchSchdulService to set
	 */
	public void setEgovBatchSchdulService(EgovBatchSchdulService egovBatchSchdulService) {
		this.egovBatchSchdulService = egovBatchSchdulService;
	}

	/**
	 * 배치결과ID 생성서비스
	 * @param idgenService the idgenService to set
	 */
	public void setIdgenService(EgovIdGnrService idgenService) {
		this.idgenService = idgenService;
	}

	/**
	 * Job Listener 이름을 리턴한다.
	 * @see org.quartz.JobListener#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().getName();
	}

	/**
	 * Batch 작업을 실행하기전에 Batch결과 '수행중'상태로 저장한다.
	 *
	 * @param jobContext JobExecutionContext
	 * @see org.quartz.JobListener#jobToBeExecuted(JobExecutionContext jobContext)
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext jobContext) {
		LOGGER.debug("job[{}] jobToBeExecuted ", jobContext.getJobDetail().getKey().getName());
		BatchResult batchResult = new BatchResult();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			batchResult.setBatchResultId(idgenService.getNextStringId());
			batchResult.setBatchSchdulId(dataMap.getString("batchSchdulId"));
			batchResult.setBatchOpertId(dataMap.getString("batchOpertId"));
			batchResult.setParamtr(dataMap.getString("paramtr"));
			batchResult.setSttus("03"); // 상태는 수행중
			batchResult.setErrorInfo("");

			String executBeginTimeStr = null;
			Date executBeginTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executBeginTimeStr = formatter.format(executBeginTime);
			batchResult.setExecutBeginTime(executBeginTimeStr);

			batchResult.setLastUpdusrId("SYSTEM");
			batchResult.setFrstRegisterId("SYSTEM");

			egovBatchSchdulService.insertBatchResult(batchResult);

			// 저장이 이상없이 완료되면  datamap에 배치결과ID를 저장한다.
			dataMap.put("batchResultId", batchResult.getBatchResultId());
		//2017.02.06 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
		} catch (FdlException e) {
			LOGGER.error("[FdlException] 배치스케줄ID : {}, 배치작업ID : {}, 배치결과저장(insert) 에러 : {}", batchResult.getBatchSchdulId(), batchResult.getBatchOpertId(), e.getMessage());
		} catch (Exception e) {
			LOGGER.error("(Ko) 배치스케줄ID : {}, 배치작업ID : {}, 배치결과저장(insert) 에러 : {}", batchResult.getBatchSchdulId(), batchResult.getBatchOpertId(), e.getMessage());
			LOGGER.error("(En) [" + e.getClass() + "] BatchScheduleID : {}, BatchJobID : {}, BatchResult(insert) Error : {}", batchResult.getBatchSchdulId(), batchResult.getBatchOpertId(), e.getMessage());
		}

	}

	/**
	 * Batch 작업을 완료한후 Batch결과 '완료'상태로 저장한다.
	 *
	 * @param jobContext JobExecutionContext
	 * @see org.quartz.JobListener#jobWasExecuted(JobExecutionContext jobContext)
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext jobContext, JobExecutionException jee) {
		LOGGER.debug("job[{}] jobWasExecuted", jobContext.getJobDetail().getKey().getName());
		LOGGER.debug("job[{}] 수행시간 : {}, {}", jobContext.getJobDetail().getKey().getName(), jobContext.getFireTime(), jobContext.getJobRunTime());

		int jobResult = 99;
		BatchResult batchResult = new BatchResult();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			batchResult.setBatchResultId(dataMap.getString("batchResultId"));
			batchResult.setBatchSchdulId(dataMap.getString("batchSchdulId"));
			batchResult.setBatchOpertId(dataMap.getString("batchOpertId"));
			batchResult.setParamtr(dataMap.getString("paramtr"));
			if (jobContext.getResult() != null) {
				jobResult = (Integer) jobContext.getResult();
			}
			if (jobResult == 0) {
				// 배치작업 성공.
				batchResult.setSttus("01");
				batchResult.setErrorInfo("");
			} else {
				// 배치작업이 0이 아닌값을 리턴하면 에러 상황임.
				batchResult.setSttus("02");
				batchResult.setErrorInfo("배치작업이 결과값 [" + jobResult + "]를 리턴했습니다. \n" + "배치프로그램 [" + dataMap.getString("batchProgrm") + "]의 로그를 확인하세요");
			}
			// 수행중 exception이 발생한 경우
			if (jee != null) {
				LOGGER.error("JobExecutionException 발생 : {}", jee);
				batchResult.setSttus("02");
				String errorInfo = batchResult.getErrorInfo();
				batchResult.setErrorInfo(errorInfo + "\n" + "JobExecutionException 발생 : " + jee);
			}

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			batchResult.setExecutEndTime(executEndTimeStr);

			batchResult.setLastUpdusrId("SYSTEM");

			egovBatchSchdulService.updateBatchResult(batchResult);

			// 저장이 이상없이 완료되면  datamap에 배치결과ID를 저장한다.
			dataMap.put("batchResultId", batchResult.getBatchResultId());
		} catch (ClassCastException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			LOGGER.error("[ClassCastException] 배치결과ID : {}, 배치스케줄ID : {}, 배치작업ID : {}, 배치결과저장(update) 에러 : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
			LOGGER.error("[ClassCastException] ["+ e.getClass() + "] BatchResultID : {}, BatchScheduleID : {}, BatchJobID : {}, BatchResult(update) Error : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
		} catch (Exception e) {
			//2017.02.06 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("(Ko) 배치결과ID : {}, 배치스케줄ID : {}, 배치작업ID : {}, 배치결과저장(update) 에러 : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
			LOGGER.error("(En) ["+ e.getClass() + "] BatchResultID : {}, BatchScheduleID : {}, BatchJobID : {}, BatchResult(update) Error : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
		}
	}

	/**
	 * Batch 작업을 실행한 후에 Batch결과 '에러'상태로 저장한다.
	 *
	 * @param jobContext JobExecutionContext
	 *
	 * @see org.quartz.JobListener#jobExecutionVetoed(JobExecutionContext jobContext)
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext jobContext) {
		LOGGER.debug("job[{}] jobExecutionVetoed", jobContext.getJobDetail().getKey().getName());

		BatchResult batchResult = new BatchResult();
		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();
		try {
			// 결과 값 세팅.
			batchResult.setBatchResultId(dataMap.getString("batchResultId"));
			batchResult.setBatchSchdulId(dataMap.getString("batchSchdulId"));
			batchResult.setBatchOpertId(dataMap.getString("batchOpertId"));
			batchResult.setParamtr(dataMap.getString("paramtr"));
			// 스케줄러가 배치작업을 실행하지 않음.
			batchResult.setSttus("02");
			batchResult.setErrorInfo("스케줄러가 배치작업을 실행하지 않았습니다(jobExecutionVetoed 이벤트). 스케줄러 로그를 확인하세요");

			String executEndTimeStr = null;
			Date executEndTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			executEndTimeStr = formatter.format(executEndTime);
			batchResult.setExecutEndTime(executEndTimeStr);

			batchResult.setLastUpdusrId("SYSTEM");

			egovBatchSchdulService.updateBatchResult(batchResult);

			// 저장이 이상없이 완료되면  datamap에 배치결과ID를 저장한다.
			dataMap.put("batchResultId", batchResult.getBatchResultId());
		} catch (ClassCastException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			LOGGER.error("[ClassCastException] 배치결과ID : {}, 배치스케줄ID : {}, 배치작업ID : {}, 배치결과저장(update) 에러 : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
			LOGGER.error("[ClassCastException] ["+ e.getClass() + "] BatchResultID : {}, BatchScheduleID : {}, BatchJobID : {}, BatchResult(update) Error : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
		} catch (Exception e) {
			//2017.02.06 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			LOGGER.error("(Ko) 배치결과ID : {}, 배치스케줄ID : {}, 배치작업ID : {}, 배치결과저장(update) 에러 : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
			LOGGER.error("(En) ["+ e.getClass() +"] BachResultID : {}, BatchScheduleID : {}, 배치작업ID : {}, 배치결과저장(update) 에러 : {}", batchResult.getBatchResultId(), batchResult.getBatchSchdulId(),
					batchResult.getBatchOpertId(), e.getMessage());
		}

	}

}
