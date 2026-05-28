package egovframework.com.dam.spe.spe.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.KnoSpecialistVO;
import jakarta.annotation.Resource;

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
public class KnoSpecialistDAO {

	@Resource(name = "knoSpecialistMapper")
	private KnoSpecialistMapper knoSpecialistMapper;

	/**
	 * 등록된 지식전문가 목록을 조회한다.
	 * @param searchVO - 지식전문가 VO
	 * @return List - 지식전문가 목록
	 */
	public List<KnoSpecialistVO> selectKnoSpecialistList(KnoSpecialistVO searchVO) throws Exception {
		return knoSpecialistMapper.selectKnoSpecialistList(searchVO);
	}

	/**
	 * 지식전문가 목록 총 개수를 조회한다.
	 * @param searchVO - 지식전문가 Vo
	 * @return int - 지식전문가 토탈 카운트 수
	 */
	public int selectKnoSpecialistTotCnt(KnoSpecialistVO searchVO) throws Exception {
		return knoSpecialistMapper.selectKnoSpecialistTotCnt(searchVO);
	}

	/**
	 * 지식전문가 상세 정보를 조회한다.
	 * @param knoSpecialist - 지식전문가 VO
	 * @return KnoSpecialist - 지식전문가 VO
	 */
	public KnoSpecialist selectKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		return knoSpecialistMapper.selectKnoSpecialist(knoSpecialist);
	}

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * @param knoSpecialist - 지식전문가 model
	 */
	public void insertKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		knoSpecialistMapper.insertKnoSpecialist(knoSpecialist);
	}

	/**
	 * 기 등록 된 지식전문가 정보를 수정한다.
	 * @param knoSpecialist - 지식전문가 model
	 */
	public void updateKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		knoSpecialistMapper.updateKnoSpecialist(knoSpecialist);
	}

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * @param knoSpecialist - 지식전문가 model
	 */
	public void deleteKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		knoSpecialistMapper.deleteKnoSpecialist(knoSpecialist);
	}

}
