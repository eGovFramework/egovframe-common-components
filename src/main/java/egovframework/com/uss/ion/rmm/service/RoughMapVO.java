package egovframework.com.uss.ion.rmm.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 약도에 대한 Model을 정의한다.
 *
 * 상세내용
 * - 약도 정보를 관리한다.
 *
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일			수정자		수정내용
 *  -----------		------		---------
 *   2014.08.27		옥찬우		최초 생성
 *
 * </pre>
 */

@Getter
@Setter
public class RoughMapVO extends RoughMapDefaultVO {

	private static final long serialVersionUID = -2344076278228282853L;

	/** roughMap ID */
    private String roughMapId;

	/** roughMap 제목 */
    @EgovNullCheck
    private String roughMapSj;

	/** roughMap 상세주소 */
    @EgovNullCheck
    private String roughMapAddress;

    /** 지도 위도 */
    private String la;

    /** 지도 경도 */
    private String lo;

    /** 마커 위도 */
    private String markerLa;

    /** 마커 경도 */
    private String markerLo;

    /** 인포윈도우(말풍선) */
    @EgovNullCheck
    private String infoWindow;

    /** 지도 확대수준 */
    private String zoomLevel;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

}
