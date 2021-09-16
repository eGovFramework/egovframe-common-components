package egovframework.com.cop.bbs.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.test.EgovTestV1;
import egovframework.rte.fdl.string.EgovDateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration(classes = { EgovBBSMasterServiceImplTestConfiguration.class })
public class EgovBBSMasterServiceImplTest_selectNotUsedBdMstrList extends EgovTestV1 {

	@Resource(name = "EgovBBSMasterService")
	private EgovBBSMasterService egovBBSMasterService;

//	@Resource(name = "egovBBSMstrIdGnrService")
//	private EgovIdGnrService egovBBSMstrIdGnrService;

//	@Resource(name = "egovNttIdGnrService")
//	private EgovIdGnrService egovNttIdGnrService;

//	@Resource(name = "egovBlogIdGnrService")
//	private EgovIdGnrService egovBlogIdGnrService;

	// given
	String today;
	LoginVO authenticatedUser;

	BoardMasterVO boardMasterVO;

	// when
	Map<String, Object> notUsedBdMstrList;

	@Test
	public void test() {
		log.debug("test");
		given();
		when();
		then();
	}

	void given() {
		today = " " + EgovDateUtil.toString(new Date(), null, null);
		authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		boardMasterVO = new BoardMasterVO();
	}

	void when() {
		notUsedBdMstrList = egovBBSMasterService.selectNotUsedBdMstrList(boardMasterVO);
	}

	void then() {
		assertEquals(notUsedBdMstrList, null);
	}

}