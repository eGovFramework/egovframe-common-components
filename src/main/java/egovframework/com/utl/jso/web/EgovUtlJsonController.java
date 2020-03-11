package egovframework.com.utl.jso.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 요소기술 json 관한 controller 클래스를 정의한다.
 * @author 2016 표준프레임웤크 유지보수 장동한
 * @since 2016.07.14
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2016.07.14  장동한          최초 생성
 *
 * </pre>
 */


@Controller
public class EgovUtlJsonController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovUtlJsonController.class);
    
    /**
	 * json 단건조회
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/utl/jso/EgovUtlJsonInquire.do",method = RequestMethod.GET)
    public String selectUtlJsonInquire()  throws Exception {
        return "egovframework/com/utl/jso/EgovUtlJsonInquire";
    }
    
    @RequestMapping(value="/utl/jso/EgovUtlJsonInquire.do",method = RequestMethod.POST)
    public ModelAndView selectUtlJsonInquirePost(@RequestParam Map<?, ?> commandMap)  throws Exception {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	LOGGER.debug("EgovUtlJsonController EgovUtlJsonInquire START=========");
    	
    	LOGGER.debug("commandMap>"+commandMap);
    	
    	modelAndView.addObject("fruits1", "apple");
    	modelAndView.addObject("fruits2", "orange");
    	modelAndView.addObject("fruits3", "lemon");
    	modelAndView.addObject("fruits4", "lime");
    	modelAndView.addObject("fruits5", "mango");
    	
    	LOGGER.debug("EgovUtlJsonController EgovUtlJsonInquire END=========");	
    	
    	return modelAndView;
    }
    /**
	 * json 다건조회
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/utl/jso/EgovUtlJsonMultiInquire.do",method = RequestMethod.GET)
    public String selectUtlJsonMultiInquire(@RequestParam Map<?, ?> commandMap)  throws Exception {    	
        return "egovframework/com/utl/jso/EgovUtlJsonMultiInquire";
    }
    @RequestMapping(value="/utl/jso/EgovUtlJsonMultiInquire.do",method = RequestMethod.POST)
    public ModelAndView selectUtlJsonMultiInquirePost(@RequestParam Map<?, ?> commandMap)  throws Exception {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	LOGGER.debug("EgovUtlJsonController selectUtlJsonMultiInquire START=========");
    	
    	LOGGER.debug("commandMap>"+commandMap);
    	
    	modelAndView.addObject("fruits1", "apple");
    	modelAndView.addObject("fruits2", "orange");
    	modelAndView.addObject("fruits3", "lemon");
    	modelAndView.addObject("fruits4", "lime");
    	modelAndView.addObject("fruits5", "mango");
    	
    	HashMap<String,String> mp = new HashMap<String,String>();
    	mp.put("fruits1", "apple");
    	mp.put("fruits2", "orange");
    	mp.put("fruits3", "lemon");
    	mp.put("fruits4", "lime");
    	mp.put("fruits5", "mango");
    	
    	List<HashMap> list = new ArrayList<HashMap>();
    	list.add(mp);
    	list.add(mp);
    	list.add(mp);
    	list.add(mp);
    	list.add(mp);
    	
    	modelAndView.addObject("list", list);
    	LOGGER.debug("EgovUtlJsonController selectUtlJsonMultiInquire END=========");	
    	
    	return modelAndView;
    }

}