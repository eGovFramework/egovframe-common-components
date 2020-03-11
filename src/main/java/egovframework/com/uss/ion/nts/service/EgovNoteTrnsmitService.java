package egovframework.com.uss.ion.nts.service;

import java.util.List;
import java.util.Map;
/**
 * 보낸쪽지함관리를 처리하는 Service Class 구현
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
public interface EgovNoteTrnsmitService {

    /**
	 * 보낸쪽지함관리 목록을 조회한다.
	 * @param noteTrnsmit -조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectNoteTrnsmitList(NoteTrnsmit noteTrnsmit) throws Exception;

    /**
     * 보낸쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO -조회할 정보가 담긴 객체
     * @return int
     * @throws Exception
     */
    public int selectNoteTrnsmitListCnt(NoteTrnsmit noteTrnsmit) throws Exception;

     /**
	 * 보낸쪽지함관리를(을) 상세조회 한다.
	 * @param noteTrnsmit -조회할 정보가 담긴 객체
	 * @return Map
	 * @throws Exception
	 */
	public Map<?, ?> selectNoteTrnsmitDetail(NoteTrnsmit noteTrnsmit) throws Exception;

	/**
	 * 보낸쪽지함관리를(을) 삭제한다.
	 * @param noteTrnsmit -보낸쪽지함관리 정보 담김 객체
	 * @throws Exception
	 */
	void  deleteNoteTrnsmit(NoteTrnsmit noteTrnsmit) throws Exception;

	/**
	 * 받은쪽지함관리를(을) 삭제한다.
	 * @param noteTrnsmit -보낸쪽지함관리 정보 담김 객체
	 * @throws Exception
	 */
	void  deleteNoteRecptn(NoteTrnsmit noteTrnsmit) throws Exception;

    /**
	 * 수신자목록을 조회한다.
	 * @param noteTrnsmit -조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectNoteTrnsmitCnfirm(NoteTrnsmit noteTrnsmit) throws Exception;

}
