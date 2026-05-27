package egovframework.com.sym.ccm.acr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;
import jakarta.annotation.Resource;

/**
 * 법정동코드에 대한 데이터 접근 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("AdministCodeRecptnDAO")
public class AdministCodeRecptnDAO {

	@Resource(name = "administCodeRecptnMapper")
	private AdministCodeRecptnMapper administCodeRecptnMapper;

	public void insertAdministCodeRecptn(AdministCodeRecptn administCodeRecptn) {
		administCodeRecptnMapper.insertAdministCodeRecptn(administCodeRecptn);
	}

	public void insertAdministCode(AdministCodeRecptn administCodeRecptn) {
		administCodeRecptnMapper.insertAdministCode(administCodeRecptn);
	}

	public void updateAdministCode(AdministCodeRecptn administCodeRecptn) {
		administCodeRecptnMapper.updateAdministCode(administCodeRecptn);
	}

	public void deleteAdministCode(AdministCodeRecptn administCodeRecptn) {
		administCodeRecptnMapper.deleteAdministCode(administCodeRecptn);
	}

	public AdministCodeRecptn selectAdministCodeDetail(AdministCodeRecptn administCodeRecptn) {
		return administCodeRecptnMapper.selectAdministCodeDetail(administCodeRecptn);
	}

	public List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO searchVO) {
		return administCodeRecptnMapper.selectAdministCodeRecptnList(searchVO);
	}

	public int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO) {
		return administCodeRecptnMapper.selectAdministCodeRecptnListTotCnt(searchVO);
	}

	public List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO searchVO) {
		return administCodeRecptnMapper.selectAdministCodeList(searchVO);
	}

	public int selectAdministCodeListTotCnt(AdministCodeRecptnVO searchVO) {
		return administCodeRecptnMapper.selectAdministCodeListTotCnt(searchVO);
	}
}
