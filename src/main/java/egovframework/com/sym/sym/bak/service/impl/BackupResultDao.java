package egovframework.com.sym.sym.bak.service.impl;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.sym.bak.service.BackupResult;

import org.springframework.stereotype.Repository;

/**
 * 백업결과관리에 대한 DAO 클래스를 정의한다.
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
@Repository("backupResultDao")
public class BackupResultDao extends EgovComAbstractDAO {

	/**
	 * 백업결과을 삭제한다.
	 *
	 * @param backupResult    삭제할 백업결과 VO
	 * @exception Exception Exception
	 */
	public void deleteBackupResult(BackupResult backupResult)
	  throws Exception{
		delete("BackupResultDao.deleteBackupResult", backupResult);
	}

	/**
	 * 백업결과을 등록한다.
	 *
	 * @param backupResult 저장할 백업결과 VO
	 * @exception Exception Exception
	 */
	public void insertBackupResult(BackupResult backupResult)
	  throws Exception{
		insert("BackupResultDao.insertBackupResult", backupResult);
	}

	/**
	 * 백업결과정보를 상세조회 한다.
	 * @return 백업결과정보
	 *
	 * @param backupResult    조회할 KEY가 있는 백업결과 VO
	 * @exception Exception Exception
	 */
	public BackupResult selectBackupResult(BackupResult backupResult)
	  throws Exception{
		return (BackupResult)selectOne("BackupResultDao.selectBackupResult", backupResult);
	}

	/**
	 * 백업결과정보목록을  조회한다.
	 * @return 백업결과목록
	 *
	 * @param searchVO    조회조건이 저장된 VO
	 * @exception Exception Exception
	 */
	public List<?> selectBackupResultList(BackupResult searchVO)
	  throws Exception{
		return selectList("BackupResultDao.selectBackupResultList", searchVO);
	}

	/**
	 * 백업결과 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectBackupResultListCnt(BackupResult searchVO)
	  throws Exception{
		return (Integer)selectOne("BackupResultDao.selectBackupResultListCnt", searchVO);
	}

	/**
	 * 백업결과정보를 수정한다.
	 *
	 * @param backupResult    수정대상 백업결과 VO
	 * @exception Exception Exception
	 */
	public void updateBackupResult(BackupResult backupResult)
	  throws Exception{
		update("BackupResultDao.updateBackupResult", backupResult);
	}

}