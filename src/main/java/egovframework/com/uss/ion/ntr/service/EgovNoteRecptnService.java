package egovframework.com.uss.ion.ntr.service;

import java.util.List;
import java.util.Map;
/**
 * 받은쪽지함관리를 처리하는 Service Class 구현
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
public interface EgovNoteRecptnService {

    /**
	 * 받은쪽지함관리 목록을 조회한다.
	 * @param noteRecptn  -조회할 정보가 담긴 객체
	 * @return List -조회목록이담긴List
	 * @throws Exception
	 */
	public List<?> selectNoteRecptnList(NoteRecptn noteRecptn) throws Exception;

    /**
     * 받은쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param noteRecptn  -조회할 정보가 담긴 객체
     * @return int -조회한건수
     * @throws Exception
     */
    public int selectNoteRecptnListCnt(NoteRecptn noteRecptn) throws Exception;

     /**
	 * 받은쪽지함관리를(을) 상세조회 한다.
	 * @param noteRecptn  -받은쪽지함관리 정보 담김 객체
	 * @return Mp -조회정보가담긴Map
	 * @throws Exception
	 */
	public Map<?, ?> selectNoteRecptnDetail(NoteRecptn noteRecptn) throws Exception;

	/**
	 * 받은쪽지함관리를(을) 삭제한다.
	 * @param noteRecptn  -받은쪽지함관리 정보 담김 객체
	 * @throws Exception
	 */
	void  deleteNoteRecptn(NoteRecptn noteRecptn) throws Exception;

}
