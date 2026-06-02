package egovframework.com.cop.ems.service;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * 발송메일 VO 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *  2011.12.06  이기하          첨부파일경로(fileStreCours), 첨부파일이름(orignlFileNm) 추가
 *
 *  </pre>
 */
@Getter
@Setter
public class SndngMailVO {

	/** 메세지ID */
	private String mssageId;
	/** 발신자 */
	private String dsptchPerson;
	/** 수신자 */
	@EgovNullCheck
	@Email
	private String recptnPerson;
	/** 제목 */
	@EgovNullCheck
	private String sj;
	/** 발송결과코드 */
	private String sndngResultCode;
	/** 메일내용 */
	@EgovNullCheck
	private String emailCn;
	/** 첨부파일ID */
	private String atchFileId;
	/** 첨부파일경로 */
	private String fileStreCours;
	/** 첨부파일이름 */
	private String orignlFileNm;
	/** 발신일자 */
	private String sndngDe;
	/** 첨부파일ID 리스트 */
	private String atchFileIdList;
	/** 발송요청XML내용 */
	private String xmlContent;
	/** 팝업링크여부(Y/N) */
	private String link;
}
