package egovframework.com.uss.olp.qmc.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qmc.service.EgovQustnrManageService;
import egovframework.com.uss.olp.qmc.service.QustnrManageVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
/**
 * 설문관리를 처리하는 ServiceImpl Class 구현
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
@Service("egovQustnrManageService")
public class EgovQustnrManageServiceImpl extends EgovAbstractServiceImpl implements EgovQustnrManageService{

	//final private Log log = LogFactory.getLog(this.getClass());

	@Resource(name="qustnrManageDao")
	private QustnrManageDao dao;

	@Resource(name="egovQustnrManageIdGnrService")
	private EgovIdGnrService idgenService;


    /**
	 * 설문템플릿 목록을 조회한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrTmplatManageList(QustnrManageVO qustnrManageVO) throws Exception{
		return dao.selectQustnrTmplatManageList(qustnrManageVO);
	}


    /**
	 * 설문관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrManageList(ComDefaultVO searchVO) throws Exception{
		return dao.selectQustnrManageList(searchVO);
	}

    /**
	 * 설문관리를 상세조회(Model) 한다.
	 * @param qustnrManageVO - 설문관리 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
    @Override
	public QustnrManageVO selectQustnrManageDetailModel(QustnrManageVO qustnrManageVO) throws Exception {
        return dao.selectQustnrManageDetailModel(qustnrManageVO);
    }

    /**
	 * 설문관리를(을) 상세조회 한다.
	 * @param QustnrManage - 회정정보가 담김 VO
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectQustnrManageDetail(QustnrManageVO qustnrManageVO) throws Exception{
		return dao.selectQustnrManageDetail(qustnrManageVO);
	}

    /**
	 * 설문관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectQustnrManageListCnt(ComDefaultVO searchVO) throws Exception{
		return dao.selectQustnrManageListCnt(searchVO);
	}

    /**
	 * 설문관리를(을) 등록한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void insertQustnrManage(QustnrManageVO qustnrManageVO) throws Exception {
		String sMakeId = idgenService.getNextStringId();

		qustnrManageVO.setQestnrId(sMakeId);

		dao.insertQustnrManage(qustnrManageVO);
	}

    /**
	 * 설문관리를(을) 수정한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void updateQustnrManage(QustnrManageVO qustnrManageVO) throws Exception{
		dao.updateQustnrManage(qustnrManageVO);
	}

    /**
	 * 설문관리를(을) 삭제한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @throws Exception
	 */
	@Override
	public void deleteQustnrManage(QustnrManageVO qustnrManageVO) throws Exception{
		dao.deleteQustnrManage(qustnrManageVO);
	}
}
