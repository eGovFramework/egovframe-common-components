package egovframework.com.uss.ion.ism.service;

import java.util.Map;

/**
 * 개요
 * - 약식결재관리에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 약식결재관리에 대한 등록, 수정, 삭제기능을 제공한다.
 * - 결재자에 대한 목록 조회기능을 제공한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:25
 */
public interface EgovInfrmlSanctnService {

	/**
	 * 결재자 목록을 조회한다.
	 * @param SanctnerVO
	 * @return  Map<String, Object>
	 * 
	 * @param sanctnerVO
	 */
	public Map<String, Object> selectSanctnerList(SanctnerVO sanctnerVO) throws Exception;
	
	/**
	 * 약식결재 정보를 조회한다.
	 * @param InfrmlSanctn
	 * @return  InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public InfrmlSanctn selectInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception;
	
	/**
	 * 약식결재관리 정보를 수정한다.
	 * @param InfrmlSanctn
	 * @return  InfrmlSanctn
	 * 
	 * @param InfrmlSanctn
	 */
	public InfrmlSanctn updateInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception;
	
	/**
	 * 약식결재관리 정보를 승인한다.
	 * @param InfrmlSanctn
	 * @return  InfrmlSanctn
	 * 
	 * @param InfrmlSanctn
	 */
	public InfrmlSanctn updateInfrmlSanctnConfm(InfrmlSanctn infrmlSanctn) throws Exception;
	
	/**
	 * 약식결재관리 정보를 반려한다.
	 * @param InfrmlSanctn
	 * @return  InfrmlSanctn
	 * 
	 * @param InfrmlSanctn
	 */
	public InfrmlSanctn updateInfrmlSanctnReturn(InfrmlSanctn infrmlSanctn) throws Exception;

	/**
	 * 약식결재관리 정보를 등록한다.
	 * @param InfrmlSanctn
	 * @return  InfrmlSanctn
	 * 
	 * @param InfrmlSanctn
	 */
	public InfrmlSanctn insertInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception;

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * @param InfrmlSanctn
	 * @return  InfrmlSanctn
	 * 
	 * @param InfrmlSanctn
	 */
	public void deleteInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception;

}