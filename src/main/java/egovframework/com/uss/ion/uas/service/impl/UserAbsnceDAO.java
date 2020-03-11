/**
 * 개요
 * - 사용자부재에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 사용자부재에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 사용자부재의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:09:35
 */

package egovframework.com.uss.ion.uas.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.uas.service.UserAbsnce;
import egovframework.com.uss.ion.uas.service.UserAbsnceVO;

import org.springframework.stereotype.Repository;

@Repository("userAbsnceDAO")
public class UserAbsnceDAO extends EgovComAbstractDAO {
	
	/**
	 * 사용자부재정보를 관리하기 위해 등록된 사용자부재 목록을 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return List - 사용자부재 목록
	 */	
	public List<UserAbsnceVO> selectUserAbsnceList(UserAbsnceVO userAbsnceVO) throws Exception {
		return selectList("userAbsnceDAO.selectUserAbsnceList", userAbsnceVO);
	}

    /**
	 * 사용자부재목록 총 갯수를 조회한다.
	 * @param mainImageVO - 사용자부재 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectUserAbsnceListTotCnt(UserAbsnceVO userAbsnceVO) throws Exception {
        return (Integer)selectOne("userAbsnceDAO.selectUserAbsnceListTotCnt", userAbsnceVO);
    }
    
	/**
	 * 등록된 사용자부재 상세정보를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	public UserAbsnceVO selectUserAbsnce(UserAbsnceVO userAbsnceVO) throws Exception {
		return (UserAbsnceVO) selectOne("userAbsnceDAO.selectUserAbsnce", userAbsnceVO);
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * @param userAbsnce - 사용자부재 model
	 */
	public void insertUserAbsnce(UserAbsnce userAbsnce) throws Exception {
		insert("userAbsnceDAO.insertUserAbsnce", userAbsnce);
	}

	/**
	 * 기 등록된 사용자부재정보를 수정한다.
	 * @param userAbsnce - 사용자부재 model
	 */
	public void updateUserAbsnce(UserAbsnce userAbsnce) throws Exception {
		update("userAbsnceDAO.updateUserAbsnce", userAbsnce);
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * @param userAbsnce - 사용자부재 model
	 */
	public void deleteUserAbsnce(UserAbsnce userAbsnce) throws Exception {
		delete("userAbsnceDAO.deleteUserAbsnce", userAbsnce);
	}

	/**
	 * 사용자부재정보가 특정화면에 반영된 결과를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	public UserAbsnceVO selectUserAbsnceResult(UserAbsnceVO userAbsnceVO) throws Exception {
		return null;
	}
}
