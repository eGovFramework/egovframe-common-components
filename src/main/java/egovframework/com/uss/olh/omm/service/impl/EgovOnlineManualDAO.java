package egovframework.com.uss.olh.omm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.olh.omm.service.OnlineManualVO;

import jakarta.annotation.Resource;

@Repository("EgovOnlineManualDAO")
public class EgovOnlineManualDAO {

	@Resource(name = "EgovOnlineManualMapper")
	private EgovOnlineManualMapper egovOnlineManualMapper;

	public List<OnlineManualVO> selectOnlineManualList(OnlineManualVO searchVO) {
		return egovOnlineManualMapper.selectOnlineManualList(searchVO);
	}

	public int selectOnlineManualListCnt(OnlineManualVO searchVO) {
		return egovOnlineManualMapper.selectOnlineManualListCnt(searchVO);
	}

	public OnlineManualVO selectOnlineManualDetail(OnlineManualVO onlineManualVO) {
		return egovOnlineManualMapper.selectOnlineManualDetail(onlineManualVO);
	}

	public void insertOnlineManual(OnlineManualVO onlineManualVO) {
		egovOnlineManualMapper.insertOnlineManual(onlineManualVO);
	}

	public void updateOnlineManual(OnlineManualVO onlineManualVO) {
		egovOnlineManualMapper.updateOnlineManual(onlineManualVO);
	}

	public void deleteOnlineManual(OnlineManualVO onlineManualVO) {
		egovOnlineManualMapper.deleteOnlineManual(onlineManualVO);
	}

}
