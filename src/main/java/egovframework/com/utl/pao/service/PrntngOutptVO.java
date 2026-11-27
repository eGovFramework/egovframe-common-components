package egovframework.com.utl.pao.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 관인이미지 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.02.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01  이중호          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class PrntngOutptVO implements Serializable {

	private static final long serialVersionUID = 941289557959718464L;

	/*
	 * 이미지정보
	 */
    private byte[] imgInfo;

    /*
     * 이미지타입
     */
	private String imgType;

	/*
	 * 기관코드
	 */
    private String orgCode;

    /*
     * 관인구분
     */
    private String erncslSe;

	/**
	 * imgInfo attribute 를 리턴한다.
	 * @return byte[]
	 */
	public byte[] getImgInfo() {
		byte[] ret = null;

		if (imgInfo != null) {
			ret = new byte[imgInfo.length];

			for (int i = 0; i < imgInfo.length; i++) {
				ret[i] = imgInfo[i];
			}
		}
		return ret;
	}

	/**
	 * imgInfo attribute 값을 설정한다.
	 * @param imgInfo byte[]
	 */
	public void setImgInfo(byte[] imgInfo) {
		this.imgInfo = new byte[imgInfo.length];

		for (int i = 0; i <  imgInfo.length; ++i) {
			this.imgInfo[i] = imgInfo[i];
		}
	}

}
