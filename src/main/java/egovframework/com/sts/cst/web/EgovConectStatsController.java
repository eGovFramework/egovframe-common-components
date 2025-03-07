package egovframework.com.sts.cst.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sts.com.StatsVO;
import egovframework.com.sts.cst.service.EgovConectStatsService;

/**
 * 접속 통계 검색 컨트롤러 클래스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.cst)
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */


@Controller
public class EgovConectStatsController {

	/** EgovConectStatsService */
	@Resource(name = "conectStatsService")
    private EgovConectStatsService conectStatsService;

	/** EgovCmmUseService */
/*	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;*/

    /**
	 * 접속 통계를 조회한다
	 * @param statsVO StatsVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="접속통계", listUrl="/sts/cst/selectConectStats.do", order = 140 ,gid = 30)
    @RequestMapping(value="/sts/cst/selectConectStats.do")
	public String selectUserStats(@ModelAttribute("statsVO") StatsVO statsVO,
			ModelMap model) throws Exception {

		if (statsVO.getFromDate() != null && !"".equals(statsVO.getFromDate())) {

			List<StatsVO> conectStats = conectStatsService.selectConectStats(statsVO);

			// 1. 서비스별
			if ("SERVICE".equals(statsVO.getStatsKind())) {
				model.addAttribute("conectStats", conectStats);
				model.addAttribute("statsInfo", statsVO);
			// 2. 개인별
			} else {
				// 그래프에 표시될 이미지 길이를 결정한다.
				float iMaxUnit = 50.0f;
				for (int i = 0; i < conectStats.size(); i++) {
					StatsVO vo = (StatsVO)conectStats.get(i);
					int iCnt = vo.getStatsCo();
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
				model.addAttribute("conectStats", conectStats);
				model.addAttribute("statsInfo", statsVO);
			}
			if (GenericValidator.isDate(statsVO.getFromDate(), "yyyyMMdd", true)) {
                model.addAttribute("fDate", (LocalDate.parse(statsVO.getFromDate(), DateTimeFormatter.BASIC_ISO_DATE).format(DateTimeFormatter.ISO_LOCAL_DATE)));
            }
            if (GenericValidator.isDate(statsVO.getToDate(), "yyyyMMdd", true)) {
                model.addAttribute("tDate", (LocalDate.parse(statsVO.getToDate(), DateTimeFormatter.BASIC_ISO_DATE).format(DateTimeFormatter.ISO_LOCAL_DATE)));
            }
		}
        return "egovframework/com/sts/cst/EgovConectStats";
	}
}