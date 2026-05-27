package egovframework.com.sym.ccm.cca.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import jakarta.annotation.Resource;

/**
 * 공통코드에 대한 데이터 접근 클래스
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
@Repository("CmmnCodeManageDAO")
public class CmmnCodeManageDAO {

	@Resource(name = "cmmnCodeManageMapper")
	private CmmnCodeManageMapper cmmnCodeManageMapper;

	public int selectCmmnCodeListTotCnt(CmmnCodeVO searchVO) {
		return cmmnCodeManageMapper.selectCmmnCodeListTotCnt(searchVO);
	}

	public List<CmmnCodeVO> selectCmmnCodeList(CmmnCodeVO searchVO) {
		return cmmnCodeManageMapper.selectCmmnCodeList(searchVO);
	}

	public CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCodeVO) {
		return cmmnCodeManageMapper.selectCmmnCodeDetail(cmmnCodeVO);
	}

	public void updateCmmnCode(CmmnCode cmmnCode) {
		cmmnCodeManageMapper.updateCmmnCode(cmmnCode);
	}

	public void insertCmmnCode(CmmnCode cmmnCode) {
		cmmnCodeManageMapper.insertCmmnCode(cmmnCode);
	}

	public void deleteCmmnCode(CmmnCode cmmnCode) {
		cmmnCodeManageMapper.deleteCmmnCode(cmmnCode);
	}
}
