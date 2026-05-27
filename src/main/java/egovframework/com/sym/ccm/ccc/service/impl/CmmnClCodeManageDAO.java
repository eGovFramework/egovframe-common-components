package egovframework.com.sym.ccm.ccc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import jakarta.annotation.Resource;

/**
 * 공통분류코드에 대한 데이터 접근 클래스
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
@Repository("CmmnClCodeManageDAO")
public class CmmnClCodeManageDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(CmmnClCodeManageDAO.class);

	@Resource(name = "cmmnClCodeManageMapper")
	private CmmnClCodeManageMapper cmmnClCodeManageMapper;

	public int selectCmmnClCodeListTotCnt(CmmnClCodeVO searchVO) {
		return cmmnClCodeManageMapper.selectCmmnClCodeListTotCnt(searchVO);
	}

	public List<CmmnClCodeVO> selectCmmnClCodeList(CmmnClCodeVO searchVO) {
		return cmmnClCodeManageMapper.selectCmmnClCodeList(searchVO);
	}

	public CmmnClCode selectCmmnClCodeDetail(CmmnClCode cmmnClCode) {
		return cmmnClCodeManageMapper.selectCmmnClCodeDetail(cmmnClCode);
	}

	public void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO) {
		LOGGER.info("TEST5 : 등록 DAO");
		cmmnClCodeManageMapper.insertCmmnClCode(cmmnClCodeVO);
	}

	public void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO) {
		cmmnClCodeManageMapper.deleteCmmnClCode(cmmnClCodeVO);
	}

	public void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO) {
		cmmnClCodeManageMapper.updateCmmnClCode(cmmnClCodeVO);
	}
}
