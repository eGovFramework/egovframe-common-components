package egovframework.com.sym.mnu.mpm.service;

/** 
 * 메뉴목록관리 처리를 위한 VO 클래스르를 정의한다
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

public class MenuManageVO{

   /** 메뉴정보 */
   /** 메뉴번호 */
   private   int      menuNo;
   /** 메뉴순서 */
   private   int      menuOrdr;
   /** 메뉴명 */
   private   String   menuNm;
   /** 상위메뉴번호 */
   private   int      upperMenuId;
   /** 메뉴설명 */
   private   String   menuDc;
   /** 관련이미지경로 */
   private   String   relateImagePath;
   /** 관련이미지명 */
   private   String   relateImageNm;
   /** 프로그램파일명 */
   private   String   progrmFileNm;

   /** 사이트맵 */
   /** 생성자ID **/
   private   String   creatPersonId;

   /** 권한정보설정 */
   /** 권한코드 */
   private   String   authorCode;

   /** 기타VO변수 */
   private   String   tempValue;
   private   int      tempInt; 
   

   /** Login 메뉴관련 VO변수 */
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
	 * menuOrdr attribute를 리턴한다.
	 * @return int
	 */
	public int getMenuOrdr() {
		return menuOrdr;
	}
	/**
	 * menuOrdr attribute 값을 설정한다.
	 * @param menuOrdr int
	 */
	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}
	/**
	 * menuNm attribute를 리턴한다.
	 * @return String
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * menuNm attribute 값을 설정한다.
	 * @param menuNm String
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	/**
	 * upperMenuId attribute를 리턴한다.
	 * @return int
	 */
	public int getUpperMenuId() {
		return upperMenuId;
	}
	/**
	 * upperMenuId attribute 값을 설정한다.
	 * @param upperMenuId int
	 */
	public void setUpperMenuId(int upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	/**
	 * menuDc attribute를 리턴한다.
	 * @return String
	 */
	public String getMenuDc() {
		return menuDc;
	}
	/**
	 * menuDc attribute 값을 설정한다.
	 * @param menuDc String
	 */
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}
	/**
	 * relateImagePath attribute를 리턴한다.
	 * @return String
	 */
	public String getRelateImagePath() {
		return relateImagePath;
	}
	/**
	 * relateImagePath attribute 값을 설정한다.
	 * @param relateImagePath String
	 */
	public void setRelateImagePath(String relateImagePath) {
		this.relateImagePath = relateImagePath;
	}
	/**
	 * relateImageNm attribute를 리턴한다.
	 * @return String
	 */
	public String getRelateImageNm() {
		return relateImageNm;
	}
	/**
	 * relateImageNm attribute 값을 설정한다.
	 * @param relateImageNm String
	 */
	public void setRelateImageNm(String relateImageNm) {
		this.relateImageNm = relateImageNm;
	}
	/**
	 * progrmFileNm attribute를 리턴한다.
	 * @return String
	 */
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	/**
	 * progrmFileNm attribute 값을 설정한다.
	 * @param progrmFileNm String
	 */
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
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
	public void setTmpUniqId(String tmpUniqId) {
		this.tmpUniqId = tmpUniqId;
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
	 * tempValue attribute를 리턴한다.
	 * @return String
	 */
	public String getTempValue() {
		return tempValue;
	}
	/**
	 * tempValue attribute 값을 설정한다.
	 * @param tempValue String
	 */
	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}
	/**
	 * tempInt attribute를 리턴한다.
	 * @return int
	 */
	public int getTempInt() {
		return tempInt;
	}
	/**
	 * tempInt attribute 값을 설정한다.
	 * @param tempInt int
	 */
	public void setTempInt(int tempInt) {
		this.tempInt = tempInt;
	}
}