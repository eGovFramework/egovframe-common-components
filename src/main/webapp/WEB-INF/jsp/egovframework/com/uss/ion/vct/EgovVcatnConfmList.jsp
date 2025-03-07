<%
/**
 * @Class Name : EgovVcatnConfmList.java
 * @Description : EgovVcatnConfmList jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2010.07.20    이      용                최초 생성
 * @ 2018.08.14    최 두 영     퍼블리싱 점검
 * @ 2018.09.18    최 두 영     다국어처리
 *
 *  @author 이      용
 *  @since 2010.08.05
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
<%@ page import="egovframework.com.utl.fcc.service.EgovDateUtil" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title><spring:message code="comUssIonVct.vcatnConfmList.title"/></title><!-- 휴가승인 목록 -->
<link href="<c:url value="/css/egovframework/com/com.css"/>" rel="stylesheet" type="text/css">
<link href="<c:url value="/css/egovframework/com/button.css"/>" rel="stylesheet" type="text/css">
<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
 /*설명 : 휴가승인 목록 페이지 조회 */
 function linkPage(pageNo){
	var varForm				 = document.all["listForm"];
	varForm.searchCondition.value = "1";
	varForm.pageIndex.value = pageNo;
	varForm.action = "<c:url value='/uss/ion/vct/EgovVcatnConfmList.do'/>";
	varForm.submit();
 }

/* ********************************************************
 * 조회 처리 
 ******************************************************** */
 /*설명 : 목록 조회 */
 function fncSelectVcatnConfmList(pageNo){
	 var varForm				 = document.all["listForm"];

	 if(varForm.searchMonth.value !=""){
		 if(varForm.searchYear.value ==""){
			 alert("<spring:message code="comUssIonVct.vcatnConfmList.validate.searchYear"/>");/* 전체연도에 월만 조회할 수 없습니다. 연도는 선택해주세요 */
			 return;
		 } 
	 }
	 
	 varForm.pageIndex.value = pageNo;
	 varForm.action = "<c:url value='/uss/ion/vct/EgovVcatnConfmList.do'/>";
	 varForm.submit();
 }

/* ********************************************************
 * 승인처리회면 호출함수
 ******************************************************** */
function fncVcatnManageDetail(applcntId,vcatnSe,bgnde,endde,infrmlSanctnId) {
	var varForm				 = document.all["listForm"];
	varForm.applcntId.value  = applcntId;
	varForm.vcatnSe.value    = vcatnSe;
	varForm.bgnde.value      = bgnde.replace("-","");
	varForm.endde.value      = endde.replace("-","");
	varForm.infrmlSanctnId.value = infrmlSanctnId;
	varForm.action           = "<c:url value='/uss/ion/vct/EgovVcatnConfm.do'/>";
	varForm.submit();
}

-->
</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="board">
	<h1><spring:message code="comUssIonVct.vcatnConfmList.vcatnManageList"/></h1><!-- 휴가승인관리 목록 -->
	
	<form name="listForm" action="<c:url value='/uss/ion/vct/EgovVcatnConfmList.do'/>" method="post">
	<input type="hidden" name="searchCondition">
	<input type="hidden" name="applcntId">
	<input type="hidden" name="vcatnSe">
	<input type="hidden" name="bgnde">
	<input type="hidden" name="endde">
    <input type="hidden" name="infrmlSanctnId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty vcatnManageVO.pageIndex }">1</c:if><c:if test="${!empty vcatnManageVO.pageIndex }"><c:out value='${vcatnManageVO.pageIndex}'/></c:if>">
	

	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />"><!-- 이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다. -->
		<ul>
			<li>
				<label for=""><spring:message code="comUssIonVct.vcatnConfmList.searchConfmAt"/> : </label><!-- 진행구분 -->
				<select name="searchConfmAt" title="<spring:message code="comUssIonVct.vcatnConfmList.searchConfmAt"/>"><!-- 진행구분 -->
			       	<option value="" <c:if test="${vcatnManageVO.searchConfmAt eq '' }">selected</c:if>><spring:message code="comUssIonVct.vcatnConfmList.selectedAll"/></option><!-- 전체 -->
			       	<option value="A" <c:if test="${vcatnManageVO.searchConfmAt eq 'A' }">selected</c:if>><spring:message code="comUssIonVct.vcatnConfmList.searchConfmAt.A"/></option><!-- 신청 -->
			       	<option value="C" <c:if test="${vcatnManageVO.searchConfmAt eq 'C' }">selected</c:if>><spring:message code="comUssIonVct.vcatnManageList.confmAt.C"/></option><!-- 승인 -->
			       	<option value="R" <c:if test="${vcatnManageVO.searchConfmAt eq 'R' }">selected</c:if>><spring:message code="comUssIonVct.vcatnManageList.confmAt.R"/></option><!-- 반려 -->
		      	</select>
		      	
		      	<label for="" style="margin-left:15px"><spring:message code="comUssIonVct.vcatnConfmList.searchConfmAt.vcatnDay"/> : </label><!-- 휴가일자 -->
		      	<select name="searchYear" title="<spring:message code="comUssIonVct.vcatnConfmList.searchYear"/>"><!-- 연도 -->
		    		<option value="" <c:if test="${vcatnManageVO.searchYear eq '' }">selected</c:if>><spring:message code="comUssIonVct.vcatnConfmList.selectedAll"/></option><!-- 전체 -->
		            <c:forEach items="${yearList}" var="result" varStatus="status">
			       	   <option value="<c:out value="${result }"/>"  <c:if test="${vcatnManageVO.searchYear eq result}">selected</c:if>><c:out value="${result }"/></option>
		            </c:forEach>
	            </select> <spring:message code="comUssIonVct.vcatnConfmList.search"/><!-- 년 -->
		        <select name="searchMonth" title="월">
			       	<option value="" <c:if test="${vcatnManageVO.searchMonth eq '' }">selected</c:if>><spring:message code="comUssIonVct.vcatnConfmList.selectedAll"/></option><!-- 전체 -->
			       	<option value="01" <c:if test="${vcatnManageVO.searchMonth eq '01' }">selected</c:if>>01</option>
			       	<option value="02" <c:if test="${vcatnManageVO.searchMonth eq '02' }">selected</c:if>>02</option>
			       	<option value="03" <c:if test="${vcatnManageVO.searchMonth eq '03' }">selected</c:if>>03</option>
			       	<option value="04" <c:if test="${vcatnManageVO.searchMonth eq '04' }">selected</c:if>>04</option>
			       	<option value="05" <c:if test="${vcatnManageVO.searchMonth eq '05' }">selected</c:if>>05</option>
			       	<option value="06" <c:if test="${vcatnManageVO.searchMonth eq '06' }">selected</c:if>>06</option>
			       	<option value="07" <c:if test="${vcatnManageVO.searchMonth eq '07' }">selected</c:if>>07</option>
			       	<option value="08" <c:if test="${vcatnManageVO.searchMonth eq '08' }">selected</c:if>>08</option>
			       	<option value="09" <c:if test="${vcatnManageVO.searchMonth eq '09' }">selected</c:if>>09</option>
			       	<option value="10" <c:if test="${vcatnManageVO.searchMonth eq '10' }">selected</c:if>>10</option>
			       	<option value="11" <c:if test="${vcatnManageVO.searchMonth eq '11' }">selected</c:if>>11</option>
			       	<option value="12" <c:if test="${vcatnManageVO.searchMonth eq '12' }">selected</c:if>>12</option>
		      	</select> <spring:message code="comUssIonVct.vcatnConfmList.month"/><!-- 월 -->
		      	
		      	<label for="" style="margin-left:15px"><spring:message code="comUssIonVct.common.applcntNm"/> : </label><!-- 신청자 -->
		      	<input name="searchNm" type="text" size="20" value="${vcatnManageVO.searchNm}"  maxlength="100" title="<spring:message code="comUssIonVct.common.applcntNm"/>" /><!-- 신청자 -->
				<input class="s_btn" type="submit" value='<spring:message code="button.inquire" />' title='<spring:message code="button.inquire" />' onclick="fncSelectVcatnConfmList('1'); return false;" />
			</li>
		</ul>
	</div>
		</form>
	<table class="board_list">
		<caption></caption>
		<colgroup>
			<col style="" />
			<col style="width:12%" />
			<col style="width:12%" />
			<col style="width:15%" />
			<col style="width:15%" />
			<col style="" />
			<col style="width:13%" />
		</colgroup>
		<thead>
			<tr>
			   <th scope="col"><spring:message code="table.num" /></th><!-- 순번 -->
			   <th scope="col"><spring:message code="comUssIonVct.common.vcatnSe"/></th><!-- 휴가구분 -->
			   <th scope="col"><spring:message code="comUssIonVct.common.applcntNm"/></th><!-- 신청자 -->
			   <th scope="col"><spring:message code="comUssIonVct.common.orgnztNm"/></th><!-- 소속 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnConfmList.bgnde"/></th><!-- 시작일 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnConfmList.endde"/></th><!-- 종료일 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnConfmList.searchConfmAt"/></th><!-- 진행구분 -->
			   <th scope="col"><spring:message code="comUssIonVct.vcatnConfmList.confmAt.agreeProcess"/></th><!-- 승인처리 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vcatnManageList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${(vcatnManageVO.pageIndex - 1) * vcatnManageVO.pageSize + status.count}"/></td>
				<td><c:out value="${resultInfo.vcatnSeNm  }"/></td>
				<td><c:out value="${resultInfo.applcntNm  }"/></td>
				<td><c:out value="${resultInfo.orgnztNm   }"/></td>
				<td><c:out value="${resultInfo.bgnde      }"/></td>
				<td><c:out value="${resultInfo.endde      }"/></td>
				<td>
		          <c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonVct.vcatnConfmList.searchConfmAt.A"/></c:if><!-- 신청 -->
		          <c:if test="${resultInfo.confmAt eq 'C'}"><spring:message code="comUssIonVct.common.agree"/></c:if><!-- 승인 -->
		          <c:if test="${resultInfo.confmAt eq 'R'}"><spring:message code="comUssIonVct.common.disagree"/></c:if><!-- 반려 -->
				</td>
				<td>
		        <form name="item" method="post" action="<c:url value='/uss/ion/vct/EgovVcatnConfm.do'/>">
		        	<input type="hidden" name="applcntId" value="<c:out value="${resultInfo.applcntId  }"/>">
		        	<input type="hidden" name="vcatnSe"   value="<c:out value="${resultInfo.vcatnSe    }"/>">
		        	<input type="hidden" name="bgnde"     value="<c:out value="${resultInfo.bgnde      }"/>">
		        	<input type="hidden" name="endde"     value="<c:out value="${resultInfo.endde      }"/>">
		        	<input type="hidden" name="infrmlSanctnId" value="<c:out value="${resultInfo.infrmlSanctnId  }"/>">
		            <input class="btn01" type="submit" style="padding:6px 10px 6px 10px; background-color:#4688d2; color:#fff; font-size:11px; border-radius:1px;" 
		                  value="<c:if test="${resultInfo.confmAt eq 'A'}"><spring:message code="comUssIonVct.vcatnConfmList.confmAt.agreeProcess"/> </c:if><c:if test="${resultInfo.confmAt ne 'A'}"><spring:message code="comUssIonVct.vcatnConfmList.confmAt.detail"/> </c:if>"
		                  onclick="fncVcatnManageDetail('<c:out value="${resultInfo.applcntId}"/>','<c:out value="${resultInfo.vcatnSe}"/>','<c:out value="${resultInfo.bgnde}"/>','<c:out value="${resultInfo.endde}"/>','<c:out value="${resultInfo.infrmlSanctnId  }"/>'); return false;"><!-- 승인처리 --><!-- 상세보기 -->
		        </form>
				</td>
			</tr>    
			</c:forEach>
			
			<c:if test="${fn:length(vcatnManageList) == 0}">
				<tr> 
					<td colspan="8">
						<spring:message code="common.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
		</tbody>
	</table>

	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/>
		</ul>
	</div>
</div>

</body>
</html>
