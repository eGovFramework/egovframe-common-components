package egovframework.com.sym.ccm.ccc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;

/**
*
* 공통분류코드에 대한 데이터 접근 클래스를 정의한다
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
@Repository("CmmnClCodeManageDAO")
public class CmmnClCodeManageDAO extends EgovComAbstractDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CmmnClCodeManageDAO.class);
	
	   /**
		 * 공통분류코드 총 개수를 조회한다.
	     * @param searchVO
	     * @return int(공통분류코드 총 개수)
	     */
	    public int selectCmmnClCodeListTotCnt(CmmnClCodeVO searchVO) throws Exception {
	        return (Integer)selectOne("CmmnClCodeManage.selectCmmnClCodeListTotCnt", searchVO);
	    }
	    
	    /**
		 * 공통분류코드 목록을 조회한다.
	     * @param searchVO
	     * @return List(공통분류코드 목록)
	     * @throws Exception
	     */
	    public List<CmmnClCodeVO> selectCmmnClCodeList(CmmnClCodeVO searchVO) throws Exception {
	        return selectList("CmmnClCodeManage.selectCmmnClCodeList", searchVO);
	    }
	    
	    /**
		 * 공통분류코드 상세항목을 조회한다.
		 * @param cmmnClCode
		 * @return CmmnClCode(공통분류코드)
		 */
		public CmmnClCode selectCmmnClCodeDetail(CmmnClCode cmmnClCode) throws Exception {
			return (CmmnClCode)selectOne("CmmnClCodeManage.selectCmmnClCodeDetail", cmmnClCode);
		}
		
		/**
		 * 공통분류코드를 등록한다.
		 * @param cmmnClCodeVO
		 * @throws Exception
		 */
		public void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws Exception{
			LOGGER.info("TEST5 : 등록 DAO");
			insert("CmmnClCodeManage.insertCmmnClCode", cmmnClCodeVO);
		}

		/**
		 * 공통분류코드를 삭제한다.
		 * @param cmmnClCodeVO
		 * @throws Exception
		 */
		public void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws Exception {
			delete("CmmnClCodeManage.deleteCmmnClCode", cmmnClCodeVO);
			
		}
		
		/**
		 * 공통분류코드를 수정한다.
		 * @param cmmnClCodeVO
		 * @throws Exception
		 */
		public void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws Exception{
			update("CmmnClCodeManage.updateCmmnClCode", cmmnClCodeVO);
			
		}

}
