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

function getAddr(){
	$.ajax({
		 //url :"http://10.182.60.22/addrlink/addrLinkApiJsonp.do"  //행정망
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
	    ,error: function(xhr,status, error){
	    	alert("error");
	    }
	});
}

function getAddrLoc(){
	$.ajax({
		 url :"${contextPath}/sym/adr/getAdressCntcApi.do"
		,type:"post"
		,data:$("#form").serialize()
		,dataType:"xml"
		,success:function(xmlStr){
			$("#list").html("");
			var errCode = $(xmlStr).find("errorCode").text();
			var errDesc = $(xmlStr).find("errorMessage").text();
			if(errCode != "0"){
				alert(errCode+"="+errDesc);
			}else{
				if(xmlStr != null){
					makeList(xmlStr);
				}
			}
		}
	    ,error: function(xhr,status, error){
	    	alert("error");
	    }
	});
}

function makeList(xmlStr){
	var htmlStr = "";
	//alert($(xmlStr).find("juso").size());
	
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
	htmlStr += "<th class=title nowrap><spring:message code="symAdr.list.addrRoad" /></th>"; //도로명주소
	htmlStr += "<th class=title nowrap><spring:message code="symAdr.list.addrRoad1" /></th>"; //도로명주소(1)
	htmlStr += "<th class=title nowrap><spring:message code="symAdr.list.addrRoad2" /></th>"; //도로명주소(2)
	htmlStr += "<th class=title nowrap><spring:message code="symAdr.list.addrGibun" /></th>"; //지번주소
	htmlStr += "<th class=title nowrap><spring:message code="symAdr.list.addrEng" /></th>"; //영문주소
	htmlStr += "<th class=title nowrap><spring:message code="symAdr.list.post" /></th>"; //우편번호
	htmlStr += "</tr>";
	htmlStr += "</thead>";
	$(xmlStr).find("juso").each(function(){
		htmlStr += "<tr>";
		htmlStr += "<td>"+$(this).find('roadAddr').text()      +"</td>";
		htmlStr += "<td>"+$(this).find('roadAddrPart1').text()      +"</td>";
		htmlStr += "<td>"+$(this).find('roadAddrPart2').text()      +"</td>";
		htmlStr += "<td>"+$(this).find('jibunAddr').text()     +"</td>";
		htmlStr += "<td>"+$(this).find('engAddr').text()     +"</td>";
		htmlStr += "<td>"+$(this).find('zipNo').text()      +"</td>";
		
		htmlStr += "</tr>";
	});
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