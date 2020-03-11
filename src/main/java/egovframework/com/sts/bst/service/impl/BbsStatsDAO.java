package egovframework.com.sts.bst.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sts.com.StatsVO;

import org.springframework.stereotype.Repository;

/**
 * 게시물 통계 검색 DAO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.19  박지욱          최초 생성
 *  2011.06.30  이기하          패키지 분리(sts -> sts.bst)
 *  2018.05.02  신용호          summaryBbsStats() 수정
 *                        게시판유형별 코드분류 변경 (COM004 => COM101)
 *                        게시판속성별(COM009) 코드분류 사용하지 않음
 *
 *  </pre>
 */
@Repository("bbsStatsDAO")
public class BbsStatsDAO extends EgovComAbstractDAO {

	/**
	 * 게시물 생성글수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectBbsCretCntStats(StatsVO vo) throws Exception {
        return selectList("BbsStatsDAO.selectBbsCretCntStats", vo);
    }

    /**
	 * 게시물 총조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectBbsTotCntStats(StatsVO vo) throws Exception {
        return selectList("BbsStatsDAO.selectBbsTotCntStats", vo);
    }

    /**
	 * 게시물 평균조회수 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectBbsAvgCntStats(StatsVO vo) throws Exception {
        return selectList("BbsStatsDAO.selectBbsAvgCntStats", vo);
    }

    /**
	 * 최고조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectBbsMaxCntStats(StatsVO vo) throws Exception {
        return selectList("BbsStatsDAO.selectBbsMaxCntStats", vo);
    }

    /**
	 * 최소조회 게시물 통계정보를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectBbsMinCntStats(StatsVO vo) throws Exception {
        return selectList("BbsStatsDAO.selectBbsMinCntStats", vo);
    }

    /**
	 * 게시물 최고게시자 통계를 조회한다
	 * @param vo StatsVO
	 * @return List
	 * @exception Exception
	 */
    public List<?> selectBbsMaxUserStats(StatsVO vo) throws Exception {
        return selectList("BbsStatsDAO.selectBbsMaxUserStats", vo);
    }

    /**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
    public void summaryBbsStats() throws Exception {

    	StatsVO parVO = new StatsVO();

    	StatsVO sumVO = null;
    	StatsVO resultVO = null;

    	// 게시판 유형별
    	// 1. 통합게시판
    	sumVO = new StatsVO();
    	sumVO.setStatsKind("COM101");
    	sumVO.setDetailStatsKind("BBST01");
    	parVO.setStatsKind("COM101");
    	parVO.setDetailStatsKind("BBST01");
    	// 1-0. 집계 여부 조회
    	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsSummary", parVO);
    	if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
    		// 1-1. 생성글수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
            if (resultVO != null)
        		sumVO.setCreatCo(resultVO.getCreatCo());
        	else
        		sumVO.setCreatCo(0);
            // 1-2. 총조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTotInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setTotInqireCo(resultVO.getTotInqireCo());
        	else
        		sumVO.setTotInqireCo(0);
            // 1-3. 평균조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsAvrgInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
        	else
        		sumVO.setAvrgInqireCo(0);
            // 1-4. 최고조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMxmmInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
        		sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
        	else
        		sumVO.setMxmmInqireBbsId("");
            // 1-5. 최소조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMummInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMummInqireBbsId() != null)
        		sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
        	else
        		sumVO.setMummInqireBbsId("");
            // 1-6. 최고게시자ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTopNtcepersonId", parVO);
        	if (resultVO != null && resultVO.getTopNtcepersonId() != null)
        		sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
        	else
        		sumVO.setTopNtcepersonId("");

        	// 1-7. 집계 등록
        	insert("BbsStatsDAO.summaryBbsStats", sumVO);
    	}

    	// 2. 블로그게시판
    	sumVO = new StatsVO();
    	sumVO.setStatsKind("COM101");
    	sumVO.setDetailStatsKind("BBST02");
    	parVO.setStatsKind("COM101");
    	parVO.setDetailStatsKind("BBST02");
    	// 2-0. 집계 여부 조회
    	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsSummary", parVO);
    	if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
    		// 2-1. 생성글수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
            if (resultVO != null)
        		sumVO.setCreatCo(resultVO.getCreatCo());
        	else
        		sumVO.setCreatCo(0);
            // 2-2. 총조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTotInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setTotInqireCo(resultVO.getTotInqireCo());
        	else
        		sumVO.setTotInqireCo(0);
            // 2-3. 평균조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsAvrgInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
        	else
        		sumVO.setAvrgInqireCo(0);
            // 2-4. 최고조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMxmmInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
        		sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
        	else
        		sumVO.setMxmmInqireBbsId("");
            // 2-5. 최소조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMummInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMummInqireBbsId() != null)
        		sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
        	else
        		sumVO.setMummInqireBbsId("");
            // 2-6. 최고게시자ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTopNtcepersonId", parVO);
        	if (resultVO != null && resultVO.getTopNtcepersonId() != null)
        		sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
        	else
        		sumVO.setTopNtcepersonId("");

        	// 2-7. 집계 등록
        	insert("BbsStatsDAO.summaryBbsStats", sumVO);
    	}

    	// 3. 방명록
    	sumVO = new StatsVO();
    	sumVO.setStatsKind("COM101");
    	sumVO.setDetailStatsKind("BBST03");
    	parVO.setStatsKind("COM101");
    	parVO.setDetailStatsKind("BBST03");
    	// 3-0. 집계 여부 조회
    	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsSummary", parVO);
    	if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
    		// 3-1. 생성글수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
            if (resultVO != null)
        		sumVO.setCreatCo(resultVO.getCreatCo());
        	else
        		sumVO.setCreatCo(0);
            // 3-2. 총조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTotInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setTotInqireCo(resultVO.getTotInqireCo());
        	else
        		sumVO.setTotInqireCo(0);
            // 3-3. 평균조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsAvrgInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
        	else
        		sumVO.setAvrgInqireCo(0);
            // 3-4. 최고조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMxmmInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
        		sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
        	else
        		sumVO.setMxmmInqireBbsId("");
            // 3-5. 최소조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMummInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMummInqireBbsId() != null)
        		sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
        	else
        		sumVO.setMummInqireBbsId("");
            // 3-6. 최고게시자ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTopNtcepersonId", parVO);
        	if (resultVO != null && resultVO.getTopNtcepersonId() != null)
        		sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
        	else
        		sumVO.setTopNtcepersonId("");

        	// 3-7. 집계 등록
        	insert("BbsStatsDAO.summaryBbsStats", sumVO);
    	}


    	// 게시판 템플릿별
    	// 1. 게시판
    	sumVO = new StatsVO();
    	sumVO.setStatsKind("COM005");
    	sumVO.setDetailStatsKind("TMPT01");
    	parVO.setStatsKind("COM005");
    	parVO.setDetailStatsKind("TMPT01");
    	// 1-0. 집계 여부 조회
    	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsSummary", parVO);
    	if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
    		// 1-1. 생성글수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
            if (resultVO != null)
        		sumVO.setCreatCo(resultVO.getCreatCo());
        	else
        		sumVO.setCreatCo(0);
            // 1-2. 총조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTotInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setTotInqireCo(resultVO.getTotInqireCo());
        	else
        		sumVO.setTotInqireCo(0);
            // 1-3. 평균조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsAvrgInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
        	else
        		sumVO.setAvrgInqireCo(0);
            // 1-4. 최고조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMxmmInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
        		sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
        	else
        		sumVO.setMxmmInqireBbsId("");
            // 1-5. 최소조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMummInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMummInqireBbsId() != null)
        		sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
        	else
        		sumVO.setMummInqireBbsId("");
            // 1-6. 최고게시자ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTopNtcepersonId", parVO);
        	if (resultVO != null && resultVO.getTopNtcepersonId() != null)
        		sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
        	else
        		sumVO.setTopNtcepersonId("");

        	// 1-7. 집계 등록
        	insert("BbsStatsDAO.summaryBbsStats", sumVO);
    	}

    	// 2. 커뮤니티
    	sumVO = new StatsVO();
    	sumVO.setStatsKind("COM005");
    	sumVO.setDetailStatsKind("TMPT02");
    	parVO.setStatsKind("COM005");
    	parVO.setDetailStatsKind("TMPT02");
    	// 2-0. 집계 여부 조회
    	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsSummary", parVO);
    	if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
    		// 2-1. 생성글수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
            if (resultVO != null)
        		sumVO.setCreatCo(resultVO.getCreatCo());
        	else
        		sumVO.setCreatCo(0);
            // 2-2. 총조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTotInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setTotInqireCo(resultVO.getTotInqireCo());
        	else
        		sumVO.setTotInqireCo(0);
            // 2-3. 평균조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsAvrgInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
        	else
        		sumVO.setAvrgInqireCo(0);
            // 2-4. 최고조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMxmmInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
        		sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
        	else
        		sumVO.setMxmmInqireBbsId("");
            // 2-5. 최소조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMummInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMummInqireBbsId() != null)
        		sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
        	else
        		sumVO.setMummInqireBbsId("");
            // 2-6. 최고게시자ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTopNtcepersonId", parVO);
        	if (resultVO != null && resultVO.getTopNtcepersonId() != null)
        		sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
        	else
        		sumVO.setTopNtcepersonId("");

        	// 2-7. 집계 등록
        	insert("BbsStatsDAO.summaryBbsStats", sumVO);
    	}

    	// 3. 동호회
    	sumVO = new StatsVO();
    	sumVO.setStatsKind("COM005");
    	sumVO.setDetailStatsKind("TMPT03");
    	parVO.setStatsKind("COM005");
    	parVO.setDetailStatsKind("TMPT03");
    	// 3-0. 집계 여부 조회
    	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsSummary", parVO);
    	if (resultVO == null || resultVO.getStatsKind() == null || "".equals(resultVO.getStatsKind())) {
    		// 3-1. 생성글수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsCreatCo", parVO);
            if (resultVO != null)
        		sumVO.setCreatCo(resultVO.getCreatCo());
        	else
        		sumVO.setCreatCo(0);
            // 3-2. 총조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTotInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setTotInqireCo(resultVO.getTotInqireCo());
        	else
        		sumVO.setTotInqireCo(0);
            // 3-3. 평균조회수
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsAvrgInqireCo", parVO);
        	if (resultVO != null)
        		sumVO.setAvrgInqireCo(resultVO.getAvrgInqireCo());
        	else
        		sumVO.setAvrgInqireCo(0);
            // 3-4. 최고조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMxmmInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMxmmInqireBbsId() != null)
        		sumVO.setMxmmInqireBbsId(resultVO.getMxmmInqireBbsId());
        	else
        		sumVO.setMxmmInqireBbsId("");
            // 3-5. 최소조회게시물ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsMummInqireBbsId", parVO);
        	if (resultVO != null && resultVO.getMummInqireBbsId() != null)
        		sumVO.setMummInqireBbsId(resultVO.getMummInqireBbsId());
        	else
        		sumVO.setMummInqireBbsId("");
            // 3-6. 최고게시자ID
        	resultVO = (StatsVO)selectOne("BbsStatsDAO.selectBbsTopNtcepersonId", parVO);
        	if (resultVO != null && resultVO.getTopNtcepersonId() != null)
        		sumVO.setTopNtcepersonId(resultVO.getTopNtcepersonId());
        	else
        		sumVO.setTopNtcepersonId("");

        	// 3-7. 집계 등록
        	insert("BbsStatsDAO.summaryBbsStats", sumVO);
    	}
    }
}
