package egovframework.com.utl.pao.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 관인이미지 모델 클래스
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.02.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01  이중호          최초 생성
 *   2025.09.03  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidArrayLoops(배열의 값을 루프문을 이용하여 복사하는 것 보다, System.arraycopy() 메소드를 이용하여 복사하는 것이 효율적이며 수행 속도가 빠름)
 *
 *      </pre>
 */
public class PrntngOutptVO implements Serializable {

	private static final long serialVersionUID = 941289557959718464L;

	/*
	 * 이미지정보
	 */
	@Getter
	@Setter
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
	 * imgType attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getImgType() {
		return imgType;
	}

	/**
	 * imgType attribute 값을 설정한다.
	 * 
	 * @param imgType String
	 */
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	/**
	 * orgCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * orgCode attribute 값을 설정한다.
	 * 
	 * @param orgCode String
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * erncslSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getErncslSe() {
		return erncslSe;
	}

	/**
	 * erncslSe attribute 값을 설정한다.
	 * 
	 * @param erncslSe String
	 */
	public void setErncslSe(String erncslSe) {
		this.erncslSe = erncslSe;
	}

}
