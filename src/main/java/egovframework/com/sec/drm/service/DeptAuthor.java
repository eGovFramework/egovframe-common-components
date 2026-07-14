package egovframework.com.sec.drm.service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sec.rgm.service.AuthorGroup;

import lombok.Getter;
import lombok.Setter;

/**
 * 부서권한에 대한 model 클래스를 정의한다.
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
public class DeptAuthor extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 권한그룹관리
	 */
	private AuthorGroup authorGroup;
	/**
	 * 설정대상 사용자 ID
	 */
	private String userId;
	/**
	 * 설정대상 사용자 명
	 */
	private String userNm;
	/**
	 * 설정대상 그룹 ID
	 */
	private String groupId;
	/**
	 * 설정대상 사용자 유형 코드
	 */
	private String mberTyCode;
	/**
	 * 설정대상 사용자 유형 명
	 */
	private String mberTyNm;
	/**
	 * 권한코드
	 */
	private String authorCode;
	/**
	 * 등록 여부
	 */
	private String regYn;
	/**
	 * Uniq ID
	 */
	private String uniqId;

}
