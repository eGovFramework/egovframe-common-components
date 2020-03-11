package egovframework.com.uss.olp.qrm.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 설문응답자관리 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovQustnrRespondManageService {

    /**
	 * 응답자정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrRespondManageList(ComDefaultVO searchVO) throws Exception;

    /**
	 * 응답자정보를(을) 상세조회 한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrRespondManageDetail(QustnrRespondManageVO qustnrRespondManageVO) throws Exception;

    /**
	 * 응답자정보를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrRespondManageListCnt(ComDefaultVO searchVO) throws Exception;

    /**
	 * 응답자정보를(을) 등록한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @throws Exception
	 */
	void  insertQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) throws Exception;

    /**
	 * 응답자정보를(을) 수정한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @throws Exception
	 */
	void  updateQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) throws Exception;

    /**
	 * 응답자정보를(을) 삭제한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) throws Exception;


}
