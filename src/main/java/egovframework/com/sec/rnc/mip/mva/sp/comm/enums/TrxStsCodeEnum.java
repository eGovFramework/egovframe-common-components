package egovframework.com.sec.rnc.mip.mva.sp.comm.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.enums
 * @FileName    : TrxStsCodeEnum.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : 거래상태코드 Enum
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
public enum TrxStsCodeEnum {

	SERCIVE_REQ("0001"), // 서비스 요청
	PROFILE_REQ("0002"), // Profile 요청
	VERIFY_REQ("0003"), // VP 검증 요청
	VERIFY_COM("0004"), // VP 검증 완료
	VERIFY_ERR("0005"), // VP 검증 오류
	;

	/** 거래상태코드 값 */
	private String val;

	/**
	 * 생성자
	 * 
	 * @param val 거래상태코드 값
	 */
	TrxStsCodeEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	/**
	 * Enum 조회
	 * 
	 * @param val Enum Value
	 * @return TrxStsCodeEnum
	 */
	public static TrxStsCodeEnum getEnum(String val) {
		if (ObjectUtils.isEmpty(val)) {
			return null;
		}

		for (TrxStsCodeEnum item : TrxStsCodeEnum.values()) {
			if (item.getVal().equals(val.toLowerCase())) {
				return item;
			}
		}

		return null;
	}
	
}
