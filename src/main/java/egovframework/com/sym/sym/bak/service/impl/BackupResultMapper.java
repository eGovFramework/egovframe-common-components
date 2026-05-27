package egovframework.com.sym.sym.bak.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.sym.bak.service.BackupResult;

/**
 * 백업결과관리에 대한 Mapper 인터페이스를 정의한다.
 *
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 * </pre>
 */
@EgovMapper("backupResultMapper")
public interface BackupResultMapper {

  /**
   * 백업결과를 삭제한다.
   *
   * @param backupResult 삭제할 백업결과 VO
   */
  void deleteBackupResult(BackupResult backupResult);

  /**
   * 백업결과를 등록한다.
   *
   * @param backupResult 저장할 백업결과 VO
   */
  void insertBackupResult(BackupResult backupResult);

  /**
   * 백업결과정보를 상세조회한다.
   *
   * @param backupResult 조회할 KEY가 있는 백업결과 VO
   * @return 백업결과정보
   */
  BackupResult selectBackupResult(BackupResult backupResult);

  /**
   * 백업결과정보 목록을 조회한다.
   *
   * @param searchVO 조회조건이 저장된 VO
   * @return 백업결과 목록
   */
  List<BackupResult> selectBackupResultList(BackupResult searchVO);

  /**
   * 백업결과 목록 전체 건수를 조회한다.
   *
   * @param searchVO 조회할 정보가 담긴 VO
   * @return 목록건수
   */
  int selectBackupResultListCnt(BackupResult searchVO);

  /**
   * 백업결과정보를 수정한다.
   *
   * @param backupResult 수정대상 백업결과 VO
   */
  void updateBackupResult(BackupResult backupResult);

}
