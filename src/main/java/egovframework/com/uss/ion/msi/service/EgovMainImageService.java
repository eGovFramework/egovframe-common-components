/**
 * 개요
 * - 메인화면이미지에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 */

package egovframework.com.uss.ion.msi.service;

import java.util.List;

public interface EgovMainImageService {

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception;

	/**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return int - 메인이미지 카운트 수
	 */
	public int selectLoginScrinImageListTotCnt(MainImageVO mainImageVO) throws Exception;
	
	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public MainImageVO selectMainImage(MainImageVO mainImageVO) throws Exception;

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 * @param mainImageVO - 메인이미지 VO
	 */
	public MainImageVO insertMainImage(MainImage mainImage,MainImageVO mainImageVO) throws Exception;

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void updateMainImage(MainImage mainImage) throws Exception;

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void deleteMainImage(MainImage mainImage) throws Exception;

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void deleteMainImageFile(MainImage mainImage) throws Exception;

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception;

}