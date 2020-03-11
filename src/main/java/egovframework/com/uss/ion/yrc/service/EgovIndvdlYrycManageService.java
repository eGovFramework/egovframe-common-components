package egovframework.com.uss.ion.yrc.service;

import java.util.List;

/**
 * 개요
 * - 개인연차관리에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 개인연차관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * @author 이기하
 * @version 1.0
 * @created 2014.11.14
 */

public interface EgovIndvdlYrycManageService {

	/**
	 * 개인연차를 조회처리한다.
	 * @param indvdlYrycManage - 개인연차관리 model
	 */
	public List<IndvdlYrycManage> selectIndvdlYrycManageList(IndvdlYrycManage indvdlYrycManage) throws Exception;

	/**
	 * 연차목록 총 갯수를 조회한다.
	 * @param indvdlYrycManage - 개인연차관리 model
	 */
	public int selectIndvdlYrycManageListTotCnt(IndvdlYrycManage indvdlYrycManage) throws Exception;

	/**
	 * 개인연차를 입력처리한다.
	 * @param indvdlYrycManage - 개인연차관리 model
	 */
	public void insertIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception;

	/**
	 * 개인연차를 수정한다.
	 * @param indvdlYrycManage - 개인연차관리 model
	 */
	public void updtIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception;

	/**
	 * 개인연차를 삭제한다.
	 * @param indvdlYrycManage - 개인연차관리 model
	 */
	public void deleteIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception;

}