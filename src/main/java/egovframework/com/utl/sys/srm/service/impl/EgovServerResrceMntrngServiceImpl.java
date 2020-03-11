package egovframework.com.utl.sys.srm.service.impl;

import java.util.List;

import egovframework.com.utl.sys.srm.service.EgovServerResrceMntrngService;
import egovframework.com.utl.sys.srm.service.ServerResrceMntrng;
import egovframework.com.utl.sys.srm.service.ServerResrceMntrngVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 서버자원모니터링에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버자원모니터링에 대한 등록, 조회 기능을 제공한다.
 * @author lee.m.j
 * @version 1.0
 * @created 06-9-2010 오전 11:23:59
 */
@Service("egovServerResrceMntrngService")
public class EgovServerResrceMntrngServiceImpl extends EgovAbstractServiceImpl implements EgovServerResrceMntrngService {

	@Resource(name="serverResrceMntrngDAO")
	private ServerResrceMntrngDAO serverResrceMntrngDAO;
	
	   /** ID Generation */
    @Resource(name="egovServerResrceMntrngLogIdGnrService")
    private EgovIdGnrService egovServerResrceMntrngLogIdGnrService;   

	/**
	 * 서버자원모니터링의 로그정보 목록을 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return List - 서버자원모니터링의 로그 목록
	 */
	public List<ServerResrceMntrngVO> selectServerResrceMntrngList(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return serverResrceMntrngDAO.selectServerResrceMntrngList(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링의 로그정보 목록 총 갯수를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return int - 서버자원모니터링의 로그 카운트 수
	 */
	public int selectServerResrceMntrngListTotCnt(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return serverResrceMntrngDAO.selectServerResrceMntrngListTotCnt(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링 로그의 상세정보를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return ServerResrceMntrngVO - 서버자원모니터링 Vo
	 */
	public ServerResrceMntrngVO selectServerResrceMntrng(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return serverResrceMntrngDAO.selectServerResrceMntrng(serverResrceMntrngVO);
	}

	/**
	 * 서버자원모니터링 로그정보를 신규로 등록한다.
	 * @param serverResrceMntrng - 서버자원모니터링 model
	 */
	public void insertServerResrceMntrng(ServerResrceMntrng serverResrceMntrng) throws Exception {
		serverResrceMntrng.setLogId(egovServerResrceMntrngLogIdGnrService.getNextStringId());
		serverResrceMntrngDAO.insertServerResrceMntrng(serverResrceMntrng);
	}

	/**
	 * 서버자원모티너링 대상서버의 목록을 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return ServerResrceMntrngVO - 서버자원모니터링 Vo
	 */
	public List<ServerResrceMntrngVO> selectMntrngServerList(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return serverResrceMntrngDAO.selectMntrngServerList(serverResrceMntrngVO);
	}
	
	/**
	 * 서버자원모티너링 대상서버 목록 총 갯수를 조회한다.
	 * @param serverResrceMntrngVO - 서버자원모니터링 Vo
	 * @return int - 서버자원모니터링 대상서버의 카운트 수
	 */
	public int selectMntrngServerListTotCnt(ServerResrceMntrngVO serverResrceMntrngVO) throws Exception {
		return serverResrceMntrngDAO.selectMntrngServerListTotCnt(serverResrceMntrngVO);
	}	
}