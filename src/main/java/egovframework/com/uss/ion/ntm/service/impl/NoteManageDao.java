package egovframework.com.uss.ion.ntm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.ntm.service.NoteManageVO;
/**
 * 쪽지 관리(보내기)를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일          수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한           최초 생성
 *   2017.06.05   최두영          공통컴포넌트 3.7 개발
 *
 * </pre>
 */
@Repository("noteManageDao")
public class NoteManageDao extends EgovComAbstractDAO {

    /**
     * 쪽지관리 정보를 조회한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담김 객체
     */
    public Map<?, ?> selectNoteManage(NoteManageVO noteManage) {
    	return (Map<?, ?>)selectOne("NoteManage.selectNoteManage", noteManage);
    }

    /**
     * 쪽지 관리(보내기)를(을) 등록한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담김 객체
     */
    public void insertNoteManage(NoteManageVO noteManage) {
    	insert("NoteManage.insertNoteManage", noteManage);
    }


    /**
     * 보낸쪽지를 등록한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담김 객체
     */
    public void insertNoteTrnsmit(NoteManageVO noteManage) {
    	insert("NoteManage.insertNoteTrnsmit", noteManage);
    }


    /**
     * 받은쪽지를 등록한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담김 객체
     */
    public void insertNoteRecptn(NoteManageVO noteManage) {
    	insert("NoteManage.insertNoteRecptn", noteManage);
    }

    /**
	 * 수신자/참조자선택팝업 목록을 조회한다.
	 * @param searchVO -조회할 정보가 담긴 VO
	 * @return List -회원정보 리스트
	 */
	public List<EgovMap> selectNoteEmpListPopup(ComDefaultVO searchVO) {
		return selectList("NoteManage.EovNoteEmpListPopup", searchVO);
	}

    /**
	 * 수신자/참조자선택팝업 건수를 조회한다.
	 * @param searchVO -조회할 정보가 담긴 VO
	 * @return int -조회된 데이터 개수
	 */
	public int selectNoteEmpListPopupCnt(ComDefaultVO searchVO) {
		 return (Integer)selectOne("NoteManage.EovNoteEmpListPopupCnt", searchVO);
	}
}
