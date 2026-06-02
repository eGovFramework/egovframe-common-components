package egovframework.com.cop.sms.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * 문자메시지 서비스를 위한 VO 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.18  한성곤          최초 생성
 *   2025.05.26  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 제거
 *
 * </pre>
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class SmsVO extends Sms {
    /** 검색조건 */
    private String searchCnd = "";

    /** 검색단어 */
    private String searchWrd = "";

    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";

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

    /** rowNo */
    private int rowNo = 0;

    /**
     * toString 메소드를 대치한다.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
