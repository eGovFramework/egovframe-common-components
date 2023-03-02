package egovframework.com.sec.rnc.mip.mva.sp.qrmpm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sec.rnc.mip.mva.sp.comm.service.MipDidVpService;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.qrmpm.web
 * @FileName    : QrmpmViewController.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 3.
 * @Description : QR-MPM 페이지 이동 Controller
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 3.    Min Gi Ju        최초생성
 */
@Controller
@RequestMapping("/qrmpm")
public class QrmpmViewController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QrmpmController.class);
	
	/** MipDidVpService */
	private final MipDidVpService mipDidVpService;
	
	/**
	 * 생성자
	 * 
	 * @param mipDidVpService MipDidVpService
	 */
	public QrmpmViewController(MipDidVpService mipDidVpService) {
		this.mipDidVpService = mipDidVpService;
	}

	/**
	 * QR-MPM 테스트 페이지로 이동
	 * 
	 * @MethodName : qrmpmView
	 * @return 페이지 URL
	 */
	@IncludedInfo(name = "모바일운전면허증", listUrl = "/qrmpm/qrmpmView.do", order = 110, gid = 20)
	@GetMapping("/qrmpmView.do")
	public String qrmpmView() {
		LOGGER.debug("Mip SP Service 시작!");
		
		try {
			mipDidVpService.apiInit();
		} catch (Exception e) {
			LOGGER.error("[OMN] API Init Error - Check Log", e);
		}
		
		return "egovframework/com/sec/rnc/mip/qrmpm/qrmpmView";
	}

}
