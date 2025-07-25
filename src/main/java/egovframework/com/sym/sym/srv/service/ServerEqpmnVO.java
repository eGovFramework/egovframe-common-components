package egovframework.com.sym.sym.srv.service;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 개요
 * - 서버장비에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 서버장비의 목록 항목 및 조회조건을 관리한다.
 * </pre>
 * 
 * @author 이문준
 * 
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
 *   2020-08-28  신용호          보안약점 조치 (Private 배열에 Public 데이터 할당[CWE-496])
 *   2025.07.24  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *
 *      </pre>
 */
public class ServerEqpmnVO extends ServerEqpmn {

	private static final long serialVersionUID = 1L;

	/**
	 * 서버 장비 목록
	 */
	private List<?> serverEqpmnList;

	/**
	 * 삭제대상 목록
	 */
	@Getter
	@Setter
	private String delYn[];

	/**
	 * 서버장비명 조회조건
	 */
	private String strServerEqpmnNm;

	/**
	 * @return the serverEqpmnList
	 */
	public List<?> getServerEqpmnList() {
		return serverEqpmnList;
	}

	/**
	 * @param serverEqpmnList the serverEqpmnList to set
	 */
	public void setServerEqpmnList(List<?> serverEqpmnList) {
		this.serverEqpmnList = Collections.unmodifiableList(serverEqpmnList);
	}

	/**
	 * @return the strServerEqpmnNm
	 */
	public String getStrServerEqpmnNm() {
		return strServerEqpmnNm;
	}

	/**
	 * @param strServerEqpmnNm the strServerEqpmnNm to set
	 */
	public void setStrServerEqpmnNm(String strServerEqpmnNm) {
		this.strServerEqpmnNm = strServerEqpmnNm;
	}
}