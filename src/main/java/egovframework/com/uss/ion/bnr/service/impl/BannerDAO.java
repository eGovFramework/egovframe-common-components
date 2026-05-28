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

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import jakarta.annotation.Resource;

@Repository("bannerDAO")
public class BannerDAO {

	@Resource(name = "bannerMapper")
	private BannerMapper bannerMapper;

	/**
	 * 배너를 관리하기 위해 등록된 배너목록을 조회한다.
	 */
	public List<BannerVO> selectBannerList(BannerVO bannerVO) throws Exception {
		return bannerMapper.selectBannerList(bannerVO);
	}

	/**
	 * 배너목록 총 개수를 조회한다.
	 */
	public int selectBannerListTotCnt(BannerVO bannerVO) throws Exception {
		return bannerMapper.selectBannerListTotCnt(bannerVO);
	}

	/**
	 * 등록된 배너의 상세정보를 조회한다.
	 */
	public BannerVO selectBanner(BannerVO bannerVO) throws Exception {
		return bannerMapper.selectBanner(bannerVO);
	}

	/**
	 * 배너정보를 신규로 등록한다.
	 */
	public void insertBanner(BannerVO bannerVO) throws Exception {
		bannerMapper.insertBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보를 수정한다.
	 */
	public void updateBanner(BannerVO bannerVO) throws Exception {
		bannerMapper.updateBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보를 삭제한다.
	 */
	public void deleteBanner(BannerVO bannerVO) throws Exception {
		bannerMapper.deleteBanner(bannerVO);
	}

	/**
	 * 기 등록된 배너정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 */
	public FileVO selectBannerFile(BannerVO bannerVO) throws Exception {
		return bannerMapper.selectBannerFile(bannerVO);
	}

	/**
	 * 배너가 특정화면에 반영된 결과를 조회한다.
	 */
	public List<BannerVO> selectBannerResult(BannerVO bannerVO) throws Exception {
		return bannerMapper.selectBannerResult(bannerVO);
	}
}
