package egovframework.com.uss.ion.lsi.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovMapper;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;

/**
 * 로그인화면이미지 관리에 대한 Mapper 인터페이스
 * @author lee.m.j
 * @version 1.0
 */
@EgovMapper("loginScrinImageMapper")
public interface LoginScrinImageMapper {

	/**
	 * 로그인화면이미지 목록을 조회한다.
	 */
	List<LoginScrinImageVO> selectLoginScrinImageList(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지 목록 총 개수를 조회한다.
	 */
	int selectLoginScrinImageListTotCnt(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지 상세정보를 조회한다.
	 */
	LoginScrinImageVO selectLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지를 등록한다.
	 */
	void insertLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지를 수정한다.
	 */
	void updateLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지를 삭제한다.
	 */
	void deleteLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지 파일정보를 조회한다.
	 */
	FileVO selectLoginScrinImageFile(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지 반영 결과를 조회한다.
	 */
	List<LoginScrinImageVO> selectLoginScrinImageResult(LoginScrinImageVO loginScrinImageVO);
}
