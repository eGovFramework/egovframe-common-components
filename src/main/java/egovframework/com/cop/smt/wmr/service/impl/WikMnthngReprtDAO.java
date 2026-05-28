package egovframework.com.cop.smt.wmr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.smt.wmr.service.ReportrVO;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprt;
import egovframework.com.cop.smt.wmr.service.WikMnthngReprtVO;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 주간월간보고에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 주간월간보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 주간월간보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:48
 *   <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19   장철호          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 *
 * </pre>
 */
@Repository("WikMnthngReprtDAO")
public class WikMnthngReprtDAO {

	@Resource(name = "wikMnthngReprtMapper")
	private WikMnthngReprtMapper mapper;

	/**
	 * 주어진 조건에 맞는 보고자를 불러온다.
	 * @param reportrVO
	 * @return List
	 */
	public List<ReportrVO> selectReportrList(ReportrVO reportrVO) throws Exception {
		return mapper.selectReportrList(reportrVO);
	}

	/**
	 * 보고자 목록에 대한 전체 건수를 조회한다.
	 * @param reportrVO
	 * @return int
	 */
	public int selectReportrListCnt(ReportrVO reportrVO) throws Exception {
		return mapper.selectReportrListCnt(reportrVO);
	}

	/**
	 * 주어진 조건에 맞는 직위명을 불러온다.
	 * @param wrterId
	 * @return String
	 */
	public String selectWrterClsfNm(String wrterId) throws Exception {
		return mapper.selectWrterClsfNm(wrterId);
	}

	/**
	 * 주어진 조건에 맞는 주간월간보고를 불러온다.
	 * @param wikMnthngReprtVO - 주간월간보고 VO
	 * @return List<WikMnthngReprtVO> - 주간월간보고 List
	 */
	public List<WikMnthngReprtVO> selectWikMnthngReprtList(WikMnthngReprtVO wikMnthngReprtVO) throws Exception {
		//날짜관련
		wikMnthngReprtVO.setSearchBgnDe(wikMnthngReprtVO.getSearchBgnDe().replaceAll("-", ""));
		wikMnthngReprtVO.setSearchEndDe(wikMnthngReprtVO.getSearchEndDe().replaceAll("-", ""));

		List<WikMnthngReprtVO> resultList = mapper.selectWikMnthngReprtList(wikMnthngReprtVO);
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
	 * @param wikMnthngReprtVO - 주간월간보고 VO
	 * @return WikMnthngReprtVO - 주간월간보고 VO
	 */
	public WikMnthngReprtVO selectWikMnthngReprt(WikMnthngReprtVO wikMnthngReprtVO) throws Exception {
		WikMnthngReprtVO resultVO = mapper.selectWikMnthngReprt(wikMnthngReprtVO);
		resultVO.setReprtDe(EgovDateUtil.convertDate(resultVO.getReprtDe(), "0000", "yyyy-MM-dd"));
		resultVO.setReprtBgnDe(EgovDateUtil.convertDate(resultVO.getReprtBgnDe(), "0000", "yyyy-MM-dd"));
		resultVO.setReprtEndDe(EgovDateUtil.convertDate(resultVO.getReprtEndDe(), "0000", "yyyy-MM-dd"));
		return resultVO;
	}

	/**
	 * 주간월간보고 정보를 수정한다.
	 * @param wikMnthngReprt - 주간월간보고 model
	 */
	public void updateWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception {
		//날짜관련
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		wikMnthngReprt.setReprtDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtBgnDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtBgnDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtEndDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtEndDe()).replaceAll("-", ""));
		mapper.updateWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 등록한다.
	 * @param wikMnthngReprt - 주간월간보고 model
	 */
	public void insertWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception {
		//날짜관련
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		wikMnthngReprt.setReprtDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtBgnDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtBgnDe()).replaceAll("-", ""));
		wikMnthngReprt.setReprtEndDe(EgovStringUtil.isNullToString(wikMnthngReprt.getReprtEndDe()).replaceAll("-", ""));
		mapper.insertWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 삭제한다.
	 * @param wikMnthngReprt - 주간월간보고 model
	 */
	public void deleteWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception {
		mapper.deleteWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 정보를 승인한다.
	 * @param wikMnthngReprt - 주간월간보고 model
	 */
	public void confirmWikMnthngReprt(WikMnthngReprt wikMnthngReprt) throws Exception {
		mapper.confirmWikMnthngReprt(wikMnthngReprt);
	}

	/**
	 * 주간월간보고 목록에 대한 전체 건수를 조회한다.
	 * @param wikMnthngReprtVO - 주간월간보고 VO
	 * @return int - 주간월간보고 목록 개수
	 */
	public int selectWikMnthngReprtListCnt(WikMnthngReprtVO wikMnthngReprtVO) throws Exception {
		//날짜관련
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		wikMnthngReprtVO.setSearchBgnDe(EgovStringUtil.isNullToString(wikMnthngReprtVO.getSearchBgnDe()).replaceAll("-", ""));
		wikMnthngReprtVO.setSearchEndDe(EgovStringUtil.isNullToString(wikMnthngReprtVO.getSearchEndDe()).replaceAll("-", ""));
		return mapper.selectWikMnthngReprtListCnt(wikMnthngReprtVO);
	}

}
