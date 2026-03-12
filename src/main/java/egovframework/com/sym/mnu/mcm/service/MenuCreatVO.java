package egovframework.com.sym.mnu.mcm.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Size;
 
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
public class MenuCreatVO{


   /** 메뉴번호 */
   @EgovNullCheck
   private   int      menuNo;
   /** 메뉴순서 */
   @EgovNullCheck
   private   int      menuOrdr;
   /** 메뉴명 */
   @EgovNullCheck
   @Size(max=50)
   private   String   menuNm;
   /** 상위메뉴번호 */
   @EgovNullCheck
   private   int      upperMenuId;
   /** 프로그램파일명 */
   @EgovNullCheck
   @Size(max=50)
   private   String   progrmFileNm;
   /** 메뉴설명 */
   @Size(max=100)
   private   String   menuDc;
   /** 관련이미지경로 */
   @Size(max=100)
   private   String   relateImagePath;
   /** 관련이미지명 */
   @Size(max=50)
   private   String   relateImageNm;
   /** 맵생성ID */
   private   String   mapCreatId;
   /** 권한코드 */
   private   String   authorCode;

   /** 권한정보설정 */
   /** 권한명 */
   private   String   authorNm;
   /** 권한설명 */
   private   String   authorDc;
   /** 권한생성일자 */
   private   String   authorCreatDe;

   /** 기타VO변수 */
   /** 생성자ID **/
   private   String   creatPersonId;

   
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

	public int getMenuOrdr() {
		return menuOrdr;
	}

	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public int getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(int upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public String getProgrmFileNm() {
		return progrmFileNm;
	}

	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	public String getMenuDc() {
		return menuDc;
	}

	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}

	public String getRelateImagePath() {
		return relateImagePath;
	}

	public void setRelateImagePath(String relateImagePath) {
		this.relateImagePath = relateImagePath;
	}

	public String getRelateImageNm() {
		return relateImageNm;
	}

	public void setRelateImageNm(String relateImageNm) {
		this.relateImageNm = relateImageNm;
	}


}