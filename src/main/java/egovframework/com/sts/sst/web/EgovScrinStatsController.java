package egovframework.com.sts.sst.web;

import java.util.List;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sts.com.StatsVO;
import egovframework.com.sts.sst.service.EgovScrinStatsService;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 화면 통계 검색 컨트롤러 클래스
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
 *  2011.06.30  이기하          패키지 분리(sts -> sts.sst)
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 *
 *  </pre>
 */

@Controller
public class EgovScrinStatsController {

	/** EgovConectStatsService */
	@Resource(name = "scrinStatsService")
    private EgovScrinStatsService scrinStatsService;

	/** EgovMenuManageService */
	@Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;

    /**
	 * 화면 통계를 조회한다
	 * @param statsVO StatsVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="화면통계", listUrl="/sts/sst/selectScrinStats.do", order = 150 ,gid = 30)
    @RequestMapping(value="/sts/sst/selectScrinStats.do")
	public String selectUserStats(@ModelAttribute("statsVO") StatsVO statsVO,
			ModelMap model) throws Exception {

    	// 트리메뉴 조회
    	List<?> list_menulist = menuManageService.selectMenuList();
        model.addAttribute("list_menulist", list_menulist);

		if (statsVO.getFromDate() != null && !"".equals(statsVO.getFromDate())) {

			List<?> scrinStats = scrinStatsService.selectScrinStats(statsVO);
			// 그래프에 표시될 이미지 길이를 결정한다.
			float iMaxUnit = 50.0f;
			for (int i = 0; i < scrinStats.size(); i++) {
				StatsVO sVo = (StatsVO)scrinStats.get(i);
				int iCnt = sVo.getStatsCo();
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

			model.addAttribute("scrinStats", scrinStats);
			model.addAttribute("statsInfo", statsVO);
		}
        return "egovframework/com/sts/sst/EgovScrinStats";
	}
}