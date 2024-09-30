<%@ page contentType="text/html; charset=utf-8"%>
<div class="wTableFrm">
	<h2>Captcha 검증 결과</h2>
	<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">
	<div style="font-size: 20px; color: ${result ? 'blue' : 'red'};">${message}</div>
	<div class="btn">
		<input type="button" class="s_submit" value="돌아가기" onclick="document.location.href='/ext/captcha/input.do';">
	</div>
</div>
