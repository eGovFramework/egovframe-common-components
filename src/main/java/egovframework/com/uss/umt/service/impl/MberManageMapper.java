package egovframework.com.uss.umt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.MberPasswordManageVO;
import egovframework.com.uss.umt.service.StplatVO;
import egovframework.com.uss.umt.service.UserDefaultVO;

/**
 * 일반회원관리에 관한 데이터 접근 Mapper 인터페이스를 정의한다.
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
@EgovMapper("mberManageMapper")
public interface MberManageMapper {

    /**
     * 일반회원 목록을 조회
     * @param userSearchVO 검색조건
     * @return List 일반회원 목록정보
     */
    List<MberManageVO> selectMberList(UserDefaultVO userSearchVO);

    /**
     * 일반회원 총 개수를 조회
     * @param userSearchVO 검색조건
     * @return int 일반회원총개수
     */
    int selectMberListTotCnt(UserDefaultVO userSearchVO);

    /**
     * 일반회원 정보를 삭제
     * @param delId 삭제 대상 일반회원아이디
     */
    void deleteMber_S(String delId);

    /**
     * 일반회원의 기본정보를 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return String 등록결과
     */
    String insertMber_S(MberManageVO mberManageVO);

    /**
     * 일반회원 상세정보를 조회
     * @param mberId 상세조회대상 일반회원아이디
     * @return MberManageVO 일반회원 상세정보
     */
    MberManageVO selectMber_S(String mberId);

    /**
     * 일반회원 기본정보를 수정
     * @param mberManageVO 일반회원수정정보
     */
    void updateMber_S(MberManageVO mberManageVO);

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return List 일반회원약관정보
     */
    List<StplatVO> selectStplat_S(String stplatId);

    /**
     * 일반회원 암호수정
     * @param mberPasswordManageVO 일반회원 비밀번호 수정정보
     */
    void updatePassword_S(MberPasswordManageVO mberPasswordManageVO);

    /**
     * 일반회원 암호 조회
     * @param mberPasswordManageVO 일반회원 암호 조회조건정보
     * @return MberPasswordManageVO 일반회원 암호정보
     */
    MberPasswordManageVO selectPassword_S(MberPasswordManageVO mberPasswordManageVO);

    /**
     * 로그인인증제한 해제
     * @param mberManageVO 일반회원정보
     */
    void updateLockIncorrect(MberManageVO mberManageVO);

}
