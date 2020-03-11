package egovframework.com.sec.rnc.service.impl;

import egovframework.com.sec.rnc.service.EgovRlnmManageService;
import egovframework.com.sec.rnc.service.EgovSocketClient;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import org.springframework.stereotype.Service;

/**
 * 실명인증관리에 관한비지니스클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 * </pre>
 */
@Service("rlnmManageService")
public class EgovRlnmManageServiceImpl extends EgovAbstractServiceImpl implements EgovRlnmManageService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(EgovRlnmManageServiceImpl.class);

	/**
	 * 실명인증확인화면 호출(주민번호)
	 * @param ihidnum 주민등록번호
	 * @param realname 실명
	 * @param sbscrbTy 사용자 유형
	 * @return result 결과메시지코드
	 * @throws Exception
	 */
	@Override
	public String rlnmCnfirm(String ihidnum, String realname, String sbscrbTy) {
		String result = "ERR";

		EgovSocketClient lm_oSocketClient = new EgovSocketClient();

		/*
		 * 서비스구분코드|인증서CN|이름|주민번호
		 *
		 * 인증서 파일 에서 숫자에 해당하는 값:예) SVR1310000001_***.***
		 * 1310000001이 인증서CN 값 이다
		 */
		String str = "99|1311000011|" + realname + "|" + ihidnum;
		//String str = "99|1311000011|홍길동|주민번호";
		byte[] lm_bResult1 = lm_oSocketClient.execute(str);
		result = new String(lm_bResult1);

		return result;
	}

	/**
	 * 실명인증확인화면 호출(GPIN)
	 * @param pinId 공공아이핀 아이디
	 * @param pinPassword 공공아이핀 패스워드
	 * @param sbscrbTy 사용자 유형
	 * @return result 결과메시지코드
	 * @throws Exception
	 */
	@Override
	public String rlnmPinCnfirm(String pinId, String pinPassword, String sbscrbTy) {
		String result = "ERR";

		/*
		try {
			// 공공아이핀 센터를 통해 pinId, pinPassword를 검증한다.
			result = "result message";
		} catch (Exception e) {
			LOGGER.error("Exception: {}", e.getClass().getName());
			LOGGER.error("Exception  Message: {}", e.getMessage());
		}
		*/

		return result;
	}

}