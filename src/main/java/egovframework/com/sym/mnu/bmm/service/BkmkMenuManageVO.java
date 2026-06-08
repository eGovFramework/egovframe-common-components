package egovframework.com.sym.mnu.bmm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 바로가기메뉴관리를 위한 VO 모델 클래스
 * @author 공통컴포넌트개발팀 윤성록
 * @since 2009.09.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.9.25  윤성록          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class BkmkMenuManageVO extends BkmkMenuManage {

	private static final long serialVersionUID = 7602193410939256848L;

	/** 검색시작일 */
	private String searchBgnDe = "";

	/** 검색조건 */
	private String searchCnd = "";

	/** 검색종료일 */
	private String searchEndDe = "";

	/** 검색단어 */
	private String searchWrd = "";

	/** 정렬순서(DESC,ASC) */
	private long sortOrdr = 0L;

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지개수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** 첫페이지 인덱스 */
	private int firstIndex = 1;

	/** 마지막페이지 인덱스 */
	private int lastIndex = 1;

	/** 페이지당 레코드 개수 */
	private int recordCountPerPage = 10;

	/** 레코드 번호 */
	private int rowNo = 0;

	/** 최초 등록자명 */
	private String frstRegisterNm = "";

	/** 최종 수정자명 */
	private String lastUpdusrNm = "";

	/** 메뉴 설명 */
	private String menuDc = "";

}
