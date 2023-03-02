package egovframework.com.sec.rnc.mip.mva.sp.comm.vo;

import java.io.Serializable;

import egovframework.com.sec.rnc.mip.mva.sp.config.ConfigBean;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.vo
 * @FileName    : M200VO.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : VP 요청 메시지 VO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class M200VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 유형 */
	private String type = ConfigBean.TYPE;
	/** 버전 */
	private String version = ConfigBean.VERSION;
	/** Command */
	private String cmd = ConfigBean.M200;
	/** 거래코드 */
	private String trxcode;
	/** 모드 */
	private String mode;
	/** Base64로 인코딩된 Profile */
	private String profile;
	/** BI 이미지 */
	private String image;
	/** CI 포함 여부 */
	private Boolean ci;
	/** 호스트명 */
	private String host;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getTrxcode() {
		return trxcode;
	}

	public void setTrxcode(String trxcode) {
		this.trxcode = trxcode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean isCi() {
		return ci;
	}

	public void setCi(Boolean ci) {
		this.ci = ci;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
