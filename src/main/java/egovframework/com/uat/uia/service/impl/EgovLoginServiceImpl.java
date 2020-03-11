package egovframework.com.uat.uia.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.utl.fcc.service.EgovNumberUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성
 *  2011.08.26  서준식          EsntlId를 이용한 로그인 추가
 *  2014.12.08	이기하			암호화방식 변경(EgovFileScrty.encryptPassword)
 *  2017.07.21  장동한 			로그인인증제한 작업
 *  </pre>
 */
@Service("loginService")
public class EgovLoginServiceImpl extends EgovAbstractServiceImpl implements EgovLoginService {

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;

    /** EgovSndngMailRegistService */
	@Resource(name = "sndngMailRegistService")
    private EgovSndngMailRegistService sndngMailRegistService;
	
	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/**
     * 2011.08.26
	 * EsntlId를 이용한 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    @Override
	public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception {

    	LoginVO loginVO = loginDAO.actionLoginByEsntlId(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }


    /**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    @Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
    	vo.setPassword(enpassword);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	LoginVO loginVO = loginDAO.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

    /**
	 * 인증서 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	@Override
	public LoginVO actionCrtfctLogin(LoginVO vo) throws Exception {

    	// 1. DN값으로 ID, PW를 조회한다.
    	LoginVO loginVO = loginDAO.actionCrtfctLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

    /**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    @Override
	public LoginVO searchId(LoginVO vo) throws Exception {

    	// 1. 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
    	LoginVO loginVO = loginDAO.searchId(vo);

    	// 2. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

    /**
	 * 비밀번호를 찾는다.
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
    @Override
	public boolean searchPassword(LoginVO vo) throws Exception {

    	boolean result = true;

    	// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
    	LoginVO loginVO = loginDAO.searchPassword(vo);
    	if (loginVO == null || loginVO.getPassword() == null || "".equals(loginVO.getPassword())) {
    		return false;
    	}

    	// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫+영+영=8자리)
    	String newpassword = "";
    	for (int i = 1; i <= 8; i++) {
    		// 영자
    		if (i % 3 != 0) {
    			newpassword += EgovStringUtil.getRandomStr('a', 'z');
    		// 숫자
    		} else {
    			newpassword += EgovNumberUtil.getRandomNum(0, 9);
    		}
    	}

    	// 3. 임시 비밀번호를 암호화하여 DB에 저장한다.
    	LoginVO pwVO = new LoginVO();
		String enpassword = EgovFileScrty.encryptPassword(newpassword, vo.getId());
    	pwVO.setId(vo.getId());
    	pwVO.setPassword(enpassword);
    	pwVO.setUserSe(vo.getUserSe());
    	loginDAO.updatePassword(pwVO);

    	// 4. 임시 비밀번호를 이메일 발송한다.(메일연동솔루션 활용)
    	SndngMailVO sndngMailVO = new SndngMailVO();
    	sndngMailVO.setDsptchPerson("webmaster");
    	sndngMailVO.setRecptnPerson(vo.getEmail());
    	sndngMailVO.setSj("[MOIS] 임시 비밀번호를 발송했습니다.");
    	sndngMailVO.setEmailCn("고객님의 임시 비밀번호는 " + newpassword + " 입니다.");
    	sndngMailVO.setAtchFileId("");

    	result = sndngMailRegistService.insertSndngMail(sndngMailVO);

    	return result;
    }
    
    
    /**
	 * 로그인인증제한을 조회한다.
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
    public Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception{
    	return loginDAO.selectLoginIncorrect(vo);
    }
    
    /**
	 * 로그인인증제한을 처리한다.
	 * @param vo LoginVO
	 * @return boolean
	 * @exception Exception
	 */
    public String processLoginIncorrect(LoginVO vo, Map<?,?> mapLockUserInfo) throws Exception{    	
    	String sRtnCode = "C";
    	//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), EgovStringUtil.isNullToString(vo.getId()));
    	Map<String,String> mapParam = new HashMap<String,String>();
    	mapParam.put("USER_SE", vo.getUserSe());
		mapParam.put("id", EgovStringUtil.isNullToString(vo.getId()));//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	//잠김시 
		if("Y".equals(((String)mapLockUserInfo.get("lockAt")))){
			sRtnCode = "L";
		//패드워드 인증시 
		}else if( ((String)mapLockUserInfo.get("userPw")).equals(enpassword) ){
    		//LOCK 해제
    		mapParam.put("updateAt", "E");
    		loginDAO.updateLoginIncorrect(mapParam);
    		sRtnCode = "E";
        //패드워드 비인증시 
    	}else if(!"Y".equals(((String)mapLockUserInfo.get("lockAt")))){
    		//LOCK 설정
    		if( Integer.parseInt(String.valueOf(mapLockUserInfo.get("lockCnt")))+1 >= egovLoginConfig.getLockCount() ){    			
	    		mapParam.put("updateAt", "L");
	    		loginDAO.updateLoginIncorrect(mapParam);
	    		sRtnCode = "L";
	    	//LOCK 증가
    		}else{
	    		mapParam.put("updateAt", "C");
	    		loginDAO.updateLoginIncorrect(mapParam);
	    		sRtnCode = "C";
    		}
    	}
    	return sRtnCode;
    }
}
