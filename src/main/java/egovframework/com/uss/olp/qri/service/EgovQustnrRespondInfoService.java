package egovframework.com.uss.olp.qri.service;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 설문조사 Service Class 구현
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
public interface EgovQustnrRespondInfoService {
	
    /**
	 * 설문템플릿을 조회한다.
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrTmplatManage(Map<?, ?> map) throws Exception;

	/**
	 * 객관식 통계를 조회 조회한다.
	 * 
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrRespondInfoManageStatistics1(Map<?, ?> map) throws Exception;
	
	/**
     * 주관식 통계를 조회 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageStatistics2(Map<?, ?> map) throws Exception;

	/**
	 * 회원정보를 조회한다.
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public Map<?, ?> selectQustnrRespondInfoManageEmplyrinfo(Map<?, ?> map) throws Exception;

    /**
	 * 설문정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageComtnqestnrinfo(Map<?, ?> map) throws Exception;
    
    /**
     * 문항정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageComtnqustnrqesitm(Map<?, ?> map) throws Exception;
    
    /**
     * 항목정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageComtnqustnriem(Map<?, ?> map) throws Exception;

    /**
	 *  설문조사(설문등록)를(을) 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrRespondInfoManageList(ComDefaultVO searchVO) throws Exception;

    /**
	 * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @throws Exception
	 */
	public int selectQustnrRespondInfoManageListCnt(ComDefaultVO searchVO) throws Exception;

	/**
	 * 응답자결과(설문조사) 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrRespondInfoList(ComDefaultVO searchVO) throws Exception;

	/**
	 * 응답자결과(설문조사)를(을) 상세조회 한다.
	 * 
	 * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrRespondInfoDetail(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

    /**
	 * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrRespondInfoListCnt(ComDefaultVO searchVO) throws Exception;

    /**
	 * 응답자결과(설문조사)를(을) 등록한다.
	 * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @throws Exception
	 */
	void  insertQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

    /**
	 * 응답자결과(설문조사)를(을) 수정한다.
	 * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @throws Exception
	 */
	void  updateQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

    /**
	 * 응답자결과(설문조사)를(을) 삭제한다.
	 * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception;

	/**
	 * 설문템플릿 화이트리스트를 조회한다.
	 * 
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrTmplatWhiteList() throws Exception;

}
