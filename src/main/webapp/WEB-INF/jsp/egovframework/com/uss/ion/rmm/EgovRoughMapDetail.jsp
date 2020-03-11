<%
 /**
  * @Class Name : EgovRoughMapInfoDetail.jsp
  * @Description : EgovRoughMapInfoDetail 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2014.08.28	옥찬우		최초 생성
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
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonRmm.roughMapInfoDetail.title" /></title><!-- 약도 상세 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<c:set var="appkey"><spring:message code="roughMap.appkey" /></c:set>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${appkey}&libraries=services"></script>
<script type="text/javaScript" language="javascript">
	
	/* ********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init_roughmapregist() {
		fn_createMap();
	}

	/* ********************************************************
	 * 맵 생성(successCallback)
	 ******************************************************** */
	 function fn_createMap() {
		
		 var mapContainer = document.getElementById('mapCanvas'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new daum.maps.LatLng(0, 0), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };

		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new daum.maps.Map(mapContainer, mapOption); 
		
		 var query = document.roughMap.roughMapAddress.value;
         if(query == null || query.length <= 0){
        	alert("<spring:message code="comUssIonRmm.roughMapInfoRegist.validate.noAddress" />");
             return;
         }

     	// 주소-좌표 변환 객체를 생성합니다
     	var geocoder = new daum.maps.services.Geocoder();     	

     	// 주소로 좌표를 검색합니다
     	geocoder.addressSearch(query, function(result, status) {

     	    // 정상적으로 검색이 완료됐으면 
     	     if (status === daum.maps.services.Status.OK) {

     	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

     	        // 결과값으로 받은 위치를 마커로 표시합니다
     	        var marker = new daum.maps.Marker({
     	            map: map,
     	            position: coords
     	        });
     	        
     	        var iwRemoveable = true;

     	        // 인포윈도우로 장소에 대한 설명을 표시합니다
     	        var infoWindow = new daum.maps.InfoWindow({
     	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+query+'</div>',
     	            removable : iwRemoveable
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
	 * 다음 길찾기로 연결
	 ******************************************************** */
	function fn_egov_find_roughmap(){
		window.open(
			'http://map.daum.net/link/to/' + 
			document.roughMap.infoWindow.value + ',' + 
			document.roughMap.markerLa.value + ',' + 
			document.roughMap.markerLo.value
		);
	}
	
	/* ********************************************************
	 * 다음 지도에서 크게 보기
	 ******************************************************** */
	function fn_egov_magnify_roughmap(){
		window.open(
			'http://map.daum.net/link/map/' + 
			document.roughMap.infoWindow.value + ',' + 
			document.roughMap.markerLa.value + ',' + 
			document.roughMap.markerLo.value
		);
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
	function fn_egov_updt_roughmap(roughMapId){
		document.roughMap.action = "<c:url value='/com/uss/ion/rmm/updateRoughMapView.do'/>";
		document.roughMap.submit();
	}

	/* ********************************************************
	 * 삭제처리화면
	 ******************************************************** */
	function fn_egov_delete_roughmap(roughMapId){

		if	(confirm("<spring:message code="common.delete.msg" />"))	{

			// Delete하기 위한 키값(wordId)을 셋팅
			document.roughMap.roughMapId.value = roughMapId;
			document.roughMap.action = "<c:url value='/com/uss/ion/rmm/deleteRoughMap.do'/>";
			document.roughMap.submit();
		}
	}
</script>

</head>
<body onLoad="fn_egov_init_roughmapregist();">

<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2><spring:message code="comUssIonRmm.roughMapInfoDetail.title" /></h2><!-- 약도 상세 -->
	
<form id="roughMap" name="roughMap" method="post">
	<!-- 등록폼 -->
	<table class="wTable">
		<colgroup>
			<col style="width:16%" />
			<col style="" />
		</colgroup>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapList.roughMapSj" /> <span class="pilsu">*</span></th><!-- 약도제목 -->
			<td class="left">
			    <c:out value="${roughMap.roughMapSj}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapList.roughMapAddress" /> <span class="pilsu">*</span></th><!-- 약도주소 -->
			<td class="left">
			    <c:out value="${roughMap.roughMapAddress}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapInfoRegist.express" /> <span class="pilsu">*</span></th><!-- 약도표시 -->
			<td class="left">
			    <c:out value="${roughMap.infoWindow}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="comUssIonRmm.roughMapInfoRegist.input" /> <span class="pilsu">*</span></th><!-- 약도입력 -->
			<td class="left">
			    <div id="mapCanvas" style="width:600px; height:400px;"></div>
			</td>
		</tr>
	</table>
		<input name="roughMapAddress" id="roughMapAddress" type="hidden" value="<c:out value='${roughMap.roughMapAddress}'/>">
		<input name="roughMapId" id="roughMapId" type="hidden" value="<c:out value='${roughMap.roughMapId}'/>">
		<input name="roughMapSj" id="roughMapSj" type="hidden" value="<c:out value='${roughMap.roughMapSj}' />" />
		<input name="la" id="a" type="hidden" value="<c:out value='${roughMap.la}' />" />
		<input name="lo" id="lo" type="hidden" value="<c:out value='${roughMap.lo}' />" />
		<input name="markerLa" id="markerLa" type="hidden" value="<c:out value='${roughMap.markerLa}' />" />
		<input name="markerLo" id="markerLo" type="hidden" value="<c:out value='${roughMap.markerLo}' />" />
		<input name="infoWindow" id="infoWindow" type="hidden" value="<c:out value='${roughMap.infoWindow}' />" />
		<input name="zoomLevel" id="zoomLevel" type="hidden" value="<c:out value='${roughMap.zoomLevel}' />" />
	</form>
	<!-- 하단 버튼 -->
	<div class="btn">
		<input class="s_submit" type="submit" value="<spring:message code="comUssIonRmm.roughMapInfoDetail.findRoughMap" />" onclick="fn_egov_find_roughmap(); return false;" /><!-- 길찾기 -->
		
		<input class="s_submit" type="submit" value="<spring:message code="comUssIonRmm.roughMapInfoDetail.magnifyRoughMap" />" onclick="fn_egov_magnify_roughmap(); return false;" /><!-- 크게보기 -->
		
		<input class="s_submit" type="submit" value='<spring:message code="button.update" />' onclick="fn_egov_updt_roughmap('<c:out value="${roughMap.roughMapId}"/>'); return false;" />
		
		<span class="btn_s"><a href="<c:url value='/com/uss/ion/rmm/deleteRoughMap.do'/>?roughMapId=<c:out value='${roughMap.roughMapId}'/>" onclick="fn_egov_delete_roughmap('<c:out value="${roughMap.roughMapId}"/>'); return false;"><spring:message code="button.delete" /></a></span>
		
		<span class="btn_s"><a href="<c:url value='/com/uss/ion/rmm/selectRoughMapList.do'/>" onclick="fn_egov_inqire_roughmaplist(); return false;"><spring:message code="button.list" /></a></span>
	</div>
	<div style="clear:both;"></div>
</div>
</body>
</html>