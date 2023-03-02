package egovframework.com.sec.rnc.mip.mva.sp.comm.service.impl;

import org.springframework.stereotype.Service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.dao.TrxInfoDAO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.MipErrorEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.TrxInfoService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoVO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service.impl
 * @FileName    : TrxInfoServiceImpl.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 거래정보 ServiceImpl
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
@Service
public class TrxInfoServiceImpl implements TrxInfoService {

	/** 거래정보 DAO */
	private final TrxInfoDAO trxInfoDAO;

	/**
	 * 거래정보 DAO
	 * 
	 * @param trxInfoDAO 거래정보 DAO
	 */
	public TrxInfoServiceImpl(TrxInfoDAO trxInfoDAO) {
		this.trxInfoDAO = trxInfoDAO;
	}

	/**
	 * 거래정보 조회
	 * 
	 * @MethodName : getTrxInfo
	 * @param trxcode 거래코드
	 * @return 거래정보
	 * @throws SpException
	 */
	@Override
	public TrxInfoVO getTrxInfo(String trxcode) throws SpException {
		TrxInfoVO trxInfo = null;

		try {
			trxInfo = trxInfoDAO.selectTrxInfo(trxcode);

			if (trxInfo == null) {
				throw new SpException(MipErrorEnum.SP_TRXCODE_NOT_FOUND, trxcode);
			}
		} catch (SpException e) {
			throw e;
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, trxcode, "trxInfo select");
		}

		return trxInfo;
	}

	/**
	 * 거래정보 등록
	 * 
	 * @MethodName : registTrxInfo
	 * @param trxInfo 거래정보
	 * @throws SpException
	 */
	@Override
	public void registTrxInfo(TrxInfoVO trxInfo) throws SpException {
		try {
			trxInfoDAO.insertTrxInfo(trxInfo);
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, trxInfo.getTrxcode(), "trxInfo insert");
		}
	}

	/**
	 * 거래정보 수정
	 * 
	 * @MethodName : modifyTrxInfo
	 * @param trxInfo 거래정보
	 * @throws SpException
	 */
	@Override
	public void modifyTrxInfo(TrxInfoVO trxInfo) throws SpException {
		try {
			trxInfoDAO.updateTrxInfo(trxInfo);
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, trxInfo.getTrxcode(), "trxInfo update");
		}
	}

	/**
	 * 거래정보 삭제
	 * 
	 * @MethodName : removeTrxInfo
	 * @param trxcode 거래코드
	 * @throws SpException
	 */
	@Override
	public void removeTrxInfo(String trxcode) throws SpException {
		try {
			trxInfoDAO.deleteTrxInfo(trxcode);
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, trxcode, "trxInfo delete");
		}
	}
	
	/**
	 * VP 정보 저장
	 * 
	 * @MethodName : insertVp
	 * @param vpName
	 * @throws SpException
	 */
	@Override
	public void insertVp(String vpName) throws SpException {
		try {
			trxInfoDAO.insertVp(vpName);
		} catch (Exception e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, null, "VP insert");
		}
	}

}
