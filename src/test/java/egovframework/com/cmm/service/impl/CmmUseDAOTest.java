package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.test.EgovAbstractTestJUnit4;
import lombok.extern.slf4j.Slf4j;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스 Test
 * 
 * @author 이백행
 * @since 2025.07.15
 * @version 4.3.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.15  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j
public class CmmUseDAOTest extends EgovAbstractTestJUnit4 {

	@Autowired
	private CmmUseDAO cmmUseDAO;

	@Test
	public void test1selectCmmCodeDetail() throws BaseRuntimeException, Exception {
		// given
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setCodeId("COM001");

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getCodeId={}", comDefaultCodeVO.getCodeId());
		}

		// when
		List<CmmnDetailCode> resultList = cmmUseDAO.selectCmmCodeDetail(comDefaultCodeVO);

		// then
		assertEquals(comDefaultCodeVO.getCodeId(), resultList.get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getCodeId={}", comDefaultCodeVO.getCodeId());

			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (CmmnDetailCode result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getCodeId={}", result.getCodeId());
				log.debug("getCode={}", result.getCode());
				log.debug("getCodeNm={}", result.getCodeNm());
				log.debug("getCodeDc={}", result.getCodeDc());
			}
		}
	}

	@Test
	public void test2selectOgrnztIdDetail() throws BaseRuntimeException, Exception {
		// given
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
		comDefaultCodeVO.setHaveDetailCondition("Y");
		comDefaultCodeVO.setDetailCondition("ORGNZT_0000000000000");

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());
		}

		// when
		List<CmmnDetailCode> resultList = cmmUseDAO.selectOgrnztIdDetail(comDefaultCodeVO);

		// then
		assertEquals(comDefaultCodeVO.getTableNm(), resultList.get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());

			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (CmmnDetailCode result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getCodeId={}", result.getCodeId());
				log.debug("getCode={}", result.getCode());
				log.debug("getCodeNm={}", result.getCodeNm());
				log.debug("getCodeDc={}", result.getCodeDc());
			}
		}
	}

	@Test
	public void test3selectGroupIdDetail() throws BaseRuntimeException, Exception {
		// given
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setTableNm("COMTNAUTHORGROUPINFO");
		comDefaultCodeVO.setHaveDetailCondition("Y");
		comDefaultCodeVO.setDetailCondition("GROUP_00000000000000");

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());
		}

		// when
		List<CmmnDetailCode> resultList = cmmUseDAO.selectGroupIdDetail(comDefaultCodeVO);

		// then
		assertEquals(comDefaultCodeVO.getTableNm(), resultList.get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());

			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (CmmnDetailCode result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getCodeId={}", result.getCodeId());
				log.debug("getCode={}", result.getCode());
				log.debug("getCodeNm={}", result.getCodeNm());
				log.debug("getCodeDc={}", result.getCodeDc());
			}
		}
	}

}