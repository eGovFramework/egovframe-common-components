package egovframework.com.cop.tpl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.tpl.service.EgovTemplateManageService;
import egovframework.com.cop.tpl.service.TemplateInf;
import egovframework.com.cop.tpl.service.TemplateInfVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 템플릿 정보관리를 위한 서비스 구현 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.17   이삼섭           최초 생성
 *  2019.05.17   신용호           selectTemplateWhiteList() 추가
 *
 * </pre>
 */
@Service("EgovTemplateManageService")
public class EgovTemplateManageServiceImpl extends EgovAbstractServiceImpl implements EgovTemplateManageService {

    @Resource(name = "TemplateManageDAO")
    private TemplateManageDAO tmplatDAO;

    @Resource(name = "egovTmplatIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 템플릿 정보를 삭제한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#deleteTemplateInf(egovframework.com.cop.bbs.com.service.TemplateInf)
     */
    public void deleteTemplateInf(TemplateInf tmplatInf) throws Exception {
	tmplatDAO.deleteTemplateInf(tmplatInf);
    }

    /**
     * 템플릿 정보를 등록한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#insertTemplateInf(egovframework.com.cop.bbs.com.service.TemplateInf)
     */
    public void insertTemplateInf(TemplateInf tmplatInf) throws Exception {

	tmplatInf.setTmplatId(idgenService.getNextStringId());

	tmplatDAO.insertTemplateInf(tmplatInf);
    }

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#selectTemplateInf(egovframework.com.cop.bbs.com.service.TemplateInfVO)
     */
    public TemplateInfVO selectTemplateInf(TemplateInfVO tmplatInfVO) throws Exception {
	TemplateInfVO vo = new TemplateInfVO();
	vo = tmplatDAO.selectTemplateInf(tmplatInfVO);
	return vo;
    }

    /**
     * 템플릿에 대한 화이트리스트 목록을 조회한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#selectTemplateInfs(egovframework.com.cop.bbs.com.service.TemplateInfVO)
     */
    public List<TemplateInfVO> selectTemplateWhiteList() throws Exception {
    	List<TemplateInfVO> resultWhiteList = tmplatDAO.selectTemplateWhiteList();

    	return resultWhiteList;
    }
    
    /**
     * 템플릿에 대한 목록를 조회한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#selectTemplateInfs(egovframework.com.cop.bbs.com.service.TemplateInfVO)
     */
    public Map<String, Object> selectTemplateInfs(TemplateInfVO tmplatInfVO) throws Exception {
	List<TemplateInfVO> result = tmplatDAO.selectTemplateInfs(tmplatInfVO);
	int cnt = tmplatDAO.selectTemplateInfsCnt(tmplatInfVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 템플릿에 대한 미리보기 정보를 조회한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#selectTemplatePreview(egovframework.com.cop.bbs.com.service.TemplateInfVO)
     */
    public TemplateInfVO selectTemplatePreview(TemplateInfVO tmplatInfVO) throws Exception {
	TemplateInfVO vo = new TemplateInfVO();
	
	vo = tmplatDAO.selectTemplatePreview(tmplatInfVO);
	
	return vo;
    }

    /**
     * 템플릿 정보를 수정한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#updateTemplateInf(egovframework.com.cop.bbs.com.service.TemplateInf)
     */
    public void updateTemplateInf(TemplateInf tmplatInf) throws Exception {
	tmplatDAO.updateTemplateInf(tmplatInf);
    }

    /**
     * 템플릿 구분에 따른 목록을 조회한다.
     * 
     * @see egovframework.com.cop.bbs.com.service.EgovTemplateManageService#selectAllTemplateInfs(egovframework.com.cop.bbs.com.service.TemplateInfVO)
     */
    public List<TemplateInfVO> selectTemplateInfsByCode(TemplateInfVO tmplatInfVO) throws Exception {
	return tmplatDAO.selectTemplateInfsByCode(tmplatInfVO);
    }
}
