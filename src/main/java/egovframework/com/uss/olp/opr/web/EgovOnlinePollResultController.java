package egovframework.com.uss.olp.opr.web;

import java.util.List;

import egovframework.com.uss.olp.opr.service.EgovOnlinePollResultService;
import egovframework.com.uss.olp.opr.service.OnlinePollResult;

import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 온라인POLL결과를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOnlinePollResultController {

    @Resource(name = "egovOnlinePollResultService")
    private EgovOnlinePollResultService egovOnlinePollResultService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 온라인POLL결과 목록을 조회한다.
     * @param searchVO
     * @param commandMap
     * @param onlinePollVO
     * @param model
     * @return "egovframework/com/uss/olp/opr/EgovOnlinePollResultList"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opr/listOnlinePollResult.do")
    public String egovOnlinePollResultList(
            OnlinePollResult onlinePollResult,
            ModelMap model
            ) throws Exception {

        List<?> reusltList = egovOnlinePollResultService.selectOnlinePollResultList(onlinePollResult);
        model.addAttribute("resultList", reusltList);

        return "egovframework/com/uss/olp/opr/EgovOnlinePollResultList";
    }

    /**
     * 온라인POLL결과 목록을 상세조회 조회한다.
     * @param searchVO
     * @param onlinePollVO
     * @param commandMap
     * @param model
     * @return
     *         "/uss/olp/opr/EgovOnlinePollDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/uss/olp/opr/delOnlinePollResult.do")
    public String egovOnlinePollResultDetail(
            OnlinePollResult onlinePollResult,
            ModelMap model) throws Exception {

        egovOnlinePollResultService.deleteOnlinePollResult(onlinePollResult);
        return "forward:/uss/olp/opr/listOnlinePollResult.do";
    }




}
