package egovframework.com.uss.umt.service.impl;

import java.util.List;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.umt.service.EntrprsManageVO;
import egovframework.com.uss.umt.service.EntrprsPasswordManageVO;
import egovframework.com.uss.umt.service.StplatVO;
import egovframework.com.uss.umt.service.UserDefaultVO;

/**
 * 기업회원관리에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("entrprsManageDAO")
public class EntrprsManageDAO {

    @Resource(name = "entrprsManageMapper")
    private EntrprsManageMapper entrprsManageMapper;

    /**
     * 화면에 조회된 기업회원의 정보를 데이터베이스에서 삭제
     * @param delId 삭제대상 기업회원 아이디
     */
    public void deleteEntrprsmber(String delId) {
        entrprsManageMapper.deleteEntrprs_S(delId);
    }

    /**
     * 기업회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param entrprsManageVO 기업회원 등록정보
     * @return String 등록결과
     */
    public String insertEntrprsmber(EntrprsManageVO entrprsManageVO) {
        return String.valueOf(entrprsManageMapper.insertEntrprs_S(entrprsManageVO));
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param entrprsmberId 상세조회대상 기업회원아이디
     * @return EntrprsManageVO 기업회원 상세정보
     */
    public EntrprsManageVO selectEntrprsmber(String entrprsmberId) {
        return entrprsManageMapper.selectEntrprs_S(entrprsmberId);
    }

    /**
     * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param entrprsManageVO 기업회원 수정정보
     */
    public void updateEntrprsmber(EntrprsManageVO entrprsManageVO) {
        entrprsManageMapper.updateEntrprs_S(entrprsManageVO);
    }

    /**
     * 약관정보를 조회
     * @param stplatId 기업회원 약관아이디
     * @return List 기업회원약관정보
     */
    public List<StplatVO> selectStplat(String stplatId) {
        return entrprsManageMapper.selectStplat_S(stplatId);
    }

    /**
     * 기업회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    public void updatePassword(EntrprsPasswordManageVO passVO) {
        entrprsManageMapper.updatePassword_S(passVO);
    }

    /**
     * 기업회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param passVO 기업회원암호 조회조건정보
     * @return EntrprsPasswordManageVO 기업회원암호정보
     */
    public EntrprsPasswordManageVO selectPassword(EntrprsPasswordManageVO passVO) {
        return entrprsManageMapper.selectPassword_S(passVO);
    }

    /**
     * 기 등록된 특정 기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List 기업회원 목록정보
     */
    public List<EntrprsManageVO> selectEntrprsMberList(UserDefaultVO userSearchVO) {
        return entrprsManageMapper.selectEntrprsMberList(userSearchVO);
    }

    /**
     * 기업회원 총 개수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 기업회원총개수
     */
    public int selectEntrprsMberListTotCnt(UserDefaultVO userSearchVO) {
        return entrprsManageMapper.selectEntrprsMberListTotCnt(userSearchVO);
    }

    /**
     * 로그인인증제한 해제
     * @param entrprsManageVO 기업회원정보
     */
    public void updateLockIncorrect(EntrprsManageVO entrprsManageVO) {
        entrprsManageMapper.updateLockIncorrect(entrprsManageVO);
    }

}