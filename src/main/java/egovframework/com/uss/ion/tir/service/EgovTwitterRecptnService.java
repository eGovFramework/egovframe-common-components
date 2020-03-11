package egovframework.com.uss.ion.tir.service;

import java.util.List;
import java.util.Map;
/**
 * 트위터수신을 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovTwitterRecptnService {

    /**
	 * 트위터 목록을 조회한다.
	 * @param sTwitterId 	-트위터 아이디
	 * @param sTwitterPw 	-트위터 비밀번호
	 * @param nPageSize 	-페이징 갯수
     * @return List 		-조회 결과
     * @throws Exception	-Exception Throws
	 */
	public List<?> twitterRecptnList(Map<?, ?> map, int nPageSize) throws Exception;

}
