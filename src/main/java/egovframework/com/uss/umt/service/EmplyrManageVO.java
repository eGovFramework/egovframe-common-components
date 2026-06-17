package egovframework.com.uss.umt.service;

import lombok.Getter;
import lombok.Setter;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovEmailCheck;
import org.egovframe.rte.ptl.reactive.validation.EgovPwdCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 업무사용자VO클래스로서 업무사용자관리 비지니스로직 처리용 항목을 구성한다.
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
public class EmplyrManageVO extends UserDefaultVO{

	private static final long serialVersionUID = 3640820362821490939L;

    /**
	 * 가입일
	 */
	private String sbscrbDe;
	/**
	 * 사용자고유아이디
	 */
	private String uniqId="";
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 지역번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String areaNo;
	/**
	 * 생일
	 */
	private String brth;
	/**
	 * 상세주소
	 */
	private String detailAdres;
	/**
	 * 이메일주소
	 */
	@EgovNullCheck
	@Size(max=50)
	@EgovEmailCheck
	private String emailAdres;
	/**
	 * 사원번호
	 */
	private String emplNo;
	/**
	 * 사용자 ID
	 */
	@EgovNullCheck
	@Size(max=20)
	private String emplyrId;
	/**
	 * 사용자 명
	 */
	@EgovNullCheck
	@Size(max=50)
	private String emplyrNm;
	/**
	 * 사용자 상태
	 */
	@EgovNullCheck
	private String emplyrSttusCode;
	/**
	 * 팩스번호
	 */
	@Size(max=15)
	private String fxnum;
	/**
	 * 그룹 ID
	 */
	@EgovNullCheck
	private String groupId;
	/**
	 * 집 주소
	 */
	@EgovNullCheck
	@Size(max=100)
	private String homeadres;
	/**
	 * 집끝전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String homeendTelno;
	/**
	 * 집중간전화번호
	 */
	@EgovNullCheck
	@Size(max=4)
	@Pattern(regexp="^[0-9]*$", message="{validation.pattern.integer}")
	private String homemiddleTelno;
	/**
	 * 주민등록번호
	 */
	private String ihidnum;
	/**
	 * 소속기관코드
	 */
	private String insttCode;
	/**
	 * 검색조건 회원타입
	 */
	private String mberTy;
	/**
	 * 핸드폰번호
	 */
	@Size(max=15)
	private String moblphonNo;
	/**
	 * 직위명
	 */
	private String ofcpsNm;
	/**
	 * 사무실전화번호
	 */
	@Size(max=15)
	private String offmTelno;
	/**
	 * 조직 ID
	 */
	private String orgnztId;
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
	 * 검색조건 가입일자 시작일
	 */
	private String sbscrbDeBegin;
	/**
	 * 검색조건 가입일자 종료일
	 */
	private String sbscrbDeEnd;
	/**
	 * 성별코드
	 */
	private String sexdstnCode;
	/**
	 * 우편번호
	 */
	@EgovNullCheck
	private String zip;
	/**
	 * DN 값
	 */
	@Size(max=100)
	private String subDn;
	
	private String lockAt;
}
