package egovframework.com.ext.captcha;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

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
 *   2026.07.13  NCSC           세션/pgNm 제한, rate limit, 일회성 검증, 이미지 강도 강화
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
	private static final int MAX_CAPTCHA_KEYS_PER_SESSION = 5;
	private static final int RATE_LIMIT_MAX_REQUESTS = 20;
	private static final long RATE_LIMIT_WINDOW_MS = 60_000L;
	private static final long RATE_LIMIT_ENTRY_TTL_MS = 120_000L;

	private static final String CAPTCHA_ATTR_PREFIX = "captcha.";
	private static final String CAPTCHA_KEY_INDEX = "captcha.keyIndex";
	/** 혼동 문자(0/O, 1/l/I) 제외 */
	private static final String CAPTCHA_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
	private static final Pattern PAGE_NAME_PATTERN = Pattern.compile("^[A-Za-z0-9_-]{1," + MAX_PAGE_NAME_LENGTH + "}$");
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final Map<String, RateWindow> RATE_LIMIT_STORE = new ConcurrentHashMap<>();

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
	 * Captcha 입력값 검증 결과 페이지 (검증 후 세션 값은 즉시 폐기 = 일회성)
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
		boolean result = false;
		if (isValidPageName(pgNm) && captcha != null && !captcha.isBlank() && captcha.length() <= MAX_CAPTCHA_LENGTH) {
			String attrName = captchaAttributeName(pgNm);
			String expectedCaptcha = (String) session.getAttribute(attrName);
			// 일회성: 비교 전에 제거하여 재사용 차단
			removeCaptchaFromSession(session, pgNm);
			result = expectedCaptcha != null && expectedCaptcha.equals(captcha);
		}

		if (result) {
			model.addAttribute("message", "Captcha 값이 올바르게 입력되었습니다.");
		} else {
			model.addAttribute("message", "Captcha 값이 올바르지 않습니다. 이미지를 새로고침 후 다시 입력하세요.");
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

		HttpSession session = request.getSession(true);
		if (!allowRequest(resolveClientKey(request, session))) {
			response.setStatus(429);
			return;
		}
		if (!reserveCaptchaSlot(session, pgNm)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		Graphics2D g2d = null;
		try {
			String captchaTxt = generateRandomText(captchaLength);
			session.setAttribute(captchaAttributeName(pgNm), captchaTxt);

			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			g2d = bufferedImage.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			drawBackground(g2d, width, height);
			drawNoiseDots(g2d, width, height);
			drawInterferenceLines(g2d, width, height);
			drawCaptchaText(g2d, captchaTxt, width, height);
			drawInterferenceLines(g2d, width, height);

			response.setContentType("image/png");
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			ImageIO.write(bufferedImage, "png", response.getOutputStream());
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			logger.error("Captcha generate error", e);
		} finally {
			if (g2d != null) {
				g2d.dispose();
			}
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
				&& isValidPageName(pgNm);
	}

	private boolean isValidPageName(String pgNm) {
		return pgNm != null && PAGE_NAME_PATTERN.matcher(pgNm).matches();
	}

	private String captchaAttributeName(String pgNm) {
		return CAPTCHA_ATTR_PREFIX + pgNm;
	}

	@SuppressWarnings("unchecked")
	private boolean reserveCaptchaSlot(HttpSession session, String pgNm) {
		Set<String> keyIndex = (Set<String>) session.getAttribute(CAPTCHA_KEY_INDEX);
		if (keyIndex == null) {
			keyIndex = new LinkedHashSet<>();
			session.setAttribute(CAPTCHA_KEY_INDEX, keyIndex);
		}
		synchronized (session) {
			if (keyIndex.contains(pgNm)) {
				return true;
			}
			if (keyIndex.size() >= MAX_CAPTCHA_KEYS_PER_SESSION) {
				Iterator<String> iterator = keyIndex.iterator();
				if (iterator.hasNext()) {
					String oldest = iterator.next();
					iterator.remove();
					session.removeAttribute(captchaAttributeName(oldest));
				}
			}
			keyIndex.add(pgNm);
			session.setAttribute(CAPTCHA_KEY_INDEX, keyIndex);
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private void removeCaptchaFromSession(HttpSession session, String pgNm) {
		session.removeAttribute(captchaAttributeName(pgNm));
		Set<String> keyIndex = (Set<String>) session.getAttribute(CAPTCHA_KEY_INDEX);
		if (keyIndex != null) {
			synchronized (session) {
				keyIndex.remove(pgNm);
				session.setAttribute(CAPTCHA_KEY_INDEX, keyIndex);
			}
		}
	}

	private String resolveClientKey(HttpServletRequest request, HttpSession session) {
		String remoteAddr = request.getRemoteAddr();
		if (remoteAddr == null || remoteAddr.isBlank()) {
			remoteAddr = "unknown";
		}
		return remoteAddr + "|" + session.getId();
	}

	private boolean allowRequest(String clientKey) {
		long now = System.currentTimeMillis();
		cleanupRateLimitStore(now);
		RateWindow window = RATE_LIMIT_STORE.computeIfAbsent(clientKey, key -> new RateWindow(now));
		synchronized (window) {
			if (now - window.windowStartMs >= RATE_LIMIT_WINDOW_MS) {
				window.windowStartMs = now;
				window.count = 0;
			}
			if (window.count >= RATE_LIMIT_MAX_REQUESTS) {
				return false;
			}
			window.count++;
			window.lastAccessMs = now;
			return true;
		}
	}

	private void cleanupRateLimitStore(long now) {
		if (RATE_LIMIT_STORE.size() < 1000) {
			return;
		}
		RATE_LIMIT_STORE.entrySet().removeIf(entry -> now - entry.getValue().lastAccessMs > RATE_LIMIT_ENTRY_TTL_MS);
	}

	private void drawBackground(Graphics2D g2d, int width, int height) {
		g2d.setColor(new Color(240 + SECURE_RANDOM.nextInt(16), 240 + SECURE_RANDOM.nextInt(16), 240 + SECURE_RANDOM.nextInt(16)));
		g2d.fillRect(0, 0, width, height);
	}

	private void drawNoiseDots(Graphics2D g2d, int width, int height) {
		int dots = Math.max(40, (width * height) / 80);
		for (int i = 0; i < dots; i++) {
			g2d.setColor(randomMutedColor());
			int x = SECURE_RANDOM.nextInt(width);
			int y = SECURE_RANDOM.nextInt(height);
			g2d.fillRect(x, y, 1 + SECURE_RANDOM.nextInt(2), 1 + SECURE_RANDOM.nextInt(2));
		}
	}

	private void drawInterferenceLines(Graphics2D g2d, int width, int height) {
		int lines = 4 + SECURE_RANDOM.nextInt(3);
		for (int i = 0; i < lines; i++) {
			g2d.setColor(randomMutedColor());
			g2d.setStroke(new BasicStroke(1.0f + SECURE_RANDOM.nextFloat() * 2.0f));
			int x1 = SECURE_RANDOM.nextInt(width);
			int y1 = SECURE_RANDOM.nextInt(height);
			int x2 = SECURE_RANDOM.nextInt(width);
			int y2 = SECURE_RANDOM.nextInt(height);
			g2d.drawLine(x1, y1, x2, y2);
		}
		g2d.setStroke(new BasicStroke(1.0f));
	}

	private void drawCaptchaText(Graphics2D g2d, String captchaTxt, int width, int height) {
		int length = captchaTxt.length();
		int fontSize = Math.max(18, Math.min(height - 8, (width / Math.max(length, 1)) - 4));
		Font baseFont = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
		int charSlot = Math.max(1, width / (length + 1));
		int baseline = (height + fontSize) / 2 - 2;

		for (int i = 0; i < length; i++) {
			AffineTransform original = g2d.getTransform();
			double angle = (SECURE_RANDOM.nextDouble() - 0.5D) * 0.6D;
			int x = charSlot * (i + 1) - fontSize / 3;
			int y = baseline + SECURE_RANDOM.nextInt(Math.max(1, height / 8)) - height / 16;
			g2d.rotate(angle, x, y);
			g2d.setFont(baseFont);
			g2d.setColor(randomTextColor());
			g2d.drawString(String.valueOf(captchaTxt.charAt(i)), x, y);
			g2d.setTransform(original);
		}
	}

	private Color randomMutedColor() {
		return new Color(80 + SECURE_RANDOM.nextInt(120), 80 + SECURE_RANDOM.nextInt(120), 80 + SECURE_RANDOM.nextInt(120));
	}

	private Color randomTextColor() {
		return new Color(20 + SECURE_RANDOM.nextInt(80), 20 + SECURE_RANDOM.nextInt(80), 20 + SECURE_RANDOM.nextInt(80));
	}

	/**
	 * 임의의 문자열을 인자로 받은 길이 만큼 생성
	 *
	 * @param length 생성하려는 문자열 길이
	 * @return length 인자의 길이만큼 생성된 임의의 문자열
	 */
	private String generateRandomText(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(CAPTCHA_CHARS.charAt(SECURE_RANDOM.nextInt(CAPTCHA_CHARS.length())));
		}
		return sb.toString();
	}

	private static final class RateWindow {
		private long windowStartMs;
		private long lastAccessMs;
		private int count;

		private RateWindow(long now) {
			this.windowStartMs = now;
			this.lastAccessMs = now;
			this.count = 0;
		}
	}

}
