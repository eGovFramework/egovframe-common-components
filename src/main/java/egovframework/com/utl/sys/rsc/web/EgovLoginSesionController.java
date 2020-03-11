package egovframework.com.utl.sys.rsc.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.utl.sys.rsc.service.EgovLoginSesionCeckUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 개요
 * - 로그인 세션정보체크 컴포넌트에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인 세션정보체크에 대한 기능을 제공한다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:26
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.28   lee.m.j    최초 생성
 *  2011.8.26	정진오			IncludedInfo annotation 추가
 * </pre>
 */

@Controller
public class EgovLoginSesionController {

	@Resource(name="egovLoginSesionCeckUtil")
	private EgovLoginSesionCeckUtil egovLoginSesionCeckUtil;
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/**
	 * 로그인 세션정보체크 화면 이동
	 * @return String
	 */
	@IncludedInfo(name="로그인세션정보체크", order = 2160 ,gid = 90)
	@RequestMapping(value="/utl/sys/rsc/loginSessionView.do")
	public String checkLoginSessionView() throws Exception {
		return "egovframework/com/utl/sys/rsc/EgovLoginSesionCheck";
	}

	/**
	 * 로그인 후 이동할 처리화면을 세션에 등록한다.
	 * @param url - String
	 * @return String
	 */
	@RequestMapping(value="/utl/sys/rsc/setLoginSession.do")
	public String setLoginSession(@RequestParam("url") String url) throws Exception {
		egovLoginSesionCeckUtil.setLoginSession(url);
		return "forward:/utl/sys/rsc/loginSessionView.do";
	}
	
	/**
	 * 로그인 세션정보체크 
	 * @return String
	 */
	@RequestMapping(value="/utl/sys/rsc/checkLloginSession.do")
	public String checkLoginSession() throws Exception {
		egovLoginSesionCeckUtil.checkLoginSessionView();
		return "egovframework/com/utl/sys/rsc/EgovLoginSesionCheck";
	}


}