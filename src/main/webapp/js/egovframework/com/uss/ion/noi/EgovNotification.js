/****************************************************************
 *
 * 파일명 : EgovNotification.js
 * 설  명 : 정보알림이 서비스 기능 사용 JavaScript
 *
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2009.06.17    한성곤       1.0             최초생성
 *
 *
 * **************************************************************/

// XmlHttpRequest 생성
function cfGetXmlHttpRequest() {
    var reqHttp;
    if (window.ActiveXObject) {  // IE인 경우
        try {
        	reqHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch(e) {
            try {
            	reqHttp =  new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e1) {
            	reqHttp =  null;
            }
        }
    } else if (window.XMLHttpRequest){  // IE 이외의 브라우져
        try {
        	reqHttp =  new XMLHttpRequest();
        } catch(e) {
        	reqHttp =  null;
        }
    }

    if (reqHttp == null) {
    	cfFail();   //XMLHttpRequest 생성 실패
    }

    return reqHttp;
}

// XmlHttpRequest 생성 실패 인 경우
function cfFail() {
  alert("이 브라우저는 실시간으로 전송되는 정보알림  메시지를 받을 수 없습니다.");
}

// 서버로 xml데이터 요청 및 call back 함수.
function cfXmlHttpStatus(sendType, url, commType, parameters) {
    var request = cfGetXmlHttpRequest();
    request.open(sendType, url, commType);                      // 송신방법,URL,통신방법

    // Call back 함수
    request.onreadystatechange = function() {                   // 처리상태 변경 발생시 수행되는 이벤트 call back함수

    	if (request.readyState == 4) {                          // 서버 처리 완료
            if (request.status == 200 || request.status == 200200)
            	cfHttpResult(request);   						// XML데이터를 수신후 그 내용을 파싱하여 출력.
            else
            	cfHttpException(request);                       // 예외 처리
        }
    }
    request.send(parameters);                                   // 서버로 요청
}

// 예외 처리 (status != 200)
function cfHttpException(xmlHttp) {
    var exceptShow = "[전자정부 표준프레임워크]\n상태 코드  " + xmlHttp.status;
    exceptShow += ",  네트워크가 연결되지 않았거나 시스템이 종료되었습니다.";
    alert(exceptShow);
}

// 서버로 XML데이터 요청.
function fcRequestData() {
	// TODO context root 지정 필요
    cfXmlHttpStatus("GET", noi_url, true, "");
}

// 메인 처리
function cfHttpResult(xmlHttp) {
    var xml = xmlHttp.responseXML;     //XML 파일 데이터
	//var xml = xmlHttp.responseText;

    var notificationList = xml.getElementsByTagName("list");    // Parent Node

    //var timeNode = xml.getElementsByTagName("time");
    var titleNode = xml.getElementsByTagName("title");
    var contentsNode = xml.getElementsByTagName("contents");

    var rowCnt = notificationList.length;    // 레코드 건수

    for (var i = 0; i < rowCnt; i++) {
	    //var time = timeNode[i].childNodes[0].nodeValue;
	    var title = titleNode[i].childNodes[0].nodeValue;
	    var contents = contentsNode[i].childNodes[0].nodeValue;

	    alert("[정보알림이] " + "<" + title + ">\n" + contents);
    }
}

// 자동 호출 등록하기...
window.setInterval('fcRequestData()', 55000);
