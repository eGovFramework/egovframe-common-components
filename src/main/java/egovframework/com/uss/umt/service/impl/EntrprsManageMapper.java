package egovframework.com.uss.umt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.umt.service.EntrprsManageVO;
import egovframework.com.uss.umt.service.EntrprsPasswordManageVO;
import egovframework.com.uss.umt.service.StplatVO;
import egovframework.com.uss.umt.service.UserDefaultVO;

/**
 * 기업회원관리에 관한 데이터 접근 Mapper 인터페이스를 정의한다.
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
@EgovMapper("entrprsManageMapper")
public interface EntrprsManageMapper {

    /**
     * 기업회원 정보를 삭제
     * @param delId 삭제대상 기업회원 아이디
     */
    void deleteEntrprs_S(String delId);

    /**
     * 기업회원의 기본정보를 데이터베이스에 저장
     * @param entrprsManageVO 기업회원 등록정보
     * @return String 등록결과
     */
    String insertEntrprs_S(EntrprsManageVO entrprsManageVO);

    /**
     * 기업회원 상세정보를 조회
     * @param entrprsmberId 상세조회대상 기업회원아이디
     * @return EntrprsManageVO 기업회원 상세정보
     */
    EntrprsManageVO selectEntrprs_S(String entrprsmberId);

    /**
     * 기업회원 기본정보를 수정
     * @param entrprsManageVO 기업회원 수정정보
     */
    void updateEntrprs_S(EntrprsManageVO entrprsManageVO);

    /**
     * 약관정보를 조회
     * @param stplatId 기업회원 약관아이디
     * @return List 기업회원약관정보
     */
    List<StplatVO> selectStplat_S(String stplatId);

    /**
     * 기업회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    void updatePassword_S(EntrprsPasswordManageVO passVO);

    /**
     * 기업회원 암호 조회
     * @param passVO 기업회원암호 조회조건정보
     * @return EntrprsPasswordManageVO 기업회원암호정보
     */
    EntrprsPasswordManageVO selectPassword_S(EntrprsPasswordManageVO passVO);

    /**
     * 기업회원 목록을 조회
     * @param userSearchVO 검색조건
     * @return List 기업회원 목록정보
     */
    List<EntrprsManageVO> selectEntrprsMberList(UserDefaultVO userSearchVO);

    /**
     * 기업회원 총 개수를 조회
     * @param userSearchVO 검색조건
     * @return int 기업회원총개수
     */
    int selectEntrprsMberListTotCnt(UserDefaultVO userSearchVO);

    /**
     * 로그인인증제한 해제
     * @param entrprsManageVO 기업회원정보
     */
    void updateLockIncorrect(EntrprsManageVO entrprsManageVO);

}
