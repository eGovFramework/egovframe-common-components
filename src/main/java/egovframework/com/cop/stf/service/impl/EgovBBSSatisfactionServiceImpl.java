package egovframework.com.cop.stf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.bbs.service.Satisfaction;
import egovframework.com.cop.bbs.service.SatisfactionVO;
import egovframework.com.cop.bbs.service.impl.BBSAddedOptionsDAO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 만족도조사를 위한 서비스 구현 클래스
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
 *   2011.09.15  서준식          addedOptions 적용 방법 수정
 *   2011.10.18  서준식          StsfdgNo 자동 생성 방식으로 변경
 * </pre>
 */
@Service("EgovBBSSatisfactionService")
public class EgovBBSSatisfactionServiceImpl extends EgovAbstractServiceImpl implements EgovBBSSatisfactionService {

    @Resource(name = "BBSAddedOptionsDAO")
    private BBSAddedOptionsDAO addedOptionsDAO;
    
    @Resource(name = "BBSSatisfactionDAO")
    private BBSSatisfactionDAO bbsSatisfactionDAO;
    
    @Resource(name = "egovStsfdgNoGnrService")
    private EgovIdGnrService egovStsfdgNoGnrService;

    /**
     * 만족도조사 사용 가능 여부를 확인한다.
     */
    public boolean canUseSatisfaction(String bbsId) throws Exception {
	//String flag = EgovProperties.getProperty("Globals.addedOptions");
	//if (flag != null && flag.trim().equalsIgnoreCase("true")) {//2011.09.15
	    BoardMaster vo = new BoardMaster();
	    
	    vo.setBbsId(bbsId);
	    
	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(vo);
	    
	    if (options == null) {
		return false;
	    }
	    
	    if (options.getStsfdgAt().equals("Y")) {
		return true;
	    }
	//}
	
	return false;
    }

    /**
     * 만족도조사에 대한 목록을 조회 한다.
     */
    public Map<String, Object> selectSatisfactionList(SatisfactionVO satisfactionVO) throws Exception {
	List<SatisfactionVO> result = bbsSatisfactionDAO.selectSatisfactionList(satisfactionVO);
	int cnt = bbsSatisfactionDAO.selectSatisfactionListCnt(satisfactionVO);
	float summary = bbsSatisfactionDAO.getSummary(satisfactionVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));
	map.put("summary", Float.toString(summary));

	return map;
    }
    
    /**
     * 만족도조사를 등록한다.
     */
    public void insertSatisfaction(Satisfaction satisfaction) throws Exception {
    
    satisfaction.setStsfdgNo(egovStsfdgNoGnrService.getNextLongId() + "");//2011.10.18	
	bbsSatisfactionDAO.insertSatisfaction(satisfaction);
    }
    
    /**
     * 만족도조사를 삭제한다.
     */
    public void deleteSatisfaction(SatisfactionVO satisfactionVO) throws Exception {
	bbsSatisfactionDAO.deleteSatisfaction(satisfactionVO);
    }
    
    /**
     * 만족도조사에 대한 내용을 조회한다.
     */
    public Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO) throws Exception {
	return bbsSatisfactionDAO.selectSatisfaction(satisfactionVO);
    }
    
    /**
     * 만족도조사에 대한 내용을 수정한다.
     */
    public void updateSatisfaction(Satisfaction satisfaction) throws Exception {
	bbsSatisfactionDAO.updateSatisfaction(satisfaction);
    }
    
    /**
     * 만족도조사 패스워드를 가져온다.
     */
    public String getSatisfactionPassword(Satisfaction satisfaction) throws Exception {
	return bbsSatisfactionDAO.getSatisfactionPassword(satisfaction);
    }
}
