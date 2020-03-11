package egovframework.com.cop.sms.service.impl;

import java.io.IOException;

import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cop.sms.service.SmsRecptn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import x3.client.smeapi.SMEConnection;
import x3.client.smeapi.SMEConnectionFactory;
import x3.client.smeapi.SMEException;
import x3.client.smeapi.SMEListener;
import x3.client.smeapi.SMEMessage;
import x3.client.smeapi.SMEReceiver;
import x3.client.smeapi.SMEReport;
import x3.client.smeapi.SMESession;
import x3.client.smeapi.impl.SMEConfig;
import x3.client.smeapi.impl.SMEConnectionFactoryImpl;
import x3.client.smeapi.impl.SMELogger;

/**
 * 문자메시지 연동 결과 수신 처리를 위한 클래스 (프레임워크 비종속 버전)
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.11.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.11.24  한성곤          최초 생성
 *   2011.10.10	 이기하			 보안점검 후속초치(디버거코드 주석처리)
 *   2017.03.07    조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *
 * </pre>
 */
public class EgovSmsBasicReceiver implements SMEListener {
	private SmsBasicDAO smsDao = new SmsBasicDAO();

	private String smeConfigPath = null;

	/** SMS 서버 URL */
	private String connString = null; // ex) sme://000.000.000.000:20000
	/** SMS 연계 ID */
	private String smsId = null;
	/** SMS 연계 password */
	private String smsPwd = null;

	/** SMS G/W Connection Factory */
	private SMEConnectionFactory factReceiver = null;
	/** SMS G/W Connection */
	private SMEConnection connReceiver = null;
	/** SMS G/W Session */
	private SMESession sessReceiver = null;
	/** SMS G/W Receiver */
	private SMEReceiver receiver = null;

	/** 연결 여부 */
	private boolean isConnected = false;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSmsBasicReceiver.class);

	/**
	 * SMS 결과 수신을 위한 Connection 및 Session 생성한다.
	 *
	 * @throws SMEException
	 */
	public void open() throws SMEException {
		this.factReceiver = new SMEConnectionFactoryImpl(connString);
		this.connReceiver = factReceiver.createConnection(smsId, smsPwd); // 아이디와 패스워드입니다.
		this.sessReceiver = connReceiver.createSession();

		this.receiver = sessReceiver.createReceiver();
		this.receiver.setListener(this);
		this.connReceiver.start();

		isConnected = true;
	}

	/**
	 * SMS 결과 수신을 위한 Connection 및 Session 해제한다.
	 */
	public void close() {
		try {
			if (receiver != null)
				receiver.close();
		} catch (SMEException ignore) {
			LOGGER.debug(ignore.getMessage());
		}

		try {
			if (sessReceiver != null)
				sessReceiver.close();
		} catch (SMEException ignore) {
			LOGGER.debug(ignore.getMessage());
		}

		try {
			if (connReceiver != null)
				connReceiver.close();
		} catch (SMEException ignore) {
			LOGGER.error("Exception: {}", ignore.getClass().getName());
			LOGGER.error("Exception  Message: {}", ignore.getMessage());
		}
	}

	public void readPropertyFile() {

		connString = SMEConfig.getSmsUrl();
		smsId = SMEConfig.getSmsId();
		smsPwd = SMEConfig.getSmsPwd();

		String tmp = null;

		tmp = SMEConfig.getLogLevel();
		if (tmp != null && !tmp.equals("")) {
			SMELogger.setLogLevel(tmp);
		}

		tmp = SMEConfig.getLogLayout();
		if (tmp != null && !tmp.equals("")) {
			SMELogger.setLogLayout(tmp);
		}

		tmp = SMEConfig.getLogPath();
		if (tmp != null && !tmp.equals("")) {
			SMELogger.setLogPath(tmp);
		}

		SMELogger.setUseConsoleLogger(SMEConfig.getUseConsoleLogger());
		SMELogger.setUseFileLogger(SMEConfig.getUseFileLogger());
	}

	/**
	 * 결과에 대한 수신 처리를 한다.
	 */
	public void onMessage(SMEReport msg) {
		if (msg instanceof SMEReport) {
			if (msg.isConnected()) {
				SMEReport rpt = (SMEReport) msg;
				String msgId = rpt.getMessageId();
				int nRes = rpt.getResult(); 						// 결과코드
				String doneTime = rpt.getDeliverTime();	// 이동통신사 결과처리시간-단말기에 전달된 시간(이동통신사 생성)
				String netCode = rpt.getDestination();		// 이동통신사 정보

				//System.out.println("Receiver Number is :" + ((SMEReportImpl)rpt).receiver.activeCount()); // 주석처리

				String resultMsg = "";

				switch (nRes) {
					case 0:
						resultMsg = "";
						break;
					case 4001:
						resultMsg = "잘못된 전화번호; 착신 이통사를 결정할 수 없음";
						break;
					case 4002:
						resultMsg = "MessageID 중복";
						break;
					case 4005:
						resultMsg = "스팸 메시지로 처리 거부됨";
						break;
					case 4006:
						resultMsg = "스팸 콜백번호로 처리 거부됨";
						break;
					case 5000:
						resultMsg = "SMG Server 내부 에러 (인증실패,연결실패)";
						break;
					case 5050:
						resultMsg = "착신 이통사 연동 실패";
						break;
					case 6000:
						resultMsg = "이통사 시스템 장애";
						break;
					case 6001:
						resultMsg = "이통사 메시지 형식 오류";
						break;
					case 6002:
						resultMsg = "이통사 착신번호 인증 에러";
						break;
					case 6003:
						resultMsg = "이통사 스팸 메시지로 처리 거부됨";
						break;
					case 6004:
						resultMsg = "이통사 순간 전송량 제한 초과";
						break;
					case 6005:
						resultMsg = "이통사 월 전송량 제한 초과";
						break;
					case 6006:
						resultMsg = "이통사 Resource 제한에 의한 전송 제어";
						break;
					case 6007:
						resultMsg = "이통사 Resource full";
						break;
					case 6008:
						resultMsg = "이통사 번호이동 시스템 장애";
						break;
					case 6009:
						resultMsg = "이통사 메시지 타입 오류";
						break;
					case 6010:
						resultMsg = "이통사 전송 실패";
						break;
					case 6011:
						resultMsg = "이통사 메시지 전송불가(단말기에서 착신 거부)";
						break;
					case 6012:
						resultMsg = "이통사 전송 실패(무선망단)";
						break;
					case 6013:
						resultMsg = "이통사 전송 실패(무선망 -> 단말기단)";
						break;
					case 6014:
						resultMsg = "이통사 수신 단말기 형식 오류";
						break;
					case 6015:
						resultMsg = "이통사 Unknown Error";
						break;
					case 7000:
						resultMsg = "수신 단말기 전원꺼짐";
						break;
					case 7001:
						resultMsg = "수신 단말기 메시지 버퍼 풀";
						break;
					case 7002:
						resultMsg = "수신 단말기 음영지역";
						break;
					case 7003:
						resultMsg = "수신 단말기 메시지 삭제됨";
						break;
					default:
						resultMsg = "알 수 없는 오류 발생";
				}

				if (nRes != SMEMessage.RESULT_SUCCESS) {
					// System.out.println("SMSMessage (msgId = " + msgId + ") report = " + rpt.getResult());
					LOGGER.info("MessageId   : {}", msgId);
					LOGGER.info("Result      : {}", nRes);
					LOGGER.info("Result Msg. : {}", resultMsg);
					LOGGER.info("Done Time   : {}", doneTime);
					LOGGER.info("Net Code    : {}", netCode);
				} else {
					// System.out.println("SMEMessage (msgId = " + msgId + ") report = " + rpt.getResult());
					LOGGER.info("MessageId   : {}", msgId);
					LOGGER.info("Result      : {}", nRes);
					LOGGER.info("Result Msg. : {}", resultMsg);
					LOGGER.info("Done Time   : {}", doneTime);
					LOGGER.info("Net Code    : {}", netCode);
				}

				// Spring context에서 호출된 경우만 DB를 처리함
				if (smeConfigPath != null) {
					SmsRecptn recptn = new SmsRecptn();

					recptn.setSmsId(msgId.substring(0, 20)); 			// SMS_ID + "-" + 수신전화번호
					recptn.setRecptnTelno(msgId.substring(21)); 	// "-" 제외

					recptn.setResultCode(Integer.toString(nRes));
					recptn.setResultMssage(resultMsg);

					try {
						smsDao.updateSmsRecptnInf(recptn);
					//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
					} catch (IOException ex) {
//						LOGGER.error("Exception: {}", ex.getClass().getName());
//						LOGGER.error("Exception  Message: {}", ex.getMessage());
						LOGGER.error("[IOException] : Connection Close");
					} catch (Exception ex) {
						LOGGER.error("["+ ex.getClass() +"] Connection Close : ", ex.getMessage());
					}
				}
			} else {
				LOGGER.debug("SMEReceiver Disconnected!!");
				isConnected = false;
			}
		}
	}

	/**
	 * 결과 수신을 위한 daemon을 기동한다.
	 *
	 * @param args
	 */
	public static void mainExample(String[] args) {
		if (args.length < 1) {
			LOGGER.error("SMEConfig.conf file full path needed.");
			LOGGER.error("ex) java [JVM Options] [className] /home/egovframe/conf/SMEConfig.conf");
			System.exit(-1);
		}

		EgovSmsBasicReceiver receiver = new EgovSmsBasicReceiver();

		try {
			try {
				SMEConfig.configSet(args[0]);
				receiver.readPropertyFile();
				
			} catch (Exception ex) {
//				LOGGER.error("DEBUG: {}", ex.getMessage());
				//2017.03.07 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				LOGGER.error("["+ ex.getClass() +"] : Connection Close ", ex.getMessage());
				return;
			}

			// 결과 수신을 위해서 리포트 세션을 접속한다.
			// 프로그램 시작시 최초 한번만 해준다.
			receiver.open();

			// 데몬이 종료안되도록 10초씩 쉬면서 루프를 돌렸습니다.
			// 실제 사용 목적에 맞게끔 고쳐주시면 됩니다.
			while (true) {
				// 연결을 유지해야하는데 서버측에서 세션을 끊어버리거나
				// 네트워크 간섭 또는 장애 상황으로 연결이 끊겼을 경우 재접속할 수 있도록 처리
				//if (receiver.isConnected == false) {
				if (!receiver.isConnected) { // recommended by PMD
					receiver.close();
					Thread.sleep(10000);
					receiver.open();
				}

				Thread.sleep(10000);
			}

		} catch (SMEException ex) {
			LOGGER.error("DEBUG: {}", ex.getMessage());
		} catch (InterruptedException ie) {
			EgovBasicLogger.ignore("InterruptedException", ie);
		} finally {
			receiver.close();
		}
	}
}
