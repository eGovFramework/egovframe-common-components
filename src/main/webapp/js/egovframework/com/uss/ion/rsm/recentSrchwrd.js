/* ********************************************************
* 검색시 자동완성 등록 함수
******************************************************** */
function fn_egov_regist_RecentSrchwrdResult(SrchwrdManageId, searchKeyword, excFunction, frm){

	var url = frm.rsm_url.value;
//	var url = "/uss/ion/rsm/registRecentSrchwrdResult.do";

	var param = {
			srchwrdManageId: SrchwrdManageId,
			srchwrdNm: searchKeyword
	};

	new Ajax.Request(url,
   {
     asynchronous:true,
     method:"post",
     parameters: param ,
     evalJSON:     false,
     evalJS:       false,
    onLoading  : function() {/*로딩중*/ },
    onSuccess  : function(returnValue)
    {
    	if(returnValue.responseText == 'Success'){
    		setTimeout(excFunction, 0);
    	}
    },
    onFailure: function() {/*불러오기 실패*/},
    onComplete : function() {/*모든 것을 완료*/}
   });


}