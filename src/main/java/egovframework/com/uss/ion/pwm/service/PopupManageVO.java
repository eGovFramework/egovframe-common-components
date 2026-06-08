package egovframework.com.uss.ion.pwm.service;

import egovframework.com.cmm.ComDefaultVO;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 개요
 * - 팝업창에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 팝업창의 팝업창아이디, 팝업창 타이틀명, 실파일 URL, 파업창이 화면에 보여지는 위치정보, 팝업창의 사이즈, 게시시작일, 게시종료일,
 * 그만보기 설정 여부, 게시여부 및 목록 항목을 관리한다.
 *
 * @author 이창원
 * @version 1.0
 * @created 05-8-2009 오후 2:21:04
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.05  이창원          최초 생성
 *   2025.05.20  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 제거
 *
 * </pre>
 */
@Getter
@Setter
public class PopupManageVO extends ComDefaultVO {

	private static final long serialVersionUID = -4822974866080666897L;

	/**
	 * 팝업창아이디
	 */
	private String popupId;
	/**
	 * 팝업창 타이틀명
	 */
	@EgovNullCheck
	@Size(max = 1024)
	private String popupTitleNm;
	/**
	 * 실파일 URL
	 */
	@EgovNullCheck
	@Size(max = 1024)
	private String fileUrl;
	/**
	 * 팝업창이 화면에 보여지는 가로 위치정보
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupWlc;

	/**
	 * 팝업창이 화면에 보여지는 세로 위치정보
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupHlc;

	/**
	 * 팝업창의 높이
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupHSize;

	/**
	 * 팝업창의 넚이
	 */
	@EgovNullCheck
	@Size(max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "{validation.pattern.integer}")
	private String popupWSize;

	/**
	 * 게시시작일
	 */
	private String ntceBgnde;
	/**
	 * 게시종료일
	 */
	private String ntceEndde;

	/** 게시시작일(시간) */
	private String ntceBgndeHH;

	/** 게시시작일(분) */
	private String ntceBgndeMM;

	/** 게시종료일(시간) */
	private String ntceEnddeHH;

	/** 게시종료일(분) */
	private String ntceEnddeMM;

	/**
	 * 그만보기 설정 여부
	 */
	@EgovNullCheck
	@Size(max = 1)
	private String stopVewAt;
	/**
	 * 게시여부
	 */
	@EgovNullCheck
	@Size(max = 1)
	private String ntceAt;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록아이디 */
	private String frstRegisterId;

	/** 최종수정일 */
	private String lastUpdusrPnttm;

	/** 최종수정자 아이디 */
	private String lastUpdusrId;

}
