<%--
  Class Name : EgovWebStandardInspectionUriDirect.jsp
  Description : 웹표준검사
  Modification Information

      수정일               수정자              수정내용
   ----------   ---------   ---------------------------
   2010.10.05   박종선              최초 생성
   2019.11.29   신용호              KISA 보안약점 조치 (부적절한 자원 해제, 경로조작및 자원 삽입)
   2020.10.29   신용호              KISA 보안약점 조치 (크로스사이트 스크립트)
    
    author   : 박종선
    since    : 2010.10.05
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" buffer="none"%>
<%@page import="java.util.*"  %>
<%@page import="java.util.regex.*"  %>
<%@page import="java.io.*"  %>
<%@page import="java.net.*"  %>
<%@page import="egovframework.com.utl.fcc.service.EgovNumberCheckUtil"%>
<%@page import="egovframework.com.cmm.util.EgovResourceCloseHelper"%>
<%@page import="egovframework.com.cmm.EgovWebUtil"%>
<%!public class HttpRequestor {

		public static final String CRLF = "\r\n";

		/**
		 * 연결할 URL
		 */
		private URL targetURL;

		/**
		 * 파라미터 목록을 저장하고 있다.
		 * 파라미터 이름과 값이 차례대로 저장된다.
		 */
		private ArrayList list;

		public HttpRequestor(URL target) {
			this(target, 20);
		}

		/**
		 * 파라미터를 추가한다.
		 * @param parameterName 파라미터 이름
		 * @param parameterValue 파라미터 값
		 * @exception IllegalArgumentException parameterValue가 null일 경우
		 */
		public void addParameter(String parameterName, String parameterValue) {
			if (parameterValue == null)
				throw new IllegalArgumentException("parameterValue can't be null!");

			list.add(parameterName);
			list.add(parameterValue);
		}

		public void addParameterBuf(String parameterName, StringBuffer parameterValue) {
			if (parameterValue == null)
				throw new IllegalArgumentException("parameterValue can't be null!");

			list.add(parameterName);
			list.add((StringBuffer) parameterValue);
		}

		/**
		 * HttpRequest를 생성한다.
		 *
		 * @param target HTTP 메시지를 전송할 대상 URL
		 */
		public HttpRequestor(URL target, int initialCapicity) {
			this.targetURL = target;
			this.list = new ArrayList();
		}

		private String makeDelimeter() {
			return "---------------------------7d115d2a20060c";
		}

		public InputStream sendMultipartPost() throws IOException {

			HttpURLConnection conn = (HttpURLConnection) targetURL.openConnection();

			// Delimeter 생성
			String delimeter = makeDelimeter();
			byte[] newLineBytes = CRLF.getBytes("utf-8");
			byte[] delimeterBytes = delimeter.getBytes("utf-8");
			byte[] dispositionBytes = "Content-Disposition: form-data; name=".getBytes("utf-8");
			String dispositionStr = "Content-Disposition: form-data; name=";
			byte[] quotationBytes = "\"".getBytes("utf-8");
			byte[] contentTypeBytes = "Content-Type: application/octet-stream".getBytes("utf-8");
			byte[] fileNameBytes = "; filename=".getBytes("utf-8");
			byte[] twoDashBytes = "--".getBytes("utf-8");

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + delimeter);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);

			BufferedOutputStream out = null;

			try {
				out = new BufferedOutputStream(conn.getOutputStream());

				Object[] obj = new Object[list.size()];
				list.toArray(obj);

				for (int i = 0; i < obj.length; i += 2) {
					// Delimeter 전송
					out.write(twoDashBytes);
					out.write(delimeterBytes);
					out.write(newLineBytes);

					// 파라미터 이름 출력
					out.write(dispositionBytes);
					out.write(quotationBytes);
					out.write(((String) obj[i]).getBytes("utf-8"));
					out.write(quotationBytes);

					if (obj[i + 1] instanceof String) {
						// String 이라면
						out.write(newLineBytes);
						out.write(newLineBytes);
						// 값 출력
						out.write(((String) obj[i + 1]).getBytes("utf-8"));
						out.write(newLineBytes);
					} else if (obj[i + 1] instanceof StringBuffer) {
						// StringBuffer 이라면
						out.write(newLineBytes);
						out.write(newLineBytes);
						// 값 출력

						out.write(((StringBuffer) obj[i + 1]).toString().getBytes("utf-8"));
						out.write(newLineBytes);
						//System.out.println("HttpRequestor>" + ((StringBuffer)obj[i+1]).toString().replaceAll("\t","").substring(0,130).toString());
					} else {
						// 파라미터의 값이 File 이나 NullFile인 경우
						if (obj[i + 1] instanceof File) {
							File file = (File) obj[i + 1];
							// File이 존재하는 지 검사한다.
							out.write(fileNameBytes);
							out.write(quotationBytes);
							out.write(file.getAbsolutePath().getBytes("utf-8"));
							out.write(quotationBytes);
						} else {
							// NullFile 인 경우
							out.write(fileNameBytes);
							out.write(quotationBytes);
							out.write(quotationBytes);
						}
						out.write(newLineBytes);
						out.write(contentTypeBytes);
						out.write(newLineBytes);
						out.write(newLineBytes);
						// File 데이터를 전송한다.
						if (obj[i + 1] instanceof File) {
							File file = (File) obj[i + 1];
							// file에 있는 내용을 전송한다.
							BufferedInputStream is = null;
							try {
								is = new BufferedInputStream(new FileInputStream(file));
								byte[] fileBuffer = new byte[1024 * 8]; // 8k
								int len = -1;
								while ((len = is.read(fileBuffer)) != -1) {
									out.write(fileBuffer, 0, len);
								}
							} finally {
								if (is != null)
									try {
										is.close();
									} catch (IOException ex) {
										throw new RuntimeException(ex);
									}
							}
						}
						out.write(newLineBytes);
					} // 파일 데이터의 전송 블럭 끝
					if (i + 2 == obj.length) {
						// 마지막 Delimeter 전송
						out.write(twoDashBytes);
						out.write(delimeterBytes);
						out.write(twoDashBytes);
						out.write(newLineBytes);
					}
				} // for 루프의 끝

				out.flush();
			} finally {
				if (out != null)
					out.close();
			}
			return conn.getInputStream();
		}
	}%>
<html lang="ko">
	<head><title></title></head>
	<body>
<%
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");

	// [SSRF 수정] rawUri: URL 연산 전용 / sUri: 화면 출력(XSS 방어) 전용으로 분리
	// clearXSSMinimum()은 '.'을 &#46;으로 변환하여 URL을 파괴하므로 URL 처리에 사용 불가.
	// clearXSS()는 <, >, & 만 이스케이프하므로 URL 표시에 적합.
	String rawUri = EgovWebUtil.removeCRLF(
		request.getParameter("uri") == null ? "" : (String) request.getParameter("uri")
	);
	String sNum = request.getParameter("num") == null ? "" : (String) request.getParameter("num");
	String sUri = EgovWebUtil.clearXSS(rawUri);

	// [Private 화이트리스트] 접속을 허용할 내부 호스트명 또는 IP 목록.
	// 운영 환경에 맞게 수정 필요. UriDirect.jsp의 ALLOWED_HOSTS와 동일하게 유지.
	final String[] ALLOWED_HOSTS = { "허용할내부호스트명" };

	String sMatcherFind = "";
	int nLine = 0;
	String[] arrMatcherFind;
	String str = null;

	StringBuffer strBufInputHtml = new StringBuffer();

	String boundary = "nvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnvnv";
	int bufferSize = 16384;

	BufferedReader buffResInput = null;
	BufferedReader buffResOld = null;
	try {
		// [SSRF 검증: Private - 화이트리스트 방식 (UriDirect.jsp와 동일 정책)]
		// 1. URL 파싱: 형식 오류 시 MalformedURLException → 하단 catch에서 처리
		URL parsedUrl = new URL(rawUri);
		// 2. 스키마 검증: http/https 외 file://, ftp:// 등 차단
		String scheme = parsedUrl.getProtocol();
		if (!"http".equals(scheme) && !"https".equals(scheme)) {
			throw new MalformedURLException("허용되지 않는 URL 스키마입니다.");
		}
		// 3. 화이트리스트 검증: ALLOWED_HOSTS에 등록된 내부 호스트만 허용.
		//    등록되지 않은 내부 URL과 외부 공인 URL 모두 차단.
		String host = parsedUrl.getHost();
		boolean allowed = false;
		for (String h : ALLOWED_HOSTS) {
			if (h.equalsIgnoreCase(host)) { allowed = true; break; }
		}
		if (!allowed) {
			throw new UnknownHostException("허용되지 않는 호스트입니다: " + host);
		}
		// 4. DNS Rebinding 방어: 화이트리스트를 통과해도 DNS 조작으로 외부 IP를 가리킬 수 있으므로
		//    InetAddress.getByName()으로 실제 해석된 IP가 내부 주소인지 재확인.
		InetAddress addr = InetAddress.getByName(host);
		if (!addr.isSiteLocalAddress() && !addr.isLoopbackAddress() && !addr.isLinkLocalAddress()) {
			throw new UnknownHostException("화이트리스트 호스트가 내부 주소로 해석되지 않습니다.");
		}
		// 5. 검증 완료: openStream()을 제거하고 URLConnection으로 내부 HTML 수집.
		URL urlCheck = new URL(rawUri);
		URLConnection urlConn = urlCheck.openConnection();
		urlConn.setRequestProperty("Cookie", "JSESSIONID=" + session.getId());

		buffResOld = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "utf-8"));
		while ((str = buffResOld.readLine()) != null) {
			strBufInputHtml.append(str + "\r\n");
		}

		// validator 호출: http → https로 통일
		HttpRequestor requestor = new HttpRequestor(new URL("https://validator.w3.org/check"));
		requestor.addParameterBuf("fragment", (StringBuffer) strBufInputHtml);
		requestor.addParameter("direct_prefill_no", "0");
		requestor.addParameter("direct-doctype", "Inline");
		requestor.addParameter("group", "0");
		requestor.addParameter("direct_prefill_no", "0");

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(requestor.sendMultipartPost(), "utf-8"));

			StringBuffer resp = new StringBuffer();

			String line;
			Pattern pattern;
			Matcher matcher;

			if (1 == 1) { // 연결이 성공적일때
				while ((str = br.readLine()) != null) {
					// validator 응답의 상대경로를 https://validator.w3.org 기준으로 치환.
					// http → https로 통일하여 Mixed Content 경고 방지.
					str = str.replaceAll("\\./", "https://validator.w3.org/");
					str = str.replaceAll("\\<form", "<form action=\"https://validator.w3.org/check\"");
					str = str.replaceAll("\"images/", "\"https://validator.w3.org/images/");

					out.println(str);
				}

			}

		} finally {
			if (br != null) {
				br.close();
			}
			if (buffResInput != null)
				buffResInput.close();
		}
%>


<%
	} catch (java.net.UnknownHostException ex) {
%>
	<script type="text/javaScript" language="javascript">
	 alert("입력하신 URL[<%=sUri%>] \n\n잘못 되었습니다!");
	</script>
<%
	} catch (java.net.MalformedURLException ex) {
%>
	<script type="text/javaScript" language="javascript">
	 alert("입력하신 URL[<%=sUri%>] \n\n잘못 되었습니다!");
	</script>
<%
	} catch (Exception ex) {
		throw new RuntimeException(ex);
	} finally {
		EgovResourceCloseHelper.close(buffResInput, buffResOld);
	}
%>
	</body>
</html>