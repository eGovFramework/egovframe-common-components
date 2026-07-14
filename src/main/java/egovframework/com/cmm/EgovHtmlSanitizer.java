package egovframework.com.cmm;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;

/**
 * CKEditor 등 리치 텍스트 에디터로 등록한 컨텐츠(이미지 태그 포함)를
 * 조회화면 등에 HTML 그대로 출력할 때, XSS(스크립트/이벤트 핸들러/위험 프로토콜 등)를
 * 제거한 안전한 HTML만 남기기 위한 유틸리티.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일        수정자       수정내용
 *  ----------  --------    ---------------------------
 *  2026.07.12  유지보수      최초 생성 (CKEditor 컨텐츠 조회화면 XSS 방지)
 * </pre>
 */
public final class EgovHtmlSanitizer {

	/** img의 width/height 인라인 style만 허용 (그 외 CSS는 모두 제거) */
	private static final Pattern SAFE_STYLE_DECLARATION = Pattern
			.compile("^(width|height)\\s*:\\s*\\d{1,4}(px|%)?$", Pattern.CASE_INSENSITIVE);

	/**
	 * jsoup의 프로토콜 검증은 Element.absUrl()로 절대경로 변환이 가능해야 동작하므로,
	 * "/utl/web/imageSrc.do?..." 같은 컨텍스트 상대경로도 검증을 통과하도록 임의의 baseUri를 부여한다.
	 * preserveRelativeLinks(true) 설정으로 실제 출력값은 절대경로로 바뀌지 않고 원본 상대경로가 그대로 유지된다.
	 */
	private static final String DUMMY_BASE_URI = "http://localhost/";

	private static final Safelist SAFELIST = buildSafelist();

	private EgovHtmlSanitizer() {
	}

	/**
	 * 신뢰할 수 없는(사용자가 입력한) HTML에서 스크립트/이벤트 핸들러/위험 프로토콜 등을 제거하고,
	 * 허용된 태그·속성만 남긴 안전한 HTML을 반환한다.
	 *
	 * @param html 리치 텍스트 에디터로 저장된 원본 HTML
	 * @return XSS 방지 처리된 HTML (허용되지 않은 태그/속성/스크립트는 모두 제거됨)
	 */
	public static String sanitize(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}

		String cleaned = Jsoup.clean(html, DUMMY_BASE_URI, SAFELIST);

		return sanitizeStyleAttributes(cleaned);
	}

	private static Safelist buildSafelist() {
		// relaxed()의 기본 프로토콜(img src: http/https, a href: ftp/http/https/mailto)만으로도
		// DUMMY_BASE_URI 기준 절대경로 변환 시 매치되므로 컨텍스트 상대경로 URL이 안전하게 유지된다.
		return Safelist.relaxed()
				.addAttributes("img", "style")
				.preserveRelativeLinks(true);
	}

	/**
	 * Safelist는 style 속성값(CSS) 자체의 위험성은 검증하지 않으므로,
	 * jsoup으로 1차 정제된 결과물에서 style 속성을 width/height 선언만 남도록 다시 필터링한다.
	 */
	private static String sanitizeStyleAttributes(String cleanedHtml) {
		Document doc = Jsoup.parseBodyFragment(cleanedHtml);
		doc.outputSettings().prettyPrint(false);

		for (Element element : doc.select("[style]")) {
			String safeStyle = filterStyle(element.attr("style"));
			if (StringUtils.isBlank(safeStyle)) {
				element.removeAttr("style");
			} else {
				element.attr("style", safeStyle);
			}
		}

		return doc.body().html();
	}

	private static String filterStyle(String style) {
		if (StringUtils.isBlank(style)) {
			return null;
		}

		StringBuilder safeStyle = new StringBuilder();
		for (String declaration : style.split(";")) {
			String trimmed = declaration.trim();
			if (SAFE_STYLE_DECLARATION.matcher(trimmed).matches()) {
				if (safeStyle.length() > 0) {
					safeStyle.append("; ");
				}
				safeStyle.append(trimmed);
			}
		}

		return safeStyle.length() > 0 ? safeStyle.toString() : null;
	}

}
