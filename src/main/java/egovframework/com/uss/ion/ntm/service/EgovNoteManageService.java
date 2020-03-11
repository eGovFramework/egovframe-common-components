package egovframework.com.uss.ion.ntm.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;

import org.springframework.web.bind.annotation.RequestParam;
/**
 * 쪽지 관리(보내기)를 처리하는 Service Class 구현
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
public interface EgovNoteManageService {

    /**
     * 쪽지관리 정보를 조회한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담김 객체
     * @throws Exception
     */
    public Map<?, ?> selectNoteManage(NoteManageVO noteManage) throws Exception;

     /**
	 * 쪽지 관리(보내기)를(을) 등록한다.
	 * @param noteManage  쪽지 관리(보내기) 정보 담김 객체
	 * @param commandMap -Request 변수
	 * @throws Exception
	 */
	void  insertNoteManage(NoteManageVO noteManage, @RequestParam Map<?, ?> commandMap) throws Exception;

    /**
	 * 수신자/참조자선택팝업 목록을 조회한다.
	 * @param searchVO -조회할 정보가 담긴 VO
	 * @return List -회원정보 리스트
	 * @throws Exception
	 */
	public List<?> selectNoteEmpListPopup(ComDefaultVO searchVO) throws Exception;

    /**
	 *  수신자/참조자선택팝업 갯수를 조회한다.
	 * @param searchVO -조회할 정보가 담긴 VO
	 * @return int -조회된 데이터 건수
	 * @throws Exception
	 */
	public int selectNoteEmpListPopupCnt(ComDefaultVO searchVO) throws Exception;

}
