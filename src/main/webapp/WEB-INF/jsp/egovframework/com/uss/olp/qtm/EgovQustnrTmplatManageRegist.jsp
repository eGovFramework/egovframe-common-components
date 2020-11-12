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
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssOlpQtm.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/validator.do'/>"></script>
<validator:javascript formName="qustnrTmplatManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
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
function fn_egov_save_QustnrTmplatManage(form){
	
	var resultExtension = EgovMultiFilesChecker.checkExtensions("qestnrTmplatImage", "<c:out value='${fileUploadExtensions}'/>"); // 결과가 false인경우 허용되지 않음
	if (!resultExtension) return true;
	var resultSize = EgovMultiFilesChecker.checkFileSize("qestnrTmplatImage", 65535); // 파일당 1M까지 허용 (1K=1024), 결과가 false인경우 허용되지 않음
	if (!resultSize) return true;
	
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateQustnrTmplatManageVO(form)){
			return;
		}else{

			if(form.qestnrTmplatImage.value == ""){
			 	alert("<spring:message code='comUssOlpQtm.regist.egovfile'/><spring:message code='comUssOlpQtm.alert.select'/>"); //템플릿유형 이미지를 선택해주세요!
				form.focus();
				return;
			}

			form.submit();
		}
	}
}


/* ********************************************************
 * 선택이미지 미리보기
 ******************************************************** */
function fnImgChange(obj){

	//기존 소스
	if(obj.value != "") {
		
		var pathname = obj.value;
		var ext = pathname.split('.').pop().toLowerCase();
		
		if( "<c:out value='${fileUploadExtensions}'/>.".indexOf(ext+".") != -1 ){
		
			document.getElementById("DIV_IMG_VIEW").style.display = "";
			
			//파일선택 후 파일명 보이기
			var leafname= pathname.split('\\').pop().split('/').pop();
			document.getElementById("uploadFileName").value= leafname;

			//파일선택 후 파일 미리보기
			if ( window.FileReader ) {
				 /* 크롬, 사파리, 파이어폭스, 오페라, IE 10 이상에서는 HTML5 FileReader  이용 */
				var reader = new FileReader();
		        reader.onload = function (e) {
		            //$('#'+preImg).attr('src', e.target.result);
		            document.getElementById("IMG_VIEW").src = e.target.result;
		        };
		        reader.readAsDataURL(obj.files[0]);
		        //return input.files[0].name;  // 파일명 return
			} else {
				
				/* IE 8, 9에서 가능하나 권장하지 않음 */
				var img = document.getElementById("IMG_VIEW");
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
                    + obj.value + "', sizingMethod='scale')"; //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
                /* IE 8, 9에서 불가능 */
                //document.getElementById("IMG_VIEW").src = obj.value;
                img.width = 66;
                img.height = 52;
                img.alt = "";
			}
		}else{
		   alert("<spring:message code='comUssOlpQtm.alert.image'/>"); //이미지 형식의 확장자만 업로드 가능합니다!
		   
	 	}

	}
	
}

</script>

</head>
<body onLoad="fn_egov_init_QustnrTmplatManage();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="wTableFrm">
<form:form commandName="qustnrTmplatManageVO" action="${pageContext.request.contextPath}/uss/olp/qtm/EgovQustnrTmplatManageRegistActor.do" method="post" enctype="multipart/form-data" onSubmit="fn_egov_save_QustnrTmplatManage(document.forms[0]); return false;">

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
				<input type="file" id="qestnrTmplatImage" name="qestnrTmplatImage" onChange="fnImgChange(this)" title="<spring:message code='comUssOlpQtm.regist.qestnrTmplatTy'/><spring:message code='input.cSelect'/>"><!-- title="템플릿유형이미지 선택" -->
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
	


</form:form>
</div><!-- div end(wTableFrm)  -->


</body>
</html>
