/**
 * 개요
 * - 자료이용현황 통계에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 자료이용현황 통계정보의 자료이용현황ID,게시판ID,게시글ID,자료명,자료이용횟수) 항목을 관리한다.
 * @author lee.m.j
 * @version 1.0
 * @created 09-8-2009 오후 2:09:15
 */

package egovframework.com.sts.dst.service;

import egovframework.com.cmm.ComDefaultVO;

public class DtaUseStats extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 자료이용현황ID
	 */
    private String dtaUseStatsId;
	/**
	 * 게시판ID
	 */
    private String bbsId;
	/**
	 * 게시판명
	 */
    private String bbsNm;    
	/**
	 * 게시글ID
	 */
    private String nttId;
	/**
	 * 게시글 제목
	 */
    private String nttSj;
	/**
	 * 첨부파일ID
	 */
    private String atchFileId;
	/**
	 * 파일연번
	 */
    private String fileSn;
	/**
	 * 다운횟수
	 */
    private String downCnt;    
	/**
	 * 파일명
	 */
    private String fileNm;
	/**
	 * 사용자ID
	 */
    private String userId;
	/**
	 * 사용자명
	 */
    private String userNm;    
	/**
	 * 등록일자
	 */
    private String regdate;
	/**
	 * 그래프용 등록일자
	 */
	private String grpRegDate;
	/**
	 * 그래프용 등록일자 카운트
	 */
	private String grpCnt;

	/**
	 * @return the dtaUseStatsId
	 */
	public String getDtaUseStatsId() {
		return dtaUseStatsId;
	}
	/**
	 * @param dtaUseStatsId the dtaUseStatsId to set
	 */
	public void setDtaUseStatsId(String dtaUseStatsId) {
		this.dtaUseStatsId = dtaUseStatsId;
	}
	/**
	 * @return the bbsId
	 */
	public String getBbsId() {
		return bbsId;
	}
	/**
	 * @param bbsId the bbsId to set
	 */
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	/**
	 * @return the bbsNm
	 */
	public String getBbsNm() {
		return bbsNm;
	}
	/**
	 * @param bbsNm the bbsNm to set
	 */
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
	/**
	 * @return the nttId
	 */
	public String getNttId() {
		return nttId;
	}
	/**
	 * @param nttId the nttId to set
	 */
	public void setNttId(String nttId) {
		this.nttId = nttId;
	}
	/**
	 * @return the nttSj
	 */
	public String getNttSj() {
		return nttSj;
	}
	/**
	 * @param nttSj the nttSj to set
	 */
	public void setNttSj(String nttSj) {
		this.nttSj = nttSj;
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
	 * @return the fileSn
	 */
	public String getFileSn() {
		return fileSn;
	}
	/**
	 * @param fileSn the fileSn to set
	 */
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	/**
	 * @return the downCnt
	 */
	public String getDownCnt() {
		return downCnt;
	}
	/**
	 * @param downCnt the downCnt to set
	 */
	public void setDownCnt(String downCnt) {
		this.downCnt = downCnt;
	}
	/**
	 * @return the fileNm
	 */
	public String getFileNm() {
		return fileNm;
	}
	/**
	 * @param fileNm the fileNm to set
	 */
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the regdate
	 */
	public String getRegdate() {
		return regdate;
	}
	/**
	 * @param regdate the regdate to set
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	/**
	 * @return the grpRegDate
	 */
	public String getGrpRegDate() {
		return grpRegDate;
	}
	/**
	 * @param grpRegDate the grpRegDate to set
	 */
	public void setGrpRegDate(String grpRegDate) {
		this.grpRegDate = grpRegDate;
	}
	/**
	 * @return the grpCnt
	 */
	public String getGrpCnt() {
		return grpCnt;
	}
	/**
	 * @param grpCnt the grpCnt to set
	 */
	public void setGrpCnt(String grpCnt) {
		this.grpCnt = grpCnt;
	}   
}
