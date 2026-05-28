package egovframework.com.uss.ion.bnr.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovMapper;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.bnr.service.BannerVO;

/**
 * 배너 관리에 대한 Mapper 인터페이스
 * @author 이문준
 * @version 1.0
 */
@EgovMapper("bannerMapper")
public interface BannerMapper {

	/**
	 * 배너 목록을 조회한다.
	 */
	List<BannerVO> selectBannerList(BannerVO bannerVO);

	/**
	 * 배너 목록 총 개수를 조회한다.
	 */
	int selectBannerListTotCnt(BannerVO bannerVO);

	/**
	 * 배너 상세정보를 조회한다.
	 */
	BannerVO selectBanner(BannerVO bannerVO);

	/**
	 * 배너를 등록한다.
	 */
	void insertBanner(BannerVO bannerVO);

	/**
	 * 배너를 수정한다.
	 */
	void updateBanner(BannerVO bannerVO);

	/**
	 * 배너를 삭제한다.
	 */
	void deleteBanner(BannerVO bannerVO);

	/**
	 * 배너 파일정보를 조회한다.
	 */
	FileVO selectBannerFile(BannerVO bannerVO);

	/**
	 * 배너 반영 결과를 조회한다.
	 */
	List<BannerVO> selectBannerResult(BannerVO bannerVO);
}
