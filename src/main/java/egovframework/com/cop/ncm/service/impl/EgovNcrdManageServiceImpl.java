package egovframework.com.cop.ncm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.ncm.service.EgovNcrdManageService;
import egovframework.com.cop.ncm.service.NameCard;
import egovframework.com.cop.ncm.service.NameCardUser;
import egovframework.com.cop.ncm.service.NameCardVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 명함정보를 관리하기 위한 서비스 구현  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.28  이삼섭          최초 생성
 *
 * </pre>
 */
@Service("EgovNcrdManageService")
public class EgovNcrdManageServiceImpl extends EgovAbstractServiceImpl implements EgovNcrdManageService {

    @Resource(name = "NcrdManageDAO")
    private NcrdManageDAO ncrdMngDAO;

    @Resource(name = "egovNcrdIdGnrService")
    private EgovIdGnrService idgenService;

    //Logger log = Logger.getLogger(this.getClass());

    /**
     * 명함 정보를 삭제한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#deleteNcrdItem(egovframework.com.cop.ncm.num.service.NameCard)
     */

    
    public void deleteNcrdItem(NameCardVO nameCardVO) throws Exception {
    	
    	ncrdMngDAO.deleteNcrdItemUser(nameCardVO);
    	ncrdMngDAO.deleteNcrdItem(nameCardVO);
	
    }

    /**
     * 명함 정보 및 명함사용자 정보를 등록한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#insertNcrdItem(egovframework.com.cop.ncm.num.service.NameCard)
     */
    public void insertNcrdItem(NameCard nameCard) throws Exception {
	nameCard.setTelNo(nameCard.getNationNo() + nameCard.getAreaNo() + nameCard.getMiddleTelNo() + nameCard.getEndTelNo());
	nameCard.setMbtlNum(nameCard.getIdntfcNo() + nameCard.getMiddleMbtlNum() + nameCard.getEndMbtlNum());

	nameCard.setNcrdId(idgenService.getNextStringId());

	NameCardUser ncrdUser = new NameCardUser();
	
	ncrdUser.setNcrdId(nameCard.getNcrdId());
	ncrdUser.setEmplyrId(nameCard.getFrstRegisterId());
	ncrdUser.setRegistSeCode("REGC04");
	ncrdUser.setUseAt("Y");		

	ncrdMngDAO.insertNcrdItem(nameCard);
	ncrdMngDAO.insertNcrdUseInf(ncrdUser);
    }

    /**
     * 명함사용자 정보를 등록한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#insertNcrdUseInf(egovframework.com.cop.ncm.num.service.NameCardUser)
     */
    public void insertNcrdUseInf(NameCardUser ncrdUser) throws Exception {
	ncrdUser.setRegistSeCode("REGC04");

	ncrdMngDAO.insertNcrdUseInf(ncrdUser);
    }

    /**
     * 명함 정보에 대한 상세정보를 조회한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#selectNcrdItem(egovframework.com.cop.ncm.num.service.NameCard)
     */
    public NameCardVO selectNcrdItem(NameCardVO ncrdVO) throws Exception {
	return ncrdMngDAO.selectNcrdItem(ncrdVO);
    }

    /**
     * 명함 정보에 대한 목록을 조회한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#selectNcrdItems(egovframework.com.cop.ncm.num.service.NameCard)
     */
    public Map<String, Object> selectNcrdItems(NameCardVO ncrdVO) throws Exception {
	List<NameCardVO> result = ncrdMngDAO.selectNcrdItemList(ncrdVO);
	int cnt = ncrdMngDAO.selectNcrdItemListCnt(ncrdVO);

	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 명함 정보에 대한 목록 전체 건수를 조회한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#selectNcrdUseInf(egovframework.com.cop.ncm.num.service.NameCardUser)
     */
    public Map<String, Object> selectNcrdUseInfs(NameCardUser ncrdUser) throws Exception {
	List<NameCardUser> result = ncrdMngDAO.selectNcrdUseInfs(ncrdUser);
	int cnt = ncrdMngDAO.selectNcrdUseInfsCnt(ncrdUser);

	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 명함 정보를 수정한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#updateNcrdItem(egovframework.com.cop.ncm.num.service.NameCard)
     */
    public void updateNcrdItem(NameCard nameCard) throws Exception {
	nameCard.setTelNo(nameCard.getNationNo() + nameCard.getAreaNo() + nameCard.getMiddleTelNo() + nameCard.getEndTelNo());
	nameCard.setMbtlNum(nameCard.getIdntfcNo() + nameCard.getMiddleMbtlNum() + nameCard.getEndMbtlNum());

	ncrdMngDAO.updateNcrdItem(nameCard);

    }

    /**
     * 명함사용자 정보를 수정한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#updateNcrdUseInf(egovframework.com.cop.ncm.num.service.NameCardUser)
     */
    public void updateNcrdUseInf(NameCardUser ncrdUser) throws Exception {
	ncrdMngDAO.updateNcrdUseInf(ncrdUser);
    }

    /**
     * 내 명함 정보에 대한 목록을 조회한다.
     * 
     * @see egovframework.com.cop.ncm.num.service.EgovNcrdManageService#selectMyNcrdItems(egovframework.com.cop.ncm.num.service.NameCard)
     */
    public Map<String, Object> selectMyNcrdItems(NameCardVO ncrdVO) throws Exception {
	List<NameCardVO> result = ncrdMngDAO.selectMyNcrdItemList(ncrdVO);
	int cnt = ncrdMngDAO.selectMyNcrdItemListCnt(ncrdVO);

	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

}
