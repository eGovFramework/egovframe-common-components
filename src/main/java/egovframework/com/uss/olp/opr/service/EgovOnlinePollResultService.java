package egovframework.com.uss.olp.opr.service;

import java.util.List;
/**
 * 온라인POLL결과를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
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
public interface EgovOnlinePollResultService {


    /**
     * 온라인POLL결과를(을) 목록을 한다.
     * @param onlinePollResult  온라인POLL결과 정보 담김 VO
     * @return List
     * @throws Exception
     */
    public List<?> selectOnlinePollResultList(OnlinePollResult onlinePollResult) throws Exception ;

    /**
     * 온라인POLL결과를(을) 삭제 한다.
     * @param onlinePollResult  온라인POLL결과 정보가 담김 VO
     * @return void
     * @throws Exception
     */
    public void deleteOnlinePollResult(OnlinePollResult onlinePollResult) throws Exception ;
}
