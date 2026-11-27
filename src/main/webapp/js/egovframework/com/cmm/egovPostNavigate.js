/**
 * CSRF 대응을 위한 POST 기반 화면 이동 유틸리티
 */
function fn_egov_appendCsrfToken(form) {
	var csrfInput = form.querySelector('input[type="hidden"][name*="csrf" i]');
	if (!csrfInput) {
		var forms = document.forms;
		for (var i = 0; i < forms.length; i++) {
			csrfInput = forms[i].querySelector('input[type="hidden"][name*="csrf" i]');
			if (csrfInput) {
				break;
			}
		}
	}
	if (csrfInput) {
		var existing = form.querySelector('input[name="' + csrfInput.name + '"]');
		if (!existing) {
			var clone = document.createElement('input');
			clone.type = 'hidden';
			clone.name = csrfInput.name;
			clone.value = csrfInput.value;
			form.appendChild(clone);
		}
	}
}

function fn_egov_clearPostNavigateParams(form) {
	var dynamicParams = form.querySelectorAll('[data-egov-post-nav="true"]');
	for (var i = 0; i < dynamicParams.length; i++) {
		dynamicParams[i].parentNode.removeChild(dynamicParams[i]);
	}
}

function fn_egov_setPostNavigateParam(form, name, value) {
	var input = document.createElement('input');
	input.type = 'hidden';
	input.name = name;
	input.value = value == null ? '' : value;
	input.setAttribute('data-egov-post-nav', 'true');
	form.appendChild(input);
}

function fn_egov_postNavigate(action, params, targetForm) {
	var form = targetForm;
	if (!form) {
		var forms = document.forms;
		form = forms.length > 0 ? forms[0] : null;
	}
	if (!form) {
		form = document.createElement('form');
		form.method = 'post';
		document.body.appendChild(form);
	}

	form.method = 'post';
	form.action = action;
	fn_egov_clearPostNavigateParams(form);

	if (params) {
		for (var key in params) {
			if (Object.prototype.hasOwnProperty.call(params, key)) {
				fn_egov_setPostNavigateParam(form, key, params[key]);
			}
		}
	}

	fn_egov_appendCsrfToken(form);
	form.submit();
}

function fn_egov_postNavigateFromQuery(action, queryString, targetForm) {
	var params = {};
	if (queryString) {
		var pairs = queryString.replace(/^\?/, '').split('&');
		for (var i = 0; i < pairs.length; i++) {
			if (!pairs[i]) {
				continue;
			}
			var idx = pairs[i].indexOf('=');
			var key = idx >= 0 ? pairs[i].substring(0, idx) : pairs[i];
			var value = idx >= 0 ? decodeURIComponent(pairs[i].substring(idx + 1).replace(/\+/g, ' ')) : '';
			params[decodeURIComponent(key)] = value;
		}
	}
	fn_egov_postNavigate(action, params, targetForm);
}
