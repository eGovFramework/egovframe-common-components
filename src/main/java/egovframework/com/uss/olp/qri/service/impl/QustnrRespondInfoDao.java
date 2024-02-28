package egovframework.com.uss.olp.qri.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olp.qri.service.QustnrRespondInfoVO;
/**
 * 설문조사 Dao Class 구현
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
 *   2017.07.24  김예영          표준프레임워크 v3.7 개선(select->selectOne수정)
 *
 * </pre>
 */
@Repository("qustnrRespondInfoDao")
public class QustnrRespondInfoDao extends EgovComAbstractDAO {


    /**
	 * 설문템플릿을 조회한다.
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrTmplatManage(Map<?, ?> map) throws Exception{
		return selectList("QustnrRespondInfo.selectQustnrTmplatManages", map);
	}

	/**
	 * 객관식 통계를 조회 조회한다.
	 *
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrRespondInfoManageStatistics1(Map<?, ?> map) throws Exception {
		return selectList("QustnrRespondInfo.selectQustnrRespondInfoManageStatistics1", map);
	}

	/**
     * 주관식 통계를 조회 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageStatistics2(Map<?, ?> map) throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrRespondInfoManageStatistics2", map);
    }

    /**
	 * 회원정보를 조회한다.
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public Map<?, ?> selectQustnrRespondInfoManageEmplyrinfo(Map<?, ?> map) throws Exception{
		return (Map<?, ?>)selectOne("QustnrRespondInfo.selectQustnrRespondInfoManageEmplyrinfo", map);
	}

    /**
     * 설문정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageComtnqestnrinfo(Map<?, ?> map) throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrRespondInfoManageComtnqestnrinfo", map);
    }

    /**
     * 문항정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageComtnqustnrqesitm(Map<?, ?> map) throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrRespondInfoManageComtnqustnrqesitm", map);
    }

    /**
     * 항목정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageComtnqustnriem(Map<?, ?> map) throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrRespondInfoManageComtnqustnriem", map);
    }

    /**
     * 설문조사(설문등록)를(을) 목록을 조회한다.
     *
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoManageList(ComDefaultVO searchVO){
        return selectList("QustnrRespondInfo.selectQustnrRespondInfoManage", searchVO);
    }

    /**
	 * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrRespondInfoManageListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)selectOne("QustnrRespondInfo.selectQustnrRespondInfoManageCnt", searchVO);
	}

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     *
     * @param searchVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoList(ComDefaultVO searchVO) throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrRespondInfo", searchVO);
    }

    /**
     * 응답자결과(설문조사)를(을) 상세조회 한다.
     *
     * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
     * @throws Exception
     */
    public List<EgovMap> selectQustnrRespondInfoDetail(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrRespondInfoDetail", qustnrRespondInfoVO);
    }

    /**
	 * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
 	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrRespondInfoListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)selectOne("QustnrRespondInfo.selectQustnrRespondInfoCnt", searchVO);
	}

    /**
	 * 응답자결과(설문조사)를(을) 등록한다.
	 * @param qqustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @throws Exception
	 */
	public void insertQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception{
		insert("QustnrRespondInfo.insertQustnrRespondInfo", qustnrRespondInfoVO);
	}

    /**
	 * 응답자결과(설문조사)를(을) 수정한다.
	 * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @throws Exception
	 */
	public void updateQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception{
		insert("QustnrRespondInfo.updateQustnrRespondInfo", qustnrRespondInfoVO);
	}

    /**
	 * 응답자결과(설문조사)를(을) 삭제한다.
	 * @param qustnrRespondInfoVO - 응답자결과(설문조사) 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception{
		insert("QustnrRespondInfo.deleteQustnrRespondInfo", qustnrRespondInfoVO);
	}

    /**
     * 설문템플릿 화이트리스트를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    public List<EgovMap> selectQustnrTmplatWhiteList() throws Exception {
        return selectList("QustnrRespondInfo.selectQustnrTmplatWhiteList");
    }
}
