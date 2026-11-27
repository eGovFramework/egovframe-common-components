package egovframework.com.cmm.validation;

/**
 * 유효성 검증을 위한 공통 Validator
 * 
 * @author 공통서비스 개발팀
 * @since 2025
 */

public class EgovCommonValidator {

	/**
	 * 연속된 3개 이상의 문자나 숫자 검사
	 */
	public boolean hasSeries(String password) {
		for (int i = 0; i < password.length() - 2; i++) {
			char ch1 = password.charAt(i);
			char ch2 = password.charAt(i + 1);
			char ch3 = password.charAt(i + 2);

			if ((ch2 - ch1 == 1 && ch3 - ch2 == 1) || (ch1 - ch2 == 1 && ch2 - ch3 == 1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 반복된 3개 이상의 문자나 숫자 검사
	 */
	public boolean hasRepeat(String password) {
		for (int i = 0; i < password.length() - 2; i++) {
			if (password.charAt(i) == password.charAt(i + 1)
					&& password.charAt(i) == password.charAt(i + 2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 영문자, 숫자, 특수문자의 최소 3가지 조합 검사
	 */
	public boolean hasComb3(String password) {
		boolean hasAlpha = password.matches(".*[a-zA-Z].*");
		boolean hasNum = password.matches(".*\\d.*");
		boolean hasSpecial = password.matches(".*[~!@#$%^&*?].*");

		int count = 0;
		if (hasAlpha)
			count++;
		if (hasNum)
			count++;
		if (hasSpecial)
			count++;

		return count >= 3;
	}

}
