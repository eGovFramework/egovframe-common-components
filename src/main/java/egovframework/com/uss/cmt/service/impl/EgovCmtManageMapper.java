package egovframework.com.uss.cmt.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.cmt.service.CmtDefaultVO;
import egovframework.com.uss.cmt.service.CmtManageVO;

/**
 * 출퇴근관리에 대한 Mapper 인터페이스
 * @author 표준프레임워크 개발팀
 * @since 2014.11.10
 * @version 1.0
 */
@EgovMapper("cmtManageMapper")
public interface EgovCmtManageMapper {

	/**
	 * 출퇴근 정보 목록을 조회한다.
	 */
	List<CmtManageVO> selectCmtList_S(CmtDefaultVO cmtSearchVO);

	/**
	 * 출근 정보를 등록한다.
	 */
	int insertWrkStartCmtInfo_S(CmtManageVO cmtManageVO);

	/**
	 * 퇴근 정보를 수정한다.
	 */
	int insertWrkEndCmtInfo_S(CmtManageVO cmtManageVO);

	/**
	 * 퇴근 정보 입력을 위한 출근 정보 id를 조회한다.
	 */
	String selectWrktmId_S(CmtManageVO cmtManageVO);

	/**
	 * 퇴근 정보 입력을 위한 출근 정보를 조회한다.
	 */
	CmtManageVO selectWrkStartInfo_S(CmtManageVO cmtManageVO);

}
