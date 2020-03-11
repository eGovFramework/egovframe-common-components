package egovframework.com.sym.sym.bak.service;

import java.util.List;

/**
 * 백업결과관리에 대한 Service Interface를 정의한다.
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
public interface EgovBackupResultService {

	/**
	 * 백업결과을  삭제한다.
	 *
	 * @param backupResult    삭제대상 백업결과model
	 * @exception Exception Exception
	 */
	public void deleteBackupResult(BackupResult backupResult) throws Exception;

	/**
	 * 백업결과을  상세조회 한다.
	 * @return 백업결과정보
	 *
	 * @param backupResult    조회대상 백업결과model
	 * @exception Exception Exception
	 */
	public BackupResult selectBackupResult(BackupResult backupResult) throws Exception;

	/**
	 * 백업결과 목록을 조회한다.
	 * @return 백업결과목록
	 *
	 * @param searchVO    조회조건VO
	 * @exception Exception Exception
	 */
	public List<?> selectBackupResultList(BackupResult searchVO) throws Exception;

	/**
	 * 백업결과 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 * @exception Exception Exception
	 */
	public int selectBackupResultListCnt(BackupResult searchVO) throws Exception;

}