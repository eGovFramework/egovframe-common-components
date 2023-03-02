package egovframework.com.sec.rnc.mip.mva.sp.comm.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.enums
 * @FileName    : ModeEnum.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : 모드 Enum
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
public enum ModeEnum {

	INDRECT("indirect"), //
	DIRECT("direct"), //
	PROXY("proxy"), //
	P2P("p2p"), //
	;

	/** 모드 값 */
	private String val;

	/**
	 * 생성자
	 * 
	 * @param val 모드 값
	 */
	ModeEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	/**
	 * Enum 조회
	 * 
	 * @param val Enum Value
	 * @return ModeEnum
	 */
	public static ModeEnum getEnum(String val) {
		if (ObjectUtils.isEmpty(val)) {
			return null;
		}

		for (ModeEnum item : ModeEnum.values()) {
			if (item.getVal().equals(val.toLowerCase())) {
				return item;
			}
		}

		return null;
	}
	
}
