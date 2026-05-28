package egovframework.com.uss.ion.ulm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.ulm.service.UnityLink;

/**
 * 통합링크관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("onlineUnityLinkMapper")
public interface UnityLinkMapper {

	List<?> selectUnityLinkSample(UnityLink unityLink);

	List<?> selectUnityLink(UnityLink unityLink);

	int selectUnityLinkCnt(UnityLink unityLink);

	UnityLink selectUnityLinkDetail(UnityLink unityLink);

	void insertUnityLink(UnityLink unityLink);

	void updateUnityLink(UnityLink unityLink);

	void deleteUnityLink(UnityLink unityLink);
}
