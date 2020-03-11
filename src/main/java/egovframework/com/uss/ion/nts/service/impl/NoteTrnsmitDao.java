package egovframework.com.uss.ion.nts.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.nts.service.NoteTrnsmit;

import org.springframework.stereotype.Repository;
/**
 * 보낸쪽지함관리를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2017.09.14	 장동한 		   표준프레임워크 3.7 개선
 *
 * </pre>
 */
@Repository("noteTrnsmitDao")
public class NoteTrnsmitDao extends EgovComAbstractDAO {

    /**
     * 보낸쪽지함관리를(을) 목록을 한다.
     * @param noteTrnsmit -조회할 정보가 담긴 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitList(NoteTrnsmit noteTrnsmit) throws Exception {
    	return list("NoteTrnsmit.selectNoteTrnsmit", noteTrnsmit);
    }

    /**
     * 보낸쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param noteTrnsmit -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectNoteTrnsmitListCnt(NoteTrnsmit noteTrnsmit) throws Exception {
    	return (Integer)selectOne("NoteTrnsmit.selectNoteTrnsmitCnt", noteTrnsmit);
    }

    /**
     * 보낸쪽지함관리를(을) 상세조회 한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @return Map -조회한정보가담긴Map
     * @throws Exception
     */
    public Map<?, ?> selectNoteTrnsmitDetail(NoteTrnsmit noteTrnsmit) throws Exception {
    	return (Map<?, ?>)selectOne("NoteTrnsmit.selectNoteTrnsmitDetail", noteTrnsmit);
    }

    /**
     * 보낸쪽지함관리를(을) 삭제한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteTrnsmit(NoteTrnsmit noteTrnsmit) throws Exception {
        delete("NoteTrnsmit.deleteNoteTrnsmit" , noteTrnsmit);
    }

    /**
     * 받은쪽지함를(을) 삭제한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteRecptn(NoteTrnsmit noteTrnsmit) throws Exception {
        delete("NoteTrnsmit.deleteNoteRecptn" , noteTrnsmit);
    }

    /**
     * 쪽지를(을) 삭제한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteManage(NoteTrnsmit noteTrnsmit) throws Exception {
        delete("NoteTrnsmit.deleteNoteManage" , noteTrnsmit);
    }

    /**
     * 쪽지관리/보낸족지함삭제
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteTrnsmitRelation(NoteTrnsmit noteTrnsmit) throws Exception {
        delete("NoteTrnsmit.deleteNoteTrnsmitRelation" , noteTrnsmit);
    }

    /**
     * 받은편지함 건수를 조회한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectTrnsmitRelationCnt(NoteTrnsmit noteTrnsmit) throws Exception {
    	return (Integer)selectOne("NoteTrnsmit.selectTrnsmitRelationCnt", noteTrnsmit);
    }


    /**
     * 수신자목록을 조회한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담김 객체
     * @return List -조회한목록이담긴List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitCnfirm(NoteTrnsmit noteTrnsmit) throws Exception {
    	return list("NoteTrnsmit.selectNoteTrnsmitCnfirm", noteTrnsmit);
    }
}
