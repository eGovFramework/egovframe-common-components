package egovframework.com.uss.olh.hpc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.olh.hpc.service.HpcmDefaultVO;
import egovframework.com.uss.olh.hpc.service.HpcmVO;

import jakarta.annotation.Resource;

@Repository("EgovHpcmDAO")
public class EgovHpcmDAO {

	@Resource(name = "EgovHpcmMapper")
	private EgovHpcmMapper egovHpcmMapper;

	public List<HpcmVO> selectHpcmList(HpcmDefaultVO searchVO) {
		return egovHpcmMapper.selectHpcmList(searchVO);
	}

	public int selectHpcmListCnt(HpcmDefaultVO searchVO) {
		return egovHpcmMapper.selectHpcmListCnt(searchVO);
	}

	public HpcmVO selectHpcmDetail(HpcmVO hpcmManageVO) {
		return egovHpcmMapper.selectHpcmDetail(hpcmManageVO);
	}

	public void insertHpcm(HpcmVO hpcmVO) {
		egovHpcmMapper.insertHpcm(hpcmVO);
	}

	public void updateHpcm(HpcmVO hpcmVO) {
		egovHpcmMapper.updateHpcm(hpcmVO);
	}

	public void deleteHpcm(HpcmVO hpcmVO) {
		egovHpcmMapper.deleteHpcm(hpcmVO);
	}

}
