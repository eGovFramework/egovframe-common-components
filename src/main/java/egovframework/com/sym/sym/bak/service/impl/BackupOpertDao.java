package egovframework.com.sym.sym.bak.service.impl;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.sym.bak.service.BackupOpert;
import egovframework.com.sym.sym.bak.service.BackupSchdulDfk;

/**
 * 백업작업관리에 대한 DAO 클래스를 정의한다.
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
@Repository("backupOpertDao")
public class BackupOpertDao extends EgovComAbstractDAO {

	/**
	 * 백업작업을 삭제한다.
	 *
	 * @param backupOpert    삭제할 백업작업 VO
	 */
	public void deleteBackupOpert(BackupOpert backupOpert) {
		// slave 테이블 삭제
		delete("BackupOpertDao.deleteBackupSchdulDfk", backupOpert.getBackupOpertId());
		// master 테이블 삭제
		delete("BackupOpertDao.deleteBackupOpert", backupOpert);

	}

	/**
	 * 백업작업을 등록한다.
	 *
	 * @param backupOpert 저장할 백업작업 VO
	 */
	public void insertBackupOpert(BackupOpert backupOpert) {
		// master 테이블 인서트
		insert("BackupOpertDao.insertBackupOpert", backupOpert);
		// slave 테이블 인서트
		if (backupOpert.getExecutSchdulDfkSes() != null && backupOpert.getExecutSchdulDfkSes().length != 0) {
			String backupOpertId = backupOpert.getBackupOpertId();
			String [] dfkSes = backupOpert.getExecutSchdulDfkSes();
			for (String element : dfkSes) {
				BackupSchdulDfk backupSchdulDfk = new BackupSchdulDfk();
				backupSchdulDfk.setBackupOpertId(backupOpertId);
				backupSchdulDfk.setExecutSchdulDfkSe(element);
				insert("BackupOpertDao.insertBackupSchdulDfk", backupSchdulDfk);
			}
		}

	}

	/**
	 * 백업작업정보를 상세조회 한다.
	 * @return 백업작업정보
	 *
	 * @param backupOpert    조회할 KEY가 있는 백업작업 VO
	 */
	public BackupOpert selectBackupOpert(BackupOpert backupOpert) {
		BackupOpert result = (BackupOpert)selectOne("BackupOpertDao.selectBackupOpert", backupOpert);
		// 스케줄요일정보를 가져온다.
		List<BackupSchdulDfk> dfkSeList = selectList("BackupOpertDao.selectBackupSchdulDfkList", result.getBackupOpertId());
		String [] dfkSes = new String [dfkSeList.size()];
		for (int j = 0; j < dfkSeList.size(); j++) {
			dfkSes[j] = dfkSeList.get(j).getExecutSchdulDfkSe();
		}
		result.setExecutSchdulDfkSes(dfkSes);
		// 화면표시용 실행스케줄 속성을 만든다.
		result.makeExecutSchdul(dfkSeList);

		return result ;
	}

	/**
     * 백업작업정보목록을 조회한다.
     *
     * @return 백업작업목록
     *
     * @param searchVO 조회조건이 저장된 VO
     */
    public List<BackupOpert> selectBackupOpertList(BackupOpert searchVO) {
        List<BackupOpert> resultList = selectList("BackupOpertDao.selectBackupOpertList", searchVO);
        if (resultList.isEmpty()) {
            return resultList;
        }

        // 스케줄요일정보를 백업작업ID 목록으로 한번에 조회한 뒤 ID별로 그룹핑한다. (행별 조회로 인한 N+1 제거)
        List<String> backupOpertIds = resultList.stream()
                .map(BackupOpert::getBackupOpertId).collect(Collectors.toList());
        List<BackupSchdulDfk> dfkList = selectList("BackupOpertDao.selectBackupSchdulDfkListByIds", backupOpertIds);
        Map<String, List<BackupSchdulDfk>> dfkMap = dfkList.stream()
                .collect(Collectors.groupingBy(BackupSchdulDfk::getBackupOpertId));

        for (BackupOpert result : resultList) {
            // 스케줄요일정보를 가져온다.
            List<BackupSchdulDfk> dfkSeList = dfkMap.getOrDefault(result.getBackupOpertId(), Collections.emptyList());
            result.setExecutSchdulDfkSes(
                    dfkSeList.stream().map(BackupSchdulDfk::getExecutSchdulDfkSe).toArray(String[]::new));
            // 화면표시용 실행스케줄 속성을 만든다.
            result.makeExecutSchdul(dfkSeList);
        }
        return resultList;
    }

	/**
	 * 백업작업 목록 전체 건수를(을) 조회한다.
	 * @return 목록건수
	 *
	 * @param searchVO    조회할 정보가 담긴 VO
	 */
	public int selectBackupOpertListCnt(BackupOpert searchVO) {
		return (Integer)selectOne("BackupOpertDao.selectBackupOpertListCnt", searchVO);
	}

	/**
	 * 백업작업정보를 수정한다.
	 *
	 * @param backupOpert    수정대상 백업작업 VO
	 */
	public void updateBackupOpert(BackupOpert backupOpert) {
		update("BackupOpertDao.updateBackupOpert", backupOpert);
		// slave 테이블 삭제
		delete("BackupOpertDao.deleteBackupSchdulDfk", backupOpert.getBackupOpertId());
		// slave 테이블 인서트
		if (backupOpert.getExecutSchdulDfkSes() != null && backupOpert.getExecutSchdulDfkSes().length != 0) {
			String backupOpertId = backupOpert.getBackupOpertId();
			String [] dfkSes = backupOpert.getExecutSchdulDfkSes();
			for (String element : dfkSes) {
				BackupSchdulDfk backupSchdulDfk = new BackupSchdulDfk();
				backupSchdulDfk.setBackupOpertId(backupOpertId);
				backupSchdulDfk.setExecutSchdulDfkSe(element);
				insert("BackupOpertDao.insertBackupSchdulDfk", backupSchdulDfk);
			}
		}
	}

}