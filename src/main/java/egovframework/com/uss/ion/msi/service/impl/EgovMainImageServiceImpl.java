/**
 * 개요
 * - 메인화면이미지에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 * 
 * 수정
 * 2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 */

package egovframework.com.uss.ion.msi.service.impl;

import java.io.File;
import java.util.List;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.msi.service.EgovMainImageService;
import egovframework.com.uss.ion.msi.service.MainImage;
import egovframework.com.uss.ion.msi.service.MainImageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("egovMainImageService")
public class EgovMainImageServiceImpl extends EgovAbstractServiceImpl implements EgovMainImageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovMainImageServiceImpl.class);

	@Resource(name="mainImageDAO")
    private MainImageDAO mainImageDAO;
	
	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImageList(mainImageVO);
	}

	/**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return int - 메인이미지 카운트 수
	 */
	public int selectLoginScrinImageListTotCnt(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImageListTotCnt(mainImageVO);
	}

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public MainImageVO selectMainImage(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImage(mainImageVO);
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 */
	public MainImageVO insertMainImage(MainImage mainImage,MainImageVO mainImageVO) throws Exception {
		mainImageDAO.insertMainImage(mainImage);
		mainImageVO.setImageId(mainImage.getImageId());
		return selectMainImage(mainImageVO);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void updateMainImage(MainImage mainImage) throws Exception {
		mainImageDAO.updateMainImage(mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void deleteMainImage(MainImage mainImage) throws Exception {

		deleteMainImageFile(mainImage);
		mainImageDAO.deleteMainImage(mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void deleteMainImageFile(MainImage mainImage) throws Exception {
		FileVO fileVO = (FileVO)mainImageDAO.selectMainImageFile(mainImage);
		File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
		//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
		if(file.delete()){
			LOGGER.debug("[file.delete] file : File Deletion Success");
		}else{
			LOGGER.error("[file.delete] file : File Deletion Fail");
		}
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImageResult(mainImageVO);
	}
}
