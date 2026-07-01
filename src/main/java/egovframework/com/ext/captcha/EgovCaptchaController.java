package egovframework.com.ext.captcha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.annotation.IncludedInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Captcha 처리를 위한 컨트롤러
 * 
 * @author 권태성
 * @since 2024.10.29
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2024.10.29  권태성          최초 생성
 *   2025.06.19  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-UselessParentheses(쓸모없는 괄호)
 *
 *      </pre>
 */
@Controller
public class EgovCaptchaController {

	private static final int DEFAULT_CAPTCHA_LENGTH = 5;
	private static final int MIN_CAPTCHA_LENGTH = 4;
	private static final int MAX_CAPTCHA_LENGTH = 10;
	private static final int MIN_IMAGE_WIDTH = 50;
	private static final int MAX_IMAGE_WIDTH = 500;
	private static final int MIN_IMAGE_HEIGHT = 30;
	private static final int MAX_IMAGE_HEIGHT = 200;
	private static final int MAX_PAGE_NAME_LENGTH = 50;
	private static final String CAPTCHA_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Captcha 사용자 입력 페이지
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@IncludedInfo(name = "Captcha", order = 3300, gid = 100)
	@RequestMapping("/ext/captcha/input.do")
	public String input(HttpSession session, ModelMap model) {
		return "egovframework/com/ext/captcha/EgovCaptcha";
	}

	/**
	 * Captcha 입력값 검증 결과 페이지
	 * 
	 * @param session
	 * @param model
	 * @param captcha
	 * @param pgNm
	 * @return
	 */
	@PostMapping("/ext/captcha/result.do")
	public String result(HttpSession session, ModelMap model, @RequestParam("captcha") String captcha,
			@RequestParam("pgNm") String pgNm) {
		String expectedCaptcha = (String) session.getAttribute("captcha" + pgNm);
		boolean result = expectedCaptcha != null && expectedCaptcha.equalsIgnoreCase(captcha);
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
	 * @param width    이미지 가로 크기
	 * @param height   이미지 세로 크기
	 * @param length   Captcha 문자 길이
	 * @param pgNm     Captcha를 사용하는 프로그램 구분값
	 */
	@GetMapping("/ext/captcha/generate.do")
	public void generate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "width", defaultValue = "150") int width,
			@RequestParam(value = "height", defaultValue = "50") int height,
			@RequestParam(value = "length", required = false) Integer length,
			@RequestParam(value = "lenght", required = false) Integer legacyLength,
			@RequestParam(value = "pgNm", defaultValue = "capt") String pgNm) {
		int captchaLength = resolveCaptchaLength(length, legacyLength);
		if (!isValidRequest(width, height, captchaLength, pgNm)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		try {
			String captchaTxt = generateRandomText(captchaLength);
			request.getSession().setAttribute("captcha" + pgNm, captchaTxt);

			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bufferedImage.createGraphics();
			
			//배경채우기
			g2d.setColor(Color.white);
			g2d.fillRect(0, 0, width, height);
			//글씨쓰기
			g2d.setFont(new Font("Arial", Font.BOLD, 40));
			g2d.setColor(Color.BLACK);
			g2d.drawString(captchaTxt, 10, 35);//텍스트,위치
			//랜덤줄긋기
			g2d.setColor(Color.white);
			for(int i =0; i <8; i++) {//i= 선 개수 x1,y1 시작점, x2,y2 끝점
				float thickness = 1.0f + SECURE_RANDOM.nextFloat() * 3.0f; // 두께 조절
			    g2d.setStroke(new BasicStroke(thickness));

				int x1 = SECURE_RANDOM.nextInt(width);
				int y1 = SECURE_RANDOM.nextInt(height);
				int x2 = SECURE_RANDOM.nextInt(width);
				int y2 = SECURE_RANDOM.nextInt(height);
				g2d.drawLine(x1,y1,x2,y2);
			}
			g2d.setStroke(new BasicStroke(1.0f));
			g2d.dispose();// 닫아주기
			response.setContentType("image/png");
			ImageIO.write(bufferedImage, "png", response.getOutputStream());
		} catch (IOException e) {
			response.setStatus(500);
			logger.error("Captcha generate error", e);
		}
	}

	private int resolveCaptchaLength(Integer length, Integer legacyLength) {
		if (length != null) {
			return length;
		}
		if (legacyLength != null) {
			return legacyLength;
		}
		return DEFAULT_CAPTCHA_LENGTH;
	}

	private boolean isValidRequest(int width, int height, int length, String pgNm) {
		return width >= MIN_IMAGE_WIDTH && width <= MAX_IMAGE_WIDTH
				&& height >= MIN_IMAGE_HEIGHT && height <= MAX_IMAGE_HEIGHT
				&& length >= MIN_CAPTCHA_LENGTH && length <= MAX_CAPTCHA_LENGTH
				&& pgNm != null && !pgNm.isBlank() && pgNm.length() <= MAX_PAGE_NAME_LENGTH;
	}

	/**
	 * 임의의 문자열을 인자로 받은 길이 만큼 생성
	 * 
	 * @param length 생성하려는 문자열 길이
	 * @return length 인자의 길이만큼 생성된 임의의 문자열
	 */
	private String generateRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(CAPTCHA_CHARS.charAt(SECURE_RANDOM.nextInt(CAPTCHA_CHARS.length())));
		}
		return sb.toString();
	}

}
