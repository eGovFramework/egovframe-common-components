package egovframework.com.sym.ccm.ccc.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.ccc.service.EgovCcmCmmnClCodeManageService;
import lombok.RequiredArgsConstructor;

/**
 *
 * 공통분류코드에 대한 서비스 구현클래스를 정의한다
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2024.09.14  강동휘          컨트리뷰션 롬복 생성자 기반 종속성 주입
 *
 *      </pre>
 */
@Service
@RequiredArgsConstructor
public class EgovCcmCmmnClCodeManageServiceImpl extends EgovAbstractServiceImpl
		implements EgovCcmCmmnClCodeManageService {

	private final CmmnClCodeManageDAO cmmnClCodeManageDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovCcmCmmnClCodeManageServiceImpl.class);

	/**
	 * 공통분류코드 총 개수를 조회한다.
	 */
	@Override
	public int selectCmmnClCodeListTotCnt(CmmnClCodeVO searchVO) throws Exception {
		return cmmnClCodeManageDAO.selectCmmnClCodeListTotCnt(searchVO);
	}

	/**
	 * 공통분류코드 목록을 조회한다.
	 */
	@Override
	public List<CmmnClCodeVO> selectCmmnClCodeList(CmmnClCodeVO searchVO) throws Exception {
		return cmmnClCodeManageDAO.selectCmmnClCodeList(searchVO);
	}

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnClCode selectCmmnClCodeDetail(CmmnClCodeVO cmmnClCodeVO) throws Exception {
		CmmnClCode ret = cmmnClCodeManageDAO.selectCmmnClCodeDetail(cmmnClCodeVO);
		return ret;
	}

	/**
	 * 공통분류코드를 등록한다.
	 */
	@Override
	public void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws Exception {
		LOGGER.info("TEST4 : 등록 Serviceimpl");
		cmmnClCodeManageDAO.insertCmmnClCode(cmmnClCodeVO);
	}

	/**
	 * 공통분류코드를 삭제한다.
	 */
	@Override
	public void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws Exception {
		cmmnClCodeManageDAO.deleteCmmnClCode(cmmnClCodeVO);
	}

	/**
	 * 공통분류코드를 수정한다.
	 */
	@Override
	public void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws Exception {
		cmmnClCodeManageDAO.updateCmmnClCode(cmmnClCodeVO);

	}

}
