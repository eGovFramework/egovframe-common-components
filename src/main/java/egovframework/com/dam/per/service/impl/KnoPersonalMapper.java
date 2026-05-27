package egovframework.com.dam.per.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.dam.per.service.KnoPersonal;
import egovframework.com.dam.per.service.KnoPersonalVO;

/**
 * 개인지식정보 관리를 위한 MyBatis 매퍼 인터페이스
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel     @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper
public interface KnoPersonalMapper {

	/**
	 * 등록된 개인지식정보 목록을 조회한다.
	 * @param searchVO 개인지식 조회 조건 VO
	 * @return 개인지식정보 목록
	 */
	List<KnoPersonalVO> selectKnoPersonalList(KnoPersonalVO searchVO);

	/**
	 * 개인지식 목록 총 개수를 조회한다.
	 * @param searchVO 개인지식 조회 조건 VO
	 * @return 총 개수
	 */
	int selectKnoPersonalTotCnt(KnoPersonalVO searchVO);

	/**
	 * 개인지식정보 상세 정보를 조회한다.
	 * @param knoPersonal 조회할 개인지식정보 식별 정보가 담긴 모델
	 * @return 개인지식 상세 모델
	 */
	KnoPersonal selectKnoPersonal(KnoPersonal knoPersonal);

	/**
	 * 개인지식 정보를 신규로 등록한다.
	 * @param knoPersonal 등록할 개인지식정보 모델
	 */
	void insertKnoPersonal(KnoPersonal knoPersonal);

	/**
	 * 기 등록된 개인지식 정보를 수정한다.
	 * @param knoPersonal 수정할 개인지식정보 모델
	 */
	void updateKnoPersonal(KnoPersonal knoPersonal);

	/**
	 * 기 등록된 개인지식 정보를 삭제한다.
	 * @param knoPersonal 삭제할 개인지식정보 모델
	 */
	void deleteKnoPersonal(KnoPersonal knoPersonal);

}
