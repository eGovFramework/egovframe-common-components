package egovframework.com.utl.sys.trm.service;

import java.util.Iterator;
import java.util.List;

import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovTrsmrcvMntrngScheduling.java
 * @Description : 송수신모니터링을 위한 스케쥴링 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2010.08.16     김진만   최초생성
 *
 * @author  김진만
 * @version
 * @see
 *
 */
@Service("egovTrsmrcvMntrngScheduling")
public class EgovTrsmrcvMntrngScheduling extends EgovAbstractServiceImpl {

	@Resource(name = "egovTrsmrcvMntrngService")
	private EgovTrsmrcvMntrngService trsmrcvMntrngService;

	@Resource(name = "mntrngMailSender")
	private MailSender mntrngMailSender;

	@Resource(name = "mntrngMessage")
	private SimpleMailMessage mntrngMessage;

	/** ID Generation */
	@Resource(name = "egovTrsmrcvMntrngLogIdGnrService")
	private EgovIdGnrService idgenService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovTrsmrcvMntrngScheduling.class);

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * 송수신 모니터링를 수행한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void monitor() throws Exception {
		// 모니터링 대상 정보 읽어들이기~~~
		List<TrsmrcvMntrng> targetList = null;
		TrsmrcvMntrng searchVO = new TrsmrcvMntrng();
		// 모니터링 대상 검색 조건 초기화
		searchVO.setPageIndex(1);
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(RECORD_COUNT_PER_PAGE);
		targetList = trsmrcvMntrngService.selectTrsmrcvMntrngList(searchVO);
		LOGGER.debug("조회조건 {}", searchVO);
		LOGGER.debug("Result 건수 : {}", targetList.size());
		// 서비스체크 함수 호출.
		Iterator<TrsmrcvMntrng> iter = targetList.iterator();
		TrsmrcvMntrng target = null;
		TrsmrcvMntrngResult result = null;
		TrsmrcvMntrngLog trsmrcvMntrngLog = null;
		String trsmrcvMntrngLogId = null;
		Class<?> klass = null;
		TrsmrcvMntrngChecker checker = null;
		while (iter.hasNext()) {
			target = (TrsmrcvMntrng) iter.next();
			LOGGER.debug("Data : {}", target);

			try {
				// Checker 클래스 생성.
				klass = Class.forName(target.getTestClassNm());
				checker = (TrsmrcvMntrngChecker) klass.newInstance();
				LOGGER.debug("Just made: {}", checker);
				// 서비스 체크 수행.
				result = checker.check(target.getCntcId());
				LOGGER.debug("Result Data: {}", checker);
			} catch (ClassNotFoundException e) {
				LOGGER.error("송수신모니터링 Checker클래스 생성에러", e);
				result = new TrsmrcvMntrngResult(false, e);
			} catch (IllegalAccessException e) {
				LOGGER.error("송수신모니터링 Checker클래스 생성에러", e);
				result = new TrsmrcvMntrngResult(false, e);
			} catch (InstantiationException e) {
				LOGGER.error("송수신모니터링 Checker클래스 생성에러", e);
				result = new TrsmrcvMntrngResult(false, e);
			}

			// 대상테이블에 DB에 결과값 저장

			if (result != null && result.isNrmltAt()) {
				target.setMntrngSttus("01");
			} else {
				target.setMntrngSttus("02");
			}
			target.setLastUpdusrId("SYSTEM");
			trsmrcvMntrngService.updateTrsmrcvMntrng(target);
			// 로그테이블 추가저장.
			trsmrcvMntrngLog = new TrsmrcvMntrngLog();
			trsmrcvMntrngLogId = idgenService.getNextStringId();
			trsmrcvMntrngLog.setLogId(trsmrcvMntrngLogId);
			trsmrcvMntrngLog.setCntcId(target.getCntcId());
			trsmrcvMntrngLog.setTestClassNm(target.getTestClassNm());
			trsmrcvMntrngLog.setMngrNm(target.getMngrNm());
			trsmrcvMntrngLog.setMngrEmailAddr(target.getMngrEmailAddr());
			trsmrcvMntrngLog.setMntrngSttus(target.getMntrngSttus());
			trsmrcvMntrngLog.setFrstRegisterId("SYSTEM");
			trsmrcvMntrngLog.setLastUpdusrId("SYSTEM");
			
			if (result != null && result.getCause() != null) {
				LOGGER.debug("에러메시지: {}", result.getCause().getMessage());

				if (result.getCause().getMessage() != null) {
					trsmrcvMntrngLog.setLogInfo(result.getCause().getClass().getName() + " - " + result.getCause().getMessage());
				} else {
					trsmrcvMntrngLog.setLogInfo("");
				}

			} else {
				trsmrcvMntrngLog.setLogInfo("");
			}
			LOGGER.debug("insert할 송수신모니터링로그 Data : {}", trsmrcvMntrngLog);
			trsmrcvMntrngService.insertTrsmrcvMntrngLog(trsmrcvMntrngLog);

			// 모니터링시각을 가져오기위해 로그정보를 가져온다.
			trsmrcvMntrngLog = trsmrcvMntrngService.selectTrsmrcvMntrngLog(trsmrcvMntrngLog);
			LOGGER.debug(" email전송할 송수신모니터링로그 Data : {}", trsmrcvMntrngLog);

			// email 전송.
			if (!result.isNrmltAt()) {
				sendEmail(trsmrcvMntrngLog);
			}

		} /* end of while */

	}

	/**
	 * 이메일을 전송한다.
	 *
	 * @param   mntrngLog   모니터링 대상정보
	 * @return
	 *
	 */
	private void sendEmail(TrsmrcvMntrngLog mntrngLog) {
		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(mntrngLog.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = EgovStringUtil.replace(subject, "{모니터링종류}", "송수신모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = EgovStringUtil.replace(text, "{모니터링종류}", "송수신모니터링");
		errorContents = "연계ID : " + mntrngLog.getCntcId() + "\n";
		errorContents = errorContents + "연계명 : " + mntrngLog.getCntcNm() + "\n";
		errorContents = errorContents + "테스트클래스명 : " + mntrngLog.getTestClassNm() + "\n";
		errorContents = errorContents + "상태 : " + mntrngLog.getMntrngSttusNm() + "\n";
		errorContents = errorContents + "모니터링시각 : " + mntrngLog.getCreatDt() + "\n";
		errorContents = errorContents + "에러메시지 : " + mntrngLog.getLogInfo() + "\n";
		text = EgovStringUtil.replace(text, "{에러내용}", errorContents);
		
		msg.setText(text);

		this.mntrngMailSender.send(msg);
	}

}
