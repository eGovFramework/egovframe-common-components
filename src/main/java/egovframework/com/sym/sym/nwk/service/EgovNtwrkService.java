/**
 * 개요
 * - 네트워크에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 네트워크에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 네트워크의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 19-8-2010 오후 4:34:35
 */

package egovframework.com.sym.sym.nwk.service;

import java.util.List;

public interface EgovNtwrkService {

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return List - 네트워크 목록
	 */
    public List<?> selectNtwrkList(NtwrkVO ntwrkVO) throws Exception;

    /**
	 * 네트워크 목록 총 갯수를 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return int - 네트워크 카운트 수
	 */
    public int selectNtwrkListTotCnt(NtwrkVO ntwrkVO) throws Exception;

    /**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return NtwrkVO - 네트워크 Vo
	 */
    public NtwrkVO selectNtwrk(NtwrkVO ntwrkVO) throws Exception;

    /**
	 * 네트워크정보를 신규로 등록한다.
	 * @param ntwrk - 네트워크 model
	 */
    public NtwrkVO insertNtwrk(Ntwrk ntwrk, NtwrkVO ntwrkVO) throws Exception;

    /**
	 * 기 등록된 네트워크정보를 수정한다.
	 * @param ntwrk - 네트워크 model
	 */
    public void updateNtwrk(Ntwrk ntwrk) throws Exception;

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * @param ntwrk - 네트워크 model
	 */
    public void deleteNtwrk(Ntwrk ntwrk) throws Exception;
}
