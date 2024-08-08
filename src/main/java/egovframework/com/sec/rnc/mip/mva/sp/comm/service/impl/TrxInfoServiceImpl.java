package egovframework.com.sec.rnc.mip.mva.sp.comm.service.impl;

import org.springframework.stereotype.Service;

import egovframework.com.sec.rnc.mip.mva.sp.comm.dao.TrxInfoDAO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.MipErrorEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.TrxInfoService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoVO;

/**
 * @Project : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service.impl
 * @FileName : TrxInfoServiceImpl.java
 * @Author : Min Gi Ju
 * @Date : 2022. 6. 3.
 * @Description : 거래정보 ServiceImpl
 * 
 *              <pre>
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 *   2024.08.09  이백행          시큐어코딩 Exception 제거
 *              </pre>
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
			int result = trxInfoDAO.insertTrxInfo(trxInfo);

			if (result == 0) {
				throw new SpException(MipErrorEnum.SP_DB_ERROR, trxInfo.getTrxcode(), "trxInfo insert");
			}
		} catch (SpException e) {
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
			int result = trxInfoDAO.updateTrxInfo(trxInfo);

			if (result == 0) {
				throw new SpException(MipErrorEnum.SP_DB_ERROR, trxInfo.getTrxcode(), "trxInfo update");
			}
		} catch (SpException e) {
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
			int result = trxInfoDAO.deleteTrxInfo(trxcode);

			if (result == 0) {
				throw new SpException(MipErrorEnum.SP_DB_ERROR, trxcode, "trxInfo delete");
			}
		} catch (SpException e) {
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
			int result = trxInfoDAO.insertVp(vpName);

			if (result == 0) {
				throw new SpException(MipErrorEnum.SP_DB_ERROR, null, "VP insert");
			}
		} catch (SpException e) {
			throw new SpException(MipErrorEnum.SP_DB_ERROR, null, "VP insert");
		}
	}

}
