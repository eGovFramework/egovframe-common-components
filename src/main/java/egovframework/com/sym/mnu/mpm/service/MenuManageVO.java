package egovframework.com.sym.mnu.mpm.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 메뉴목록관리 처리를 위한 VO 클래스르를 정의한다
 *
 * @author 개발환경 개발팀 이용
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이용           최초 생성
 *   2025.07.17  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *
 *      </pre>
 */
@Getter
@Setter
public class MenuManageVO {

	/** 메뉴정보 */
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
	/** 메뉴설명 */
	@Size(max=100)
	private String menuDc;
	/** 관련이미지경로 */
	@Size(max=100)
	private String relateImagePath;
	/** 관련이미지명 */
	@Size(max=50)
	private String relateImageNm;
	/** 프로그램파일명 */
	@EgovNullCheck
	@Size(max=50)
	private String progrmFileNm;

	/** 사이트맵 */
	/** 생성자ID **/
	private String creatPersonId;

	/** 권한정보설정 */
	/** 권한코드 */
	private String authorCode;

	/** 기타VO변수 */
	private String tempValue;
	private int tempInt;

	/** Login 메뉴관련 VO변수 */
	/** tmp_Id */
	private String tmpId;
	/** tmp_Password */
	private String tmpPassword;
	/** tmp_Name */
	private String tmpName;
	/** tmp_UserSe */
	private String tmpUserSe;
	/** tmp_Email */
	private String tmpEmail;
	/** tmp_OrgnztId */
	private String tmpOrgnztId;
	/** tmp_UniqId */
	private String tmpUniqId;
	/** tmp_Cmd */
	private String tmpCmd;

}
