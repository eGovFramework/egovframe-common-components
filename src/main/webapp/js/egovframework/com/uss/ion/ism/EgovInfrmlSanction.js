/****************************************************************
 *
 * 파일명 : EgovInfrmlSanction.js
 * 설  명 : 약식결재자 서비스 기능 사용 JavaScript
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2010.09.10    장철호       1.0             최초생성
 *
 *
 * **************************************************************/

function dirname(path) {
	if (path.lastIndexOf("/") == -1)
		return "./";
	return path.replace(/\\/g, '/').replace(/\/[^\/]*\/?$/, '') + "/";
}

function getActiveScript() {
	var d = document.getElementsByTagName("script");
	var path = dirname(d[d.length - 1].src);
	d = "";			// 221114	김혜준	2022 시큐어코딩 조치
	
	var offset=path.indexOf(location.host)+location.host.length;
	return path.substring(offset);
} 


function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset, location.href.indexOf('/',offset+1));
    
    if ((/^\/js/).test(getActiveScript())) {
    	return "";
    }

    return ctxPath;
}

function loadScript(src, f) {
  var head = document.getElementsByTagName("head")[0];
  var script = document.createElement("script");
  script.src = src;
  var done = false;
  script.onload = script.onreadystatechange = function() { 
    // attach to both events for cross browser finish detection:
    if ( !done && (!this.readyState ||
      this.readyState == "loaded" || this.readyState == "complete") ) {
      done = true;
      if (typeof f == 'function') f();
      // cleans up a little memory:
      script.onload = script.onreadystatechange = null;
      head.removeChild(script);
    }
  };
  head.appendChild(script);
}

loadScript(getContextPath() + '/js/egovframework/com/cmm/showModalDialog.js');

/* ********************************************************
* 아이디  팝업창열기
fn_egov_sanctner
* param 타이틀, ID 폼명, 사원번호 폼명, 사원명 폼명, 부서명 폼명, url
* ******************************************************** */
function fn_egov_sanctner(strTitle, frmUniqId, frmEmplNo, frmEmplyrNm, frmOrgnztNm, url){
	var arrParam = new Array(6);
	arrParam[0] = window;
	arrParam[1] = strTitle;
	arrParam[2] = frmUniqId;
	arrParam[3] = frmEmplNo;
	arrParam[4] = frmEmplyrNm;
	arrParam[5] = frmOrgnztNm;

 	window.showModalDialog(url, arrParam,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes", "sanctionCallback");
}

function sanctionCallback(retVal) {
	if (!retVal || retVal.trim() === '' || retVal === ',' || retVal.match(/^,+$/)) {
	    return;
	}
	
	// retVal 파싱: uniqId,emplNo,emplyrNm,orgnztNm
	var tmp = retVal.split(",");
	
	// dialogArguments에서 파라미터 가져오기
	var arrParam = window.dialogArguments;
	if (arrParam && arrParam.length >= 6) {
		// sanctnerId 설정 (frmUniqId = arrParam[2])
		if (arrParam[2] != '' && tmp[0] && document.getElementById(arrParam[2]) != null) {
			document.getElementById(arrParam[2]).value = tmp[0];
		}
		
		// 사원번호 설정 (frmEmplNo = arrParam[3])
		if (arrParam[3] != '' && tmp[1] && document.getElementById(arrParam[3]) != null) {
			document.getElementById(arrParam[3]).value = tmp[1];
		}
		
		// 사원명 설정 (frmEmplyrNm = arrParam[4])
		if (arrParam[4] != '' && tmp[2] && document.getElementById(arrParam[4]) != null) {
			document.getElementById(arrParam[4]).value = tmp[2];
		}
		
		// 부서명 설정 (frmOrgnztNm = arrParam[5])
		if (arrParam[5] != '' && tmp[3] && document.getElementById(arrParam[5]) != null) {
			document.getElementById(arrParam[5]).value = tmp[3];
		}
	}
}

