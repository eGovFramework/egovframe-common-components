package egovframework.com.cop.stf.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;

/**
 * 만족도조사를 위한 데이터 접근 클래스
 * 
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *   2024.08.21  이백행          시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Repository("BBSSatisfactionDAO")
public class BBSSatisfactionDAO extends EgovComAbstractDAO {

	/**
	 * 만족도조사에 대한 목록을 조회 한다.
	 * 
	 * @param satisfactionVO
	 * @return
	 */
	public List<SatisfactionVO> selectSatisfactionList(SatisfactionVO satisfactionVO) {
		return selectList("BBSSatisfactionDAO.selectSatisfactionList", satisfactionVO);
	}

	/**
	 * 만족도조사에 대한 목록 건수를 조회 한다.
	 * 
	 * @param satisfactionVO
	 * @return
	 */
	public int selectSatisfactionListCnt(SatisfactionVO satisfactionVO) {
		return (Integer) selectOne("BBSSatisfactionDAO.selectSatisfactionListCnt", satisfactionVO);
	}

	/**
	 * 만족도조사를 등록한다.
	 * 
	 * @param satisfaction
	 */
	public void insertSatisfaction(Satisfaction satisfaction) {
		insert("BBSSatisfactionDAO.insertSatisfaction", satisfaction);
	}

	/**
	 * 만족도조사를 삭제한다.
	 * 
	 * @param satisfactionVO
	 */
	public void deleteSatisfaction(SatisfactionVO satisfactionVO) {
		update("BBSSatisfactionDAO.deleteSatisfaction", satisfactionVO);
	}

	/**
	 * 만족도조사에 대한 내용을 조회한다.
	 * 
	 * @param satisfactionVO
	 * @return
	 */
	public Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO) {
		return (Satisfaction) selectOne("BBSSatisfactionDAO.selectSatisfaction", satisfactionVO);
	}

	/**
	 * 만족도조사에 대한 내용을 수정한다.
	 * 
	 * @param satisfaction
	 */
	public void updateSatisfaction(Satisfaction satisfaction) {
		insert("BBSSatisfactionDAO.updateSatisfaction", satisfaction);
	}

	/**
	 * 만족도조사에 대한 패스워드를 조회 한다.
	 * 
	 * @param satisfaction
	 * @return
	 */
	public String getSatisfactionPassword(Satisfaction satisfaction) {
		return (String) selectOne("BBSSatisfactionDAO.getSatisfactionPassword", satisfaction);
	}

	/**
	 * 만족도 전체 점수를 제공한다.
	 * 
	 * @param satisfactionVO
	 * @return
	 */
	public Float getSummary(SatisfactionVO satisfactionVO) {
		return (Float) selectOne("BBSSatisfactionDAO.getSummary", satisfactionVO);
	}
}
