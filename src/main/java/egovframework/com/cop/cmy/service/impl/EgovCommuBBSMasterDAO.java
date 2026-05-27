package egovframework.com.cop.cmy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.cop.bbs.service.BoardMasterVO;

@Repository("EgovCommuBBSMasterDAO")
public class EgovCommuBBSMasterDAO {

	@Resource(name = "egovCommuBBSMasterMapper")
	private EgovCommuBBSMasterMapper egovCommuBBSMasterMapper;

	public List<BoardMasterVO> selectCommuBBSMasterListMain(BoardMasterVO boardMasterVO) {
		return egovCommuBBSMasterMapper.selectCommuBBSMasterListMain(boardMasterVO);
	}

}
