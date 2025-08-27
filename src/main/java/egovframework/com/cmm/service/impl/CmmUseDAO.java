package egovframework.com.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2025.07.16  이백행          2025년 컨트리뷰션 `throws Exception` 제거
 *
 *      </pre>
 */
@Repository
public class CmmUseDAO extends EgovComAbstractDAO {

	/**
	 * 주어진 조건에 따른 공통코드를 불러온다.
	 * 
	 * @param comDefaultCodeVO
	 * @return
	 */
	public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO comDefaultCodeVO) {
		return selectList("CmmUseDAO.selectCmmCodeDetail", comDefaultCodeVO);
	}

	/**
	 * 공통코드로 사용할 조직정보를 를 불러온다.
	 * 
	 * @param comDefaultCodeVO
	 * @return
	 */
	public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
		return selectList("CmmUseDAO.selectOgrnztIdDetail", comDefaultCodeVO);
	}

	/**
	 * 공통코드로 사용할그룹정보를 를 불러온다.
	 * 
	 * @param comDefaultCodeVO
	 * @return
	 */
	public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
		return selectList("CmmUseDAO.selectGroupIdDetail", comDefaultCodeVO);
	}

	/**
	 * 여러 코드ID에 대한 공통코드를 한번에 조회한다.
	 * 
	 * @param codeIds 코드ID 목록
	 * @return
	 */
	public List<CmmnDetailCode> selectCmmCodeDetailsByCodeIds(List<String> codeIds) {
		return selectList("CmmUseDAO.selectCmmCodeDetailsByCodeIds", codeIds);
	}
}
