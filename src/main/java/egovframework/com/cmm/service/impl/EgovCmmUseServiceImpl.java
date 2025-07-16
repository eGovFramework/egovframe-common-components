package egovframework.com.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import lombok.RequiredArgsConstructor;

/**
 * @Class Name : EgovCmmUseServiceImpl.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * @Modification Information
 * 
 *               <pre>
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.     이삼섭
 *    2024.10.29.	LeeBaekHaeng	@Override 표기
 *               </pre>
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Service("EgovCmmUseService")
@RequiredArgsConstructor
public class EgovCmmUseServiceImpl extends EgovAbstractServiceImpl implements EgovCmmUseService {

	private final CmmUseDAO cmmUseDAO;

	/**
	 * 공통코드를 조회한다.
	 *
	 * @param comDefaultCodeVO
	 * @return
	 */
	@Override
	public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO comDefaultCodeVO) {
		return cmmUseDAO.selectCmmCodeDetail(comDefaultCodeVO);
	}

	/**
	 * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
	 *
	 * @param comDefaultCodeVOs
	 * @return
	 */
	@Override
	public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<ComDefaultCodeVO> comDefaultCodeVOs) {
		Map<String, List<CmmnDetailCode>> map = new HashMap<>();
		for (ComDefaultCodeVO comDefaultCodeVO : comDefaultCodeVOs) {
			map.put(comDefaultCodeVO.getCodeId(), cmmUseDAO.selectCmmCodeDetail(comDefaultCodeVO));
		}
		return map;
	}

	/**
	 * 조직정보를 코드형태로 리턴한다.
	 *
	 * @param 조회조건정보 vo
	 * @return 조직정보 List
	 */
	@Override
	public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
		return cmmUseDAO.selectOgrnztIdDetail(comDefaultCodeVO);
	}

	/**
	 * 그룹정보를 코드형태로 리턴한다.
	 *
	 * @param 조회조건정보 vo
	 * @return 그룹정보 List
	 */
	@Override
	public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO comDefaultCodeVO) {
		return cmmUseDAO.selectGroupIdDetail(comDefaultCodeVO);
	}
}
