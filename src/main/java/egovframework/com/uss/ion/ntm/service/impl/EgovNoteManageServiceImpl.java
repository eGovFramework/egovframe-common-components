package egovframework.com.uss.ion.ntm.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.ion.ntm.service.EgovNoteManageService;
import egovframework.com.uss.ion.ntm.service.NoteManageVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 쪽지 관리(보내기)를 처리하는 ServiceImpl Class 구현
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
@Service("egovNoteManageService")
public class EgovNoteManageServiceImpl extends EgovAbstractServiceImpl
        implements EgovNoteManageService {

    @Resource(name = "noteManageDao")
    private NoteManageDao dao;

    /* 쪽지관리 ID Generator Service */
    @Resource(name = "egovNoteManageIdGnrService")
    private EgovIdGnrService noteIdgenService;

    /* 보낸쪽지함관리 ID Generator Service */
    @Resource(name = "egovNoteTrnsmitIdGnrService")
    private EgovIdGnrService noteTrnsmitIdgenService;

    /* 받은쪽지함관리 ID Generator Service */
    @Resource(name = "egovNoteRecptnIdGnrService")
    private EgovIdGnrService noteRecptnIdgenService;

    /**
     * 쪽지관리 정보를 조회한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담김 객체
     * @throws Exception
     */
    @Override
	public Map<?, ?> selectNoteManage(NoteManageVO noteManage) throws Exception {
    	return dao.selectNoteManage(noteManage);
    }

    /**
     * 쪽지 관리(보내기)를(을) 등록한다.
     * @param noteManage -쪽지 관리(보내기) 정보가 담긴 객체
     * @param commandMap -Request 변수
     * @throws Exception
     */
    @Override
	public void insertNoteManage(NoteManageVO noteManage, @RequestParam Map<?, ?> commandMap)throws Exception{

        /* ****************************************************************
         * 쪽지관리 처리
         **************************************************************** */
    	//쪽지 ID설정
    	noteManage.setNoteId(noteIdgenService.getNextStringId());
    	//쪽지 등록
    	dao.insertNoteManage(noteManage);

        /* ****************************************************************
         * 보낸쪽지 처리
         **************************************************************** */
    	//보낸쪽지함 ID설정
    	noteManage.setNoteTrnsmitId(noteTrnsmitIdgenService.getNextStringId());
    	//발신자 아이디설정
    	noteManage.setTrnsmiterId(noteManage.getFrstRegisterId());

    	//보낸쪽지등록
    	dao.insertNoteTrnsmit(noteManage);

        //수신자 리스트
        String sRecptnEmpList = (String)commandMap.get("recptnEmpList");
        String[] sRecptnEmpListResult = sRecptnEmpList.split(",");

        //수신자구분 리스트
        String sRecptnSeList = (String)commandMap.get("recptnSeList");
        String[] sRecptnSeListResult = sRecptnSeList.split(",");


        /* ****************************************************************
         * 받은쪽지함 처리
         **************************************************************** */
        for(int i=0;i<sRecptnEmpListResult.length;i++){

        	//받은쪽지함 ID설정
        	noteManage.setNoteRecptnId(noteRecptnIdgenService.getNextStringId());
        	//받은쪽지함 수신여부 설정
        	noteManage.setOpenYn("N");
        	//받은쪽지함 수신자 설정
        	noteManage.setRcverId(sRecptnEmpListResult[i]);
        	//받은쪽지함 수신 구분설정
        	noteManage.setRecptnSe(sRecptnSeListResult[i]);
        	//받은쪽지함 등록
        	dao.insertNoteRecptn(noteManage);
        }


    }

    /**
	 * 수신자/참조자선택팝업 목록을 조회한다.
	 * @param searchVO -조회할 정보가 담긴 VO
	 * @return List -회원정보 리스트
	 * @throws Exception
	 */
	@Override
	public List<?> selectNoteEmpListPopup(ComDefaultVO searchVO) throws Exception{
		return dao.selectNoteEmpListPopup(searchVO);
	}

    /**
	 *  수신자/참조자선택팝업 갯수를 조회한다.
	 * @param searchVO -조회할 정보가 담긴 VO
	 * @return int -조회된 데이터 건수
	 * @throws Exception
	 */
	@Override
	public int selectNoteEmpListPopupCnt(ComDefaultVO searchVO) throws Exception{
		return dao.selectNoteEmpListPopupCnt(searchVO);
	}
}
