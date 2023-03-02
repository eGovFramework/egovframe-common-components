package egovframework.com.sec.rnc.mip.mva.sp.comm.enums;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.enums
 * @FileName    : RequestTypeEnum.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : 요청유형 Enum
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
public enum RequestTypeEnum {

	CMD_310_REQ("mip", "1.0.0", "310", "profile"), //
	CMD_400_REQ("mip", "1.0.0", "400", "presentation") //
	;

	/** 유형 */
	private String type;
	/** 버전 */
	private String version;
	/** Command */
	private String cmd;
	/** Request */
	private String request;

	/**
	 * 생성자
	 * 
	 * @param type 유형
	 * @param version 버전
	 * @param cmd Command
	 * @param request Request
	 */
	RequestTypeEnum(String type, String version, String cmd, String request) {
		this.type = type;
		this.version = version;
		this.cmd = cmd;
		this.request = request;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	public String getCmd() {
		return cmd;
	}

	public String getRequest() {
		return request;
	}

	/**
	 * Enum 조회
	 * 
	 * @MethodName : getEnum
	 * @param cmd Command
	 * @return RequestTypeEnum
	 */
	public static RequestTypeEnum getEnum(String cmd) {
		for (RequestTypeEnum item : RequestTypeEnum.values()) {
			if (item.getCmd().equals(cmd)) {
				return item;
			}
		}

		throw new IllegalArgumentException("No enum constant " + RequestTypeEnum.class.getCanonicalName() + ".cmd : " + cmd);
	}

}
