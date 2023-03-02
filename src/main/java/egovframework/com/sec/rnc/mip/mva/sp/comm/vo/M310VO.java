package egovframework.com.sec.rnc.mip.mva.sp.comm.vo;

import java.io.Serializable;

import egovframework.com.sec.rnc.mip.mva.sp.config.ConfigBean;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.vo
 * @FileName    : M310VO.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : Profile 요청 메시지 VO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class M310VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 유형 */
	private String type = ConfigBean.TYPE;
	/** 버전 */
	private String version = ConfigBean.VERSION;
	/** Command */
	private String cmd = ConfigBean.M310;
	/** Request */
	private String request;
	/** 거래코드 */
	private String trxcode;
	/** Base64로 인코딩된 Profile */
	private String profile;

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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getTrxcode() {
		return trxcode;
	}

	public void setTrxcode(String trxcode) {
		this.trxcode = trxcode;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
