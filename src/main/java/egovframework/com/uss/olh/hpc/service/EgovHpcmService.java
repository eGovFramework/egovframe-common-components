package egovframework.com.uss.olh.hpc.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;

public interface EgovHpcmService {

	List<HpcmVO> selectHpcmList(HpcmDefaultVO searchVO);

	int selectHpcmListCnt(HpcmDefaultVO searchVO);

	HpcmVO selectHpcmDetail(HpcmVO hpcmManageVO) throws Exception;

	void insertHpcm(HpcmVO hpcmVO) throws FdlException;

	void updateHpcm(HpcmVO hpcmVO);

	void deleteHpcmCn(HpcmVO hpcmVO);

}
