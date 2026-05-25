package egovframework.com.uss.ion.noi.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Size;

/**
 * 정보알림이 서비스를 위한 VO 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.08
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.6.8  한성곤          최초 생성
 *   2011.10.07 이기하         보안취약점 수정(private 배열 처리)
 *
 * </pre>
 */
@Getter
@Setter
public class NotificationVO extends ComDefaultVO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** 알림 번호 */
    private String ntfcNo = "";

    /** 알림 제목 */
    @EgovNullCheck
    @Size(max = 30)
    private String ntfcSj = "";

    /** 알림 내용 */
    @EgovNullCheck
    @Size(max = 50)
    private String ntfcCn = "";

    /** 알림 시간 */
    @EgovNullCheck
    private String ntfcDate = "";

    /** 알림 시간 */
    private String ntfcTime = "";

    /** 사전 알림 간격 (최소 1개 이상 선택) - 방어적 복사를 위해 직접 구현 */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Size(min = 1, message = "{ussIonNoi.notificationVO.bhNtfcIntrvlRequired}")
    private String[] bhNtfcIntrvl = new String[0];

    /** 사전 알림 간격 문자열 */
    private String bhNtfcIntrvlString = "";

    /** 최초등록자 아이디 */
    private String frstRegisterId = "";

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 최초등록시점 */
    private String frstRegisterPnttm = "";

    /** 최종수정자 아이디 */
    private String lastUpdusrId = "";

    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";

    /** 유일 아이디 */
    private String uniqId = "";

    /** 알림 시간(시) */
    @EgovNullCheck
    private String ntfcHH = "";

    /** 알림 시간(분) */
    @EgovNullCheck
    private String ntfcMM = "";

    /** 검색조건 */
    private String searchCnd = "";

    /** 검색단어 */
    private String searchWrd = "";

    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";

    /** rowNo */
    private int rowNo = 0;

    /** 정보알림이 표시를 위한 시작일 및 시작시간 */
    private String startDateTime = "";

    /** 정보알림이 표시를 위한 종료일 및 종료시간 */
    private String endDateTime = "";

    public String[] getBhNtfcIntrvl() {
        if (this.bhNtfcIntrvl == null) {
            return null;
        }
        String[] ret = new String[bhNtfcIntrvl.length];
        for (int i = 0; i < bhNtfcIntrvl.length; i++) {
            ret[i] = this.bhNtfcIntrvl[i];
        }
        return ret;
    }

    public void setBhNtfcIntrvl(String[] bhNtfcIntrvl) {
        if (bhNtfcIntrvl == null) {
            this.bhNtfcIntrvl = new String[0];
            return;
        }
        this.bhNtfcIntrvl = new String[bhNtfcIntrvl.length];
        for (int i = 0; i < bhNtfcIntrvl.length; i++) {
            this.bhNtfcIntrvl[i] = bhNtfcIntrvl[i];
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
