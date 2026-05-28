package egovframework.com.utl.sys.trm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.trm.service.CntcVO;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrng;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrngLog;

/**
 * 송수신모니터링관리에 대한 Mapper 인터페이스를 정의한다.
 *
 * @author 김진만
 * @since 2010.06.21
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.06.21   김진만     최초 생성
 *  2026.05.28   dasomel    @EgovMapper 인터페이스 방식으로 전환
 * </pre>
 */
@EgovMapper("trsmrcvMntrngMapper")
public interface TrsmrcvMntrngMapper {

	void deleteTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng);

	void insertTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng);

	void insertTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog);

	TrsmrcvMntrng selectTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng);

	TrsmrcvMntrngLog selectTrsmrcvMntrngLog(TrsmrcvMntrngLog trsmrcvMntrngLog);

	List<TrsmrcvMntrng> selectTrsmrcvMntrngList(TrsmrcvMntrng searchVO);

	int selectTrsmrcvMntrngListCnt(TrsmrcvMntrng searchVO);

	List<TrsmrcvMntrngLog> selectTrsmrcvMntrngLogList(TrsmrcvMntrngLog searchVO);

	int selectTrsmrcvMntrngLogListCnt(TrsmrcvMntrngLog searchVO);

	void updateTrsmrcvMntrng(TrsmrcvMntrng trsmrcvMntrng);

	List<CntcVO> selectCntcList(CntcVO searchVO);

	int selectCntcListCnt(CntcVO searchVO);

}
