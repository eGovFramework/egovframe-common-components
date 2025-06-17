package egovframework.com.dam.mgm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.dam.mgm.service.EgovKnoManagementService;
import egovframework.com.dam.mgm.service.KnoManagement;
import egovframework.com.dam.mgm.service.KnoManagementVO;

/**
 * <pre>
 * 개요
 * - 지식정보에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식정보에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 박종선
 * @since 2010.08.12
 * @version 1.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.12  박종선          최초 생성
 *   2025.06.17  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙)
 *
 *      </pre>
 */
@Service("KnoManagementService")
public class EgovKnoManagementServiceImpl extends EgovAbstractServiceImpl implements EgovKnoManagementService {

	@Resource(name = "KnoManagementDAO")
	private KnoManagementDAO knoManagementDAO;

	/**
	 * 등록된 지식정보 정보를 조회 한다.
	 * 
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	@Override
	public List<EgovMap> selectKnoManagementList(KnoManagementVO searchVO) throws Exception {
		return knoManagementDAO.selectKnoManagementList(searchVO);
	}

	/**
	 * 지식정보 목록 총 개수를 조회한다.
	 * 
	 * @param KnoManagementVO - 지식정보 Vo
	 * @return int - 지식정보 토탈 카운트 수
	 *
	 * @param KnoManagementVO
	 */
	@Override
	public int selectKnoManagementTotCnt(KnoManagementVO searchVO) throws Exception {
		return knoManagementDAO.selectKnoManagementTotCnt(searchVO);
	}

	/**
	 * 지식정보 상세 정보를 조회 한다.
	 * 
	 * @param KnoManagementVO - 지식정보 VO
	 * @return String - 지식정보 VO
	 *
	 * @param KnoManagementVO
	 */
	@Override
	public KnoManagement selectKnoManagement(KnoManagement knoManagement) throws Exception {
		KnoManagement kmt = knoManagementDAO.selectKnoManagement(knoManagement);
		return kmt;
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * 
	 * @param KnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	@Override
	public void insertKnoManagement(KnoManagement knoManagement) throws Exception {
		knoManagementDAO.insertKnoManagement(knoManagement);
	}

	/**
	 * 기 등록 된 지식정보 정보를 수정 한다.
	 * 
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	@Override
	public void updateKnoManagement(KnoManagement knoManagement) throws Exception {
		knoManagementDAO.updateKnoManagement(knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * 
	 * @param ManagementKnoNm - 지식정보 model
	 *
	 * @param knoNm
	 */
	@Override
	public void deleteKnoManagement(KnoManagement knoManagement) throws Exception {
		knoManagementDAO.deleteKnoManagement(knoManagement);
	}

}