package egovframework.com.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import lombok.RequiredArgsConstructor;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *   2024.10.29  이백행          @Override 표기
 *   2025.07.16  이백행          2025년 컨트리뷰션 `throws Exception` 제거
 *   2025.08.27  문종운          DB 호출을 줄이기 위한 IN절 조회로 수정
 *
 *      </pre>
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
		List<String> codeIds = comDefaultCodeVOs.stream()
			.map(ComDefaultCodeVO::getCodeId)
			.collect(Collectors.toList());

		List<CmmnDetailCode> allCodes = cmmUseDAO.selectCmmCodeDetailsByCodeIds(codeIds);

		return allCodes.stream()
			.collect(Collectors.groupingBy(CmmnDetailCode::getCodeId));
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
