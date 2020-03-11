package egovframework.com.uss.olp.qmc.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 설문관리를 처리하는 Service Class 구현
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
public interface EgovQustnrManageService {

    /**
	 * 설문템플릿 목록을 조회한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrTmplatManageList(QustnrManageVO qustnrManageVO) throws Exception;

    /**
	 * 설문관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrManageList(ComDefaultVO searchVO) throws Exception;

    /**
	 * 설문관리를(을) 상세조회 한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrManageDetail(QustnrManageVO qustnrManageVO) throws Exception;

    /**
	 * 설문관리를 상세조회(Model) 한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
    public QustnrManageVO selectQustnrManageDetailModel(QustnrManageVO qustnrManageVO) throws Exception ;

    /**
	 * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrManageListCnt(ComDefaultVO searchVO) throws Exception;

    /**
	 * 설문관리를(을) 등록한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  insertQustnrManage(QustnrManageVO qustnrManageVO) throws Exception;

    /**
	 * 설문관리를(을) 수정한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  updateQustnrManage(QustnrManageVO qustnrManageVO) throws Exception;

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteQustnrManage(QustnrManageVO qustnrManageVO) throws Exception;


}
