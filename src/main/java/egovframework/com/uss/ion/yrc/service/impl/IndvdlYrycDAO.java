package egovframework.com.uss.ion.yrc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.yrc.service.IndvdlYrycManage;

/**
 * 개요
 * - 연차관리에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 연차관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * @author 이기하
 * @version 1.0
 * @created 2014.11.14
 */

@Repository("indvdlYrycDAO")
public class IndvdlYrycDAO extends EgovComAbstractDAO {

	/**
	 * 연차를 조회처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public List<IndvdlYrycManage> selectIndvdlYrycManageList(IndvdlYrycManage indvdlYrycManage) throws Exception {
		return selectList("indvdlYrycDAO.selectIndvdlYrycManageList", indvdlYrycManage);
	}

	/**
	 * 연차목록 총 개수를 조회한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public int selectIndvdlYrycManageListTotCnt(IndvdlYrycManage indvdlYrycManage) throws Exception {
		return (Integer)selectOne("indvdlYrycDAO.selectIndvdlYrycManageListTotCnt", indvdlYrycManage);
	}

	/**
	 * 연차를 입력처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public void insertIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		insert("indvdlYrycDAO.insertIndvdlYrycManage", indvdlYrycManage);
	}

	/**
	 * 연차를 수정처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public void updtIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		update("indvdlYrycDAO.updateIndvdlYrycManage", indvdlYrycManage);
	}

	/**
	 * 연차를 삭제처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public void deleteIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		delete("indvdlYrycDAO.deleteIndvdlYrycManage", indvdlYrycManage);
	}

}
