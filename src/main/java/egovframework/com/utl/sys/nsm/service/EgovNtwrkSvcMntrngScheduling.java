package egovframework.com.utl.sys.nsm.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * 개요
 * - 네트워크서비스 모니터링을 위한 스케쥴링클래스를 정의한다.
 *
 * 상세내용
 * - 네트워크서비스 모니터링 기능을 제공한다.
 * - 네트워크서비스 모니터링 결과를 관리자에게 이메일로 전송한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:43
 */

@Service("egovNtwrkSvcMntrngScheduling")
public class EgovNtwrkSvcMntrngScheduling extends EgovAbstractServiceImpl {

	@Resource(name="EgovNtwrkSvcMntrngService")
	private EgovNtwrkSvcMntrngService ntwrkSvcMntrngService;

	@Resource(name="mntrngMailSender")
    private MailSender mntrngMailSender;

    @Resource(name="mntrngMessage")
    private SimpleMailMessage mntrngMessage;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovNtwrkSvcMntrngScheduling.class);

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;



	/**
	 * 네트워크 서비스 모니터링를 수행한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void monitorNtwrkSvc() throws Exception {
		// 모니터링 대상 정보 읽어들이기
		Map<String, Object> map = null;
		List<NtwrkSvcMntrng> targetList = null;
		NtwrkSvcMntrngVO searchVO = new NtwrkSvcMntrngVO();
		// 모니터링 대상 검색 조건 초기화
		searchVO.setPageIndex(1);
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(RECORD_COUNT_PER_PAGE);
		map = ntwrkSvcMntrngService.selectNtwrkSvcMntrngList(searchVO);
		targetList = (List<NtwrkSvcMntrng>)map.get("resultList");
		LOGGER.debug("조회조건 {}", searchVO);
		LOGGER.debug("Result 건수 : {}", targetList.size());
		// 서비스체크 함수 호출.
		Iterator<NtwrkSvcMntrng> iter = targetList.iterator();
		NtwrkSvcMntrng target = null;
		NtwrkSvcMntrngResult result = null;
		while(iter.hasNext()) {
			target = iter.next();
			LOGGER.debug("Data : {}", target);
			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			target.setCreatDt(formatter.format(new java.util.Date()));
			result = NtwrkSvcMntrngChecker.check(target.getSysIp(), Integer.valueOf(target.getSysPort()));

			// email 전송.
			if (!result.isNrmltAt()) {
				target.setMntrngSttus("비정상");
				sendEmail(target);
			}

			// DB에 결과값 저장
			if (result.isNrmltAt()) {
				target.setMntrngSttus("01");
			} else {
				target.setMntrngSttus("02");
			}

			// DB에 로그정보 저장
			if(result.getCause() != null){
				target.setLogInfo(result.getCause().getMessage());
			}else{
				target.setLogInfo("");
			}
			target.setLastUpdusrId("SYSTEM");
			ntwrkSvcMntrngService.updateNtwrkSvcMntrngSttus(target);

		}


	}

	/**
	 * 이메일을 전송한다.
	 *
	 * @param   target   모니터링 대상정보
	 * @return
	 *
	 */
    private void sendEmail(NtwrkSvcMntrng target)
    {
    	String subject = null;
    	String text = null;
    	String errorContents = null;

    	SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
        // 수신자
        msg.setTo(target.getMngrEmailAddr());
        // 메일제목
        subject = msg.getSubject();
        subject = EgovStringUtil.replace(subject, "{모니터링종류}", "네트워크서비스모니터링");
        msg.setSubject(subject);
        // 메일내용
        text = msg.getText();
        text = EgovStringUtil.replace(text, "{모니터링종류}", "네트워크서비스모니터링");
        errorContents = "서버명 : ";
        errorContents += target.getSysNm();
        errorContents += "\n";
        errorContents += "서버IP : ";
        errorContents += target.getSysIp();
        errorContents += "\n";
        errorContents += "서버포트 : ";
        errorContents += target.getSysPort();
        errorContents += "\n";
        errorContents += "상태 : ";
        errorContents += target.getMntrngSttus();
        errorContents += "\n";
        errorContents += "모니터링 시각 : ";
        errorContents += EgovDateUtil.convertDate(target.getCreatDt(), "", "", "");
        errorContents += "\n";
        errorContents += target.getSysNm() + " 의 네트워크 서비스 상태가 비정상입니다. \n로그를 확인해주세요.";
        text = EgovStringUtil.replace(text, "{에러내용}", errorContents);
        msg.setText(text);

        this.mntrngMailSender.send(msg);
    }

}
