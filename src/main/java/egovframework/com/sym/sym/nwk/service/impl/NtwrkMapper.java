package egovframework.com.sym.sym.nwk.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.sym.sym.nwk.service.Ntwrk;
import egovframework.com.sym.sym.nwk.service.NtwrkVO;

/**
 * <pre>
 * 개요
 * - 네트워크에 대한 Mapper 인터페이스를 정의한다.
 *
 * 상세내용
 * - 네트워크에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 네트워크의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 *
 * @author lee.m.j
 * @since 2010.08.19
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  lee.m.j       최초 생성
 *   2026.05.26  dasomel       @EgovMapper 인터페이스 방식으로 전환
 *
 *      </pre>
 */
@EgovMapper("ntwrkDAO")
public interface NtwrkMapper {

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 *
	 * @param ntwrkVO - 네트워크 Vo
	 * @return List - 네트워크 목록
	 */
	List<NtwrkVO> selectNtwrkList(NtwrkVO ntwrkVO) throws Exception;

	/**
	 * 네트워크목록 총 개수를 조회한다.
	 *
	 * @param ntwrkVO - 네트워크 Vo
	 * @return int - 네트워크 카운트 수
	 */
	int selectNtwrkListTotCnt(NtwrkVO ntwrkVO) throws Exception;

	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 *
	 * @param ntwrkVO - 네트워크 Vo
	 * @return NtwrkVO - 네트워크 Vo
	 */
	NtwrkVO selectNtwrk(NtwrkVO ntwrkVO) throws Exception;

	/**
	 * 네트워크정보를 신규로 등록한다.
	 *
	 * @param ntwrk - 네트워크 model
	 */
	void insertNtwrk(Ntwrk ntwrk) throws Exception;

	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 *
	 * @param ntwrk - 네트워크 model
	 */
	void updateNtwrk(Ntwrk ntwrk) throws Exception;

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 *
	 * @param ntwrk - 네트워크 model
	 */
	void deleteNtwrk(Ntwrk ntwrk) throws Exception;

}
