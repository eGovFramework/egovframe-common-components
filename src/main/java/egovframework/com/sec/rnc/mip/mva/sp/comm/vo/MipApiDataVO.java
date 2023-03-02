package egovframework.com.sec.rnc.mip.mva.sp.comm.vo;

import java.io.Serializable;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.vo
 * @FileName    : MipApiDataVO.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : 검증 API 데이터 VO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
public class MipApiDataVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 결과 */
	private Boolean result;
	/** 데이터 */
	private String data;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
