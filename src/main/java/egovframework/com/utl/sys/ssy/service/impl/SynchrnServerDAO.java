package egovframework.com.utl.sys.ssy.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.utl.sys.ssy.service.SynchrnServer;

/**
 * 개요
 * - 동기화대상 서버에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 동기화대상 서버에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 동기화대상 서버의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 28-6-2010 오전 10:44:57
 */
@Repository("synchrnServerDAO")
public class SynchrnServerDAO extends EgovComAbstractDAO {

	/**
	 * 동기화대상 서버를 관리하기 위해 등록된 동기화대상 서버목록을 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return List - 동기화대상 서버 목록
	 */
	public List<SynchrnServer> selectSynchrnServerList(SynchrnServer synchrnServer) {
		return selectList("synchrnServerDAO.selectSynchrnServerList", synchrnServer);
	}

	/**
	 * 동기화대상 서버목록 총 개수를 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return int - 동기화대상 서버 카운트 수
	 */
	public int selectSynchrnServerListTotCnt(SynchrnServer synchrnServer) {
		return (Integer)selectOne("synchrnServerDAO.selectSynchrnServerListTotCnt", synchrnServer);
	}

	/**
	 * 등록된 동기화대상 서버의 상세정보를 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return SynchrnServer - 동기화대상 서버
	 */
	public SynchrnServer selectSynchrnServer(SynchrnServer synchrnServer) {
		return (SynchrnServer) selectOne("synchrnServerDAO.selectSynchrnServer", synchrnServer);
	}

	/**
	 * 동기화대상 서버정보를 신규로 등록한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void insertSynchrnServer(SynchrnServer synchrnServer) {
		insert("synchrnServerDAO.insertSynchrnServer", synchrnServer);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 수정한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void updateSynchrnServer(SynchrnServer synchrnServer) {
		update("synchrnServerDAO.updateSynchrnServer", synchrnServer);
	}

	/**
	 * 기 등록된 동기화대상 서버정보를 삭제한다.
	 * @param synchrnServer - 동기화대상 서버 model
	 */
	public void deleteSynchrnServer(SynchrnServer synchrnServer) {
		delete("synchrnServerDAO.deleteSynchrnServer", synchrnServer);
	}

	/**
	 * 업로드 파일을 동기화대상 서버들을 대상으로 동기화 처리를 한다.
	 * @param synchrnServer - 동기화대상 서버
	 */
	public void processSynchrn(SynchrnServer synchrnServer) {
		update("synchrnServerDAO.processSynchrn", synchrnServer);
	}

	/**
	 * 동기화 처리를 하기 위해 동기화대상 서버목록을 조회한다.
	 * @param synchrnServer - 동기화대상 서버
	 * @return List - 동기화대상 서버 목록
	 */
	public List<SynchrnServer> processSynchrnServerList(SynchrnServer synchrnServer) {
		return selectList("synchrnServerDAO.processSynchrnServerList", synchrnServer);
	}
}