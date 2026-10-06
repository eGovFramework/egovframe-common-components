package egovframework.com.uss.umt.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EmplyrManageInsertVO;

class EgovEmplyrManageControllerTest {

	private static final String ERROR_VIEW = "egovframework/com/cmm/error/egovError";

	private static final String INSERT_VIEW = "egovframework/com/uss/umt/EgovEmplyrInsert";

	/** EgovEmplyrInsert.jsp의 form:form modelAttribute 값 */
	private static final String FORM_OBJECT_NAME = "emplyrManageVO";

	/** EgovEmplyrInsert.jsp의 form:options items 목록 */
	private static final List<String> CODE_LIST_ATTRIBUTES = Arrays.asList("passwordHint_result", "sexdstnCode_result",
			"emplyrSttusCode_result", "insttCode_result", "orgnztId_result", "groupId_result");

	private EgovEmplyrManageController controller;

	@BeforeEach
	void setUp() {
		controller = new EgovEmplyrManageController();
		controller.nextUrlWhitelist = Arrays.asList("/uss/umt/EgovMberSbscrbView.do");
	}

	@AfterEach
	void tearDown() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(null);
	}

	@Test
	void rlnmCnfirmRejectsNegativeNextUrlIndex() throws Exception {
		assertEquals(ERROR_VIEW, controller.rlnmCnfirm(model(), commandMap(), -1));
	}

	@Test
	void rlnmCnfirmRejectsOutOfRangeNextUrlIndex() throws Exception {
		assertEquals(ERROR_VIEW, controller.rlnmCnfirm(model(), commandMap(), 1));
	}

	@Test
	void insertUserUsesSameFormObjectNameAsInsertUserView() throws Exception {
		assertEquals(FORM_OBJECT_NAME, formObjectName("insertUserView"));
		assertEquals(FORM_OBJECT_NAME, formObjectName("insertUser"));
	}

	@Test
	void insertUserRestoresCodeListsWhenValidationFails() throws Exception {
		authenticate();
		setField("cmmUseService", cmmUseService());

		EmplyrManageInsertVO emplyrManageInsertVO = new EmplyrManageInsertVO();
		BindingResult bindingResult = new BeanPropertyBindingResult(emplyrManageInsertVO, FORM_OBJECT_NAME);
		bindingResult.rejectValue("emplyrId", "validation.egov.error.emplyrId");
		Model model = model();

		assertEquals(INSERT_VIEW, controller.insertUser(emplyrManageInsertVO, bindingResult, model));
		for (String attribute : CODE_LIST_ATTRIBUTES) {
			assertNotNull(model.asMap().get(attribute), attribute);
		}
	}

	/** 핸들러 파라미터에 선언된 @ModelAttribute 이름을 읽는다. */
	private String formObjectName(String methodName) {
		for (Method method : EgovEmplyrManageController.class.getDeclaredMethods()) {
			if (!methodName.equals(method.getName())) {
				continue;
			}
			for (Parameter parameter : method.getParameters()) {
				if (EmplyrManageInsertVO.class.equals(parameter.getType())) {
					return parameter.getAnnotation(ModelAttribute.class).value();
				}
			}
		}
		throw new IllegalStateException(methodName);
	}

	private void authenticate() {
		new EgovUserDetailsHelper().setEgovUserDetailsService(new EgovUserDetailsService() {

			@Override
			public Object getAuthenticatedUser() {
				return null;
			}

			@Override
			public List<String> getAuthorities() {
				return Collections.emptyList();
			}

			@Override
			public Boolean isAuthenticated() {
				return Boolean.TRUE;
			}
		});
	}

	private EgovCmmUseService cmmUseService() {
		return new EgovCmmUseService() {

			@Override
			public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.singletonList(new CmmnDetailCode());
			}

			@Override
			public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<ComDefaultCodeVO> comDefaultCodeVOs) {
				return new HashMap<>();
			}

			@Override
			public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.singletonList(new CmmnDetailCode());
			}

			@Override
			public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
				return Collections.singletonList(new CmmnDetailCode());
			}
		};
	}

	private void setField(String fieldName, Object value) throws Exception {
		Field field = EgovEmplyrManageController.class.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(controller, value);
	}

	private Model model() {
		return new ExtendedModelMap();
	}

	private Map<String, Object> commandMap() {
		return new HashMap<>();
	}

}
