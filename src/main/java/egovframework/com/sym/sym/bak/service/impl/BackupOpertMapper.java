package egovframework.com.sym.sym.bak.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.sym.bak.service.BackupOpert;
import egovframework.com.sym.sym.bak.service.BackupSchdulDfk;

/**
 * 백업작업관리에 대한 Mapper 인터페이스를 정의한다.
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
@EgovMapper("backupOpertMapper")
public interface BackupOpertMapper {

  /**
   * 백업작업을 삭제한다(논리 삭제).
   *
   * @param backupOpert 삭제할 백업작업 VO
   */
  void deleteBackupOpert(BackupOpert backupOpert);

  /**
   * 백업스케줄요일정보를 삭제한다.
   *
   * @param backupOpertId 삭제할 백업작업 ID
   */
  void deleteBackupSchdulDfk(String backupOpertId);

  /**
   * 백업작업을 등록한다.
   *
   * @param backupOpert 저장할 백업작업 VO
   */
  void insertBackupOpert(BackupOpert backupOpert);

  /**
   * 백업스케줄요일정보를 등록한다.
   *
   * @param backupSchdulDfk 저장할 스케줄요일 VO
   */
  void insertBackupSchdulDfk(BackupSchdulDfk backupSchdulDfk);

  /**
   * 백업작업정보를 상세조회한다.
   *
   * @param backupOpert 조회할 KEY가 있는 백업작업 VO
   * @return 백업작업정보
   */
  BackupOpert selectBackupOpert(BackupOpert backupOpert);

  /**
   * 백업스케줄요일 목록을 조회한다.
   *
   * @param backupOpertId 조회할 백업작업 ID
   * @return 스케줄요일 목록
   */
  List<BackupSchdulDfk> selectBackupSchdulDfkList(String backupOpertId);

  /**
   * 백업작업정보 목록을 조회한다.
   *
   * @param searchVO 조회조건이 저장된 VO
   * @return 백업작업 목록
   */
  List<BackupOpert> selectBackupOpertList(BackupOpert searchVO);

  /**
   * 백업작업 목록 전체 건수를 조회한다.
   *
   * @param searchVO 조회할 정보가 담긴 VO
   * @return 목록건수
   */
  int selectBackupOpertListCnt(BackupOpert searchVO);

  /**
   * 백업작업정보를 수정한다.
   *
   * @param backupOpert 수정대상 백업작업 VO
   */
  void updateBackupOpert(BackupOpert backupOpert);

}
