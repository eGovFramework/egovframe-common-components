package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovTestV1;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovFileMngServiceImplConfigurationTest.class })
public class EgovFileMngServiceImplTest_insertFileInf extends EgovTestV1 {

	@Autowired
	private EgovFileMngService egovFileMngService;

	@Autowired
	@Qualifier("egovFileIdGnrService")
	private EgovIdGnrService egovFileIdGnrService;

	@Test
	public void test() throws Exception {
		log.debug("test");

		// given
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(egovFileIdGnrService.getNextStringId());
		int maxFileSN = egovFileMngService.getMaxFileSN(fvo);
		log.debug("maxFileSN={}", maxFileSN);

		FileVO vo = new FileVO();
		vo.setAtchFileId(fvo.getAtchFileId());
		vo.setFileSn(String.valueOf(maxFileSN++));
		vo.setFileMg(vo.getFileSn());

		// when
		boolean result = false;
		try {
			egovFileMngService.insertFileInf(vo);
			result = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		// then
		assertEquals(result, true);

		log.debug("result={}", result);
	}

}