package egovframework.com.uss.ion.ism.service.impl;
import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.ism.service.SanctnerVO;

import org.springframework.stereotype.Repository;

/**
 * 개요
 * - 약식결재관리에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 약식결재관리에 대한 등록, 수정, 삭제기능을 제공한다.
 * - 결재자에 대한 목록조회기능을 제공한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 */
@Repository("InfrmlSanctnDAO")
public class InfrmlSanctnDAO extends EgovComAbstractDAO {

	
	/**
	 * 주어진 조건에 맞는 결재자를 불러온다.
	 * @param SanctnerVO
	 * @return List
	 * 
	 * @param sanctnerVO
	 */	
	public List<SanctnerVO> selectSanctnerList(SanctnerVO sanctnerVO) throws Exception{
		return selectList("InfrmlSanctnDAO.selectSanctnerList", sanctnerVO);
	}
	
	/**
	 * 결재자 목록에 대한 전체 건수를 조회한다.
	 * @param SanctnerVO
	 * @return int
	 * 
	 * @param sanctnerVO
	 */
	public int selectSanctnerListCnt(SanctnerVO sanctnerVO) throws Exception{
		return (Integer)selectOne("InfrmlSanctnDAO.selectSanctnerListCnt", sanctnerVO);
	}
	
	/**
	 * 주어진 조건에 맞는 약식결재정보를 불러온다.
	 * @param InfrmlSanctn
	 * @return InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public InfrmlSanctn selectInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		return (InfrmlSanctn)selectOne("InfrmlSanctnDAO.selectInfrmlSanctn", infrmlSanctn);
	}
	

	/**
	 * 약식결재관리 정보를 수정한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public void updateInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		update("InfrmlSanctnDAO.updateInfrmlSanctn", infrmlSanctn);
	}
	
	/**
	 * 약식결재관리 정보를 승인 또는 반려한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public void updateInfrmlSanctnConfm(InfrmlSanctn infrmlSanctn) throws Exception{
		update("InfrmlSanctnDAO.updateInfrmlSanctnConfm", infrmlSanctn);
	}

	/**
	 * 약식결재관리 정보를 등록한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public void insertInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		insert("InfrmlSanctnDAO.insertInfrmlSanctn", infrmlSanctn);
	}

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * @param InfrmlSanctn
	 * 
	 * @param infrmlSanctn
	 */
	public void deleteInfrmlSanctn(InfrmlSanctn infrmlSanctn) throws Exception{
		delete("InfrmlSanctnDAO.deleteInfrmlSanctn", infrmlSanctn);
	}

}