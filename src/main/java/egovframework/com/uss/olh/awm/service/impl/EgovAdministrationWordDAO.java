package egovframework.com.uss.olh.awm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.olh.awm.service.AdministrationWordVO;

import jakarta.annotation.Resource;

@Repository("EgovAdministrationWordDAO")
public class EgovAdministrationWordDAO {

	@Resource(name = "EgovAdministrationWordMapper")
	private EgovAdministrationWordMapper egovAdministrationWordMapper;

	public List<AdministrationWordVO> selectAdministrationWordList(AdministrationWordVO searchVO) {
		return egovAdministrationWordMapper.selectAdministrationWordList(searchVO);
	}

	public int selectAdministrationWordListCnt(AdministrationWordVO searchVO) {
		return egovAdministrationWordMapper.selectAdministrationWordListCnt(searchVO);
	}

	public AdministrationWordVO selectAdministrationWordDetail(AdministrationWordVO administrationWord) {
		return egovAdministrationWordMapper.selectAdministrationWordDetail(administrationWord);
	}

	public void insertAdministrationWord(AdministrationWordVO administrationWordVO) {
		egovAdministrationWordMapper.insertAdministrationWord(administrationWordVO);
	}

	public void updateAdministrationWord(AdministrationWordVO administrationWordVO) {
		egovAdministrationWordMapper.updateAdministrationWord(administrationWordVO);
	}

	public void deleteAdministrationWord(AdministrationWordVO administrationWordVO) {
		egovAdministrationWordMapper.deleteAdministrationWord(administrationWordVO);
	}

}
