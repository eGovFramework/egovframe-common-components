<%
 /**
  * @Class Name  : EgovCcmZipModify.jsp
  * @Description : EgovCcmZipModify 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀 
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comSymCcmZip.zipVO.title"/></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>${pageTitle } <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cop/bbs/style.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="zip" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_Zip(){
	location.href = "<c:url value='/sym/ccm/zip/EgovCcmZipList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_Zip(form){
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateZip(form)){ 			
			return;
		}else{
			form.submit();
		}
	}
}
-->
</script>
</head>
<body>
<form:form modelAttribute="zip" name="zip" method="post">
<input name="cmd" type="hidden" value="Modify">
	<form:hidden path="zip"/>
	<form:hidden path="sn"/>
	<form:hidden path="ctprvnNm"/>
	<form:hidden path="signguNm"/>
<c:if test="${searchList == '1'}">
	<form:hidden path="emdNm"/>
</c:if>
<c:if test="${searchList == '2'}">
	<form:hidden path="rdmnCode"/>
	<form:hidden path="rdmn"/>
</c:if>
<div id="note" style="width:730px";>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg"/></noscript>
<!-- 상단타이틀 -->

<!-- 상단 타이틀  영역 -->
<h1>
  <c:set var="titleZip"><spring:message code="comSymCcmZip.zipVO.zipUpdate"/></c:set>
  <c:set var="titleRdmn"><spring:message code="comSymCcmZip.zipVO.rdmnUpdate"/></c:set>
    <c:if test="${searchList == '1'}">
     <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle">&nbsp;${titleZip }</h1></td>
    </c:if>
    <c:if test="${searchList == '2'}">
     <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle">&nbsp;${titleRdmn }</h1></td>
    </c:if>

<!-- 등록  폼 영역  -->
<table class="tbl_note" width="700" border="0" cellpadding="0" cellspacing="1" summary="<spring:message code="comSymCcmZip.zipVO.summaryInsert"/>"> <!-- 리건물명과 번지동호를 수정하는 우편번호 수정 테이블이다. -->
<CAPTION style="display: none;">${pageTitle } <spring:message code="title.update" /></CAPTION>
  <c:if test="${searchList == '1'}">
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.zip"/> <span class="pilsu">*</span></th> <!-- 우편번호 -->          
	    <td>
	    	<c:out value='${fn:substring(zip.zip, 0,3)}'/>-<c:out value='${fn:substring(zip.zip, 3,6)}'/>
	    </td>
	  </tr> 
	  <tr> 
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/> <span class="pilsu">*</span></th> <!-- 시도명 -->
	    <td>
	    	<c:out value='${zip.ctprvnNm}'/>
	    </td>    
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.signguNm"/> <span class="pilsu">*</span></th> <!-- 시군구명 -->          
	    <td>
	    	<c:out value='${zip.signguNm}'/>
	    </td>    
	  </tr> 
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.emdNm"/> <span class="pilsu">*</span></th> <!-- 읍면동명 -->          
	    <td>
			<c:out value='${zip.emdNm}'/>    
	    </td>    
	  </tr> 
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><label for="liBuldNm"><spring:message code="comSymCcmZip.zipVO.liBuldNm"/></label></th> <!-- 리건물명 -->           
	    <td>
	    	<input name="liBuldNm" type="text" size="60" value="<c:out value='${zip.liBuldNm}'/>"  maxlength="60" id="liBuldNm" >
	    </td>    
	  </tr> 
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><label for="lnbrDongHo"><spring:message code="comSymCcmZip.zipVO.lnbrDongHo"/></label></th> <!-- 번지동호 -->          
	    <td>
	    	<input name="lnbrDongHo" type="text" size="20" value="<c:out value='${zip.lnbrDongHo}'/>"  maxlength="20" id="lnbrDongHo">
	    </td>    
	  </tr>
  	  <input type=hidden name="rdmnCode" id="rdmnCode" value="0"/>
	  <input type=hidden name="rdmn" id="rdmn" value="0"/> 
  </c:if>
  <c:if test="${searchList == '2'}">
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.zip"/> <span class="pilsu">*</span></th><!-- 우편번호 -->
	    <td><c:out value='${fn:substring(zip.zip, 0,3)}'/>-<c:out value='${fn:substring(zip.zip, 3,6)}'/></td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.rdmnCode"/> <span class="pilsu">*</span></th> <!-- 도로명코드 -->
	    <td><c:out value='${zip.rdmnCode}'/></td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.ctprvnNm"/> <span class="pilsu">*</span></th> <!-- 시도명 -->
	    <td><c:out value='${zip.ctprvnNm}'/>}</td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.signguNm"/> <span class="pilsu">*</span></th> <!-- 시군구명 -->
	    <td><c:out value='${zip.signguNm}'/></td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.rdmn"/> <span class="pilsu">*</span></th> <!-- 도로명 -->
	    <td><c:out value='${zip.rdmn}'/></td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.bdnbrMnnm"/></th> <!-- 건물번호본번 -->
	    <td>
	    <input name="bdnbrMnnm" type="text" size="5" value="<c:out value='${zip.bdnbrMnnm}'/>"  maxlength="5" id="bdnbrMnnm">
	    </td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.bdnbrSlno"/></th> <!-- 건물번호부번 -->
	    <td>
	    <input name="bdnbrSlno" type="text" size="5" value="<c:out value='${zip.bdnbrSlno}'/>"  maxlength="5" id="bdnbrSlno">
	    </td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.buldNm"/></th> <!-- 건물명 -->
	    <td>
	    <input name="buldNm" type="text" size="60" value="<c:out value='${zip.buldNm}'/>"  maxlength="60" id="buldNm">
	    </td>
	  </tr>
	  <tr>
	    <th class="ic_none" width="20%" height="23" scope="row" nowrap><spring:message code="comSymCcmZip.zipVO.detailBuldNm"/></th> <!-- 상세건물명 -->
	    <td>
	    <input name="detailBuldNm" type="text" size="60" value="<c:out value='${zip.detailBuldNm}'/>"  maxlength="60" id="detailBuldNm">
	    </td>
	  </tr>
  	  <input type=hidden name="emdNm" id="emdNm" value="0"/>
  </c:if>
</table>
<table width="700" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="10"></td>
  </tr>
</table>

<!-- 줄간격조정  -->
<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>
<!-- 목록/저장버튼  -->
<div class="txt-cnt mt20">
  <input class="btnStyle02 bg_gray" type="submit" value="<spring:message code="button.list" />" onclick="fn_egov_list_Zip(); return false;"> <!-- 목록 -->
  <input class="btnStyle02" type="submit" value="<spring:message code="button.save" />" onclick="fn_egov_modify_Zip(document.zip); return false;">  <!-- 등록 -->    
  <input name="searchList" type="hidden" value="${searchList}">
</div><div style="clear:both;"></div>
</form:form>
</div>
</body>
</html>
