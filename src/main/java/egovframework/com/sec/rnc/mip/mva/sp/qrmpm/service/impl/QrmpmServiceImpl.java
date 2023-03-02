package egovframework.com.sec.rnc.mip.mva.sp.qrmpm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.MipErrorEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.ModeEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.TrxInfoService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.util.Base64Util;
import egovframework.com.sec.rnc.mip.mva.sp.comm.util.Generator;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.M200VO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.T510VO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoVO;
import egovframework.com.sec.rnc.mip.mva.sp.config.ConfigBean;
import egovframework.com.sec.rnc.mip.mva.sp.qrmpm.service.QrmpmService;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.qrmpm.service.impl
 * @FileName    : QrmpmServiceImpl.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : QR-MPM 인터페이스 검증 처리 ServiceImpl
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
@Service
public class QrmpmServiceImpl implements QrmpmService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QrmpmServiceImpl.class);

	/** 커스텀 프로퍼티 */
	private final ConfigBean configBean;
	/** 거래정보 Service */
	private final TrxInfoService trxInfoService;

	/**
	 * 생성자
	 * 
	 * @param configBean 커스텀 프로퍼티
	 * @param trxInfoService 거래정보 Service
	 */
	public QrmpmServiceImpl(ConfigBean configBean, TrxInfoService trxInfoService) {
		this.configBean = configBean;
		this.trxInfoService = trxInfoService;
	}

	/**
	 * QR-MPM 시작
	 * 
	 * @MethodName : start
	 * @param t510 QR-MPM 정보
	 * @return QR-MPM 정보 + Base64로 인코딩된 M200 메시지
	 * @throws SpException
	 */
	@Override
	public T510VO start(T510VO t510) throws SpException {
		LOGGER.debug("t510 : {}", ConfigBean.gson.toJson(t510));

		String mode = t510.getMode();
		String svcCode = t510.getSvcCode();
		String branchName = t510.getBranchName();
		String deviceId = t510.getDeviceId();
		
		if (ObjectUtils.isEmpty(mode))
			throw new SpException(MipErrorEnum.SP_MISSING_MANDATORY_ITEM, null, "t510.mode");
		if (ObjectUtils.isEmpty(svcCode))
			throw new SpException(MipErrorEnum.SP_MISSING_MANDATORY_ITEM, null, "t510.svcCode");
		if (ObjectUtils.isEmpty(branchName))
			throw new SpException(MipErrorEnum.SP_MISSING_MANDATORY_ITEM, null, "t510.branchName");
		if (ObjectUtils.isEmpty(deviceId))
			throw new SpException(MipErrorEnum.SP_MISSING_MANDATORY_ITEM, null, "t510.deviceId");

		TrxInfoVO trxInfo = new TrxInfoVO();

		trxInfo.setMode(mode);
		trxInfo.setSvcCode(svcCode);
		trxInfo.setBranchName(branchName);
		trxInfo.setDeviceId(deviceId);

		M200VO m200 = null;

		if (ModeEnum.DIRECT.getVal().equals(mode)) {
			m200 = this.directStart(trxInfo);
		} else {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, null, "unsupported mode");
		}

		String m200Str = ConfigBean.gson.toJson(m200);

		LOGGER.debug("m200Str : {}", m200Str);

		String m200Base64 = Base64Util.encode(m200Str);

		t510.setM200Base64(m200Base64);

		return t510;
	}

	/**
	 * QR-MPM 시작(Direct 모드)
	 * 
	 * @MethodName : directStart
	 * @param trxInfo
	 * @return
	 * @throws SpException
	 */
	private M200VO directStart(TrxInfoVO trxInfo) throws SpException {
		String spServer = configBean.getSpServer();

		String trxcode = Generator.genTrxcode();
		String mode = trxInfo.getMode();

		if (trxcode == null) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, null, "거래코드 생성 실패");
		}

		trxInfo.setTrxcode(trxcode);

		trxInfoService.registTrxInfo(trxInfo);

		M200VO m200 = new M200VO();

		m200.setType(ConfigBean.TYPE);
		m200.setVersion(ConfigBean.VERSION);
		m200.setCmd(ConfigBean.M200);
		m200.setTrxcode(trxcode);
		m200.setMode(mode);
		m200.setHost(spServer);

		return m200;
	}

}
