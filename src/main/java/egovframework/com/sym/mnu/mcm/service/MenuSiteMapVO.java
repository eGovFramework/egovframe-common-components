package egovframework.com.sym.mnu.mcm.service;
/** 
 * 사이트맵/메인메뉴 처리를 위한 VO 클래스르를 정의한다
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

public class MenuSiteMapVO{
	
   /** 메뉴번호 */
   private   int      menuNo;
   
   /* 사이트맵 */
   /** 생성자ID **/
   private   String   creatPersonId;
   /** 맵생성ID */
   private   String   mapCreatId;
   /** 맵파일명 */
   private   String   bndeFileNm;
   /** 맵파일경로 */
   private   String   bndeFilePath;

   /* 권한정보설정 */
   /** 권한코드 */
   private   String   authorCode;
   /** 권한명 */
   private   String   authorNm;
   /** 권한설명 */
   private   String   authorDc;
   /** 권한생성일자 */
   private   String   authorCreatDe;

   /* 기타VO변수 */
   /** rootPath Temp */
   private   String   tmpRootPath;

/* Login 메뉴관련 VO변수 */
   /** tmp_Id */
   private   String   tmpId;
   /** tmp_Password */
   private   String   tmpPassword;
   /** tmp_Name */
   private   String   tmpName;
   /** tmp_UserSe */
   private   String   tmpUserSe;
   /** tmp_Email */
   private   String   tmpEmail;
   /** tmp_OrgnztId */
   private   String   tmpOrgnztId;
   /** tmp_UniqId */
   private   String   tmpUniqId;
   /** tmp_Cmd */
   private   String   tmpCmd;
   
	/**
	 * menuNo attribute를 리턴한다.
	 * @return int
	 */
	public int getMenuNo() {
		return menuNo;
	}
	/**
	 * menuNo attribute 값을 설정한다.
	 * @param menuNo int
	 */
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	/**
	 * creatPersonId attribute를 리턴한다.
	 * @return String
	 */
	public String getCreatPersonId() {
		return creatPersonId;
	}
	/**
	 * creatPersonId attribute 값을 설정한다.
	 * @param creatPersonId String
	 */
	public void setCreatPersonId(String creatPersonId) {
		this.creatPersonId = creatPersonId;
	}
	/**
	 * mapCreatId attribute를 리턴한다.
	 * @return String
	 */
	public String getMapCreatId() {
		return mapCreatId;
	}
	/**
	 * mapCreatId attribute 값을 설정한다.
	 * @param mapCreatId String
	 */
	public void setMapCreatId(String mapCreatId) {
		this.mapCreatId = mapCreatId;
	}
	/**
	 * bndeFileNm attribute를 리턴한다.
	 * @return String
	 */
	public String getBndeFileNm() {
		return bndeFileNm;
	}
	/**
	 * bndeFileNm attribute 값을 설정한다.
	 * @param bndeFileNm String
	 */
	public void setBndeFileNm(String bndeFileNm) {
		this.bndeFileNm = bndeFileNm;
	}
	/**
	 * bndeFilePath attribute를 리턴한다.
	 * @return String
	 */
	public String getBndeFilePath() {
		return bndeFilePath;
	}
	/**
	 * bndeFilePath attribute 값을 설정한다.
	 * @param bndeFilePath String
	 */
	public void setBndeFilePath(String bndeFilePath) {
		this.bndeFilePath = bndeFilePath;
	}
	/**
	 * authorCode attribute를 리턴한다.
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * authorCode attribute 값을 설정한다.
	 * @param authorCode String
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	/**
	 * authorNm attribute를 리턴한다.
	 * @return String
	 */
	public String getAuthorNm() {
		return authorNm;
	}
	/**
	 * authorNm attribute 값을 설정한다.
	 * @param authorNm String
	 */
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}
	/**
	 * authorDc attribute를 리턴한다.
	 * @return String
	 */
	public String getAuthorDc() {
		return authorDc;
	}
	/**
	 * authorDc attribute 값을 설정한다.
	 * @param authorDc String
	 */
	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
	}
	/**
	 * authorCreatDe attribute를 리턴한다.
	 * @return String
	 */
	public String getAuthorCreatDe() {
		return authorCreatDe;
	}
	/**
	 * authorCreatDe attribute 값을 설정한다.
	 * @param authorCreatDe String
	 */
	public void setAuthorCreatDe(String authorCreatDe) {
		this.authorCreatDe = authorCreatDe;
	}

	/**
	 * tmp_Id attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpId() {
		return tmpId;
	}
	/**
	 * tmp_Id attribute 값을 설정한다.
	 * @param tmp_Id String
	 */
	public void setTmpId(String tmp_Id) {
		this.tmpId = tmp_Id;
	}
	/**
	 * tmp_Password attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpPassword() {
		return tmpPassword;
	}
	/**
	 * tmp_Password attribute 값을 설정한다.
	 * @param tmp_Password String
	 */
	public void setTmpPassword(String tmp_Password) {
		this.tmpPassword = tmp_Password;
	}
	/**
	 * tmp_Name attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpName() {
		return tmpName;
	}
	/**
	 * tmp_Name attribute 값을 설정한다.
	 * @param tmp_Name String
	 */
	public void setTmpName(String tmp_Name) {
		this.tmpName = tmp_Name;
	}
	/**
	 * tmp_UserSe attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpUserSe() {
		return tmpUserSe;
	}
	/**
	 * tmp_UserSe attribute 값을 설정한다.
	 * @param tmp_UserSe String
	 */
	public void setTmpUserSe(String tmp_UserSe) {
		this.tmpUserSe = tmp_UserSe;
	}
	/**
	 * tmp_Email attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpEmail() {
		return tmpEmail;
	}
	/**
	 * tmp_Email attribute 값을 설정한다.
	 * @param tmp_Email String
	 */
	public void setTmpEmail(String tmp_Email) {
		this.tmpEmail = tmp_Email;
	}
	/**
	 * tmp_OrgnztId attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpOrgnztId() {
		return tmpOrgnztId;
	}
	/**
	 * tmp_OrgnztId attribute 값을 설정한다.
	 * @param tmp_OrgnztId String
	 */
	public void setTmpOrgnztId(String tmp_OrgnztId) {
		this.tmpOrgnztId = tmp_OrgnztId;
	}
	/**
	 * tmp_UniqId attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpUniqId() {
		return tmpUniqId;
	}
	/**
	 * tmp_UniqId attribute 값을 설정한다.
	 * @param tmp_UniqId String
	 */
	public void setTmpUniqId(String tmp_UniqId) {
		this.tmpUniqId = tmp_UniqId;
	}
	/**
	 * tmp_Cmd attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpCmd() {
		return tmpCmd;
	}
	/**
	 * tmp_Cmd attribute 값을 설정한다.
	 * @param tmp_Cmd String
	 */
	public void setTmpCmd(String tmp_Cmd) {
		this.tmpCmd = tmp_Cmd;
	}

	/**
	 * tmp_rootPath attribute를 리턴한다.
	 * @return String
	 */
	public String getTmpRootPath() {
		return tmpRootPath;
	}
	/**
	 * tmp_rootPath attribute 값을 설정한다.
	 * @param tmp_rootPath String
	 */
	public void setTmpRootPath(String tmp_rootPath) {
		this.tmpRootPath = tmp_rootPath;
	}

}