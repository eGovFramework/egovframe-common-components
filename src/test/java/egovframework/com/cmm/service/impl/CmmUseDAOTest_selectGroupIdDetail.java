package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { CmmUseDAOConfigurationTest.class })
public class CmmUseDAOTest_selectGroupIdDetail extends EgovTestV1 {

	@Autowired
	private CmmUseDAO cmmUseDAO;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setTableNm("COMTNAUTHORGROUPINFO");
		vo.setHaveDetailCondition("Y");
		vo.setDetailCondition("GROUP_00000000000000");

		// when
		List<CmmnDetailCode> results = cmmUseDAO.selectGroupIdDetail(vo);

		// then
		assertEquals(results.get(0).getCodeId(), vo.getTableNm());

		log.debug("results={}", results);

		results.forEach(result -> {
			log.debug("result={}", result);
			log.debug("getCodeId={}", result.getCodeId());
			log.debug("getCode={}", result.getCode());
			log.debug("getCodeNm={}", result.getCodeNm());
			log.debug("getCodeDc={}", result.getCodeDc());
		});
	}

}