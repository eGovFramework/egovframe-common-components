package egovframework.com.sec.ram.service;

import egovframework.com.cmm.ComDefaultVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 권한별 롤 관리에 대한 model 클래스를 정의한다.
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
 *
 * </pre>
 */
@Getter
@Setter
public class AuthorRoleManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 권한 롤 관리
	 */
	private AuthorRoleManage authorRole;
	/**
	 * 권한코드
	 */
	private String authorCode;
	/**
	 * 롤코드
	 */
	private String roleCode;
	/**
	 * 롤명
	 */
	private String roleNm;
	/**
	 * 롤 패턴
	 */
	private String rolePtn;
	/**
	 * 롤 설명
	 */
	private String roleDc;
	/**
	 * 롤 타입
	 */
	private String roleTyp;
	/**
	 * 롤 순서정렬
	 */
	private String roleSort;
	/**
	 * 롤 등록여부
	 */
	private String regYn;
	/**
	 * 등록일자
	 */
	private String creatDt;

}
