/**
 * 개요
 * - 메인화면이미지에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 */

package egovframework.com.uss.ion.msi.service.impl;

import java.util.List;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.msi.service.MainImage;
import egovframework.com.uss.ion.msi.service.MainImageVO;

import org.springframework.stereotype.Repository;

@Repository("mainImageDAO")
public class MainImageDAO extends EgovComAbstractDAO {
	
	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception {
		return selectList("mainImageDAO.selectMainImageList", mainImageVO);
	}

    /**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * @param mainImageVO - 메인화면이미지 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectMainImageListTotCnt(MainImageVO mainImageVO) throws Exception {
        return (Integer)selectOne("mainImageDAO.selectMainImageListTotCnt", mainImageVO);
    }

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public MainImageVO selectMainImage(MainImageVO mainImageVO) throws Exception {
		return (MainImageVO) selectOne("mainImageDAO.selectMainImage", mainImageVO);
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void insertMainImage(MainImage mainImage) throws Exception {
		insert("mainImageDAO.insertMainImage", mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void updateMainImage(MainImage mainImage) throws Exception {
		update("mainImageDAO.updateMainImage", mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void deleteMainImage(MainImage mainImage) throws Exception {
		delete("mainImageDAO.deleteMainImage", mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * @param mainImage - 메인이미지 model
	 */
	public FileVO selectMainImageFile(MainImage mainImage) throws Exception {
		return (FileVO) selectOne("mainImageDAO.selectMainImageFile", mainImage);
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception {
		return selectList("mainImageDAO.selectMainImageResult", mainImageVO);
	}
}
