package egovframework.com.uss.olp.qri.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qri.service.EgovQustnrRespondInfoService;
import egovframework.com.uss.olp.qri.service.QustnrRespondInfoVO;

/**
 * 설문조사 ServiceImpl Class 구현
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
@Service("egovQustnrRespondInfoService")
public class EgovQustnrRespondInfoServiceImpl extends EgovAbstractServiceImpl implements EgovQustnrRespondInfoService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="qustnrRespondInfoDao")
	private QustnrRespondInfoDao dao;

	@Resource(name="qustnrRespondInfoIdGnrService")
	private EgovIdGnrService idgenService;


    /**
	 * 설문템플릿을 조회한다.
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrTmplatManage(Map<?, ?> map) throws Exception{
		return dao.selectQustnrTmplatManage(map);
	}

	/**
	 * 객관식 통계를 조회 조회한다.
	 *
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<EgovMap> selectQustnrRespondInfoManageStatistics1(Map<?, ?> map) throws Exception {
		return dao.selectQustnrRespondInfoManageStatistics1(map);
	}

	/**
     * 주관식 통계를 조회 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoManageStatistics2(Map<?, ?> map) throws Exception {
        return dao.selectQustnrRespondInfoManageStatistics2(map);
    }

    /**
	 * 회원정보를 조회한다.
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	@Override
	public Map<?, ?> selectQustnrRespondInfoManageEmplyrinfo(Map<?, ?> map) throws Exception{
		return dao.selectQustnrRespondInfoManageEmplyrinfo(map);
	}

    /**
     * 설문정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoManageComtnqestnrinfo(Map<?, ?> map) throws Exception {
        return dao.selectQustnrRespondInfoManageComtnqestnrinfo(map);
    }

    /**
     * 문항정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoManageComtnqustnrqesitm(Map<?, ?> map) throws Exception {
        return dao.selectQustnrRespondInfoManageComtnqustnrqesitm(map);
    }

    /**
     * 항목정보를 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoManageComtnqustnriem(Map<?, ?> map) throws Exception {
        return dao.selectQustnrRespondInfoManageComtnqustnriem(map);
    }

    /**
     * 설문조사(설문등록)를(을) 목록을 조회한다.
     *
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoManageList(ComDefaultVO searchVO){
        return dao.selectQustnrRespondInfoManageList(searchVO);
    }

    /**
	 * 설문조사(설문등록)를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectQustnrRespondInfoManageListCnt(ComDefaultVO searchVO) throws Exception{
		return dao.selectQustnrRespondInfoManageListCnt(searchVO);
	}

    /**
     * 응답자결과(설문조사) 목록을 조회한다.
     *
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoList(ComDefaultVO searchVO) throws Exception {
        return dao.selectQustnrRespondInfoList(searchVO);
    }

    /**
     * 응답자결과(설문조사)를(을) 상세조회 한다.
     *
     * @param QustnrRespondInfo - 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrRespondInfoDetail(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception {
        return dao.selectQustnrRespondInfoDetail(qustnrRespondInfoVO);
    }

    /**
	 * 응답자결과(설문조사)를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectQustnrRespondInfoListCnt(ComDefaultVO searchVO) throws Exception{
		return dao.selectQustnrRespondInfoListCnt(searchVO);
	}

    /**
	 * 응답자결과(설문조사)를(을) 등록한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();

		qustnrRespondInfoVO.setQestnrQesrspnsId(sMakeId);

		dao.insertQustnrRespondInfo(qustnrRespondInfoVO);
	}

    /**
	 * 응답자결과(설문조사)를(을) 수정한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void updateQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception{
		dao.updateQustnrRespondInfo(qustnrRespondInfoVO);
	}

    /**
	 * 응답자결과(설문조사)를(을) 삭제한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteQustnrRespondInfo(QustnrRespondInfoVO qustnrRespondInfoVO) throws Exception{
		dao.deleteQustnrRespondInfo(qustnrRespondInfoVO);
	}

    /**
     * 설문템플릿을 조회한다.
     *
     * @param map - 조회할 정보가 담긴 map
     * @return List
     * @throws Exception
     */
    @Override
    public List<EgovMap> selectQustnrTmplatWhiteList() throws Exception {
        return dao.selectQustnrTmplatWhiteList();
    }

}
