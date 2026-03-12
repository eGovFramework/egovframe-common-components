package egovframework.com.db;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import jakarta.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.uss.olh.hpc.service.EgovHpcmService;
import egovframework.com.uss.olh.hpc.service.HpcmVO;

/**
 * @Transactional Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.23
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일        수정자      수정내용
 *  ----------  --------  ---------------------------
 *  2019.04.23  신용호      최초 생성
 *  2026.01.26  신용호      JUnit 4 => JUnit 5 마이그레이션
 *
 * @ 특징
   - 사용자 지원 도움말 DB Insert 테스트
   
 * </pre>
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { 
		"file:src/main/resources/egovframework/spring/com/context-*.xml",
		"file:src/main/resources/egovframework/spring/com/idgn/context-*.xml"
})

@WebAppConfiguration
@ActiveProfiles({"mysql","session"})
public class TestService {

	@Resource(name = "EgovHpcmService")
    private EgovHpcmService egovHpcmService;
	
	@Test
	void test() throws Exception {
		//fail("Not yet implemented");
		//mockMvc.perform(MockMvcRequestBuilders.get("/cmm/main/mainPage.do"));
		//mockMvc.perform(MockMvcRequestBuilders.get("/cmm/main/mainPage.do"));
		
		System.out.println("start test~~~");
		HpcmVO searchVO = new HpcmVO();
		searchVO.setFirstIndex(0);
		List<HpcmVO> HpcmList = egovHpcmService.selectHpcmList(searchVO);
		System.out.println("====> count = "+HpcmList.size());
	}

	// 도움말 Insert 테스트
	@Test
	@Transactional
	void insertHpcmCn() throws Exception {

		HpcmVO hpcmVO = new HpcmVO();
		hpcmVO.setHpcmDf("테스트 title -5");
		hpcmVO.setHpcmDc("테스트 content -5");
		hpcmVO.setHpcmSeCode("1");
    	hpcmVO.setFrstRegisterId("USRCNFRM_00000000000");		// 최초등록자ID
    	hpcmVO.setLastUpdusrId("USRCNFRM_00000000000");    	// 최종수정자ID
		
		// 정상적인 1자리 코드로 Insert 성공
		egovHpcmService.insertHpcm(hpcmVO);
		
		// induce length error: HPCM_SE_CODE는 char(1)이므로 2자리 문자열은 오류 발생해야 함
		hpcmVO.setHpcmSeCode("11");
		assertThrows(DataIntegrityViolationException.class, () -> {
			egovHpcmService.insertHpcm(hpcmVO);
		}, "HPCM_SE_CODE 컬럼은 char(1)이므로 2자리 이상의 값은 DataIntegrityViolationException이 발생해야 합니다.");
	}
	
}
