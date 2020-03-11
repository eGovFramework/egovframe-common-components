/**
 * 개요
 * - 배너에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:11
 */

package egovframework.com.uss.ion.bnr.service.impl;

import java.util.List;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.bnr.service.Banner;
import egovframework.com.uss.ion.bnr.service.BannerVO;

import org.springframework.stereotype.Repository;

@Repository("bannerDAO")
public class BannerDAO extends EgovComAbstractDAO {
	
	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * @param bannerVO - 배너 Vo
	 * @return List - 배너 목록
	 * @exception Exception
	 */	
	public List<BannerVO> selectBannerList(BannerVO bannerVO) throws Exception {
		return selectList("bannerDAO.selectBannerList", bannerVO);
	}

    /**
	 * 배너목록 총 갯수를 조회한다.
	 * @param bannerVO BannerVO
	 * @return int
	 * @exception Exception
	 */
    public int selectBannerListTotCnt(BannerVO bannerVO) throws Exception {
        return (Integer)selectOne("bannerDAO.selectBannerListTotCnt", bannerVO);
    }

	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 * @param bannerVO - 배너 Vo
	 * @return BannerVO - 배너 Vo
	 * 
	 * @param bannerVO
	 */
	public BannerVO selectBanner(BannerVO bannerVO) throws Exception {
		return (BannerVO) selectOne("bannerDAO.selectBanner", bannerVO);
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 * @param banner - 배너 model
	 */
	public void insertBanner(Banner banner) throws Exception {
		insert("bannerDAO.insertBanner", banner);
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * @param banner - 배너 model
	 */
	public void updateBanner(Banner banner) throws Exception {
        update("bannerDAO.updateBanner", banner);
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * @param banner - 배너 model
	 * 
	 * @param banner
	 */
	public void deleteBanner(Banner banner) throws Exception {
		delete("bannerDAO.deleteBanner", banner);
	}

	/**
	 * 기 등록된 배너정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * @param banner - 배너 model
	 * @return FileVO - 파일 VO
	 */
	public FileVO selectBannerFile(Banner banner) throws Exception {
		return (FileVO) selectOne("bannerDAO.selectBannerFile", banner);
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return BannerVO - 배너 VO
	 * @exception Exception
	 */
	
	public List<BannerVO> selectBannerResult(BannerVO bannerVO) throws Exception {
		return selectList("bannerDAO.selectBannerResult", bannerVO);
	}

}
