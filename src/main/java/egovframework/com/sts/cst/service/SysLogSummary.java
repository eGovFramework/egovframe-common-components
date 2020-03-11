package egovframework.com.sts.cst.service;

/**
 * 시스템로그집계정보에 대한 모델 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.04.15
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.04.15  박지욱          최초 생성
 *  2011.07.01  이기하          패키지 분리(sts -> sts.cst)
 *
 *  </pre>
 */
public class SysLogSummary {

	/**
	 * 생성횟수
	 */
	private int creatCo;
	/**
	 * 삭제횟수
	 */
	private int deleteCo;
	/**
	 * 에러횟수
	 */
	private int errorCo;
	/**
	 * 조회횟수
	 */
	private int rdCnt;
	/**
	 * 메소드명
	 */
	private String methodNm;
	/**
	 * 발생일자
	 */
	private String occrrncDe;
	/**
	 * 출력횟수
	 */
	private int outptCo;
	/**
	 * 서비스명
	 */
	private String srvcNm;
	/**
	 * 수정횟수
	 */
	private int updtCo;
	/**
	 * creatCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getCreatCo() {
		return creatCo;
	}
	/**
	 * creatCo attribute 값을 설정한다.
	 * @param creatCo int
	 */
	public void setCreatCo(int creatCo) {
		this.creatCo = creatCo;
	}
	/**
	 * deleteCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getDeleteCo() {
		return deleteCo;
	}
	/**
	 * deleteCo attribute 값을 설정한다.
	 * @param deleteCo int
	 */
	public void setDeleteCo(int deleteCo) {
		this.deleteCo = deleteCo;
	}
	/**
	 * errorCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getErrorCo() {
		return errorCo;
	}
	/**
	 * errorCo attribute 값을 설정한다.
	 * @param errorCo int
	 */
	public void setErrorCo(int errorCo) {
		this.errorCo = errorCo;
	}
	/**
	 * rdCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getRdCnt() {
		return rdCnt;
	}
	/**
	 * rdCnt attribute 값을 설정한다.
	 * @param rdCnt int
	 */
	public void setRdCnt(int rdCnt) {
		this.rdCnt = rdCnt;
	}
	/**
	 * methodNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getMethodNm() {
		return methodNm;
	}
	/**
	 * methodNm attribute 값을 설정한다.
	 * @param methodNm String
	 */
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}
	/**
	 * occrrncDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getOccrrncDe() {
		return occrrncDe;
	}
	/**
	 * occrrncDe attribute 값을 설정한다.
	 * @param occrrncDe String
	 */
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}
	/**
	 * outptCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getOutptCo() {
		return outptCo;
	}
	/**
	 * outptCo attribute 값을 설정한다.
	 * @param outptCo int
	 */
	public void setOutptCo(int outptCo) {
		this.outptCo = outptCo;
	}
	/**
	 * srvcNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getSrvcNm() {
		return srvcNm;
	}
	/**
	 * srvcNm attribute 값을 설정한다.
	 * @param srvcNm String
	 */
	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
	}
	/**
	 * updtCo attribute 를 리턴한다.
	 * @return int
	 */
	public int getUpdtCo() {
		return updtCo;
	}
	/**
	 * updtCo attribute 값을 설정한다.
	 * @param updtCo int
	 */
	public void setUpdtCo(int updtCo) {
		this.updtCo = updtCo;
	}
}
