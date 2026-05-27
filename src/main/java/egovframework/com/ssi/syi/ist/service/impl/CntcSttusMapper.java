package egovframework.com.ssi.syi.ist.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.ssi.syi.ist.service.CntcSttus;
import egovframework.com.ssi.syi.ist.service.CntcSttusVO;

/**
 * 연계현황에 대한 매퍼 인터페이스를 정의한다
 *
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2026.05.28  dasomel        @EgovMapper 인터페이스 방식으로 전환
 *
 *      </pre>
 */
@EgovMapper("CntcSttusMapper")
public interface CntcSttusMapper {

	/**
	 * 연계현황 상세항목을 조회한다.
	 *
	 * @param cntcSttus
	 * @return CntcSttus(연계현황)
	 */
	CntcSttus selectCntcSttusDetail(CntcSttus cntcSttus);

	/**
	 * 연계현황 목록을 조회한다.
	 *
	 * @param searchVO
	 * @return List(연계현황 목록)
	 */
	List<EgovMap> selectCntcSttusList(CntcSttusVO searchVO);

	/**
	 * 연계현황 총 개수를 조회한다.
	 *
	 * @param searchVO
	 * @return int(연계현황 총 개수)
	 */
	int selectCntcSttusListTotCnt(CntcSttusVO searchVO);

}
