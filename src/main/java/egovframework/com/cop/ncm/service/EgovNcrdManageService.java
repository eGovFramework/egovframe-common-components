package egovframework.com.cop.ncm.service;

import java.util.Map;


/**
 * 명함정보를 관리하기 위한 서비스 인터페이스 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.28  이삼섭          최초 생성
 *
 * </pre>
 */
public interface EgovNcrdManageService {

    /**
     * 명함 정보를 삭제한다.
     * 
     * @param nameCard
     * @throws Exception
     */

	public void deleteNcrdItem(NameCardVO namecardVO) throws Exception;
    /**
     * 명함 정보 및 명함사용자 정보를 등록한다.
     * 
     * @param nameCard
     * @throws Exception
     */
    public void insertNcrdItem(NameCard nameCard) throws Exception;

    /**
     * 명함사용자 정보를 등록한다.
     * 
     * @param ncrdUser
     * @throws Exception
     */
    public void insertNcrdUseInf(NameCardUser ncrdUser) throws Exception;

    /**
     * 명함 정보에 대한 상세정보를 조회한다.
     * 
     * @param nameCard
     * @return
     * @throws Exception
     */
    public NameCardVO selectNcrdItem(NameCardVO ncrdVO) throws Exception;

    /**
     * 명함 정보에 대한 목록을 조회한다.
     * 
     * @param nameCard
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectNcrdItems(NameCardVO ncrdVO) throws Exception;

    /**
     * 명함 정보에 대한 목록 전체 건수를 조회한다.
     * 
     * @param ncrdUser
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectNcrdUseInfs(NameCardUser ncrdUser) throws Exception;

    /**
     * 명함 정보를 수정한다.
     * 
     * @param nameCard
     * @throws Exception
     */
    public void updateNcrdItem(NameCard nameCard) throws Exception;

    /**
     * 명함사용자 정보를 수정한다.
     * 
     * @param ncrdUser
     * @throws Exception
     */
    public void updateNcrdUseInf(NameCardUser ncrdUser) throws Exception;

    /**
     * 내 명함 정보에 대한 목록을 조회한다.
     * 
     * @param ncrdVO
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectMyNcrdItems(NameCardVO ncrdVO) throws Exception;
    
}
