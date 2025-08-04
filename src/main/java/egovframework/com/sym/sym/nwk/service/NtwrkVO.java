package egovframework.com.sym.sym.nwk.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 개요
 * - 네트워크에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 네트워크의 목록 항목, 조회조건 등을 관리한다.
 * </pre>
 * 
 * @author lee.m.j
 * @since 2010.08.19
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.19  lee.m.j       최초 생성
 *   2025.07.22  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidArrayLoops(배열의 값을 루프문을 이용하여 복사하는 것 보다, System.arraycopy() 메소드를 이용하여 복사하는 것이 효율적이며 수행 속도가 빠름)
 *
 *      </pre>
 */
public class NtwrkVO extends Ntwrk {

	private static final long serialVersionUID = 1L;

	/**
	 * 관리항목
	 */
	private String strManageIem;
	/**
	 * 사용자명
	 */
	private String strUserNm;
	/**
	 * 네트워크목록
	 */
	private List<NtwrkVO> ntwrkList;
	/**
	 * 삭제여부
	 */
	private String delYn[];

	/**
	 * @return the strManageIem
	 */

	public String getStrManageIem() {
		return strManageIem;
	}

	/**
	 * @param strManageIem the strManageIem to set
	 */
	public void setStrManageIem(String strManageIem) {
		this.strManageIem = strManageIem;
	}

	/**
	 * @return the strUserNm
	 */
	public String getStrUserNm() {
		return strUserNm;
	}

	/**
	 * @param strUserNm the strUserNm to set
	 */
	public void setStrUserNm(String strUserNm) {
		this.strUserNm = strUserNm;
	}

	/**
	 * @return the ntwrkList
	 */
	public List<NtwrkVO> getNtwrkList() {
		return ntwrkList;
	}

	/**
	 * @param ntwrkList the ntwrkList to set
	 */
	public void setNtwrkList(List<NtwrkVO> ntwrkList) {
		this.ntwrkList = Collections.unmodifiableList(ntwrkList);
	}

	/**
	 * @return the delYn
	 */
	public String[] getDelYn() {
		if (delYn == null) {
			return null;
		}
		return Arrays.copyOf(delYn, delYn.length);  // System.arraycopy 대신 사용
	}
	
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String[] delYn) {
		if (delYn == null) {
			this.delYn = null;
		} else {
			this.delYn = Arrays.copyOf(delYn, delYn.length);
		}
	}

}