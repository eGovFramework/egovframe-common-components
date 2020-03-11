package egovframework.com.uss.ion.tir.service;

import java.util.Map;

import twitter4j.Status;
/**
 * 트위터송신을 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovTwitterTrnsmitService {


    /**
	 * 트위터를 송신하다.
	 * @param sTwitterId 	-트위터 아이디
	 * @param sTwitterPw 	-트위터 비밀번호
	 * @param sTwitterText 	-트위터 등록 메세지
	 */
	public Status twitterTrnsmitRegist(Map<?, ?> map, String sTwitterText) throws Exception;

    /**
     * 트위터 계정을 건수를 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return Map - 조회 정보가 담긴 Map
     * @throws Exception
     */
    public Map<?, ?> selectTwitterAccount(Map<?, ?> param) throws Exception;

	/**
     * 트위터 계정을 건수를 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return int - 조회 정보가 담긴 Integer
     * @throws Exception
     */
    public int selectTwitterAccountCheck(Map<?, ?> param) throws Exception;

	/**
	 * 트위터 계정을 신규로 등록한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	public void insertTwitterAccount(Map<?, ?> param) throws Exception;

	/**
	 * 트위터 계정을 수정한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	public void updtTwitterAccount(Map<?, ?> param) throws Exception;

	/**
	 * 트위터 계정을 삭제한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	public void deleteTwitterAccount(Map<?, ?> param) throws Exception;

}
