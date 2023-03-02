<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>SP 테스트 화면 - QR-MPM(direct mode)</title>
		
		<%@include file="../comm/header.jsp" %>
		
		<script src="<c:url value='/js/egovframework/com/sec/rnc/mip/qrmpm.js' />"></script>
	</head>
	
	<body>
		<div class="board">
			<h1>모바일 운전면허증</h1>
		    <div class="top-wrap">
				<form class="mt-20" id="form" name="form">
					<div class="box-group">
						<div class="box-th">
							<h2 class="box-tit">QR-MPM</h2>
							<p>UI 설명: <small>응대 장치에서 SP 서버로 QR코드를 요청 후 신분증 앱에서 QR 스캔 후 업무처리 완료 후 서비스 제공 전까지 진행한다.</small></p>
						</div>
						
						<div class="row mt-20">
							<input type="hidden" id="cmd" name="cmd" value="510" />
							<input type="hidden" id="mode" name="mode" value="direct" />
							
							<div class="mr-20">
								<label for="svcCode">서비스 코드</label>
								<input type="text" list="svcCodes" id="svcCode" name="svcCode" value="egovframe.1" />
							</div>
							
							<div class="mr-20">
								<label for="branchName">지점명</label>
								<input type="text" id="branchName" name="branchName" value="테스트 지점명" />
							</div>
							
							<div class="mr-20">
								<label for="deviceId">기기ID</label>
								<input type="text" id="deviceId" name="deviceId" value="테스트 기기ID" />
							</div>
							
							<div class="mr-10 row">
								<button type="button" class="s_btn mb-20" id="qrInfoReqBtn">QR 정보요청</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		
			<div class="bottom-wrap">
				<!-- QR코드 -->
				<div class="box-group w-50" id="qrCodeDiv">
					<div class="box-th">
						<h2 class="box-tit">QR</h2>
					</div>
					
					<div class="qr-wrap mt-25" id="qrCodeArea">
						<img src="<c:url value='/images/egovframework/com/sec/rnc/mip/bg/sample-qrcode.png' />" alt="">
					</div>
				</div>
				
				<%@include file="../comm/trxsts.jsp" %>
			</div>
		</div>
	</body>
</html>