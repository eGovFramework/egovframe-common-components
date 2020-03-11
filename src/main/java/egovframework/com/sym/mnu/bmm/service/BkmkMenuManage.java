package egovframework.com.sym.mnu.bmm.service;

import java.io.Serializable;

/**
 * 바로가기메뉴관리를 위한 모델 클래스
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
@SuppressWarnings("serial")
public class BkmkMenuManage implements Serializable{

    /** 메뉴 아이디 */
    String menuId = "";
    
    /** 메뉴명 */
    String menuNm = "";
    
    /** 메뉴 URL */
    String progrmStrePath = "";
    
    /** 등록자 아이디 */
    String userId = "";
    
    /**
     * menuId attribute를 리턴한다.
     * 
     * @return the menuId
     */
    public String getMenuId() {
        return menuId;
    }    

    /**
     * menuId attribute 값을 설정한다.
     * 
     * @param menuId
     *            the menuId to set
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * menuNm attribute를 리턴한다.
     * 
     * @return the menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }    

    /**
     * menuNm attribute 값을 설정한다.
     * 
     * @param menuNm
     *            the menuNm to set
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }
    
    /**
     * progrmStrePath attribute를 리턴한다.
     * 
     * @return the progrmStrePath
     */
    public String getProgrmStrePath() {
        return progrmStrePath;
    }
    

    /**
     * progrmStrePath attribute 값을 설정한다.
     * 
     * @param progrmStrePath
     *            the progrmStrePath to set
     */
    public void setProgrmStrePath(String progrmStrePath) {
        this.progrmStrePath = progrmStrePath;
    }
    
    /**
     * userId attribute를 리턴한다.
     * 
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }    

    /**
     * userId attribute 값을 설정한다.
     * 
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    } 
}
