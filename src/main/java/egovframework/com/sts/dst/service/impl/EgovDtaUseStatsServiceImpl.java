/**
 * 개요
 * - 자료이용현황 통계에 대한 ServiceImpl를 정의한다.
 *
 * 상세내용
 * - 자료이용현황 통계에 대한 등록, 조회 기능을 제공한다.
 * - 자료이용현황 통계의 조회기능은 목록조회, 상세조회로 구분된다.
 * - 게시판에서 다운로드한 통계만 적용된다.(게시판이 아닌경우는 통계에서 제외함)
 * @author lee.m.j
 * @version 1.0
 * @created 08-9-2009 오후 1:40:19
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.8.23  정진오 		SQL Map에서 정의한 파라미터 클래스와 실제 전달하는 클래스가 달라서 발생하는 에러 수정
 *   						new DtaUseStats() -> new DtaUseStatsVO()
 *   2011.9.29	이기하		게시판외 다운로드시 에러발생(dtaUseStats 값이 null)을 방지
 *
 * </pre>
 */

package egovframework.com.sts.dst.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sts.dst.service.DtaUseStats;
import egovframework.com.sts.dst.service.DtaUseStatsVO;
import egovframework.com.sts.dst.service.EgovDtaUseStatsService;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;

@Service("egovDtaUseStatsService")
public class EgovDtaUseStatsServiceImpl extends EgovAbstractServiceImpl implements EgovDtaUseStatsService {




	@Resource(name="dtaUseStatsDAO")
	DtaUseStatsDAO dtaUseStatsDAO;

	@Resource(name="egovDtaUseStatsIdGnrService")
    private EgovIdGnrService egovDtaUseStatsIdGnrService;

	/**
	 * 자료이용현황 통계정보의 대상목록을 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return List - 자료이용현황 목록
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsList(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return dtaUseStatsDAO.selectDtaUseStatsList(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 대상목록 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsListTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return dtaUseStatsDAO.selectDtaUseStatsListTotCnt(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 전체 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsListBarTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return dtaUseStatsDAO.selectDtaUseStatsListBarTotCnt(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계의 상세정보를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return reprtStatsVO - 자료이용현황 VO
	 */
	public List<DtaUseStatsVO> selectDtaUseStats(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return dtaUseStatsDAO.selectDtaUseStats(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 통계정보의 상세정보목록 카운트를 조회한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return int
	 */
	public int selectDtaUseStatsTotCnt(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return dtaUseStatsDAO.selectDtaUseStatsTotCnt(dtaUseStatsVO);
	}

	/**
	 * 자료이용현황 정보를 생성한다.
	 * @param jp - AOP의 pointcut을 위한 JoinPoint
	 * @param dtaUseStats - 자료이용현황 model
	 */
    public void insertDtaUseStats(JoinPoint jp, @RequestParam Map<String, Object> commandMap) throws Exception {

    	String atchFileId = (String)commandMap.get("atchFileId");
    	String fileSn = (String)commandMap.get("fileSn");

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		DtaUseStats dtaUseStats = new DtaUseStatsVO();	//2011.08.23 수정 부분
		dtaUseStats.setAtchFileId(atchFileId);
		dtaUseStats.setFileSn(fileSn);

		dtaUseStats = dtaUseStatsDAO.selectInsertDtaUseStats(dtaUseStats);

		// 2011.09.29 게시판외 다운로드시 에러발생(dtaUseStats 값이 null)을 방지
		if (dtaUseStats != null) {
			DtaUseStats vo = new DtaUseStatsVO();			//2011.08.23 수정 부분
			String id = user == null ? "" : EgovStringUtil.isNullToString(user.getId()); // KISA 보안약점 조치 (2018-12-11, 신용호)
			vo.setDtaUseStatsId(egovDtaUseStatsIdGnrService.getNextStringId());
			vo.setBbsId(dtaUseStats.getBbsId());
			vo.setNttId(dtaUseStats.getNttId());
	        vo.setAtchFileId(atchFileId);
	        vo.setFileSn(fileSn);
			vo.setUserId(id);

			dtaUseStatsDAO.insertDtaUseStats(vo);
		}
	}

	/**
	 * 등록일자별 통계정보를 그래프로 표현한다.
	 * @param dtaUseStatsVO - 자료이용현황 VO
	 * @return List - 등록일자별 자료이용현황 목록
	 */
	public List<DtaUseStatsVO> selectDtaUseStatsBarList(DtaUseStatsVO dtaUseStatsVO) throws Exception {
		return dtaUseStatsDAO.selectDtaUseStatsBarList(dtaUseStatsVO);
	}

}
