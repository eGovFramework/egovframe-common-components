package egovframework.com.dam.spe.spe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.dam.spe.spe.service.EgovKnoSpecialistService;
import egovframework.com.dam.spe.spe.service.KnoSpecialist;
import egovframework.com.dam.spe.spe.service.KnoSpecialistVO;

/**
 * <pre>
 * 개요
 * - 지식전문가에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 지식전문가에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 지식전문가의 조회기능은 목록조회, 상세조회로 구분된다.
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
 *   2025.06.18  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-FieldNamingConventions(필드 명명 규칙)
 *
 *      </pre>
 */
@Service("KnoSpecialistService")
public class EgovKnoSpecialistServiceImpl extends EgovAbstractServiceImpl implements EgovKnoSpecialistService {

	@Resource(name = "KnoSpecialistDAO")
	private KnoSpecialistDAO knoSpecialistDAO;

	/**
	 * 등록된 지식전문가 정보를 조회 한다.
	 * 
	 * @param KnoSpecialistVO- 지식전문가 VO
	 * @return String - 지식전문가 목록
	 *
	 * @param KnoSpecialistVO
	 */
	@Override
	public List<KnoSpecialistVO> selectKnoSpecialistList(KnoSpecialistVO searchVO) throws Exception {
		return knoSpecialistDAO.selectKnoSpecialistList(searchVO);
	}

	/**
	 * 지식전문가 목록 총 개수를 조회한다.
	 * 
	 * @param KnoSpecialistVO - 지식전문가 Vo
	 * @return int - 지식전문가 토탈 카운트 수
	 *
	 * @param KnoSpecialistVO
	 */
	@Override
	public int selectKnoSpecialistTotCnt(KnoSpecialistVO searchVO) throws Exception {
		return knoSpecialistDAO.selectKnoSpecialistTotCnt(searchVO);
	}

	/**
	 * 지식전문가 상세 정보를 조회 한다.
	 * 
	 * @param KonSpecialistVO - 지식전문가 VO
	 * @return String - 지식전문가 VO
	 *
	 * @param KonSpecialistVO
	 */
	@Override
	public KnoSpecialist selectKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		KnoSpecialist ksl = knoSpecialistDAO.selectKnoSpecialist(knoSpecialist);
		return ksl;
	}

	/**
	 * 지식전문가 정보를 신규로 등록한다.
	 * 
	 * @param speNm - 지식전문가 model
	 *
	 * @param speNm
	 */
	@Override
	public void insertKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		knoSpecialistDAO.insertKnoSpecialist(knoSpecialist);
	}

	/**
	 * 기 등록 된 지식전문가 정보를 수정 한다.
	 * 
	 * @param speNm - 지식전문가 model
	 *
	 * @param speNm
	 */
	@Override
	public void updateKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		knoSpecialistDAO.updateKnoSpecialist(knoSpecialist);
	}

	/**
	 * 기 등록된 지식전문가 정보를 삭제한다.
	 * 
	 * @param siteUrl - 지식전문가 model
	 *
	 * @param speNm
	 */
	@Override
	public void deleteKnoSpecialist(KnoSpecialist knoSpecialist) throws Exception {
		knoSpecialistDAO.deleteKnoSpecialist(knoSpecialist);
	}

}