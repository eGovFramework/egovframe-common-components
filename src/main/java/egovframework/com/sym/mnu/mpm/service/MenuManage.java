package egovframework.com.sym.mnu.mpm.service;

/**
 * 메뉴관리, 메뉴 생성을 위한 모델 클래스를 정의한다.
 * @author 공통서비스 개발팀 이 용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이용          최초 생성
 *
 * </pre>
 */

public class MenuManage {

	/**
	 * 메뉴설명
	 */
	private String menuDc;
	public String getMenuDc() {
		return menuDc;
	}
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public int getMenuOrdr() {
		return menuOrdr;
	}
	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}
	public String getRelateImageNm() {
		return relateImageNm;
	}
	public void setRelateImageNm(String relateImageNm) {
		this.relateImageNm = relateImageNm;
	}
	public String getRelateImagePath() {
		return relateImagePath;
	}
	public void setRelateImagePath(String relateImagePath) {
		this.relateImagePath = relateImagePath;
	}
	public int getUpperMenuId() {
		return upperMenuId;
	}
	public void setUpperMenuId(int upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	/**
	 * 메뉴명
	 */
	private String menuNm;
	/**
	 * 메뉴번호
	 */
	private int menuNo;
	/**
	 * 메뉴순서
	 */
	private int menuOrdr;
	/**
	 * 프로그램파일명
	 */
	private String progrmFileNm;
	/**
	 * 관련이미지명
	 */
	private String relateImageNm;
	/**
	 * 관련이미지경로
	 */
	private String relateImagePath;
	/**
	 * 상위메뉴번호
	 */
	private int upperMenuId;
}