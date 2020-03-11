package egovframework.com.cmm.web;

/**
 * 컴포넌트 설치 후 설치된 컴포넌트들을 IncludedInfo annotation을 통해 찾아낸 후
 * 화면에 표시할 정보를 처리하는 Controller 클래스
 * <Notice>
 * 		개발시 메뉴 구조가 잡히기 전에 배포파일들에 포함된 공통 컴포넌트들의 목록성 화면에
 * 		URL을 제공하여 개발자가 편하게 활용하도록 하기 위해 작성된 것으로,
 * 		실제 운영되는 시스템에서는 적용해서는 안 됨
 *      실 운영 시에는 삭제해서 배포해도 좋음
 * <Disclaimer>
 * 		운영시에 본 컨트롤을 사용하여 메뉴를 구성하는 경우 성능 문제를 일으키거나
 * 		사용자별 메뉴 구성에 오류를 발생할 수 있음
 * @author 공통컴포넌트 정진오
 * @since 2011.08.26
 * @version 2.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일		  수정자		수정내용
 *  ----------   --------   ---------------------------
 *  2011.08.26   정진오            최초 생성
 *  2011.09.16   서준식            컨텐츠 페이지 생성
 *  2011.09.26     이기하		header, footer 페이지 생성
 *  2019.12.04   신용호            KISA 보안코드 점검 : Map<Integer, IncludedCompInfoVO> map를 지역변수로 수정
 * </pre>
 */

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import egovframework.com.cmm.IncludedCompInfoVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EgovComIndexController implements ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovComIndexController.class);

	public void afterPropertiesSet() throws Exception {}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
		LOGGER.info("EgovComIndexController setApplicationContext method has called!");
	}

	@RequestMapping("/index.do")
	public String index(ModelMap model) {
		return "egovframework/com/cmm/EgovUnitMain";
	}

	@RequestMapping("/EgovTop.do")
	public String top() {
		return "egovframework/com/cmm/EgovUnitTop";
	}

	@RequestMapping("/EgovBottom.do")
	public String bottom() {
		return "egovframework/com/cmm/EgovUnitBottom";
	}

	@RequestMapping("/EgovContent.do")
	public String setContent(ModelMap model) {

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);

		return "egovframework/com/cmm/EgovUnitContent";
	}

	@RequestMapping("/EgovLeft.do")
	public String setLeftMenu(ModelMap model) {

		Map<Integer, IncludedCompInfoVO> map = new TreeMap<Integer, IncludedCompInfoVO>();
		RequestMapping rmAnnotation;
		IncludedInfo annotation;
		IncludedCompInfoVO zooVO;

		/*
		 * EgovLoginController가 AOP Proxy되는 바람에 클래스를 reflection으로 가져올 수 없음
		 */
		try {
			Class<?> loginController = Class.forName("egovframework.com.uat.uia.web.EgovLoginController");
			Method[] methods = loginController.getMethods();
			for (int i = 0; i < methods.length; i++) {
				annotation = methods[i].getAnnotation(IncludedInfo.class);

				if (annotation != null) {
					LOGGER.debug("Found @IncludedInfo Method : {}", methods[i]);
					zooVO = new IncludedCompInfoVO();
					zooVO.setName(annotation.name());
					zooVO.setOrder(annotation.order());
					zooVO.setGid(annotation.gid());

					rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
					if ("".equals(annotation.listUrl()) && rmAnnotation != null) {
						zooVO.setListUrl(rmAnnotation.value()[0]);
					} else {
						zooVO.setListUrl(annotation.listUrl());
					}
					map.put(zooVO.getOrder(), zooVO);
				}
			}
		} catch (ClassNotFoundException e) {
			LOGGER.error("No egovframework.com.uat.uia.web.EgovLoginController!!");
		}
		/* 여기까지 AOP Proxy로 인한 코드 */

		/*@Controller Annotation 처리된 클래스를 모두 찾는다.*/
		Map<String, Object> myZoos = applicationContext.getBeansWithAnnotation(Controller.class);
		LOGGER.debug("How many Controllers : ", myZoos.size());
		for (final Object myZoo : myZoos.values()) {
			Class<? extends Object> zooClass = myZoo.getClass();

			Method[] methods = zooClass.getMethods();
			LOGGER.debug("Controller Detected {}", zooClass);
			for (int i = 0; i < methods.length; i++) {
				annotation = methods[i].getAnnotation(IncludedInfo.class);

				if (annotation != null) {
					//LOG.debug("Found @IncludedInfo Method : " + methods[i] );
					zooVO = new IncludedCompInfoVO();
					zooVO.setName(annotation.name());
					zooVO.setOrder(annotation.order());
					zooVO.setGid(annotation.gid());
					/*
					 * 목록형 조회를 위한 url 매핑은 @IncludedInfo나 @RequestMapping에서 가져온다
					 */
					rmAnnotation = methods[i].getAnnotation(RequestMapping.class);
					if ("".equals(annotation.listUrl())) {
						zooVO.setListUrl(rmAnnotation.value()[0]);
					} else {
						zooVO.setListUrl(annotation.listUrl());
					}

					map.put(zooVO.getOrder(), zooVO);
				}
			}
		}

		model.addAttribute("resultList", map.values());
		
		LOGGER.debug("EgovComIndexController index is called ");

		return "egovframework/com/cmm/EgovUnitLeft";
	}
}
