package egovframework.com.uss.umt.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
@Getter
@Setter
public class EntrprsManageVO  extends UserDefaultVO{

	private static final long serialVersionUID = -6532736688851136256L;

    /**
	 * 사용자고유아이디
	 */
	private String uniqId="";
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 주소
	 */
	@EgovNullCheck
	@Size(max=100)
	private String adres;
	/**
	 * 상세주소
	 */
	private String detailAdres;
	/**
	 * 신청자 주민등록번호
	 */
	private String applcntIhidnum;
	/**
	 * 신청자 명
	 */
	@EgovNullCheck
	private String applcntNm;
	/**
	 * 사업자번호
	 */
	@EgovNullCheck
	private String bizrno;
	/**
	 * 회사명
	 */
	@EgovNullCheck
	@Size(max=50)
	private String cmpnyNm;
	/**
	 * 대표이사
	 */
	private String cxfc;
	/**
	 * 기업 회원 ID
	 */
	@EgovNullCheck
	@Size(max=20)
	private String entrprsmberId;
	/**
	 * 기업 회원 비밀번호
	 */
	@EgovNullCheck
	@EgovPwdCheck
	private String entrprsMberPassword;
	/**
	 * 기업 회원 비밀번호 정답
	 */
	@EgovNullCheck
	@Size(max=100)
	private String entrprsMberPasswordCnsr;
	/**
	 * 기업 회원 비밀번호 힌트
	 */
	@EgovNullCheck
	private String entrprsMberPasswordHint;
	/**
	 * 기업 회원 상태
	 */
	@EgovNullCheck
	private String entrprsMberSttus;
	/**
	 * 기업구분코드
	 */
	private String entrprsSeCode;
	/**
	 * 팩스번호
	 */
	private String fxnum;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 업종코드
	 */
	private String indutyCode;
	/**
	 * 법인등록번호
	 */
	private String jurirno;
	/**
	 * 지역번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String areaNo;
	/**
	 * 회사끝전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String entrprsEndTelno;
	/**
	 * 회사중간전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String entrprsMiddleTelno;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 우편번호
	 */
	@EgovNullCheck
	private String zip;
	/**
	 * 신청자 이메일주소
	 */
	@EgovNullCheck
	@Size(max=50)
	@EgovEmailCheck
	private String applcntEmailAdres;

	private String lockAt;

	/**
	 * 일반회원관리(JSP/MyBatis)와 동일한 password 속성명 호환
	 */
	public String getPassword() {
		return entrprsMberPassword;
	}

	public void setPassword(String password) {
		this.entrprsMberPassword = password;
	}

	public String getPasswordHint() {
		return entrprsMberPasswordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.entrprsMberPasswordHint = passwordHint;
	}

	public String getPasswordCnsr() {
		return entrprsMberPasswordCnsr;
	}

	public void setPasswordCnsr(String passwordCnsr) {
		this.entrprsMberPasswordCnsr = passwordCnsr;
	}

}
