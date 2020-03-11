package egovframework.com.sym.mnu.stm.service;

/** 
 * 사이트맵 조회를 위한 VO 클래스르를 정의한다.
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
public class SiteMapngVO{

   /** 메뉴생성내역 */
   /** 보안설정대상ID */
   private   String   scrtyEstbstrgetId;

   /** 사이트맵 */
   /** 생성자ID **/
   private   String   creatPersonId;
   /** 맵생성ID */
   private   String   mapCreatId;
   /** 맵파일명 */
   private   String   bndeFileNm;
   /** 맵파일경로 */
   private   String   bndeFilePath;
   
	/**
	 * scrtyEstbstrgetId attribute를 리턴한다.
	 * @return String
	 */
	public String getScrtyEstbstrgetId() {
		return scrtyEstbstrgetId;
	}
	/**
	 * scrtyEstbstrgetId attribute 값을 설정한다.
	 * @param scrtyEstbstrgetId String
	 */
	public void setScrtyEstbstrgetId(String scrtyEstbstrgetId) {
		this.scrtyEstbstrgetId = scrtyEstbstrgetId;
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


}