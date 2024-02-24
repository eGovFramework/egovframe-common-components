package egovframework.com.cop.sms.service;

import java.util.Map;

/**
 * 문자메시지를 위한 서비스 인터페이스 클래스
 * 
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.18
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.18  한성곤          최초 생성
 *   2024.02.24  이백행          보안약점 조치: 부적절한 예외 처리 (광범위한 예외객체 선언)
 *
 *      </pre>
 */
public interface EgovSmsInfoService {
	/**
	 * 문자메시지 목록을 조회 한다.
	 * 
	 * @param SmsVO
	 */
	public Map<String, Object> selectSmsInfs(SmsVO searchVO);

	/**
	 * 문자메시지를 전송(등록)한다.
	 * 
	 * @param sms @
	 */
	public void insertSmsInf(Sms sms);

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param searchVO
	 * @return @
	 */
	public SmsVO selectSmsInf(SmsVO searchVO);

	/**
	 * 문자메시지 실 전송을 요청한다.
	 * 
	 * @param smsConn
	 * @return @
	 */
	public SmsConnection sendRequsest(SmsConnection smsConn);

	/**
	 * 여러 건의 문자메시지 실 전송을 요청한다.
	 * 
	 * @param smsConn
	 * @return @
	 */
	public SmsConnection[] sendRequsest(SmsConnection[] smsConn);
}
