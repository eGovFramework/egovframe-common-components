package egovframework.com.cop.smt.sam.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.sam.service.EgovAllSchdulManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * 전체일정을 처리하는 ServiceImpl Class 구현
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
 *   2009.04.10  장동한          최초 생성
 *
 * </pre>
 */
@Service("egovAllSchdulManageService")
public class EgovAllSchdulManageServiceImpl extends EgovAbstractServiceImpl implements EgovAllSchdulManageService{

	@Resource(name="allSchdulManageDao")
	private AllSchdulManageDao dao;

    /**
	 * 전체일정 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectAllSchdulManageeList(ComDefaultVO searchVO) throws Exception{

		return dao.selectAllSchdulManageeList(searchVO);
	}

    /**
	 * 전체일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectAllSchdulManageListCnt(ComDefaultVO searchVO) throws Exception{

		return dao.selectAllSchdulManageListCnt(searchVO);
	}

}
