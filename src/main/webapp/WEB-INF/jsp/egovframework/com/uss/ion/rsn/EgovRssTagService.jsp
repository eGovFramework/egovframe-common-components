<%@ page contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%><?xml version="1.0" encoding="UTF-8" ?><%@ page import="java.util.Map,java.util.List,java.util.HashMap,java.util.ArrayList"%>
		<rss version="2.0"
			xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
			xmlns:dc="http://purl.org/dc/elements/1.1/"
			xmlns:taxo="http://purl.org/rss/1.0/modules/taxonomy/"
			xmlns:activity="http://activitystrea.ms/spec/1.0/" >
<channel>
<%
Map mapResult =(Map)request.getAttribute("mapRssInfo");
List mapResultList =(List)request.getAttribute("mapRssInfoList");
%>
<title><![CDATA[<%=mapResult.get("HDER_TITLE") %>]]></title>
<link><![CDATA[<%=mapResult.get("HDER_LINK") %>]]></link>
<description><![CDATA[<%=mapResult.get("HDER_DESCRIPTION") %>]]></description>
<tag><![CDATA[<%=mapResult.get("HDER_TAG") %>]]></tag>
<%
for(int i=0; i < mapResultList.size();i++ ){
	Map mapItem = (Map)mapResultList.get(i);
%>
	<item>
	<title><![CDATA[<%=mapItem.get("BDT_TITLE")%>]]></title>
	<link><![CDATA[<%=mapItem.get("BDT_LINK")%>]]></link>
	<description><![CDATA[<%=mapItem.get("BDT_DESCRIPTION")%>]]></description>
	<tag><![CDATA[<%=mapItem.get("BDT_TAG")%>]]></tag>
	<pubDate><%=mapItem.get("FRST_REGISTER_PNTTM")%></pubDate>
	<%=mapItem.get("BDT_ETC")%>
	</item>
<%}%>
</channel>

</rss>