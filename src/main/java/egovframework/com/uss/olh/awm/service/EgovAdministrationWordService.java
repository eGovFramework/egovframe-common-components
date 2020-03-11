package egovframework.com.uss.olh.awm.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovAdministrationWordService {

	List<?> selectAdministrationWordList(AdministrationWordVO searchVO);

	int selectAdministrationWordListCnt(AdministrationWordVO searchVO);

	AdministrationWordVO selectAdministrationWordDetail(AdministrationWordVO administrationWord) throws Exception;

	void insertAdministrationWord(AdministrationWordVO administrationWordVO) throws FdlException;

	void updateAdministrationWord(AdministrationWordVO administrationWordVO);

	void deleteAdministrationWord(AdministrationWordVO administrationWordVO);

}
