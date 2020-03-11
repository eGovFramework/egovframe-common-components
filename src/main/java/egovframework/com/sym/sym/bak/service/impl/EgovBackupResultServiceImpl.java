package egovframework.com.sym.sym.bak.service.impl;
import java.util.List;

import egovframework.com.sym.sym.bak.service.BackupResult;
import egovframework.com.sym.sym.bak.service.EgovBackupResultService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 백업결과관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovBackupResultService")
public class EgovBackupResultServiceImpl extends EgovAbstractServiceImpl implements EgovBackupResultService {

	/**
	 * 백업결과DAO
	 */
	@Resource(name = "backupResultDao")
	private BackupResultDao dao;

	/**
	 * 백업결과을 삭제한다.
	 * @param backupResult    삭제대상 백업결과model
	 * @exception Exception Exception
	 */
	@Override
	public void deleteBackupResult(BackupResult backupResult)
	  throws Exception{
		dao.deleteBackupResult(backupResult);
	}

	/**
	 * 백업결과을 상세조회 한다.
	 * @return 백업결과정보
	 *
	 * @param backupResult 조회대상 백업결과model
	 * @exception Exception Exception
	 */
	@Override
	public BackupResult selectBackupResult(BackupResult backupResult)
	  throws Exception{
		return dao.selectBackupResult(backupResult);
	}

	/**
	 * 백업결과의 목록을 조회 한다.
	 * @return 백업결과목록
	 *
	 * @param searchVO 	조회정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public List<?> selectBackupResultList(BackupResult searchVO)
	  throws Exception{
		List<?> result = dao.selectBackupResultList(searchVO);
		return result;
	}

	/**
	 * 백업결과 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	@Override
	public int selectBackupResultListCnt(BackupResult searchVO)
	  throws Exception{
		int cnt = dao.selectBackupResultListCnt(searchVO);
		return cnt;
	}

}