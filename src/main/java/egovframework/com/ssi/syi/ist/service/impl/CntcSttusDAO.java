package egovframework.com.ssi.syi.ist.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.ssi.syi.ist.service.CntcSttus;
import egovframework.com.ssi.syi.ist.service.CntcSttusVO;

/**
 * 연계현황에 대한 데이터 접근 클래스를 정의한다
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2025.06.28  이백행          컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *   2025.06.28  이백행          컨트리뷰션 형 변환 제거-(CntcSttus), (Integer)
 *
 *      </pre>
 */
@Repository("CntcSttusDAO")
public class CntcSttusDAO extends EgovComAbstractDAO {

	/**
	 * 연계현황 상세항목을 조회한다.
	 * 
	 * @param cntcSttus
	 * @return CntcSttus(연계현황)
	 */
	public CntcSttus selectCntcSttusDetail(CntcSttus cntcSttus) throws Exception {
		return selectOne("CntcSttusDAO.selectCntcSttusDetail", cntcSttus);
	}

	/**
	 * 연계현황 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return List(연계현황 목록)
	 * @throws Exception
	 */
	public List<EgovMap> selectCntcSttusList(CntcSttusVO searchVO) throws Exception {
		return selectList("CntcSttusDAO.selectCntcSttusList", searchVO);
	}

	/**
	 * 연계현황 총 개수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int(연계현황 총 개수)
	 */
	public int selectCntcSttusListTotCnt(CntcSttusVO searchVO) throws Exception {
		return selectOne("CntcSttusDAO.selectCntcSttusListTotCnt", searchVO);
	}

}
