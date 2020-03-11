package egovframework.com.utl.sys.fsm.service;

import java.util.Map;

/**
 * 개요
 * - 파일시스템 모니터링대상에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
public interface EgovFileSysMntrngService {

	/**
	 * 파일시스템 모니터링대상 목록을 조회한다.
	 * @param FileSysMntrngVO - 파일시스템 모니터링대상 VO
	 * @return  Map<String, Object> - 파일시스템 모니터링 List
	 * 
	 * @param fileSysMntrngVO
	 */
	public Map<String, Object> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO) throws Exception;

	/**
	 * 파일시스템 모니터링대상을 조회한다.
	 * @param FileSysMntrngVO - 파일시스템 모니터링대상 VO
	 * @return  FileSysMntrngVO - 파일시스템 모니터링대상 VO
	 * 
	 * @param fileSysMntrngVO
	 */
	public FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) throws Exception;

	/**
	 * 파일시스템 모니터링대상을 수정한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void updateFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception;

	/**
	 * 파일시스템 모니터링대상을 등록한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void insertFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception;

	/**
	 * 파일시스템 모니터링대상을 삭제한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void deleteFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception;

	/**
	 * 파일시스템의 크기를 조회한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * @return  int
	 * 
	 * @param fileSysMntrng
	 */
	public int selectFileSysMg(FileSysMntrng fileSysMntrng) throws Exception;
	
	/**
	 * 파일시스템 모니터링 결과를 수정한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void updateFileSysMntrngSttus(FileSysMntrng fileSysMntrng) throws Exception;
	
	/**
	 * 파일시스템 모니터링로그 목록을 조회한다.
	 * @param FileSysMntrngLogVO - 파일시스템 모니터링로그 VO
	 * @return  Map<String, Object> - 파일시스템 모니터링로그 List
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public Map<String, Object> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception;

	/**
	 * 파일시스템 모니터링로그를 조회한다.
	 * @param FileSysMntrngLogVO - 파일시스템 모니터링로그 VO
	 * @return  FileSysMntrngLogVO - 파일시스템 모니터링로그 VO
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception;
	
	/**
	 * 파일시스템 모니터링로그를 등록한다.
	 * @param FileSysMntrngLog - 파일시스템 모니터링로그 model
	 * 
	 * @param fileSysMntrngLog
	 */
	public void insertFileSysMntrngLog(FileSysMntrngLog fileSysMntrngLog) throws Exception;
}