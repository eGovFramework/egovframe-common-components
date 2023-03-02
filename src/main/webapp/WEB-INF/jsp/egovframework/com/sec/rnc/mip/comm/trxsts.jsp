<%@page language="java" pageEncoding="UTF-8"%>
<div class="box-group w-50" id="qrResDiv">
	<div class="box-th">
		<h2 class="box-tit">트랜잭션 처리상태 확인</h2>
	</div>
	
	<div class="code-area" id="qrResResult">
		<div class="box-line">
			<label for="trxcodeTag">거래코드 : <span id="trxcodeTag"></span></label>
		</div>
		<div class="box-line">
			<label for="trxStsCodeTag">서비스 상태 : <span id="trxStsCodeTag"></span></label>
		</div>
		<div class="box-line">
			<label for="vpVerifyResult">VP 검증 결과 여부 : <span id="vpVerifyResult"></span></label>
		</div>
		<div class="box-line">
			<label for="vpName">이름 : <span id="vpName"></span></label>
		</div>
		
		<button type="button" class="s_btn float-r" id="trxstsBtn">확인요청</button>
	</div>
</div>