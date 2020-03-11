package egovframework.com.uss.olp.qrm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olp.qrm.service.QustnrRespondManageVO;

import org.springframework.stereotype.Repository;
/**
 * 설문응답자관리 Dao Class 구현
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
 *   2017.07.19  김예영          표준프레임워크 v3.7 개선(select->selectOne수정)
 *
 * </pre>
 */
@Repository("qustnrRespondManageDao")
public class QustnrRespondManageDao extends EgovComAbstractDAO {

    /**
	 * 응답자정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrRespondManageList(ComDefaultVO searchVO) throws Exception{
		return list("QustnrRespondManage.selectQustnrRespondManage", searchVO);
	}

    /**
	 * 응답자정보를(을) 상세조회 한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrRespondManageDetail(QustnrRespondManageVO qustnrRespondManageVO) throws Exception{
		return list("QustnrRespondManage.selectQustnrRespondManageDetail", qustnrRespondManageVO);
	}

    /**
	 * 응답자정보를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrRespondManageListCnt(ComDefaultVO searchVO) throws Exception{
		return (Integer)selectOne("QustnrRespondManage.selectQustnrRespondManageCnt", searchVO);
	}

    /**
	 * 응답자정보를(을) 등록한다.
	 * @param qqustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @throws Exception
	 */
	public void insertQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) throws Exception{
		insert("QustnrRespondManage.insertQustnrRespondManage", qustnrRespondManageVO);
	}

    /**
	 * 응답자정보를(을) 수정한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @throws Exception
	 */
	public void updateQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) throws Exception{
		insert("QustnrRespondManage.updateQustnrRespondManage", qustnrRespondManageVO);
	}

    /**
	 * 응답자정보를(을) 삭제한다.
	 * @param qustnrRespondManageVO - 응답자정보 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteQustnrRespondManage(QustnrRespondManageVO qustnrRespondManageVO) throws Exception{
		insert("QustnrRespondManage.deleteQustnrRespondManage", qustnrRespondManageVO);
	}
}
