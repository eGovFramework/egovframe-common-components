package egovframework.com.uss.ion.pwm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.ion.pwm.service.PopupManageVO;

/**
 * 팝업창관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("popupManageMapper")
public interface PopupManageMapper {

	void deletePopupManage(PopupManageVO popupManageVO);

	void insertPopupManage(PopupManageVO popupManageVO);

	void updatePopupManage(PopupManageVO popupManageVO);

	PopupManageVO selectPopupManageDetail(PopupManageVO popupManageVO);

	List<EgovMap> selectPopupWhiteList();

	List<EgovMap> selectPopupManage(PopupManageVO popupManageVO);

	int selectPopupManageCnt(PopupManageVO popupManageVO);

	List<EgovMap> selectPopupManageMain(PopupManageVO popupManageVO);
}
