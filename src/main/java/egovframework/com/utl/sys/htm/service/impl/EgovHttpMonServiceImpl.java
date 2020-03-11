package egovframework.com.utl.sys.htm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.utl.sys.htm.service.EgovHttpMonService;
import egovframework.com.utl.sys.htm.service.HttpMon;
import egovframework.com.utl.sys.htm.service.HttpMonLog;
import egovframework.com.utl.sys.htm.service.HttpMonLogVO;
import egovframework.com.utl.sys.htm.service.HttpMonVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - HTTP서비스모니터링에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - HTTP서비스모니터링에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - HTTP서비스모니터링의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 박종선
 * @version 1.0
 * @created 17-6-2010 오후 5:12:43
 */
@Service("EgovHttpMonService")
public class EgovHttpMonServiceImpl extends EgovAbstractServiceImpl implements EgovHttpMonService {

    @Resource(name="HttpMonDAO")
    private HttpMonDAO HttpMonDAO;

    /** ID Generation */
	@Resource(name="egovHttpManageIdGnrService")
	private EgovIdGnrService idgenService;

    /** ID Generation */
	@Resource(name="egovHttpLogManageIdGnrService")
	private EgovIdGnrService idgenServiceLog;

	/**
	 * 등록된 HTTP서비스모니터링 목록을 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 *
	 * @param httpMonVO
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectHttpMonList(HttpMonVO searchVO) throws Exception {
        return HttpMonDAO.selectHttpMonList(searchVO);
	}

	/**
	 * HTTP서비스모니터링 목록 총 갯수를 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return int - HTTP서비스 토탈 카운트 수
	 *
	 * @param httpMonVO
	 */
	@Override
	public int selectHttpMonTotCnt(HttpMonVO searchVO) throws Exception {
        return HttpMonDAO.selectHttpMonTotCnt(searchVO);
	}

	/**
	 * 등록된 HTTP서비스모니터링의 상세정보를 조회한다.
	 * @param httpMonVO - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 *
	 * @param httpMonVO
	 */
	@Override
	public HttpMon selectHttpMonDetail(HttpMon httpMon) throws Exception {
		HttpMon ret = HttpMonDAO.selectHttpMonDetail(httpMon);
    	return ret;
	}

	/**
	 * HTTP서비스모니터링 정보를 신규로 등록한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 *
	 * @param siteUrl
	 */
	@Override
	public void insertHttpMon(HttpMon httpMon) throws Exception {
		httpMon.setSysId(idgenService.getNextStringId());
		HttpMonDAO.insertHttpMon(httpMon);
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 수정한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 *
	 * @param siteUrl
	 */
	@Override
	public void updateHttpMon(HttpMon httpMon) throws Exception {
		HttpMonDAO.updateHttpMon(httpMon);
	}

	/**
	 * 기 등록된 HTTP서비스모니터링 정보를 삭제한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 *
	 * @param siteUrl
	 */
	@Override
	public void deleteHttpMon(HttpMon httpMon) throws Exception {
		HttpMonDAO.deleteHttpMon(httpMon);
	}

	/**
	 * 등록된 HTTP서비스모니터링로그 목록을 조회한다.
	 * @param HttpMonVO - HTTP서비스모니터링 Vo
	 * @return List - HTTP서비스모니터링 목록
	 *
	 * @param httpMonVO
	 */
	@Override
	public Map<String, Object> selectHttpMonLogList(HttpMonLogVO httpMonLogVO) throws Exception {

		List<HttpMonLogVO> result = HttpMonDAO.selectHttpMonLogList(httpMonLogVO);
		int cnt = HttpMonDAO.selectHttpMonLogTotCnt(httpMonLogVO);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;

	}

	/**
	 * 등록된 HTTP서비스모니터링로그의 상세정보를 조회한다.
	 * @param httpMonVO - HTTP서비스모니터링 Vo
	 * @return httpMonVO - HTTP서비스모니터링 Vo
	 *
	 * @param httpMonVO
	 */
	@Override
	public HttpMonLog selectHttpMonDetailLog(HttpMonLog httpMonLog) throws Exception {
		return HttpMonDAO.selectHttpMonDetailLog(httpMonLog);
	}

	/**
	 * HTTP서비스모니터링로그 정보를 등록한다.
	 * @param siteUrl - HTTP서비스모니터링 model
	 *
	 * @param siteUrl
	 */
	@Override
	public void insertHttpMonLog(HttpMonLog httpMonLog) throws Exception {
		HttpMonDAO.insertHttpMonLog(httpMonLog);
	}

	/**
	 * HTTP서비스 모니터링 결과를 수정한다.
	 * @param httpMonLog - HTTP서비스 모니터링대상 model
	 *
	 * @param httpMonLog
	 */
	@Override
	public void updateHttpMonSttus(HttpMon httpMon) throws Exception{
		HttpMonDAO.updateHttpMonSttus(httpMon);

		HttpMonLog httpMonLog = new HttpMonLog();
		httpMonLog.setSysId(httpMon.getSysId());
		httpMonLog.setLogId(idgenServiceLog.getNextStringId());
		httpMonLog.setWebKind(httpMon.getWebKind());
		httpMonLog.setSiteUrl(httpMon.getSiteUrl());
		httpMonLog.setHttpSttusCd(httpMon.getHttpSttusCd());
		httpMonLog.setCreatDt(httpMon.getCreatDt());
		httpMonLog.setLogInfo(httpMon.getLogInfo());
		httpMonLog.setMngrNm(httpMon.getMngrNm());
		httpMonLog.setMngrEmailAddr(httpMon.getMngrEmailAddr());
		httpMonLog.setFrstRegisterId(httpMon.getFrstRegisterId());
		httpMonLog.setFrstRegisterPnttm(httpMon.getFrstRegisterPnttm());
		httpMonLog.setLastUpdusrId(httpMon.getLastUpdusrId());
		insertHttpMonLog(httpMonLog);
	}

}
