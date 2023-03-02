$(function() {
	// 샘플입력 버튼 클릭시
	$('#sampleInputBtn').click(function() {
		console.log('#sampleInputBtn click');
		
		$('#svcCode').val('egovframe.1');
		$('#branchName').val('논현역점');
		$('#deviceId').val('123456789');
	});
	
	// QR 정보요청 버튼 클릭시
	$('#qrInfoReqBtn').click(function() {
		console.log('#qrInfoReqBtn click');
		console.log("cmd : " + $('#cmd').val());
		console.log("mode : " + $('#mode').val());
		console.log("svcCode : " + $('#svcCode').val());
		console.log("branchName : " + $('#branchName').val());
		console.log("diviceId : " + $('#deviceId').val());
		
		fnQrInfoReq();
	});
	
	// 입력조건 초기화 버튼 클릭시
	$('#formResetBtn').click(function() {
		console.log('#formResetBtn click');
		
		$('#form')[0].reset();
	});
	
	// QR코드 초기화
	$('#qrCodeResetBtn').click(function() {
		console.log('#qrCodeResetBtn click');
		
		$('#qrCodeArea').text('QR 코드 영역');
		
		TRX_CODE = '';
	});
	
	// 응답 상태 확인
	$('#trxstsBtn').click(function() {
		console.log('#trxstsBtn click');
		
		fnGetTrxsts(TRX_CODE);
	});

});

// test QR 정보요청
function fnQrInfoReq() {
	let errMsg = new StringBuffer();

	if($('#svcCode').val().trim() == '') {
		errMsg.append('서비스 코드를 입력해주세요.');
	}
	
	if($('#branchName').val().trim() == '') {
		errMsg.append('지점명을 입력해주세요.');
	}
	
	if($('#deviceId').val().trim() == '') {
		errMsg.append('기기ID를 입력해주세요.');
	}
	
	if(errMsg.toString() != '') {
		alert(errMsg.toString('\n'));
		return;
	}

	TRX_CODE = '';
	
	let param = {
		  url: contextPath + '/qrmpm/start'
		, dataType: 'json'
		, data: JSON.stringify({
			  'cmd'       :  $('#cmd').val()
			, 'mode'      :  $('#mode').val()
			, 'svcCode'   :  $('#svcCode').val()
			, 'branchName':  $('#branchName').val()
			, 'deviceId'  :  $('#deviceId').val()
		})
		, contentType: 'application/json; charset=utf-8'
		, type: 'POST'
		, success: function(data) {
			console.log(data);
			
			if((data.errmsg || '') == '') {
				$('#qrCodeArea').empty();
				
				TRX_CODE = JSON.parse(Base64.decode(data.m200Base64)).trxcode;
				
				new QRCode(document.getElementById('qrCodeArea'), {
					  width: 150
					, height: 150
					, text: data.m200Base64
				});
			} else {
				alert(data.errmsg);
			}
		}
		, error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	};
	
	$.ajax(param);
}
