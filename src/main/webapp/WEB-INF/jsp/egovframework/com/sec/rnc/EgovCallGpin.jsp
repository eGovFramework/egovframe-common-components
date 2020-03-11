<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="egovframework.com.cmm.service.Globals"  %>
<%@ page import="egovframework.com.cmm.EgovWebUtil"  %>
<% 
String sbscrbTy = safeGetParameter(request, "sbscrbTy");
String nextUrl =  safeGetParameter(request, "nextUrl");
String nextUrlName =  safeGetParameter(request, "nextUrlName");
//if(sbscrbTy==null || sbscrbTy.equals("")) return;
/* gpin client 호출 */
response.sendRedirect("http://"+Globals.LOCAL_IP+":9901/G-PIN/Sample-AuthRequest.jsp?sbscrbTy="+sbscrbTy
		              +"&nextUrlName="+nextUrlName+"&nextUrl="+nextUrl);
%>
<%!
    String safeGetParameter (HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (value == null) {
            value = "";
        }
        return  EgovWebUtil.removeCRLF(value);
    }
%>
