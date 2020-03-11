package egovframework.com.utl.sys.htm.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : HttpMntrngScheduling.java
 * @Description : HTTP서비스모니터링을 위한 스케쥴링 클래스
 * @Modification Information
 *
 *    수정일                수정자          수정내용
 *    ----------   -------   -------------------
 *    2010.09.01   박종선          최초생성
 *    2019.12.06   신용호          KISA 보안약점 조치 (부적절한 예외처리)
 *
 * @author  박종선
 * @since 2010.05.01
 * @version
 * @see
 *
 */

@Service("httpMntrngScheduling")
public class HttpMntrngScheduling extends EgovAbstractServiceImpl {

	@Resource(name = "EgovHttpMonService")
	private EgovHttpMonService egovHttpMonService;

	@Resource(name = "mntrngMailSender")
	private MailSender mntrngMailSender;

	@Resource(name = "mntrngMessage")
	private SimpleMailMessage mntrngMessage;

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpMntrngScheduling.class);

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * HTTP서비스 모니터링를 수행한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void monitorHttp() throws Exception {

		// 모니터링 대상 정보 읽어들이기
		List<HttpMon> targetList = null;
		HttpMonVO searchVO = new HttpMonVO();

		// 모니터링 대상 검색 조건 초기화
		searchVO.setPageIndex(1);
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(RECORD_COUNT_PER_PAGE);
		targetList = egovHttpMonService.selectHttpMonList(searchVO);

		LOGGER.debug("조회조건 {}", searchVO);
		LOGGER.debug("Result 건수 : {}", targetList.size());

		// 서비스체크 함수 호출.
		Iterator<HttpMon> iter = targetList.iterator();
		HttpMon target = null;

		String webKind = null;
		String httpSttusCd = null;
		String sysId = null;
		String siteUrl = null;

		boolean nrmltAt = true;

		while (iter.hasNext()) {

			nrmltAt = true;
			target = (HttpMon) iter.next();
			siteUrl = target.getSiteUrl();
			LOGGER.debug("Data : {}", target);

			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			target.setCreatDt(formatter.format(new java.util.Date()));

			sysId = target.getSysId();
			webKind = target.getWebKind();
			LOGGER.debug("webKind : {}", webKind);
			LOGGER.debug("sysId : {}", sysId);

			try {
				httpSttusCd = HttpMntrngChecker.getPrductStatus(siteUrl);
				target.setHttpSttusCd(httpSttusCd);
			} catch (IOException e1) {
				target.setLogInfo(e1.getMessage());
				nrmltAt = false;
			}

			if (httpSttusCd == "02") {
				nrmltAt = false;
			}

			// email 전송.
			if (!nrmltAt) {
				target.setHttpSttusCd("비정상");
				sendEmail(target);
			}

			// DB에 결과값 저장
			target.setHttpSttusCd(httpSttusCd);
			if (httpSttusCd == "02") {
				target.setLogInfo("Connection timed out: connect");
			}

			target.setLastUpdusrId("SYSTEM");
			egovHttpMonService.updateHttpMonSttus(target);
		}
	}

	/**
	 * 이메일을 전송한다.
	 * @return
	 *
	 * @param target
	 */
	private void sendEmail(HttpMon target) {

		String subject = null;
		String text = null;
		String errorContents = null;

		SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
		// 수신자
		msg.setTo(target.getMngrEmailAddr());
		// 메일제목
		subject = msg.getSubject();
		subject = EgovStringUtil.replace(subject, "{모니터링종류}", "HTTP서비스 모니터링");
		msg.setSubject(subject);
		// 메일내용
		text = msg.getText();
		text = EgovStringUtil.replace(text, "{모니터링종류}", "HTTP서비스 모니터링");
		errorContents = "웹서비스종류 : ";
		errorContents += target.getWebKind();
		errorContents += "\n";
		errorContents += "시스템URL : ";
		errorContents += target.getSiteUrl();
		errorContents += "\n";
		errorContents += "상태 : ";
		errorContents += target.getHttpSttusCd();
		errorContents += "\n";
		errorContents += "모티터링 시각 : ";
		errorContents += EgovDateUtil.convertDate(target.getCreatDt(), "", "", "");
		errorContents += "\n";
		
		if (target.getLogInfo() != null && !target.getLogInfo().equals("")) {
			errorContents += target.getWebKind() + " 의 프로세스 상태가 비정상입니다.  \n로그를 확인해주세요.";
		}
		
		text = EgovStringUtil.replace(text, "{에러내용}", errorContents);
		msg.setText(text);

		this.mntrngMailSender.send(msg);
	}

}