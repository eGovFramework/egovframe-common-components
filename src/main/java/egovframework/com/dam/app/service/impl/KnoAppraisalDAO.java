package egovframework.com.dam.app.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.dam.app.service.KnoAppraisal;
import egovframework.com.dam.app.service.KnoAppraisalVO;

import jakarta.annotation.Resource;

/**
 * 개요
 * - 지식정보평가에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보평가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보평가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:47
 */
@Repository("KnoAppraisalDAO")
public class KnoAppraisalDAO {

	@Resource(name = "knoAppraisalMapper")
	private KnoAppraisalMapper knoAppraisalMapper;

	/**
	 * 등록된 지식정보평가 목록을 조회한다.
	 * @param searchVO 지식정보평가 조회 조건 VO
	 * @return 지식정보평가 목록
	 */
	public List<EgovMap> selectKnoAppraisalList(KnoAppraisalVO searchVO) {
		return knoAppraisalMapper.selectKnoAppraisalList(searchVO);
	}

	/**
	 * 지식정보평가 목록 총 개수를 조회한다.
	 * @param searchVO 지식정보평가 조회 조건 VO
	 * @return 총 개수
	 */
	public int selectKnoAppraisalTotCnt(KnoAppraisalVO searchVO) {
		return knoAppraisalMapper.selectKnoAppraisalTotCnt(searchVO);
	}

	/**
	 * 지식정보평가 상세 정보를 조회한다.
	 * @param knoAppraisal 조회할 지식정보평가 식별 정보가 담긴 모델
	 * @return 지식정보평가 상세 모델
	 */
	public KnoAppraisal selectKnoAppraisal(KnoAppraisal knoAppraisal) {
		return knoAppraisalMapper.selectKnoAppraisal(knoAppraisal);
	}

	/**
	 * 지식정보평가 정보를 신규로 등록한다.
	 * @param knoAppraisal 등록할 지식정보평가 모델
	 */
	public void insertKnoAppraisal(KnoAppraisal knoAppraisal) {
		knoAppraisalMapper.insertKnoAppraisal(knoAppraisal);
	}

	/**
	 * 기 등록된 지식정보평가 정보를 수정한다.
	 * @param knoAppraisal 수정할 지식정보평가 모델
	 */
	public void updateKnoAppraisal(KnoAppraisal knoAppraisal) {
		knoAppraisalMapper.updateKnoAppraisal(knoAppraisal);
	}

	/**
	 * 기 등록된 지식정보평가 정보를 삭제한다.
	 * @param knoAppraisal 삭제할 지식정보평가 모델
	 */
	public void deleteKnoAppraisal(KnoAppraisal knoAppraisal) {
		knoAppraisalMapper.deleteKnoAppraisal(knoAppraisal);
	}

}
