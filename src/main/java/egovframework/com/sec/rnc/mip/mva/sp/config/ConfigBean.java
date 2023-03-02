package egovframework.com.sec.rnc.mip.mva.sp.config;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import egovframework.com.sec.rnc.mip.mva.sp.comm.service.MipProperties;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.config
 * @FileName    : ConfigBean.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 스프링 부트 커스텀 프로퍼티 설정 Class
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
@Component
//@ConfigurationProperties("app")
public class ConfigBean {

	public static final String TYPE = "mip";
	public static final String VERSION = "1.0.0";

	public static final String M120 = "120";
	public static final String M150 = "150";
	public static final String M200 = "200";
	public static final String M310 = "310";
	public static final String M320 = "320";
	public static final String M400 = "400";
	public static final String M900 = "900";

	public static final String START_QRCPM = "start_qrcpm";
	public static final String START_NONCPM = "start_noncpm";
	public static final String WAIT_JOIN = "wait_join";
	public static final String JOIN = "join";
	public static final String WAIT_VERIFY = "wait_verify";
	public static final String VERIFY_HOLDER = "verify_holder";
	public static final String VERIFY_VERIFIER = "verify_verifier";
	public static final String ACK = "ack";
	public static final String WAIT_PROFILE = "wait_profile";
	public static final String PROFILE = "profile";
	public static final String VP = "vp";
	public static final String FINISH = "finish";
	public static final String ERROR = "error";

	public static Gson gson = new Gson();

	private String svcFilePath;

	private String blockchainServerDomain = MipProperties.getProperty("app.blockchain-server-domain");
	private String spServer = MipProperties.getProperty("app.sp-server");

	private String keymanagerPath = MipProperties.getProperty("app.keymanager-path");
	private String keymanagerPassword = MipProperties.getProperty("app.keymanager-password");
	private String spKeyId = MipProperties.getProperty("app.sp-key-id");
	private String spRsaKeyId = MipProperties.getProperty("app.sp-rsa-key-id");
	private String spAccount = MipProperties.getProperty("app.sp-account");
	private String spDidPath = MipProperties.getProperty("app.sp-did-path");
	private Boolean includeProfile = Boolean.parseBoolean(MipProperties.getProperty("app.include-profile"));

	private Boolean spApiUse;
	private String spApiJoin;
	private String spApiVerify;
	private String spApiProfile;
	private String spApiVp;
	private String spApiError;

	public String getSvcFilePath() {
		return svcFilePath;
	}

	public void setSvcFilePath(String svcFilePath) {
		this.svcFilePath = svcFilePath;
	}

	public String getBlockchainServerDomain() {
		return blockchainServerDomain;
	}

	public void setBlockchainServerDomain(String blockchainServerDomain) {
		this.blockchainServerDomain = blockchainServerDomain;
	}

	public String getSpServer() {
		return spServer;
	}

	public void setSpServer(String spServer) {
		this.spServer = spServer;
	}

	public String getKeymanagerPath() {
		return keymanagerPath;
	}

	public void setKeymanagerPath(String keymanagerPath) {
		this.keymanagerPath = keymanagerPath;
	}

	public String getKeymanagerPassword() {
		return keymanagerPassword;
	}

	public void setKeymanagerPassword(String keymanagerPassword) {
		this.keymanagerPassword = keymanagerPassword;
	}

	public String getSpKeyId() {
		return spKeyId;
	}

	public void setSpKeyId(String spKeyId) {
		this.spKeyId = spKeyId;
	}

	public String getSpRsaKeyId() {
		return spRsaKeyId;
	}

	public void setSpRsaKeyId(String spRsaKeyId) {
		this.spRsaKeyId = spRsaKeyId;
	}

	public String getSpAccount() {
		return spAccount;
	}

	public void setSpAccount(String spAccount) {
		this.spAccount = spAccount;
	}

	public String getSpDidPath() {
		return spDidPath;
	}

	public void setSpDidPath(String spDidPath) {
		this.spDidPath = spDidPath;
	}

	public Boolean getIncludeProfile() {
		return includeProfile;
	}

	public void setIncludeProfile(Boolean includeProfile) {
		this.includeProfile = includeProfile;
	}

	public Boolean getSpApiUse() {
		return spApiUse;
	}

	public void setSpApiUse(Boolean spApiUse) {
		this.spApiUse = spApiUse;
	}

	public String getSpApiJoin() {
		return spApiJoin;
	}

	public void setSpApiJoin(String spApiJoin) {
		this.spApiJoin = spApiJoin;
	}

	public String getSpApiVerify() {
		return spApiVerify;
	}

	public void setSpApiVerify(String spApiVerify) {
		this.spApiVerify = spApiVerify;
	}

	public String getSpApiProfile() {
		return spApiProfile;
	}

	public void setSpApiProfile(String spApiProfile) {
		this.spApiProfile = spApiProfile;
	}

	public String getSpApiVp() {
		return spApiVp;
	}

	public void setSpApiVp(String spApiVp) {
		this.spApiVp = spApiVp;
	}

	public String getSpApiError() {
		return spApiError;
	}

	public void setSpApiError(String spApiError) {
		this.spApiError = spApiError;
	}

}
