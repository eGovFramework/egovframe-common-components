package egovframework.com.sym.mnu.mcm.service;

/** 
 * 사이트맵 생성을 위한 클래스를 정의한다
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
public class MenuSiteMap{
	   public String getCreatPersonId() {
		return creatPersonId;
	}
	public void setCreatPersonId(String creatPersonId) {
		this.creatPersonId = creatPersonId;
	}
	public String getMapCreatId() {
		return mapCreatId;
	}
	public void setMapCreatId(String mapCreatId) {
		this.mapCreatId = mapCreatId;
	}
	public String getBndeFileNm() {
		return bndeFileNm;
	}
	public void setBndeFileNm(String bndeFileNm) {
		this.bndeFileNm = bndeFileNm;
	}
	public String getBndeFilePath() {
		return bndeFilePath;
	}
	public void setBndeFilePath(String bndeFilePath) {
		this.bndeFilePath = bndeFilePath;
	}
	/** 사이트맵 */
	   /** 생성자ID **/
	   private   String   creatPersonId;
	   /** 맵생성ID */
	   private   String   mapCreatId;
	   /** 맵파일명 */
	   private   String   bndeFileNm;
	   /** 맵파일경로 */
	   private   String   bndeFilePath;
}