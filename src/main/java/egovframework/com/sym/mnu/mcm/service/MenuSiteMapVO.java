package egovframework.com.sym.mnu.mcm.service;

/**
 * 사이트맵/메인메뉴 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이용           최초 생성
 *   2025.07.16  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-FormalParameterNamingConventions(변수명에 밑줄 사용)
 *
 *      </pre>
 */
public class MenuSiteMapVO {

	/** 메뉴번호 */
	private int menuNo;

	/* 사이트맵 */
	/** 생성자ID **/
	private String creatPersonId;
	/** 맵생성ID */
	private String mapCreatId;
	/** 맵파일명 */
	private String bndeFileNm;
	/** 맵파일경로 */
	private String bndeFilePath;

	/* 권한정보설정 */
	/** 권한코드 */
	private String authorCode;
	/** 권한명 */
	private String authorNm;
	/** 권한설명 */
	private String authorDc;
	/** 권한생성일자 */
	private String authorCreatDe;

	/* 기타VO변수 */
	/** rootPath Temp */
	private String tmpRootPath;

	/* Login 메뉴관련 VO변수 */
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

	/**
	 * menuNo attribute를 리턴한다.
	 * 
	 * @return int
	 */
	public int getMenuNo() {
		return menuNo;
	}

	/**
	 * menuNo attribute 값을 설정한다.
	 * 
	 * @param menuNo int
	 */
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	/**
	 * creatPersonId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getCreatPersonId() {
		return creatPersonId;
	}

	/**
	 * creatPersonId attribute 값을 설정한다.
	 * 
	 * @param creatPersonId String
	 */
	public void setCreatPersonId(String creatPersonId) {
		this.creatPersonId = creatPersonId;
	}

	/**
	 * mapCreatId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getMapCreatId() {
		return mapCreatId;
	}

	/**
	 * mapCreatId attribute 값을 설정한다.
	 * 
	 * @param mapCreatId String
	 */
	public void setMapCreatId(String mapCreatId) {
		this.mapCreatId = mapCreatId;
	}

	/**
	 * bndeFileNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getBndeFileNm() {
		return bndeFileNm;
	}

	/**
	 * bndeFileNm attribute 값을 설정한다.
	 * 
	 * @param bndeFileNm String
	 */
	public void setBndeFileNm(String bndeFileNm) {
		this.bndeFileNm = bndeFileNm;
	}

	/**
	 * bndeFilePath attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getBndeFilePath() {
		return bndeFilePath;
	}

	/**
	 * bndeFilePath attribute 값을 설정한다.
	 * 
	 * @param bndeFilePath String
	 */
	public void setBndeFilePath(String bndeFilePath) {
		this.bndeFilePath = bndeFilePath;
	}

	/**
	 * authorCode attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}

	/**
	 * authorCode attribute 값을 설정한다.
	 * 
	 * @param authorCode String
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	/**
	 * authorNm attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorNm() {
		return authorNm;
	}

	/**
	 * authorNm attribute 값을 설정한다.
	 * 
	 * @param authorNm String
	 */
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	/**
	 * authorDc attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorDc() {
		return authorDc;
	}

	/**
	 * authorDc attribute 값을 설정한다.
	 * 
	 * @param authorDc String
	 */
	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
	}

	/**
	 * authorCreatDe attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getAuthorCreatDe() {
		return authorCreatDe;
	}

	/**
	 * authorCreatDe attribute 값을 설정한다.
	 * 
	 * @param authorCreatDe String
	 */
	public void setAuthorCreatDe(String authorCreatDe) {
		this.authorCreatDe = authorCreatDe;
	}

	/**
	 * tmpId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpId() {
		return tmpId;
	}

	/**
	 * tmpId attribute 값을 설정한다.
	 * 
	 * @param tmpId String
	 */
	public void setTmpId(String tmpId) {
		this.tmpId = tmpId;
	}

	/**
	 * tmpPassword attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpPassword() {
		return tmpPassword;
	}

	/**
	 * tmpPassword attribute 값을 설정한다.
	 * 
	 * @param tmpPassword String
	 */
	public void setTmpPassword(String tmpPassword) {
		this.tmpPassword = tmpPassword;
	}

	/**
	 * tmpName attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpName() {
		return tmpName;
	}

	/**
	 * tmpName attribute 값을 설정한다.
	 * 
	 * @param tmpName String
	 */
	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	/**
	 * tmpUserSe attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpUserSe() {
		return tmpUserSe;
	}

	/**
	 * tmpUserSe attribute 값을 설정한다.
	 * 
	 * @param tmpUserSe String
	 */
	public void setTmpUserSe(String tmpUserSe) {
		this.tmpUserSe = tmpUserSe;
	}

	/**
	 * tmpEmail attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpEmail() {
		return tmpEmail;
	}

	/**
	 * tmpEmail attribute 값을 설정한다.
	 * 
	 * @param tmpEmail String
	 */
	public void setTmpEmail(String tmpEmail) {
		this.tmpEmail = tmpEmail;
	}

	/**
	 * tmpOrgnztId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpOrgnztId() {
		return tmpOrgnztId;
	}

	/**
	 * tmpOrgnztId attribute 값을 설정한다.
	 * 
	 * @param tmpOrgnztId String
	 */
	public void setTmpOrgnztId(String tmpOrgnztId) {
		this.tmpOrgnztId = tmpOrgnztId;
	}

	/**
	 * tmpUniqId attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpUniqId() {
		return tmpUniqId;
	}

	/**
	 * tmpUniqId attribute 값을 설정한다.
	 * 
	 * @param tmpUniqId String
	 */
	public void setTmpUniqId(String tmpUniqId) {
		this.tmpUniqId = tmpUniqId;
	}

	/**
	 * tmpCmd attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpCmd() {
		return tmpCmd;
	}

	/**
	 * tmpCmd attribute 값을 설정한다.
	 * 
	 * @param tmpCmd String
	 */
	public void setTmpCmd(String tmpCmd) {
		this.tmpCmd = tmpCmd;
	}

	/**
	 * tmprootPath attribute를 리턴한다.
	 * 
	 * @return String
	 */
	public String getTmpRootPath() {
		return tmpRootPath;
	}

	/**
	 * tmprootPath attribute 값을 설정한다.
	 * 
	 * @param tmprootPath String
	 */
	public void setTmpRootPath(String tmprootPath) {
		this.tmpRootPath = tmprootPath;
	}

}