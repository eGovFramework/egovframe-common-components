package egovframework.com.utl.sys.fsm.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.utl.sys.fsm.service.EgovFileSysMntrngService;
import egovframework.com.utl.sys.fsm.service.FileSysMntrng;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLog;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngLogVO;
import egovframework.com.utl.sys.fsm.service.FileSysMntrngVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.apache.commons.io.FileSystemUtils;
import org.springframework.stereotype.Service;

/**
 * 개요
 * 파일시스템 모니터링대상에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 파일시스템 모니터링대상에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 파일시스템 모니터링대상의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:33:26
 */
@Service("EgovFileSysMntrngService")
public class EgovFileSysMntrngServiceImpl extends EgovAbstractServiceImpl implements EgovFileSysMntrngService {
	
	@Resource(name = "FileSysMntrngDAO")
    private FileSysMntrngDAO fileSysMntrngDAO;
	
	@Resource(name="egovFileSysMntrngIdGnrService")
	private EgovIdGnrService idgenServiceFileSysMntrng;
	
	@Resource(name="egovFileSysMntrngLogIdGnrService")
	private EgovIdGnrService idgenServiceFileSysMntrngLog;
	/**
	 * 파일시스템 모니터링대상 목록을 조회한다.
	 * @param FileSysMntrngVO - 파일시스템 모니터링대상 VO
	 * @return  Map<String, Object> - 파일시스템 모니터링 List
	 * 
	 * @param fileSysMntrngVO
	 */
	public Map<String, Object> selectFileSysMntrngList(FileSysMntrngVO fileSysMntrngVO) throws Exception{
		List<FileSysMntrngVO> result = fileSysMntrngDAO.selectFileSysMntrngList(fileSysMntrngVO);
		int cnt = fileSysMntrngDAO.selectFileSysMntrngListCnt(fileSysMntrngVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 파일시스템 모니터링대상을 조회한다.
	 * @param FileSysMntrngVO - 파일시스템 모니터링대상 VO
	 * @return  FileSysMntrngVO - 파일시스템 모니터링대상 VO
	 * 
	 * @param fileSysMntrngVO
	 */
	public FileSysMntrngVO selectFileSysMntrng(FileSysMntrngVO fileSysMntrngVO) throws Exception{
		return fileSysMntrngDAO.selectFileSysMntrng(fileSysMntrngVO);
	}

	/**
	 * 파일시스템 모니터링대상을 수정한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void updateFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception{
		fileSysMntrngDAO.updateFileSysMntrng(fileSysMntrng);
	}

	/**
	 * 파일시스템 모니터링대상을 등록한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void insertFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception{
		fileSysMntrng.setFileSysId(idgenServiceFileSysMntrng.getNextStringId());
		fileSysMntrngDAO.insertFileSysMntrng(fileSysMntrng);
	}

	/**
	 * 파일시스템 모니터링대상을 삭제한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void deleteFileSysMntrng(FileSysMntrng fileSysMntrng) throws Exception{
		fileSysMntrngDAO.deleteFileSysMntrng(fileSysMntrng);
	}

	/**
	 * 파일시스템의 크기를 조회한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * @return  int
	 * 
	 * @param fileSysMntrng
	 */
	public int selectFileSysMg(FileSysMntrng fileSysMntrng) throws Exception{
		FileSystemUtils.freeSpaceKb("");
		return 0;
	}
	
	/**
	 * 파일시스템 모니터링 결과를 수정한다.
	 * @param FileSysMntrng - 파일시스템 모니터링대상 model
	 * 
	 * @param fileSysMntrng
	 */
	public void updateFileSysMntrngSttus(FileSysMntrng fileSysMntrng) throws Exception{
		fileSysMntrngDAO.updateFileSysMntrngSttus(fileSysMntrng);
		
		FileSysMntrngLog fileSysMntrngLog = new FileSysMntrngLog();
		fileSysMntrngLog.setFileSysId(fileSysMntrng.getFileSysId());
		fileSysMntrngLog.setLogId(idgenServiceFileSysMntrngLog.getNextStringId());
		fileSysMntrngLog.setFileSysNm(fileSysMntrng.getFileSysNm());
		fileSysMntrngLog.setFileSysManageNm(fileSysMntrng.getFileSysManageNm());
		fileSysMntrngLog.setFileSysMg(fileSysMntrng.getFileSysMg());
		fileSysMntrngLog.setFileSysThrhld(fileSysMntrng.getFileSysThrhld());
		fileSysMntrngLog.setFileSysUsgQty(fileSysMntrng.getFileSysUsgQty());
		fileSysMntrngLog.setLogInfo(fileSysMntrng.getLogInfo());
		fileSysMntrngLog.setMntrngSttus(fileSysMntrng.getMntrngSttus());
		fileSysMntrngLog.setCreatDt(fileSysMntrng.getCreatDt());
		insertFileSysMntrngLog(fileSysMntrngLog);
	}
	
	/**
	 * 파일시스템 모니터링로그 목록을 조회한다.
	 * @param FileSysMntrngLogVO - 파일시스템 모니터링로그 VO
	 * @return  Map<String, Object> - 파일시스템 모니터링로그 List
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public Map<String, Object> selectFileSysMntrngLogList(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception{
		List<FileSysMntrngLogVO> result = fileSysMntrngDAO.selectFileSysMntrngLogList(fileSysMntrngLogVO);
		int cnt = fileSysMntrngDAO.selectFileSysMntrngLogListCnt(fileSysMntrngLogVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 파일시스템 모니터링로그를 조회한다.
	 * @param FileSysMntrngLogVO - 파일시스템 모니터링로그 VO
	 * @return  FileSysMntrngLogVO - 파일시스템 모니터링로그 VO
	 * 
	 * @param fileSysMntrngLogVO
	 */
	public FileSysMntrngLogVO selectFileSysMntrngLog(FileSysMntrngLogVO fileSysMntrngLogVO) throws Exception{
		return fileSysMntrngDAO.selectFileSysMntrngLog(fileSysMntrngLogVO);
	}
	
	/**
	 * 파일시스템 모니터링로그를 등록한다.
	 * @param FileSysMntrngLog - 파일시스템 모니터링로그 model
	 * 
	 * @param fileSysMntrngLog
	 */
	public void insertFileSysMntrngLog(FileSysMntrngLog fileSysMntrngLog) throws Exception{
		fileSysMntrngDAO.insertFileSysMntrngLog(fileSysMntrngLog);
	}

}