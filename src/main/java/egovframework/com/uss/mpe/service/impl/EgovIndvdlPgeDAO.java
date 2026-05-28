package egovframework.com.uss.mpe.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.mpe.service.IndvdlPgeVO;
import jakarta.annotation.Resource;

@Repository("EgovIndvdlPgeDAO")
public class EgovIndvdlPgeDAO {

	@Resource(name = "egovIndvdlPgeMapper")
	private EgovIndvdlPgeMapper egovIndvdlPgeMapper;

	public List<IndvdlPgeVO> selectIndvdlPgeList(IndvdlPgeVO searchVO) {
		return egovIndvdlPgeMapper.selectIndvdlPgeList(searchVO);
	}

	public int selectIndvdlPgeListCnt(IndvdlPgeVO searchVO) {
		return egovIndvdlPgeMapper.selectIndvdlPgeListCnt(searchVO);
	}

	public IndvdlPgeVO selectIndvdlPgeDetail(IndvdlPgeVO indvdlPgeVO) {
		return egovIndvdlPgeMapper.selectIndvdlPgeDetail(indvdlPgeVO);
	}

	public void insertIndvdlPge(IndvdlPgeVO indvdlPgeVO) {
		egovIndvdlPgeMapper.insertIndvdlPge(indvdlPgeVO);
	}

	public void updateIndvdlPge(IndvdlPgeVO indvdlPgeVO) {
		egovIndvdlPgeMapper.updateIndvdlPge(indvdlPgeVO);
	}

}
