package egovframework.com.sec.rnc.mip.mva.sp.comm.service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.SvcVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoSvcVO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service
 * @FileName    : SvcService.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 서비스 Service
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public interface SvcService {

	/**
	 * 서비스 조회
	 * 
	 * @MethodName : getSvc
	 * @param svcCode
	 * @return 서비스정보
	 * @throws SpException
	 */
	public SvcVO getSvc(String svcCode) throws SpException;

	/**
	 * 거래 & 서비스정보 조회
	 * 
	 * @MethodName : getTrxInfoSvc
	 * @param trxcode 거래코드
	 * @return 거래 & 서비스정보
	 * @throws SpException
	 */
	public TrxInfoSvcVO getTrxInfoSvc(String trxcode) throws SpException;

}
