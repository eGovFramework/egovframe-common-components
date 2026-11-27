package egovframework.com.uss.olh.hpc.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 도움말을 처리하는 DefaultVO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2016.08.02	김연호		표준프레임워크3.6 개선
 *   2025.05.25  장동한          Lombok @Getter/@Setter 적용
 *
 * </pre>
 */
@Getter
@Setter
public class HpcmDefaultVO implements Serializable {

	private static final long serialVersionUID = 4448507252972240186L;

	/** 검색조건 */
    private String searchCnd = "";

    /** 검색Keyword */
    private String searchWrd = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지개수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
