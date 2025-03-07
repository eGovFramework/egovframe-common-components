package egovframework.com.sts.bst.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.sts.bst.service.EgovBbsStatsService;
import egovframework.com.sts.com.StatsVO;

/**
 * 게시물 통계 검색 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.19
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
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *  2018.05.02  신용호          게시판유형별 코드분류 변경 (COM004 => COM101)
 *                        게시판속성별(COM009) 코드분류 사용하지 않음
 *
 *  </pre>
 */


@Controller
public class EgovBbsStatsController {

	/** EgovBbsStatsService */
	@Resource(name = "bbsStatsService")
    private EgovBbsStatsService bbsStatsService;

	/** EgovCmmUseService */
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /**
	 * 게시물 통계를 조회한다
	 * @param statsVO StatsVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="게시물통계", listUrl="/sts/bst/selectBbsStats.do", order = 120 ,gid = 30)
    @RequestMapping(value="/sts/bst/selectBbsStats.do")
	public String selectBbsStats(@ModelAttribute("statsVO") StatsVO statsVO,
			ModelMap model) throws Exception {

    	// 세부통계구분 공통코드 목록 조회(게시판유형,속성에 대한 세부통계구분코드)
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();

    	vo.setCodeId("COM101");
		List<CmmnDetailCode> code004 = cmmUseService.selectCmmCodeDetail(vo);
		vo.setCodeId("COM005");
		List<CmmnDetailCode> code005 = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("COM101", code004);
		model.addAttribute("COM005", code005);

		if (statsVO.getFromDate() != null && !"".equals(statsVO.getFromDate())) {

			// 탭구분 : 생성글수(tab1), 총조회수(tab2), 평균조회수(tab3), 최고/최소조회수(tab4), 최고게시자(tab5)
			List<StatsVO> bbsStatsList = null;
			List<StatsVO> bbsMaxStatsList = null;
			List<StatsVO> bbsMinStatsList = null;
			List<StatsVO> bbsMaxNtcrList = null;

			// 1. 생성글수(tab1)
			if ("tab1".equals(statsVO.getTabKind())) {
				// 생성글수 조회
				bbsStatsList = bbsStatsService.selectBbsCretCntStats(statsVO);
				// 그래프 길이 설정
				float iMaxUnit = 50.0f;
				for (int i = 0; i < bbsStatsList.size(); i++) {
					StatsVO sVO = (StatsVO)bbsStatsList.get(i);
					int iCnt = sVO.getStatsCo();
					if (iCnt > 10 && iCnt <= 100) {
						if (iMaxUnit > 5.0f) {
							iMaxUnit = 5.0f;
						}
					} else if (iCnt > 100 && iCnt <= 1000) {
						if (iMaxUnit > 0.5f) {
							iMaxUnit = 0.5f;
						}
					} else if (iCnt > 1000) {
						if (iMaxUnit > 0.05f) {
							iMaxUnit = 0.05f;
						}
					}
				}
				statsVO.setMaxUnit(iMaxUnit);
				// 결과 리턴
				model.addAttribute("bbsStatsList", bbsStatsList);
				model.addAttribute("statsInfo", statsVO);

			// 2. 총조회수(tab2)
			} else if ("tab2".equals(statsVO.getTabKind())) {
				// 총조회수 조회
				bbsStatsList = bbsStatsService.selectBbsTotCntStats(statsVO);
				// 그래프 길이 설정
				float iMaxUnit = 50.0f;
				for (int i = 0; i < bbsStatsList.size(); i++) {
					StatsVO sVO = (StatsVO)bbsStatsList.get(i);
					int iCnt = sVO.getStatsCo();
					if (iCnt > 10 && iCnt <= 100) {
						if (iMaxUnit > 5.0f) {
							iMaxUnit = 5.0f;
						}
					} else if (iCnt > 100 && iCnt <= 1000) {
						if (iMaxUnit > 0.5f) {
							iMaxUnit = 0.5f;
						}
					} else if (iCnt > 1000) {
						if (iMaxUnit > 0.05f) {
							iMaxUnit = 0.05f;
						}
					}
				}
				statsVO.setMaxUnit(iMaxUnit);
				// 결과 리턴
				model.addAttribute("bbsStatsList", bbsStatsList);
				model.addAttribute("statsInfo", statsVO);

			// 3. 평균조회수(tab3)
			} else if ("tab3".equals(statsVO.getTabKind())) {
				// 평균조회수 조회
				bbsStatsList = bbsStatsService.selectBbsAvgCntStats(statsVO);
				// 그래프 길이 설정
				float iMaxUnit = 50.0f;
				for (int i = 0; i < bbsStatsList.size(); i++) {
					StatsVO sVO = (StatsVO)bbsStatsList.get(i);
					int iCnt = (int)sVO.getAvrgInqireCo();
					sVO.setStatsCo(iCnt);
					if (iCnt > 10 && iCnt <= 100) {
						if (iMaxUnit > 5.0f) {
							iMaxUnit = 5.0f;
						}
					} else if (iCnt > 100 && iCnt <= 1000) {
						if (iMaxUnit > 0.5f) {
							iMaxUnit = 0.5f;
						}
					} else if (iCnt > 1000) {
						if (iMaxUnit > 0.05f) {
							iMaxUnit = 0.05f;
						}
					}
				}
				statsVO.setMaxUnit(iMaxUnit);
				// 결과 리턴
				model.addAttribute("bbsStatsList", bbsStatsList);
				model.addAttribute("statsInfo", statsVO);

			// 4. 최고/최소조회수(tab4)
			} else if ("tab4".equals(statsVO.getTabKind())) {
				// 최고게시글 정보 조회
				bbsMaxStatsList = bbsStatsService.selectBbsMaxCntStats(statsVO);
				// 최소게시글 정보 조회
				bbsMinStatsList = bbsStatsService.selectBbsMinCntStats(statsVO);
				// 결과 리턴
				model.addAttribute("bbsMaxStatsList", bbsMaxStatsList);
				model.addAttribute("bbsMinStatsList", bbsMinStatsList);
				model.addAttribute("statsInfo", statsVO);

			// 5. 최고게시자(tab5)
			} else if ("tab5".equals(statsVO.getTabKind())) {

				bbsMaxNtcrList = bbsStatsService.selectBbsMaxUserStats(statsVO);
				// 결과 리턴
				model.addAttribute("bbsMaxNtcrList", bbsMaxNtcrList);
				model.addAttribute("statsInfo", statsVO);
			}
			if (GenericValidator.isDate(statsVO.getFromDate(), "yyyyMMdd", true)) {
                model.addAttribute("fDate", (LocalDate.parse(statsVO.getFromDate(), DateTimeFormatter.BASIC_ISO_DATE).format(DateTimeFormatter.ISO_LOCAL_DATE)));
            }
            if (GenericValidator.isDate(statsVO.getToDate(), "yyyyMMdd", true)) {
                model.addAttribute("tDate", (LocalDate.parse(statsVO.getToDate(), DateTimeFormatter.BASIC_ISO_DATE).format(DateTimeFormatter.ISO_LOCAL_DATE)));
            }
		} else {
			statsVO.setTabKind("tab1");
			model.addAttribute("statsInfo", statsVO);
		}

        return "egovframework/com/sts/bst/EgovBbsStats";
	}
}