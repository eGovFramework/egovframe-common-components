package egovframework.com.sym.ccm.icr.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 기관코드 VO 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All rights reserved.
 * </pre>
 */
@Getter
@Setter
public class InsttCodeRecptnVO extends InsttCodeRecptn implements Serializable {

	private static final long serialVersionUID = -1775265891182170426L;

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

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

}
