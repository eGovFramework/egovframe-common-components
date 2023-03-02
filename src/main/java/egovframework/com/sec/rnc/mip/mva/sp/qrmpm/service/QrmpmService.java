package egovframework.com.sec.rnc.mip.mva.sp.qrmpm.service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.T510VO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.qrmpm.service
 * @FileName    : QrmpmService.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : QR-MPM 인터페이스 검증 처리 Service
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public interface QrmpmService {

	/**
	 * QR-MPM 시작
	 * 
	 * @MethodName : start
	 * @param t510 QR-MPM 정보
	 * @return QR-MPM 정보 + Base64로 인코딩된 M200 메시지
	 * @throws SpException
	 */
	public T510VO start(T510VO t510) throws SpException;

}
