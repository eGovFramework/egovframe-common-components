<%--
  Class Name : EgovWebStandardInspectionUriDirect.jsp
  Description : 웹표준검사
  Modification Information

      수정일               수정자             수정내용
   ----------   --------    ---------------------------
   2010.10.05   박종선              최초 생성
   2020.10.29   신용호              KISA 보안약점 조치 (경로 조작 및 자원 삽입)

    author   : 박종선
    since    : 2010.10.05
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	buffer="none"%>
<%@page import="java.util.*"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="egovframework.com.utl.fcc.service.EgovNumberCheckUtil"%>
<%@page import="egovframework.com.cmm.EgovWebUtil"%>
<%@page import="egovframework.com.cmm.util.EgovResourceCloseHelper"%>
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
<%
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");

	String sUri = request.getParameter("uri") == null ? "" : (String) request.getParameter("uri");
	String sNum = request.getParameter("num") == null ? "" : (String) request.getParameter("num");

	sUri = EgovWebUtil.clearXSSMinimum(sUri);
	sNum = EgovWebUtil.clearXSSMinimum(sNum);

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

		//URL 체크
		sUri = EgovWebUtil.filePathBlackList(sUri);
		URL urlCheck = new URL(sUri);
		urlCheck.openStream();

		URLConnection urlConn = urlCheck.openConnection();
		urlConn.setRequestProperty("Cookie", "JSESSIONID=" + session.getId());

		buffResOld = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "utf-8"));
		while ((str = buffResOld.readLine()) != null) {
			strBufInputHtml.append(str + "\r\n");
			//out.println(str);
		}

		HttpRequestor requestor = new HttpRequestor(new URL("http://validator.w3.org/check"));
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
				//out.println("<xmp>");  BufferedReader br = new BufferedReader(new InputStreamReader(is));
				//buffResInput = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
				while ((str = br.readLine()) != null) {
					if (str != null) {
						//1차검색
						pattern = Pattern.compile("\\sErrors*|\\swarning*|Passed");
						matcher = pattern.matcher(str);

						if (matcher.find()) {
							sMatcherFind = str;
							break;
						}
					}

					if (nLine > 100) {
						break;
					}

					nLine++;
				}

				if (buffResInput != null)
					buffResInput.close();
				//out.println("</xmp>");
			}
		} finally {
			EgovResourceCloseHelper.close(br);
		}

		if (sMatcherFind.trim().equals("Passed")) {
%>
<script type="text/javaScript" language="javascript">
		    parent.document.getElementById("divErr<%=sNum%>").innerHTML = '0 Errors';
		    parent.document.getElementById("divWar<%=sNum%>").innerHTML = '0 warning(s)';
		   </script>

<%
		} else if (!sMatcherFind.equals("")) {

			if (sMatcherFind.indexOf(",") > -1) {
				arrMatcherFind = sMatcherFind.split(",");
%>

<script type="text/javaScript" language="javascript">
		    parent.document.getElementById("divErr<%=sNum%>").innerHTML = '<%=arrMatcherFind[0].replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?(\\s)*(/)?>", "")%>';
		    parent.document.getElementById("divWar<%=sNum%>").innerHTML = '<%=arrMatcherFind[1].replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?(\\s)*(/)?>", "")%>';
		   </script>

<%
			} else if (sMatcherFind.indexOf("E") > -1) {
%>
<script type="text/javaScript" language="javascript">
		   	 parent.document.getElementById("divErr<%=sNum%>").innerHTML = '<%=sMatcherFind.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?(\\s)*(/)?>", "")%>';
		   	 parent.document.getElementById("divWar<%=sNum%>").innerHTML = '0 warning(s)';
		   	</script>
<%
			} else if (sMatcherFind.indexOf("s") > -1) {
%>
<script type="text/javaScript" language="javascript">
		   	 parent.document.getElementById("divErr<%=sNum%>").innerHTML = '0 Errors';
		   	 parent.document.getElementById("divWar<%=sNum%>").innerHTML = '<%=sMatcherFind.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?(\\s)*(/)?>", "")%>';
		   	</script>
<%
			}
%>
<%
		}
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

