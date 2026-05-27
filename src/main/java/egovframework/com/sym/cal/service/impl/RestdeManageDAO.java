package egovframework.com.sym.cal.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.sym.cal.service.Restde;
import egovframework.com.sym.cal.service.RestdeVO;
import jakarta.annotation.Resource;

/**
 * 휴일에 대한 데이터 접근 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("RestdeManageDAO")
public class RestdeManageDAO {

	@Resource(name = "restdeManageMapper")
	private RestdeManageMapper restdeManageMapper;

	public List<EgovMap> selectNormalRestdePopup(Restde restde) { return restdeManageMapper.selectNormalRestdePopup(restde); }
	public List<EgovMap> selectAdministRestdePopup(Restde restde) { return restdeManageMapper.selectAdministRestdePopup(restde); }
	public List<EgovMap> selectNormalDayCal(Restde restde) { return restdeManageMapper.selectNormalDayCal(restde); }
	public List<EgovMap> selectNormalDayRestde(Restde restde) { return restdeManageMapper.selectNormalDayRestde(restde); }
	public List<EgovMap> selectNormalMonthRestde(Restde restde) { return restdeManageMapper.selectNormalMonthRestde(restde); }
	public List<EgovMap> selectAdministDayCal(Restde restde) { return restdeManageMapper.selectAdministDayCal(restde); }
	public List<EgovMap> selectAdministDayRestde(Restde restde) { return restdeManageMapper.selectAdministDayRestde(restde); }
	public List<EgovMap> selectAdministMonthRestde(Restde restde) { return restdeManageMapper.selectAdministMonthRestde(restde); }
	public void deleteRestde(Restde restde) { restdeManageMapper.deleteRestde(restde); }
	public void insertRestde(Restde restde) { restdeManageMapper.insertRestde(restde); }
	public Restde selectRestdeDetail(Restde restde) { return restdeManageMapper.selectRestdeDetail(restde); }
	public List<EgovMap> selectRestdeList(RestdeVO searchVO) { return restdeManageMapper.selectRestdeList(searchVO); }
	public int selectRestdeListTotCnt(RestdeVO searchVO) { return restdeManageMapper.selectRestdeListTotCnt(searchVO); }
	public void updateRestde(Restde restde) { restdeManageMapper.updateRestde(restde); }
}
