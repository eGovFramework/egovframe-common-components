/**
 * 개요
 * - 메인화면이미지에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.03  이문준           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */

package egovframework.com.uss.ion.msi.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.uss.ion.msi.service.MainImageVO;

@EgovMapper("mainImageDAO")
public interface MainImageDAO {

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	List<MainImageVO> selectMainImageList(MainImageVO mainImageVO);

	/**
	 * 메인화면이미지목록 총 개수를 조회한다.
	 * @param mainImageVO - 메인화면이미지 VO
	 * @return int
	 */
	int selectMainImageListTotCnt(MainImageVO mainImageVO);

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	MainImageVO selectMainImage(MainImageVO mainImageVO);

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImageVO - 메인이미지 VO
	 */
	void insertMainImage(MainImageVO mainImageVO);

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImageVO - 메인이미지 VO
	 */
	void updateMainImage(MainImageVO mainImageVO);

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImageVO - 메인이미지 VO
	 */
	void deleteMainImage(MainImageVO mainImageVO);

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return FileVO - 파일 VO
	 */
	FileVO selectMainImageFile(MainImageVO mainImageVO);

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO);
}
