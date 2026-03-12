<%--
  Class Name : EgovQustnrTmplatManageRegist.jsp
  Description : 설문템플릿 등록 페이지
  Modification Information

       수정일                 수정자            수정내용
    ----------    --------   ---------------------------
    2008.03.09    장동한            최초 생성
    2017.06.26    김예영            표준프레임워크 v3.7 개선
    2020.10.30    신용호            파일업로드 예외처리 수정
    
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQtm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/EgovValidation.js" />"></script>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrTmplatManage(){

	//------------------------------------------
	//------------------------- 첨부파일 등록 Start
	//-------------------------------------------
	var maxFileNum = 1;
	var multi_selector = new MultiSelector( document.getElementById( 'qestnrTmplatImage' ), maxFileNum);
	multi_selector.addElement( document.getElementById( 'qestnrTmplatImage' ) );
	//------------------------- 첨부파일 등록 End
	
	// 첫 입력란에 포커스..
	qustnrTmplatManageVO.qestnrTmplatTy.focus();
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrTmplatManage(){
	location.href = "<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_save_QustnrTmplatManage(form) {
     const fileInput = document.getElementById("qestnrTmplatImage");
		//중복검사 제거 파일이 들어가지 않을 때 
     if (fileInput.files.length === 0) {
         alert("템플릿 유형 이미지를 선택해주세요!");
         fileInput.focus();
         return;
     }

     if (confirm("<spring:message code='common.save.msg'/>")) {
         if (!validateQustnrTmplatManageVO(form)) {
             return;
         }
         form.submit();
     }
 }

/* ********************************************************
 * 선택이미지 미리보기
 ******************************************************** */
function fnImgChange(obj) {
    console.log("함수 호출됨");

    if (!obj.files || obj.files.length === 0) {
        console.log("파일 선택 취소");
        return;
    }

    const file = obj.files[0];
    const fileName = file.name;

    // 공통 컴포넌트로 확장자 검사 (false면 불허용)
    const isAllowed = EgovMultiFilesChecker.checkExtensions("qestnrTmplatImage", "<c:out value='${fileUploadExtensions}'/>");
    // 확인용
    console.log(EgovMultiFilesChecker.checkExtensions("qestnrTmplatImage", ".jpg,.png"));
    if (!isAllowed) {
        obj.value = ""; // 초기화
        document.getElementById("DIV_IMG_VIEW").style.display = "none";
        return;
    }

    console.log("허용 확장자 통과!");

    // 미리보기 영역 보이기
    document.getElementById("DIV_IMG_VIEW").style.display = "block";

    // 파일명 표시
    document.getElementById("uploadFileName").value = fileName;

    // 이미지 미리보기
    if (window.FileReader) {
        const reader = new FileReader();
        reader.onload = function(e) {
            console.log("미리보기 데이터 로드 완료");
            document.getElementById("IMG_VIEW").src = e.target.result;
        };
        reader.readAsDataURL(file);
    } else {
        // IE 8~9 fallback (거의 안 씀)
        const img = document.getElementById("IMG_VIEW");
        img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + obj.value + "', sizingMethod='scale')";
        img.width = 66;
        img.height = 52;
        img.alt = "";
    }
}

</script>

</head>
<body onLoad="fn_egov_init_QustnrTmplatManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">
<form:form modelAttribute="qustnrTmplatManageVO" action="${pageContext.request.contextPath}/uss/olp/qtm/EgovQustnrTmplatManageRegistActor.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrTmplatManage(document.forms[0]); return false;">

<!-- 첨부파일 개수 설정을 위한 Hidden 설정 -->
<!-- <input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="1" /> -->
<!-- <input name="managtCn" type="hidden" value="Testing..."> -->
 

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
		<!-- 템플릿 유형 -->
		<c:set var="title"><spring:message code="comUssOlpQtm.regist.qestnrTmplatTy"/></c:set>
		<tr>
			<th><label for="qestnrTmplatTy">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
  				<form:input type="text" path="qestnrTmplatTy" size="70" cssClass="txaIpt" maxlength="100" title="<spring:message code='comUssOlpQtm.regist.qestnrTmplatTy'/><spring:message code='input.input'/>" /> <!-- title="템플릿유형 입력" -->
      			<div><form:errors path="qestnrTmplatTy"/></div>
			</td>
		</tr>
		<!-- 템플릿 유형 이미지 -->	
		<c:set var="title"><spring:message code="comUssOlpQtm.regist.egovfile"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
			
			<div class="egov_file_box">
				<label for="qestnrTmplatImage" id="qestnrTmplatImage_label"><spring:message code="title.attachedFileSelect" /></label><!-- 파일선택 -->
				<input type="file" id="qestnrTmplatImage" name="qestnrTmplatImage" onchange="fnImgChange(obj)" title="<spring:message code='comUssOlpQtm.regist.qestnrTmplatTy'/><spring:message code='input.cSelect'/>"><!-- title="템플릿유형이미지 선택" -->
	    		<input type="text" id="uploadFileName" value="" readonly style="width:150px;"/><!-- 파일명 보이게 하는 기능 -->
	    	</div>
	    	<div id="DIV_IMG_VIEW" style="display:none;">
		     	<img src="" name="IMG_VIEW" id="IMG_VIEW" align="middle" alt="<spring:message code='comUssOlpQtm.title.image'/><spring:message code='button.preview'/>" title="<spring:message code='comUssOlpQtm.title.image'/><spring:message code='button.preview'/>"><!-- alt="이미지미리보기" title="이미지미리보기" -->
		     	<!-- onLoad="if(this.width>65){this.width=65}" -->
		    </div>
		    <!-- <div id="egovComFileList"></div> -->
			</td>
		</tr>
		<!-- 템플릿 설명 -->
		<c:set var="title"><spring:message code="comUssOlpQtm.regist.qestnrTmplatCn"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:textarea path="qestnrTmplatCn" height="50" rows="50" cols="75" cssClass="txaClass" title="<spring:message code='comUssOlpQtm.regist.qestnrTmplatCn'/><spring:message code='input.input'/>"/><!-- title="템플릿설명 입력" -->
    			<div><form:errors path="qestnrTmplatCn"/></div>
			</td>
		</tr>
		<!-- 템플릿 파일(경로) -->
		<c:set var="title"><spring:message code="comUssOlpQtm.regist.qestnrTmplatCours"/></c:set>
		<tr>
			<th>${title}<span class="pilsu">*</span></th>
			<td class="left">
  				<form:input type="text" path="qestnrTmplatCours" size="73" cssClass="txaIpt" maxlength="100" value="/egovframework/com/uss/olp/qri/template/template" title="<spring:message code='comUssOlpQtm.regist.qestnrTmplatCours'/><spring:message code='input.input'/>"/><!-- title="템플릿파일(경로) 입력" -->
      			<div><form:errors path="qestnrTmplatCours"/></div>
			</td>
		</tr>
	</tbody>
	</table>
	
	<!-- 하단 버튼 -->
	<div class="btn">
		<!-- 등록버튼 -->
		<input type="submit" class="s_submit" value="<spring:message code='button.create' />" title="<spring:message code='button.create' /> <spring:message code='input.button' />" />
		<!-- 목록버튼 -->
		<span class="btn_s"><a href="<c:url value='/uss/olp/qtm/EgovQustnrTmplatManageList.do' />"  title="<spring:message code='button.list' />  <spring:message code='input.button' />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	

<script>

/*onchange가 동작을 안함*/
document.getElementById("qestnrTmplatImage").addEventListener("change",function(){fnImgChange(this)});
/*
document.addEventListener("DOMContentLoaded", function() {
    const fileInput = document.getElementById("qestnrTmplatImage");
    
    if (fileInput) {
        fileInput.addEventListener("change", function() {
            fnImgChange(this);  // 기존 함수 호출
            console.log("onchange 이벤트 정상 작동!");
        });
    } else {
        console.error("ID 'qestnrTmplatImage' 요소를 찾을 수 없습니다!");
    }
});
*/
</script>
</form:form>
</div><!-- div end(wTableFrm)  -->

</body>
</html>
