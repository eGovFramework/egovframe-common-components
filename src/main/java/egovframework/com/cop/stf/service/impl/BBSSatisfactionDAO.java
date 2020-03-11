package egovframework.com.cop.stf.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;

import org.springframework.stereotype.Repository;

/**
 * 만족도조사를 위한 데이터 접근 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *
 * </pre>
 */
@Repository("BBSSatisfactionDAO")
public class BBSSatisfactionDAO extends EgovComAbstractDAO {

    /**
     * 만족도조사에 대한 목록을 조회 한다.
     * 
     * @param satisfactionVO
     * @return
     * @throws Exception
     */
    public List<SatisfactionVO> selectSatisfactionList(SatisfactionVO satisfactionVO) throws Exception {
	return selectList("BBSSatisfactionDAO.selectSatisfactionList", satisfactionVO);
    }
    
    /**
     * 만족도조사에 대한 목록 건수를 조회 한다.
     * 
     * @param satisfactionVO
     * @return
     * @throws Exception
     */
    public int selectSatisfactionListCnt(SatisfactionVO satisfactionVO) throws Exception {
	return (Integer)selectOne("BBSSatisfactionDAO.selectSatisfactionListCnt", satisfactionVO);
    }
    
    /**
     * 만족도조사를 등록한다.
     * 
     * @param satisfaction
     * @throws Exception
     */
    public void insertSatisfaction(Satisfaction satisfaction) throws Exception {	
	insert("BBSSatisfactionDAO.insertSatisfaction", satisfaction);
    }
    
    /**
     * 만족도조사를 삭제한다.
     * 
     * @param satisfactionVO
     * @throws Exception
     */
    public void deleteSatisfaction(SatisfactionVO satisfactionVO) throws Exception {
	update("BBSSatisfactionDAO.deleteSatisfaction", satisfactionVO);
    }
    
    /**
     * 만족도조사에 대한 내용을 조회한다.
     * 
     * @param satisfactionVO
     * @return
     * @throws Exception
     */
    public Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO) throws Exception {
	return (Satisfaction)selectOne("BBSSatisfactionDAO.selectSatisfaction", satisfactionVO);
    }
    
    /**
     * 만족도조사에 대한 내용을 수정한다.
     * 
     * @param satisfaction
     * @throws Exception
     */
    public void updateSatisfaction(Satisfaction satisfaction) throws Exception {	
	insert("BBSSatisfactionDAO.updateSatisfaction", satisfaction);
    }
    
    /**
     * 만족도조사에 대한 패스워드를 조회 한다.
     * 
     * @param satisfaction
     * @return
     * @throws Exception
     */
    public String getSatisfactionPassword(Satisfaction satisfaction) throws Exception {
	return (String)selectOne("BBSSatisfactionDAO.getSatisfactionPassword", satisfaction);
    }
    
    /**
     * 만족도 전체 점수를 제공한다.
     * 
     * @param satisfactionVO
     * @return
     * @throws Exception
     */
    public Float getSummary(SatisfactionVO satisfactionVO) throws Exception {
	return (Float)selectOne("BBSSatisfactionDAO.getSummary", satisfactionVO);
    }
}
