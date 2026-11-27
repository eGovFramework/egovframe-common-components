package egovframework.com.sym.sym.bak.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 백업결과관리에 대한 model 클래스
 *
 * @author 김진만
 * @since 2010.06.17
 * @version 1.0
 * @updated 17-6-2010 오전 10:27:13
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.17   김진만     최초 생성
 *  2025.05.21   dasomel    Lombok @Getter/@Setter 적용
 * </pre>
 */
@Getter
@Setter
public class BackupResult extends ComDefaultVO implements Serializable {

	private static final long serialVersionUID = -743292072713546949L;
	/**
	 * 백업결과ID
	 */
	private String backupResultId;
	/**
	 * 백업작업ID
	 */
	private String backupOpertId;
	/**
	 * 백업화일
	 */
	private String backupFile;
	/**
	 * 상태
	 */
	private String sttus;
	/**
	 * 실행시작시각
	 */
	private String executBeginTime;
	/**
	 * 실행종료시각
	 */
	private String executEndTime;
	/**
	 * 최종수정자 아이디
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm;
	/**
	 * 최초등록자 아이디
	 */
	private String frstRegisterId;
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm;

	/**
	 * 에러정보
	 */
	private String errorInfo;

	/**
	 * 백업작업명
	 */
	private String backupOpertNm;
	/**
	 * 상태명
	 */
	private String sttusNm;
	/**
	 * 백업원본디렉토리
	 */
	private String backupOrginlDrctry;
	/**
	 * 백업저장디렉토리
	 */
	private String backupStreDrctry;

}