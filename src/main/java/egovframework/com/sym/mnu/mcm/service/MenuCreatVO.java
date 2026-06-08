package egovframework.com.sym.mnu.mcm.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 메뉴생성 처리를 위한 VO 클래스르를 정의한다
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class MenuCreatVO {

	/** 메뉴번호 */
	@EgovNullCheck
	private int menuNo;
	/** 메뉴순서 */
	@EgovNullCheck
	private int menuOrdr;
	/** 메뉴명 */
	@EgovNullCheck
	@Size(max=50)
	private String menuNm;
	/** 상위메뉴번호 */
	@EgovNullCheck
	private int upperMenuId;
	/** 프로그램파일명 */
	@EgovNullCheck
	@Size(max=50)
	private String progrmFileNm;
	/** 메뉴설명 */
	@Size(max=100)
	private String menuDc;
	/** 관련이미지경로 */
	@Size(max=100)
	private String relateImagePath;
	/** 관련이미지명 */
	@Size(max=50)
	private String relateImageNm;
	/** 맵생성ID */
	private String mapCreatId;
	/** 권한코드 */
	private String authorCode;

	/** 권한정보설정 */
	/** 권한명 */
	private String authorNm;
	/** 권한설명 */
	private String authorDc;
	/** 권한생성일자 */
	private String authorCreatDe;

	/** 기타VO변수 */
	/** 생성자ID **/
	private String creatPersonId;

}
