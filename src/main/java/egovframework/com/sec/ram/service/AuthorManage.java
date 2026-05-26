package egovframework.com.sec.ram.service;

import egovframework.com.cmm.ComDefaultVO;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 권한관리에 대한 model 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *   2024.10.29	LeeBaekHaeng	시큐어코딩 일련번호 PK 파라미터 암복호화
 * </pre>
 */
@Getter
@Setter
public class AuthorManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 권한관리
	 */
	private AuthorManage authorManage;
	/**
	 * 권한코드
	 */
	@EgovNullCheck
	@Size(max=30)
	private String authorCode;
	/**
	 * 권한코드 암호화
	 */
	private String authorCodeEncrypt;
	/**
	 * 권한등록일자
	 */
	private String authorCreatDe;
	/**
	 * 권한코드설명
	 */
	@Size(max=200)
	private String authorDc;
	/**
	 * 권한 명
	 */
	@EgovNullCheck
	@Size(max=60)
	private String authorNm;

}
