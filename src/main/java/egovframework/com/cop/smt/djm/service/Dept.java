package egovframework.com.cop.smt.djm.service;

import java.io.Serializable;

/**
 * 개요
 * - 부서에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 부서업무함ID, 부서업무ID, 부서업무명, 부서업무내용, 업무담당자, 우선순위, 첨부파일ID 의 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:04
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *
 * </pre>
 */

@SuppressWarnings("serial")
public class Dept implements Serializable {
	/** 부서 ID */
	private String orgnztId;
	/** 부서명 */
	private String orgnztNm;
	/** 부서설명 */
	private String orgnztDc;
	
	public String getOrgnztId() {
		return orgnztId;
	}
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}
	public String getOrgnztNm() {
		return orgnztNm;
	}
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}
	public String getOrgnztDc() {
		return orgnztDc;
	}
	public void setOrgnztDc(String orgnztDc) {
		this.orgnztDc = orgnztDc;
	}
}
