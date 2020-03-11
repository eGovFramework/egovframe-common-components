package egovframework.com.uss.ion.ntr.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.uss.ion.ntr.service.EgovNoteRecptnService;
import egovframework.com.uss.ion.ntr.service.NoteRecptn;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * 받은쪽지함관리를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
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
@Service("egovNoteRecptnService")
public class EgovNoteRecptnServiceImpl extends EgovAbstractServiceImpl
        implements EgovNoteRecptnService {

    @Resource(name = "noteRecptnDao")
    private NoteRecptnDao dao;

    /**
     * 받은쪽지함관리를(을) 목록을 조회 한다.
     * @param noteRecptn -조회할 정보가 담긴 객체
     * @return List -조회한목록
     * @throws Exception
     */
    @Override
	public List<?> selectNoteRecptnList(NoteRecptn noteRecptn) throws Exception {
    	return dao.selectNoteRecptnList(noteRecptn);
    }

    /**
     * 받은쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param noteRecptn -조회할 정보가 담긴 객체
     * @return int -조회한전체건수
     * @throws Exception
     */
    @Override
	public int selectNoteRecptnListCnt(NoteRecptn noteRecptn) throws Exception {
        return dao.selectNoteRecptnListCnt(noteRecptn);
    }

    /**
     * 받은쪽지함관리를(을) 상세조회 한다.
     * @param noteRecptn -받은쪽지함관리 Model
     * @return Map -성세조회정보가 담긴 Map
     * @throws Exception
     */
    @Override
	public Map<?, ?> selectNoteRecptnDetail(NoteRecptn noteRecptn) throws Exception {
    	//받은쪽지함관리를 개봉으로 상태를 바꾼다.
    	dao.updateNoteRecptnRelationOpenYn(noteRecptn);
        return dao.selectNoteRecptnDetail(noteRecptn);
    }

    /**
     * 받은쪽지함관리를(을) 삭제한다.
     * @param noteRecptn 받은쪽지함관리 정보가 담긴 객체
     * @return void
     * @throws Exception
     */
    @Override
	public void deleteNoteRecptn(NoteRecptn noteRecptn) throws Exception {

        //보낸쪽지함 건수를 조회함
        int nNoteTrnsmitCnt = dao.selectNoteTrnsmitRelationCnt(noteRecptn);

        //받은쪽지함 건수를 조회함
        int nNoteRecptnCnt = dao.selectNoteRecptnRelationCnt(noteRecptn);

        System.out.println("nNoteTrnsmitCnt>"+nNoteTrnsmitCnt);
        System.out.println("nNoteRecptnCnt>"+nNoteRecptnCnt);
        if(nNoteTrnsmitCnt == 1 && nNoteRecptnCnt==1){
        	//받은쪽지/쪽지관리 삭제 처리
        	//dao.deleteNoteRecptnRelation(noteRecptn);
        	//받은쪽지함삭제
        	dao.deleteNoteRecptn(noteRecptn);
        	//보낸쪽지함삭제
        	dao.deleteNoteTrnsmit(noteRecptn);
        	//쪽지관리삭제
        	dao.deleteNoteManage(noteRecptn);
        }else{
        	//받은쪽지 삭제
        	dao.deleteNoteRecptn(noteRecptn);
        }
    }

}
