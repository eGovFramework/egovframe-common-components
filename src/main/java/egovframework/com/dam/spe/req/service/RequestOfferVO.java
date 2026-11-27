package egovframework.com.dam.spe.req.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 지식정보제공/지식정보요청 Model and VO Class 구현
 * @author 공통서비스 장동한
 * @since 2010.08.30
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.30  장동한          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class RequestOfferVO extends RequestOffer {

	/** 명령어 */
    private String cmd = "";

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 페이지개수 */
    private int pageUnit = 10;

	/** 페이지사이즈 */
    private int pageSize = 10;

    /** 현재페이지 */
    private int pageIndex = 1;

    /** firstIndex */
    private int firstIndex = 1;

	/** lastIndex */
    private int lastIndex = 1;

	/** recordCountPerPage */
    private int recordCountPerPage = 10;

}



