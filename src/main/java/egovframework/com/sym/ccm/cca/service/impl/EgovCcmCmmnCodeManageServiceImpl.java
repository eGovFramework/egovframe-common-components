package egovframework.com.sym.ccm.cca.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
*
* 공통코드에 대한 서비스 구현클래스를 정의한다
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호          최초 생성
*
* </pre>
*/

@Service("CmmnCodeManageService")
public class EgovCcmCmmnCodeManageServiceImpl extends EgovAbstractServiceImpl implements EgovCcmCmmnCodeManageService{

    @Resource(name="CmmnCodeManageDAO")
    private CmmnCodeManageDAO cmmnCodeManageDAO;
    
	/**
	 * 공통코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnCodeListTotCnt(CmmnCodeVO searchVO) throws Exception {
        return cmmnCodeManageDAO.selectCmmnCodeListTotCnt(searchVO);
	}

	/**
	 * 공통코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnCodeList(CmmnCodeVO searchVO) throws Exception {
		return cmmnCodeManageDAO.selectCmmnCodeList(searchVO);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCodeVO) throws Exception{
		CmmnCodeVO ret = cmmnCodeManageDAO.selectCmmnCodeDetail(cmmnCodeVO);
    	return ret;
	}

	/**
	 * 공통코드를 수정한다.
	 */
	@Override
	public void updateCmmnCode(CmmnCodeVO cmmnCodeVO) throws Exception {
		cmmnCodeManageDAO.updateCmmnCode(cmmnCodeVO);
	}

	/**
	 * 공통코드를 등록한다.
	 */
	@Override
	public void insertCmmnCode(CmmnCode cmmnCode) throws Exception {
		cmmnCodeManageDAO.insertCmmnCode(cmmnCode);
		
	}

	/**
	 * 공통코드를 삭제한다.
	 */
	@Override
	public void deleteCmmnCode(CmmnCode cmmnCode) throws Exception {
		cmmnCodeManageDAO.deleteCmmnCode(cmmnCode);
	}

}
