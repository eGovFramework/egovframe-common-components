package egovframework.com.sym.mnu.bmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.sym.mnu.bmm.service.BkmkMenuManage;
import egovframework.com.sym.mnu.bmm.service.BkmkMenuManageVO;
import egovframework.com.sym.mnu.bmm.service.EgovBkmkMenuManageservice;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovBkmkMenuManageServiceImpl.java
 * @Description : 바로가기메뉴를 관리하는 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 9. 25.     윤성록
 *
 * @author 공통 컴포넌트 개발팀 윤성록
 * @since 2009. 9. 25.
 * @version
 * @see
 *
 */
@Service("bkmkMenuManageservice")
public class EgovBkmkMenuManageServiceImpl extends EgovAbstractServiceImpl implements EgovBkmkMenuManageservice{

    
    @Resource(name = "bkmkMenuManageDAO")
    private BkmkMenuManageDAO bkmkMenuManageDAO;
       
    /**
     * 바로가기메뉴관리 정보를 삭제한다.
     * @param BkmkMenuManage
     * @return  
     * @exception Exception
     */
    public void deleteBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception  {
        bkmkMenuManageDAO.deleteBkmkMenuManage(bkmkMenuManage);        
    }

    /**
     * 바로가기메뉴관리 정보를 등록한다.
     * @param BkmkMenuManage
     * @return  
     * @exception Exception
     */
    public void insertBkmkMenuManage(BkmkMenuManage bkmkMenuManage) throws Exception {
        bkmkMenuManageDAO.insertBkmkMenuManage(bkmkMenuManage);        
    }

    /**
     * 바로가기메뉴관리 정보의 전체목록을 조회한다.
     * @param BkmkMenuManage
     * @return  Map<String, Object>
     * @exception Exception
     */
    public Map<String, Object> selectBkmkMenuManageList(
            BkmkMenuManageVO bkmkMenuManageVO)  throws Exception{
        
        List<BkmkMenuManageVO> result = bkmkMenuManageDAO.selectBkmkMenuManageList(bkmkMenuManageVO);
        
        int cnt = bkmkMenuManageDAO.selectBkmkMenuManageListCnt(bkmkMenuManageVO);

        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;  
    }

    /**
     * 바로가기메뉴관리 정보를 조회한다.
     * @param BkmkMenuManageVO
     * @return  BkmkMenuManageVO
     * @exception Exception
     */
    public BkmkMenuManageVO selectBkmkMenuManageResult(
            BkmkMenuManageVO bkmkMenuManageVO) throws Exception {

        return bkmkMenuManageDAO.selectBkmkMenuManageResult(bkmkMenuManageVO);        
    }

    /**
     * 등록할  메뉴정보 목록을 조회한다.
     * @param BkmkMenuManageVO
     * @return  Map<String, Object>
     * @exception Exception
     */
    public  Map<String, Object> selectMenuList(BkmkMenuManageVO bkmkMenuManageVO)
            throws Exception {
        
        List<BkmkMenuManageVO> result = bkmkMenuManageDAO.selectBkmkMenuList(bkmkMenuManageVO);
        
        int cnt = bkmkMenuManageDAO.selectBkmkMenuListCnt(bkmkMenuManageVO);

        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;  
        
    }

    /**
     * 미리보기를 할 바로가기메뉴관리의 목록을 조회한다.
     * 
     * @param BkmkMenuManageVO
     * @return  List<MenuManageVO> 
     * @throws Exception
     */
    public List<MenuManageVO>  selectBkmkPreviewList(BkmkMenuManageVO bkmkMenuManageVO)
            throws Exception {
        
                
        List<MenuManageVO> result = bkmkMenuManageDAO.selectBkmkPreview(bkmkMenuManageVO);
        
        return result;
    }

    /**
     * 선택된 메뉴의 URL 을 조회한다.
     * 
     * @param bkmkMenuManage
     * @return
     * @throws Exception
     */
    public String selectUrl(BkmkMenuManage bkmkMenuManage) throws Exception {
        
        return bkmkMenuManageDAO.selectUrl(bkmkMenuManage);
    }
}
