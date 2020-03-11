/**
 * 개요
 * - 네트워크에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 네트워크에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 네트워크의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 19-8-2010 오후 4:34:35
 */

package egovframework.com.sym.sym.nwk.service.impl;

import java.util.List;

import egovframework.com.sym.sym.nwk.service.EgovNtwrkService;
import egovframework.com.sym.sym.nwk.service.Ntwrk;
import egovframework.com.sym.sym.nwk.service.NtwrkVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("egovNtwrkService")
public class EgovNtwrkServiceImpl extends EgovAbstractServiceImpl implements EgovNtwrkService  {

	@Resource(name="ntwrkDAO")
	private NtwrkDAO ntwrkDAO;

	/**
	 * 네트워크를 관리하기 위해 등록된 네트워크목록을 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return List - 네트워크 목록
	 */
	@Override
	public List<NtwrkVO> selectNtwrkList(NtwrkVO ntwrkVO) throws Exception {
        return ntwrkDAO.selectNtwrkList(ntwrkVO);
    }

	/**
	 * 네트워크목록 총 갯수를 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return int - 네트워크 카운트 수
	 */
	@Override
	public int selectNtwrkListTotCnt(NtwrkVO ntwrkVO) throws Exception {
		return ntwrkDAO.selectNtwrkListTotCnt(ntwrkVO);
	}

	/**
	 * 등록된 네트워크의 상세정보를 조회한다.
	 * @param ntwrkVO - 네트워크 Vo
	 * @return NtwrkVO - 네트워크 Vo
	 */
	@Override
	public NtwrkVO selectNtwrk(NtwrkVO ntwrkVO) throws Exception {
		return ntwrkDAO.selectNtwrk(ntwrkVO);
	}

	/**
	 * 네트워크정보를 신규로 등록한다.
	 * @param ntwrk - 네트워크 model
	 * @return NtwrkVO - 네트워크 Vo
	 */
	@Override
	public NtwrkVO insertNtwrk(Ntwrk ntwrk, NtwrkVO ntwrkVO) throws Exception {
		ntwrk.setRegstYmd(EgovStringUtil.removeMinusChar(ntwrk.getRegstYmd()));
		ntwrkDAO.insertNtwrk(ntwrk);
        ntwrkVO.setNtwrkId(ntwrk.getNtwrkId());
        return ntwrkDAO.selectNtwrk(ntwrkVO);
	}

	/**
	 * 기 등록된 네트워크정보를 수정한다.
	 * @param ntwrk - 네트워크 model
	 */
	@Override
	public void updateNtwrk(Ntwrk ntwrk) throws Exception {
		ntwrk.setRegstYmd(EgovStringUtil.removeMinusChar(ntwrk.getRegstYmd()));
		ntwrkDAO.updateNtwrk(ntwrk);
	}

	/**
	 * 기 등록된 네트워크정보를 삭제한다.
	 * @param ntwrk - 네트워크 model
	 */
	@Override
	public void deleteNtwrk(Ntwrk ntwrk) throws Exception {
        ntwrkDAO.deleteNtwrk(ntwrk);
	}

}
