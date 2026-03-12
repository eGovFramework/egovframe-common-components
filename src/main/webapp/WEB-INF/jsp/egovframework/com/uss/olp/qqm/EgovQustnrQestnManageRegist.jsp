<%--
  Class Name : EgovQustnrQestnManageRegist.jsp
  Description : 설문문항 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2017.07.17    김예영          표준프레임워크 v3.7 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.19

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQqm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/EgovValidation.js'/>" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery-1.12.4.min.js'/>"></script>  <!-- jQuery core -->
<script src="<c:url value='/js/egovframework/com/cmm/jquery-ui_1.12.1.js'/>"></script>  <!-- jQuery UI -->
<link rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jquery-ui_1.12.1.css'/>">
<c:url var="resistUrl" value='/uss/olp/qqm/EgovQustnrQestnManageRegist.do' />
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 모달창 설정
 ******************************************************** */
const QUSTNR_POPUP_CONFIG = {
	    manage: {
	        title: "설문 관리 목록",
	        baseUrl: "<c:url value='/uss/olp/qmc/EgovQustnrManageListPopup.do' />"
	    }
	};
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrQestnManage(){

	//document.getElementById("qestnrBeginDe").value = "2008-01-01";
	//document.getElementById("qestnrEndDe").value = "2008-01-30";
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrQestnManage(){
	var varFrom = document.getElementById("qustnrQestnManageVO");
	varFrom.action =  "<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />";
	varFrom.submit();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrQestnManage(form){
	var vform = document.getElementById("qustnrQestnManageVO")
	if(confirm("<spring:message code='common.save.msg' />")){

		/* if(form.qestnrCn.value == "" ||
		   form.getElementById("qestnrTmplatId").value == "" ||
		   form.getElementById("qestnrId").value == ""
				){
			alert("<spring:message code='comUssOlpQqm.regist.qestnrCn'/><spring:message code='comUssOlpQqm.alert.input'/>"); //설문지정보(제목)를  입력해주세요!
			//form.getElementById("qestnrCn").focus();
			return;
		} */
		
		if(document.getElementById("qestnrCn").value == "" ||
				document.getElementById("qestnrTmplatId").value == "" ||
				document.getElementById("qestnrId").value == ""
						){
					alert("<spring:message code='comUssOlpQqm.regist.qestnrCn'/><spring:message code='comUssOlpQqm.alert.input'/>"); //설문지정보(제목)를  입력해주세요!
					console.log("qestnrCn: "+ $("#qestnrCn").val());
					console.log("qestnrTmplatId: "+ $("#qestnrTmplatId").val());
					console.log("qestnrId: "+ $("#qestnrId").val());
					//form.getElementById("qestnrCn").focus();
					return;
		} 		
		
		//if(!validateQustnrQestnManageVO(form)){
		if(!validateQustnrQestnManageVO(vform)){
			return;
		}else{
			
			//form.submit();
			vform.submit();
		}
	}
}
</script>

</head>
<body onLoad="fn_egov_init_QustnrQestnManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form modelAttribute="qustnrQestnManageVO" id="qustnrQestnManageVO" action="${pageContext.request.contextPath}/uss/olp/qqm/EgovQustnrQestnManageRegist.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrQestnManage(document.forms[0]); return false;">
 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>
	
	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code='common.summary.inqire' arguments='${pageTitle}' />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width:25%;">
		<col style="width: ;">		
	</colgroup>
	<tbody >
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" />c</c:set>
		<!-- 설문지 정보(제목) -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnrCn"/></c:set>
		<tr>
			<th><label for="qestnrCn">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<input name="qestnrCn" id="qestnrCn" title="<spring:message code='comUssOlpQqm.regist.qestnrCn'/><spring:message code='input.input'/>" type="text" size="73" value="${qestnrInfo.qestnrSj}" maxlength="2000" style="width:300px;" readonly="readonly"><!-- title="설문지정보(제목) 입력" -->
      			<a href="#" onclick="openQustnrModal('manage');">
      			<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" style="border:0px" alt="<spring:message code='comUssOlpQqm.regist.qestnrCn'/>" title="<spring:message code='comUssOlpQqm.regist.qestnrCn'/>"><!-- alt="설문지정보(제목)" title="설문지정보(제목)" -->
      			</a>
      			<div><form:errors path="qestnrTmplatId"/></div>
     			<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qustnrQestnManageVO.qestnrTmplatId}">
      			<input name="qestnrId" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
      			<input name="searchMode" id="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">
      			
			</td>
		</tr>
		<!-- 질문순번 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnSn"/></c:set>
		<tr>
			<th><label for="qestnSn">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<input name="qestnSn" id="qestnSn" type="text" size="50" value="1" maxlength="10" style="width:60px;" title="<spring:message code='comUssOlpQqm.regist.qestnSn'/><spring:message code='input.input'/>"><!-- title="질문순번 입력" -->
      			<div><form:errors path="qestnSn"/></div>
			</td>
		</tr>
		<!-- 질문유형 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnTyCode"/></c:set>
		<tr>
			<th><label for="qestnTyCode">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<select name="qestnTyCode" title="<spring:message code='comUssOlpQqm.regist.qestnTyCode'/><spring:message code='input.cSelect'/>"><!-- title="질문유형 선택" -->
				<option value=""><spring:message code="input.cSelect"/></option>
				<c:forEach items="${cmmCode018}" var="comCodeList" varStatus="status">
				<option value="${comCodeList.code}">${comCodeList.codeNm}</option>
				</c:forEach>
				</select>
				<div><form:errors path="qestnTyCode"/></div>
			</td>
		</tr>
		<!-- 질문내용 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.qestnCn"/></c:set>
		<tr>
			<th><label for="qestnCn">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<textarea name="qestnCn" id="qestnCn" class="textarea"  cols="75" rows="10"  style="width:99%;" title="<spring:message code='comUssOlpQqm.regist.qestnCn'/><spring:message code='input.input'/>"></textarea><!-- title="질문내용 입력" -->
      			<div><form:errors path="qestnCn"/></div>
			</td>
		</tr>
		<!-- 최대선택건수 -->
		<c:set var="title"><spring:message code="comUssOlpQqm.regist.mxmmChoiseCo"/></c:set>
		<tr>
			<th><label for="mxmmChoiseCo">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<select name="mxmmChoiseCo" title="<spring:message code='comUssOlpQqm.regist.mxmmChoiseCo'/><spring:message code='input.cSelect'/>"><!-- title="최대선택건수 선택" -->
		       	<option value="1">1</option>
		       	<option value="2">2</option>
		       	<option value="3">3</option>
		       	<option value="4">4</option>
		       	<option value="5">5</option>
		       	<option value="6">6</option>
		       	<option value="7">7</option>
		       	<option value="8">8</option>
		       	<option value="9">9</option>
		       	<option value="10">10</option>
		      	</select>
		      	<div><form:errors path="mxmmChoiseCo"/></div>
			</td>
		</tr>
		
	</tbody>
	</table>	
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 등록버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code='button.create' />" title="<spring:message code='button.create' /> <spring:message code='input.button' />" />
		<!-- 목록버튼 -->
		<span class="btn_s"><a href="<c:url value='/uss/olp/qqm/EgovQustnrQestnManageList.do' />"  title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>

</div><!-- div end(wTableFrm)  -->


<input name="cmd" type="hidden" value="<c:out value='save'/>">
<!-- 추가한 input hidden -->
<%-- <input type="hidden" id="qestnTyCode" value="${cmmCode018.code}"> --%>
</form:form>

<!-- 모달 컨테이너  -->
<div id="qestnrModal" title="설문 항목 선택" style="display:none;">
    <iframe id="qestnrModalFrame"
            src=""
            style="width:100%; height:100%; border:0;">
    </iframe>
</div>
<script>
/*jquery + iframe 모달*/
$(document).ready(function() {
    $("#qestnrModal").dialog({
        autoOpen: false,
        modal: true,
        width: 800,
        height: 600,
        resizable: false,
        close: function() {
            $("#qestnrModalFrame").attr("src", "about:blank"); // 메모리 정리
        }
    });
});

//모달 열기 함수 레거시 url 사용
function openQustnrModal(type) {
  const config = QUSTNR_POPUP_CONFIG[type];
  if (!config) {
      console.error("알 수 없는 팝업 타입:", type);
      return;
  }

  let url = config.baseUrl;

  if (config.searchCondition && config.getKeyword) {
      const keyword = config.getKeyword();

      if (!keyword) {
          console.log("url 필수 값이 없습니다.");
          return;
      }

      url +=
          "?searchCondition=" + encodeURIComponent(config.searchCondition) +
          "&searchKeyword=" + encodeURIComponent(keyword);
  }

  $("#qestnrModal").dialog("option", "title", config.title);
  $("#qestnrModalFrame").attr("src", url);
  $("#qestnrModal").dialog("open");
}

</script>

</body>
</html>
