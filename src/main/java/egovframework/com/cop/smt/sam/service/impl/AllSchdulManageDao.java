package egovframework.com.cop.smt.sam.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

import org.springframework.stereotype.Repository;
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
 *
 * </pre>
 */
@Repository("allSchdulManageDao")
public class AllSchdulManageDao extends EgovComAbstractDAO {
	
    /**
	 * 전체일정 목록을 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List selectAllSchdulManageeList(ComDefaultVO searchVO) throws Exception{
		return (List)list("AllSchdulManage.selectIndvdlSchdulManage", searchVO);
	}
	

    /**
	 * 전체일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectAllSchdulManageListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)selectOne("AllSchdulManage.selectIndvdlSchdulManageCnt", searchVO);
	}
}
