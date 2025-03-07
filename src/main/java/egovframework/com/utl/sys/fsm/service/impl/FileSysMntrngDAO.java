package egovframework.com.utl.sys.fsm.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.fsm.service.FileSysMntrng;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLog;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLogVO;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngVO;

/**
 * 개요
 * - 파일시스템 모니터링대상에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
@Repository("FileSysMntrngDAO")
public class FileSysMntrngDAO extends EgovComAbstractDAO {

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 대상 목록을 불러온다.
	 * @param FileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * @return List<FileSysMntrngVO> - 파일시스템모니터링 대상 List
	 * 
	 * @param fileSysMntrngVO
	 */
	public List<FileSysMntrngVO> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO) throws Exception{
		return selectList("FileSysMntrngDAO.selectFileSysMntrngList", fileSysMntrngVO);
	}

	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 대상을 불러온다.
	 * @param FileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * @return FileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * 
	 * @param fileSysMntrngVO
	 */
	public FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) throws Exception{
		return (FileSysMntrngVO)selectOne("FileSysMntrngDAO.selectFileSysMntrng", fileSysMntrngVO);
	}

	/**
	 * 파일시스템 모니터링 대상 정보를 수정한다.
	 * @param FileSysMntrng - 파일시스템모니터링 대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void updateFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception{
		update("FileSysMntrngDAO.updateFileSysMntrng", fileSysMntrng);
	}

	/**
	 * 파일시스템 모니터링 대상 정보를 등록한다.
	 * @param FileSysMntrng - 파일시스템모니터링 대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void insertFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception{
		insert("FileSysMntrngDAO.insertFileSysMntrng", fileSysMntrng);
	}

	/**
	 * 파일시스템 모니터링 대상 정보를 삭제한다.
	 * @param FileSysMntrng - 파일시스템모니터링 대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void deleteFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception{
		delete("FileSysMntrngDAO.deleteFileSysMntrng", fileSysMntrng);
	}

	/**
	 * 파일시스템 모니터링대상 목록에 대한 전체 건수를 조회한다.
	 * @param FileSysMntrngVO - 파일시스템모니터링 대상 VO
	 * @return int
	 * 
	 * @param fileSysMntrngVO
	 */
	public int selectFileSysMntrngListCnt(FileSysMntrngVO fileSysMntrngVO) throws Exception{
		return (Integer)selectOne("FileSysMntrngDAO.selectFileSysMntrngListCnt", fileSysMntrngVO);
	}
	
	/**
	 * 파일시스템 모니터링 결과 정보를 수정한다.
	 * @param FileSysMntrng - 파일시스템모니터링 대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void updateFileSysMntrngSttus(FileSysMntrng fileSysMntrng) throws Exception{
		update("FileSysMntrngDAO.updateFileSysMntrngSttus", fileSysMntrng);
	}
	
	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 로그 목록을 불러온다.
	 * @param FileSysMntrngVO - 파일시스템모니터링 로그 VO
	 * @return List<FileSysMntrngLogVO> - 파일시스템모니터링 로그 List
	 * 
	 * @param fileSysMntrngVO
	 */
	public List<FileSysMntrngLogVO> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception{
		return selectList("FileSysMntrngDAO.selectFileSysMntrngLogList", fileSysMntrngLogVO);
	}
	
	/**
	 * 파일시스템 모니터링로그 목록에 대한 전체 건수를 조회한다.
	 * @param FileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 * @return int
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public int selectFileSysMntrngLogListCnt(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception{
		return (Integer)selectOne("FileSysMntrngDAO.selectFileSysMntrngLogListCnt", fileSysMntrngLogVO);
	}
	
	/**
	 * 주어진 조건에 맞는 파일시스템모니터링 로그를 불러온다.
	 * @param FileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 * @return FileSysMntrngLogVO - 파일시스템모니터링 로그 VO
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception{
		return (FileSysMntrngLogVO)selectOne("FileSysMntrngDAO.selectFileSysMntrngLog", fileSysMntrngLogVO);
	}
	
	/**
	 * 파일시스템 모니터링 대상 정보를 등록한다.
	 * @param FileSysMntrngLog - 파일시스템모니터링 대상 model
	 * 
	 * @param fileSysMntrngLog
	 */
	public void insertFileSysMntrngLog(FileSysMntrngLog fileSysMntrngLog) throws Exception{
		insert("FileSysMntrngDAO.insertFileSysMntrngLog", fileSysMntrngLog);
	}

}