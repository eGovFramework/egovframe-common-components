package egovframework.com.uss.ion.ism.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.uss.ion.ism.service.EgovInfrmlSanctnService;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.ism.service.SanctnerVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * 약식결재관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 약식결재관리에 대한 등록, 수정, 삭제기능을 제공한다.
 * - 결재자에 대한 목록조회기능을 제공한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 */

@Service("EgovInfrmlSanctnService")
public class EgovInfrmlSanctnServiceImpl extends EgovAbstractServiceImpl implements EgovInfrmlSanctnService {
	
	@Resource(name = "InfrmlSanctnDAO")
    private InfrmlSanctnDAO infrmlSanctnDAO;
    
	@Resource(name="egovInfrmlSanctnIdGnrService")
	private EgovIdGnrService idgenServiceInfrmlSanctn;
	/**
	 * 결재자 목록을 조회한다.
	 * @param SanctnerVO
	 * @return  Map<String, Object>
	 * 
	 * @param sanctnerVO
	 */
	public Map<String, Object> selectSanctnerList(SanctnerVO sanctnerVO) throws Exception{
		List<SanctnerVO> result = infrmlSanctnDAO.selectSanctnerList(sanctnerVO);
		int cnt = infrmlSanctnDAO.selectSanctnerListCnt(sanctnerVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	/**
	 * 약식결재 정보를 조회한다.
	 * @param InfrmlSanctnVO
	 * @return  InfrmlSanctnVO
	 * 
	 * @param infrmlSanctnVO
	 */
	public InfrmlSanctn selectInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		InfrmlSanctn result = infrmlSanctnDAO.selectInfrmlSanctn(infrmlSanctn);
		if(result.getSanctnDt() != null && !result.getSanctnDt().equals("")){
			if(result.getSanctnDt().length() > 18){
				result.setSanctnDt(result.getSanctnDt().substring(0, 19));
			}
		}
		return result;
	}
	
	/**
	 * 약식결재관리 정보를 수정한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public InfrmlSanctn updateInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		infrmlSanctnDAO.updateInfrmlSanctn(infrmlSanctn);
		return selectInfrmlSanctn(infrmlSanctn);
	}
	
	/**
	 * 약식결재관리 정보를 승인한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public InfrmlSanctn updateInfrmlSanctnConfm(InfrmlSanctn infrmlSanctn) throws Exception{
		infrmlSanctn.setConfmAt("C");
		infrmlSanctnDAO.updateInfrmlSanctnConfm(infrmlSanctn);
		
		return selectInfrmlSanctn(infrmlSanctn);
	}
	
	/**
	 * 약식결재관리 정보를 반려한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public InfrmlSanctn updateInfrmlSanctnReturn(InfrmlSanctn infrmlSanctn) throws Exception{
		infrmlSanctn.setConfmAt("R");
		infrmlSanctnDAO.updateInfrmlSanctnConfm(infrmlSanctn);
		
		return selectInfrmlSanctn(infrmlSanctn);
	}

	/**
	 * 약식결재관리 정보를 등록한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public InfrmlSanctn insertInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		infrmlSanctn.setInfrmlSanctnId(idgenServiceInfrmlSanctn.getNextStringId());
		infrmlSanctn.setConfmAt("A");
		infrmlSanctnDAO.insertInfrmlSanctn(infrmlSanctn);
		
		return selectInfrmlSanctn(infrmlSanctn);
	}

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public void deleteInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		infrmlSanctnDAO.deleteInfrmlSanctn(infrmlSanctn);
	}
	
}