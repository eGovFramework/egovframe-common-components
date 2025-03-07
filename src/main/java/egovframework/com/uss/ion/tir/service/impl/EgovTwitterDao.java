package egovframework.com.uss.ion.tir.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
/**
 * RSS태그관리를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.10.04
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.10.04  장동한          최초 생성
 *
 * </pre>
 */
@Repository("twitterDao")
public class EgovTwitterDao extends EgovComAbstractDAO {

    /**
     * 트위터 계정을 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return Map - 조회 정보가 담긴 Map
     * @throws Exception
     */
    public Map<?, ?> selectTwitterAccount(Map<?, ?> param) throws Exception {
    	return (Map<?, ?>)selectOne("Twitter.selectTwitterAccount",param);
    }


    /**
     * 트위터 계정을 건수를 조회 한다.
     * @param param -조회할 정보가 담긴 객체
     * @return int - 조회 정보가 담긴 Integer
     * @throws Exception
     */
    public int selectTwitterAccountCheck(Map<?, ?> param) throws Exception {
    	return (Integer)selectOne("Twitter.selectTwitterAccountCheck",param);
    }

	/**
	 * 트위터 계정을 신규로 등록한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	public void insertTwitterAccount(Map<?, ?> param) throws Exception {
		insert("Twitter.insertTwitterAccount", param);
	}

	/**
	 * 트위터 계정을 수정한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	public void updtTwitterAccount(Map<?, ?> param) throws Exception {
		update("Twitter.updateTwitterAccount", param);
	}

	/**
	 * 트위터 계정을 삭제한다.
	 * @param param - 조회할 정보가 담긴 Map
	 */
	public void deleteTwitterAccount(Map<?, ?> param) throws Exception {
        delete("Twitter.deleteTwitterAccount",param);
	}
}
