package egovframework.com.utl.sys.fsm.service;

import java.io.IOException;
import java.util.ArrayList;
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
 * 
 * 2017.03.03 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 */

@Service("egovFileSysMntrngScheduling")
public class EgovFileSystemMntrngScheduling extends EgovAbstractServiceImpl {

	@Resource(name="EgovFileSysMntrngService")
	private EgovFileSysMntrngService ntwrkSvcMntrngService;

	@Resource(name="mntrngMailSender")
    private MailSender mntrngMailSender;

    @Resource(name="mntrngMessage")
    private SimpleMailMessage mntrngMessage;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileSystemMntrngScheduling.class);

	// 모니터링 대상을 읽기위한 페이지 크기
	private static final int RECORD_COUNT_PER_PAGE = 10000;

	/**
	 * DB서비스 모니터링를 수행한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void monitorFileSys() throws Exception {
		// 모니터링 대상 정보 읽어들이기
		Map<String, Object> map = null;
		//2017.03.03 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
		List<FileSysMntrng> targetList = new ArrayList<FileSysMntrng>();
		FileSysMntrngVO searchVO = new FileSysMntrngVO();
		// 모니터링 대상 검색 조건 초기화
		searchVO.setPageIndex(1);
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(RECORD_COUNT_PER_PAGE);
		map = ntwrkSvcMntrngService.selectFileSysMntrngList(searchVO);
		//2017.03.03 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
		if(map != null){
			targetList = (List<FileSysMntrng>)map.get("resultList");
		}
		
		LOGGER.debug("조회조건 {}", searchVO);
		LOGGER.debug("Result 건수 : {}", targetList.size());
		// 서비스체크 함수 호출.
		Iterator<FileSysMntrng> iter = targetList.iterator();
		FileSysMntrng target = null;

		String fileSysNm = "";
		int fileSysMg = 0;
		int fileSysThrhld = 0;
		int fileSysUsgQty = 0;
		boolean nrmltAt = true;
		while(iter.hasNext()) {
			nrmltAt = true;
			target = iter.next();
			LOGGER.debug("Data : {}", target);
			// 서비스 체크 수행.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
			target.setCreatDt(formatter.format(new java.util.Date()));

			fileSysNm = target.getFileSysNm();
			fileSysThrhld = target.getFileSysThrhld();
			try{
				fileSysMg = FileSystemChecker.totalSpaceGb(fileSysNm);
				fileSysUsgQty = fileSysMg - FileSystemChecker.freeSpaceGb(fileSysNm);

				target.setFileSysMg(fileSysMg);
				target.setFileSysUsgQty(fileSysUsgQty);
			}catch (IOException e1) {
				target.setLogInfo(e1.getMessage());
				nrmltAt = false;
			}catch (Exception e2) {
				target.setLogInfo(e2.getMessage());
				nrmltAt = false;
			}

			if(fileSysUsgQty > fileSysThrhld){
				nrmltAt = false;
			}

			// email 전송.
			if (!nrmltAt) {
				target.setMntrngSttus("비정상");
				sendEmail(target);
			}

			// DB에 결과값 저장
			if (nrmltAt) {
				target.setMntrngSttus("01");
			} else {
				target.setMntrngSttus("02");
			}

			target.setLastUpdusrId("SYSTEM");
			ntwrkSvcMntrngService.updateFileSysMntrngSttus(target);
		}
	}

	/**
	 * 이메일을 전송한다.
	 *
	 * @param   target   모니터링 대상정보
	 * @return
	 *
	 */
    private void sendEmail(FileSysMntrng target)
    {
    	String subject = null;
    	String text = null;
    	String errorContents = null;

    	SimpleMailMessage msg = new SimpleMailMessage(this.mntrngMessage);
        // 수신자
        msg.setTo(target.getMngrEmailAddr());
        // 메일제목
        subject = msg.getSubject();
        subject = EgovStringUtil.replace(subject, "{모니터링종류}", "파일시스템모니터링");
        msg.setSubject(subject);
        // 메일내용
        text = msg.getText();
        text = EgovStringUtil.replace(text, "{모니터링종류}", "파일시스템모니터링");
        errorContents = "파일시스템명 : ";
        errorContents += target.getFileSysNm();
        errorContents += "\n";
        errorContents += "파일시스템관리명 : ";
        errorContents += target.getFileSysManageNm();
        errorContents += "\n";

        if(target.getLogInfo() != null && !target.getLogInfo().equals("")){
        	errorContents += "해당파일의 파일시스템 정보를 가져오는중 에러가 발생하였습니다.";
        }else{
	        errorContents += "크기 : ";
	        errorContents += target.getFileSysMg();
	        errorContents += "GB\n";
	        errorContents += "임계치 : ";
	        errorContents += target.getFileSysThrhld();
	        errorContents += "GB\n";
	        errorContents += "사용량 : ";
	        errorContents += target.getFileSysUsgQty();
	        errorContents += "GB\n";
        }

        errorContents += "상태 : ";
        errorContents += target.getMntrngSttus();
        errorContents += "\n";
        errorContents += "모니터링 시각 : ";
        errorContents += EgovDateUtil.convertDate(target.getCreatDt(), "", "", "");
        errorContents += "\n";
        if(target.getLogInfo() != null && !target.getLogInfo().equals("")){
        	errorContents += target.getFileSysManageNm() + " 의 파일시스템 상태가 비정상입니다.  \n로그를 확인해주세요.";
        }else{
        	errorContents += target.getFileSysManageNm() + " 의 파일시스템이 임계치를 넘었습니다.";
        }
        text = EgovStringUtil.replace(text, "{에러내용}", errorContents);
        msg.setText(text);

        this.mntrngMailSender.send(msg);
    }

}
