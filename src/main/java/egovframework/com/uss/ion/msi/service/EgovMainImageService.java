package egovframework.com.uss.ion.msi.service;

import java.util.List;

/**
 * 개요
 * - 메인화면이미지에 대한 Service Interface를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 */
public interface EgovMainImageService {

	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception;

	public int selectMainImageListTotCnt(MainImageVO mainImageVO) throws Exception;

	public MainImageVO selectMainImage(MainImageVO mainImageVO) throws Exception;

	public MainImageVO insertMainImage(MainImageVO mainImageVO) throws Exception;

	public void updateMainImage(MainImageVO mainImageVO) throws Exception;

	public void deleteMainImage(MainImageVO mainImageVO) throws Exception;

	public void deleteMainImageFile(MainImageVO mainImageVO) throws Exception;

	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception;
}
