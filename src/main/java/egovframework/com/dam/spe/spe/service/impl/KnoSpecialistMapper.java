package egovframework.com.dam.spe.spe.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.KnoSpecialistVO;

/**
 * 지식전문가에 대한 Mapper 인터페이스
 * @author 박종선
 * @version 1.0
 */
@EgovMapper("knoSpecialistMapper")
public interface KnoSpecialistMapper {

	/**
	 * 등록된 지식전문가 목록을 조회한다.
	 */
	List<KnoSpecialistVO> selectKnoSpecialistList(KnoSpecialistVO searchVO);

	/**
	 * 지식전문가 목록 총 개수를 조회한다.
	 */
	int selectKnoSpecialistTotCnt(KnoSpecialistVO searchVO);

	/**
	 * 지식전문가 상세 정보를 조회한다.
	 */
	KnoSpecialist selectKnoSpecialist(KnoSpecialist knoSpecialist);

	/**
	 * 지식전문가 정보를 등록한다.
	 */
	void insertKnoSpecialist(KnoSpecialist knoSpecialist);

	/**
	 * 지식전문가 정보를 수정한다.
	 */
	void updateKnoSpecialist(KnoSpecialist knoSpecialist);

	/**
	 * 지식전문가 정보를 삭제한다.
	 */
	void deleteKnoSpecialist(KnoSpecialist knoSpecialist);

}
