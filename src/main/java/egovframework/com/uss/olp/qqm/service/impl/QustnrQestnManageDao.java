package egovframework.com.uss.olp.qqm.service.impl;

import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qqm.service.QustnrQestnManageVO;

/**
 * 설문문항을 처리하는 Dao Class 구현
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
 *   2017.07.17  김예영          표준프레임워크 v3.7 개선(select->selectOne수정)
 *
 * </pre>
 */
@Repository("qustnrQestnManageDao")
public class QustnrQestnManageDao {

	@Resource(name = "qustnrQestnManageMapper")
	private QustnrQestnManageMapper qustnrQestnManageMapper;

	/**
	 * 설문조사 응답자답변내용결과/기타답변내용결과 통계를 조회한다.
	 * @param map - 설문지 정보가 담김 Parameter
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrManageStatistics2(Map<?, ?> map) throws Exception {
		return qustnrQestnManageMapper.selectQustnrManageStatistics2(map);
	}

	/**
	 * 설문조사 통계를 조회한다.
	 * @param map - 설문지 정보가 담김 Parameter
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrManageStatistics(Map<?, ?> map) throws Exception {
		return qustnrQestnManageMapper.selectQustnrManageStatistics(map);
	}

	/**
	 * 설문지정보 설문제목을 조회한다.
	 * @param map - 설문지 정보가 담김 Parameter
	 * @return Map
	 * @throws Exception
	 */
	public Map<?, ?> selectQustnrManageQestnrSj(Map<?, ?> map) throws Exception {
		return qustnrQestnManageMapper.selectQustnrManageQestnrSj(map);
	}

	/**
	 * 설문문항 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectQustnrQestnManageList(ComDefaultVO searchVO) throws Exception {
		return qustnrQestnManageMapper.selectQustnrQestnManage(searchVO);
	}

	/**
	 * 설문문항를(을) 상세조회 한다.
	 * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<EgovMap> selectQustnrQestnManageDetail(QustnrQestnManageVO qustnrQestnManageVO) throws Exception {
		return qustnrQestnManageMapper.selectQustnrQestnManageDetail(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectQustnrQestnManageListCnt(ComDefaultVO searchVO) throws Exception {
		return qustnrQestnManageMapper.selectQustnrQestnManageCnt(searchVO);
	}

	/**
	 * 설문문항를(을) 등록한다.
	 * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
	 * @throws Exception
	 */
	public void insertQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception {
		qustnrQestnManageMapper.insertQustnrQestnManage(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 수정한다.
	 * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
	 * @throws Exception
	 */
	public void updateQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception {
		qustnrQestnManageMapper.updateQustnrQestnManage(qustnrQestnManageVO);
	}

	/**
	 * 설문문항를(을) 삭제한다.
	 * @param qustnrQestnManageVO - 설문문항 정보 담김 VO
	 * @throws Exception
	 */
	public void deleteQustnrQestnManage(QustnrQestnManageVO qustnrQestnManageVO) throws Exception {
		//설문조사(설문결과) 삭제
		qustnrQestnManageMapper.deleteQustnrRespondInfo(qustnrQestnManageVO);
		//설문항목 삭제
		qustnrQestnManageMapper.deleteQustnrItemManage(qustnrQestnManageVO);
		//설문문항
		qustnrQestnManageMapper.deleteQustnrQestnManage(qustnrQestnManageVO);
	}
}
