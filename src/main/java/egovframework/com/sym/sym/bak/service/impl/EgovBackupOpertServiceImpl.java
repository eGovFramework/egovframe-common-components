package egovframework.com.sym.sym.bak.service.impl;
import java.util.List;

import egovframework.com.sym.sym.bak.service.BackupOpert;
import egovframework.com.sym.sym.bak.service.BackupResult;
import egovframework.com.sym.sym.bak.service.EgovBackupOpertService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 백업작업관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovBackupOpertService")
public class EgovBackupOpertServiceImpl extends EgovAbstractServiceImpl implements EgovBackupOpertService {

	/**
	 * 백업작업DAO
	 */
	@Resource(name = "backupOpertDao")
	private BackupOpertDao backupOpertDao;

	/**
	 * 백업결과DAO
	 */
	@Resource(name = "backupResultDao")
	private BackupResultDao backupResultDao;

	/**
	 * 백업작업을 삭제한다.
	 * @param backupOpert    삭제대상 백업작업model
	 * @exception Exception Exception
	 */
	@Override
	public void deleteBackupOpert(BackupOpert backupOpert)
	  throws Exception{
		backupOpertDao.deleteBackupOpert(backupOpert);
	}

	/**
	 * 백업작업을 등록한다.
	 * @param backupOpert    등록대상 백업작업model
	 * @exception Exception Exception
	 */
	@Override
	public void insertBackupOpert(BackupOpert backupOpert)
	  throws Exception{
		backupOpertDao.insertBackupOpert(backupOpert);
	}

	/**
	 * 백업작업을 상세조회 한다.
	 * @return 백업작업정보
	 *
	 * @param backupOpert 조회대상 백업작업model
	 * @exception Exception Exception
	 */
	@Override
	public BackupOpert selectBackupOpert(BackupOpert backupOpert)
	  throws Exception{
		return backupOpertDao.selectBackupOpert(backupOpert);
	}

	/**
	 * 백업작업의 목록을 조회 한다.
	 * @return 백업작업목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public List<?> selectBackupOpertList(BackupOpert searchVO)
	  throws Exception{
		List<?> result = backupOpertDao.selectBackupOpertList(searchVO);
		return result;
	}

	/**
	 * 백업작업 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectBackupOpertListCnt(BackupOpert searchVO)
	  throws Exception{
		int cnt = backupOpertDao.selectBackupOpertListCnt(searchVO);
		return cnt;
	}

	/**
	 * 백업작업정보를 수정한다.
	 *
	 * @param backupOpert    수정대상 백업작업model
	 * @exception Exception Exception
	 */
	@Override
	public void updateBackupOpert(BackupOpert backupOpert)
	  throws Exception{
		backupOpertDao.updateBackupOpert(backupOpert);
	}

	/**
	 * 백업결과를 등록한다.
	 * @param backupResult    등록대상 백업결과model
	 * @exception Exception Exception
	 */
	@Override
	public void insertBackupResult(BackupResult backupResult)
	  throws Exception{
		backupResultDao.insertBackupResult(backupResult);
	}

	/**
	 * 백업결과정보를 수정한다.
	 *
	 * @param backupResult    수정대상 백업결과model
	 * @exception Exception Exception
	 */
	@Override
	public void updateBackupResult(BackupResult backupResult)
	  throws Exception{
		backupResultDao.updateBackupResult(backupResult);
	}

}