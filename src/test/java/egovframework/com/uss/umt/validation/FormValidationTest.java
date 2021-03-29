package egovframework.com.uss.umt.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 *
 * 시스템명 : 실행환경시스템
 * 서브시스템명 : 화면처리
 * 요구사항ID : REQ-RTE-112
 * 요구사항명 : 폼 값 검증
 * 설명 : 입력값에 대한 Validation 기능을 제공해야 하며, 해당 기능을 사용자가 지정가능한 구조로 개발하여야 함.
 *
 * @author Ham Cheol
 */

public class FormValidationTest {

	@Test
	public void testValidation() throws BindException {

		Employee employee = new Employee();
		DataBinder binder = new DataBinder(employee);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue("empId", "AA1000");
		pvs.addPropertyValue("empName", "Nobody");
		pvs.addPropertyValue("empAge", 10);
		binder.bind(pvs);

		Validator validator = new EmployeeValidator();
		Errors errors = binder.getBindingResult();
		validator.validate(employee, errors);

		assertFalse(errors.hasFieldErrors("empId"));
		assertFalse(errors.hasFieldErrors("empName"));
		assertTrue(errors.hasFieldErrors("empAge"));
	}

	@Test
	public void testValidationWithServletRequest() {

		Employee employee = new Employee();
		MockHttpServletRequest request = new MockMultipartHttpServletRequest();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(employee, "employee");

		request.addParameter("empId", "AA1001");
		request.addParameter("empName", "But You");
		request.addParameter("empAge", "12");

		binder.bind(request);
		Validator validator = new EmployeeValidator();
		Errors errors = binder.getBindingResult();
		validator.validate(employee, errors);

		assertFalse(errors.hasFieldErrors("empId"));
		assertFalse(errors.hasFieldErrors("empName"));
		assertTrue(errors.hasFieldErrors("empAge"));
	}

	private class Employee {

		private String empId;
		private String empName;
		private int empAge;
		private Date birthDate;

		public String getEmpId() {
			return empId;
		}

		@SuppressWarnings("unused")
		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getEmpName() {
			return empName;
		}

		@SuppressWarnings("unused")
		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public int getEmpAge() {
			return empAge;
		}

		@SuppressWarnings("unused")
		public void setEmpAge(int empAge) {
			this.empAge = empAge;
		}

		@SuppressWarnings("unused")
		public Date getBirthDate() {
			return birthDate;
		}

		@SuppressWarnings("unused")
		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
	}

	private class EmployeeValidator implements Validator {

		public boolean supports(Class<?> clazz) {
			return clazz.isAssignableFrom(Employee.class);
		}

		public void validate(Object target, Errors errors) {

			Employee employee = (Employee) target;

			if (employee.getEmpId() == null) {
				errors.rejectValue("empId", "employee's id is required");
			}

			if (employee.getEmpName() == null || employee.getEmpName().length() < 2) {
				errors.rejectValue("empName", "employee's name is required");
			}

			if (!(employee.getEmpAge() > 18 && employee.getEmpAge() < 65)) {
				errors.rejectValue("empAge", "employee's age must be between 18 and 65.");
			}
		}
	}

}
