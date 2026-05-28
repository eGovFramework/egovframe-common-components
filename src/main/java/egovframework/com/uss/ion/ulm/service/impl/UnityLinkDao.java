package egovframework.com.uss.ion.ulm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.ulm.service.UnityLink;
import jakarta.annotation.Resource;

/**
 * 통합링크관리를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * </pre>
 */
@Repository("onlineUnityLinkDao")
public class UnityLinkDao {

    @Resource(name = "onlineUnityLinkMapper")
    private UnityLinkMapper mapper;

    /**
     * 통합링크관리 메인 셈플 목록을 조회한다.
     * @param unityLink - 통합링크관리 VO
     * @return List - 통합링크관리 목록
     */
    public List<?> selectUnityLinkSample(UnityLink unityLink) throws Exception {
        return mapper.selectUnityLinkSample(unityLink);
    }

    /**
     * 통합링크관리를(을) 목록을 한다.
     * @param unityLink 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    public List<?> selectUnityLinkList(UnityLink unityLink) throws Exception {
        return mapper.selectUnityLink(unityLink);
    }

    /**
     * 통합링크관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param unityLink  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectUnityLinkListCnt(UnityLink unityLink) throws Exception {
        return mapper.selectUnityLinkCnt(unityLink);
    }

    /**
     * 통합링크관리를(을) 상세조회 한다.
     * @param unityLink  통합링크관리 정보가 담김 VO
     * @return List
     * @throws Exception
     */
    public UnityLink selectUnityLinkDetail(UnityLink unityLink) throws Exception {
        return mapper.selectUnityLinkDetail(unityLink);
    }

    /**
     * 통합링크관리를(을) 등록한다.
     * @param unityLink  통합링크관리 정보가 담김 VO
     * @throws Exception
     */
    public void insertUnityLink(UnityLink unityLink) throws Exception {
        mapper.insertUnityLink(unityLink);
    }

    /**
     * 통합링크관리를(을) 수정한다.
     * @param unityLink  통합링크관리 정보가 담김 VO
     * @throws Exception
     */
    public void updateUnityLink(UnityLink unityLink) throws Exception {
        mapper.updateUnityLink(unityLink);
    }

    /**
     * 통합링크관리를(을) 삭제한다.
     * @param unityLink  통합링크관리 정보가 담김 VO
     * @throws Exception
     */
    public void deleteUnityLink(UnityLink unityLink) throws Exception {
        mapper.deleteUnityLink(unityLink);
    }

}
