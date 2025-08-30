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
	 * 등록된 지식정보 목록을 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 지식정보 목록(List<EgovMap>)
	 * @throws Exception 조회 조건이 유효하지 않거나 데이터 접근 중 오류가 발생한 경우
	 */
	@Override
	public List<EgovMap> selectKnoManagementList(KnoManagementVO searchVO) throws Exception {
		return knoManagementDAO.selectKnoManagementList(searchVO);
	}

	/**
	 * 지식정보 목록 총 개수를 조회한다.
	 * @param searchVO 지식정보 조회 조건 VO
	 * @return 총 개수
	 * @throws Exception 조회 조건이 유효하지 않거나 데이터 접근 중 오류가 발생한 경우
	 */
	@Override
	public int selectKnoManagementTotCnt(KnoManagementVO searchVO) throws Exception {
		return knoManagementDAO.selectKnoManagementTotCnt(searchVO);
	}

	/**
	 * 지식정보 상세 정보를 조회한다.
	 * @param knoManagement 조회할 지식정보 식별 정보가 담긴 모델
	 * @return 지식정보 상세 모델
	 * @throws Exception 식별자가 없거나 해당 지식정보가 존재하지 않거나 데이터 접근 오류가 발생한 경우
	 */
	@Override
	public KnoManagement selectKnoManagement(KnoManagement knoManagement) throws Exception {
		KnoManagement kmt = knoManagementDAO.selectKnoManagement(knoManagement);
		return kmt;
	}

	/**
	 * 지식정보 정보를 신규로 등록한다.
	 * @param knoManagement 등록할 지식정보 모델
	 * @throws Exception 필수 값 누락, 권한 없음, 또는 데이터 접근 오류가 발생한 경우
	 */
	@Override
	public void insertKnoManagement(KnoManagement knoManagement) throws Exception {
		knoManagementDAO.insertKnoManagement(knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 수정한다.
	 * @param knoManagement 수정할 지식정보 모델
	 * @throws Exception 대상이 존재하지 않거나 권한 없음, 또는 데이터 접근 오류가 발생한 경우
	 */
	@Override
	public void updateKnoManagement(KnoManagement knoManagement) throws Exception {
		knoManagementDAO.updateKnoManagement(knoManagement);
	}

	/**
	 * 기 등록된 지식정보 정보를 삭제한다.
	 * @param knoManagement 삭제할 지식정보 모델
	 * @throws Exception 대상이 존재하지 않거나 권한 없음, 또는 데이터 접근 오류가 발생한 경우
	 */
	@Override
	public void deleteKnoManagement(KnoManagement knoManagement) throws Exception {
		knoManagementDAO.deleteKnoManagement(knoManagement);
	}

}