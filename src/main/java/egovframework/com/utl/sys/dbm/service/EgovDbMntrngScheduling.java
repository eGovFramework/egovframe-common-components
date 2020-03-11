package egovframework.com.utl.sys.dbm.service;

import java.util.Iterator;
import java.util.List;

import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovDbMntrngScheduling.java
 * @Description : DB서비스모니터링을 위한 스케쥴링 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2010.06.30     김진만   최초생성
 *
 * @author  김진만
 * @since 2010.06.30
 * @version
 * @see
 *
 */
@Service("egovDbMntrngScheduling")
public class EgovDbMntrngScheduling extends EgovAbstractServiceImpl implements ApplicationContextAware {

	@Resource(name = "egovDbMntrngService")
	private EgovDbMntrngService dbMntrngService;

	@Resource(name = "mntrngMailSender")
	private MailSender mntrngMailSender;

	@Resource(name = "mntrngMessage")
	private SimpleMailMessage mntrngMessage;

	/** ID Generation */
	@Resource(name = "egovDbMntrngLogIdGnrService")
	private EgovIdGnrService idgenService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovDbMntrngScheduling.class);

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;

	}

	/**
	 * DB서비스 모니터링를 수행한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void monitorDb() throws Exception {
		// 모니터링 대상 정보 읽어들이기~~~
		List<DbMntrng> targetList = null;
		DbMntrng searchVO = new DbMntrng();
		// 모니터링 대상 검색 조건 초기화
		searchVO.setPageIndex(1);
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(RECORD_COUNT_PER_PAGE);
		targetList = dbMntrngService.selectDbMntrngList(searchVO);
		LOGGER.debug("조회조건 {}", searchVO);
		LOGGER.debug("Result 건수 : {}", targetList.size());
		// 서비스체크 함수 호출.
		Iterator<DbMntrng> iter = targetList.iterator();
		DbMntrng target = null;
		DbMntrngResult result = null;
		DbMntrngLog dbMntrngLog = null;
		String dmMntrngLogId = null;
		while (iter.hasNext()) {
			target = iter.next();
			LOGGER.debug("Data : {}", target);
			// 서비스 체크 수행.
			result = DbMntrngChecker.check(context, target.getDataSourcNm(), target.getCeckSql());

			// 대상테이블에 DB에 결과값 저장
			if (result.isNrmltAt()) {
				target.setMntrngSttus("01");
			} else {
				target.setMntrngSttus("02");
			}
			target.setLastUpdusrId("SYSTEM");
			dbMntrngService.updateDbMntrng(target);
			// 로그테이블 추가저장.
			dbMntrngLog = new DbMntrngLog();
			dmMntrngLogId = idgenService.getNextStringId();
			dbMntrngLog.setLogId(dmMntrngLogId);
			dbMntrngLog.setDataSourcNm(target.getDataSourcNm());
			dbMntrngLog.setServerNm(target.getServerNm());
			dbMntrngLog.setDbmsKind(target.getDbmsKind());
			dbMntrngLog.setCeckSql(target.getCeckSql());
			dbMntrngLog.setMngrNm(target.getMngrNm());
			dbMntrngLog.setMngrEmailAddr(target.getMngrEmailAddr());
			dbMntrngLog.setMntrngSttus(target.getMntrngSttus());
			dbMntrngLog.setFrstRegisterId("SYSTEM");
			dbMntrngLog.setLastUpdusrId("SYSTEM");
			if (result.getCause() != null) {
				LOGGER.debug("에러메시지: {}", result.getCause().getMessage());

				if (result.getCause().getMessage() != null) {
					dbMntrngLog.setLogInfo(result.getCause().getMessage());
				} else {
					dbMntrngLog.setLogInfo("");
				}

			} else {
				dbMntrngLog.setLogInfo("");
			}
			dbMntrngService.insertDbMntrngLog(dbMntrngLog);

			// 모니터링시각을 가져오기위해 로그정보를 가져온다.
			dbMntrngLog = dbMntrngService.selectDbMntrngLog(dbMntrngLog);
			LOGGER.debug("DB서비스로그 Data : {}", dbMntrngLog);
			// email 전송.
			if (!result.isNrmltAt()) {
				sendEmail(dbMntrngLog);
			}
		}

	}

	/**
	 * 이메일을 전송한다.
	 *
	 * @param   mntrngLog   모니터링 대상정보
	 * @return
	 *
	 */
	private void sendEmail(DbMntrngLog mntrngLog) {
		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(mntrngLog.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = EgovStringUtil.replace(subject, "{모니터링종류}", "DB서비스모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = EgovStringUtil.replace(text, "{모니터링종류}", "DB서비스모니터링");
		errorContents = "데이타소스명 : " + mntrngLog.getDataSourcNm() + "\n";
		errorContents = errorContents + "서버명  : " + mntrngLog.getServerNm() + "\n";
		errorContents = errorContents + "DBMS종류 : " + mntrngLog.getDbmsKindNm() + "\n";
		errorContents = errorContents + "체크SQL : " + mntrngLog.getCeckSql() + "\n";
		errorContents = errorContents + "상태 : " + mntrngLog.getMntrngSttusNm() + "\n";
		errorContents = errorContents + "모니터링시각 : " + mntrngLog.getCreatDt() + "\n";
		errorContents = errorContents + "에러메시지 : " + mntrngLog.getLogInfo() + "\n";
		text = EgovStringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

		this.mntrngMailSender.send(msg);
	}

}
