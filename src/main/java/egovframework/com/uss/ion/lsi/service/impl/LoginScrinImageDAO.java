/**
 * 개요
 * - 로그인화면이미지에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 2009.08.07 오후 2:08:56
 */

package egovframework.com.uss.ion.lsi.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.lsi.service.LoginScrinImage;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;

@Repository("loginScrinImageDAO")
public class LoginScrinImageDAO extends EgovComAbstractDAO {

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return List - 로그인화면이미지 목록
	 */	
	public List<LoginScrinImageVO> selectLoginScrinImageList(LoginScrinImageVO loginScrinImageVO) throws Exception {
		return selectList("loginScrinImageDAO.selectLoginScrinImageList", loginScrinImageVO);
	}

    /**
	 * 로그인화면이미지목록 총 개수를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectLoginScrinImageListTotCnt(LoginScrinImageVO loginScrinImageVO) throws Exception {
        return (Integer)selectOne("loginScrinImageDAO.selectLoginScrinImageListTotCnt", loginScrinImageVO);
    }

	/**
	 * 등록된 로그인화면이미지의 상세정보를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return LoginScrinImageVO - 로그인화면이미지 VO
	 */
	public LoginScrinImageVO selectLoginScrinImage(LoginScrinImageVO loginScrinImageVO)  throws Exception {
		return (LoginScrinImageVO) selectOne("loginScrinImageDAO.selectLoginScrinImage", loginScrinImageVO);
	}

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void insertLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception {
		insert("loginScrinImageDAO.insertLoginScrinImage", loginScrinImage);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void updateLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception {
		update("loginScrinImageDAO.updateLoginScrinImage", loginScrinImage);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void deleteLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception {
        delete("loginScrinImageDAO.deleteLoginScrinImage",loginScrinImage);
	}

	/**
	 * 기 등록된 로그인화면이미지정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public FileVO selectLoginScrinImageFile(LoginScrinImage loginScrinImage) throws Exception {
		return (FileVO) selectOne("loginScrinImageDAO.selectLoginScrinImageFile", loginScrinImage);
	}

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return LoginScrinImageVO - 로그인화면이미지 VO
	 */
	public List<LoginScrinImageVO> selectLoginScrinImageResult(LoginScrinImageVO loginScrinImageVO) throws Exception {
		return selectList("loginScrinImageDAO.selectLoginScrinImageResult", loginScrinImageVO);
	}
}
