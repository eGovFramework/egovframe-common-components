<%
 /**
  * @Class Name : EgovModal.jsp
  * @Description : 모달 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2016.07.13    장동한          최초 생성
  *
  *  @author 2016 표준프레임워크유지보수  개발팀 장동한
  *  @since 2016.07.13
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.EgovWebUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	String sScriptYn = request.getParameter("scriptYn") == null ? "" : (String)request.getParameter("scriptYn"); 
	String sModelName = request.getParameter("modalName") == null ? "" : (String)request.getParameter("modalName"); 
%>
<%if(sScriptYn.equals("Y")){ %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/modal.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/modal.js' />"></script>
<%} %>
<!-- The Modal -->
<div id="<%=EgovWebUtil.clearXSSMaximum(sModelName) %>" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<div class="modal-header">
			<span class="close"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_close.png'/>" /></span>
			<div id="title" class="modal-title"><h2>Modal Header</h2></div>
		</div>
		<div id="body" class="modal-body">
			<p>Modal Body</p>
			<p>Modal Body</p>
			<div style='clear:both;'></div>
		</div>
		<div class="modal-footer">
			
			<div id="footer" >
			<span class="btn_style1 blue"><a href="#">확 인</a></span>
			<span class="btn_style1 blue"><a href="#">확 인</a></span>
			<span class="btn_style1 gray" id='btnModalClose'><a href="#">닫 기</a></span>
			</div>
			
			
		</div>
	</div>
</div>
