package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovCmmUseServiceImplConfigurationTest.class })
public class EgovCmmUseServiceImplTest_selectCmmCodeDetails extends EgovTestV1 {

	@Autowired
	private EgovCmmUseService egovCmmUseService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		List<ComDefaultCodeVO> voList = new ArrayList<>();
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM001");
		voList.add(vo);

		vo = new ComDefaultCodeVO();
		vo.setCodeId("COM002");
		voList.add(vo);

		// when
		Map<String, List<CmmnDetailCode>> result = egovCmmUseService.selectCmmCodeDetails(voList);

		// then
		assertEquals(result.get(voList.get(0).getCodeId()).get(0).getCodeId(), voList.get(0).getCodeId());

		log.debug("result={}", result);
		
		result.get(voList.get(0).getCodeId()).forEach(action -> {
			log.debug("action={}", action);
			log.debug("getCodeId={}", action.getCodeId());
			log.debug("getCode={}", action.getCode());
			log.debug("getCodeNm={}", action.getCodeNm());
			log.debug("getCodeDc={}", action.getCodeDc());
		});

		result.get(voList.get(1).getCodeId()).forEach(action -> {
			log.debug("action={}", action);
			log.debug("getCodeId={}", action.getCodeId());
			log.debug("getCode={}", action.getCode());
			log.debug("getCodeNm={}", action.getCodeNm());
			log.debug("getCodeDc={}", action.getCodeDc());
		});
	}

}