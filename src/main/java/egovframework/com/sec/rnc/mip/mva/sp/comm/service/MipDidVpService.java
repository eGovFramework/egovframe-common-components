package egovframework.com.sec.rnc.mip.mva.sp.comm.service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoSvcVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.VP;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service
 * @FileName    : MipDidVpService.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : VP 검증 Service
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public interface MipDidVpService {

	/**
	 * 초기 설정
	 */
	public void apiInit() throws Exception;
	
	/**
	 * DID Assertion 생성
	 * 
	 * @MethodName : makeDIDAssertion
	 * @param nonce Nonce
	 * @return DID Assertion
	 * @throws SpException
	 */
	public String makeDIDAssertion(String nonce) throws SpException;

	/**
	 * Profile 요청
	 * 
	 * @MethodName : getProfile
	 * @param trxInfoSvc 거래 & 서비스정보
	 * @return Base64로 인코딩된 Profile
	 * @throws SpException
	 */
	public String getProfile(TrxInfoSvcVO trxInfoSvc) throws SpException;

	/**
	 * VP 검증
	 * 
	 * @MethodName : verifyVP
	 * @param trxcode 거래코드
	 * @param vp VP 정보
	 * @return 검증 결과
	 * @throws SpException
	 */
	public Boolean verifyVP(String trxcode, VP vp) throws SpException;

}
