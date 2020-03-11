package egovframework.com.uss.ion.ntr.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.ntr.service.NoteRecptn;

import org.springframework.stereotype.Repository;
/**
 * 받은쪽지함관리를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2017.09.14	 장동한		   표준프레임워크 3.7 개선
 *
 * </pre>
 */
@Repository("noteRecptnDao")
public class NoteRecptnDao extends EgovComAbstractDAO {

    /**
     * 받은쪽지함관리를(을) 목록을 한다.
     * @param noteRecptn  -조회할 정보가 담긴 객체
     * @return List -조회한목록
     * @throws Exception
     */
    public List<?> selectNoteRecptnList(NoteRecptn noteRecptn) throws Exception {
    	return selectList("NoteRecptn.selectNoteRecptn", noteRecptn);
    }

    /**
     * 받은쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param noteRecptn  -조회할 정보가 담긴 객체
     * @return int -조회한건수
     * @throws Exception
     */
    public int selectNoteRecptnListCnt(NoteRecptn noteRecptn) throws Exception {
    	return (Integer)selectOne("NoteRecptn.selectNoteRecptnCnt", noteRecptn);
    }

    /**
     * 받은쪽지함관리를 개봉으로 상태를 바꾼다.
     * @param noteRecptn  -받은쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void updateNoteRecptnRelationOpenYn(NoteRecptn noteRecptn) throws Exception {
    	update("NoteRecptn.updateNoteRecptnRelationOpenYn" , noteRecptn);
    }

    /**
     * 받은쪽지함관리를(을) 상세조회 한다.
     * @param noteRecptn  -받은쪽지함관리 정보가 담김 객체
     * @return NoteRecptn -조회한받은쪽지함객체
     * @throws Exception
     */
    public Map<?, ?> selectNoteRecptnDetail(NoteRecptn noteRecptn) throws Exception {
    	return (Map<?, ?>)selectOne("NoteRecptn.selectNoteRecptnDetail", noteRecptn);
    }

    /**
     * 쪽지관리/쪽지관리,보낸보낸쪽지함, 받은쪽지함 삭제
     * @param noteRecptn  -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteRecptnRelation(NoteRecptn noteRecptn) throws Exception {
        delete("NoteRecptn.deleteNoteRecptnRelation" , noteRecptn);
    }

    /**
     * 받은쪽지함관리를(을) 삭제한다.
     * @param noteRecptn  -받은쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteRecptn(NoteRecptn noteRecptn) throws Exception {
    	delete("NoteRecptn.deleteNoteRecptn" , noteRecptn);
    }

    /**
     * 쪽지관리/보낸족지함삭제
     * @param noteRecptn  -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteTrnsmit(NoteRecptn noteRecptn) throws Exception {
        delete("NoteRecptn.deleteNoteTrnsmit" , noteRecptn);
    }

    /**
     * 쪽지관리/쪽지관리삭제
     * @param noteRecptn  -보낸쪽지함관리 정보가 담김 객체
     * @throws Exception
     */
    public void deleteNoteManage(NoteRecptn noteRecptn) throws Exception {
        delete("NoteRecptn.deleteNoteManage" , noteRecptn);
    }

    /**
     * 보낸쪽지함관리 건수를 조회한다.
     * @param  noteRecptn  -조회할 정보가 담긴 객체
     * @return int -조회한건수
     * @throws Exception
     */
    public int selectNoteTrnsmitRelationCnt(NoteRecptn noteRecptn) throws Exception {
    	return (Integer)selectOne("NoteRecptn.selectNoteTrnsmitRelationCnt", noteRecptn);
    }

    /**
     * 받은쪽지함관리 건수를 조회한다.
     * @param noteRecptn  조회할 정보가 담긴 객체
     * @return int -조회한건수
     * @throws Exception
     */
    public int selectNoteRecptnRelationCnt(NoteRecptn noteRecptn) throws Exception {
    	return (Integer)selectOne("NoteRecptn.selectNoteRecptnRelationCnt", noteRecptn);
    }
}
