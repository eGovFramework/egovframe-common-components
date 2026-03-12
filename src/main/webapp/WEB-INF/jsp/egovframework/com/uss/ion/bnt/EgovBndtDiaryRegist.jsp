<%
/**
 * @Class Name : EgovBndtDiaryRegist.java
 * @Description : EgovBndtDiaryRegist jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.09.27    최 두 영                 다국어처리
 *
 *  @author 이      용
 *  @since 2010.07.20
 *  @version 1.0
 *  @see
 *  
 *  Copyright (C) 2009 by MOPAS  All rights reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtDiaryRegist.title"/></title><!-- 당직일지  등록 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript">
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>";
}
/* ********************************************************
* 멀티입력 처리 함수 - 전체 저장
******************************************************** */
function fncInsertBndtDiary() {
	var varFrom = document.getElementById("bndtDiaryVO");

	// bndtCeckSe 배열의 길이를 구하여 목록 수 확인
	var bndtCeckSeElements = varFrom.querySelectorAll('input[name="bndtCeckSe"]');
	var itemCount = bndtCeckSeElements.length;

	// chckSttus 유효성 검증
	for(var i = 0; i < itemCount; i++) {
		var bndtCeckSeValue = bndtCeckSeElements[i].value;
		var chckSttusInput = varFrom.querySelector('input[name="chckSttus' + i + '"]');
		var chckSttusValue = "";
		var bndtCeckCdNm = "";

		// bndtCeckCdNm 값 가져오기 (에러 메시지용)
		var bndtCeckCdNmInputs = varFrom.querySelectorAll('input[name="bndtCeckCdNm"]');
		if(bndtCeckCdNmInputs && bndtCeckCdNmInputs[i]) {
			bndtCeckCdNm = bndtCeckCdNmInputs[i].value;
		}

		if(chckSttusInput) {
			if(chckSttusInput.type === "text") {
				chckSttusValue = chckSttusInput.value ? chckSttusInput.value.trim() : "";
				// 텍스트 필드가 비어있는지 검증
				if(chckSttusValue === "" || chckSttusValue === null) {
					alert((bndtCeckCdNm || "항목") + "의 점검상태를 입력해주세요.");
					chckSttusInput.focus();
					return false;
				}
			} else if(chckSttusInput.type === "radio") {
				var radioButtons = varFrom.querySelectorAll('input[name="chckSttus' + i + '"][type="radio"]');
				var isChecked = false;
				for(var j = 0; j < radioButtons.length; j++) {
					if(radioButtons[j].checked) {
						chckSttusValue = radioButtons[j].value;
						isChecked = true;
						break;
					}
				}
				// 라디오 버튼이 선택되지 않은 경우 검증 실패
				if(!isChecked || chckSttusValue === "" || chckSttusValue === null) {
					alert((bndtCeckCdNm || "항목") + "의 점검상태를 선택해주세요.");
					if(radioButtons.length > 0) {
						radioButtons[0].focus();
					}
					return false;
				}
			}
		} else {
			// chckSttus input이 존재하지 않는 경우
			alert((bndtCeckCdNm || "항목") + "의 점검상태 입력 필드를 찾을 수 없습니다.");
			return false;
		}
	}

	// 기존 chckSttus hidden input 제거
	var existingChckSttus = varFrom.querySelectorAll('input[name="chckSttus"][type="hidden"]');
	for(var i = 0; i < existingChckSttus.length; i++) {
		existingChckSttus[i].remove();
	}

	// 목록 수만큼 반복하면서 chckSttus 배열에 등록
	for(var i = 0; i < itemCount; i++) {
		var chckSttusValue = "";

		// chckSttus${i} 형태의 input 찾기
		var chckSttusInput = varFrom.querySelector('input[name="chckSttus' + i + '"]');

		if(chckSttusInput) {
			if(chckSttusInput.type === "text") {
				chckSttusValue = chckSttusInput.value ? chckSttusInput.value.trim() : "";
			} else if(chckSttusInput.type === "radio") {
				var radioButtons = varFrom.querySelectorAll('input[name="chckSttus' + i + '"][type="radio"]');
				for(var j = 0; j < radioButtons.length; j++) {
					if(radioButtons[j].checked) {
						chckSttusValue = radioButtons[j].value;
						break;
					}
				}
				// 라디오 버튼이 선택되지 않은 경우 기본값(양호) 설정
				if(chckSttusValue === "") {
					chckSttusValue = "1";
				}
			}
		}

		// hidden input 생성하여 chckSttus 배열에 추가
		var hiddenInput = document.createElement("input");
		hiddenInput.type = "hidden";
		hiddenInput.name = "chckSttus";
		hiddenInput.value = chckSttusValue;
		varFrom.appendChild(hiddenInput);
	}

	varFrom.action = "<c:url value='/uss/ion/bnt/insertBndtDiary.do'/>";

	if(confirm("<spring:message code="common.save.msg" />")) { /* 저장하시겠습니까? */
		varFrom.submit();
	}
}
</script>
</head>
<body>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonBnt.common.submit"/>" title="<spring:message code='comUssIonBnt.common.submit'/>"></div><!-- 전송 -->

<form:form modelAttribute="bndtDiaryVO" name="bndtDiaryVO" method="post" action="${pageContext.request.contextPath}/uss/ion/bnt/insertBndtDiary.do"> 
<input name="cmd" type="hidden" value="<c:out value='insert'/>"/>
<input name="bndtId" type="hidden" value="<c:out value='${bndtDiaryVO.bndtId}'/>"/>
<input name="bndtDe" type="hidden" value="<c:out value='${bndtDiaryVO.bndtDe}'/>"/>

<div class="board">
	<h1><spring:message code="comUssIonBnt.bndtDiaryRegist.title"/></h1><!-- 당직일지  등록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<span class="btn_b"><a href="<c:url value='/uss/ion/bnt/insertBndtDiary.do'/>" onclick="fncInsertBndtDiary(); return false;"><spring:message code="button.save" /></a></span>			
				<span class="btn_b"><a href="<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>" onclick="fncBndtManageList(); return false;"><spring:message code="button.list" /></a></span>
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:40%" />
			<col style="width:30%" />
			<col style="width:30%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCdNm"/></th><!-- 당직체크목록 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCd.good"/></th><!-- 양호 -->
			   <th scope="col"><spring:message code="comUssIonBnt.common.bndtCeckCd.bad"/></th><!-- 불량 -->	
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bndtDiaryList}" var="resultInfo" varStatus="status">
				<input name="bndtCeckCd" type="hidden" value="<c:out value='${resultInfo.bndtCeckCd}'/>" />
				<input name="bndtCeckSe" type="hidden" value="<c:out value='${resultInfo.bndtCeckSe}'/>" />
				<input name="bndtCeckCdNm" type="hidden" value="<c:out value='${resultInfo.bndtCeckCdNm}'/>" />
				<tr>
					<td><c:out value="${resultInfo.bndtCeckCdNm}"/><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="<spring:message code='comUssIonBnt.common.requiredInputSign'/>" width="15" height="15"></td><!-- 필수입력표시 -->
					<c:choose>
						<c:when test="${resultInfo.bndtCeckSe == '99'}">
							<td colspan="2"><input name="chckSttus${status.index}" type="text" size="70" value="<c:out value='${resultInfo.chckSttus}'/>" maxlength="2000" style="width:90%;" title="<c:out value='${resultInfo.bndtCeckCdNm}'/>"></td>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${resultInfo.chckSttus == '2'}">
									<td><input name="chckSttus${status.index}" type="radio" value="1" title="<spring:message code='comUssIonBnt.common.bndtCeckCd.good'/>" ></td><!-- 양호 -->
									<td><input name="chckSttus${status.index}" type="radio" value="2" title="<spring:message code='comUssIonBnt.common.bndtCeckCd.bad'/>" checked></td><!-- 불량 -->
								</c:when>
								<c:otherwise>
									<td><input name="chckSttus${status.index}" type="radio" value="1" title="<spring:message code='comUssIonBnt.common.bndtCeckCd.good'/>" checked></td><!-- 양호 -->
									<td><input name="chckSttus${status.index}" type="radio" value="2" title="<spring:message code='comUssIonBnt.common.bndtCeckCd.bad'/>" ></td><!-- 불량 -->
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</form:form>
</body>
</html>