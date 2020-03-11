package egovframework.com.sym.ccm.adc.service.impl;

import java.util.List;

import egovframework.com.sym.ccm.adc.service.AdministCode;
import egovframework.com.sym.ccm.adc.service.AdministCodeVO;
import egovframework.com.sym.ccm.adc.service.EgovCcmAdministCodeManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



/**
 *
 * 행정코드에 대한 서비스 구현클래스를 정의한다.
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
@Service("AdministCodeManageService")
public class EgovCcmAdministCodeManageServiceImpl extends EgovAbstractServiceImpl implements EgovCcmAdministCodeManageService {

    @Resource(name="AdministCodeManageDAO")
    private AdministCodeManageDAO administCodeManageDAO;

	/**
	 * 행정코드를 삭제한다.
	 */
	@Override
	public void deleteAdministCode(AdministCode administCode) throws Exception {
		administCodeManageDAO.deleteAdministCode(administCode);
	}

	/**
	 * 행정코드를 등록한다.
	 */
	@Override
	public void insertAdministCode(AdministCode administCode) throws Exception {
    	administCodeManageDAO.insertAdministCode(administCode);
	}

	/**
	 * 행정코드 상세항목을 조회한다.
	 */
	@Override
	public AdministCode selectAdministCodeDetail(AdministCode administCode) throws Exception {
    	AdministCode ret = administCodeManageDAO.selectAdministCodeDetail(administCode);
    	return ret;
	}

	/**
	 * 행정코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectAdministCodeList(AdministCodeVO searchVO) throws Exception {
        return administCodeManageDAO.selectAdministCodeList(searchVO);
	}

	/**
	 * 행정코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectAdministCodeListTotCnt(AdministCodeVO searchVO) throws Exception {
        return administCodeManageDAO.selectAdministCodeListTotCnt(searchVO);
	}

	/**
	 * 행정코드를 수정한다.
	 */
	@Override
	public void updateAdministCode(AdministCode administCode) throws Exception {
		administCodeManageDAO.updateAdministCode(administCode);
	}

}
