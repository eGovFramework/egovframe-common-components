package egovframework.com.uss.olh.omm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olh.omm.service.OnlineManualVO;

@Repository("EgovOnlineManualDAO")
public class EgovOnlineManualDAO extends EgovComAbstractDAO {

	public List<?> selectOnlineManualList(OnlineManualVO searchVO) {
		return list("OnlineManual.selectOnlineManualList", searchVO);
	}

	public int selectOnlineManualListCnt(OnlineManualVO searchVO) {
		return (Integer) selectOne("OnlineManual.selectOnlineManualListCnt", searchVO);
	}

	public OnlineManualVO selectOnlineManualDetail(OnlineManualVO onlineManualVO) {
		return (OnlineManualVO) selectOne("OnlineManual.selectOnlineManualDetail", onlineManualVO);
	}

	public void insertOnlineManual(OnlineManualVO onlineManualVO) {
		insert("OnlineManual.insertOnlineManual", onlineManualVO);
	}

	public void updateOnlineManual(OnlineManualVO onlineManualVO) {
		update("OnlineManual.updateOnlineManual", onlineManualVO);
	}

	public void deleteOnlineManual(OnlineManualVO onlineManualVO) {
		delete("OnlineManual.deleteOnlineManual", onlineManualVO);
	}

}
