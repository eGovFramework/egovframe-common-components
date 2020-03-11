package egovframework.com.sym.bat.service;

import java.io.Serializable;

/**
 * 배치스케줄요일에 대한 model 클래스
 *
 * @author 김진만
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.08.23   김진만     최초 생성
 * </pre>
 */
public class BatchSchdulDfk implements Serializable {

	private static final long serialVersionUID = -4152071306992470303L;

	/**
	 * 배치스케줄ID
	 */
	private String batchSchdulId;

	/**
	 * 실행스케줄요일
	 */
	private String executSchdulDfkSe;

	/**
	 * 실행스케줄요일명
	 */
	private String executSchdulDfkSeNm;


	/**
	 * @return the batchSchdulId
	 */
	public String getBatchSchdulId() {
		return batchSchdulId;
	}
	/**
	 * @return the executSchdulDfkSe
	 */
	public String getExecutSchdulDfkSe() {
		return executSchdulDfkSe;
	}
	/**
	 * @param batchSchdulId the batchSchdulId to set
	 */
	public void setBatchSchdulId(String batchSchdulId) {
		this.batchSchdulId = batchSchdulId;
	}
	/**
	 * @param executSchdulDfkSe the executSchdulDfkSe to set
	 */
	public void setExecutSchdulDfkSe(String executSchdulDfkSe) {
		this.executSchdulDfkSe = executSchdulDfkSe;
	}
	/**
	 * @return the executSchdulDfkSeNm
	 */
	public String getExecutSchdulDfkSeNm() {
		return executSchdulDfkSeNm;
	}
	/**
	 * @param executSchdulDfkSeNm the executSchdulDfkSeNm to set
	 */
	public void setExecutSchdulDfkSeNm(String executSchdulDfkSeNm) {
		this.executSchdulDfkSeNm = executSchdulDfkSeNm;
	}



}