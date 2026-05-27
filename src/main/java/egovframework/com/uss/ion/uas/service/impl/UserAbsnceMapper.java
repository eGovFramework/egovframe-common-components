package egovframework.com.uss.ion.uas.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.uas.service.UserAbsnceVO;

/**
 * 사용자부재 Mapper 인터페이스
 *
 * @author 이문준
 * @since 2009.03.08
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.08  이문준          최초 생성
 *
 * </pre>
 */
@EgovMapper("userAbsnceMapper")
public interface UserAbsnceMapper {

	/**
	 * 사용자부재 목록을 조회한다.
	 * @param userAbsnceVO 사용자부재 VO
	 * @return List 사용자부재 목록
	 */
	List<UserAbsnceVO> selectUserAbsnceList(UserAbsnceVO userAbsnceVO);

	/**
	 * 사용자부재 목록 총 개수를 조회한다.
	 * @param userAbsnceVO 사용자부재 VO
	 * @return int 총 개수
	 */
	int selectUserAbsnceListTotCnt(UserAbsnceVO userAbsnceVO);

	/**
	 * 사용자부재 상세정보를 조회한다.
	 * @param userAbsnceVO 사용자부재 VO
	 * @return UserAbsnceVO 사용자부재 VO
	 */
	UserAbsnceVO selectUserAbsnce(UserAbsnceVO userAbsnceVO);

	/**
	 * 사용자부재정보를 등록한다.
	 * @param userAbsnceVO 사용자부재 VO
	 */
	void insertUserAbsnce(UserAbsnceVO userAbsnceVO);

	/**
	 * 사용자부재정보를 수정한다.
	 * @param userAbsnceVO 사용자부재 VO
	 */
	void updateUserAbsnce(UserAbsnceVO userAbsnceVO);

	/**
	 * 사용자부재정보를 삭제한다.
	 * @param userAbsnceVO 사용자부재 VO
	 */
	void deleteUserAbsnce(UserAbsnceVO userAbsnceVO);
}
