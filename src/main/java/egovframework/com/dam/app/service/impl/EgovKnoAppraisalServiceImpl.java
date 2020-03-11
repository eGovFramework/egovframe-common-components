package egovframework.com.dam.app.service.impl;

import java.util.List;

import egovframework.com.dam.app.service.EgovKnoAppraisalService;
import egovframework.com.dam.app.service.KnoAppraisal;
import egovframework.com.dam.app.service.KnoAppraisalVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 지식정보평가에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보평가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보평가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:38
 */

@Service("KnoAppraisalService")
public class EgovKnoAppraisalServiceImpl extends EgovAbstractServiceImpl implements EgovKnoAppraisalService {

	@Resource(name="KnoAppraisalDAO")
	private KnoAppraisalDAO KnoAppraisalDAO;

	/**
	 * 등록된 지식정보평가 정보를 조회 한다.
	 * @param KnoAppraisalVO - 지식정보평가 VO
	 * @return String - 지식정보평가 VO
	 *
	 * @param KnoAppraisalVO
	 */
	@Override
	public List<?> selectKnoAppraisalList(KnoAppraisalVO  searchVO) throws Exception {
		return KnoAppraisalDAO.selectKnoAppraisalList(searchVO);
	}

	/**
	 * 지식정보평가 목록 총 갯수를 조회한다.
	 * @param MapTeamVO - 지식정보평가 Vo
	 * @return int - 지식정보평가 토탈 카운트 수
	 *
	 * @param KnoAppraisalVO
	 */
	@Override
	public int selectKnoAppraisalTotCnt(KnoAppraisalVO searchVO) throws Exception {
		return KnoAppraisalDAO.selectKnoAppraisalTotCnt(searchVO);
	}

	/**
	 * 지식정보평가 상세 정보를 조회 한다.
	 * @param KnoAppraisalVO - 지식정보평가 VO
	 * @return String - 지식정보평가 VO
	 *
	 * @param KnoAppraisalVO
	 */
	@Override
	public KnoAppraisal selectKnoAppraisal(KnoAppraisal knoAppraisal) throws Exception {
		KnoAppraisal kal = KnoAppraisalDAO.selectKnoAppraisal(knoAppraisal);
		return kal;
	}

	/**
	 * 지식정보평가 정보를 신규로 등록한다.
	 * @param knoAps - 지식정보평가 model
	 *
	 * @param knoAps
	 */
	@Override
	public void insertKnoAppraisal(KnoAppraisal knoAppraisal) throws Exception {
		KnoAppraisalDAO.insertKnoAppraisal(knoAppraisal);
	}

	/**
	 * 기 등록 된 지식정보평가 정보를 수정 한다.
	 * @param AppraisalknoAps - 지식정보평가 model
	 *
	 * @param knoAps
	 */
	@Override
	public void updateKnoAppraisal(KnoAppraisal knoAppraisal) throws Exception {
		KnoAppraisalDAO.updateKnoAppraisal(knoAppraisal);
	}

	/**
	 * 기 등록된 지식정보평가 정보를 삭제한다.
	 * @param AppraisalknoAps - 지식정보평가 model
	 *
	 * @param knoAps
	 */
	@Override
	public void deleteKnoAppraisal(KnoAppraisal knoAppraisal) throws Exception {
		KnoAppraisalDAO.deleteKnoAppraisal(knoAppraisal);
	}

}