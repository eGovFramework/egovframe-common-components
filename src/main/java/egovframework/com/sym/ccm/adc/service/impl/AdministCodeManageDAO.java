package egovframework.com.sym.ccm.adc.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.adc.service.AdministCode;
import egovframework.com.sym.ccm.adc.service.AdministCodeVO;

/**
 *
 * 행정코드에 대한 데이터 접근 클래스를 정의한다
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
@Repository("AdministCodeManageDAO")
public class AdministCodeManageDAO extends EgovComAbstractDAO {

	/**
	 * 행정코드를 삭제한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void deleteAdministCode(AdministCode administCode) throws Exception {
		delete("AdministCodeManageDAO.deleteAdministCode", administCode);
	}


	/**
	 * 행정코드를 등록한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void insertAdministCode(AdministCode administCode) throws Exception {
        insert("AdministCodeManageDAO.insertAdministCode", administCode);
	}

	/**
	 * 행정코드 상세항목을 조회한다.
	 * @param administCode
	 * @return AdministCode(행정코드)
	 */
	public AdministCode selectAdministCodeDetail(AdministCode administCode) throws Exception {
		return (AdministCode) selectOne("AdministCodeManageDAO.selectAdministCodeDetail", administCode);
	}


    /**
	 * 행정코드 목록을 조회한다.
     * @param searchVO
     * @return List(행정코드 목록)
     * @throws Exception
     */
    public List<EgovMap> selectAdministCodeList(AdministCodeVO searchVO) throws Exception {
        return selectList("AdministCodeManageDAO.selectAdministCodeList", searchVO);
    }

    /**
	 * 행정코드 총 개수를 조회한다.
     * @param searchVO
     * @return int(행정코드 총 개수)
     */
    public int selectAdministCodeListTotCnt(AdministCodeVO searchVO) throws Exception {
        return (Integer)selectOne("AdministCodeManageDAO.selectAdministCodeListTotCnt", searchVO);
    }

	/**
	 * 행정코드를 수정한다.
	 * @param administCode
	 * @throws Exception
	 */
	public void updateAdministCode(AdministCode administCode) throws Exception {
		update("AdministCodeManageDAO.updateAdministCode", administCode);
	}

}
