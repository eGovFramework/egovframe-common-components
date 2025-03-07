package egovframework.com.uss.umt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageVO;

/**
 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
@Repository("userManageDAO")
public class UserManageDAO extends EgovComAbstractDAO{

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    public int checkIdDplct(String checkId){
        return (Integer)selectOne("userManageDAO.checkIdDplct_S", checkId);
    }

    /**
     * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
     * @param delId 삭제대상 업무사용자 아이디
     */
    public void deleteUser(String delId){
        delete("userManageDAO.deleteUser_S", delId);
    }


    /**
     * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param userManageVO 업무사용자 등록정보
     * @return String result 등록결과
     */
    public String insertUser(UserManageVO userManageVO){
        return String.valueOf((int)insert("userManageDAO.insertUser_S", userManageVO));
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 사용자들의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param uniqId 상세조회대상 업무사용자아이디
     * @return UserManageVO 업무사용자  상세정보
     */
    public UserManageVO selectUser(String uniqId){
        return (UserManageVO) selectOne("userManageDAO.selectUser_S", uniqId);
    }

    /**
     * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List 업무사용자 목록정보
     */
    public List<EgovMap> selectUserList(UserDefaultVO userSearchVO){
        return selectList("userManageDAO.selectUserList_S", userSearchVO);
    }

    /**
     * 사용자총 개수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 업무사용자 총개수
     */
    public int selectUserListTotCnt(UserDefaultVO userSearchVO) {
        return (Integer)selectOne("userManageDAO.selectUserListTotCnt_S", userSearchVO);
    }

    /**
     * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param userManageVO 업무사용자 수정정보
     */
    public void updateUser(UserManageVO userManageVO){
        update("userManageDAO.updateUser_S",userManageVO);
    }

    /**
     * 사용자정보 수정시 히스토리 정보를 추가
     * @param userManageVO 업무사용자 히스토리 정보
     * @return String 히스토리 등록결과
     */
    public String insertUserHistory(UserManageVO userManageVO){
    	return String.valueOf((int)insert("userManageDAO.insertUserHistory_S", userManageVO));
    }

    /**
     * 업무사용자 암호수정
     * @param passVO 업무사용자수정정보(비밀번호)
     */
    public void updatePassword(UserManageVO passVO) {
        update("userManageDAO.updatePassword_S", passVO);
    }

    /**
     * 업무사용자가 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param userManageVO 업무 사용자암호 조회조건정보
     * @return UserManageVO 업무사용자 암호정보
     */
    public UserManageVO selectPassword(UserManageVO userManageVO){
    	return (UserManageVO) selectOne("userManageDAO.selectPassword_S", userManageVO);
    }
    
    
    /**
     * 로그인인증제한 해제
     * @param passVO 업무사용자
     */
    public void updateLockIncorrect(UserManageVO userManageVO) {
        update("userManageDAO.updateLockIncorrect", userManageVO);
    }

}