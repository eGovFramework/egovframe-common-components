package egovframework.com.uss.olh.wor.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name  : WordDicaryDefaultVO.java
 * @Description : WordDicaryDefaultVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01     박정규          최초 생성
 * @ 2025.05.25     장동한          Lombok @Getter/@Setter 적용
 *
 *  @author 공통서비스 개발팀  박정규
 *  @since 2009.02.01
 *  @version 1.0
 *  @see
 *
 */
@Getter
@Setter
public class WordDicaryDefaultVO implements Serializable {

	private static final long serialVersionUID = 3772228415902548017L;

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
