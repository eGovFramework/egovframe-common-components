package egovframework.com.cop.smt.lsm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdul;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import egovframework.com.cop.smt.lsm.service.LeaderSttus;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;

/**
 * 간부일정에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("leaderSchdulMapper")
public interface LeaderSchdulMapper {

	List<EmplyrVO> selectEmplyrList(EmplyrVO emplyrVO);

	int selectEmplyrListCnt(EmplyrVO emplyrVO);

	List<LeaderSchdulVO> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVO);

	LeaderSchdulVO selectLeaderSchdul(LeaderSchdulVO leaderSchdulVO);

	void updateLeaderSchdul(LeaderSchdul leaderSchdul);

	void insertLeaderSchdul(LeaderSchdul leaderSchdul);

	void insertLeaderSchdulDe(LeaderSchdul leaderSchdul);

	void deleteLeaderSchdul(LeaderSchdul leaderSchdul);

	void deleteLeaderSchdulDe(LeaderSchdul leaderSchdul);

	List<LeaderSttusVO> selectLeaderSttusList(LeaderSttusVO leaderSttusVO);

	int selectLeaderSttusListCnt(LeaderSttusVO leaderSttusVO);

	LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO);

	void updateLeaderSttus(LeaderSttus leaderSttus);

	void insertLeaderSttus(LeaderSttus leaderSttus);

	int selectLeaderSttusCheck(LeaderSttus leaderSttus);

	void deleteLeaderSttus(LeaderSttus leaderSttus);

}
