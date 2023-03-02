package egovframework.com.sec.rnc.mip.mva.sp.comm.service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.M310VO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.M400VO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.M900VO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service
 * @FileName    : DirectService.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : Direct 검증 Service
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public interface DirectService {

	/**
	 * Profile 요청
	 * 
	 * @MethodName : getProfile
	 * @param m310 M310 메세지
	 * @return M310 메세지 + Profile
	 * @throws SpException
	 */
	public M310VO getProfile(M310VO m310) throws SpException;

	/**
	 * VP 검증
	 * 
	 * @MethodName : verifyVP
	 * @param m400 M400메세지
	 * @return 검증 결과
	 * @throws SpException
	 */
	public Boolean verifyVP(M400VO m400) throws SpException;

	/**
	 * 오류 전송
	 * 
	 * @MethodName : sendError
	 * @param m900 M900 메세지
	 * @throws SpException
	 */
	public void sendError(M900VO m900) throws SpException;

}
