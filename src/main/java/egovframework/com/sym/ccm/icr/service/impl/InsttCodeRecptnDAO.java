package egovframework.com.sym.ccm.icr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.sym.ccm.icr.service.InsttCodeRecptn;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptnVO;
import jakarta.annotation.Resource;

/**
 * 기관코드에 대한 데이터 접근 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("InsttCodeRecptnDAO")
public class InsttCodeRecptnDAO {

	@Resource(name = "insttCodeRecptnMapper")
	private InsttCodeRecptnMapper insttCodeRecptnMapper;

	public void insertInsttCodeRecptn(InsttCodeRecptn insttCodeRecptn) { insttCodeRecptnMapper.insertInsttCodeRecptn(insttCodeRecptn); }
	public void insertInsttCode(InsttCodeRecptn insttCodeRecptn) { insttCodeRecptnMapper.insertInsttCode(insttCodeRecptn); }
	public void updateInsttCode(InsttCodeRecptn insttCodeRecptn) { insttCodeRecptnMapper.updateInsttCode(insttCodeRecptn); }
	public void deleteInsttCode(InsttCodeRecptn insttCodeRecptn) { insttCodeRecptnMapper.deleteInsttCode(insttCodeRecptn); }
	public InsttCodeRecptn selectInsttCodeDetail(InsttCodeRecptn insttCodeRecptn) { return insttCodeRecptnMapper.selectInsttCodeDetail(insttCodeRecptn); }
	public List<EgovMap> selectInsttCodeRecptnList(InsttCodeRecptnVO searchVO) { return insttCodeRecptnMapper.selectInsttCodeRecptnList(searchVO); }
	public int selectInsttCodeRecptnListTotCnt(InsttCodeRecptnVO searchVO) { return insttCodeRecptnMapper.selectInsttCodeRecptnListTotCnt(searchVO); }
	public List<EgovMap> selectInsttCodeList(InsttCodeRecptnVO searchVO) { return insttCodeRecptnMapper.selectInsttCodeList(searchVO); }
	public int selectInsttCodeListTotCnt(InsttCodeRecptnVO searchVO) { return insttCodeRecptnMapper.selectInsttCodeListTotCnt(searchVO); }
}
