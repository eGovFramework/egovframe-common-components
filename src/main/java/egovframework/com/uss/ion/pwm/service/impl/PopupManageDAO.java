package egovframework.com.uss.ion.pwm.service.impl;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.pwm.service.PopupManageVO;

/**
 * 개요
 * - 팝업창에 대한 DAO를 정의한다.
 *
 * 상세내용
 * - 팝업창에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 팝업창의 조회기능은 목록조회, 상세조회로, 사용자화면 보기로 구분된다.
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:21:04
 */
@Repository("popupManageDAO")
public class PopupManageDAO extends EgovComAbstractDAO {

	public PopupManageDAO(){}

	/**
	 * 기 등록된 팝업창정보를 삭제한다.
	 * @param popupManage - 팝업창 model
	 * @return boolean - 반영성공 여부
	 *
	 * @param popupManage
	 */
	public void deletePopup(PopupManageVO popupManageVO) throws Exception {
	    delete("PopupManage.deletePopupManage", popupManageVO);
	}

	/**
	 * 팝업창정보를 신규로 등록한다.
	 * @param popupManage - 팝업창 model
	 * @return boolean - 반영성공 여부
	 *
	 * @param popupManage
	 */
	public void insertPopup(PopupManageVO popupManageVO) throws Exception {
	    insert("PopupManage.insertPopupManage", popupManageVO);
	}

        /**
         * 기 등록된 팝업창정보를 수정한다.
         * @param popupManage - 팝업창 model
         * @return boolean - 반영성공 여부
         *
         * @param popupManage
         */
        public void updatePopup(PopupManageVO popupManageVO) throws Exception {
            update("PopupManage.updatePopupManage", popupManageVO);
        }

	/**
	 * 팝업창을 사용자 화면에서 볼수 있는 정보들을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return popupManageVO - 팝업창 Vo
	 *
	 * @param popupManageVO
	 */
	public PopupManageVO selectPopup(PopupManageVO popupManageVO) throws Exception {
	    return (PopupManageVO)selectOne("PopupManage.selectPopupManageDetail", popupManageVO);
	}

	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창 화이트리스트를 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 화이트 목록
	 *
	 * @param popupManageVO
	 */
	public List<EgovMap> selectPopupWhiteList() throws Exception {
	    return selectList("PopupManage.selectPopupWhiteList");
	}
	
	/**
	 * 팝업창를 관리하기 위해 등록된 팝업창목록을 조회한다.
	 * @param popupManageVO - 팝업창 Vo
	 * @return List - 팝업창 목록
	 *
	 * @param popupManageVO
	 */
	public List<EgovMap> selectPopupList(PopupManageVO popupManageVO) throws Exception {
	    return selectList("PopupManage.selectPopupManage", popupManageVO);
	}

        /**
         * 팝업창를 관리하기 위해 등록된 팝업창목록 총개수를 조회한다.
         * @param popupManageVO - 팝업창 Vo
         * @return List - 팝업창 목록
         *
         * @param popupManageVO
         */
        public int selectPopupListCount(PopupManageVO popupManageVO) throws Exception {
        return (Integer)selectOne("PopupManage.selectPopupManageCnt", popupManageVO);
        }

        /**
         * 팝업창를 사용하기 위해 등록된 팝업창목록을 조회한다.
         * @param popupManageVO - 팝업창 Vo
         * @return List - 팝업창 목록
         *
         * @param popupManageVO
         */
        public List<EgovMap> selectPopupMainList(PopupManageVO popupManageVO) throws Exception {
            return selectList("PopupManage.selectPopupManageMain", popupManageVO);
        }


}