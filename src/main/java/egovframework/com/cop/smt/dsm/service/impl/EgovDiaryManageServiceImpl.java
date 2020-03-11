package egovframework.com.cop.smt.dsm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.smt.dsm.service.DiaryManageVO;
import egovframework.com.cop.smt.dsm.service.EgovDiaryManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * 일지관리를 처리하는 ServiceImpl Class 구현
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
@Service("egovDiaryManageService")
public class EgovDiaryManageServiceImpl extends EgovAbstractServiceImpl implements EgovDiaryManageService{

	//final private Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="diaryManageDao")
	private DiaryManageDao dao;

	
	@Resource(name="diaryManageIdGnrService")
	private EgovIdGnrService idgenService;
	
    /**
	 * 일지관리 목록를(을) 조회한다. 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectDiaryManageList(ComDefaultVO searchVO) throws Exception{
		return (List)dao.selectDiaryManageList(searchVO);
	}
	
    /**
	 * 일지관리를(을) 상세조회 한다.
	 * @param DiaryManage - 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public DiaryManageVO selectDiaryManageDetail(DiaryManageVO diaryManageVO) throws Exception{
		return (DiaryManageVO)dao.selectDiaryManageDetail(diaryManageVO);
	}
	
    /**
	 * 일지관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectDiaryManageListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)dao.selectDiaryManageListCnt(searchVO);
	}

    /**
	 * 일지관리를(을) 등록한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	public void insertDiaryManage(DiaryManageVO diaryManageVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();
		
		diaryManageVO.setDiaryId(sMakeId);

		dao.insertDiaryManage(diaryManageVO);
	}
	
    /**
	 * 일지관리를(을) 수정한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	public void updateDiaryManage(DiaryManageVO diaryManageVO) throws Exception{
		dao.updateDiaryManage(diaryManageVO);
	}
	
    /**
	 * 일지관리를(을) 삭제한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	public void deleteDiaryManage(DiaryManageVO diaryManageVO) throws Exception{
		dao.deleteDiaryManage(diaryManageVO);
	}
}
