package egovframework.com.sym.ccm.adc.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.sym.ccm.adc.service.AdministCode;
import egovframework.com.sym.ccm.adc.service.AdministCodeVO;
import jakarta.annotation.Resource;

/**
 * 행정코드에 대한 데이터 접근 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("AdministCodeManageDAO")
public class AdministCodeManageDAO {

	@Resource(name = "administCodeManageMapper")
	private AdministCodeManageMapper administCodeManageMapper;

	public void deleteAdministCode(AdministCode administCode) {
		administCodeManageMapper.deleteAdministCode(administCode);
	}

	public void insertAdministCode(AdministCode administCode) {
		administCodeManageMapper.insertAdministCode(administCode);
	}

	public AdministCode selectAdministCodeDetail(AdministCode administCode) {
		return administCodeManageMapper.selectAdministCodeDetail(administCode);
	}

	public List<EgovMap> selectAdministCodeList(AdministCodeVO searchVO) {
		return administCodeManageMapper.selectAdministCodeList(searchVO);
	}

	public int selectAdministCodeListTotCnt(AdministCodeVO searchVO) {
		return administCodeManageMapper.selectAdministCodeListTotCnt(searchVO);
	}

	public void updateAdministCode(AdministCode administCode) {
		administCodeManageMapper.updateAdministCode(administCode);
	}
}
