package egovframework.com.uss.umt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.umt.service.EmplyrManageVO;
import egovframework.com.uss.umt.service.EmplyrPasswordManageVO;
import egovframework.com.uss.umt.service.UserDefaultVO;

/**
 * 사용자관리에 관한 데이터 접근 Mapper 인터페이스를 정의한다.
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
 *   2017.07.21  장동한          로그인인증제한 작업
 *   2026.05.28  표준프레임워크센터   @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("emplyrManageMapper")
public interface EmplyrManageMapper {

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수)
     */
    int checkIdDplct_S(String checkId);

    /**
     * 사용자 정보변경 이력을 삭제
     * @param delId 삭제대상 업무사용자 아이디
     */
    void deleteEmplyrInfoChangeDtls_S(String delId);

    /**
     * 업무사용자 정보를 삭제
     * @param delId 삭제대상 업무사용자 아이디
     */
    void deleteEmplyr_S(String delId);

    /**
     * 사용자의 기본정보를 데이터베이스에 저장
     * @param emplyrManageVO 업무사용자 등록정보
     * @return String 등록결과
     */
    String insertEmplyr_S(EmplyrManageVO emplyrManageVO);

    /**
     * 사용자 상세정보를 조회
     * @param uniqId 상세조회대상 업무사용자아이디
     * @return EmplyrManageVO 업무사용자 상세정보
     */
    EmplyrManageVO selectEmplyr_S(String uniqId);

    /**
     * 업무사용자 목록을 조회
     * @param userSearchVO 검색조건
     * @return List 업무사용자 목록정보
     */
    List<EgovMap> selectEmplyrList_S(UserDefaultVO userSearchVO);

    /**
     * 업무사용자 총 개수를 조회
     * @param userSearchVO 검색조건
     * @return int 업무사용자 총개수
     */
    int selectEmplyrListTotCnt_S(UserDefaultVO userSearchVO);

    /**
     * 사용자의 기본정보를 수정
     * @param emplyrManageVO 업무사용자 수정정보
     */
    void updateEmplyr_S(EmplyrManageVO emplyrManageVO);

    /**
     * 사용자정보 수정시 히스토리 정보를 추가
     * @param emplyrManageVO 업무사용자 히스토리 정보
     * @return String 히스토리 등록결과
     */
    String insertEmplyrHistory_S(EmplyrManageVO emplyrManageVO);

    /**
     * 업무사용자 암호수정
     * @param emplyrPasswordManageVO 업무사용자 비밀번호 수정정보
     */
    void updatePassword_S(EmplyrPasswordManageVO emplyrPasswordManageVO);

    /**
     * 업무사용자 암호 조회
     * @param emplyrPasswordManageVO 업무사용자 암호 조회조건정보
     * @return EmplyrPasswordManageVO 업무사용자 암호정보
     */
    EmplyrPasswordManageVO selectPassword_S(EmplyrPasswordManageVO emplyrPasswordManageVO);

    /**
     * 로그인인증제한 해제
     * @param emplyrManageVO 업무사용자
     */
    void updateLockIncorrect(EmplyrManageVO emplyrManageVO);

}
