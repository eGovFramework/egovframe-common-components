package egovframework.com.uss.ion.isg.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 개요
 * - 인터넷서비스안내에 대한 VO 클래스를 정의한다.
 * 
 * 상세내용
 * - 인터넷서비스안내의 일련번호, 인터넷서비스명, 인터넷서비스설명, 반영여부 항목을 관리한다.
 * </pre>
 * 
 * @author 공통컴포넌트 개발팀 홍길동
 * @since 2010.08.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.03  이문준          최초 생성
 *   2025.08.06  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.08.06  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *
 *      </pre>
 */
public class IntnetSvcGuidanceVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 인터넷서비스ID
	 */	
	private String intnetSvcId;
	/**
	 * 인터넷서비스명
	 */		
	@EgovNullCheck
	@Size(max=20)
	private String intnetSvcNm;
	/**
	 * 인터넷서비스설명
	 */	
	@EgovNullCheck
	@Size(max=200)
	private String intnetSvcDc;
	/**
	 * 반영여부
	 */		
	private String reflctAt;
	/**
	 * 사용자 ID
	 */
	private String userId;
	/**
	 * 등록일자
	 */
	@EgovNullCheck
	private String frstRegisterPnttm;

	/**
	 * 인터넷서비스 목록
	 */
	private List<IntnetSvcGuidanceVO> intnetSvcGuidanceList;

	/**
	 * 삭제여부
	 */
	@Getter
	@Setter
	private String[] delYn;

	/**
	 * @return the intnetSvcId
	 */
	public String getIntnetSvcId() {
		return intnetSvcId;
	}
	/**
	 * @param intnetSvcId the intnetSvcId to set
	 */
	public void setIntnetSvcId(String intnetSvcId) {
		this.intnetSvcId = intnetSvcId;
	}
	/**
	 * @return the intnetSvcNm
	 */
	public String getIntnetSvcNm() {
		return intnetSvcNm;
	}
	/**
	 * @param intnetSvcNm the intnetSvcNm to set
	 */
	public void setIntnetSvcNm(String intnetSvcNm) {
		this.intnetSvcNm = intnetSvcNm;
	}
	/**
	 * @return the intnetSvcDc
	 */
	public String getIntnetSvcDc() {
		return intnetSvcDc;
	}
	/**
	 * @param intnetSvcDc the intnetSvcDc to set
	 */
	public void setIntnetSvcDc(String intnetSvcDc) {
		this.intnetSvcDc = intnetSvcDc;
	}
	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * @return the intnetSvcGuidanceList
	 */
	public List<IntnetSvcGuidanceVO> getIntnetSvcGuidanceList() {
		return intnetSvcGuidanceList;
	}

	/**
	 * @param intnetSvcGuidanceList the intnetSvcGuidanceList to set
	 */
	public void setIntnetSvcGuidanceList(List<IntnetSvcGuidanceVO> intnetSvcGuidanceList) {
		this.intnetSvcGuidanceList = intnetSvcGuidanceList;
	}

}
