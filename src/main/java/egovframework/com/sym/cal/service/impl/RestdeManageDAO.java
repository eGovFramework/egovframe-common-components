package egovframework.com.sym.cal.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.cal.service.Restde;
import egovframework.com.sym.cal.service.RestdeVO;

/**
 *
 * 휴일에 대한 데이터 접근 클래스를 정의한다
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
 *   2026.06.16  이백행          [2026년 컨트리뷰션] 불필요한 예외(throws Exception) 제거
 *
 * </pre>
 */
@Repository("RestdeManageDAO")
public class RestdeManageDAO extends EgovComAbstractDAO {

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * @param restde
	 * @return List(일반달력 팝업 날짜정보)
	 */
    public List<EgovMap> selectNormalRestdePopup(Restde restde) {
        return selectList("RestdeManageDAO.selectNormalRestdePopup", restde);
	}

	/**
	 * 행정달력 팝업 정보를 조회한다.
	 * @param restde
	 * @return List(행정달력 팝업 날짜정보)
	 */
    public List<EgovMap> selectAdministRestdePopup(Restde restde) {
        return selectList("RestdeManageDAO.selectAdministRestdePopup", restde);
	}

	/**
	 * 일반달력 일간 정보를 조회한다.
	 * @param restde
	 * @return List(일반달력 일간 날짜정보)
	 */
    public List<EgovMap> selectNormalDayCal(Restde restde) {
        return selectList("RestdeManageDAO.selectNormalDayCal", restde);
	}

	/**
	 * 일반달력 일간 휴일을 조회한다.
	 * @param restde
	 * @return List(일반달력 일간 휴일정보)
	 */
    public List<EgovMap> selectNormalDayRestde(Restde restde) {
        return selectList("RestdeManageDAO.selectNormalDayRestde", restde);
	}

	/**
	 * 일반달력 월간 휴일을 조회한다.
	 * @param restde
	 * @return List(일반달력 월간 휴일정보)
	 */
    public List<EgovMap> selectNormalMonthRestde(Restde restde) {
        return selectList("RestdeManageDAO.selectNormalMonthRestde", restde);
	}

	/**
	 * 행정달력 일간 정보를 조회한다.
	 * @param restde
	 * @return List(행정달력 일간 날짜정보)
	 */
    public List<EgovMap> selectAdministDayCal(Restde restde) {
        return selectList("RestdeManageDAO.selectAdministDayCal", restde);
	}

	/**
	 * 행정달력 일간 휴일을 조회한다.
	 * @param restde
	 * @return List(행정달력 일간 휴일정보)
	 */
    public List<EgovMap> selectAdministDayRestde(Restde restde) {
        return selectList("RestdeManageDAO.selectAdministDayRestde", restde);
	}

	/**
	 * 행정달력 월간 휴일을 조회한다.
	 * @param restde
	 * @return List(행정달력 월간 휴일정보)
	 */
    public List<EgovMap> selectAdministMonthRestde(Restde restde) {
        return selectList("RestdeManageDAO.selectAdministMonthRestde", restde);
	}

	/**
	 * 휴일을 삭제한다.
	 * @param restde
	 */
	public void deleteRestde(Restde restde) {
		delete("RestdeManageDAO.deleteRestde", restde);
	}


	/**
	 * 휴일을 등록한다.
	 * @param restde
	 */
	public void insertRestde(Restde restde) {
        insert("RestdeManageDAO.insertRestde", restde);
	}

	/**
	 * 휴일 상세항목을 조회한다.
	 * @param restde
	 * @return Restde(휴일)
	 */
	public Restde selectRestdeDetail(Restde restde) {
		return (Restde) selectOne("RestdeManageDAO.selectRestdeDetail", restde);
	}


    /**
     * 휴일 목록을 조회한다.
     * @param searchVO
	 * @return List(휴일 목록)
     */
    public List<EgovMap> selectRestdeList(RestdeVO searchVO) {
        return selectList("RestdeManageDAO.selectRestdeList", searchVO);
    }

    /**
     * 글 총 개수를 조회한다.
     * @param searchVO
     * @return int(휴일 총 개수)
     */
    public int selectRestdeListTotCnt(RestdeVO searchVO) {
        return (Integer)selectOne("RestdeManageDAO.selectRestdeListTotCnt", searchVO);
    }

	/**
	 * 휴일을 수정한다.
	 * @param restde
	 */
	public void updateRestde(Restde restde) {
		update("RestdeManageDAO.updateRestde", restde);
	}

}
