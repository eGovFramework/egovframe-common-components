/**
 * 개요
 * - 회의실관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

package egovframework.com.uss.ion.mtg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.uss.ion.mtg.service.EgovMtgPlaceManageService;
import egovframework.com.uss.ion.mtg.service.MtgPlaceManageVO;
import egovframework.com.uss.ion.mtg.service.MtgPlaceResveVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;


@Service("egovMtgPlaceManageService")
public class EgovMtgPlaceManageServiceImpl extends EgovAbstractServiceImpl implements EgovMtgPlaceManageService {

	@Resource(name="mtgPlaceManageDAO")
    private MtgPlaceManageDAO mtgPlaceManageDAO;

    /** ID Generation */
	@Resource(name="egovMtgPlaceManageIdGnrService")
	private EgovIdGnrService idgenService;

    /** ID Generation */
	@Resource(name="egovMtgPlaceResveIdGnrService")
	private EgovIdGnrService idgenResveService;

	/**
	 * 회의실관리정보를 관리하기 위해 등록된 회의실관리 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	@Override
	public List<MtgPlaceManageVO> selectMtgPlaceManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception{
		return mtgPlaceManageDAO.selectMtgPlaceManageList(mtgPlaceManageVO);
	}

	/**
	 * 회의실관리목록 총 개수를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 회의실관리 카운트 수
	 */
	@Override
	public int selectMtgPlaceManageListTotCnt(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceManageListTotCnt(mtgPlaceManageVO);
	}

	/**
	 * 등록된 회의실관리의 상세정보를 조회한다.
	 */
	@Override
	public MtgPlaceManageVO selectMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceManage(mtgPlaceManageVO);
	}

	/**
	 * 회의실관리정보를 신규로 등록한다.
	 */
	@Override
	public void insertMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		String mtgPlaceId = idgenService.getNextStringId();
		mtgPlaceManageVO.setMtgPlaceId(mtgPlaceId);
		mtgPlaceManageDAO.insertMtgPlaceManage(mtgPlaceManageVO);
	}

	/**
	 * 기 등록된 회의실관리정보를 수정한다.
	 */
	@Override
	public void updtMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		mtgPlaceManageDAO.updtMtgPlaceManage(mtgPlaceManageVO);
	}

	/**
	 * 기 등록된 회의실관리정보를 삭제한다.
	 */
	@Override
	public void deleteMtgPlaceManage(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		mtgPlaceManageDAO.deleteMtgPlaceManage(mtgPlaceManageVO);
	}

	/******** 회의실 예약 관리 *************/

	/**
	 * 회의실 예약정보를 관리하기 위해 등록된 회의실 예약 목록을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return List - 회의실관리 목록
	 */
	@Override
	public List<MtgPlaceManageVO> selectMtgPlaceResveManageList(MtgPlaceManageVO mtgPlaceManageVO) throws Exception{

		List<MtgPlaceManageVO> result = mtgPlaceManageDAO.selectMtgPlaceIDList(mtgPlaceManageVO);
		List<MtgPlaceManageVO> list = new ArrayList<>();
		String tempResveDe = EgovStringUtil.removeMinusChar(mtgPlaceManageVO.getResveDe());
		int num = result.size();

	    for (int i = 0 ; i < num ; i ++ ){
	    	MtgPlaceManageVO mtgPlaceManageVO1 = result.get(i);
	    	mtgPlaceManageVO1.setResveDe(tempResveDe);
	        list.add(mtgPlaceManageDAO.selectMtgPlaceResveManageList(mtgPlaceManageVO1));
	    }

		return list;
	}

	/**
	 * 회의실예약 신청화면을 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	@Override
	public MtgPlaceManageVO selectMtgPlaceResve(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceResve(mtgPlaceManageVO);
	}

	/**
	 * 등록된 회의실 예약 상세정보를 조회한다.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return MtgPlaceManageVO - 회의실관리 VO
	 */
	@Override
	public MtgPlaceManageVO selectMtgPlaceResveDetail(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		return mtgPlaceManageDAO.selectMtgPlaceResveDetail(mtgPlaceManageVO);
	}

	/**
	 * 회의실 예약정보를 신규로 등록한다.
	 */
	@Override
	public void insertMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception {
		mtgPlaceResveVO.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceResveVO.getResveDe()));
		String sResveId = idgenResveService.getNextStringId();
		mtgPlaceResveVO.setResveId(sResveId);
		mtgPlaceManageDAO.insertMtgPlaceResve(mtgPlaceResveVO);
	}

	/**
	 * 기 등록된 회의실 예약정보를 수정한다.
	 */
	@Override
	public void updtMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception {
		mtgPlaceResveVO.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceResveVO.getResveDe()));
		mtgPlaceManageDAO.updtMtgPlaceResve(mtgPlaceResveVO);
	}

	/**
	 * 기 등록된 회의실 예약정보를 삭제한다.
	 */
	@Override
	public void deleteMtgPlaceResve(MtgPlaceResveVO mtgPlaceResveVO) throws Exception {
		mtgPlaceResveVO.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceResveVO.getResveDe()));
		mtgPlaceManageDAO.deleteMtgPlaceResve(mtgPlaceResveVO);
	}


	/**
	 * 회의실 중복여부 체크.
	 * @param mtgPlaceManageVO - 회의실관리 VO
	 * @return int - 중복건수
	 */
	@Override
	public int mtgPlaceResveDplactCeck(MtgPlaceManageVO mtgPlaceManageVO) throws Exception {
		mtgPlaceManageVO.setResveDe(EgovStringUtil.removeMinusChar(mtgPlaceManageVO.getResveDe()));
		return mtgPlaceManageDAO.mtgPlaceResveDplactCeck(mtgPlaceManageVO);
	}

}
