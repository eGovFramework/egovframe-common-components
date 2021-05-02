package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.test.EgovTestV1;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ContextConfiguration(classes = { EgovCmmUseServiceImplTest_selectCmmCodeDetail.class })

//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
@ActiveProfiles({ "mysql", "dummy" })
//@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })

@Configuration

@ImportResource({

//	"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",
		"classpath*:/egovframework/spring/com/context-mapper.xml",
		"classpath*:/egovframework/spring/com/context-transaction.xml",

		"classpath*:/egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.cmm.service.impl" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovCmmUseService.class, CmmUseDAO.class }) })

public class EgovCmmUseServiceImplTest_selectCmmCodeDetail extends EgovTestV1 {

	@Autowired
	private EgovCmmUseService egovCmmUseService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM001");

		// when
		List<CmmnDetailCode> results = egovCmmUseService.selectCmmCodeDetail(vo);

		// then
		assertEquals(results.get(0).getCodeId(), vo.getCodeId());

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
