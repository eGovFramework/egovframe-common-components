package egovframework.com.cop.ems.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 발송메일에 첨부되는 파일 VO 클래스
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
 *
 *  </pre>
 */
@Getter
@Setter
public class AtchmnFileVO {

	/** 첨부파일ID */
	private String atchFileId;
	/** 파일연번 */
	private String fileSn;
	/** 원파일명 */
	private String orignlFileNm;
	/** 저장파일명 */
	private String streFileNm;
	/** 파일저장경로 */
	private String fileStreCours;
	/** 파일확장자 */
	private String fileExtsn;
	/** 파일크기 */
	private int fileMg;
}
