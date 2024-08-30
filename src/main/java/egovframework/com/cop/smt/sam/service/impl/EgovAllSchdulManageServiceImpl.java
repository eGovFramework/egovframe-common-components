package egovframework.com.cop.smt.sam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.sam.service.EgovAllSchdulManageService;

/**
 * 전체일정을 처리하는 ServiceImpl Class 구현
 * 
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *   2024.08.31  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *      </pre>
 */
@Service("egovAllSchdulManageService")
public class EgovAllSchdulManageServiceImpl extends EgovAbstractServiceImpl implements EgovAllSchdulManageService {

	@Resource(name = "allSchdulManageDao")
	private AllSchdulManageDao dao;

	/**
	 * 전체일정 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	@Override
	public List<EgovMap> selectAllSchdulManageeList(ComDefaultVO searchVO) {

		return dao.selectAllSchdulManageeList(searchVO);
	}

	/**
	 * 전체일정를(을) 목록 전체 건수를(을) 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 */
	@Override
	public int selectAllSchdulManageListCnt(ComDefaultVO searchVO) {

		return dao.selectAllSchdulManageListCnt(searchVO);
	}

}
