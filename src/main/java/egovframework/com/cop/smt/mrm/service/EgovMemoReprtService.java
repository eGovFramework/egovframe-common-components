package egovframework.com.cop.smt.mrm.service;

import java.util.Map;

/**
 * 개요
 * - 메모보고에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 메모보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:14:53
 *  <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
public interface EgovMemoReprtService {
	/**
	 * 보고자 목록을 조회한다.
	 * @param ReportrVO
	 * @return  Map<String, Object>
	 * 
	 * @param reportrVO
	 */
	public Map<String, Object> selectReportrList(ReportrVO reportrVO) throws Exception;
	
	
	/**
	 * 사용자 직위명을 정보를 조회한다.
	 * @param String
	 * @return  String
	 * 
	 * @param String
	 */
	public String selectWrterClsfNm(String wrterId) throws Exception;
	
	/**
	 * 메모보고 목록을 조회한다.
	 * @param MemoReprtVO - 메모보고 VO
	 * @return  List<MemoReprtVO> - 메모보고 List
	 * 
	 * @param memoReprtVO
	 */
	public Map<String, Object> selectMemoReprtList(MemoReprtVO memoReprtVO) throws Exception;

	/**
	 * 메모보고 정보를 조회한다.
	 * @param MemoReprtVO - 메모보고 VO
	 * @return  MemoReprtVO - 메모보고 VO
	 * 
	 * @param memoReprtVO
	 */
	public MemoReprtVO selectMemoReprt(MemoReprtVO memoReprtVO) throws Exception;

	/**
	 * 메모보고 정보의 보고자 조회일시를 수정한다.
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	public void readMemoReprt(MemoReprt memoReprt) throws Exception;

	/**
	 * 메모보고 정보를 수정한다.
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	public void updateMemoReprt(MemoReprt memoReprt) throws Exception;

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	public void updateMemoReprtDrctMatter(MemoReprt memoReprt) throws Exception;

	/**
	 * 메모보고 정보를 등록한다.
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	public void insertMemoReprt(MemoReprt memoReprt) throws Exception;

	/**
	 * 메모보고 정보를 삭제한다.
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	public void deleteMemoReprt(MemoReprt memoReprt) throws Exception;

}