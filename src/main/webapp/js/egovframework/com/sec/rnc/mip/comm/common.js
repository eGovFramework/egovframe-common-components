/**
 * 공통 script
 */

//StringBuffer 설정
let StringBuffer = function() {
	this.buffer = new Array();
};

StringBuffer.prototype.append = function(str) {
	this.buffer[this.buffer.length] = str;
};

StringBuffer.prototype.toString = function(s) {
	return this.buffer.join((s || ''));
};

let TRX_CODE = '';  // 거래코드

// 거래 상태 조회
function fnGetTrxsts() {
	let trxcode = TRX_CODE;
	
	let errMsg = new StringBuffer();

	if((trxcode || '') == '') {
		errMsg.append('거래코드가 없습니다.');
	}
	
	if(errMsg.toString() != '') {
		alert(errMsg.toString('\n'));
		
		return;
	}
	
	let param = {
		  url: contextPath + '/mip/trxsts'
		, dataType: 'json'
		, data: JSON.stringify({'data': Base64.encode(JSON.stringify({'trxcode': trxcode}))})
		, contentType: "application/json; charset=utf-8"
		, type: 'POST'
		, success: function(data) {
			console.log(data);
			
			if((data.errcode || '') == '') {
				let trxinfo = JSON.parse(Base64.decode(data.data));
				let trxStsCodeVal = {
					  '0001': '서비스 요청'
					, '0002': 'profile 요청'
					, '0003': 'VP 검증요청'
					, '0004': 'VP 검증완료'
				};
				
				$('#trxcodeTag').text(trxinfo.trxcode);
				$('#trxStsCodeTag').text(trxStsCodeVal[trxinfo.trxStsCode] + '(' + trxinfo.trxStsCode + ')');
				$('#vpVerifyResult').text(trxinfo.vpVerifyResult);
				$('#vpName').text(trxinfo.vpName);
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
