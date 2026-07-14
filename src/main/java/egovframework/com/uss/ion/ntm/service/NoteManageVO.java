package egovframework.com.uss.ion.ntm.service;

import java.util.Arrays;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

import egovframework.com.cmm.ComDefaultVO;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 쪽지 관리(보내기) Model and VO Class 구현
 *
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.16  장동한          최초 생성
 *   2025.08.08  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidArrayLoops(배열의 값을 루프문을 이용하여 복사하는 것 보다, System.arraycopy() 메소드를 이용하여 복사하는 것이 효율적이며 수행 속도가 빠름)
 *   2026.05.26  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 코드 제거 (atchFile 필드는 방어적 복사 유지)
 *
 *      </pre>
 */
@Getter
@Setter
public class NoteManageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 쪽지 ID */
	private String noteId;

	/** 쪽지 송신 ID */
	private String noteTrnsmitId;

	/** 쪽지 수신 ID */
	private String noteRecptnId;

	/** 수신자 ID */
	private String rcverId;

	/** 개봉여부 */
	private String openYn;

	/** 수신구분 */
	private String recptnSe;

	/** 쪽지 내용 */
	@EgovNullCheck
	@Size(max=4000)
	private String noteCn;

	/** 쪽지 제목 */
	@EgovNullCheck
	@Size(max=255)
	private String noteSj;

	/** 쪽지 발신자 */
	private String trnsmiterId;

	/** 쪽지 수신자 목록 */
	@EgovNullCheck
	private String recptnEmpList;

	/** 쪽지 첨부파일 아이디 */
	private String atchFileId;

	/** 쪽지 첨부파일 */
	@Getter(lombok.AccessLevel.NONE)
	@Setter(lombok.AccessLevel.NONE)
	private byte[] atchFile;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록아이디 */
	private String frstRegisterId;

	/** 최종수정일 */
	private String lastUpdusrPnttm;

	/** 최종수정자 아이디 */
	private String lastUpdusrId;

	/**
	 * @return the atchFile
	 */
	public byte[] getAtchFile() {
		return atchFile == null ? new byte[0] : Arrays.copyOf(atchFile, atchFile.length);
	}

	/**
	 * @param atchFile the atchFile to set
	 */
	public void setAtchFile(byte[] atchFile) {
		if (atchFile == null) {
			this.atchFile = new byte[0];
		} else {
			this.atchFile = Arrays.copyOf(atchFile, atchFile.length);
		}
	}

}
