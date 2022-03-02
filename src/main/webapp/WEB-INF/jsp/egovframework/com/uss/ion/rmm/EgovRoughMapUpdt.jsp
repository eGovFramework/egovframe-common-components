<%
 /**
  * @Class Name : EgovRoughMapUpdt.jsp
  * @Description : EgovRoughMapUpdtl 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2014.08.28	옥찬우		최초 생성
  * @ 2015.04.13   전여철       주소검색 추가
  * @ 2018.09.10   최두영       v3.8 퍼블리싱 점검
  * @ 2018.09.13   최두영       다국어처리
  * @ 2018.10.10   최두영       약도 관리 API 테스트 및 변경
  *
  *  @author 유지보수팀 
  *  @since 2014.08.28
  *  @version 1.0
  *  @see
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonRmm.roughMapUpdt.title" /></title><!-- 약도 수정 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="roughMapVO" staticJavascript="false" xhtml="true" cdata="false"/>
<c:set var="appkey"><spring:message code="roughMap.appkey" /></c:set>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${appkey}&libraries=services"></script>
<script type="text/javascript" language="javascript">
	
	/* ********************************************************
	 * 초기화
	 ******************************************************** */
	var map;   //map
	var maker;//marker
 	var infoWindow;  //infowindow
	 
	function fn_egov_init_roughmapregist() {
		fn_createMap();
	}
	
	function fn_createMap() {
	
		var query = document.getElementById("roughMapAddress").value;
	       
        if(query == null || query.length <= 0){
       	alert("<spring:message code="comUssIonRmm.roughMapInfoRegist.validate.noAddress" />");/* 주소를 찾을 수 없습니다. */
            return;
        }

    	// 주소-좌표 변환 객체를 생성합니다
    	var geocoder = new daum.maps.services.Geocoder();

    	// 주소로 좌표를 검색합니다
    	geocoder.addressSearch(query, function(result, status) {

	    		document.roughMap.la.value = result[0].y;
	 	    	document.roughMap.markerLa.value = result[0].y;
	 	    	document.roughMap.lo.value = result[0].x;
	 	    	document.roughMap.markerLo.value = result[0].x;

    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

    			var mapContainer = document.getElementById('mapCanvas'), // 지도를 표시할 div 
    		    mapOption = {
    		        center: coords, // 지도의 중심좌표
    		        level: 3 // 지도의 확대 레벨
    		    };  
    			
    			// 지도를 생성합니다    
    			map = new daum.maps.Map(mapContainer, mapOption); 
    	            	        
     	        // 결과값으로 받은 위치를 마커로 표시합니다
     	        marker = new daum.maps.Marker({
     	            map: map,
     	            position: coords
     	        });   	        

    	        marker.setMap(map);
    	        
     	        // 인포윈도우로 장소에 대한 설명을 표시합니다
     	        infoWindow = new daum.maps.InfoWindow({
     	           content: '<div style="width:150px;text-align:center;padding:6px 0;">'+query+'</div>',
     	           position: coords
     	        });     	        
     	        infoWindow.open(map, marker);   	    
    	});
	}
	
// 	/* ********************************************************
//	 * 약도주소로 검색
//	 ******************************************************** */
    function fn_Address_Search() {

    	marker.setMap(null);
		infoWindow.close();
		document.roughMap.la.value = "";
    	document.roughMap.lo.value = "";

        var query = document.getElementById("roughMapAddress").value;
       
        if(query == null || query.length <= 0){
       	alert("<spring:message code="comUssIonRmm.roughMapInfoRegist.validate.noAddress" />");/* 주소를 찾을 수 없습니다. */
            return;
        }         

    	// 주소-좌표 변환 객체를 생성합니다
    	var geocoder = new daum.maps.services.Geocoder();     	

    	// 주소로 좌표를 검색합니다
    	geocoder.addressSearch(query, function(result, status) {

    	    // 정상적으로 검색이 완료됐으면 
    	     if (status === daum.maps.services.Status.OK) {

	   	    	document.roughMap.la.value = result[0].y;
 	 	    	document.roughMap.markerLa.value = result[0].y;
 	 	    	document.roughMap.lo.value = result[0].x;
 	 	    	document.roughMap.markerLo.value = result[0].x;

    	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

    	        // 결과값으로 받은 위치를 마커로 표시합니다
    	        marker = new daum.maps.Marker({
    	            map: map,
    	            position: coords
    	        });
    	        
    	        marker.getPosition();    	        

    	        // 인포윈도우로 장소에 대한 설명을 표시합니다
    	        infoWindow = new daum.maps.InfoWindow({
    	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+query+'</div>'
    	        });
    	        infoWindow.open(map, marker);

    	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    	        map.setCenter(coords);
    	    }else{
     	    	alert("<spring:message code="comUssIonRmm.roughMapInfoRegist.validate.noAddress" />");/* 주소를 찾을 수 없습니다. */
    	    }
    	});    	
    }

	/* ********************************************************
	 * 목록 으로 가기
	 ******************************************************** */
	function fn_egov_inqire_roughmaplist() {
		document.roughMap.action = "<c:url value='/com/uss/ion/rmm/selectRoughMapList.do'/>";
		document.roughMap.submit();
	}
	
	/* ********************************************************
	 * 수정처리화면
	 ******************************************************** */
	function fn_egov_updt_roughmap(form){
		if (!validateRoughMapVO(form)) {
			return;
		} else {
			var la = document.roughMap.la.value;
			var lo = document.roughMap.lo.value;
			if (la.length < 1 || lo.length < 1) {
				alert('<spring:message code="comUssIonRmm.roughMapInfoRegist.validate.searchAddress" />'); //약도주소를 검색해주세요.
				return false;
			} else {
				document.roughMap.action = "<c:url value='/com/uss/ion/rmm/updateRoughMap.do'/>";
				document.roughMap.submit();
			}
		}
	}
</script>

</head>
<body onLoad="fn_egov_init_roughmapregist();">

<form:form modelAttribute="roughMap" name="roughMap" method="post">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonRmm.roughMapUpdt.title" /></h2><!-- 약도 수정 -->

	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapList.roughMapSj" /> <span class="pilsu">*</span></th><!-- 약도제목 -->
			<td class="left">
			    <form:input path="roughMapSj" size="90" maxlength="75" />
				<div><form:errors path="roughMapSj"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapList.roughMapAddress" /></th><!-- 약도주소 -->
			<td class="left">
			    <input name="roughMapAddress" id="roughMapAddress" title="<spring:message code="comUssIonRmm.roughMapList.roughMapAddress" />" type="text" maxlength="100" value="<c:out value='${result.roughMapAddress}' />" style="width:547px;" />
				<span class="button"><input type="submit" value="<spring:message code="button.search" />" onclick="fn_Address_Search(); return false;"></span>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapInfoRegist.express" /> <span class="pilsu">*</span></th><!-- 약도표시 -->
			<td class="left">
			    <form:input path="infoWindow" size="90" maxlength="20" />
				<div><form:errors path="infoWindow"/></div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapInfoRegist.input" /></th><!-- 약도입력 -->
			<td class="left">
			    <div id="mapCanvas" style="width: 600px; height: 400px;"></div>
			</td>
		</tr>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value='<spring:message code="button.save" />' onclick="fn_egov_updt_roughmap(document.forms[0]); return false;" />
		<span class="btn_s"><a href="<c:url value='/com/uss/ion/rmm/selectRoughMapList.do'/>" onclick="fn_egov_inqire_roughmaplist(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
	<input name="roughMapId" type="hidden" value="<c:out value='${result.roughMapId}'/>" />
	<input name="la" id="la" type="hidden" value="<c:out value='${result.la}' />" />
	<input name="lo" id="lo" type="hidden" value="<c:out value='${result.lo}' />" />
	<input name="markerLa" id="markerLa" type="hidden" value="<c:out value='${result.markerLa}' />" />
	<input name="markerLo" id="markerLo" type="hidden" value="<c:out value='${result.markerLo}' />" />
	<input name="zoomLevel" id="zoomLevel" type="hidden" value="<c:out value='${result.zoomLevel}' />" />
</form:form>

</body>
</html>