package egovframework.com.uss.umt.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 일반회원VO클래스로서 일반회원관리 비즈니스로직 처리용 항목을 구성한다.
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
public class MberManageVO extends UserDefaultVO{

	private static final long serialVersionUID = -4255594107023139972L;

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
	 * 끝전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String endTelno;
	/**
	 * 팩스번호
	 */
	@Size(max=15)
	private String mberFxnum;
	/**
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 주민등록번호
	 */
	private String ihidnum;
	/**
	 * 성별코드
	 */
	private String sexdstnCode;
	/**
	 * 회원 ID
	 */
	@EgovNullCheck
	@Size(max=20)
	private String mberId;
	/**
	 * 회원명
	 */
	@EgovNullCheck
	@Size(max=50)
	private String mberNm;
	/**
	 * 회원상태
	 */
	@EgovNullCheck
	private String mberSttus;
	/**
	 * 지역번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String areaNo;
	/**
	 * 중간전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String middleTelno;
	/**
	 * 핸드폰번호
	 */
	@EgovNullCheck
	@Size(max=15)
	private String moblphonNo;
	/**
	 * 비밀번호
	 */
	@EgovNullCheck
	@EgovPwdCheck
	private String password;
	/**
	 * 비밀번호 정답
	 */
	@EgovNullCheck
	@Size(max=100)
	private String passwordCnsr;
	/**
	 * 비밀번호 힌트
	 */
	@EgovNullCheck
	private String passwordHint;
	/**
	 * 가입 일자
	 */
	private String sbscrbDe;
	/**
	 * 우편번호
	 */
	@EgovNullCheck
	@Size(max=6)
	private String zip;
	/**
	 * 이메일주소
	 */
	@EgovNullCheck
	@Size(max=50)
	@EgovEmailCheck
	private String mberEmailAdres;

	private String lockAt;

}
