package egovframework.com.cop.smt.lsm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.smt.lsm.service.EmplyrVO;
import egovframework.com.cop.smt.lsm.service.LeaderSchdul;
import egovframework.com.cop.smt.lsm.service.LeaderSchdulVO;
import egovframework.com.cop.smt.lsm.service.LeaderSttus;
import egovframework.com.cop.smt.lsm.service.LeaderSttusVO;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 간부일정에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 간부일정에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 간부일정의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 28-6-2010 오전 10:59:06
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.6.28	장철호          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 */
@Repository("LeaderSchdulDAO")
public class LeaderSchdulDAO {

	@Resource(name = "leaderSchdulMapper")
	private LeaderSchdulMapper mapper;

	public List<EmplyrVO> selectEmplyrList(EmplyrVO emplyrVO) throws Exception {
		return mapper.selectEmplyrList(emplyrVO);
	}

	public int selectEmplyrListCnt(EmplyrVO emplyrVO) throws Exception {
		return mapper.selectEmplyrListCnt(emplyrVO);
	}

	public List<LeaderSchdulVO> selectLeaderSchdulList(LeaderSchdulVO leaderSchdulVO) throws Exception {
		return mapper.selectLeaderSchdulList(leaderSchdulVO);
	}

	public LeaderSchdulVO selectLeaderSchdul(LeaderSchdulVO leaderSchdulVO) throws Exception {
		return mapper.selectLeaderSchdul(leaderSchdulVO);
	}

	public void updateLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception {
		mapper.updateLeaderSchdul(leaderSchdul);
	}

	public void insertLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception {
		mapper.insertLeaderSchdul(leaderSchdul);
	}

	public void insertLeaderSchdulDe(LeaderSchdul leaderSchdul) throws Exception {
		mapper.insertLeaderSchdulDe(leaderSchdul);
	}

	public void deleteLeaderSchdul(LeaderSchdul leaderSchdul) throws Exception {
		mapper.deleteLeaderSchdul(leaderSchdul);
	}

	public void deleteLeaderSchdulDe(LeaderSchdul leaderSchdul) throws Exception {
		mapper.deleteLeaderSchdulDe(leaderSchdul);
	}

	public List<LeaderSttusVO> selectLeaderSttusList(LeaderSttusVO leaderSttusVO) throws Exception {
		return mapper.selectLeaderSttusList(leaderSttusVO);
	}

	public int selectLeaderSttusListCnt(LeaderSttusVO leaderSttusVO) throws Exception {
		return mapper.selectLeaderSttusListCnt(leaderSttusVO);
	}

	public LeaderSttusVO selectLeaderSttus(LeaderSttusVO leaderSttusVO) throws Exception {
		return mapper.selectLeaderSttus(leaderSttusVO);
	}

	public void updateLeaderSttus(LeaderSttus leaderSttus) throws Exception {
		mapper.updateLeaderSttus(leaderSttus);
	}

	public void insertLeaderSttus(LeaderSttus leaderSttus) throws Exception {
		mapper.insertLeaderSttus(leaderSttus);
	}

	public int selectLeaderSttusCheck(LeaderSttus leaderSttus) throws Exception {
		return mapper.selectLeaderSttusCheck(leaderSttus);
	}

	public void deleteLeaderSttus(LeaderSttus leaderSttus) throws Exception {
		mapper.deleteLeaderSttus(leaderSttus);
	}

}
