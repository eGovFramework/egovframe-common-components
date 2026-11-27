package egovframework.com.uss.umt.service;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자정보 VO클래스로서일반회원, 기업회원, 업무사용자의  비즈니스로직 처리시 기타조건성 항을 구성한다.
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
 *
 * </pre>
 */
@Getter
@Setter
public class UserDefaultVO implements Serializable {

	private static final long serialVersionUID = 4829684178121022508L;

	/** 검색조건-회원상태     (0, A, D, P)*/
	private String sbscrbSttus = "0";

	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지개수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	/**
	 * 목록 범위. 'N' 이면 전체(관리자), 그 외에는 ownerUniqId 한 건만 조회한다.
	 * 요청 파라미터로 바인딩되더라도 목록 컨트롤러가 항상 서버 값으로 덮어쓴다. 기본값은 본인 한정(값이 없으면 0건).
	 */
	private String ownerOnly = "Y";

	/** 본인 한정 조회 시 고유ID(ESNTL_ID) */
	private String ownerUniqId = "";

}
