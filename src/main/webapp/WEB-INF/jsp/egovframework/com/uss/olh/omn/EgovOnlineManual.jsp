<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 떠다니는 레이어 시작 -->  
<div id="ScrollMenu" style="position:absolute; left:614px; top:10px; width:108px; z-index:1;height: 137px;"> 
	<form name="subForm" method="post" action="<c:url value='/uss/olh/omn/detailOnlineManual.do'/>">
		<input name="onlineMnlId" type="hidden" value="${onlineMnlId}">
		<table width="100" height="129" border="0" cellpadding="3" cellspacing="1" bgcolor="#000000"> 
		<tr> 
		<td align="center" valign="middle" bgcolor="#FFFFFF">
			<input type="submit" value="온라인메뉴얼" style="height:100%;width:100%;" >
		</td> 
		</tr> 
		</table>
	</form>
</div> 
<script type="text/javaScript" language="javascript">
<!--

var yScrollThumb;
var yMenuTop;

function CheckMenuPosition()
{
    // 아래의 400은 웹페이지 구성이 화면 가운데 이고 폭이 400px일 때,
	// 페이지 내용 오른쪽에 레이어를 위치
	//yScrollThumb = 400 + (document.body.clientWidth-400) /2;
	yScrollThumb = 800;

	if (yScrollThumb != ScrollMenu.style.left)
	{
	   ScrollMenu.style.left = yScrollThumb;
    }
	// 레이어는 스크롤바를 내릴 때 항상 위에서 100px 밑에 위치
	yScrollThumb = document.body.scrollTop + 10;
	// 현재 메뉴의 위치를 10진수로 구한다.
    yMenuTop = parseInt ( ScrollMenu.style.top, 10); 

	if ( yMenuTop == yScrollThumb)	
	{// 레이어를 이동하 필요가 없다면 다음 타이머는 조금 늦추어 발생 
		TimeOutInterval = 500;
	}
	else
	{// 레이어의 위치를 스크롤바의 썸브 위치로 이동한다.
	
	    // 레이어의 위치를 스크롤바의 썸브 위치 사이의 중간 위치를 구한다. 
		yMenuTop = ( yMenuTop + yScrollThumb) / 2;
		
		if ( 10 >= yMenuTop)
		{// 변경할 위치가 200보다 작다면 200으로 고정한다.
		 // 즉, 화면 맨 위로 이동했다면 상단의 캡션부분을 넘지 못하게
		    ScrollMenu.style.top = 10;
			TimeOutInterval = 500;	
		}
		else
		{// 레이어의 위치를 변경하고 다시 스크롤바의 썸브 위치로
		 // 레이어를 빠르게 이동 시키기 위해
		 // 다음 타이머 이벤트를 빨리 발생하게 한다. 
	    	ScrollMenu.style.top = yMenuTop;
			TimeOutInterval = 10;	
		}
   }
   setTimeout ("CheckMenuPosition()", TimeOutInterval);
}

function OnLoad()
{
   CheckMenuPosition();
   return true;
}
OnLoad();
//-->
</script>
