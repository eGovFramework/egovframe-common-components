package egovframework.com.cop.sms.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cop.sms.service.EgovSmsInfoService;
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsConnection;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 문자메시지를 위한 서비스 구현 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.18  한성곤          최초 생성
 *
 * </pre>
 */
@Service("EgovSmsInfoService")
public class EgovSmsInfoServiceImpl extends EgovAbstractServiceImpl implements EgovSmsInfoService {
    @Resource(name="SmsDAO")
    private SmsDAO smsDao;

    @Resource(name="egovSmsIdGnrService")
    private EgovIdGnrService idgenService;

    private String smeConfigPath = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovSmsInfoServiceImpl.class);

    @PostConstruct
    public void init() {
	//--------------------------------
	// 속성 정보 얻기
	//--------------------------------
	smeConfigPath = EgovProperties.getPathProperty("Globals.SMEConfigPath");
    }

    private String getPhoneNumber(String number) {
	String result = number;

	if (number == null || number.trim().equals("")) {
	    return "";
	}

	result = result.replace("-", "");
	result = result.replace("(", "");
	result = result.replace(")", "");
	result = result.replace(" ", "");

	return result;
    }

    private String formatPhoneNumber(String number) throws ParseException {
	if (number == null || number.trim().equals("")) {
	    return "";
	}

	StringBuffer buffer = new StringBuffer();


	if (number.length() == 9) {	// 02-500-1234 형식
	    buffer.append(number.substring(0, 2));
	    buffer.append("-");
	    buffer.append(number.substring(2, 2+3));
	    buffer.append("-");
	    buffer.append(number.substring(2+3, 2+3+4));

	} else if (number.length() == 10) {
	    if (number.startsWith("02")) {	// 02-5000-1234 형식
		buffer.append(number.substring(0, 2));
		buffer.append("-");
		buffer.append(number.substring(2, 2 + 4));
		buffer.append("-");
		buffer.append(number.substring(2 + 4, 2 + 4 + 4));

	    } else {				// 031-500-1234 형식
		buffer.append(number.substring(0, 3));
		buffer.append("-");
		buffer.append(number.substring(3, 3 + 3));
		buffer.append("-");
		buffer.append(number.substring(3 + 3, 3 + 3 + 4));
	    }

	} else if (number.length() == 11) {	// 031-5000-1234 형식
	    buffer.append(number.substring(0, 3));
	    buffer.append("-");
	    buffer.append(number.substring(3, 3+4));
	    buffer.append("-");
	    buffer.append(number.substring(3+4,3+4+4));

	} else if (number.length() == 12) {	// 0505-5000-1234 형식
	    buffer.append(number.substring(0, 4));
	    buffer.append("-");
	    buffer.append(number.substring(4, 4+4));
	    buffer.append("-");
	    buffer.append(number.substring(4+4, 4+4+4));

	} else {
	    return number;
	}

	return buffer.toString();
    }

    /**
     * 문자메시지 목록을 조회 한다.
     */
    public Map<String, Object> selectSmsInfs(SmsVO searchVO) throws Exception {
	List<SmsVO> result = smsDao.selectSmsInfs(searchVO);
	int cnt = smsDao.selectSmsInfsCnt(searchVO);

	// 전화번호 포맷 처리
	for (int i = 0; i < result.size(); i++) {
	    String phone = result.get(i).getTrnsmitTelno();
	    result.get(i).setTrnsmitTelno(formatPhoneNumber(phone));
	}

	Map<String, Object> map = new HashMap<String, Object>();

	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 문자메시지를 전송(등록)한다.
     */
    public void insertSmsInf(Sms sms) throws Exception {
	HashMap<String, SmsRecptn> check = new HashMap<String, SmsRecptn>();

	String smsId = idgenService.getNextStringId();

	sms.setSmsId(smsId);

	sms.setTrnsmitTelno(getPhoneNumber(sms.getTrnsmitTelno()));

	//---------------------------------------
	// 마스터 정보 등록
	//---------------------------------------
	smsDao.insertSmsInf(sms);

	//---------------------------------------
	// 전송 요청 및 상세(수신자)정보 등록
	//---------------------------------------
	SmsRecptn smsRecptn = null;
		if (sms != null && sms.getRecptnTelno() != null) {
			for (int i = 0; i < sms.getRecptnTelno().length; i++) {
				if (getPhoneNumber(sms.getRecptnTelno()[i]).equals("")) {
					continue;
				}
				smsRecptn = new SmsRecptn();

				smsRecptn.setSmsId(smsId);
				smsRecptn
						.setRecptnTelno(getPhoneNumber(sms.getRecptnTelno()[i]));

				// 동일 전화번호면 SKIP
				if (check.containsKey(smsRecptn.getRecptnTelno())) {
					continue;
				} else {
					check.put(smsRecptn.getRecptnTelno(), smsRecptn);
				}

				// ---------------------------------------
				// 실 전송 요청 저장
				// ---------------------------------------
				SmsConnection smsConn = new SmsConnection();

				smsConn.setCallFrom(sms.getTrnsmitTelno());
				smsConn.setCallTo(smsRecptn.getRecptnTelno());
				smsConn.setCallBack(smsRecptn.getRecptnTelno());
				smsConn.setCallBackUrl("");
				smsConn.setText(sms.getTrnsmitCn());

				smsConn.setMessageId(smsId + "-" + smsRecptn.getRecptnTelno());

				// SMS 전송 요청
				EgovSmsInfoSender sender = null;
				SmsConnection result = null;
				try {
					sender = new EgovSmsInfoSender(smeConfigPath);

					sender.open();
					result = sender.send(smsConn);
				} finally {
					if (sender != null) {
						sender.close();
					}
				}
				// //-------------------------------------

				// Sender의 전송 결과는 SMS G/W 처리 상의 결과만 리턴함
				// 이동통신사의 오류는 별도의 Receiver에서 수신 처리함
				// 수신 처리시 MessageId의 구성 형식(SMS_ID + "-" + 수신전화번호)를 통해 DB에 결과를 반영

				// 2011.10.21 보안점검 후속조치
				if (result != null) {
					smsRecptn
							.setResultCode(Integer.toString(result.getResult()));
					smsRecptn.setResultMssage(result.getResultMessage());
				}
				smsDao.insertSmsRecptnInf(smsRecptn);
			}
		}
	}

    /**
     * 문자메시지에 대한 상세정보를 조회한다.
     */
    public SmsVO selectSmsInf(SmsVO searchVO) throws Exception {
	SmsVO vo = smsDao.selectSmsInf(searchVO);

	// 전화번호 포맷 처리
	vo.setTrnsmitTelno(formatPhoneNumber(vo.getTrnsmitTelno()));

	SmsRecptn recptn = new SmsRecptn();

	recptn.setSmsId(searchVO.getSmsId());

	List<SmsRecptn> list = smsDao.selectSmsRecptnInfs(recptn);

	// 전화번호 포맷 처리
	for (int i = 0; i < list.size(); i++) {
	    String phone = list.get(i).getRecptnTelno();
	    list.get(i).setRecptnTelno(formatPhoneNumber(phone));
	}

	vo.setRecptn(list);

	return vo;
    }

    /**
     * 문자메시지 실 전송을 요청한다.
     */
    public SmsConnection sendRequsest(SmsConnection smsConn) throws Exception {
	String callTo = smsConn.getCallTo();
	String callFrom = smsConn.getCallFrom();
	String callBack = smsConn.getCallBack();
	String callBackUrl = smsConn.getCallBackUrl();
	String text = smsConn.getText();
	String messageId = smsConn.getMessageId();	// messageId 지정 필요

	LOGGER.info("------------------------");
	LOGGER.info("callTo = {}", callTo);
	LOGGER.info("callFrom = {}", callFrom);
	LOGGER.info("callBack = {}", callBack);
	LOGGER.info("callBackUrl = {}", callBackUrl);
	LOGGER.info("text = {}", text);
	LOGGER.info("messageId = {}", messageId);

	// SMS 전송 요청
	EgovSmsInfoSender sender = null;
	SmsConnection result = null;
	try {
	    sender = new EgovSmsInfoSender(smeConfigPath);

	    sender.open();
	    result = sender.send(smsConn);
	} finally {
	    if (sender != null) {
		sender.close();
	    }
	}

	// Sender의 전송 결과는 SMS G/W 처리 상의 결과만 리턴함
	// 이동통신사의 오류는 별도의 Receiver에서 수신 처리함 (로그 기록)

	if (result != null) {  // 2011.10.21 보안점검 후속조치
		smsConn.setResult(result.getResult());
		smsConn.setResultMessage(result.getResultMessage());
	}
	return smsConn;
    }

    /**
     * 여러 건의 문자메시지 실 전송을 요청한다.
     *
     * @param smsConn
     * @return
     * @throws Exception
     */
    public SmsConnection[] sendRequsest(SmsConnection[] smsConn) throws Exception {
	EgovSmsInfoSender sender = null;

	try {
	    sender = new EgovSmsInfoSender(smeConfigPath);

	    sender.open();

	    // SMS 전송 요청
	    SmsConnection result = null;
	    for (int i = 0; i < smsConn.length; i++) {
		String callTo = smsConn[i].getCallTo();
		String callFrom = smsConn[i].getCallFrom();
		String callBack = smsConn[i].getCallBack();
		String callBackUrl = smsConn[i].getCallBackUrl();
		String text = smsConn[i].getText();
		String messageId = smsConn[i].getMessageId();	// messageId 지정 필요

		LOGGER.info("------------------------");
		LOGGER.info("callTo[{}] = {}", i, callTo);
		LOGGER.info("callFrom[{}] = {}", i, callFrom);
		LOGGER.info("callBack[{}] = {}", i, callBack);
		LOGGER.info("callBackUrl[{}] = {}", i, callBackUrl);
		LOGGER.info("text =[{}] = {}", i, text);
		LOGGER.info("messageId[{}] = {}", i, messageId);

		//smsConn[i] = sendRequsest(smsConn[i]);
		result = sender.send(smsConn[i]);

		// Sender의 전송 결과는 SMS G/W 처리 상의 결과만 리턴함
		// 이동통신사의 오류는 별도의 Receiver에서 수신 처리함 (로그 기록)

		smsConn[i].setResult(result.getResult());
		smsConn[i].setResultMessage(result.getResultMessage());
	    }

	} finally {
	    if (sender != null) {
		sender.close();
	    }
	}

	return smsConn;
    }
}
