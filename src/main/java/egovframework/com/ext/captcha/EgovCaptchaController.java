package egovframework.com.ext.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.annotation.IncludedInfo;

/**
 * @Class Name : EgovCaptchaController.java
 * @Description : Captcha 처리를 위한 컨트롤러
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------      -------     -------------------
 *    2024.10.29.  권태성         최초 생성
 *
 */
@Controller
public class EgovCaptchaController {

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Captcha 사용자 입력 페이지
	 * @param session
	 * @param model
	 * @return
	 */
	@IncludedInfo(name="Captcha", order = 3300, gid = 100)
	@RequestMapping("/ext/captcha/input.do")
	public String input(HttpSession session, ModelMap model) {
		return "egovframework/com/ext/captcha/EgovCaptcha";
	}

	/**
	 * Captcha 입력값 검증 결과 페이지
	 * @param session
	 * @param model
	 * @param captcha
	 * @param pgNm
	 * @return
	 */
	@PostMapping("/ext/captcha/result.do")
	public String result(HttpSession session, ModelMap model,
			@RequestParam("captcha") String captcha,
			@RequestParam("pgNm") String pgNm) {
		String expectedCaptcha = (String) session.getAttribute("captcha" + pgNm);
		boolean result = (expectedCaptcha != null && expectedCaptcha.equalsIgnoreCase(captcha));
		if (result) {
			model.addAttribute("message", "Captcha 값이 올바르게 입력되었습니다.");
		} else {
			model.addAttribute("message", "Captcha 값이 올바르지 않습니다.");
		}
		model.addAttribute("result", result);
		return "egovframework/com/ext/captcha/EgovCaptchaResult";
	}

	/**
	 * Captcha 이미지 생성
	 * 
	 * @param request
	 * @param response
	 * @param width 이미지 가로 크기
	 * @param height 이미지 세로 크기
	 * @param length Captcha 문자 길이
	 * @param pgNm Captcha를 사용하는 프로그램 구분값
	 */
	@GetMapping("/ext/captcha/generate.do")
	public void generate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "width", defaultValue = "150") int width,
			@RequestParam(value = "height", defaultValue = "50") int height,
			@RequestParam(value = "lenght", defaultValue = "5") int length,
			@RequestParam(value = "pgNm", defaultValue = "capt") String pgNm
			) {
		try {
			String captchaTxt = generateRandomText(length);
			request.getSession().setAttribute("captcha" + pgNm, captchaTxt);

			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bufferedImage.createGraphics();

			g2d.setColor(Color.white);
			g2d.fillRect(0, 0, width, height);

			g2d.setFont(new Font("Arial", Font.BOLD, 40));
			g2d.setColor(Color.BLACK);

			g2d.drawString(captchaTxt, 10, 35);
			g2d.dispose();
			response.setContentType("image/png");
			ImageIO.write(bufferedImage, "png", response.getOutputStream());
		} catch (IOException e) {
			response.setStatus(500);
			logger.error("Captcha generate error", e);
		}
	}

	/**
	 * 임의의 문자열을 인자로 받은 길이 만큼 생성
	 * 
	 * @param length 생성하려는 문자열 길이
	 * @return length 인자의 길이만큼 생성된 임의의 문자열
	 */
	private String generateRandomText(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}

}
