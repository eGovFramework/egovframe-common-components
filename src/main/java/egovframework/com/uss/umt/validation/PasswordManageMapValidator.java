package egovframework.com.uss.umt.validation;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import egovframework.com.cmm.validation.EgovCommonValidator;
import egovframework.com.uss.umt.service.PasswordManageVO;

/**
 * 비밀번호 변경 VO 검증을 위한 Validator
 * 
 * @author 공통서비스 개발팀
 * @since 2025
 */
@Component("passwordManageMapValidator")
public class PasswordManageMapValidator extends EgovCommonValidator implements Validator {

	@Override
	public boolean supports(@NonNull Class<?> clazz) {
		return PasswordManageVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(@NonNull Object target, @NonNull Errors errors) {
		PasswordManageVO passwordChgVO = (PasswordManageVO) target;

		String oldPassword = passwordChgVO.getOldPassword();
		String newPassword = passwordChgVO.getPassword();
		String newPassword2 = passwordChgVO.getPassword2();

		// oldPassword 필수 검증
		if (oldPassword == null || oldPassword.trim().isEmpty()) {
			errors.rejectValue("oldPassword", "validation.password.oldPassword.required", 
					new Object[] { "이전비밀번호" }, "이전비밀번호은(는) 필수입력 항목입니다.");
		}

		// newPassword 필수 검증
		if (newPassword == null || newPassword.trim().isEmpty()) {
			errors.rejectValue("newPassword", "validation.password.newPassword.required", 
					new Object[] { "비밀번호" }, "비밀번호은(는) 필수입력 항목입니다.");
		} else {
			// 비밀번호 길이 검증 (8~20자)
			if (newPassword.length() < 8 || newPassword.length() > 20) {
				errors.rejectValue("newPassword", "validation.password.length", 
						"패스워드는 8~20자 이내여야 합니다.");
			}

			// 연속된 문자 검증
			if (hasSeries(newPassword)) {
				errors.rejectValue("newPassword", "validation.password.series",
						"연속된 3개 이상의 문자나 숫자를 사용할 수 없습니다.");
			}

			// 반복 문자 검증
			if (hasRepeat(newPassword)) {
				errors.rejectValue("newPassword", "validation.password.repeat",
						"반복된 3개 이상의 문자나 숫자를 사용할 수 없습니다.");
			}

			// 3가지 조합 검증 (영문자, 숫자, 특수문자)
			if (!hasComb3(newPassword)) {
				errors.rejectValue("newPassword", "validation.password.combination",
						"영문자, 숫자, 특수문자의 최소 3가지 조합이어야 합니다.");
			}
		}

		// newPassword2 필수 검증
		if (newPassword2 == null || newPassword2.trim().isEmpty()) {
			errors.rejectValue("newPassword2", "validation.password.newPassword2.required", 
					new Object[] { "비밀번호확인" }, "비밀번호확인은(는) 필수입력 항목입니다.");
		}

		// 비밀번호 일치 검증
		if (newPassword != null && newPassword2 != null && !newPassword.equals(newPassword2)) {
			errors.rejectValue("newPassword2", "validation.password.mismatch", 
					"비밀번호가 일치하지 않습니다.");
		}
	}

}
