package egovframework.com.uat.uia.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.impl.FileManageDAO;
import egovframework.com.cop.ems.service.EgovMultiPartEmail;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;
import egovframework.com.cop.ems.service.EgovSndngMailService;
import egovframework.com.cop.ems.service.impl.SndngMailRegistDAO;
import egovframework.com.test.EgovTestAbstractDAO;
import egovframework.com.uat.uia.service.EgovLoginService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [10. 로그인][EgovLoginServiceImpl.actionLoginByEsntlId] ServiceImpl 단위 테스트
 * 
 * @author 이백행
 * @since 2024-09-24
 *
 */
@ContextConfiguration(classes = { EgovLoginServiceImplTestactionLoginByEsntlIdTest.class, EgovTestAbstractDAO.class })
@Configuration
@ImportResource({ "classpath*:egovframework/spring/com/idgn/context-idgn-MailMsg.xml", })
@ComponentScan(useDefaultFilters = false, basePackages = { "egovframework.com.uat.uia.service.impl",
		"egovframework.com.cmm.config", "egovframework.com.cop.ems.service",
		"egovframework.com.cmm.service.impl", }, includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { EgovLoginServiceImpl.class, LoginDAO.class,
						EgovLoginConfig.class,

						EgovSndngMailRegistService.class, SndngMailRegistDAO.class,

						EgovSndngMailService.class, EgovMultiPartEmail.class,

						EgovFileMngService.class, FileManageDAO.class,

				}) })
@NoArgsConstructor
@Slf4j
public class EgovLoginServiceImplTestactionLoginByEsntlIdTest extends EgovTestAbstractDAO {

	/**
	 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
	 */
	@Autowired
	private EgovLoginService egovLoginService;

	/**
	 * 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("EsntlId를 이용한 로그인을 처리한다");
		}

		// given
		final LoginVO loginVO = new LoginVO();
		loginVO.setUserSe("GNR"); // 일반회원
//		vo.setUserSe("ENT"); // 기업회원
//		vo.setUserSe("USR"); // 업무사용자

		// `ESNTL_ID` char(20) NOT NULL COMMENT '고유ID',
		loginVO.setUniqId("USRCNFRM_00000000001");

		// when
		final LoginVO result = egovLoginService.actionLoginByEsntlId(loginVO);

		// then
		debug(result);
		asserts(loginVO, result);
	}

	private void debug(final LoginVO result) {
		if (log.isDebugEnabled()) {
			log.debug("result={}", result);
			log.debug("getId={}", result.getId());
			log.debug("getName={}", result.getName());
			log.debug("getIhidNum={}", result.getIhidNum());
			log.debug("getPassword={}", result.getPassword());
			log.debug("getEmail={}", result.getEmail());
			log.debug("getOrgnztId={}", result.getOrgnztId());
			log.debug("getUniqId={}", result.getUniqId());

			log.debug("assert");
			log.debug("getUserSe={}", result.getUserSe());
			log.debug("getUniqId={}", result.getUniqId());
		}
	}

	private void asserts(final LoginVO loginVO, final LoginVO result) {
		assertEquals("getUserSe", loginVO.getUserSe(), result.getUserSe());
		assertEquals("getUniqId 고유ID", loginVO.getUniqId(), result.getUniqId());
	}

}
