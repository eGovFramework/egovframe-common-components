package egovframework.com.uss.ion.vct.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.vct.service.VcatnManageVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 휴가관리에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 휴가관리의 조회기능은 목록조회, 상세조회로 구분된다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.15  이용              최초 생성
 *   2026.05.28  dasomel           @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author 이용
 * @version 1.0
 */
@Repository("vcatnManageDAO")
public class VcatnManageDAO {

	@Resource(name = "vcatnManageMapper")
	private VcatnManageMapper mapper;

	/**
	 * 휴가관리정보를 관리하기 위해 등록된 휴가관리 목록을 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	public List<VcatnManageVO> selectVcatnManageList(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectVcatnManageList(vcatnManageVO);
	}

	/**
	 * 휴가관리목록 총 개수를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectVcatnManageListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectVcatnManageListTotCnt(vcatnManageVO);
	}

	/**
	 * 등록된 휴가관리의 상세정보를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	public VcatnManageVO selectVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectVcatnManage(vcatnManageVO);
	}

	/**
	 * 휴가관리정보를 신규로 등록한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	public void insertVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		mapper.insertVcatnManage(vcatnManageVO);
	}

	/**
	 * 기 등록된 휴가관리정보를 수정한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	public void updtVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		mapper.updateVcatnManage(vcatnManageVO);
	}

	/**
	 * 기 등록된 휴가관리정보를 삭제한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	public void deleteVcatnManage(VcatnManageVO vcatnManageVO) throws Exception {
		mapper.deleteVcatnManage(vcatnManageVO);
	}

	/**
	 * 휴가일자 중복여부 체크
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectVcatnManageDplctAt(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectVcatnManageDplctAt(vcatnManageVO);
	}

	/*** 승인관련 ***/
	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록을 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return List - 휴가관리 목록
	 */
	public List<VcatnManageVO> selectVcatnManageConfmList(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectVcatnManageConfmList(vcatnManageVO);
	}

	/**
	 * 휴가관리정보 승인 처리를 위해 신청된 휴가관리 목록 총 개수를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectVcatnManageConfmListTotCnt(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectVcatnManageConfmListTotCnt(vcatnManageVO);
	}

	/**
	 * 신청된 휴가를 승인처리한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	public void updtVcatnManageConfm(VcatnManageVO vcatnManageVO) throws Exception {
		mapper.updateVcatnManageConfm(vcatnManageVO);
	}

	/*** 연차관련 ***/
	/**
	 * 개인별 연차관리의 상세정보를 조회한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 * @return VcatnManageVO - 휴가관리 VO
	 */
	public VcatnManageVO selectIndvdlYrycManage(VcatnManageVO vcatnManageVO) throws Exception {
		return mapper.selectIndvdlYrycManage(vcatnManageVO);
	}

	/**
	 * 연차정보를 수정처리한다.
	 * @param vcatnManageVO - 휴가관리 VO
	 */
	public void updtIndvdlYrycManage(VcatnManageVO vcatnManageVO) throws Exception {
		mapper.updateIndvdlYrycManage(vcatnManageVO);
	}
}
