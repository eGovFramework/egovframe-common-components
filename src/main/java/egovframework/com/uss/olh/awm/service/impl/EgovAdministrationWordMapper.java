package egovframework.com.uss.olh.awm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.uss.olh.awm.service.AdministrationWordVO;

/**
 * 행정용어사전 정보를 관리하기 위한 Mapper 인터페이스
 * @author 공통서비스 개발팀
 * @since 2009.04.01
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자       수정내용
 *  ----------    --------    ---------------------------
 *   2009.04.01    개발팀       최초 생성
 *
 * </pre>
 */
@EgovMapper
public interface EgovAdministrationWordMapper {

	List<AdministrationWordVO> selectAdministrationWordList(AdministrationWordVO searchVO);

	int selectAdministrationWordListCnt(AdministrationWordVO searchVO);

	AdministrationWordVO selectAdministrationWordDetail(AdministrationWordVO administrationWord);

	void insertAdministrationWord(AdministrationWordVO administrationWordVO);

	void updateAdministrationWord(AdministrationWordVO administrationWordVO);

	void deleteAdministrationWord(AdministrationWordVO administrationWordVO);

}
