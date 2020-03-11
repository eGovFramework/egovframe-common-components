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
	delete d;
	
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
	// no-op
}

