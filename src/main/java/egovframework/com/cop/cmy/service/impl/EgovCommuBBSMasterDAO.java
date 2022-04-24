package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractMapper;
import egovframework.com.cop.bbs.service.BoardMasterVO;

@Repository("EgovCommuBBSMasterDAO")
public class EgovCommuBBSMasterDAO extends EgovComAbstractMapper {

	public List<BoardMasterVO> selectCommuBBSMasterListMain(BoardMasterVO boardMasterVO) {
		return selectList("CommuBBSMaster.selectCommuBBSMasterListMain", boardMasterVO);
	}

}
