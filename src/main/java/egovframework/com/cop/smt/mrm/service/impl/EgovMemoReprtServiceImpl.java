package egovframework.com.cop.smt.mrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.com.cop.smt.mrm.service.EgovMemoReprtService;
import egovframework.com.cop.smt.mrm.service.MemoReprt;
import egovframework.com.cop.smt.mrm.service.MemoReprtVO;
import egovframework.com.cop.smt.mrm.service.ReportrVO;

/**
 * <pre>
 * 개요
 * 메모보고에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 메모보고에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모보고의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:14:53
 * 
 *          <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  장철호          최초 생성
 *   2024.09.12  이백행          컨트리뷰션 시큐어코딩 Exception 제거
 *
 *          </pre>
 */
@Service("EgovMemoReprtService")
public class EgovMemoReprtServiceImpl extends EgovAbstractServiceImpl implements EgovMemoReprtService {

	@Resource(name = "MemoReprtDAO")
	private MemoReprtDAO memoReprtDAO;

	@Resource(name = "egovMemoReprtIdGnrService")
	private EgovIdGnrService idgenServiceMemoReprt;

	/**
	 * 보고자 목록을 조회한다.
	 * 
	 * @param ReportrVO
	 * @return Map<String, Object>
	 * 
	 * @param reportrVO
	 */
	@Override
	public Map<String, Object> selectReportrList(ReportrVO reportrVO) {
		List<ReportrVO> result = memoReprtDAO.selectReportrList(reportrVO);
		int cnt = memoReprtDAO.selectReportrListCnt(reportrVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 사용자 직위명을 정보를 조회한다.
	 * 
	 * @param String
	 * @return String
	 * 
	 * @param String
	 */
	@Override
	public String selectWrterClsfNm(String wrterId) {
		return memoReprtDAO.selectWrterClsfNm(wrterId);
	}

	/**
	 * 메모보고 목록을 조회한다.
	 * 
	 * @param MemoReprtVO - 메모보고 VO
	 * @return Map<String, Object> - 메모보고 List
	 * 
	 * @param memoReprtVO
	 */
	@Override
	public Map<String, Object> selectMemoReprtList(MemoReprtVO memoReprtVO) {
		List<MemoReprtVO> result = memoReprtDAO.selectMemoReprtList(memoReprtVO);
		int cnt = memoReprtDAO.selectMemoReprtListCnt(memoReprtVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	/**
	 * 메모보고 정보를 조회한다.
	 * 
	 * @param MemoReprtVO - 메모보고 VO
	 * @return MemoReprtVO - 메모보고 VO
	 * 
	 * @param memoReprtVO
	 */
	@Override
	public MemoReprtVO selectMemoReprt(MemoReprtVO memoReprtVO) {
		MemoReprtVO resultVO = memoReprtDAO.selectMemoReprt(memoReprtVO);
		if (resultVO.getReportrInqireDt() == null || resultVO.getReportrInqireDt().equals("")) {
			resultVO.setReprtSttus("미확인");
		} else {
			String year = resultVO.getReportrInqireDt().substring(0, 4);
			String month = resultVO.getReportrInqireDt().substring(4, 6);
			String day = resultVO.getReportrInqireDt().substring(6, 8);
			String hour = resultVO.getReportrInqireDt().substring(8, 10);
			String min = resultVO.getReportrInqireDt().substring(10, 12);

			String yymmddhhmm = year + "/" + month + "/" + day + "  " + hour + "시 " + min + "분";
			resultVO.setReprtSttus("확인 (" + yymmddhhmm + ") ");
		}

		return resultVO;
	}

	/**
	 * 메모보고 정보의 보고자 조회일시를 수정한다.
	 * 
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	@Override
	public void readMemoReprt(MemoReprt memoReprt) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		memoReprt.setReportrInqireDt(formatter.format(new java.util.Date()));
		memoReprtDAO.readMemoReprt(memoReprt);
	}

	/**
	 * 메모보고 정보를 수정한다.
	 * 
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	@Override
	public void updateMemoReprt(MemoReprt memoReprt) {
		memoReprtDAO.updateMemoReprt(memoReprt);
	}

	/**
	 * 메모보고 정보의 지시사항을 등록한다.
	 * 
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	@Override
	public void updateMemoReprtDrctMatter(MemoReprt memoReprt) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.KOREA);
		memoReprt.setDrctMatterRegistDt(formatter.format(new java.util.Date()));
		memoReprtDAO.updateMemoReprtDrctMatter(memoReprt);
	}

	/**
	 * 메모보고 정보를 등록한다.
	 * 
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 * @throws Exception
	 */
	@Override
	public void insertMemoReprt(MemoReprt memoReprt) throws Exception {
		try {
			memoReprt.setReprtId(idgenServiceMemoReprt.getNextStringId());
		} catch (FdlException e) {
			throw processException("FdlException: egovMemoReprtIdGnrService", e);
		}
		memoReprtDAO.insertMemoReprt(memoReprt);
	}

	/**
	 * 메모보고 정보를 삭제한다.
	 * 
	 * @param MemoReprt - 메모보고 model
	 * 
	 * @param memoReprt
	 */
	@Override
	public void deleteMemoReprt(MemoReprt memoReprt) {
		memoReprtDAO.deleteMemoReprt(memoReprt);
	}

}