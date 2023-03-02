package egovframework.com.sec.rnc.mip.mva.sp.comm.vo;

import java.io.Serializable;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.vo
 * @FileName    : SvcVO.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 검증 서비스 VO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class SvcVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 서비스코드 */
	private String svcCode;
	/** 제공유형 */
	private Integer presentType;
	/** 암호화유형 */
	private Integer encryptType;
	/** 키유형 */
	private Integer keyType;
	/** 인증유형 */
	private String authType;
	/** 서비스명 */
	private String serviceName;
	/** SP이름 */
	private String spName;
	/** 값 자체를 제출하는 영지식 증명 항목 리스트. ["zkpaddr","zkpsex","zkpasort"] 과 같이 JSON 배열로 정의해야 함 - zkpaddr: 주소(동 까지) - zkpsex: 성별 - zkpasort: 면허종별 */
	private String attrList;
	/** 검증을 위한 조건을 제시하여 조건에 맞음을 검증하는 영지식 증명 항목 리스트 [{"zkpbirth":{"type":"LE","value":"19"}}] 과 같이 JSON 배열로 정의해야 함 현재는 zkpbirth(생년월일) 밖에 없음 */
	private String predList;
	/** 콜백URL */
	private String callBackUrl;
	/** 등록일시 */
	private String regDt;
	/** 수정일시 */
	private String udtDt;

	public String getSvcCode() {
		return svcCode;
	}

	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	public Integer getPresentType() {
		return presentType;
	}

	public void setPresentType(Integer presentType) {
		this.presentType = presentType;
	}

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

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getAttrList() {
		return attrList;
	}

	public void setAttrList(String attrList) {
		this.attrList = attrList;
	}

	public String getPredList() {
		return predList;
	}

	public void setPredList(String predList) {
		this.predList = predList;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUdtDt() {
		return udtDt;
	}

	public void setUdtDt(String udtDt) {
		this.udtDt = udtDt;
	}

}
