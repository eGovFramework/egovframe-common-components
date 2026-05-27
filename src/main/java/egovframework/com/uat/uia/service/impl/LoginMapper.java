package egovframework.com.uat.uia.service.impl;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cmm.LoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 Mapper 인터페이스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.06   박지욱            최초 생성
 *  2011.08.26   서준식            EsntlId를 이용한 로그인 추가
 *  2017.07.21   장동한            로그인인증제한 작업
 *  2020.07.08   신용호            비밀번호를 수정한후 경과한 날짜 조회
 *  2021.05.30   정진오            디지털원패스 인증 회원 조회
 *  </pre>
 */
@EgovMapper
public interface LoginMapper {

    /**
     * EsntlId를 이용한 로그인을 처리한다
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception;

    /**
     * 일반 로그인을 처리한다
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    LoginVO actionLogin(LoginVO vo) throws Exception;

    /**
     * 인증서 로그인을 처리한다
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    LoginVO actionCrtfctLogin(LoginVO vo) throws Exception;

    /**
     * 아이디를 찾는다.
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    LoginVO searchId(LoginVO vo) throws Exception;

    /**
     * 비밀번호를 찾는다.
     * @param vo LoginVO
     * @return LoginVO
     * @exception Exception
     */
    LoginVO searchPassword(LoginVO vo) throws Exception;

    /**
     * 변경된 비밀번호를 저장한다.
     * @param vo LoginVO
     * @exception Exception
     */
    void updatePassword(LoginVO vo) throws Exception;

    /**
     * 로그인인증제한 내역을 조회한다.
     * @param vo LoginVO
     * @return Map
     * @exception Exception
     */
    Map<?, ?> selectLoginIncorrect(LoginVO vo) throws Exception;

    /**
     * 로그인인증제한 내역을 업데이트 한다. (일반회원)
     * @param map 파라미터 Map
     * @exception Exception
     */
    void updateLoginIncorrectGNR(Map<?, ?> map) throws Exception;

    /**
     * 로그인인증제한 내역을 업데이트 한다. (기업회원)
     * @param map 파라미터 Map
     * @exception Exception
     */
    void updateLoginIncorrectENT(Map<?, ?> map) throws Exception;

    /**
     * 로그인인증제한 내역을 업데이트 한다. (업무사용자)
     * @param map 파라미터 Map
     * @exception Exception
     */
    void updateLoginIncorrectUSR(Map<?, ?> map) throws Exception;

    /**
     * 비밀번호를 수정한후 경과한 날짜를 조회한다.
     * @param vo LoginVO
     * @return int
     * @exception Exception
     */
    int selectPassedDayChangePWD(LoginVO vo) throws Exception;

    /**
     * 디지털원패스 인증 회원 조회한다.
     * @param id
     * @return LoginVO
     * @exception Exception
     */
    LoginVO onepassLogin(String id) throws Exception;

}
