package egovframework.com.sec.rnc.mip.mva.sp.comm.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.enums
 * @FileName    : VcStatusEnum.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : VC 상태 Enum
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
public enum VcStatusEnum {

	ACTIVE("ACTIVE"), //
	PAUSE("PAUSE"), //
	NEED_RENEW("NEED_RENEW"), //
	REMOVE("REMOVE"), //
	NOT_EXIST("NOT_EXIST"), //
	;

	/** VC 상태 값 */
	private String val;

	/**
	 * 생성자
	 * 
	 * @param val VC 상태 값
	 */
	VcStatusEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	/**
	 * Enum 조회
	 * 
	 * @param val Enum Value
	 * @return VcStatusEnum
	 */
	public static VcStatusEnum getEnum(String val) {
		if (ObjectUtils.isEmpty(val)) {
			return null;
		}

		for (VcStatusEnum item : VcStatusEnum.values()) {
			if (item.getVal().equals(val.toLowerCase())) {
				return item;
			}
		}

		return null;
	}

}
