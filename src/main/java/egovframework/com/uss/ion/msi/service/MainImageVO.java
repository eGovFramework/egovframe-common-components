package egovframework.com.uss.ion.msi.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 개요
 * - 메인화면이미지에 대한 Vo 클래스를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지의 목록 항목을 관리한다.
 * </pre>
 * 
 * @author 이문준
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
 *   2025.08.07  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-MethodReturnsInternalArray(Private 배열에 Public 데이터 할당)
 *   2025.08.07  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-ArrayIsStoredDirectly(Public 메소드부터 반환된 Private 배열)
 *
 *      </pre>
 */
public class MainImageVO extends MainImage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 메인화면이미지 목록
	 */
	private List<MainImageVO> mainImageList;

	/**
	 * 삭제대상 목록
	 */
	@Getter
	@Setter
	private String[] delYn;

	/**
	 * @return the mainImageList
	 */
	public List<MainImageVO> getMainImageList() {
		return mainImageList;
	}

	/**
	 * @param mainImageList the mainImageList to set
	 */
	public void setMainImageList(List<MainImageVO> mainImageList) {
		this.mainImageList = mainImageList;
	}

}
