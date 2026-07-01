package egovframework.com.uss.umt.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;
import lombok.Getter;
import lombok.Setter;

/**
 * 일반회원 등록용 VO 클래스
 * EmplyrManageVO를 상속받아 비밀번호 검증 필드를 추가
 *
 * @author 공통서비스
 * @since 2025.12.16
 * @version 1.0
 * @see EmplyrManageVO
 */
@Getter
@Setter
public class EmplyrManageInsertVO extends EmplyrManageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 새 비밀번호 확인 (등록 시 확인)
	 */
	@EgovNullCheck
	@EgovPwdCheck
	private String password2;

}
