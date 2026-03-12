/**
 * 개요
 * - 배너에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:12
 */

package egovframework.com.uss.ion.bnr.service.impl;

import java.io.File;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import egovframework.com.uss.ion.bnr.service.EgovBannerService;
import jakarta.annotation.Resource;

@Service("egovBannerService")
public class EgovBannerServiceImpl extends EgovAbstractServiceImpl implements EgovBannerService {

	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBannerServiceImpl.class);

	@Resource(name="bannerDAO")
    private BannerDAO bannerDAO;

	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return List - 배너 목록
	 */
	@Override
	public List<BannerVO> selectBannerList(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectBannerList(bannerVO);
	}

	/**
	 * 배너목록 총 개수를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return int - 배너 카운트 수
	 */
	@Override
	public int selectBannerListTotCnt(BannerVO bannerVO) throws Exception {
		return bannerDAO.selectBannerListTotCnt(bannerVO);
	}

	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return BannerVO - 배너 VO
	 */
	@Override
	public BannerVO selectBanner(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectBanner(bannerVO);
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 * @param bannerVO - 배너 VO
	 */
	@Override
	public BannerVO insertBanner(BannerVO bannerVO) throws Exception{
        bannerDAO.insertBanner(bannerVO);
        return selectBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 * @param bannerVO - 배너 VO
	 */
	@Override
	public void updateBanner(BannerVO bannerVO) throws Exception{
        bannerDAO.updateBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 * @param bannerVO - 배너 VO
	 */
	@Override
	public void deleteBanner(BannerVO bannerVO) throws Exception {
		deleteBannerFile(bannerVO);
        bannerDAO.deleteBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보의 이미지파일을 삭제한다.
	 * @param bannerVO - 배너 VO
	 */
	@Override
	public void deleteBannerFile(BannerVO bannerVO) throws Exception{
		FileVO fileVO = bannerDAO.selectBannerFile(bannerVO);
		File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
		//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
		if(file.delete()){
			LOGGER.debug("[file.delete] file : File Deletion Success");
		}else{
			LOGGER.error("[file.delete] file : File Deletion Fail");
		}
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 * @param bannerVO - 배너 VO
	 * @return BannerVO - 배너 VO
	 */
	@Override
	public List<BannerVO> selectBannerResult(BannerVO bannerVO) throws Exception{
		return bannerDAO.selectBannerResult(bannerVO);
	}

}