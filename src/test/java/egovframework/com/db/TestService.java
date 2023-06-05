package egovframework.com.db;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.04.23  신용호          최초 생성
 *
 * @ 특징
   - 사용자 지원 도움말 DB Insert 테스트
   
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
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
	public void test() throws Exception {
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
	public void insertHpcmCn() throws Exception {

		HpcmVO hpcmVO = new HpcmVO();
		hpcmVO.setHpcmDf("테스트 title -5");
		hpcmVO.setHpcmDc("테스트 content -5");
		hpcmVO.setHpcmSeCode("1");
    	hpcmVO.setFrstRegisterId("USRCNFRM_00000000000");		// 최초등록자ID
    	hpcmVO.setLastUpdusrId("USRCNFRM_00000000000");    	// 최종수정자ID
		
		egovHpcmService.insertHpcm(hpcmVO);
		// induce length error
		hpcmVO.setHpcmSeCode("11");
		egovHpcmService.insertHpcm(hpcmVO);
	}
	
}
