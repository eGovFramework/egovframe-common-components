package egovframework.com.uss.ion.vct.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

import lombok.Getter;
import lombok.Setter;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 휴가관리에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 휴가관리의 신청자ID,휴가구분,시작일자,종료일자,신청일자,휴가사유,발생연도,결재자ID,승인여부,결재일시,반려사유,최초등록자ID,최초등록시점,최종수정자ID,최종수정시점 항목을 관리한다.
 * - 휴가관리의 목록 항목을 관리한다.
 * @author 이용
 * @version 1.0
 * @created 06-15-2010 오후 2:08:56
 */
@Getter
@Setter
public class VcatnManageVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	*  신청자ID
	*/
	private String applcntId;

	/**
	*  휴가구분
	*/
	@EgovNullCheck
	private String vcatnSe;

	/**
	*  시작일자
	*/
	@EgovNullCheck
	@Size(max = 8)
	private String bgnde;

	/**
	*  종료일자
	*/
	@EgovNullCheck
	@Size(max = 8)
	private String endde;

	/**
	*  신청일자
	*/
	private String reqstDe;

	/**
	*  휴가사유
	*/
	@EgovNullCheck
	@Size(max = 200)
	private String vcatnResn;

	/**
	*  발생연도
	*/
	private String occrrncYear;

	/**
	*  정오구분
	*/
	private String noonSe;

	/**
	*  결재자ID
	*/
	@NotBlank(message = "{comUssIonVct.vcatnManage.validate.sanctnerId.required}")
	private String sanctnerId;

	/**
	*  승인여부
	*/
	private String confmAt;

	/**
	*  결재일시
	*/
	private String sanctnDt;

	/**
	*  반려사유
	*/
	private String returnResn;

	/**
	*  약식결재ID
	*/
	private String infrmlSanctnId;

	/**
	*  최초등록자ID
	*/
	private String frstRegisterId;

	/**
	*  최초등록시점
	*/
	private String frstRegisterPnttm;

	/**
	*  최종수정자ID
	*/
	private String lastUpdusrId;

	/**
	*  최종수정시점
	*/
	private String lastUpdusrPnttm;

	/**
	*  sanctnDtNm (승인권자 표시용)
	*/
	private String sanctnDtNm;

	/**
	*  orgnztNm
	*/
	private String orgnztNm;

	/**
	 * 휴가 목록
	 */
	List<VcatnManageVO> vcatnManageList;

	/**
	*  신청자명
	*/
	private String applcntNm;

	/**
	*  승인자명
	*/
	private String sanctnerNm;

	/**
	*  휴가구분명
	*/
	private String vcatnSeNm;

	/**
	*  사용자ID
	*/
	private String usid;

	/**
	*  발생연차개수
	*/
	private double occrncYrycCo = 0.0;

	/**
	*  사용연차개수
	*/
	private double useYrycCo = 0.0;

	/**
	*  잔여연차개수
	*/
	private double remndrYrycCo = 0.0;

	/**
	*  승인자 소속명
	*/
	private String sanctnerOrgnztNm;

	/**
	*  검색 연도
	*/
	private String searchYear;

	/**
	*  검색 월
	*/
	private String searchMonth;

	/**
	*  검색 성명
	*/
	private String searchNm;

	/**
	*  검색 진행구분
	*/
	private String searchConfmAt;

	/**
	*  sTempBgnde
	*/
	private String tempBgnde;

	/**
	*  sTempEndde
	*/
	private String tempEndde;

	/**
	*  tempUsNm
	*/
	private String tempUsNm;

	/**
	*  tempOrgnztNm
	*/
	private String tempOrgnztNm;

	/**
	*  신청자ID
	*/
	private String applcntIdKey;

	/**
	*  휴가구분
	*/
	private String vcatnSeKey;

	/**
	*  시작일자
	*/
	private String bgndeKey;

	/**
	*  종료일자
	*/
	private String enddeKey;

}
