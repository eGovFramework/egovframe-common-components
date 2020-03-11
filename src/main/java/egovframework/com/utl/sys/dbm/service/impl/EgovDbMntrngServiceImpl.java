package egovframework.com.utl.sys.dbm.service.impl;
import java.util.List;

import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.dbm.service.DbMntrngLog;
import egovframework.com.utl.sys.dbm.service.EgovDbMntrngService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * DB서비스모니터링관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @updated 21-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 * </pre>
 */
@Service("egovDbMntrngService")
public class EgovDbMntrngServiceImpl extends EgovAbstractServiceImpl implements EgovDbMntrngService {

	/**
	 * DB서비스모니터링DAO
	 */
	@Resource(name = "dbMntrngDao")
	private DbMntrngDao dao;

	/**
	 * DB서비스모니터링을 삭제한다.
	 * @param dbMntrng    삭제대상 DB서비스모니터링model
	 * @exception Exception Exception
	 */
	public void deleteDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		dao.deleteDbMntrng(dbMntrng);
	}

	/**
	 * DB서비스모니터링을 등록한다.
	 * @param dbMntrng    등록대상 DB서비스모니터링model
	 * @exception Exception Exception
	 */
	public void insertDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		// 상태값을 초기치로 설정한다. 
		dbMntrng.setMntrngSttus("01");
		dao.insertDbMntrng(dbMntrng);
	}

	/**
	 * DB서비스모니터링로그를 등록한다.
	 * @param dbMntrngLog    등록대상 DB서비스모니터링로그model
	 * @exception Exception Exception
	 */
	public void insertDbMntrngLog(DbMntrngLog dbMntrngLog)
	  throws Exception{
		dao.insertDbMntrngLog(dbMntrngLog);
	}

	/**
	 * DB서비스모니터링을 상세조회 한다.
	 * @return DB서비스모니터링정보
	 * 
	 * @param dbMntrng 조회대상 DB서비스모니터링model
	 * @exception Exception Exception
	 */
	public DbMntrng selectDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		return dao.selectDbMntrng(dbMntrng);
	}
	
	/**
	 * DB서비스모니터링로그을 상세조회 한다.
	 * @return DB서비스모니터링로그정보
	 * 
	 * @param dbMntrng 조회대상 DB서비스모니터링로그model
	 * @exception Exception Exception
	 */
	public DbMntrngLog selectDbMntrngLog(DbMntrngLog dbMntrngLog)
	  throws Exception{
		return dao.selectDbMntrngLog(dbMntrngLog);
	}

	/**
	 * DB서비스모니터링의 목록을 조회 한다.
	 * @return DB서비스모니터링목록
	 * 
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public List<DbMntrng> selectDbMntrngList(DbMntrng searchVO) 
	  throws Exception{
		List<DbMntrng> result = dao.selectDbMntrngList(searchVO);
		return result;
	}

	/**
	 * DB서비스모니터링 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 * 
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectDbMntrngListCnt(DbMntrng searchVO)
	  throws Exception{
		int cnt = dao.selectDbMntrngListCnt(searchVO);
		return cnt;
	}

	/**
	 * DB서비스모니터링로그의 목록을 조회 한다.
	 * @return DB서비스모니터링로그목록
	 * 
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public List<DbMntrngLog> selectDbMntrngLogList(DbMntrngLog searchVO) 
	  throws Exception{
		List<DbMntrngLog> result = dao.selectDbMntrngLogList(searchVO);
		return result;
	}

	/**
	 * DB서비스모니터링로그 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 * 
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectDbMntrngLogListCnt(DbMntrngLog searchVO)
	  throws Exception{
		int cnt = dao.selectDbMntrngLogListCnt(searchVO);
		return cnt;
	}

	/**
	 * DB서비스모니터링정보를 수정한다.
	 * 
	 * @param dbMntrng    수정대상 DB서비스모니터링model
	 * @exception Exception Exception
	 */
	public void updateDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		dao.updateDbMntrng(dbMntrng);
	}


}