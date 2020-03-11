<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
	<meta charset="utf-8">
	<title><spring:message code="extLdapumtDpt.deptChart.Title"/></title>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="<c:url value='/html/egovframework/com/ext/ldapumt/libs/jquery.js' />"></script>
	<script src="<c:url value='/html/egovframework/com/ext/ldapumt/jstree.js' />"></script>
	<script type="text/javascript">
	  google.load("visualization", "1", {packages:["orgchart"]});
	  google.setOnLoadCallback(init);
	  
	  var data;
	  var chart;
	  var obj;
	  
	  function init() {
		$('#detail_div').hide();

		chart = new google.visualization.OrgChart(document.getElementById('chart_div'));

		data = new google.visualization.DataTable();  
		data.addColumn('string', 'Name');
		data.addColumn('string', 'Manager');
		data.addColumn('string', 'ToolTip');

		var dn="<c:out value='${param.baseDn}'/>";
		
		drawChart(dn);
	  }
	  
	  function drawChart(dn){
		  
		var url = "<c:url value='/ext/ldapumt/dpt/getDeptManageSublist.do' />";							
  		
  		$.get(url, { 'dn' : dn}).done(function (d) {
  			if(obj==null) {
  				obj = [[ {v:d.id, f:d.text+'<div style="color:red; font-style:italic"></div>'},'', 'parent']];
  			}
  			
  			var objLength = obj.length;
  			for(var i=0; i < d.children.length;i++) {
  				obj[i+objLength] = [ {v:d.children[i].id, f:'<div id="'+ d.children[i].id +'">'+d.children[i].text+'</div>'},d.id, 'child'];
  			}


  			data.addRows(obj);
  			
  			chart.draw(data, {allowHtml:true, allowCollapse: false});
  			
  			$('.google-visualization-orgchart-node-medium').dblclick(collapse);
  			$('.google-visualization-orgchart-node-medium').click(changeData);
  			
  		});
		  
	  }
	  
	  function collapse() {
		$('#detail_div').hide();
		  
		elements = $(this).children()
		elements.each(function(){
			
			var hasChild = false;
			var size = obj.length;

				for(var i=size-1;i>=0;i--){
					var node = obj[i];
					if(this.id==obj[i][1]){
						hasChild = true;
						obj.splice(i, 1);
					}
				}
			
				if(!hasChild) {
					drawChart(this.id);
				} else {
	  				data = new google.visualization.DataTable();  
		  			data.addColumn('string', 'Name');
		  			data.addColumn('string', 'Manager');
		  			data.addColumn('string', 'ToolTip');
		  			data.addRows(obj);
		  			chart.draw(data, {allowHtml:true, allowCollapse: false});
		  			$('.google-visualization-orgchart-node-medium').dblclick(collapse);
		  			$('.google-visualization-orgchart-node-medium').click(changeData);
				}
			
		}); 
	}
	  
	function changeData(){
		$('#detail_div').show();
		elements = $(this).children()
		elements.each(function(){
			var dn = this.id;
			
			var url;
			var htmlfile;
			
			url = "<c:url value='/ext/ldapumt/dpt/getDeptManage.do' />";
			htmlfile = "dept_html.html";
			if(dn.indexOf("ou")==0) {
				url = "<c:url value='/ext/ldapumt/dpt/getDeptManage.do' />";
				htmlfile = "<c:url value='/html/egovframework/com/ext/ldapumt/dept_html.jsp' />";
			} else {
				url = "<c:url value='/ext/ldapumt/dpt/getUserManage.do' />";
				htmlfile = "<c:url value='/html/egovframework/com/ext/ldapumt/user_html.jsp' />";
			}
			
			$.get(url+"?dn="+dn, function (d) {
				 $("#detail_div").load(htmlfile, function() {
						$.each($("input"), function(i,v) {
						    $(v).val(d[v.name]);
						});
				 });

			});
			
		}
		)

	}
	
	function modifyOrgManage(str){
		var url="";
		if(str=='dept') {
			url = '<c:url value="/ext/ldapumt/dpt/modifyDeptManage.do" />';
		} else {
			url = '<c:url value="/ext/ldapumt/dpt/modifyUserManage.do" />';					
		}
		
		$.ajax({
	           type: "POST",
	           url: url,
	           data: $("#form1").serialize(), // serializes the form's elements.
	           success: function(data)
	           {
	           }
	         });
		return;
	}

   </script>
	</head>
  <body>
	<div id="chart_div">
	</div>
	<div id="detail_div" style="position:fixed;bottom:10;right:10;width: 750px;height: 220px">
	</div>
  </body>
</html>