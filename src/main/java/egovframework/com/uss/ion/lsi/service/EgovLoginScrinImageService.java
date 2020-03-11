
/**
 * 개요
 * - 로그인화면이미지에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 로그인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:56
 */

package egovframework.com.uss.ion.lsi.service;

import java.util.List;

public interface EgovLoginScrinImageService {

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return List - 로그인화면이미지 목록
	 */
	public List<LoginScrinImageVO> selectLoginScrinImageList(LoginScrinImageVO loginScrinImageVO) throws Exception;

	/**
	 * 로그인화면이미지목록 총 갯수를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return int - 로그인화면이미지 카운트 수
	 */
	public int selectLoginScrinImageListTotCnt(LoginScrinImageVO loginScrinImageVO) throws Exception ;
	
	/**
	 * 등록된 로그인화면이미지의 상세정보를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return LoginScrinImageVO - 로그인화면이미지 VO
	 */
	public LoginScrinImageVO selectLoginScrinImage(LoginScrinImageVO loginScrinImageVO) throws Exception;

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public LoginScrinImageVO insertLoginScrinImage(LoginScrinImage loginScrinImage, LoginScrinImageVO loginScrinImageVO) throws Exception;

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void updateLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception;

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void deleteLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception;

	/**
	 * 기 등록된 로그인화면이미지정보의 파일을 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void deleteLoginScrinImageFile(LoginScrinImage loginScrinImage) throws Exception;

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return LoginScrinImageVO - 로그인화면이미지 VO
	 */
	public List<LoginScrinImageVO> selectLoginScrinImageResult(LoginScrinImageVO loginScrinImageVO) throws Exception;
	
}