package egovframework.com.sec.rnc.mip.mva.sp.comm.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.vo
 * @FileName    : VP.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : VP 정보 VO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class VP implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 제공유형 */
	private Integer presentType;
	/** 암호화유형 */
	private Integer encryptType;
	/** 키유형 */
	private Integer keyType;
	/** 인증유형 */
	private List<String> authType;
	/** DID */
	private String did;
	/** Nonce */
	private String nonce;
	/** ZKP Nonce */
	private String zkpNonce;
	/** 유형 */
	private String type;
	/** 암호화된 검증 요청 데이터 */
	private String data;

	public Integer getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(Integer encryptType) {
		this.encryptType = encryptType;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getZkpNonce() {
		return zkpNonce;
	}

	public void setZkpNonce(String zkpNonce) {
		this.zkpNonce = zkpNonce;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getPresentType() {
		return presentType;
	}

	public void setPresentType(Integer presentType) {
		this.presentType = presentType;
	}

	public List<String> getAuthType() {
		return authType;
	}

	public void setAuthType(List<String> authType) {
		this.authType = authType;
	}

}
