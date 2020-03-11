 package egovframework.com.cop.sms.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;

import org.springframework.stereotype.Repository;

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
 *
 * </pre>
 */
@Repository("SmsDAO")
public class SmsDAO extends EgovComAbstractDAO {
    /**
     * 문자메시지 목록을 조회한다.
     * 
     * @param SmsVO
     */
    public List<SmsVO> selectSmsInfs(SmsVO vo) throws Exception {
	return selectList("SmsDAO.selectSmsInfs", vo);
    }

    /**
     * 문자메시지 목록 숫자를 조회한다
     * 
     * @param SmsVO
     * @return
     * @throws Exception
     */
    public int selectSmsInfsCnt(SmsVO vo) throws Exception {
	return (Integer)selectOne("SmsDAO.selectSmsInfsCnt", vo);
    }
    
    /**
     * 문자메시지 정보를 등록한다.
     * 
     * @param notification
     * @return
     * @throws Exception
     */
    public String insertSmsInf(Sms sms) throws Exception {    	
    	return Integer.toString(insert("SmsDAO.insertSmsInf", sms)); 
    }
    
    /**
     * 문자메시지 수신정보 및 결과 정보를 등록한다.
     * @param smsRecptn
     * @return
     * @throws Exception
     */
    public String insertSmsRecptnInf(SmsRecptn smsRecptn) throws Exception {
    	return Integer.toString(insert("SmsDAO.insertSmsRecptnInf", smsRecptn));
    }
    
    /**
     * 문자메시지에 대한 상세정보를 조회한다.
     * 
     * @param searchVO
     * @return
     */
    public SmsVO selectSmsInf(SmsVO searchVO) {
	return (SmsVO)selectOne("SmsDAO.selectSmsInf", searchVO);
    }
    
    /**
     * 문자메시지 수신 및 결과 목록을 조회한다.
     * 
     * @param SmsRecptn
     */
    public List<SmsRecptn> selectSmsRecptnInfs(SmsRecptn vo) throws Exception {
	return selectList("SmsDAO.selectSmsRecptnInfs", vo);
    }
    
    /**
     * 문자메시지 전송 결과 수신을 처리한다.
     * EgovSmsInfoReceiver(Schedule job)에 의해 호출된다.
     * 
     * @param smsRecptn
     * @return
     * @throws Exception
     */
    public String updateSmsRecptnInf(SmsRecptn smsRecptn) throws Exception {
    	return Integer.toString(insert("SmsDAO.updateSmsRecptnInf", smsRecptn));
    }
}
