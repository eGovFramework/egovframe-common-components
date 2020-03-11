package egovframework.com.cop.smt.wmr.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.smt.wmr.service.EgovWikMnthngReprtService;
import egovframework.com.cop.smt.wmr.service.ReportrVO;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprt;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprtVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * 주간월간보고에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 주간월간보고에 대한 등록, 수정, 삭제, 조회, 승인기능을 제공한다.
 * - 주간월간보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:47
 *   <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
@Service("EgovWikMnthngReprtService")
public class EgovWikMnthngReprtServiceImpl extends EgovAbstractServiceImpl implements EgovWikMnthngReprtService {

	@Resource(name = "WikMnthngReprtDAO")
    private WikMnthngReprtDAO wikMnthngReprtDAO;
	
	@Resource(name="egovWikMnthngReprtIdGnrService")
	private EgovIdGnrService idgenServiceWikMnthngReprt;
	
	/**
	 * 보고자 목록을 조회한다.
	 * @param ReportrVO
	 * @return  Map<String, Object>
	 * 
	 * @param reportrVO
	 */
	public Map<String, Object> selectReportrList(ReportrVO reportrVO) throws Exception{
		List<ReportrVO> result = wikMnthngReprtDAO.selectReportrList(reportrVO);
		int cnt = wikMnthngReprtDAO.selectReportrListCnt(reportrVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
	 * 사용자 직위명 정보를 조회한다.
	 * @param String
	 * @return  String
	 * 
	 * @param String
	 */
	public String selectWrterClsfNm(String wrterId) throws Exception{
		return wikMnthngReprtDAO.selectWrterClsfNm(wrterId);
	}
	
	/**
	 * 주간월간보고 목록을 조회한다.
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return  List<WikMnthngReprtVO> - 주간월간보고 List
	 * 
	 * @param wikMnthngReprtVO
	 */
	public Map<String, Object> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO) throws Exception{
		List<WikMnthngReprtVO> result = wikMnthngReprtDAO.selectWikMnthngReprtList(wikMnthngReprtVO);
		int cnt = wikMnthngReprtDAO.selectWikMnthngReprtListCnt(wikMnthngReprtVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 주간월간보고 정보를 조회한다.
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return  WikMnthngReprtVO - 주간월간보고 VO
	 * 
	 * @param wikMnthngReprtVO
	 */
	public WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) throws Exception{
		
		WikMnthngReprtVO resultVO = wikMnthngReprtDAO.selectWikMnthngReprt(wikMnthngReprtVO);
		if(resultVO.getConfmDt() == null || resultVO.getConfmDt().equals("")){
			String year = resultVO.getFrstRegisterPnttm().substring(0,4);
			String month = resultVO.getFrstRegisterPnttm().substring(5,7);
			String day = resultVO.getFrstRegisterPnttm().substring(8,10);
			String hour = resultVO.getFrstRegisterPnttm().substring(11,13);
			String min = resultVO.getFrstRegisterPnttm().substring(14,16);
			
			String yymmddhhmm = year + "/" + month + "/" + day + "  " + hour + "시 " + min + "분";
			resultVO.setReprtSttus("등록 (" + yymmddhhmm + ") ");
		}else{
			String year = resultVO.getConfmDt().substring(0,4);
			String month = resultVO.getConfmDt().substring(4,6);
			String day = resultVO.getConfmDt().substring(6,8);
			String hour = resultVO.getConfmDt().substring(8,10);
			String min = resultVO.getConfmDt().substring(10,12);
			
			String yymmddhhmm = year + "/" + month + "/" + day + "  " + hour + "시 " + min + "분";
			resultVO.setReprtSttus("승인 (" + yymmddhhmm  + ") ");
		}
		
		return resultVO;
	}

	/**
	 * 주간월간보고 정보를 수정한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void updateWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception{
		wikMnthngReprtDAO.updateWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 등록한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void insertWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception{
		wikMnthngReprt.setReprtId(idgenServiceWikMnthngReprt.getNextStringId());
		wikMnthngReprtDAO.insertWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 승인한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void confirmWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		wikMnthngReprt.setConfmDt(formatter.format(new java.util.Date()));
		wikMnthngReprtDAO.confirmWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void deleteWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception{
		wikMnthngReprtDAO.deleteWikMnthngReprt(wikMnthngReprt);
	}
}