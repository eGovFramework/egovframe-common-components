package egovframework.com.uss.ion.ism.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.ion.ism.service.InfrmlSanctn;
import egovframework.com.uss.ion.ism.service.SanctnerVO;

/**
 * 개요
 * - 약식결재관리에 대한 DAO 인터페이스를 정의한다.
 *
 * 상세내용
 * - 약식결재관리에 대한 등록, 수정, 삭제기능을 제공한다.
 * - 결재자에 대한 목록조회기능을 제공한다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 11:29:26
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.06.28  장철호           최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 * </pre>
 */
@EgovMapper("InfrmlSanctnDAO")
public interface InfrmlSanctnDAO {

	/**
	 * 주어진 조건에 맞는 결재자를 불러온다.
	 * @param sanctnerVO
	 * @return List
	 */
	List<SanctnerVO> selectSanctnerList(SanctnerVO sanctnerVO);

	/**
	 * 결재자 목록에 대한 전체 건수를 조회한다.
	 * @param sanctnerVO
	 * @return int
	 */
	int selectSanctnerListCnt(SanctnerVO sanctnerVO);

	/**
	 * 주어진 조건에 맞는 약식결재정보를 불러온다.
	 * @param infrmlSanctn
	 * @return InfrmlSanctn
	 */
	InfrmlSanctn selectInfrmlSanctn(InfrmlSanctn infrmlSanctn);

	/**
	 * 약식결재관리 정보를 수정한다.
	 * @param infrmlSanctn
	 */
	void updateInfrmlSanctn(InfrmlSanctn infrmlSanctn);

	/**
	 * 약식결재관리 정보를 승인 또는 반려한다.
	 * @param infrmlSanctn
	 */
	void updateInfrmlSanctnConfm(InfrmlSanctn infrmlSanctn);

	/**
	 * 약식결재관리 정보를 등록한다.
	 * @param infrmlSanctn
	 */
	void insertInfrmlSanctn(InfrmlSanctn infrmlSanctn);

	/**
	 * 약식결재관리 정보를 삭제한다.
	 * @param infrmlSanctn
	 */
	void deleteInfrmlSanctn(InfrmlSanctn infrmlSanctn);

}
