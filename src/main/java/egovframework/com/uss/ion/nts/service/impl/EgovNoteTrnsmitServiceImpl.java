package egovframework.com.uss.ion.nts.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.ion.nts.service.EgovNoteTrnsmitService;
import egovframework.com.uss.ion.nts.service.NoteTrnsmit;
import jakarta.annotation.Resource;
/**
 * 보낸쪽지함관리를 처리하는 ServiceImpl Class 구현
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
@Service("egovNoteTrnsmitService")
public class EgovNoteTrnsmitServiceImpl extends EgovAbstractServiceImpl
        implements EgovNoteTrnsmitService {

    @Resource(name = "noteTrnsmitDao")
    private NoteTrnsmitDao dao;


    /**
     * 보낸쪽지함관리를(을) 목록을 조회 한다.
     * @param noteTrnsmit -조회할 정보가 담긴 객체
     * @return List -조회목록이담긴List
     * @throws Exception
     */
    @Override
	public List<EgovMap> selectNoteTrnsmitList(NoteTrnsmit noteTrnsmit) throws Exception {
    	return dao.selectNoteTrnsmitList(noteTrnsmit);
    }

    /**
     * 보낸쪽지함관리를(을) 목록 전체 건수를(을) 조회한다.
     * @param noteTrnsmit -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    @Override
	public int selectNoteTrnsmitListCnt(NoteTrnsmit noteTrnsmit) throws Exception {
        return dao.selectNoteTrnsmitListCnt(noteTrnsmit);
    }

    /**
     * 보낸쪽지함관리를(을) 상세조회 한다.
     * @param noteTrnsmit -조회할 정보가 담긴 객체
     * @return Map -조회정보가담긴Map
     * @throws Exception
     */
    @Override
	public Map<?, ?> selectNoteTrnsmitDetail(NoteTrnsmit noteTrnsmit) throws Exception {
        return dao.selectNoteTrnsmitDetail(noteTrnsmit);
    }

    /**
     * 보낸쪽지함관리를(을) 삭제한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void deleteNoteTrnsmit(NoteTrnsmit noteTrnsmit) throws Exception {
		// 2026.07.13 KISA 보안취약점 조치
		LoginVO _loginVO = egovAssertLoginUser();

    	LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
    	if (loginVO == null || loginVO.getUniqId() == null) {
    		throw new EgovBizException("인증 정보가 없습니다.");
    	}
    	noteTrnsmit.setTrnsmiterId(loginVO.getUniqId());
    	Map<?, ?> noteTrnsmitMap = dao.selectNoteTrnsmitDetail(noteTrnsmit);
    	if (noteTrnsmitMap == null || noteTrnsmitMap.isEmpty()) {
    		throw new EgovBizException("권한이 없습니다.");
    	}
    	Object trnsmiterId = noteTrnsmitMap.get("trnsmiterId");
    	if (trnsmiterId == null) {
    		trnsmiterId = noteTrnsmitMap.get("TRNSMITER_ID");
    	}
    	// 2026.07.13 KISA 보안취약점 조치
    	if (!loginVO.getUniqId().equals(String.valueOf(trnsmiterId))) {
    		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
    		if (auth == null || !auth.contains("ROLE_ADMIN")) {
    			throw new EgovBizException("권한이 없습니다.");
    		}
    	}

        //보낸쪽지함 건수를 조회함
        int nCnt = dao.selectTrnsmitRelationCnt(noteTrnsmit);

        if(nCnt == 0){
        	//받은쪽지/쪽지관리 삭제 처리
        	dao.deleteNoteTrnsmitRelation(noteTrnsmit);
        	//쪽지정보를 삭제한다.
        	dao.deleteNoteManage(noteTrnsmit);
        }else{
        	dao.deleteNoteTrnsmit(noteTrnsmit);
        }
    }

    /**
     * 보낸쪽지함관리를(을) 삭제한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담긴 객체
     * @throws Exception
     */
    @Override
	public void deleteNoteRecptn(NoteTrnsmit noteTrnsmit) throws Exception {

        dao.deleteNoteRecptn(noteTrnsmit);
    }


    /**
     * 수신자목록을 조회한다.
     * @param noteTrnsmit -보낸쪽지함관리 정보가 담긴 객체
     * @return List -조회목록이담긴List
     * @throws Exception
     */
    @Override
	public List<EgovMap> selectNoteTrnsmitCnfirm(NoteTrnsmit noteTrnsmit) throws Exception {
        return dao.selectNoteTrnsmitCnfirm(noteTrnsmit);
    }

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 로그인 사용자 확인
	 */
	private LoginVO egovAssertLoginUser() {
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO == null || loginVO.getUniqId() == null || "".equals(loginVO.getUniqId())) {
			throw new IllegalStateException("인증 정보가 없습니다.");
		}
		return loginVO;
	}

	/**
	 * 2026.07.13 KISA 보안취약점 조치 - 관리자 또는 소유자
	 */
	private void egovAssertAdminOrOwner(String ownerUniqId) {
		LoginVO loginVO = egovAssertLoginUser();
		if (ownerUniqId != null && ownerUniqId.equals(loginVO.getUniqId())) {
			return;
		}
		java.util.List<String> auth = EgovUserDetailsHelper.getAuthorities();
		if (auth != null && auth.contains("ROLE_ADMIN")) {
			return;
		}
		throw new IllegalStateException("권한이 없습니다.");
	}

}
