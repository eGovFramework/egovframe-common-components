package egovframework.com.uss.mpe.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovIndvdlPgeService {

	List<?> selectIndvdlPgeList(IndvdlPgeVO searchVO);

	int selectIndvdlPgeListCnt(IndvdlPgeVO searchVO);

	IndvdlPgeVO selectIndvdlPgeDetail(IndvdlPgeVO indvdlPgeVO) throws Exception;

	void insertIndvdlPge(IndvdlPgeVO indvdlPgeVO) throws FdlException;

	void updateIndvdlPge(IndvdlPgeVO indvdlPgeVO);

}
