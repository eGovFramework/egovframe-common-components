package egovframework.com.uss.olp.opr.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olp.opr.service.OnlinePollResult;

import org.springframework.stereotype.Repository;

/**
 * 온라인POLL결과를 처리하는 Dao Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
@Repository("onlinePollResultDao")
public class OnlinePollResultDao extends EgovComAbstractDAO {

    /**
     * 온라인POLL결과를(을) 목록을 한다.
     * @param onlinePollResult  온라인POLL결과 정보 담김 VO
     * @return List
     * @throws Exception
     */
    public List<?> selectOnlinePollResultList(OnlinePollResult onlinePollResult) throws Exception {
        return list("OnlinePollResult.selectOnlinePollResult", onlinePollResult);
    }

    /**
     * 온라인POLL결과를(을) 삭제 한다.
     * @param onlinePollResult  온라인POLL결과 정보가 담김 VO
     * @return void
     * @throws Exception
     */
    public void deleteOnlinePollResult(OnlinePollResult onlinePollResult) throws Exception {
        delete("OnlinePollResult.deleteOnlinePollResult", onlinePollResult);
    }


}
