<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	 * @Class Name : EgovUploadImageComplte.jsp
	 * @Description : 웹에디터 이미지 upload 기능을 위한 팝업 화면 (기존 insert_image.html 대체)
	 * @Modification Information
	 * @
	 * @  수정일      	수정자          수정내용
	 * @ -----------   --------    ---------------------------
	 * @ 2018.05.31   	신용호          최초 생성
	 *
	 *  @author 공통컴포넌트개발팀 한성곤
	 *  @since 2009.08.26
	 *  @version 1.0 
	 *  @see
	 */
%>
<script type='text/javascript'>
window.parent.CKEDITOR.tools.callFunction('${ckEditorFuncNum}', '${url}', '${msg}');
</script>
