package egovframework.com.uss.ion.ntm.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 쪽지 관리(보내기) Model and VO Class 구현
 * @author 공통서비스 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 * 
 * </pre>
 */
@SuppressWarnings("serial")
public class NoteManageVO extends ComDefaultVO implements Serializable{
    	
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
	private String noteCn;
	
	/** 쪽지 제목 */
	private String noteSj;

	/** 쪽지 발신자 */
	private String trnsmiterId;
	
	/** 쪽지 수신자 목록 */
	private String recptnEmpList;
	
	/** 쪽지 첨부파일 아이디 */
	private String atchFileId;
	
	/** 쪽지 첨부파일 */
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
	 * @return the noteId
	 */
	public String getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return the noteTrnsmitId
	 */
	public String getNoteTrnsmitId() {
		return noteTrnsmitId;
	}

	/**
	 * @param noteTrnsmitId the noteTrnsmitId to set
	 */
	public void setNoteTrnsmitId(String noteTrnsmitId) {
		this.noteTrnsmitId = noteTrnsmitId;
	}

	/**
	 * @return the noteRecptnId
	 */
	public String getNoteRecptnId() {
		return noteRecptnId;
	}

	/**
	 * @param noteRecptnId the noteRecptnId to set
	 */
	public void setNoteRecptnId(String noteRecptnId) {
		this.noteRecptnId = noteRecptnId;
	}

	/**
	 * @return the rcverId
	 */
	public String getRcverId() {
		return rcverId;
	}

	/**
	 * @param rcverId the rcverId to set
	 */
	public void setRcverId(String rcverId) {
		this.rcverId = rcverId;
	}

	/**
	 * @return the openYn
	 */
	public String getOpenYn() {
		return openYn;
	}

	/**
	 * @param openYn the openYn to set
	 */
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}

	/**
	 * @return the recptnSe
	 */
	public String getRecptnSe() {
		return recptnSe;
	}

	/**
	 * @param recptnSe the recptnSe to set
	 */
	public void setRecptnSe(String recptnSe) {
		this.recptnSe = recptnSe;
	}

	/**
	 * @return the noteCn
	 */
	public String getNoteCn() {
		return noteCn;
	}

	/**
	 * @param noteCn the noteCn to set
	 */
	public void setNoteCn(String noteCn) {
		this.noteCn = noteCn;
	}

	/**
	 * @return the noteSj
	 */
	public String getNoteSj() {
		return noteSj;
	}

	/**
	 * @param noteSj the noteSj to set
	 */
	public void setNoteSj(String noteSj) {
		this.noteSj = noteSj;
	}

	/**
	 * @return the trnsmiterId
	 */
	public String getTrnsmiterId() {
		return trnsmiterId;
	}

	/**
	 * @param trnsmiterId the trnsmiterId to set
	 */
	public void setTrnsmiterId(String trnsmiterId) {
		this.trnsmiterId = trnsmiterId;
	}

	/**
	 * @return the recptnEmpList
	 */
	public String getRecptnEmpList() {
		return recptnEmpList;
	}

	/**
	 * @param recptnEmpList the recptnEmpList to set
	 */
	public void setRecptnEmpList(String recptnEmpList) {
		this.recptnEmpList = recptnEmpList;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * @return the atchFile
	 */
	public byte[] getAtchFile() {
		byte[] ret = null;
		
		if (atchFile != null) {
			ret = new byte[atchFile.length];
			
			for (int i = 0; i < atchFile.length; i++) {
				ret[i] = atchFile[i];
			}
		}
		return ret;
	}

	/**
	 * @param atchFile the atchFile to set
	 */
	public void setAtchFile(byte[] atchFile) {
		this.atchFile = new byte[atchFile.length];
		
		for (int i = 0; i <  atchFile.length; ++i) {
			this.atchFile[i] = atchFile[i];
		}
	}

	/**
	 * @return the frstRegisterPnttm
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * @param frstRegisterPnttm the frstRegisterPnttm to set
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * @return the frstRegisterId
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * @param frstRegisterId the frstRegisterId to set
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * @return the lastUpdusrPnttm
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * @param lastUpdusrPnttm the lastUpdusrPnttm to set
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * @return the lastUpdusrId
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * @param lastUpdusrId the lastUpdusrId to set
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

    
}
