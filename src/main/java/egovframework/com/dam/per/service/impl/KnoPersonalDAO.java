package egovframework.com.dam.per.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.dam.per.service.KnoPersonal;
import egovframework.com.dam.per.service.KnoPersonalVO;

import jakarta.annotation.Resource;

/**
 * 개요
 * - 개인지식정보에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 개인지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 개인지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 12-8-2010 오후 3:44:50
 */
@Repository("KnoPersonalDAO")
public class KnoPersonalDAO {

	@Resource(name = "knoPersonalMapper")
	private KnoPersonalMapper knoPersonalMapper;

	/**
	 * 등록된 개인지식정보 목록을 조회한다.
	 * @param searchVO 개인지식 조회 조건 VO
	 * @return 개인지식정보 목록
	 */
	public List<KnoPersonalVO> selectKnoPersonalList(KnoPersonalVO searchVO) {
		return knoPersonalMapper.selectKnoPersonalList(searchVO);
	}

	/**
	 * 개인지식 목록 총 개수를 조회한다.
	 * @param searchVO 개인지식 조회 조건 VO
	 * @return 총 개수
	 */
	public int selectKnoPersonalTotCnt(KnoPersonalVO searchVO) {
		return knoPersonalMapper.selectKnoPersonalTotCnt(searchVO);
	}

	/**
	 * 개인지식정보 상세 정보를 조회한다.
	 * @param knoPersonal 조회할 개인지식정보 식별 정보가 담긴 모델
	 * @return 개인지식 상세 모델
	 */
	public KnoPersonal selectKnoPersonal(KnoPersonal knoPersonal) {
		return knoPersonalMapper.selectKnoPersonal(knoPersonal);
	}

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * @param knoPersonal 등록할 개인지식정보 모델
	 */
	public void insertKnoPersonal(KnoPersonal knoPersonal) {
		knoPersonalMapper.insertKnoPersonal(knoPersonal);
	}

	/**
	 * 기 등록된 개인지식 정보를 수정한다.
	 * @param knoPersonal 수정할 개인지식정보 모델
	 */
	public void updateKnoPersonal(KnoPersonal knoPersonal) {
		knoPersonalMapper.updateKnoPersonal(knoPersonal);
	}

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * @param knoPersonal 삭제할 개인지식정보 모델
	 */
	public void deleteKnoPersonal(KnoPersonal knoPersonal) {
		knoPersonalMapper.deleteKnoPersonal(knoPersonal);
	}

}
