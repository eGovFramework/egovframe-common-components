package egovframework.com.uss.olp.qtm.service.impl;

import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;

/**
 * 설문템플릿 Dao Class 구현
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
 *   2017.06.21  김예영          표준프레임워크 v3.7개선 (select->selectOne으로 수정)
 *
 * </pre>
 */
@Repository("qustnrTmplatManageDao")
public class QustnrTmplatManageDao {

	@Resource(name = "qustnrTmplatManageMapper")
	private QustnrTmplatManageMapper qustnrTmplatManageMapper;

	/**
	 * 템플릿파일명을 조회한다.
	 * @param qustnrTmplatManageVO - 조회할 정보가 담긴 VO
	 * @return Map
	 */
	public Map<?, ?> selectQustnrTmplatManageTmplatImagepathnm(QustnrTmplatManageVO qustnrTmplatManageVO) {
		return qustnrTmplatManageMapper.selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO);
	}

	/**
	 * 설문템플릿 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 */
	public List<EgovMap> selectQustnrTmplatManageList(ComDefaultVO searchVO) {
		return qustnrTmplatManageMapper.selectQustnrTmplatManage(searchVO);
	}

	/**
	 * 설문템플릿를(을) 상세조회 한다.
	 * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
	 * @return List
	 */
	public List<EgovMap> selectQustnrTmplatManageDetail(QustnrTmplatManageVO qustnrTmplatManageVO) {
		return qustnrTmplatManageMapper.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
	}

	/**
	 * 설문템플릿를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 */
	public int selectQustnrTmplatManageListCnt(ComDefaultVO searchVO) {
		return qustnrTmplatManageMapper.selectQustnrTmplatManageCnt(searchVO);
	}

	/**
	 * 설문템플릿를(을) 등록한다.
	 * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
	 */
	public void insertQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO) {
		qustnrTmplatManageMapper.insertQustnrTmplatManage(qustnrTmplatManageVO);
	}

	/**
	 * 설문템플릿를(을) 수정한다.
	 * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
	 */
	public void updateQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO) {
		qustnrTmplatManageMapper.updateQustnrTmplatManage(qustnrTmplatManageVO);
	}

	/**
	 * 설문템플릿를(을) 삭제한다.
	 * @param qustnrTmplatManageVO - 설문템플릿 정보 담김 VO
	 */
	public void deleteQustnrTmplatManage(QustnrTmplatManageVO qustnrTmplatManageVO) {
		//설문응답자 삭제
		qustnrTmplatManageMapper.deleteQustnrRespondManage(qustnrTmplatManageVO);
		//설문조사(설문결과) 삭제
		qustnrTmplatManageMapper.deleteQustnrRespondInfo(qustnrTmplatManageVO);
		//설문항목 삭제
		qustnrTmplatManageMapper.deleteQustnrItemManage(qustnrTmplatManageVO);
		//설문문항 삭제
		qustnrTmplatManageMapper.deleteQustnrQestnManage(qustnrTmplatManageVO);
		//설문관리 삭제
		qustnrTmplatManageMapper.deleteQustnrManage(qustnrTmplatManageVO);
		//설문템플릿삭제
		qustnrTmplatManageMapper.deleteQustnrTmplatManage(qustnrTmplatManageVO);
	}
}
