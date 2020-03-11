package egovframework.com.utl.pao.service;

/**
 * 
 * 관인 처리 Util 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.02.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01  이중호          최초 생성
 *
 * </pre>
 */
public interface EgovPrntngOutpt {

	/**
	 * 전자관인 정보를 제공하는 기능
	 */
	PrntngOutptVO selectErncsl(PrntngOutptVO searchVO) throws Exception;

}
