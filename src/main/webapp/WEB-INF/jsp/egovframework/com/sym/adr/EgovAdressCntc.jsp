<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="symAdr.adressCntcList.Title"/></c:set>
<%
 /**
  * @Class Name : EgovAdressCntc.jsp
  * @Description : 도로명주소연계 화면
  * @Modification Information
  * @
  * @  수정일               수정자                     수정내용
  * @ ----------    ------------    ---------------------------
  * @ 2014.10.21    표준프레임워크           최초 생성
  * @ 2018.06.12    신용호                     Test URL 서비스 중지에 따른 변경
  *
  *  @author 표준프레임워크
  *  @since 2017.08.25
  *  @version 3.7
  *  @see
  *
  */
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/JavaScript" src="<c:url value='/js/egovframework/com/cmm/jquery-1.12.4.min.js'/>"></script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javaScript" language="javascript">

var addrRoad = "<spring:message code='symAdr.list.addrRoad' />";
var addrRoad1 = "<spring:message code='symAdr.list.addrRoad1' />";
var addrRoad2 = "<spring:message code='symAdr.list.addrRoad2' />";
var addrGibun = "<spring:message code='symAdr.list.addrGibun' />";
var addrEng = "<spring:message code='symAdr.list.addrEng' />";
var addrPost = "<spring:message code='symAdr.list.post' />";

function getAddr(){
	$.ajax({
		 //url :"http://행정망 IP/addrlink/addrLinkApiJsonp.do"		//행정망
		 url :"http://www.juso.go.kr/addrlink/addrLinkApiJsonp.do"  //인터넷망
		,type:"post"
		,data:$("#form").serialize()
		,dataType:"jsonp"
		,crossDomain:true
		,success:function(xmlStr){
			if(navigator.appName.indexOf("Microsoft") > -1){
				var xmlData = new ActiveXObject("Microsoft.XMLDOM");
				xmlData.loadXML(xmlStr.returnXml)
			}else{
				var xmlData = xmlStr.returnXml;
			}
			$("#list").html("");
			var errCode = $(xmlData).find("errorCode").text();
			var errDesc = $(xmlData).find("errorMessage").text();
			if(errCode != "0"){
				alert(errCode+"="+errDesc);
			}else{
				if(xmlStr != null){
					makeList(xmlData);
				}
			}
		}
	    ,error: function(xhr, status, error){
        	console.log("Error occurred: " + status + " " + error);
        	alert("주소 정보를 가져오는 데 실패했습니다. 상태 코드: " + xhr.status + ", 오류 메시지: " + error);
        }
    });
}

function decodeXmlEntities(str) {
    var txt = document.createElement("textarea");
    txt.innerHTML = str;
    return txt.value;
}

function getAddrLoc(){
    $.ajax({
         url :"${contextPath}/sym/adr/getAdressCntcApi.do"
        ,type:"post"
        ,data:$("#form").serialize()
        ,dataType:"text"
        ,success:function(xmlStr){
            // 1. 엔티티 복원
            var decodedXmlStr = decodeXmlEntities(xmlStr);

            // 2. 파싱
            var xmlData;
            if (window.DOMParser) {
                var parser = new DOMParser();
                xmlData = parser.parseFromString(decodedXmlStr, "text/xml");
            } else {
                xmlData = new ActiveXObject("Microsoft.XMLDOM");
                xmlData.async = false;
                xmlData.loadXML(decodedXmlStr);
            }

            // 3. parsererror 체크
            if (xmlData.getElementsByTagName("parsererror").length > 0) {
                alert("XML 파싱 오류!\n" + decodedXmlStr);
                return;
            }

            // 이하 기존 코드
            var errCode = "";
            var errDesc = "";
            var commonNodeList = xmlData.getElementsByTagName("common");
            var commonNode = commonNodeList && commonNodeList.length > 0 ? commonNodeList[0] : null;
            if (commonNode) {
                var errorCodeNode = commonNode.getElementsByTagName("errorCode")[0];
                var errorMessageNode = commonNode.getElementsByTagName("errorMessage")[0];
                if (errorCodeNode) errCode = errorCodeNode.textContent || errorCodeNode.text || "";
                if (errorMessageNode) errDesc = errorMessageNode.textContent || errorMessageNode.text || "";
            }

            if (!errCode && !errDesc) {
                alert("오류 코드 또는 메시지를 찾을 수 없습니다.\n원본 XML:\n" + decodedXmlStr);
                return;
            }

            if (errCode === "0") {
                makeList(xmlData);
            } else {
                alert("오류 코드: " + errCode + "\n오류 메시지: " + errDesc);
            }
        },
        error: function(xhr, status, error) {
            console.log("Error occurred: " + status + " " + error);
            alert("주소 정보를 가져오는 데 실패했습니다. 상태 코드: " + xhr.status + ", 오류 메시지: " + error);
        }
    });
}


function makeList(xmlInput){
    var htmlStr = "";
    htmlStr += "<table class='board_list'>";
    htmlStr += "<caption></caption>";
    htmlStr += "<colgroup>";
    htmlStr += "	<col style='width:25%' />";
    htmlStr += "	<col style='width:25%' />";
    htmlStr += "	<col style='width:25%' />";
    htmlStr += "	<col style='width:25%' />";
    htmlStr += "	<col style='width:25%' />";
    htmlStr += "	<col style='width:25%' />";
    htmlStr += "</colgroup>";
    htmlStr += "<thead>";
    htmlStr += "<tr>";
    htmlStr += "<th class='title' nowrap>" + addrRoad + "</th>";
    htmlStr += "<th class='title' nowrap>" + addrRoad1 + "</th>";
    htmlStr += "<th class='title' nowrap>" + addrRoad2 + "</th>";
    htmlStr += "<th class='title' nowrap>" + addrGibun + "</th>";
    htmlStr += "<th class='title' nowrap>" + addrEng + "</th>";
    htmlStr += "<th class='title' nowrap>" + addrPost + "</th>";
    htmlStr += "</tr>";
    htmlStr += "</thead>";

    // XML DOM 객체일 경우
    if (xmlInput && typeof xmlInput.getElementsByTagName === "function") {
        var jusoList = xmlInput.getElementsByTagName("juso");
        for(var i=0; i<jusoList.length; i++){
            var juso = jusoList[i];
            // IE와 표준 브라우저 모두 대응
            var getVal = function(tag) {
                var node = juso.getElementsByTagName(tag)[0];
                return node ? (node.textContent || node.text || "") : "";
            };
            var roadAddr = getVal("roadAddr");
            var roadAddrPart1 = getVal("roadAddrPart1");
            var roadAddrPart2 = getVal("roadAddrPart2");
            var jibunAddr = getVal("jibunAddr");
            var engAddr = getVal("engAddr");
            var zipNo = getVal("zipNo");

            htmlStr += "<tr>";
            htmlStr += "<td>"+roadAddr+"</td>";
            htmlStr += "<td>"+roadAddrPart1+"</td>";
            htmlStr += "<td>"+roadAddrPart2+"</td>";
            htmlStr += "<td>"+jibunAddr+"</td>";
            htmlStr += "<td>"+engAddr+"</td>";
            htmlStr += "<td>"+zipNo+"</td>";
            htmlStr += "</tr>";
        }
    } else {
        // jQuery 객체(jQuery 방식)
        $(xmlInput).find("juso").each(function(){
            htmlStr += "<tr>";
            htmlStr += "<td>"+$(this).find('roadAddr').text()+"</td>";
            htmlStr += "<td>"+$(this).find('roadAddrPart1').text()+"</td>";
            htmlStr += "<td>"+$(this).find('roadAddrPart2').text()+"</td>";
            htmlStr += "<td>"+$(this).find('jibunAddr').text()+"</td>";
            htmlStr += "<td>"+$(this).find('engAddr').text()+"</td>";
            htmlStr += "<td>"+$(this).find('zipNo').text()+"</td>";
            htmlStr += "</tr>";
        });
    }
    htmlStr += "</table>";
    $("#list").html(htmlStr);
}
</script>
<title>${pageTitle}</title>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form name="form" id="form" method="post">
<div class="board">
	<h1>${pageTitle}</h1>

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<label for="currentPage"><spring:message code="symAdr.adressCntcList.scCurrentPage" /> : </label><!-- 현재 페이지 -->
				<input type="text" name="currentPage" value="1" title="<spring:message code="symAdr.adressCntcList.scCurrentPage" /> <spring:message code="input.input" />" />

				<label for="countPerPage"><spring:message code="symAdr.adressCntcList.scCountPerPage" /> : </label><!-- 페이지 사이즈  -->
				<input type="text" name="countPerPage" value="10" title=" <spring:message code="input.input" />" />
			</li>
			<li>
				<label for="keyword"><spring:message code="symAdr.adressCntcList.scKeyword" /> : </label><!-- 검색어 -->
				<input type="text" name="keyword" value="정보화진흥원" title="<spring:message code="symAdr.adressCntcList.scKeyword" /> <spring:message code="input.input" />" />

				<label for="confmKey"><spring:message code="symAdr.adressCntcList.scConfmKey" /> : </label><!-- 승인키  -->
				<input type="text" name="confmKey" id="confmKey" style="width:250px;" value="bnVsbDIwMTQxMDAxMTQwNDA1" title="<spring:message code="symAdr.adressCntcList.scConfmKey" /> <spring:message code="input.input" />" />
			</li>
			<li>
				<span class="btn_b"><a href="#" onclick="getAddrLoc();" title="<spring:message code="symAdr.btn.addrLoc" /> <spring:message code="input.button" />"><spring:message code="symAdr.btn.addrLoc" /></a></span><!-- 주소가져오기(Controller) -->
				<span class="btn_b"><a href="#" onclick="getAddr();" title="<spring:message code="symAdr.btn.addrjson" /> <spring:message code="input.button" />"><spring:message code="symAdr.btn.addrjson" /></a></span><!-- 주소가져오기(JSON) -->
			</li>
		</ul>
	</div>

	 <div id="list" class="board_list"></div>


</div>
</form>
</body>
</html>
