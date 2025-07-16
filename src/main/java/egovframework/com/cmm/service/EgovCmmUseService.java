package egovframework.com.cmm.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;

/**
 *
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기 위한 서비스 인터페이스
 * 
 * @author 공통서비스 개발팀 이삼섭
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이삼섭          최초 생성
 *
 *      </pre>
 */
public interface EgovCmmUseService {

	/**
	 * 공통코드를 조회한다.
	 *
	 * @param comDefaultCodeVO
	 * @return List(코드)
	 */
	public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO comDefaultCodeVO);

	/**
	 * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
	 *
	 * @param comDefaultCodeVOs
	 * @return Map(코드)
	 */
	public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<ComDefaultCodeVO> comDefaultCodeVOs);

	/**
	 * 조직정보를 코드형태로 리턴한다.
	 *
	 * @param 조회조건정보 vo
	 * @return 조직정보 List
	 */
	public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO comDefaultCodeVO);

	/**
	 * 그룹정보를 코드형태로 리턴한다.
	 *
	 * @param 조회조건정보 vo
	 * @return 그룹정보 List
	 */
	public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO comDefaultCodeVO);
}
