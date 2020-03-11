package egovframework.com.uss.olh.hpc.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovHpcmService {

	List<?> selectHpcmList(HpcmDefaultVO searchVO);

	int selectHpcmListCnt(HpcmDefaultVO searchVO);

	HpcmVO selectHpcmDetail(HpcmVO hpcmManageVO) throws Exception;

	void insertHpcm(HpcmVO hpcmVO) throws FdlException;

	void updateHpcm(HpcmVO hpcmVO);

	void deleteHpcmCn(HpcmVO hpcmVO);

}
