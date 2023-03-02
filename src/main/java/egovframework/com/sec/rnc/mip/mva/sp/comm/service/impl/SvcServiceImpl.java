package egovframework.com.sec.rnc.mip.mva.sp.comm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.dao.SvcDAO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.MipErrorEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.SvcService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.SvcVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoSvcVO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service.impl
 * @FileName    : SvcServiceImpl.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 서비스 ServiceImpl
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
@Service
public class SvcServiceImpl implements SvcService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SvcServiceImpl.class);

	/** 서비스 DAO */
	private final SvcDAO svcDAO;

	/**
	 * 생성자
	 * 
	 * @param svcDAO 서비스 DAO
	 */
	public SvcServiceImpl(SvcDAO svcDAO) {
		this.svcDAO = svcDAO;
	}

	/**
	 * 서비스 조회
	 * 
	 * @MethodName : getSvc
	 * @param svcCode
	 * @return 서비스정보
	 * @throws SpException
	 */
	@Override
	public SvcVO getSvc(String svcCode) throws SpException {
		LOGGER.debug("svcCode : {}", svcCode);

		SvcVO svc = null;

		try {
			svc = svcDAO.selectSvc(svcCode);

			if (svc == null) {
				throw new SpException(MipErrorEnum.SP_INVALID_DATA, null, "Service Code");
			}
		} catch (SpException e) {
			throw e;
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, null, "Service select");
		}

		return svc;
	}

	/**
	 * 거래 & 서비스정보 조회
	 * 
	 * @MethodName : getTrxInfoSvc
	 * @param trxcode 거래코드
	 * @return 거래 & 서비스정보
	 * @throws SpException
	 */
	@Override
	public TrxInfoSvcVO getTrxInfoSvc(String trxcode) throws SpException {
		LOGGER.debug("trxcode : {}", trxcode);

		TrxInfoSvcVO trxInfoSvc = null;

		try {
			trxInfoSvc = svcDAO.selectTrxInfoSvc(trxcode);
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, trxcode, "TrxInfo select for Service");
		}

		return trxInfoSvc;
	}

}
