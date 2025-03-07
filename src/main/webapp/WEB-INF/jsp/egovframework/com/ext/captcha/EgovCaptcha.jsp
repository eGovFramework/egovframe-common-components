<%
 /**
  * @Class Name : EgovCaptcha.jsp
  * @Description
  * - 이 페이지는 Captcha를 통해 데이터를 입력받고 결과 페이지에서 확인하는 절차로 구현 되어있습니다.
  * - 등록 화면의 필수 구성요소는 아래와 같습니다.
  *   - Captcha 이미지 출력 요소 (img src="/ext/captcha/generate.do?pgNm=testprogram")
  *   - Captcha 검증용 매개변수
  *     - captcha : captcha 이미지를 보고 사용자가 입력한 값
  *     - pgNm : 여러 프로그램에서 captcha 사용 시 프로그램을 구분하기 위한 구분 값
  *   호출 URL에 대한 옵션은 EgovCaptchaController를 참조
  * @Modification Information
  * @
  * @ 수정일        수정자            수정내용
  * @ ----------   ---------    ---------------------------
  * @ 2024.09.30    권태성              최초 생성
  *
  *  @author 권태성 (Contributor) 
  *  @since 2024.10.29
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comCmm.left.3300"/></title>
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<script>
	function generateCaptcha() {
		var pgNm = "testprogram";
		var url = "<c:url value='/ext/captcha/generate.do?pgNm=' />" + pgNm + "&" + Math.random();
		document.getElementById("captchaImg").src = url;
	}
</script>
</head>
<body>
<div class="wTableFrm">
	<h2><spring:message code="comCmm.left.3300"/> <spring:message code="title.create" /></h2>

	<form id="frm" action="<c:url value='/ext/captcha/result.do'/>" method="post">
		<input type="hidden" id="pgNm" name="pgNm" value="testprogram" />
		<table class="wTable" summary="Captcha와 함께한 입력 값 등록 폼">
			<caption>등록폼</caption>
			<colgroup>
				<col style="width: 20%;">
				<col style="width: ;">
			</colgroup>
			<tbody>
				<tr>
					<th><label for="answerCn">Captcha</th>
					<td class="nopd" colspan="3">
						<img id="captchaImg" src="<c:url value='/ext/captcha/generate.do?pgNm=testprogram'/>" alt="캡차 이미지" onclick="generateCaptcha();" style="cursor: pointer;">
						<p>이미지를 클릭하면 이미지가 변경됩니다.</p>
						<input type="text" id="captcha" name="captcha" />
						<div></div>
					</td>
				</tr>
			</tbody>
		</table>

		<div class="btn">
			<input type="submit" class="s_submit" value="등록" title="등록 버튼">
		</div>
		<div style="clear:both;"></div>
	</form>
</div>
</body>
</html>
