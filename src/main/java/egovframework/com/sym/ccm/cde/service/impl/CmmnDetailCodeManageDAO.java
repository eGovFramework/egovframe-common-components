package egovframework.com.sym.ccm.cde.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;

/**
*
* 공통상세코드에 대한 데이터 접근 클래스를 정의한다
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

@Repository("CmmnDetailCodeManageDAO")
public class CmmnDetailCodeManageDAO extends EgovComAbstractDAO {

    /**
	 * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     */
    public int selectCmmnDetailCodeListTotCnt(CmmnDetailCodeVO searchVO) throws Exception {
        return (Integer)selectOne("CmmnDetailCodeManage.selectCmmnDetailCodeListTotCnt", searchVO);
    }
    
    /**
	 * 공통상세코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통상세코드 목록)
     * @throws Exception
     */
    public List<?> selectCmmnDetailCodeList(CmmnDetailCodeVO searchVO) throws Exception {
        return list("CmmnDetailCodeManage.selectCmmnDetailCodeList", searchVO);
    }

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param cmmnDetailCodeVO
	 * @return CmmnDetailCodeVO(공통상세코드)
	 */
	public CmmnDetailCode selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		return (CmmnDetailCode) selectOne("CmmnDetailCodeManage.selectCmmnDetailCodeDetail", cmmnDetailCodeVO);
	}
	
	/**
	 * 공통상세코드를 삭제한다.
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	public void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		delete("CmmnDetailCodeManage.deleteCmmnDetailCode", cmmnDetailCodeVO);
		
	}

	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	public void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		insert("CmmnDetailCodeManage.insertCmmnDetailCode", cmmnDetailCodeVO);
		
	}

	/**
	 * 공통상세코드를 수정한다.
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	public void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		insert("CmmnDetailCodeManage.updateCmmnDetailCode", cmmnDetailCodeVO);
		
	}
    
}
