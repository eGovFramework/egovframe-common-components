package egovframework.com.utl.sys.dbm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.dbm.service.DbMntrngLog;

/**
 * DB서비스모니터링관리에 대한 Mapper 인터페이스를 정의한다.
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
@EgovMapper("dbMntrngMapper")
public interface DbMntrngMapper {

	void deleteDbMntrng(DbMntrng dbMntrng);

	void insertDbMntrng(DbMntrng dbMntrng);

	void insertDbMntrngLog(DbMntrngLog dbMntrngLog);

	DbMntrng selectDbMntrng(DbMntrng dbMntrng);

	DbMntrngLog selectDbMntrngLog(DbMntrngLog dbMntrngLog);

	List<DbMntrng> selectDbMntrngList(DbMntrng searchVO);

	int selectDbMntrngListCnt(DbMntrng searchVO);

	List<DbMntrngLog> selectDbMntrngLogList(DbMntrngLog searchVO);

	int selectDbMntrngLogListCnt(DbMntrngLog searchVO);

	void updateDbMntrng(DbMntrng dbMntrng);

}
