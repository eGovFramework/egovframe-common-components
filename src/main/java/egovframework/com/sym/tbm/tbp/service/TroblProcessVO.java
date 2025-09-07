package egovframework.com.sym.tbm.tbp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 개요
 * - 장애처리결과정보에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 장애정보의 목록 항목, 조회조건, 삭제대상을 관리한다.
 * </pre>
 * 
 * @author 이문준
 * @version 1.0
 * @since 2010.06.28
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.28  이문준          최초 생성
 *   2025.07.26  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidArrayLoops(배열의 값을 루프문을 이용하여 복사하는 것 보다, System.arraycopy() 메소드를 이용하여 복사하는 것이 효율적이며 수행 속도가 빠름)
 *
 *      </pre>
 */
public class TroblProcessVO extends TroblProcess {

	private static final long serialVersionUID = 1L;
	/**
	 * 장애정보 목록
	 */
	private List<TroblProcessVO> troblProcessList;
	/**
	 * 장애명 조회조건
	 */
	private String strTroblNm;
	/**
	 * 장애종류 조회조건
	 */
	private String strTroblKnd;
	/**
	 * 처리상태 조회조건
	 */
	private String strProcessSttus;

	/**
	 * 삭제대상 목록
	 */
	private String delYn[];

	/**
	 * @return the troblProcessList
	 */
	public List<TroblProcessVO> getTroblProcessList() {
		return troblProcessList;
	}

	/**
	 * @param troblProcessList the troblProcessList to set
	 */
	public void setTroblProcessList(List<TroblProcessVO> troblProcessList) {
		this.troblProcessList = Collections.unmodifiableList(troblProcessList);
	}

	/**
	 * @return the strTroblNm
	 */
	public String getStrTroblNm() {
		return strTroblNm;
	}

	/**
	 * @param strTroblNm the strTroblNm to set
	 */
	public void setStrTroblNm(String strTroblNm) {
		this.strTroblNm = strTroblNm;
	}

	/**
	 * @return the strTroblKnd
	 */
	public String getStrTroblKnd() {
		return strTroblKnd;
	}

	/**
	 * @param strTroblKnd the strTroblKnd to set
	 */
	public void setStrTroblKnd(String strTroblKnd) {
		this.strTroblKnd = strTroblKnd;
	}

	/**
	 * @return the strProcessSttus
	 */
	public String getStrProcessSttus() {
		return strProcessSttus;
	}

	/**
	 * @param strProcessSttus the strProcessSttus to set
	 */
	public void setStrProcessSttus(String strProcessSttus) {
		this.strProcessSttus = strProcessSttus;
	}

	/**
	 * @return the delYn
	 */
	public String[] getDelYn() {
		return delYn == null ? new String[0] : Arrays.copyOf(delYn, delYn.length);
	}

	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String[] delYn) {
		if (delYn == null) {
			this.delYn = new String[0];
		} else {
			this.delYn = Arrays.copyOf(delYn, delYn.length);
		}
	}

}