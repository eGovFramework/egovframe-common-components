package egovframework.com.uss.ion.ulm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.ion.ulm.service.EgovUnityLinkService;
import egovframework.com.uss.ion.ulm.service.UnityLink;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 통합링크관리를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
@Service("egovUnityLinkService")
public class EgovUnityLinkServiceImpl extends EgovAbstractServiceImpl
        implements EgovUnityLinkService {

    @Resource(name = "onlineUnityLinkDao")
    private UnityLinkDao dao;

    @Resource(name = "egovUnityLinkIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     *통합링크관리 메인 셈플 목록을 조회한다.
     * @param unityLink  통합링크관리 정보 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectUnityLinkSample(UnityLink unityLink) throws Exception {
        return dao.selectUnityLinkSample(unityLink);
    }

    /**
     * 통합링크관리를(을) 목록을 조회 한다.
     * @param searchVO 조회할 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectUnityLinkList(ComDefaultVO searchVO) throws Exception {
        return dao.selectUnityLinkList(searchVO);
    }

    /**
     * 통합링크관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Override
	public int selectUnityLinkListCnt(ComDefaultVO searchVO) throws Exception {
        return dao.selectUnityLinkListCnt(searchVO);
    }

    /**
     * 통합링크관리를(을) 상세조회 한다.
     * @param unityLink 조회할 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public UnityLink selectUnityLinkDetail(UnityLink unityLink) throws Exception {
        return dao.selectUnityLinkDetail(unityLink);
    }

    /**
     * 통합링크관리를(을) 등록한다.
     * @param unityLink 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Override
	public void insertUnityLink(UnityLink unityLink)throws Exception {
        String sMakeId = idgenService.getNextStringId();
        unityLink.setUnityLinkId(sMakeId);
        dao.insertUnityLink(unityLink);
    }

    /**
     * 통합링크관리를(을) 수정한다.
     * @param searchVO 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Override
	public void updateUnityLink(UnityLink unityLink) throws Exception {
        dao.updateUnityLink(unityLink);
    }

    /**
     * 통합링크관리를(을) 삭제한다.
     * @param searchVO 조회할 정보가 담긴 VO
     * @throws Exception
     */
    @Override
	public void deleteUnityLink(UnityLink unityLink) throws Exception {
        dao.deleteUnityLink(unityLink);
    }

}
