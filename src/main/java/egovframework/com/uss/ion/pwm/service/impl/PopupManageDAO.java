package egovframework.com.uss.ion.pwm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.pwm.service.PopupManageVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 팝업창에 대한 DAO를 정의한다.
 *
 * 상세내용
 * - 팝업창에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 팝업창의 조회기능은 목록조회, 상세조회로, 사용자화면 보기로 구분된다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.05  이창원          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author 이창원
 * @version 1.0
 */
@Repository("popupManageDAO")
public class PopupManageDAO {

	@Resource(name = "popupManageMapper")
	private PopupManageMapper mapper;

	/**
	 * 기 등록된 팝업창정보를 삭제한다.
	 * @param popupManageVO - 팝업창 model
	 */
	public void deletePopup(PopupManageVO popupManageVO) throws Exception {
		mapper.deletePopupManage(popupManageVO);
	}

	/**
	 * 팝업창정보를 신규로 등록한다.
	 * @param popupManageVO - 팝업창 model
	 */
	public void insertPopup(PopupManageVO popupManageVO) throws Exception {
		mapper.insertPopupManage(popupManageVO);
	}

	/**
	 * 기 등록된 팝업창정보를 수정한다.
	 * @param popupManageVO - 팝업창 model
	 */
	public void updatePopup(PopupManageVO popupManageVO) throws Exception {
		mapper.updatePopupManage(popupManageVO);
	}

	/**
	 * 팝업창을 사용자 화면에서 볼수 있는 정보들을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return popupManageVO - 팝업창 Vo
	 */
	public PopupManageVO selectPopup(PopupManageVO popupManageVO) throws Exception {
		return mapper.selectPopupManageDetail(popupManageVO);
	}

	/**
	 * 팝업창 화이트리스트를 조회한다.
	 * @return List - 팝업창 화이트 목록
	 */
	public List<EgovMap> selectPopupWhiteList() throws Exception {
		return mapper.selectPopupWhiteList();
	}

	/**
	 * 팝업창목록을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 목록
	 */
	public List<EgovMap> selectPopupList(PopupManageVO popupManageVO) throws Exception {
		return mapper.selectPopupManage(popupManageVO);
	}

	/**
	 * 팝업창목록 총개수를 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return int - 팝업창 목록 총개수
	 */
	public int selectPopupListCount(PopupManageVO popupManageVO) throws Exception {
		return mapper.selectPopupManageCnt(popupManageVO);
	}

	/**
	 * 메인 팝업창목록을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 목록
	 */
	public List<EgovMap> selectPopupMainList(PopupManageVO popupManageVO) throws Exception {
		return mapper.selectPopupManageMain(popupManageVO);
	}
}
