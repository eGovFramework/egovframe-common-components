package egovframework.com.cop.smt.sim.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
/**
 * 일정관리를 처리하는 Service Class 구현
 * @author 공통서비스 장동한
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  장동한          최초 생성
 *
 * </pre>
 */
public interface EgovIndvdlSchdulManageService {


    /**
	 * 메인페이지/일정관리조회
	 * @param map - 조회할 정보가 담긴 map
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectIndvdlSchdulManageMainList(Map<?, ?> map) throws Exception;

    /**
	 * 일정 목록을 Map(map)형식으로 조회한다.
	 * @param Map(map) - 조회할 정보가 담긴 Map
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectIndvdlSchdulManageRetrieve(Map<?, ?> map) throws Exception;


    /**
	 * 일정 목록을 VO(model)형식으로 조회한다.
	 * @param indvdlSchdulManageVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public IndvdlSchdulManageVO selectIndvdlSchdulManageDetailVO(IndvdlSchdulManageVO indvdlSchdulManageVO) throws Exception;

    /**
	 * 일정 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectIndvdlSchdulManageList(ComDefaultVO searchVO) throws Exception;

    /**
	 * 일정를(을) 상세조회 한다.
	 * @param indvdlSchdulManageVO - 일정 정보 담김 VO
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectIndvdlSchdulManageDetail(IndvdlSchdulManageVO indvdlSchdulManageVO) throws Exception;

    /**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @throws Exception
	 */
	public int selectIndvdlSchdulManageListCnt(ComDefaultVO searchVO) throws Exception;

    /**
	 * 일정를(을) 등록한다.
	 * @param indvdlSchdulManageVO - 일정 정보 담김 VO
	 * @throws Exception
	 */
	void  insertIndvdlSchdulManage(IndvdlSchdulManageVO indvdlSchdulManageVO) throws Exception;

    /**
	 * 일정를(을) 수정한다.
	 * @param indvdlSchdulManageVO - 일정 정보 담김 VO
	 * @throws Exception
	 */
	void  updateIndvdlSchdulManage(IndvdlSchdulManageVO indvdlSchdulManageVO) throws Exception;

    /**
	 * 일정를(을) 삭제한다.
	 * @param indvdlSchdulManageVO - 일정 정보 담김 VO
	 * @throws Exception
	 */
	void  deleteIndvdlSchdulManage(IndvdlSchdulManageVO indvdlSchdulManageVO) throws Exception;


}
