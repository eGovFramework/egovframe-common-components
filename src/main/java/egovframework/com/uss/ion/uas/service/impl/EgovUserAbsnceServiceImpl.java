/**
 * 개요
 * - 사용자부재에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 사용자부재에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 사용자부재의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:09:36
 */

package egovframework.com.uss.ion.uas.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.uas.service.EgovUserAbsnceService;
import egovframework.com.uss.ion.uas.service.UserAbsnceVO;
import jakarta.annotation.Resource;

@Service("egovUserAbsnceService")
public class EgovUserAbsnceServiceImpl extends EgovAbstractServiceImpl implements EgovUserAbsnceService {

	@Resource(name="userAbsnceDAO")
	private UserAbsnceDAO userAbsnceDAO;

	/**
	 * 사용자부재정보를 관리하기 위해 등록된 사용자부재 목록을 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return List - 사용자부재 목록
	 */
	@Override
	public List<UserAbsnceVO> selectUserAbsnceList(UserAbsnceVO userAbsnceVO) throws Exception {
		return userAbsnceDAO.selectUserAbsnceList(userAbsnceVO);
	}

	/**
	 * 사용자부재정보목록 총 개수를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return int - 사용자부재 카운트 수
	 */
	@Override
	public int selectUserAbsnceListTotCnt(UserAbsnceVO userAbsnceVO) throws Exception {
		return userAbsnceDAO.selectUserAbsnceListTotCnt(userAbsnceVO);
	}

	/**
	 * 등록된 사용자부재 상세정보를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	@Override
	public UserAbsnceVO selectUserAbsnce(UserAbsnceVO userAbsnceVO) throws Exception {
		return userAbsnceDAO.selectUserAbsnce(userAbsnceVO);
	}

	/**
	 * 사용자부재정보를 신규로 등록한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	@Override
	public UserAbsnceVO insertUserAbsnce(UserAbsnceVO userAbsnceVO) throws Exception {
		userAbsnceDAO.insertUserAbsnce(userAbsnceVO);
		return selectUserAbsnce(userAbsnceVO);
	}

	/**
	 * 기 등록된 사용자부재정보를 수정한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 */
	@Override
	public void updateUserAbsnce(UserAbsnceVO userAbsnceVO) throws Exception {
		userAbsnceDAO.updateUserAbsnce(userAbsnceVO);
	}

	/**
	 * 기 등록된 사용자부재정보를 삭제한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 */
	@Override
	public void deleteUserAbsnce(UserAbsnceVO userAbsnceVO) throws Exception {
		userAbsnceDAO.deleteUserAbsnce(userAbsnceVO);
	}

	/**
	 * 사용자부재정보가 특정화면에 반영된 결과를 조회한다.
	 * @param userAbsnceVO - 사용자부재 VO
	 * @return UserAbsnceVO - 사용자부재 VO
	 */
	@Override
	public UserAbsnceVO selectUserAbsnceResult(UserAbsnceVO userAbsnceVO) throws Exception {
		return null;
	}
}
