package egovframework.com.cop.smt.wmr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.wmr.service.ReportrVO;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprt;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprtVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * <pre>
 * 개요
 * - 주간월간보고에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 주간월간보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 주간월간보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:48
 * 
 *          <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  장철호          최초 생성
 *   2024.09.10  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *          </pre>
 */
@Repository("WikMnthngReprtDAO")
public class WikMnthngReprtDAO extends EgovComAbstractDAO {

	/**
	 * 주어진 조건에 맞는 보고자를 불러온다.
	 * 
	 * @param ReportrVO
	 * @return List
	 * 
	 * @param reportrVO
	 */
	public List<ReportrVO> selectReportrList(ReportrVO reportrVO) {
		return selectList("WikMnthngReprtDAO.selectReportrList", reportrVO);
	}

	/**
	 * 보고자 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param ReportrVO
	 * @return int
	 * 
	 * @param reportrVO
	 */
	public int selectReportrListCnt(ReportrVO reportrVO) {
		return (Integer) selectOne("WikMnthngReprtDAO.selectReportrListCnt", reportrVO);
	}

	/**
	 * 주어진 조건에 맞는 직위명을 불러온다.
	 * 
	 * @param DeptVO
	 * @return String
	 * 
	 * @param DeptVO
	 */
	public String selectWrterClsfNm(String wrterId) {
		return (String) selectOne("WikMnthngReprtDAO.selectWrterClsfNm", wrterId);
	}

	/**
	 * 주어진 조건에 맞는 주간월간보고를 불러온다.
	 * 
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return List<WikMnthngReprtVO> - 주간월간보고 List
	 * 
	 * @param wikMnthngReprtVO
	 */
	public List<WikMnthngReprtVO> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO) {
		// 날짜관련
		wikMnthngReprtVO.setSearchBgnDe(wikMnthngReprtVO.getSearchBgnDe().replaceAll("-", ""));
		wikMnthngReprtVO.setSearchEndDe(wikMnthngReprtVO.getSearchEndDe().replaceAll("-", ""));

		List<WikMnthngReprtVO> resultList = selectList("WikMnthngReprtDAO.selectWikMnthngReprtList", wikMnthngReprtVO);
		for (int i = 0; i < resultList.size(); i++) {
			WikMnthngReprtVO resultVO = resultList.get(i);
			resultVO.setReprtDe(EgovDateUtil.convertDate(resultVO.getReprtDe(), "0000", "yyyy-MM-dd"));
			resultVO.setReprtBgnDe(EgovDateUtil.convertDate(resultVO.getReprtBgnDe(), "0000", "yyyy-MM-dd"));
			resultVO.setReprtEndDe(EgovDateUtil.convertDate(resultVO.getReprtEndDe(), "0000", "yyyy-MM-dd"));
			resultList.set(i, resultVO);
		}
		return resultList;
	}

	/**
	 * 주어진 조건에 맞는 주간월간보고 목록을 불러온다.
	 * 
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return WikMnthngReprtVO - 주간월간보고 VO
	 * 
	 * @param wikMnthngReprtVO
	 */
	public WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) {
		WikMnthngReprtVO resultVO = (WikMnthngReprtVO) selectOne("WikMnthngReprtDAO.selectWikMnthngReprt",
				wikMnthngReprtVO);
		resultVO.setReprtDe(EgovDateUtil.convertDate(resultVO.getReprtDe(), "0000", "yyyy-MM-dd"));
		resultVO.setReprtBgnDe(EgovDateUtil.convertDate(resultVO.getReprtBgnDe(), "0000", "yyyy-MM-dd"));
		resultVO.setReprtEndDe(EgovDateUtil.convertDate(resultVO.getReprtEndDe(), "0000", "yyyy-MM-dd"));
		return resultVO;
	}

	/**
	 * 주간월간보고 정보를 수정한다.
	 * 
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void updateWikMnthngReprt(WikMnthngReprt wikMnthngReprt) {
		// 날짜관련
		// KISA 보안약점 조치 (2018-10-29, 윤창원)
		wikMnthngReprt.setReprtDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtBgnDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtBgnDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtEndDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtEndDe()).replaceAll("-", ""));
		update("WikMnthngReprtDAO.updateWikMnthngReprt", wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 등록한다.
	 * 
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void insertWikMnthngReprt(WikMnthngReprt wikMnthngReprt) {
		// 날짜관련
		// KISA 보안약점 조치 (2018-10-29, 윤창원)
		wikMnthngReprt.setReprtDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtBgnDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtBgnDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtEndDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtEndDe()).replaceAll("-", ""));
		insert("WikMnthngReprtDAO.insertWikMnthngReprt", wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * 
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void deleteWikMnthngReprt(WikMnthngReprt wikMnthngReprt) {
		delete("WikMnthngReprtDAO.deleteWikMnthngReprt", wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 승인한다.
	 * 
	 * @param WikMnthngReprt - 주간월간보고 model
	 * 
	 * @param wikMnthngReprt
	 */
	public void confirmWikMnthngReprt(WikMnthngReprt wikMnthngReprt) {
		update("WikMnthngReprtDAO.confirmWikMnthngReprt", wikMnthngReprt);
	}

	/**
	 * 주간월간보고 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param WikMnthngReprtVO - 주간월간보고 VO
	 * @return int - 주간월간보고 목록 개수
	 * 
	 * @param wikMnthngReprtVO
	 */
	public int selectWikMnthngReprtListCnt(WikMnthngReprtVO wikMnthngReprtVO) {
		// 날짜관련
		// KISA 보안약점 조치 (2018-10-29, 윤창원)
		wikMnthngReprtVO
				.setSearchBgnDe(EgovStringUtil.isNullToString(wikMnthngReprtVO.getSearchBgnDe()).replaceAll("-", ""));
		wikMnthngReprtVO
				.setSearchEndDe(EgovStringUtil.isNullToString(wikMnthngReprtVO.getSearchEndDe()).replaceAll("-", ""));

		return (Integer) selectOne("WikMnthngReprtDAO.selectWikMnthngReprtListCnt", wikMnthngReprtVO);
	}

}