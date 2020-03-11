/****************************************************************
 *
 * 파일명 : EgovZipPopup.js
 * 설  명 : 전자정부 공통서비스 달력 팝업 JavaScript
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2009.03.30    이중호       1.0             최초생성
 * 2011.08.30	 이기하		  1.1			  contextpath 적용
 *
 *
 */

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

/**********************************************
 * 함수명 : fn_egov_ZipSearch
 * 설  명 : 우편번호찾기 팝업 호출 - form별로 이름이 다른 경우 사용
 * 인  자 : 사용할 Form 객체, 우편번호(123456), 우편번호(123-456), 주소
 * 사용법 : fn_egov_ZipSearch(frm, sZip, vZip, sAddr)
 *
 * 수정일        수정자      수정내용
 * ------        ------     -------------------
 * 2009.03.30    이중호      신규작업
 * 2011.08.30	 이기하		 contextpath 적용
 *
 */

function fn_egov_ZipSearch(frm, sZip, vZip, sAddr) {
	var retVal;

	var url = frm.zip_url.value;
//	var url ="/sym/ccm/zip/EgovCcmZipSearchPopup.do";

	var varParam = new Object();
	varParam.sZip = sZip.value;

	// IE
	//var openParam = "dialogWidth:500px;dialogHeight:325px;scroll:no;status:no;center:yes;resizable:yes;";
	// FIREFOX
	var openParam = "dialogWidth:700px;dialogHeight:365px;scroll:no;status:no;center:yes;resizable:yes;";

	retVal = window.showModalDialog(url, varParam, openParam, "zipCallback");
	
	otherParameters[0] = sZip;
	otherParameters[1] = vZip;
	otherParameters[2] = sAddr;

	if(retVal) {
		sZip.value  = retVal.sZip;
		vZip.value  = retVal.vZip;
		sAddr.value = retVal.sAddr;
	}
}


function zipCallback(retVal) {
	if (retVal) {
		otherParameters[0].value  = retVal.sZip;
		otherParameters[1].value  = retVal.vZip;
		otherParameters[2].value = retVal.sAddr;
	}
}
