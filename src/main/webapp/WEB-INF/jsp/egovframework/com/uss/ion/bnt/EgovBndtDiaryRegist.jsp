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
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonBnt.bndtDiaryRegist.title"/></title><!-- 당직일지  등록 -->
<link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/com/button.css' />" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bndtDiary" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fncBndtManageList(){
	location.href = "<c:url value='/uss/ion/bnt/EgovBndtManageList.do'/>";
}


/* ********************************************************
* 멀티입력 처리 함수
******************************************************** */
function fncInsertBndtDiary() {
   var varFrom = document.getElementById("bndtDiary");
   var bndtCeckCd   = varFrom.bndtCeckCd;
   var bndtCeckSe   = varFrom.bndtCeckSe;
   var bndtCeckCdNm = varFrom.bndtCeckCdNm;   
   var checkChckSttus = "";
   var checkMtgPlaces = "";
   var valBndtDiary = "";
   var checkedCount = 0;
   var validatorCount = 0;
   var obj ;

   if(bndtCeckCd.length > 1) {
      for(var i=0; i < bndtCeckCd.length; i++) {

		   if(bndtCeckSe[i].value == "99"){
			   checkChckSttus = document.getElementById("chckSttus"+bndtCeckSe[i].value+bndtCeckCd[i].value).value;
			   if(checkChckSttus == "" || checkChckSttus == null){
				   alert(bndtCeckCdNm[i].value+" <spring:message code="comUssIonBnt.common.validate.fieldRequiredInput"/>");/* 필드는 필수 입력입니다. */
				   validatorCount++;
			   }
		   }
		   else
		   {
            obj = document.getElementsByName("chckSttus"+bndtCeckSe[i].value+bndtCeckCd[i].value);
            for(var j=0; j < obj.length; j++) {
               if(obj[j].checked) checkChckSttus = obj[j].value;
            }
		   }
         valBndtDiary += ((checkedCount==0? "" : "@")+bndtCeckSe[i].value+"$"+bndtCeckCd[i].value+"$"+checkChckSttus);
         checkedCount++;
      }
   } else {

	   if(bndtCeckSe.value == "99"){
		    checkChckSttus = document.getElementById("chckSttus"+bndtCeckSe.value+bndtCeckCd.value).value;
		    if(checkChckSttus == "" || checkChckSttus == null){
			   alert(bndtCeckCdNm.value+" <spring:message code="comUssIonBnt.common.validate.fieldRequiredInput"/>");/* 필드는 필수 입력입니다. */
			   validatorCount++; 
		    }
	   }
	   else{
         obj = document.getElementsByName("chckSttus"+bndtCeckSe.value+bndtCeckCd.value);
         for(var j=0; j < obj.length; j++) {
            if(obj[j].checked) checkChckSttus = obj[j].value;
         }
	   }
      valBndtDiary = bndtCeckSe.value+"$"+bndtCeckCd.value+"$"+checkChckSttus;
   }
   varFrom.diaryForInsert.value=valBndtDiary;
   varFrom.action = "<c:url value='/uss/ion/bnt/insertBndtDiary.do'/>";

   if(validatorCount ==0){
      if(confirm("<spring:message code="common.save.msg" />"))   varFrom.submit(); /* 저장 하시겠습니까? */
   }
}
-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<form:form modelAttribute="bndtDiary" name="bndtDiary" method="post" >
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="<spring:message code="comUssIonBnt.common.submit"/>" title="<spring:message code="comUssIonBnt.common.submit"/>"></div><!-- 전송 -->
<input name="cmd" type="hidden" value="<c:out value='insert'/>"/>
<input name="diaryForInsert" type="hidden" />
<input name="bndtId" type="hidden" value="<c:out value='${bndtDiaryVO.bndtId}'/>"/>
<input name="bndtDe" type="hidden" value="<c:out value='${bndtDiaryVO.bndtDe}'/>"/>

<div class="board">
	<h1><spring:message code="comUssIonBnt.bndtDiaryRegist.title"/></h1><!-- 당직일지  등록 -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				
				<span class="btn_b"><a href="" onclick="fncInsertBndtDiary(); return false;" title='<spring:message code="button.save" />'><spring:message code="button.save" /></a></span>
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
			   <th scope="col"></th>		
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bndtDiaryList}" var="resultInfo" varStatus="status">
			<input name="bndtCeckCd"   type="hidden" value="<c:out value='${resultInfo.bndtCeckCd}'/>"/>
			<input name="bndtCeckSe"   type="hidden" value="<c:out value='${resultInfo.bndtCeckSe}'/>"/>
			<input name="bndtCeckCdNm" type="hidden" value="<c:out value='${resultInfo.bndtCeckCdNm}'/>"/>
			<tr>
				<td><c:out value="${resultInfo.bndtCeckCdNm}"/><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif'/>" alt="<spring:message code="comUssIonBnt.common.requiredInputSign"/>"  width="15" height="15"></td><!-- 필수입력표시 -->
				<c:if test="${resultInfo.bndtCeckSe == '99'}">
				        <td colspan="4">
				           <input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" id="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="text" size="70" value="<c:out value='${resultInfo.chckSttus}'/>" maxlength="2000" style="width:90%;" title="<c:out value="${resultInfo.bndtCeckCdNm}"/>">
				        </td>
				</c:if>
				<c:if test="${resultInfo.bndtCeckSe != '99'}">
					<c:if test="${resultInfo.chckSttus == '1'}"></c:if>
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="1" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.good"/>"   checked></td><!-- 양호 -->
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="2" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.bad"/>"   ></td><!-- 불량 -->
					<c:if test="${resultInfo.chckSttus == '2'}">
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="1" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.good"/>"   ></td><!-- 양호 -->
					   <td><input name="chckSttus${resultInfo.bndtCeckSe}${resultInfo.bndtCeckCd}" type="radio" value="2" title="<spring:message code="comUssIonBnt.common.bndtCeckCd.bad"/>"   checked></td><!-- 불량 -->
					</c:if>
				</c:if>
			</tr>   
			</c:forEach>
		</tbody>
	</table>
</div>
</form:form>
</body>
</html>