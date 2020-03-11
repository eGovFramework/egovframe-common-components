package egovframework.com.cop.smt.wmr.service;

import java.util.Map;


/**
 * 개요
 * - 주간월간보고에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 주간월간보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 주간월간보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:47
 *   <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
public interface EgovWikMnthngReprtService {
	
	/**
	 * 보고자 목록을 조회한다.
	 * @param ReportrVO
	 * @return  Map<String, Object>
	 * 
	 * @param reportrVO
	 */
	public Map<String, Object> selectReportrList(ReportrVO reportrVO) throws Exception;
	
	/**
	 * 사용자 직위명 정보를 조회한다.
	 * @param String
	 * @return  String
	 * 
	 * @param String
	 */
	public String selectWrterClsfNm(String wrterId) throws Exception;
	
	/**
	 * 주간월간보고 목록을 조회한다.
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return  Map<String, Object> - 주간월간보고 List
	 * 
	 * @param wikMnthngReprtVO
	 */
	public Map<String, Object> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO) throws Exception;

	/**
	 * 주간월간보고 정보를 조회한다.
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return  WikMnthngReprtVO - 주간월간보고 VO
	 * 
	 * @param wikMnthngReprtVO
	 */
	public WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) throws Exception;

	/**
	 * 주간월간보고 정보를 수정한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void updateWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception;

	/**
	 * 주간월간보고 정보를 등록한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void insertWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception;

	/**
	 * 주간월간보고 정보를 승인한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void confirmWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception;

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void deleteWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception;

}