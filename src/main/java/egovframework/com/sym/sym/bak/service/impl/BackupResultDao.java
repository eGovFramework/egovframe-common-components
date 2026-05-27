package egovframework.com.sym.sym.bak.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.sym.bak.service.BackupResult;
import jakarta.annotation.Resource;

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
public class BackupResultDao {

  @Resource(name = "backupResultMapper")
  private BackupResultMapper backupResultMapper;

  /**
   * 백업결과를 삭제한다.
   *
   * @param backupResult 삭제할 백업결과 VO
   * @exception Exception Exception
   */
  public void deleteBackupResult(BackupResult backupResult) throws Exception {
    backupResultMapper.deleteBackupResult(backupResult);
  }

  /**
   * 백업결과를 등록한다.
   *
   * @param backupResult 저장할 백업결과 VO
   * @exception Exception Exception
   */
  public void insertBackupResult(BackupResult backupResult) throws Exception {
    backupResultMapper.insertBackupResult(backupResult);
  }

  /**
   * 백업결과정보를 상세조회한다.
   *
   * @param backupResult 조회할 KEY가 있는 백업결과 VO
   * @return 백업결과정보
   * @exception Exception Exception
   */
  public BackupResult selectBackupResult(BackupResult backupResult) throws Exception {
    return backupResultMapper.selectBackupResult(backupResult);
  }

  /**
   * 백업결과정보 목록을 조회한다.
   *
   * @param searchVO 조회조건이 저장된 VO
   * @return 백업결과 목록
   * @exception Exception Exception
   */
  public List<BackupResult> selectBackupResultList(BackupResult searchVO) throws Exception {
    return backupResultMapper.selectBackupResultList(searchVO);
  }

  /**
   * 백업결과 목록 전체 건수를 조회한다.
   *
   * @param searchVO 조회할 정보가 담긴 VO
   * @return 목록건수
   * @exception Exception Exception
   */
  public int selectBackupResultListCnt(BackupResult searchVO) throws Exception {
    return backupResultMapper.selectBackupResultListCnt(searchVO);
  }

  /**
   * 백업결과정보를 수정한다.
   *
   * @param backupResult 수정대상 백업결과 VO
   * @exception Exception Exception
   */
  public void updateBackupResult(BackupResult backupResult) throws Exception {
    backupResultMapper.updateBackupResult(backupResult);
  }

}
