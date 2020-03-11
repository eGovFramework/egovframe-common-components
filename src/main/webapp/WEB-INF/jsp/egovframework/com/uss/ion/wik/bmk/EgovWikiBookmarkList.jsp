<%--
  Class Name : EgovWikiBookmarkList.jsp
  Description : 위키북마크 목록조회
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.10.20    장동한          최초 생성
     2018.10.30    이정은          공통컴포넌트 3.8 개선

    author   : 공통서비스 개발팀 장동한
    since    : 2010.10.20

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><spring:message code="ussIonWikBmk.wikiBookmarkList.wikiBookmarkList"/></title><!-- 위키북마크 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listFormS.pageIndex.value = pageNo;
	document.listFormS.action = "<c:url value='/uss/ion/wik/bmk/listWikiBookmark.do'/>";
   	document.listFormS.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_WikiBookmark(){
	var vForm = document.listFormS;
	vForm.pageIndex.value = "1";
	vForm.action = "<c:url value='/uss/ion/wik/bmk/listWikiBookmark.do'/>";
	vForm.submit();

}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_WikiBookmark(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.listFormS.checkList.checked = checkAllValue;
	}else{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
	}

}

/* ********************************************************
* 체크 박스 선택건수
******************************************************** */
var g_nDelCount;
function fn_egov_delCnt_WikiBookmark(){

	g_nDelCount = 0;
	var FLength = document.getElementsByName("checkList").length;

	//undefined
	if(FLength == 1){
		if(document.listFormS.checkList.checked == true){g_nDelCount++;}
	}else{
			for(var i=0; i < FLength; i++){
				if(document.getElementsByName("checkList")[i].checked == true){
					g_nDelCount++;
					}
			}
	}

	return g_nDelCount;

}
/* ********************************************************
* 목록 삭제
******************************************************** */
function fn_egov_delete_WikiBookmark(){
	var vForm = document.listFormS;

	if(fn_egov_delCnt_WikiBookmark() == 0){
		alert("<spring:message code="ussIonWikBmk.wikiBookmarkList.chooseDelete"/>"); /* 삭제할 목록을 선택해주세요! */
		document.getElementById('checkAll').focus();
		return;
		
		}else{
				if(confirm("<spring:message code="ussIonWikBmk.wikiBookmarkList.confirmDelete"/>")){/* 선택된 정보를 삭제 하시겠습니까? */
					
					vForm.action = "<c:url value='/uss/ion/wik/bmk/listWikiBookmark.do'/>";
					vForm.cmd.value = 'del';
					vForm.submit();
				}
		}

}
</script>
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}

a:link, a:visited, a:hover, a:active  { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }

td {font-family: "돋움"; font-size: 9pt; color:#595959; font-weight:normal;}
th {font-family: "돋움"; font-size: 9pt; color:#000000; font-weight:normal;}

img {
border : 0;
}
table {
margin : 0;
padding : 0;
}
h1 {
	font-size:12px;
}
caption {
	visibility:hidden;
	font-size:0;
	height:0;
	margin:0;
	padding:0;
	line-height:0;
}
.t_strong {
font-weight : bold;
}

.noScriptTitle {
font-size : 17px;
color : blue;
text-align : center;
font-family : "돋움";
font-weight : bold;
padding-bottom : 12px;
}

.TopMenuBg {
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/menu_bg.jpg'/>');
background-repeat : no-repeat;
}
.LeftMenuTitle {
font-size : 14px;
color : #fff;
text-align : center;
font-family : "돋움";
font-weight : bold;
padding-top : 20px;
padding-bottom : 3px;
}
.LeftMenuWelcome {
font-size : 12px;
color : #e6e6e6;
text-align : center;
font-family : "돋움";
padding-top : 6px;
}
.LeftMenuBg {
font-size : 12px;
color : #7f9db9;
text-align : left;
font-family : "돋움";
font-weight : bold;
padding-top : 3px;
padding-left : 50px;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/left_menu_bg.gif'/>');
}
.LeftMenuBtn {
text-align : center;
padding-top : 5px;
padding-bottom : 5px;
}
.LeftMenuBgList {
font-size : 12px;
color : #fff;
text-align : left;
font-family : "돋움";
padding-top : 3px;
padding-left : 60px;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/left_bg.gif'/>');
}
.QuickLink {
font-size : 11px;
color : #7794be;
text-align : right;
font-family : "돋움";
padding-top : 3px;
}
.PageTitle {
color : #000000;
text-align : left;
font-family : "돋움";
font-weight : bold;
padding-left : 45px;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/page_title.gif'/>');
}
.PageNumber {
text-align : center;
font-family : "돋움";
}
.checkbox {
background-color : #fff;
}
.BottomStyle {
font-size : 11px;
color : #999999;
font-family : dotum;
}
.LoginIdText {
margin-top : 80px;
margin-left : 400px;
font-size : 12px;
font-family : dotum;
width : 190px;
}
.LoginPassText {
margin-left : 400px;
font-size : 12px;
font-family : dotum;
width : 180px;
}
.LoginJoinBtn {
margin-top : 5px;
margin-left : 400px;
font-size : 12px;
font-family : dotum;
width : 180px;
}
.TopMenuBg {
font-size : 12px;
color : #888888;
font-family : "돋움";
}
.TopMenuBg a:link {
font-size : 12px;
color : #888888;
font-family : "돋움";
}
.TopMenuBg a:visited {
font-size : 12px;
color : #888888;
font-family : "돋움";
}
.TopMenuBg a:hover {
font-size : 12px;
color : #4791a5;
font-family : "돋움";
font-weight : bold;
}
.TopMenuBg a:active {
font-size : 12px;
color : #86cd00;
font-family : "돋움";
font-weight : bold;
}
.LeftMenuBgList {
font-size : 12px;
color : #fff;
font-family : "돋움";
}
.LeftMenuBgList a:link {
font-size : 12px;
color : #fff;
font-family : "돋움";
}
.LeftMenuBgList a:visited {
font-size : 12px;
color : #fff;
font-family : "돋움";
}
.LeftMenuBgList a:hover {
font-size : 12px;
color : #fff;
font-family : "돋움";
font-weight : bold;
}
.LeftMenuBgList a:active {
font-size : 12px;
color : #fff;
font-family : "돋움";
font-weight : bold;
}
.LeftMenuBg {
font-size : 12px;
color : #8594a8;
font-family : "돋움";
}
.LeftMenuBg a:link {
font-size : 12px;
color : #8594a8;
font-family : "돋움";
}
.LeftMenuBg a:visited {
font-size : 12px;
color : #8594a8;
font-family : "돋움";
}
.LeftMenuBg a:hover {
font-size : 12px;
color : #445b7b;
font-family : "돋움";
font-weight : bold;
}
.LeftMenuBg a:active {
font-size : 12px;
color : #445b7b;
font-family : "돋움";
font-weight : bold;
}
.QuickLink {
font-size : 11px;
color : #7794be;
font-family : "돋움";
}
.QuickLink a:link {
font-size : 11px;
color : #7794be;
font-family : "돋움";
}
.QuickLink a:visited {
font-size : 11px;
color : #5877a4;
font-family : "돋움";
}
.QuickLink a:hover {
font-size : 11px;
color : #5877a4;
font-family : "돋움";
font-weight : bold;
}
.QuickLink a:active {
font-size : 11px;
color : #5877a4;
font-family : "돋움";
font-weight : bold;
}
.table_listA {
margin-top : 20px;
margin-left : 8px;
padding : 0 3px 0 3px;
height : 30px;
font : 11px Dotum;
color : #626262;
}
.table_listA th {
margin-top : 10px;
height : 40px;
font : 11px Dotum;
font-weight : bold;
color : #4f657d;
background : url('<c:url value='/images/egovframework/com/cmm/uss/umt/bg_list_th.gif'/>');
}
.table_listA tbody {
height : 35px;
font : 11px Dotum;
color : #828282;
}
.table_listA a:link {
font-size : 11px;
color : #828282;
font-family : "돋움";
text-decoration : none;
}
.table_listA a:visited {
font-size : 11px;
color : #828282;
font-family : "돋움";
text-decoration : none;
}
.table_listA a:hover {
font-size : 11px;
color : #a7a7a7;
font-family : "돋움";
text-decoration : none;
}
.table_listA a:active {
font-size : 11px;
color : #828282;
font-family : "돋움";
text-decoration : none;
}
th {
font-family : "돋움";
font-size : 9pt;
color : #000000;
font-weight : normal;
}
textarea, input, select {
padding-left : 2px;
color : #555555;
border : 1px solid #9bbed2;
font-family : "돋움";
font-size : 9pt;
color : #595959;
}
.lt_text {
font-size : 9pt;
color : #000000;
font-family : "돋움, Arial";
height : 24px;
text-align : left;
vertical-align : middle;
padding-left : 10px;
padding-right : 10px;
}
.lt_text2 {
font-size : 9pt;
color : #444444;
font-family : "돋움, Arial";
height : 30px;
text-align : left;
vertical-align : middle;
padding-left : 10px;
padding-right : 10px;
font-weight : bold;
}
.lt_text3 {
font-size : 9pt;
color : #000000;
font-family : "돋움, Arial";
height : 24px;
text-align : center;
vertical-align : middle;
}
.lt_text4 {
font-size : 9pt;
color : #000000;
font-family : "돋움, Arial";
text-align : center;
vertical-align : middle;
}
.lt_text5 {
font-size : 9pt;
color : #000000;
font-family : "돋움, Arial";
height : 24px;
text-align : left;
vertical-align : top;
padding-left : 10px;
padding-right : 10px;
padding-top : 10px;
padding-bottom : 10px;
}
.button {
border-style : none;
background-color : transparent;
font-size : 12px;
}
textarea {
color : #000000;
border-color : #9cbed3;
border-style : solid;
}
input.cb {
background-color : transparent;
}
.input-r {
border : 1px solid #9bbed2;
text-align : right;
}
multilinegrid {
width : 780px;
border-color : #9f9f9f !important ;
border-style : solid !important ;
}
select {
color : #000000;
border-color : #9cbed3;
border-style : solid;
}
select1 {
color : #000000;
border-color : #9cbed3;
border-style : solid;
}
.select12 {
color : #000000;
border-color : #9cbed3;
border-style : solid;
}
select1[appearance="full"] {
color : #000000;
border-color : #9cbed3;
border-style : solid;
}
select[appearance="full"] {
color : #000000;
border-color : #9cbed3;
border-style : solid;
}
.check2 {
color : #000000;
border-color : #9cbed3;
background-color : transparent !important ;
border-style : none !important ;
}
.radio2 {
color : #000000;
border-color : #9cbed3;
background-color : #eaeaea !important ;
border-style : none !important ;
}
.group_pagenav {
vertical-align : middle;
background-color : transparent !important ;
border-style : none;
}
.page_prevend {
width : 13px;
height : 13px;
top : 3px;
background-color : transparent;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/icon/icon_prevend.gif'/>');
border-style : none;
}
.page_prev {
width : 13px;
height : 13px;
top : 3px;
background-color : transparent;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/icon/icon_prev.gif'/>');
border-style : none;
}
.page_nextend {
width : 13px;
height : 13px;
top : 3px;
background-color : transparent;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/icon/icon_nextend.gif'/>');
border-style : none;
}
.page_next {
width : 13px;
height : 13px;
top : 3px;
background-color : transparent;
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/icon/icon_next.gif'/>');
border-style : none;
}
.page_no {
width : 30px;
height : 13px !important ;
top : 3px;
text-align : center;
background-color : transparent;
border-style : none;
}
.page_sep {
width : 13px;
height : 13px !important ;
top : 3px;
text-align : center;
background-color : transparent;
border-style : none;
}
.group_pagenav > caption {
vertical-align : top !important ;
background-color : transparent !important ;
border-style : none !important ;
padding-left : 2px !important ;
padding-top : 1px !important ;
text-align : left !important ;
}
.location {
font-family : "돋움";
font-size : 8pt;
color : #6d6d6d;
padding-top : 1px;
padding-left : 3px;
vertical-align : middle;
}
.title {
font-family : "돋움";
font-size : 9pt;
color : #000000;
font-weight : bold;
vertical-align : middle;
}
.title_left {
font-family : "돋움";
font-size : 9pt;
color : #000000;
font-weight : bold;
vertical-align : middle;
text-align : left;
}
.title_wh {
font-family : "돋움";
font-size : 9pt;
color : #ffffff;
font-weight : bold;
vertical-align : middle;
}
.title_wh_b {
font-family : "돋움";
font-size : 9pt;
color : #ffffff;
font-weight : bold;
vertical-align : middle;
}
.required_text {
font-family : "돋움";
font-size : 9pt;
color : #2e4b90;
font-weight : bold;
text-align : right;
vertical-align : middle;
}
.text1 {
color : #ff8903;
}
.text2 {
color : #a2a2a2;
}
.table-line {
border-top : 2px solid #1a90d8;
border-bottom : 1px solid #bababa;
border-collapse : collapse;
}
.table-line th {
border-bottom : 1px solid #a3a3a3;
background-color : #e4eaf8;
height : 20px;
}
.table-line td {
border-bottom : 1px solid #e0e0e0;
background-color : #f7f7f7;
height : 20px;
}
.table-list {
border-top : 2px solid #1a90d8;
border-bottom : 1px solid #bababa;
border-collapse : collapse;
table-layout : fixed;
}
.table-list th {
border-bottom : 1px solid #a3a3a3;
background-color : #e4eaf8;
height : 20px;
}
.table-list td {
border-bottom : 1px solid #e0e0e0;
background-color : #f7f7f7;
height : 20px;
overflow : hidden;
}
.table-register {
border-top : 1px solid #d2d4d1;
border-bottom : 1px solid #d2d4d1;
border-left : 1px solid #d2d4d1;
border-right : 1px solid #d2d4d1;
border-collapse : collapse;
}
.table-register th {
background-color : #e4eaf8;
text-align : right;
}
.table-register td {
background-color : #f7f7f7;
}
.readOnlyClass {
background-color : #eef1f7;
}
.table-trans {
border-left : 0 solid;
border-right : 0 solid;
border-bottom : 0 solid;
border-top : 0 solid;
border-collapse : collapse;
}
.table-trans td {
border : 0 solid;
padding-top : 0;
padding-bottom : 0;
padding-left : 0;
padding-right : 0;
border : 0 solid;
border-collapse : collapse;
}
.divDotText {
overflow : hidden;
}
.error {
color : #ff0000;
}
.txaClass {
width : 99%;
}
.txaIpt {
width : 99%;
}
.txaIpt60 {
width : 60px;
}
.btnBackground {
background-image : url('<c:url value='/images/egovframework/com/cmm/uss/umt/button/bu2_bg.gif'/>');
}


</style>

</head>
<body>

<div class="board">
<form name="listFormS" action="<c:url value='/uss/ion/wik/bmk/listWikiBookmark.do'/>" method="post">
	<h1><spring:message code="ussIonWikBmk.wikiBookmarkList.wikiBookmarkList"/></h1><!-- 위키북마크 목록 -->
	<span><spring:message code="ussIonWikBmk.wikiBookmarkList.bookmarkgd"/></span><!-- 위키북마크 등록은 JSP WIki 설치가 필요합니다.(자세한 내용은 위키가이드 참고) -->

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="ussIonWikBmk.wikiBookmarkList.bookmarkNm"/> : </label><!-- 북마크명 -->
				<input class="s_input2 vat" name="searchKeyword" type="text" value="<c:out value='${searchKeyword}'/>" size="10" maxlength="35" onkeypress="press();" onkeyup="if(window.event.keyCode==13){fn_egov_search_WikiBookmark(); return false;}" title="검색어" />
				
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fn_egov_search_WikiBookmark(); return false;" />
				<input class="s_btn" type="submit" value="<spring:message code="button.delete"/>" title="<spring:message code="button.delete"/>" onclick="fn_egov_delete_WikiBookmark(); return false;" /><!-- 삭제 -->
			</li>
		</ul>
	</div>

	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="width:50px" />
			<col style="width:50px" />
			<col style="" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><input type="checkbox" name="checkAll" id="checkAll" title="전체선택" value="1" onclick="fn_egov_checkAll_WikiBookmark();"></th>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="ussIonWikBmk.wikiBookmarkList.bookmarkNm"/></th><!-- 북마크명 -->
			</tr>
		</thead>
		<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="3">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
			</c:if>
			<%-- 데이터를 화면에 출력해준다 --%>
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td>
					<input type="checkbox" name="checkList" title="선택" value="${resultInfo.wikiBkmkId}" />
				</td>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td style="word-break;break-all">
					<div style="visibility:hidden;display:none;"><a href="#LINK_PAGE${status.count}"></a></div>
					<a href="<c:url value="../../../../../JSPWiki/Wiki.jsp"><c:param name="page" value="${resultInfo.wikiBkmkNm}"/></c:url>" target="_blank"><c:out value="${resultInfo.wikiBkmkNm}"/></a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>

<input name="cmd" type="hidden" value="">
<input name="searchCondition" type="hidden" value="A.WIKI_BKMK_NM">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</DIV>

</body>
</html>