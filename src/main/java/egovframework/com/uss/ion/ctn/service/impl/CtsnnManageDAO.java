package egovframework.com.uss.ion.ctn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.ctn.service.CtsnnManageVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 경조관리에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 경조관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 경조관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */

@Repository("ctsnnManageDAO")
public class CtsnnManageDAO {

	@Resource(name = "ctsnnManageMapper")
	private CtsnnManageMapper ctsnnManageMapper;

	/**
	 * 경조관리 목록을 조회한다.
	 */
	public List<CtsnnManageVO> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageMapper.selectCtsnnManageList(ctsnnManageVO);
	}

	/**
	 * 경조관리목록 총 개수를 조회한다.
	 */
	public int selectCtsnnManageListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageMapper.selectCtsnnManageListTotCnt(ctsnnManageVO);
	}

	/**
	 * 경조관리 상세정보를 조회한다.
	 */
	public CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageMapper.selectCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 경조관리정보를 신규로 등록한다.
	 */
	public void insertCtsnnManage(CtsnnManageVO ctsnnManageVO) throws Exception {
		ctsnnManageMapper.insertCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 기 등록된 경조관리정보를 수정한다.
	 */
	public void updtCtsnnManage(CtsnnManageVO ctsnnManageVO) throws Exception {
		ctsnnManageMapper.updateCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 기 등록된 경조관리정보를 삭제한다.
	 */
	public void deleteCtsnnManage(CtsnnManageVO ctsnnManageVO) throws Exception {
		ctsnnManageMapper.deleteCtsnnManage(ctsnnManageVO);
	}

	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록을 조회한다.
	 */
	public List<CtsnnManageVO> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageMapper.selectCtsnnManageConfmList(ctsnnManageVO);
	}

	/**
	 * 경조관리정보 승인 처리를 위해 신청된 경조관리 목록 총 개수를 조회한다.
	 */
	public int selectCtsnnManageConfmListTotCnt(CtsnnManageVO ctsnnManageVO) throws Exception {
		return ctsnnManageMapper.selectCtsnnManageConfmListTotCnt(ctsnnManageVO);
	}

	/**
	 * 경조정보를 승인처리 한다.
	 */
	public void updtCtsnnManageConfm(CtsnnManageVO ctsnnManageVO) throws Exception {
		ctsnnManageMapper.updtCtsnnManageConfm(ctsnnManageVO);
	}
}
