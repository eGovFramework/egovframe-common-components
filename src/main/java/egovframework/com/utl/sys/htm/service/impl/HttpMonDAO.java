package egovframework.com.utl.sys.htm.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.htm.service.HttpMon;
import egovframework.com.utl.sys.htm.service.HttpMonLog;
import egovframework.com.utl.sys.htm.service.HttpMonLogVO;
import egovframework.com.utl.sys.htm.service.HttpMonVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - HTTP서비스모니터링에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:45
 */
@Repository("HttpMonDAO")
public class HttpMonDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 HTTP서비스모니터링 목록을 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 * 
	 * @param httpMonVO
	 */	
    public List<HttpMon> selectHttpMonList(HttpMonVO searchVO) throws Exception {
        return selectList("HttpMonDAO.selectHttpMonList", searchVO);
    }		
	
	/**
	 * HTTP서비스모니터링 목록 총 갯수를 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 * 
	 * @param httpMonVO
	 */
    public int selectHttpMonTotCnt(HttpMonVO searchVO) throws Exception {
        return (Integer)selectOne("HttpMonDAO.selectHttpMonTotCnt", searchVO);
    }  
	
	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 * @param httpMonVO - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 * 
	 * @param httpMonVO
	 */
	public HttpMon selectHttpMonDetail(HttpMon httpMon) throws Exception {
		return (HttpMon)selectOne("HttpMonDAO.selectHttpMonDetail", httpMon);
	}	

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	public void insertHttpMon(HttpMon httpMon) throws Exception {
        insert("HttpMonDAO.insertHttpMon", httpMon);
	}			

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	public void updateHttpMon(HttpMon httpMon) throws Exception {
		update("HttpMonDAO.updateHttpMon", httpMon);
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	public void deleteHttpMon(HttpMon httpMon) throws Exception {
		delete("HttpMonDAO.deleteHttpMon", httpMon);
	}
	
	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 * 
	 * @param httpMonVO
	 */
    public List<HttpMonLogVO> selectHttpMonLogList(HttpMonLogVO httpMonLogVO) throws Exception {
        return selectList("HttpMonDAO.selectHttpMonLogList", httpMonLogVO);
    }
	
	/**
	 * HTTP서비스모니터링로그 목록 총 갯수를 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 * 
	 * @param httpMonVO
	 */
    public int selectHttpMonLogTotCnt(HttpMonLogVO httpMonLogVO) throws Exception {
        return (Integer)selectOne("HttpMonDAO.selectHttpMonLogTotCnt", httpMonLogVO);
    }     

	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 * @param httpMonVO - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 * 
	 * @param httpMonVO
	 */
	public HttpMonLog selectHttpMonDetailLog(HttpMonLog httpMonLog) throws Exception {
		return (HttpMonLog)selectOne("HttpMonDAO.selectHttpMonDetailLog", httpMonLog);
	}	    
    
	/**
	 * HTTP서비스모니터링로그 정보를 신규로 등록한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	public void insertHttpMonLog(HttpMonLog httpMonLog) throws Exception {
        insert("HttpMonDAO.insertHttpMonLog", httpMonLog);
	}
	
	/**
	 * HTTP서비스모니터링 결과 정보를 수정한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 * 
	 * @param siteUrl
	 */
	public void updateHttpMonSttus(HttpMon httpMon) throws Exception {
		update("HttpMonDAO.updateHttpMonSttus", httpMon);
	}		
	
}
