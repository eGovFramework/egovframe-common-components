package egovframework.com.dam.spe.spe.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.KnoSpecialistVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 지식전문가에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 지식전문가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식전문가의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:52
 */

@Repository("KnoSpecialistDAO")
public class KnoSpecialistDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * @param KnoSpecialistVO- 지식전문가 VO
	 * @return String - 지식전문가 목록
	 *
	 * @param KnoSpecialistVO
	 */
	public List<?> selectKnoSpecialistList(KnoSpecialistVO searchVO) throws Exception {
		return  selectList("KnoSpecialistDAO.selectKnoSpecialistList", searchVO);
	}

	/**
	 * 지식전문가 목록 총 갯수를 조회한다.
	 * @param KnoSpecialistVO - 지식전문가 Vo
	 * @return int - 지식전문가 토탈 카운트 수
	 *
	 * @param KnoSpecialistVO
	 */
	public int selectKnoSpecialistTotCnt(KnoSpecialistVO searchVO) throws Exception {
		return  (Integer)selectOne("KnoSpecialistDAO.selectKnoSpecialistTotCnt", searchVO);
	}

	/**
	 * 지식전문가 상세 정보를 조회 한다.
	 * @param KonSpecialistVO - 지식전문가 VO
	 * @return String - 지식전문가 VO
	 *
	 * @param KonSpecialistVO
	 */
	public KnoSpecialist selectKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		return (KnoSpecialist)selectOne("KnoSpecialistDAO.selectKnoSpecialist", knoSpecialist);
	}

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * @param speNm - 지식전문가 model
	 *
	 * @param speNm
	 */
	public void insertKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		insert("KnoSpecialistDAO.insertKnoSpecialist", knoSpecialist);
	}

	/**
	 * 기 등록 된 지식전문가 정보를 수정 한다.
	 * @param speNm - 지식전문가 model
	 *
	 * @param speNm
	 */
	public void updateKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		update("KnoSpecialistDAO.updateKnoSpecialist", knoSpecialist);
	}

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * @param siteUrl - 지식전문가 model
	 *
	 * @param speNm
	 */
	public void deleteKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		delete("KnoSpecialistDAO.deleteKnoSpecialist", knoSpecialist);
	}

}