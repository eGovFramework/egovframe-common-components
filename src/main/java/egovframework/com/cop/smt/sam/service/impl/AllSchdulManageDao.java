package egovframework.com.cop.smt.sam.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.annotation.Resource;

/**
 * 전체일정을 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한         최초 생성
 *   2016.08.01  장동한         표준프레임워크 v3.6 개선
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * </pre>
 */
@Repository("allSchdulManageDao")
public class AllSchdulManageDao {

	@Resource(name = "allSchdulManageMapper")
	private AllSchdulManageMapper mapper;

	/**
	 * 전체일정 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectAllSchdulManageeList(ComDefaultVO searchVO) throws Exception {
		return mapper.selectIndvdlSchdulManage(searchVO);
	}

	/**
	 * 전체일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectAllSchdulManageListCnt(ComDefaultVO searchVO) throws Exception {
		return mapper.selectIndvdlSchdulManageCnt(searchVO);
	}
}
