<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
  * @Class Name : EgovFileList.jsp
  * @Description : 파일 목록화면
  * @Modification Information
  * @
  * @ 수정일   	      수정자		         수정내용
  * @ ----------   -----------   ---------------------------
  * @ 2009.03.26   이삼섭최초 생성      
  *   2011.07.20   옥찬우<Input>   Tag id속성 추가( Line : 68 )
  *   2018.09.11   신용호                 불필요한 function 삭제 - fn_egov_multi_selector_update_delete
  *   2019.12.11   신용호                 KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.26
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css"-->

<script type="text/javascript">

	function fn_egov_downFile(atchFileId, fileSn){
		window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	}

	function fn_egov_deleteFile(atchFileId, fileSn, delTrName) {

		var forms;
		var form;
		forms = document.getElementsByTagName("form");

		for (var i = 0; i < forms.length; i++) {
			if (typeof(forms[i].atchFileId) != "undefined" &&
					typeof(forms[i].fileSn) != "undefined" &&
					typeof(forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}
		
		

		var newForm = document.createElement( 'form' );
		var newfileSn = document.createElement( 'input' );
		var newAtchFileId = document.createElement( 'input' );
		// Chrome 56+ 동적 생성한 form submit 불가 (HTML5)
		// HTML5 표준에선 Browsing contexts(document)에 form 이 연결되어 있지 않으면, form submit을 중단하도록 규정
		// https://www.w3.org/TR/html5/forms.html#constraints 4.10.22.3
		document.body.appendChild(newForm);
		
		newfileSn.setAttribute("name","fileSn");
		newAtchFileId.setAttribute("name","atchFileId");
		
		newfileSn.setAttribute("type","hidden");
		newAtchFileId.setAttribute("type","hidden");

		newfileSn.setAttribute("value",fileSn);
		newAtchFileId.setAttribute("value",atchFileId);
		
		newForm.appendChild( newfileSn );
		newForm.appendChild( newAtchFileId );

		//form = document.forms[0];
		//form.atchFileId.value = atchFileId;
		//form.fileSn.value = fileSn;
		newForm.method = "post";
		newForm.action = "<c:url value='/cmm/fms/deleteFileInfs.do'/>";
		newForm.target = "iframe_egov_file_delete" 
		newForm.submit();
		//console.log('del submit!!');
		var objDelTr = document.getElementById(delTrName);
		objDelTr.parentNode.removeChild(objDelTr);
		//fn_egov_multi_selector_update_delete();
		//console.log('egov_file_view_table delete!!');

	}

	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";
		} else {
			document.getElementById('file_upload_posbl').style.display = "none";
			document.getElementById('file_upload_imposbl').style.display = "block";
		}
	}
</script>

<!-- <form name="fileForm" action="" method="post" >  -->
<input type="hidden" name="atchFileId" value="<c:out value='${atchFileId}'/>">
<input type="hidden" name="fileSn" >
<input type="hidden" name="fileListCnt" id="fileListCnt" value="<c:out value='${fileListCnt}'/>">
<c:set var="fileCount" value="${fn:length(fileList) }" />
<!-- </form>  -->

<!--<title>파일목록</title> -->

	<table id="egov_file_view_table" style="border:0px solid #666;">
		<c:forEach var="fileVO" items="${fileList}" varStatus="status">
		<tr id="egov_file_view_table_tr_${status.count}">
			<td style="border:0px solid #666;text-align:left;padding:0 0 0 0;margin:0 0 0 0;" height="22">
			<c:choose>
				<c:when test="${updateFlag eq 'Y'}">
					<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
					<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_del.png' />" class="cursor" onClick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','egov_file_view_table_tr_${status.count}');" alt="<spring:message code="title.attachedFileDelete" />">
				</c:when>
				<c:otherwise>
					<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')">
					<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
					</a>
				</c:otherwise>
			
			</c:choose>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(fileList) == 0}">
			<tr>
				<td style="border:0px solid #666;padding:0 0 0 0;margin:0 0 0 0;"></td>
			</tr>
	    </c:if>
	</table>
<c:if test="${updateFlag eq 'Y'}">
<div style="display:none;"><iframe name="iframe_egov_file_delete" src=""></iframe></div>
<script type="text/javaScript">
//multi_selector.update_count = <c:out value="${fileCount}"/>;
var _multi_selector;
function fn_egov_multi_selector_update_setting(multi_selector){
	alert("fn_egov_multi_selector_update_setting");
	_multi_selector = multi_selector;
}
function fn_egov_multi_selector_update_delete(){
	_multi_selector.update_count = _multi_selector.update_count -1;
}

/*
window.addEventListener('load', function(){
	multi_selector.update_count = <c:out value="${fileCount}"/>;
});
*/
</script>
</c:if>
