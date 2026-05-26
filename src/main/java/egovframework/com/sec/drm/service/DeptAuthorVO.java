package egovframework.com.sec.drm.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 부서권한에 대한 Vo 클래스를 정의한다.
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
public class DeptAuthorVO extends DeptAuthor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 부서권한목록
	 */
	List <DeptAuthorVO> deptAuthorList;
	/**
	 * 부서목록
	 */
	List <DeptAuthorVO> deptList;
	/**
	 * 부서코드
	 */
	private String deptCode;
	/**
	 * 부서 명
	 */
	private String deptNm;

}
