/**
 * 개요
 * - 로그인화면이미지에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 05-8-2009 오후 2:08:56
 * 
 * 수정
 * 2017.02.07 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 */

package egovframework.com.uss.ion.lsi.service.impl;

import java.io.File;
import java.util.List;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.lsi.service.EgovLoginScrinImageService;
import egovframework.com.uss.ion.lsi.service.LoginScrinImage;
import egovframework.com.uss.ion.lsi.service.LoginScrinImageVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("egovLoginScrinImageService")
public class EgovLoginScrinImageServiceImpl extends EgovAbstractServiceImpl implements EgovLoginScrinImageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovLoginScrinImageServiceImpl.class);

	@Resource(name="loginScrinImageDAO")
    private LoginScrinImageDAO loginScrinImageDAO;

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return List - 로그인화면이미지 목록
	 */
	public List<LoginScrinImageVO> selectLoginScrinImageList(LoginScrinImageVO loginScrinImageVO) throws Exception{
		return loginScrinImageDAO.selectLoginScrinImageList(loginScrinImageVO);
	}

	/**
	 * 로그인화면이미지목록 총 갯수를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return int - 로그인화면이미지 카운트 수
	 */
	public int selectLoginScrinImageListTotCnt(LoginScrinImageVO loginScrinImageVO) throws Exception {
		return loginScrinImageDAO.selectLoginScrinImageListTotCnt(loginScrinImageVO);
	}
	
	/**
	 * 등록된 로그인화면이미지의 상세정보를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return LoginScrinImageVO - 로그인화면이미지 VO
	 */
	public LoginScrinImageVO selectLoginScrinImage(LoginScrinImageVO loginScrinImageVO) throws Exception {
		return loginScrinImageDAO.selectLoginScrinImage(loginScrinImageVO);
	}

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public LoginScrinImageVO insertLoginScrinImage(LoginScrinImage loginScrinImage, LoginScrinImageVO loginScrinImageVO) throws Exception {
		loginScrinImageDAO.insertLoginScrinImage(loginScrinImage);
		loginScrinImageVO.setImageId(loginScrinImage.getImageId());
        return selectLoginScrinImage(loginScrinImageVO);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void updateLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception {
		loginScrinImageDAO.updateLoginScrinImage(loginScrinImage);
	}

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void deleteLoginScrinImage(LoginScrinImage loginScrinImage) throws Exception {
		deleteLoginScrinImageFile(loginScrinImage);		
		loginScrinImageDAO.deleteLoginScrinImage(loginScrinImage);
	}

	/**
	 * 기 등록된 로그인화면이미지정보의 파일을 삭제한다.
	 * @param loginScrinImage - 로그인화면이미지 model
	 */
	public void deleteLoginScrinImageFile(LoginScrinImage loginScrinImage) throws Exception {
		FileVO fileVO = (FileVO)loginScrinImageDAO.selectLoginScrinImageFile(loginScrinImage);
		File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
		//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
		if(file.delete()){
			LOGGER.debug("[file.delete] file : File Deletion Success");
		}else{
			LOGGER.error("[file.delete] file : File Deletion Fail");
		}
	}

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param loginScrinImageVO - 로그인화면이미지 VO
	 * @return LoginScrinImageVO - 로그인화면이미지 VO
	 */
	public List<LoginScrinImageVO> selectLoginScrinImageResult(LoginScrinImageVO loginScrinImageVO) throws Exception {
		return loginScrinImageDAO.selectLoginScrinImageResult(loginScrinImageVO);
	}
}
