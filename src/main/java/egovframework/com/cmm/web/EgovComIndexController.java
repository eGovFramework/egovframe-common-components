package egovframework.com.cmm.web;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.IncludedCompInfoVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uia.service.EgovLoginService;
import jakarta.annotation.Resource;

/**
 * <pre>
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
 * </pre>
 * 
 * @author 공통컴포넌트 정진오
 * @since 2011.08.26
 * @version 2.0.0
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.08.26  정진오          최초 생성
 *   2011.09.16  서준식          컨텐츠 페이지 생성
 *   2011.09.26  이기하          header, footer 페이지 생성
 *   2019.12.04  신용호          KISA 보안코드 점검 : Map<Integer, IncludedCompInfoVO> map를 지역변수로 수정
 *   2020.07.08  신용호          비밀번호를 수정한후 경과한 날짜 조회
 *   2020.08.28  정진오          표준프레임워크 v3.10 개선
 *   2025.05.30  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(지역 변수 명명 규칙)
 *
 *      </pre>
 */

@Controller
public class EgovComIndexController {

	@Autowired
	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovComIndexController.class);

	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	@RequestMapping("/index.do")
	public String index(ModelMap model) {
		return "egovframework/com/cmm/EgovUnitMain";
	}

	@RequestMapping("/EgovTop.do")
	public String top(ModelMap model) {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);
		return "egovframework/com/cmm/EgovUnitTop";
	}

	@RequestMapping("/EgovBottom.do")
	public String bottom() {
		return "egovframework/com/cmm/EgovUnitBottom";
	}

	@RequestMapping("/EgovContent.do")
	public String setContent(ModelMap model) throws Exception {

		// 설정된 비밀번호 유효기간을 가져온다. ex) 180이면 비밀번호 변경후 만료일이 앞으로 180일
		String propertyExpirePwdDay = EgovProperties.getProperty("Globals.ExpirePwdDay");
		int expirePwdDay = 0;
		try {
			expirePwdDay = Integer.parseInt(propertyExpirePwdDay);
		} catch (NumberFormatException nfe) {
			LOGGER.debug("convert expirePwdDay Err : " + nfe.getMessage());
		}

		model.addAttribute("expirePwdDay", expirePwdDay);

		// 비밀번호 설정일로부터 며칠이 지났는지 확인한다. ex) 3이면 비밀번호 설정후 3일 경과
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);
		int passedDayChangePWD = 0;
		if (loginVO != null) {
			LOGGER.debug("===>>> loginVO.getId() = " + loginVO.getId());
			LOGGER.debug("===>>> loginVO.getUniqId() = " + loginVO.getUniqId());
			LOGGER.debug("===>>> loginVO.getUserSe() = " + loginVO.getUserSe());
			// 비밀번호 변경후 경과한 일수
			passedDayChangePWD = loginService.selectPassedDayChangePWD(loginVO);
			LOGGER.debug("===>>> passedDayChangePWD = " + passedDayChangePWD);
			model.addAttribute("passedDay", passedDayChangePWD);
		}

		// 만료일자로부터 경과한 일수 => ex)1이면 만료일에서 1일 경과
		model.addAttribute("elapsedTimeExpiration", passedDayChangePWD - expirePwdDay);

		return "egovframework/com/cmm/EgovUnitContent";
	}

	@RequestMapping("/EgovLeft.do")
	public String setLeftMenu(ModelMap model) {

		Map<Integer, IncludedCompInfoVO> map = new TreeMap<Integer, IncludedCompInfoVO>();
		IncludedInfo annotation;
		IncludedCompInfoVO zooVO;

		/* @Controller Annotation 처리된 클래스를 모두 찾는다. */
		Map<String, Object> myZoos = applicationContext.getBeansWithAnnotation(Controller.class);
		LOGGER.debug("How many Controllers : {}", myZoos.size());
		for (final Object myZoo : myZoos.values()) {

			Class<?> zooClass = ClassUtils.getUserClass(myZoo);

			Method[] methods = zooClass.getMethods();
			LOGGER.debug("Controller Detected {}", zooClass);
			for (int i = 0; i < methods.length; i++) {
				annotation = methods[i].getAnnotation(IncludedInfo.class);

				if (annotation != null) {
					// LOG.debug("Found @IncludedInfo Method : " + methods[i] );
					String listUrl = annotation.listUrl();
					if ("".equals(listUrl)) {
						/*
						 * 목록형 조회를 위한 url 매핑은 @IncludedInfo나 @RequestMapping류(@RequestMapping,
						 * @GetMapping, @PostMapping, @PutMapping, @PatchMapping, @DeleteMapping)
						 * 에서 가져온다
						 */
						listUrl = resolveMappingUrl(methods[i]);
					}

					if (listUrl == null) {
						/*
						 * URL을 찾지 못한 경우 좌측메뉴 전체가 에러 화면(egovError.jsp)으로 대체되는 것을
						 * 방지하기 위해 해당 항목만 건너뛴다.
						 */
						LOGGER.warn("@IncludedInfo가 설정되었으나 매핑 URL을 확인할 수 없어 좌측메뉴에서 제외합니다. method={}",
								methods[i]);
						continue;
					}

					zooVO = new IncludedCompInfoVO();
					zooVO.setName(annotation.name());
					zooVO.setOrder(annotation.order());
					zooVO.setGid(annotation.gid());
					zooVO.setListUrl(listUrl);

					map.put(zooVO.getOrder(), zooVO);
				}
			}
		}

		model.addAttribute("resultList", map.values());

		LOGGER.debug("EgovComIndexController index is called ");

		return "egovframework/com/cmm/EgovUnitLeft";
	}

	/**
	 * 메서드에 설정된 @RequestMapping류 애노테이션에서 첫번째 매핑 URL을 조회한다.
	 * @RequestMapping이 없는 경우 @GetMapping, @PostMapping, @PutMapping, @PatchMapping,
	 * @DeleteMapping 순서로 확인한다.
	 *
	 * @param method 확인 대상 메서드
	 * @return 매핑된 URL, 없으면 null
	 */
	private String resolveMappingUrl(Method method) {
		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		if (requestMapping != null && requestMapping.value().length > 0) {
			return requestMapping.value()[0];
		}

		GetMapping getMapping = method.getAnnotation(GetMapping.class);
		if (getMapping != null && getMapping.value().length > 0) {
			return getMapping.value()[0];
		}

		PostMapping postMapping = method.getAnnotation(PostMapping.class);
		if (postMapping != null && postMapping.value().length > 0) {
			return postMapping.value()[0];
		}

		PutMapping putMapping = method.getAnnotation(PutMapping.class);
		if (putMapping != null && putMapping.value().length > 0) {
			return putMapping.value()[0];
		}

		PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
		if (patchMapping != null && patchMapping.value().length > 0) {
			return patchMapping.value()[0];
		}

		DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
		if (deleteMapping != null && deleteMapping.value().length > 0) {
			return deleteMapping.value()[0];
		}

		return null;
	}

	// context-security.xml 설정
	// csrf="true"인 경우 csrf Token이 없는경우 이동하는 페이지
	// csrfAccessDeniedUrl="/egovCSRFAccessDenied.do"
	@RequestMapping("/egovCSRFAccessDenied.do")
	public String egovCSRFAccessDenied() {
		return "egovframework/com/cmm/error/csrfAccessDenied";
	}
}
