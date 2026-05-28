package egovframework.com.cop.sms.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;

/**
 * 문자메시지를 위한 Mapper 인터페이스
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
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 *
 * </pre>
 */
@EgovMapper("smsMapper")
public interface SmsMapper {

	List<SmsVO> selectSmsInfs(SmsVO smsVO);

	int selectSmsInfsCnt(SmsVO smsVO);

	int insertSmsInf(Sms sms);

	int insertSmsRecptnInf(SmsRecptn smsRecptn);

	SmsVO selectSmsInf(SmsVO smsVO);

	List<SmsRecptn> selectSmsRecptnInfs(SmsRecptn smsRecptn);

	int updateSmsRecptnInf(SmsRecptn smsRecptn);

}
