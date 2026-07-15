<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><spring:message code="extLdapumtDpt.deptTree.Title"/></title>
		<meta name="viewport" content="width=device-width" />
		<link rel="stylesheet" href="<c:url value='/html/egovframework/com/ext/ldapumt/themes/default/style.css' />" />
		<style>
		html, body { background:#ebebeb; font-size:10px; font-family:Verdana; margin:0; padding:0; }
		#container { min-width:320px; margin:0px auto 0 auto; background:white; border-radius:0px; padding:0px; overflow:hidden; }
		#tree { float:left; min-width:319px; border-right:1px solid silver; overflow:auto; padding:0px 0; }
		#data { margin-left:320px; }
		#data textarea { margin:0; padding:0; height:100%; width:100%; border:0; background:white; display:block; line-height:18px; resize:none; }
		#data, #code { font: normal normal normal 12px/18px 'Consolas', monospace !important; }

		#tree .dept { background:url('<c:url value="/html/egovframework/com/ext/ldapumt/image/file_sprite.png" />') right bottom no-repeat; }
		#tree .user { background:url('<c:url value="/html/egovframework/com/ext/ldapumt/image/user.png" />') 0 0 no-repeat; }
		</style>
	</head>
	<body>
		<div id="container" role="main">
			<div id="tree"></div>
			<div id="data">
				<div class="content code" style="display:none;"><textarea id="code" readonly="readonly"></textarea></div>
				<div class="content dept" style="display:none;"></div>
				<div class="content image" style="display:none; position:relative;"><img src="" alt="" style="display:block; position:absolute; left:50%; top:50%; padding:0; max-height:90%; max-width:90%;" /></div>
				<div class="content default" style="text-align:center;lineHeight:0">Select a file from the tree.</div>
			</div>
		</div>
		<script language="javascript" src="<c:url value='/html/egovframework/com/ext/ldapumt/libs/jquery.js' />"></script>
		<script language="javascript" src="<c:url value='/html/egovframework/com/ext/ldapumt/jstree.js' />"></script>

		<script>
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfToken = "${_csrf.token}";

		function ldapCsrfBeforeSend(xhr) {
			if (csrfHeaderName && csrfToken) {
				xhr.setRequestHeader(csrfHeaderName, csrfToken);
			}
		}

		function ldapPost(url, data) {
			return $.ajax({
				type: "POST",
				url: url,
				data: data,
				beforeSend: ldapCsrfBeforeSend
			});
		}
		
		$(function () {
			$(window).resize(function () {
				var h = Math.max($(window).height() - 0, 420);
				$('#container, #data, #tree, #data .content').height(h);
			}).resize();

			$('#tree')
				.jstree({
					'core' : {
						// 2026.07.13 NCSC 보안점검 조치: LDAP ou/cn 값이 innerHTML로 그대로 렌더링되어 저장된 XSS로 이어지지 않도록 노드 텍스트를 항상 escape(plain text) 처리
						'force_text' : true,
						'data' : {
							'type' : 'POST',
							'url' : '<c:url value="/ext/ldapumt/dpt/getDeptManageSublist.do"/>',
							'beforeSend' : ldapCsrfBeforeSend,
							'data' : function (node) {
								
								if(node.id == '#')
									node.dn="<c:out value='${empty param.baseDn ? baseDn : param.baseDn}'/>";
								else 
									node.dn= node.id;
								
								return { 'dn' : node.dn };
							}
						},
						'check_callback' : function(o, n, p, i, m) {
							if(m && m.dnd && m.pos !== 'i') { return false; }
							if(o === "move_node" || o === "copy_node") {
								if(this.get_node(n).parent === this.get_node(p).id) { return false; }
							}
							return true;
						},
						'themes' : {
							'responsive' : false,
							'variant' : 'small',
							'stripes' : true
						}
					},
					'sort' : function(a, b) {
						return this.get_type(a) === this.get_type(b) ? (this.get_text(a) > this.get_text(b) ? 1 : -1) : (this.get_type(a) >= this.get_type(b) ? 1 : -1);
					},
					'contextmenu' : {
						'items' : function(node) {
							var tmp = $.jstree.defaults.contextmenu.items();
							delete tmp.create.action;
							tmp.rename.label = "<spring:message code="extLdapumtDpt.deptTree.rename"/>"; //이름변경
							tmp.remove.label = "<spring:message code="extLdapumtDpt.deptTree.del"/>"; //삭제
							tmp.ccp.label = "<spring:message code="extLdapumtDpt.deptTree.edit"/>"; //편집
							tmp.ccp.submenu.cut.label="<spring:message code="extLdapumtDpt.deptTree.deptMove"/>"; //이동부서선택
							tmp.ccp.submenu.paste.label="<spring:message code="extLdapumtDpt.deptTree.paste"/>"; //붙여넣기
							delete tmp.ccp.submenu.copy;
							
							tmp.create.label = "<spring:message code="extLdapumtDpt.deptTree.add"/>"; //추가
							tmp.create.submenu = {
								"create_dept" : {
									"separator_after"	: true,
									"label"				: "<spring:message code="extLdapumtDpt.deptTree.dept"/>", //부서
									"action"			: function (data) {
										var inst = $.jstree.reference(data.reference),
											obj = inst.get_node(data.reference);
										inst.create_node(obj, { type : "default" }, "last", function (new_node) {
											setTimeout(function () { inst.edit(new_node); },100);
										});
									}
								},
								"create_user" : {
									"label"				: "<spring:message code="extLdapumtDpt.deptTree.user"/>", //사용자
									"action"			: function (data) {
										var inst = $.jstree.reference(data.reference),
											obj = inst.get_node(data.reference);
										inst.create_node(obj, { type : "user" }, "last", function (new_node) {
											setTimeout(function () { inst.edit(new_node); },100);
										});

									}
								}
							};
							if(this.get_type(node) === "user") {
								delete tmp.create;
							}
							return tmp;
						}
					},
					'types' : {
						'default' : { 'icon' : 'dept' },
						'user' : { 'valid_children' : [], 'icon' : 'user' }
					},
					'unique' : {
						'duplicate' : function (name, counter) {
							return name + ' ' + counter;
						}
					},
					'plugins' : ['state','dnd','sort','types','contextmenu','unique']
				})
				.on('delete_node.jstree', function (e, data) {
					ldapPost('<c:url value="/ext/ldapumt/dpt/deleteNode.do" />', { 'dn' : data.node.id })
						.fail(function () {
							data.instance.refresh();
						});
				})
				.on('create_node.jstree', function (e, data) {
					var url;
					if(data.node.type == "default") {
						url="<c:url value='/ext/ldapumt/dpt/createNode.do' />";
					} else {
						url="<c:url value='/ext/ldapumt/dpt/createUserNode.do' />";
					}
					ldapPost(url, { 'dn' : data.node.parent, 'text' : data.node.text })
						.done(function (d) {
							d = getDataBody(d);
							data.instance.set_id(data.node, d.id);
						})
						.fail(function () {
							data.instance.refresh();
						});
				})
				.on('rename_node.jstree', function (e, data) {
					if(data.node.icon == "DEPT") {
						url="<c:url value='/ext/ldapumt/dpt/renameNode.do' />";
					} else {
						url="<c:url value='/ext/ldapumt/dpt/renameUserNode.do' />";
					}

					ldapPost(url, { id: data.node.id, text: data.text });
					data.instance.refresh();
				})
				.on('move_node.jstree', function (e, data) {
					ldapPost('<c:url value="/ext/ldapumt/dpt/moveOrgNode.do" />', { 'id' : data.node.id, 'parent' : data.parent })
						.done(function (d) {
							data.instance.refresh();
						})
						.fail(function () {
							data.instance.refresh();
						});
				})
				.on('copy_node.jstree', function (e, data) {
					$.get('?operation=copy_node', { 'id' : data.original.id, 'parent' : data.parent })
						.done(function (d) {
							data.instance.refresh();
						})
						.fail(function () {
							data.instance.refresh();
						});
				})
				.on('changed.jstree', function (e, data) {
					if(data && data.selected && data.selected.length) {
						var dn = data.selected.join(':');
						
						var url;
						var htmlfile;
						// 선택이 부서일 경우
						// DEPT, USER (LdapTreeObject.java 기준)
						if(data.node.icon == "DEPT") {
							url = "<c:url value='/ext/ldapumt/dpt/getDeptManage.do' />";
							htmlfile = "<c:url value='/html/egovframework/com/ext/ldapumt/dept_html.jsp' />";
						}
							
						if(data.node.icon == "USER") {
							url = "<c:url value='/ext/ldapumt/dpt/getUserManage.do' />";
							htmlfile = "<c:url value='/html/egovframework/com/ext/ldapumt/user_html.jsp' />";
						}
						if ( dn == "j1_1" ) return;
						ldapPost(url, { dn: dn }).done(function (d) {
							d = getDataBody(d);
							 $("#data .default").load(htmlfile, function() {
									$.each($("input"), function(i,v) {
									    $(v).val(d[v.name]);
									});
							 });

						});
						
					} else {
						$('#data .content').hide();
						$('#data .default').html('Select a file from the tree.').show();
					}
				});
		});

		function getDataBody(data) {
			var body;
			if ( typeof data.deptManage != "undefined" ) {
				body = data.deptManage
			}
			if ( typeof data.userManage != "undefined" ) {
				body = data.userManage
			}
			return body;
		}

		function modifyOrgManage(str){
			var url="";
			if(str=='dept') {
				url = '<c:url value="/ext/ldapumt/dpt/modifyDeptManage.do" />';
			} else {
				url = '<c:url value="/ext/ldapumt/dpt/modifyUserManage.do" />';					
			}

			ldapPost(url, $("#form1").serialize());
			return;
		}
		
		</script>
	</body>
</html>