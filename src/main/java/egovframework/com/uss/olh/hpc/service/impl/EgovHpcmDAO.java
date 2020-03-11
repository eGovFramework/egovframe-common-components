package egovframework.com.uss.olh.hpc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olh.hpc.service.HpcmDefaultVO;
import egovframework.com.uss.olh.hpc.service.HpcmVO;

@Repository("EgovHpcmDAO")
public class EgovHpcmDAO extends EgovComAbstractDAO {

	public List<?> selectHpcmList(HpcmDefaultVO searchVO) {
		return list("Hpcm.selectHpcmList", searchVO);
	}

	public int selectHpcmListCnt(HpcmDefaultVO searchVO) {
		return (Integer)selectOne("Hpcm.selectHpcmListCnt", searchVO);
	}

	public HpcmVO selectHpcmDetail(HpcmVO hpcmManageVO) {
		return (HpcmVO) selectOne("Hpcm.selectHpcmDetail", hpcmManageVO);
	}

	public void insertHpcm(HpcmVO hpcmVO) {
		insert ("Hpcm.insertHpcm", hpcmVO);
	}

	public void updateHpcm(HpcmVO hpcmVO) {
		update("Hpcm.updateHpcm", hpcmVO);
	}

	public void deleteHpcm(HpcmVO hpcmVO) {
		delete("Hpcm.deleteHpcm", hpcmVO);
	}

}
