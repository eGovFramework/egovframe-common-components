package egovframework.com.cop.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;
import jakarta.annotation.Resource;

/**
 * 문자메시지를 위한 데이터 접근 클래스
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
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * </pre>
 */
@Repository("SmsDAO")
public class SmsDAO {

	@Resource(name = "smsMapper")
	private SmsMapper mapper;

    /**
     * 문자메시지 목록을 조회한다.
     *
     * @param smsVO
     * @return List<SmsVO>
     */
	public List<SmsVO> selectSmsInfs(SmsVO smsVO) {
		return mapper.selectSmsInfs(smsVO);
	}

    /**
     * 문자메시지 목록 숫자를 조회한다
     *
     * @param smsVO
     * @return int
     */
	public int selectSmsInfsCnt(SmsVO smsVO) {
		return mapper.selectSmsInfsCnt(smsVO);
	}

    /**
     * 문자메시지 정보를 등록한다.
     *
     * @param sms
     * @return int
     */
	public int insertSmsInf(Sms sms) {
		return mapper.insertSmsInf(sms);
	}

    /**
     * 문자메시지 수신정보 및 결과 정보를 등록한다.
     *
     * @param smsRecptn
     * @return int
     */
	public int insertSmsRecptnInf(SmsRecptn smsRecptn) {
		return mapper.insertSmsRecptnInf(smsRecptn);
	}

    /**
     * 문자메시지에 대한 상세정보를 조회한다.
     *
     * @param smsVO
     * @return SmsVO
     */
	public SmsVO selectSmsInf(SmsVO smsVO) {
		return mapper.selectSmsInf(smsVO);
	}

    /**
     * 문자메시지 수신 및 결과 목록을 조회한다.
     *
     * @param smsRecptn
     * @return List<SmsRecptn>
     */
	public List<SmsRecptn> selectSmsRecptnInfs(SmsRecptn smsRecptn) {
		return mapper.selectSmsRecptnInfs(smsRecptn);
	}

    /**
     * 문자메시지 전송 결과 수신을 처리한다. EgovSmsInfoReceiver(Schedule job)에 의해 호출된다.
     *
     * @param smsRecptn
     * @return int
     */
	public int updateSmsRecptnInf(SmsRecptn smsRecptn) {
		return mapper.updateSmsRecptnInf(smsRecptn);
	}

}
