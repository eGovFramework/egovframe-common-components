package egovframework.com.sym.sym.srv.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.sym.srv.service.Server;
import egovframework.com.sym.sym.srv.service.ServerEqpmn;
import egovframework.com.sym.sym.srv.service.ServerEqpmnRelate;
import egovframework.com.sym.sym.srv.service.ServerEqpmnRelateVO;
import egovframework.com.sym.sym.srv.service.ServerEqpmnVO;
import egovframework.com.sym.sym.srv.service.ServerVO;

/**
 * 개요
 * - 서버정보에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 서버정보에 대한 등록, 수정, 삭제, 조회 등의 기능을 제공한다.
 * - 서버정보의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:54
 */
@Repository("serverDAO")
public class ServerDAO extends EgovComAbstractDAO {

	/**
	 * 서버장비를 관리하기 위해 등록된 서버장비목록을 조회한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return List - 서버장비 목록
	 * 
	 * @param serverEqpmnVO
	 */
	public List<ServerEqpmnVO> selectServerEqpmnList(ServerEqpmnVO serverEqpmnVO) {
		return selectList("serverDAO.selectServerEqpmnList", serverEqpmnVO);
	}

	/**
	 * 서버장비목록 총 갯수를 조회한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return int - 서버장비 카운트 수
	 */
	public int selectServerEqpmnListTotCnt(ServerEqpmnVO serverEqpmnVO) throws Exception{
		return (Integer)selectOne("serverDAO.selectServerEqpmnListTotCnt", serverEqpmnVO);
	}

	/**
	 * 등록된 서버장비의 상세정보를 조회한다.
	 * @param serverEqpmnVO - 서버장비 Vo
	 * @return serverEqpmnVO - 서버장비 Vo
	 */
	public ServerEqpmnVO selectServerEqpmn(ServerEqpmnVO serverEqpmnVO) throws Exception{
		return (ServerEqpmnVO) selectOne("serverDAO.selectServerEqpmn", serverEqpmnVO);
	}

	/**
	 * 서버장비정보를 신규로 등록한다.
	 * @param serverEqpmn - 서버장비 model
	 */
	public void insertServerEqpmn(ServerEqpmn serverEqpmn) throws Exception {
		insert("serverDAO.insertServerEqpmn", serverEqpmn);
	}

	/**
	 * 기 등록된 서버장비정보를 수정한다.
	 * @param serverEqpmn - 서버장비 model
	 */
	public void updateServerEqpmn(ServerEqpmn serverEqpmn) throws Exception {
		update("serverDAO.updateServerEqpmn", serverEqpmn);
	}

	/**
	 * 기 등록된 서버장비정보를 삭제한다.
	 * @param serverEqpmn - 서버장비 model
	 */
	public void deleteServerEqpmn(ServerEqpmn serverEqpmn) throws Exception {
		delete("serverDAO.deleteServerEqpmn", serverEqpmn);
	}

	/**
	 * 서버정보를 관리하기 위해 등록된 서버목록을 조회한다.
	 * @param serverVO - 서버 Vo
	 * @return List - 서버 목록
	 */
	public List<ServerVO> selectServerList(ServerVO serverVO) throws Exception {
		return selectList("serverDAO.selectServerList", serverVO);
	}

	/**
	 * @param serverVO - 서버 Vo
	 * @return int - 서버 카운트 수
	 * @exception Exception
	 */
	public int selectServerListTotCnt(ServerVO serverVO) throws Exception {
		return (Integer)selectOne("serverDAO.selectServerListTotCnt", serverVO);
	}

	/**
	 * 등록된 서버의 상세정보를 조회한다.
	 * @param serverVO - 서버 Vo
	 * @return serverVO - 서버 Vo
	 */
	public ServerVO selectServer(ServerVO serverVO) throws Exception {
		return (ServerVO) selectOne("serverDAO.selectServer", serverVO);
	}

	/**
	 * 서버에 등록된 서버장비목록을 조회한다.
	 * @param serverVO - 서버 Vo
	 * @return List - 서버장비 목록
	 */
	public List<ServerEqpmnVO> selectServerEqpmnRelateDetail(ServerVO serverVO) throws Exception {
		return selectList("serverDAO.selectServerEqpmnRelateDetail", serverVO);
	}
	
	/**
	 * 서버에 등록된 서버장비목록의 카운트를 조회한다.
	 * @param serverVO - 서버 Vo
	 * @return int - 서버에 등록된 서버장비 카운트 수
	 */
	public int selectServerEqpmnRelateDetailTotCnt(ServerVO serverVO) throws Exception {
		return (Integer)selectOne("serverDAO.selectServerEqpmnRelateDetailTotCnt", serverVO);
	}	
	
	/**
	 * 서버정보를 신규로 등록한다.
	 * @param server - 서버 model
	 */
	public void insertServer(Server server) throws Exception {
		insert("serverDAO.insertServer", server);
	}

	/**
	 * 기 등록된 서버정보를 수정한다.
	 * @param server - 서버 model
	 */
	public void updateServer(Server server) throws Exception {
		update("serverDAO.updateServer", server);
	}

	/**
	 * 기 등록된 서버정보를 삭제한다.
	 * @param server - 서버 model
	 */
	public void deleteServer(Server server) throws Exception {
		delete("serverDAO.deleteServer", server);
	}

	/**
	 * 서버장비관계정보를 관리하기 위해 대상 서버장비목록을 조회한다.
	 * @param serverEqpmnRelateVO - 서버장비관계 Vo
	 * @return List - 서버장비 목록
	 */
	public List<ServerEqpmnRelateVO> selectServerEqpmnRelateList(ServerEqpmnRelateVO serverEqpmnRelateVO) throws Exception {
		return selectList("serverDAO.selectServerEqpmnRelateList", serverEqpmnRelateVO);
	}

	/**
	 * 서버장비관계 대상 목록 총 갯수를 조회한다.
	 * @param serverEqpmnRelateVO - 서버장비관계 Vo
	 * @return int - 서버장비관계 카운트 수
	 */
	public int selectServerEqpmnRelateListTotCnt(ServerEqpmnRelateVO serverEqpmnRelateVO) throws Exception {
		return (Integer)selectOne("serverDAO.selectServerEqpmnRelateListTotCnt", serverEqpmnRelateVO);
	}

	/**
	 * 서버장비관계정보를 신규로 등록한다.
	 * @param serverEqpmnRelate - 서버장비관계 model
	 */
	public void insertServerEqpmnRelate(ServerEqpmnRelate serverEqpmnRelate) throws Exception {
		insert("serverDAO.insertServerEqpmnRelate", serverEqpmnRelate);
	}

	/**
	 * 기 등록된 서버장비관계정보를 삭제한다.
	 * @param serverEqpmnRelate - 서버장비관계 model
	 */
	public void deleteServerEqpmnRelate(ServerEqpmnRelate serverEqpmnRelate) throws Exception {
		delete("serverDAO.deleteServerEqpmnRelate", serverEqpmnRelate);
	}

}