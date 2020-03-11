package egovframework.com.uss.olp.qqm.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qqm.service.EgovQustnrQestnManageService;
import egovframework.com.uss.olp.qqm.service.QustnrQestnManageVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * 설문문항을 처리하는 ServiceImpl Class 구현
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
@Service("egovQustnrQestnManageService")
public class EgovQustnrQestnManageServiceImpl extends EgovAbstractServiceImpl implements EgovQustnrQestnManageService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="qustnrQestnManageDao")
	private QustnrQestnManageDao dao;

	@Resource(name="egovQustnrQestnManageIdGnrService")
	private EgovIdGnrService idgenService;


    /**
	 * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
	 * @param Map - 설문지 정보가 담김 Parameter
	 * @return Map
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrManageStatistics2(Map<?, ?> map) throws Exception{
		return dao.selectQustnrManageStatistics2(map);
	}

    /**
	 * 설문조사 통계를 조회한다.
	 * @param Map - 설문지 정보가 담김 Parameter
	 * @return Map
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrManageStatistics(Map<?, ?> map) throws Exception{
		return dao.selectQustnrManageStatistics(map);
	}
    /**
	 * 설문지정보 설문제목을 조회한다.
	 * @param Map - 설문지 정보가 담김 Parameter
	 * @return Map
	 * @throws Exception
	 */
	@Override
	public Map<?, ?> selectQustnrManageQestnrSj(Map<?, ?> map) throws Exception{
		return dao.selectQustnrManageQestnrSj(map);
	}

    /**
	 * 설문문항 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrQestnManageList(ComDefaultVO searchVO) throws Exception{
		return dao.selectQustnrQestnManageList(searchVO);
	}

    /**
	 * 설문문항를(을) 상세조회 한다.
	 * @param QustnrQestnManage - 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrQestnManageDetail(QustnrQestnManageVO qustnrQestnManageVO) throws Exception{
		return dao.selectQustnrQestnManageDetail(qustnrQestnManageVO);
	}

    /**
	 * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectQustnrQestnManageListCnt(ComDefaultVO searchVO) throws Exception{
		return dao.selectQustnrQestnManageListCnt(searchVO);
	}

    /**
	 * 설문문항를(을) 등록한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();

		qustnrQestnManageVO.setQestnrQesitmId(sMakeId);

		dao.insertQustnrQestnManage(qustnrQestnManageVO);
	}

    /**
	 * 설문문항를(을) 수정한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void updateQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception{
		dao.updateQustnrQestnManage(qustnrQestnManageVO);
	}

    /**
	 * 설문문항를(을) 삭제한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception{
		dao.deleteQustnrQestnManage(qustnrQestnManageVO);
	}
}
