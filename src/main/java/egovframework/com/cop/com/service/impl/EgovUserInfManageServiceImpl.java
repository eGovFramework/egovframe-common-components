package egovframework.com.cop.com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cop.com.service.EgovUserInfManageService;
import egovframework.com.cop.com.service.UserInfVO;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 협업에서 사용할 사용자 조회 서비스 기능 구현 클래스
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
 *   2009.4.6  이삼섭          최초 생성
 *
 * </pre>
 */
@Service("EgovUserInfManageService")
public class EgovUserInfManageServiceImpl extends EgovAbstractServiceImpl implements EgovUserInfManageService {

	@Resource(name = "EgovUserInfManageDAO")
	private EgovUserInfManageDAO userInfDAO;

	/**
	 * 동호회 운영자 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectClubOprtrList(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	@Deprecated
	public Map<String, Object> selectClubOprtrList(UserInfVO userVO) throws Exception {
		List<UserInfVO> result = userInfDAO.selectClubOprtrList(userVO);
		int cnt = userInfDAO.selectClubOprtrListCnt(userVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 동호회 사용자 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectClubUserList(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	@Deprecated
	public Map<String, Object> selectClubUserList(UserInfVO userVO) throws Exception {
		List<UserInfVO> result = userInfDAO.selectClubUserList(userVO);
		int cnt = userInfDAO.selectClubUserListCnt(userVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 커뮤니티 관리자 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectCmmntyMngrList(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	@Deprecated
	public Map<String, Object> selectCmmntyMngrList(UserInfVO userVO) throws Exception {
		List<UserInfVO> result = userInfDAO.selectCmmntyMngrList(userVO);
		int cnt = userInfDAO.selectCmmntyMngrListCnt(userVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 커뮤니티 사용자 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectCmmntyUserList(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	@Deprecated
	public Map<String, Object> selectCmmntyUserList(UserInfVO userVO) throws Exception {
		List<UserInfVO> result = userInfDAO.selectCmmntyUserList(userVO);
		int cnt = userInfDAO.selectCmmntyUserListCnt(userVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 사용자 정보에 대한 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectUserList(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	public Map<String, Object> selectUserList(UserInfVO userVO) throws Exception {
		List<UserInfVO> result = userInfDAO.selectUserList(userVO);
		int cnt = userInfDAO.selectUserListCnt(userVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 동호회에 대한 모든 사용자 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectAllClubUser(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	@Deprecated
	public List<UserInfVO> selectAllClubUser(UserInfVO userVO) throws Exception {
		return userInfDAO.selectAllClubUser(userVO);
	}

	/**
	 * 커뮤니티에 대한 모든 사용자 목록을 조회한다.
	 *
	 * @see egovframework.com.cop.com.service.EgovUserInfManageService#selectAllCmmntyUser(egovframework.com.cop.com.service.UserInfVO)
	 */
	@Override
	@Deprecated
	public List<UserInfVO> selectAllCmmntyUser(UserInfVO userVO) throws Exception {
		return userInfDAO.selectAllCmmntyUser(userVO);
	}
}
