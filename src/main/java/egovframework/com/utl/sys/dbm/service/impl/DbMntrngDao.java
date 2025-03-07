package egovframework.com.utl.sys.dbm.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.dbm.service.DbMntrngLog;

/**
 * DB서비스모니터링관리에 대한 DAO 클래스를 정의한다.
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
@Repository("dbMntrngDao")
public class DbMntrngDao extends EgovComAbstractDAO {

	/**
	 * DB서비스모니터링을 삭제한다.
	 * 
	 * @param dbMntrng    삭제할 DB서비스모니터링 VO
	 * @exception Exception Exception
	 */
	public void deleteDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		delete("DbMntrngDao.deleteDbMntrng", dbMntrng);
	}

	/**
	 * DB서비스모니터링을 등록한다.
	 * 
	 * @param dbMntrng 저장할 DB서비스모니터링 VO
	 * @exception Exception Exception
	 */
	public void insertDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		insert("DbMntrngDao.insertDbMntrng", dbMntrng);
	}

	/**
	 * DB서비스모니터링로그를 등록한다.
	 * 
	 * @param dbMntrngLog 저장할 DB서비스모니터링로그 VO
	 * @exception Exception Exception
	 */
	public void insertDbMntrngLog(DbMntrngLog dbMntrngLog)
	  throws Exception{
		insert("DbMntrngDao.insertDbMntrngLog", dbMntrngLog);
	}

	/**
	 * DB서비스모니터링정보를 상세조회 한다.
	 * @return DB서비스모니터링정보
	 * 
	 * @param dbMntrng    조회할 KEY가 있는 DB서비스모니터링 VO
	 * @exception Exception Exception
	 */
	public DbMntrng selectDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		return (DbMntrng)selectOne("DbMntrngDao.selectDbMntrng", dbMntrng);
	}

	/**
	 * DB서비스모니터링로그정보를 상세조회 한다.
	 * @return DB서비스모니터링로그정보
	 * 
	 * @param dbMntrng    조회할 KEY가 있는 DB서비스모니터링로그 VO
	 * @exception Exception Exception
	 */
	public DbMntrngLog selectDbMntrngLog(DbMntrngLog dbMntrngLog)
	  throws Exception{
		return (DbMntrngLog)selectOne("DbMntrngDao.selectDbMntrngLog", dbMntrngLog);
	}

	/**
	 * DB서비스모니터링정보목록을  조회한다.
	 * @return DB서비스모니터링목록
	 * 
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */	
	public List<DbMntrng> selectDbMntrngList(DbMntrng searchVO) throws Exception{
		return selectList("DbMntrngDao.selectDbMntrngList", searchVO);
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
		return (Integer)selectOne("DbMntrngDao.selectDbMntrngListCnt", searchVO);
	}

	/**
	 * DB서비스모니터링로그정보목록을  조회한다.
	 * @return DB서비스모니터링목록
	 * 
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */	
	public List<DbMntrngLog> selectDbMntrngLogList(DbMntrngLog searchVO)
	  throws Exception{
		return selectList("DbMntrngDao.selectDbMntrngLogList", searchVO);
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
		return (Integer)selectOne("DbMntrngDao.selectDbMntrngLogListCnt", searchVO);
	}
	
	/**
	 * DB서비스모니터링정보를 수정한다.
	 * 
	 * @param dbMntrng    수정대상 DB서비스모니터링 VO
	 * @exception Exception Exception
	 */
	public void updateDbMntrng(DbMntrng dbMntrng)
	  throws Exception{
		update("DbMntrngDao.updateDbMntrng", dbMntrng);
	}

}