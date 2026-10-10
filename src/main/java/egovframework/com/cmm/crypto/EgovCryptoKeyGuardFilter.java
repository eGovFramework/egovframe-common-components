package egovframework.com.cmm.crypto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 암호화 키 안내 페이지 필터
 *
 * <p>{@link EgovCryptoKeyGuard} 가 키 설정을 안전하지 않다고 판정하면, 어떤 주소로 접근해도 보안상 이유와 조치 방법
 * (init-crypto-key 실행 → 다시 빌드·재기동)을 설명하는 안내 페이지를 HTTP 503 으로 응답한다. 키를 초기화하기 전에는
 * 서비스를 제공하지 않지만, 기동 실패 대신 브라우저에서 이유를 볼 수 있게 한다. 안전하면 아무것도 하지 않는다.</p>
 *
 * <p>안내 페이지는 JSP·CSS·이미지·DB 에 기대지 않는 독립 HTML 이다(설정이 덜 된 상태에서도 보이도록). 문구는 요청 언어
 * (Accept-Language)에 맞춰 애플리케이션 메시지 소스(message-cryptokey_{ko,en}.properties)에서 꺼낸다.
 * EgovWebApplicationInitializer 가 보안·로그인 필터보다 앞에 등록한다.</p>
 *
 * @author 개발팀
 * @since 2026.10.02
 * @version 1.0
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.10.02  개발팀         최초 생성
 *
 *      </pre>
 */
public class EgovCryptoKeyGuardFilter implements Filter {

	/** 가드 빈 이름(context-common.xml) */
	static final String GUARD_BEAN = "egovCryptoKeyGuard";

	private final ApplicationContext context;

	private volatile EgovCryptoKeyGuard guard;

	private volatile boolean resolved;

	/**
	 * @param context 가드 빈과 메시지 소스를 가진 루트 애플리케이션 컨텍스트
	 */
	public EgovCryptoKeyGuardFilter(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		EgovCryptoKeyGuard current = guard();
		if (current == null || current.isSecure()
				|| !(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
			return;
		}
		writeNotice((HttpServletRequest) request, (HttpServletResponse) response, current);
	}

	/** 가드 빈 - 없으면(가드를 쓰지 않는 구성) null 이고 필터는 통과만 한다 */
	private EgovCryptoKeyGuard guard() {
		if (!resolved) {
			synchronized (this) {
				if (!resolved) {
					guard = (context != null && context.containsBean(GUARD_BEAN))
							? context.getBean(GUARD_BEAN, EgovCryptoKeyGuard.class) : null;
					resolved = true;
				}
			}
		}
		return guard;
	}

	private void writeNotice(HttpServletRequest request, HttpServletResponse response, EgovCryptoKeyGuard current)
			throws IOException {
		Locale locale = request.getLocale() != null ? request.getLocale() : Locale.getDefault();
		response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		if ("HEAD".equalsIgnoreCase(request.getMethod())) {
			return;
		}
		PrintWriter out = response.getWriter();
		out.write(render(current, locale));
		out.flush();
	}

	/**
	 * 안내 페이지 HTML.
	 *
	 * @param current 가드
	 * @param locale 언어
	 * @return HTML
	 */
	String render(EgovCryptoKeyGuard current, Locale locale) {
		String lang = "ko".equals(locale.getLanguage()) ? "ko" : "en";
		StringBuilder sb = new StringBuilder(4096);
		sb.append("<!DOCTYPE html>\n<html lang=\"").append(lang).append("\">\n<head>\n<meta charset=\"UTF-8\">\n")
				.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n")
				.append("<title>").append(text("comCmm.cryptoKey.page.title", locale)).append("</title>\n")
				.append("<style>\n")
				.append("body{margin:0;padding:40px 16px;background:#f5f6f8;color:#1e2124;font-family:'Malgun Gothic','Apple SD Gothic Neo',sans-serif;line-height:1.6}\n")
				.append(".box{max-width:760px;margin:0 auto;background:#fff;border:1px solid #d6d9dd;border-top:6px solid #c62828;border-radius:6px;padding:28px 32px}\n")
				.append("h1{margin:0 0 16px;font-size:22px;color:#c62828}h2{margin:24px 0 8px;font-size:16px}\n")
				.append("code{background:#eef0f3;border-radius:4px;padding:2px 6px;font-family:Consolas,monospace}\n")
				.append("pre{background:#1e2124;color:#f5f6f8;border-radius:4px;padding:12px 16px;overflow-x:auto}\n")
				.append("li{margin:4px 0}.muted{color:#5f6670;font-size:14px}\n")
				.append("</style>\n</head>\n<body>\n<div class=\"box\">\n")
				.append("<h1>").append(text("comCmm.cryptoKey.page.title", locale)).append("</h1>\n")
				.append("<p>").append(text("comCmm.cryptoKey.page.reason", locale)).append("</p>\n")
				.append("<h2>").append(text("comCmm.cryptoKey.page.problems", locale)).append("</h2>\n<ul>\n");
		for (String problem : current.getProblems()) {
			sb.append("<li>").append(text(problem, locale)).append("</li>\n");
		}
		sb.append("</ul>\n<p class=\"muted\">").append(text("comCmm.cryptoKey.page.configPath", locale))
				.append(" : <code>").append(escape(current.displayConfigPath(locale))).append("</code></p>\n")
				.append("<h2>").append(text("comCmm.cryptoKey.page.steps", locale)).append("</h2>\n<ol>\n")
				.append("<li>").append(text("comCmm.cryptoKey.page.step1", locale))
				.append("<pre>Windows : init-crypto-key.bat\nLinux / macOS / Git Bash : ./init-crypto-key.sh</pre></li>\n")
				.append("<li>").append(text("comCmm.cryptoKey.page.step2", locale)).append("</li>\n")
				.append("<li>").append(text("comCmm.cryptoKey.page.step3", locale)).append("</li>\n")
				.append("</ol>\n<p class=\"muted\">").append(text("comCmm.cryptoKey.page.more", locale)).append("</p>\n")
				.append("</div>\n</body>\n</html>\n");
		return sb.toString();
	}

	/** 메시지를 꺼내 HTML 이스케이프한다 */
	private String text(String code, Locale locale) {
		MessageSource source = context;
		String message = (source != null) ? source.getMessage(code, null, code, locale)
				: EgovCryptoKeyMessages.of(locale).get(code);
		return escape(message);
	}

	static String escape(String value) {
		if (value == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(value.length() + 16);
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '<': sb.append("&lt;"); break;
				case '>': sb.append("&gt;"); break;
				case '&': sb.append("&amp;"); break;
				case '"': sb.append("&quot;"); break;
				case '\'': sb.append("&#39;"); break;
				default: sb.append(c);
			}
		}
		return sb.toString();
	}
}
