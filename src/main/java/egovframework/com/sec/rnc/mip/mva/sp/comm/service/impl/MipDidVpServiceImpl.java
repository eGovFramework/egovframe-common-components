package egovframework.com.sec.rnc.mip.mva.sp.comm.service.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import com.google.gson.JsonSyntaxException;
import com.raonsecure.omnione.core.crypto.GDPCryptoHelperClient;
import com.raonsecure.omnione.core.data.did.DIDAssertionType;
import com.raonsecure.omnione.core.data.did.v2.DIDs;
import com.raonsecure.omnione.core.data.iw.profile.CommonProfile;
import com.raonsecure.omnione.core.data.iw.profile.EncryptKeyTypeEnum;
import com.raonsecure.omnione.core.data.iw.profile.Profile;
import com.raonsecure.omnione.core.data.iw.profile.result.VCVerifyProfileResult;
import com.raonsecure.omnione.core.data.rest.ResultJson;
import com.raonsecure.omnione.core.data.rest.ResultProfile;
import com.raonsecure.omnione.core.data.rest.ResultVcStatus;
import com.raonsecure.omnione.core.eoscommander.crypto.digest.Sha256;
import com.raonsecure.omnione.core.eoscommander.crypto.util.HexUtils;
import com.raonsecure.omnione.core.exception.IWException;
import com.raonsecure.omnione.core.key.IWDIDManager;
import com.raonsecure.omnione.core.key.IWKeyManagerInterface;
import com.raonsecure.omnione.core.key.IWKeyManagerInterface.OnUnLockListener;
import com.raonsecure.omnione.core.key.KeyManagerFactory;
import com.raonsecure.omnione.core.key.KeyManagerFactory.KeyManagerType;
import com.raonsecure.omnione.core.key.data.AESType;
import com.raonsecure.omnione.core.key.store.IWDIDFile;
import com.raonsecure.omnione.core.util.http.HttpException;
import com.raonsecure.omnione.sdk_server_core.api.EosDataApi;
import com.raonsecure.omnione.sdk_server_core.blockchain.common.BlockChainException;
import com.raonsecure.omnione.sdk_server_core.blockchain.common.ServerInfo;
import com.raonsecure.omnione.sdk_server_core.blockchain.common.StateDBResultDatas;
import com.raonsecure.omnione.sdk_server_core.blockchain.convert.VcStatusTbl;
import com.raonsecure.omnione.sdk_server_core.blockchain.convert.VcStatusTbl.VCStatusEnum;
import com.raonsecure.omnione.sdk_server_core.data.VcResult;
import com.raonsecure.omnione.sdk_verifier.VerifyApi;
import com.raonsecure.omnione.sdk_verifier.api.data.SpProfileParam;
import com.raonsecure.omnione.sdk_verifier.api.data.VcVerifyProfileParam;

import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.MipErrorEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.TrxStsCodeEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.VcStatusEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.exception.SpException;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.MipDidVpService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.SvcService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.TrxInfoService;
import egovframework.com.sec.rnc.mip.mva.sp.comm.util.Base64Util;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.SvcVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoSvcVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.VP;
import egovframework.com.sec.rnc.mip.mva.sp.config.ConfigBean;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.service.impl
 * @FileName    : MipDidVpServiceImpl.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 5. 31.
 * @Description : VP 검증 ServiceImpl
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 5. 31.    Min Gi Ju        최초생성
 */
@SuppressWarnings("unchecked")
@Service
public class MipDidVpServiceImpl implements MipDidVpService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MipDidVpServiceImpl.class);

	/** 커스텀 프로퍼티 */
	private final ConfigBean configBean;
	/** 서비스 Service */
	private final SvcService svcService;
	/** 거래정보 Service */
	private final TrxInfoService trxInfoService;

	/** 블록체인 서버정보 */
	private ServerInfo blockChainServerInfo;
	/** 키메니져 */
	private IWKeyManagerInterface keyManager;
	/** DID 파일 경로 */
	private String didFilePath;
	/** DID Document */
	private DIDs didDoc;

	/**
	 * 생성자
	 * 
	 * @param configBean 커스텀 프로퍼티
	 * @param svcService 서비스 Service
	 * @param trxInfoService 거래정보 Service
	 */
	public MipDidVpServiceImpl(ConfigBean configBean, SvcService svcService, TrxInfoService trxInfoService) {
		this.configBean = configBean;
		this.svcService = svcService;
		this.trxInfoService = trxInfoService;
	}

	/**
	 * 초기 설정
	 */
	public void apiInit() throws Exception {
		try {
			LOGGER.debug("blockchainServerDomain : {}", configBean.getBlockchainServerDomain());
			LOGGER.debug("keyManagerPath : {}", configBean.getKeymanagerPath());
			LOGGER.debug("spDidPath : {}", configBean.getSpDidPath());

			blockChainServerInfo = new ServerInfo(configBean.getBlockchainServerDomain());

			File keyManagerFile = ResourceUtils.getFile(configBean.getKeymanagerPath());
			String keyManagerPath = keyManagerFile.getAbsolutePath();

			keyManager = KeyManagerFactory.getKeyManager(KeyManagerType.DEFAULT, keyManagerPath, configBean.getKeymanagerPassword().toCharArray());
			
			keyManager.unLock(configBean.getKeymanagerPassword().toCharArray(), new OnUnLockListener() {
				@Override
				public void onSuccess() {
					LOGGER.debug("[OMN] API keyManager onSuccess");
				}

				@Override
				public void onFail(int errCode) {
					LOGGER.error("[OMN] API keyManager onFail", errCode);
				}

				@Override
				public void onCancel() {
					LOGGER.error("[OMN] API keyManager onCancel");
				}
			});

			File didFile = ResourceUtils.getFile(configBean.getSpDidPath());

			didFilePath = didFile.getAbsolutePath();

			IWDIDFile iWDIDFile = new IWDIDFile(didFilePath);

			didDoc = iWDIDFile.getDataFromDIDsV2();
		} catch (Exception e) {
			LOGGER.error("[OMN] API Init Error - Check Log", e);
		}
	}

	/**
	 * DID Assertion 생성
	 * 
	 * @MethodName : makeDIDAssertion
	 * @param nonce Nonce
	 * @return DID Assertion
	 * @throws SpException
	 */
	@Override
	public String makeDIDAssertion(String nonce) throws SpException {
		String didAssertion = "";

		try {
			IWDIDManager didManager = new IWDIDManager(didFilePath);

			didAssertion = didManager.makeDIDAssertion2(DIDAssertionType.DEFAULT, configBean.getSpKeyId(), HexUtils.toBytes(nonce), null, keyManager);
		} catch (IWException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, null, e.getErrorMsg());
		}

		return didAssertion;
	}

	/**
	 * Profile 요청
	 * 
	 * @MethodName : getProfile
	 * @param trxInfoSvc 거래 & 서비스정보
	 * @return Base64로 인코딩된 Profile
	 * @throws SpException
	 */
	@Override
	public String getProfile(TrxInfoSvcVO trxInfoSvc) throws SpException {
		LOGGER.debug("trxInfoSvc : {}", ConfigBean.gson.toJson(trxInfoSvc));

		String trxcode = trxInfoSvc.getTrxcode();
		String trxStsCode = trxInfoSvc.getTrxStsCode();
		String vpVerifyResult = trxInfoSvc.getVpVerifyResult();

		// profile 요청은 시작(0001) 상태에서 해야 한다.
		// 0001 상태가 아닌 것은 이미 profile 요청이 됐거나, verify 된 상태일 수 있다.
		if (!TrxStsCodeEnum.SERCIVE_REQ.getVal().equals(trxStsCode)) {
			throw new SpException(MipErrorEnum.SP_MSG_SEQ_ERROR, trxcode, "stsCode != 0001");
		}

		// 이미 verify 된 trx
		if ("Y".equals(vpVerifyResult)) {
			throw new SpException(MipErrorEnum.SP_MSG_SEQ_ERROR, trxcode, "verifyResult == Y");
		}

		String svcCode = trxInfoSvc.getSvcCode();
		String branchName = trxInfoSvc.getBranchName();

		// 서비스 조회
		SvcVO svc = svcService.getSvc(svcCode);

		LOGGER.debug("svc : {}", ConfigBean.gson.toJson(svc));

		String name = (branchName == null) ? (String) svc.getServiceName() : branchName;

		// Profile 생성 Start
		Profile profile = new Profile();

//		profile.setCallBackUrl(svc.getCallBackUrl());
		profile.setEncryptType(svc.getEncryptType());
		profile.setPresentType(svc.getPresentType());
		profile.setSpName(svc.getSpName() + "-" + name);
		profile.setName(svc.getServiceName());
		profile.setKeyType(svc.getKeyType());

		String authType = svc.getAuthType();

		if (!ObjectUtils.isEmpty(authType)) {
			try {
				profile.setAuthType(ConfigBean.gson.fromJson(authType.toLowerCase(), ArrayList.class));
			} catch (JsonSyntaxException e) {
				throw new SpException(MipErrorEnum.SP_UNEXPECTED_MSG_FORMAT, trxcode, "authType");
			}
		}

		byte[] tempNonce = null;

		try {
			tempNonce = new GDPCryptoHelperClient().generateNonce();
		} catch (IWException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
		}

		String nonce = Sha256.from(tempNonce).toString();

		profile.setNonce(nonce);
		
		SpProfileParam spProfileParam = new SpProfileParam(blockChainServerInfo, keyManager, configBean.getSpKeyId(), svcCode, profile, didDoc.getId(), configBean.getSpAccount());

		if (profile.getKeyType() == EncryptKeyTypeEnum.ALGORITHM_RSA.getVal()) {
			try {
				spProfileParam.setEncPublicKey(keyManager.getPublicKey(configBean.getSpRsaKeyId()));
			} catch (IWException e) {
				throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
			}
		}

		String spProfileJson = null;

		try {
			spProfileJson = VerifyApi.makeSpProfile(spProfileParam);
		} catch (BlockChainException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
		} catch (HttpException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
		}
		// Profile 생성 End

		ResultProfile resultJson = new ResultProfile();

		resultJson.setResult(true);
		resultJson.setProfileJson(spProfileJson);

		CommonProfile commonProfile = ConfigBean.gson.fromJson(Base64Util.decode(resultJson.getProfileBase64()), CommonProfile.class);

		TrxInfoVO trxInfo = new TrxInfoVO();

		trxInfo.setTrxcode(trxcode);
		trxInfo.setTrxStsCode(TrxStsCodeEnum.PROFILE_REQ.getVal());
		trxInfo.setNonce(commonProfile.getProfile().getNonce());

		trxInfoService.modifyTrxInfo(trxInfo);

		return resultJson.getProfileBase64();
	}

	/**
	 * VP 검증
	 * 
	 * @MethodName : verifyVP
	 * @param trxcode 거래코드
	 * @param vp VP 정보
	 * @return 검증 결과
	 * @throws SpException
	 */
	@Override
	public Boolean verifyVP(String trxcode, VP vp) throws SpException {
		LOGGER.debug("trxcode : {}, vp : {}", trxcode, ConfigBean.gson.toJson(vp));

		Boolean result = false;

		TrxInfoVO trxInfo = new TrxInfoVO();

		trxInfo.setTrxcode(trxcode);
		trxInfo.setTrxStsCode(TrxStsCodeEnum.VERIFY_REQ.getVal());

		trxInfoService.modifyTrxInfo(trxInfo);

		Integer encryptType = vp.getEncryptType();
		Integer keyType = vp.getKeyType();
		String type = vp.getType();
		String data = vp.getData();
		List<String> authType = vp.getAuthType();
		String did = vp.getDid();
		String nonce = vp.getNonce();

		// VP 검증 Start
		VCVerifyProfileResult vCVerifyProfileResult = new VCVerifyProfileResult();

		vCVerifyProfileResult.setEncryptType(encryptType);
		vCVerifyProfileResult.setKeyType(keyType);
		vCVerifyProfileResult.setType(type);
		vCVerifyProfileResult.setData(data);

		vCVerifyProfileResult.setAuthType(authType);
		vCVerifyProfileResult.setDid(did);
		vCVerifyProfileResult.setNonce(nonce);

		ResultJson resultJson = this.verify(vCVerifyProfileResult, trxcode);

		if (resultJson == null || !resultJson.isResult()) {
			return result;
		}
		// VP 검증 End

		// VP 상태 확인 Start
		Map<String, Object> vpDataMap = null;

		EncryptKeyTypeEnum keyTypeEnum = EncryptKeyTypeEnum.getEnum(vCVerifyProfileResult.getKeyType());

		if (keyTypeEnum == EncryptKeyTypeEnum.ALGORITHM_RSA) {
			try {
				AESType aESType = vCVerifyProfileResult.getEncryptType() == 1 ? AESType.AES128 : AESType.AES256;

				byte[] vpDataByte = keyManager.rsaDecrypt(configBean.getSpRsaKeyId(), HexUtils.toBytes(vCVerifyProfileResult.getData()), aESType);

				data = new String(vpDataByte, StandardCharsets.UTF_8);

				LOGGER.debug("data : {}", data);
			} catch (IWException e) {
				throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
			}
		}
		
		try {
			vpDataMap = ConfigBean.gson.fromJson(data, HashMap.class);
		} catch (JsonSyntaxException e) {
			throw new SpException(MipErrorEnum.SP_UNEXPECTED_MSG_FORMAT, trxcode, "data");
		}
		
		List<Map<String, Object>> verifiableCredentialList = (List<Map<String, Object>>) vpDataMap.get("verifiableCredential");

		if (ObjectUtils.isEmpty(verifiableCredentialList)) {
			throw new SpException(MipErrorEnum.SP_UNEXPECTED_MSG_FORMAT, trxcode, "vp");
		}

		Map<String, Object> verifiableCredential = verifiableCredentialList.get(0);

		String vcId = (String) verifiableCredential.get("id");

		ResultVcStatus resultVcStatus = null;

		try {
			resultVcStatus = this.getVCStatus(vcId);
		} catch (BlockChainException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
		}

		String vcStatus = resultVcStatus.getVcStatus();

		if (vcStatus.equalsIgnoreCase(VcStatusEnum.ACTIVE.getVal())) { // 활성화 상태
			result = true;
		} else if (vcStatus.equalsIgnoreCase(VcStatusEnum.NEED_RENEW.getVal())) { // 갱신필요 상태
			String memo = resultVcStatus.getMemo();

			if (memo.equals("주소변경")) {
				result = true;
			} else {
				throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, "제출불가 상태 : " + vcStatus + "(" + memo + ")");
			}
		} else {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, "제출불가 상태 : " + vcStatus);
		}
		// VP 상태 확인 End
		
		// Nonce 위변조 확인 Start
		TrxInfoVO curTrxInfo = trxInfoService.getTrxInfo(trxcode);
		
		if (curTrxInfo == null) {
			throw new SpException(MipErrorEnum.SP_TRXCODE_NOT_FOUND, trxcode);
		}
		
		String curVpVerifyResult = curTrxInfo.getVpVerifyResult();
		
		// 이미 verify 된 trx
		if ("Y".equals(curVpVerifyResult)) {
			throw new SpException(MipErrorEnum.SP_MSG_SEQ_ERROR, trxcode, "verifyResult == Y");
		}
		
		String profileNonce = curTrxInfo.getNonce();
		
		// 일반인증시 proof를 사용하고 안심인증시 proofs를 사용
		Map<String, Object> proof = (Map<String, Object>) vpDataMap.get("proof");
		List<Map<String, Object>> proofs = (List<Map<String, Object>>) vpDataMap.get("proofs");
		
		if (ObjectUtils.isEmpty(proof) && ObjectUtils.isEmpty(proofs)) {
			throw new SpException(MipErrorEnum.SP_UNEXPECTED_MSG_FORMAT, trxcode, "proof");
		}
		
		if (!ObjectUtils.isEmpty(proof)) {
			String vpNonce = (String) proof.get("nonce");
			
			LOGGER.debug("profileNonce : {}, vpNonce : {}", profileNonce, vpNonce);
			
			if (!profileNonce.equals(vpNonce)) {
				throw new SpException(MipErrorEnum.SP_MISMATCHING_NONCE, trxcode);
			}
		}
		
		if (!ObjectUtils.isEmpty(proofs)) {
			for (Map<String, Object> obj : proofs) {
				String vpNonce = (String) obj.get("nonce");
				
				LOGGER.debug("profileNonce : {}, vpNonce : {}", profileNonce, vpNonce);
				
				if (!profileNonce.equals(vpNonce)) {
					throw new SpException(MipErrorEnum.SP_MISMATCHING_NONCE, trxcode);
				}
			}
		}
		// Nonce 위변조 확인 End

		String vpVerifyResult = result ? "Y" : "N";

		trxInfo.setTrxcode(trxcode);
		trxInfo.setTrxStsCode(TrxStsCodeEnum.VERIFY_COM.getVal());
		trxInfo.setVpVerifyResult(vpVerifyResult);
		
		// VP 정보 추출 및 저장
		if(result) {
			Map<String, Object> credentialSubject = (Map<String, Object>) verifiableCredentialList.get(8).get("credentialSubject");
			List<Map<String, Object>> privacy = (List<Map<String, Object>>) credentialSubject.get("privacy");
			trxInfo.setVpName((String) privacy.get(0).get("value"));
		}
		// VP 정보 추출 및 저장 End

		trxInfoService.modifyTrxInfo(trxInfo);

		return result;
	}
	
	/**
	 * VC 상태 조회
	 * 
	 * @MethodName : getVCStatus
	 * @param vcId VCID
	 * @return VC 상태
	 * @throws BlockChainException
	 */
	private ResultVcStatus getVCStatus(String vcId) throws BlockChainException {
		ResultVcStatus resultVcStatus = new ResultVcStatus();

		resultVcStatus.setVcId(vcId);

		EosDataApi eosDataApi = new EosDataApi();

		StateDBResultDatas<VcStatusTbl> stateDBResultDatas = eosDataApi.getVCStatus(blockChainServerInfo, vcId);

		if (!stateDBResultDatas.getDataList().isEmpty()) {
			VcStatusTbl vcStatusTbl = stateDBResultDatas.getDataList().get(0);

			resultVcStatus.setVcStatus(vcStatusTbl.getStatusCodeEnum().toString());
			resultVcStatus.setMemo(vcStatusTbl.getMemo().toString());
		} else {
			resultVcStatus.setVcStatus(VCStatusEnum.NOT_EXIST.toString());
		}

		resultVcStatus.setResult(true);

		return resultVcStatus;
	}

	/**
	 * 검증
	 * 
	 * @MethodName : verify
	 * @param vCVerifyProfileResult 검증 파라미터
	 * @param trxcode 거래코드
	 * @return 검증 결과
	 * @throws SpException
	 */
	private ResultJson verify(VCVerifyProfileResult vCVerifyProfileResult, String trxcode) throws SpException {
		VcResult vcResult = null;

		TrxInfoVO trxInfo = trxInfoService.getTrxInfo(trxcode);

		String svcCode = trxInfo.getSvcCode();

		VcVerifyProfileParam vcVerifyProfileParam = new VcVerifyProfileParam(blockChainServerInfo, keyManager, configBean.getSpKeyId(),
				configBean.getSpAccount(), vCVerifyProfileResult, didFilePath);

		vcVerifyProfileParam.setServiceCode(svcCode);
		vcVerifyProfileParam.setCheckVCExpirationDate(true);
		vcVerifyProfileParam.setIssuerProofVerifyCheck(true);

		EncryptKeyTypeEnum keyTypeEnum = EncryptKeyTypeEnum.getEnum(vCVerifyProfileResult.getKeyType());

		if (keyTypeEnum == EncryptKeyTypeEnum.ALGORITHM_RSA)
			vcVerifyProfileParam.setEncryptKeyId(configBean.getSpRsaKeyId());

		try {
			vcResult = VerifyApi.verify2(vcVerifyProfileParam, false);
		} catch (BlockChainException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
		} catch (HttpException e) {
			throw new SpException(MipErrorEnum.UNKNOWN_ERROR, trxcode, e.getErrorMsg());
		}

		ResultJson resultJson = new ResultJson();

		if (vCVerifyProfileResult.getAuthType() != null) {
			String signKeyId = vcResult.getSignKeyId().toString();

			for (String auth : vCVerifyProfileResult.getAuthType()) {
				if (!StringUtils.containsIgnoreCase(signKeyId, auth)) {
					resultJson.setResult(vcResult.getStatus().equals("0"));

					return resultJson;
				}
			}
		}

		resultJson.setResult(vcResult.getStatus().equals("1"));

		return resultJson;
	}

}
