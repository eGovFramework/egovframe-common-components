<%@ page contentType="text/json;charset=utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
 response.setHeader("Pragma", "no-cache");
 response.setHeader("Expires", "0");
 response.setHeader("Cache-Control", "no-cache");

 request.setCharacterEncoding("UTF-8");
 
 ArrayList arrListColumnList = (ArrayList)request.getAttribute("ColumnList");
%>
[
	
            <%for(int i=0;i<arrListColumnList.size();i++){ 
            
            	HashMap hmColumn = (HashMap)arrListColumnList.get(i);
            %>
                {"text":"<%=hmColumn.get("codeNm") %>","value":"<%=hmColumn.get("codeNm") %>"}<%if(i != arrListColumnList.size()-1){%>,<%}%>    
            <%} %>
]

