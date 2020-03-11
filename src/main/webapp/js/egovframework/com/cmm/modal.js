/****************************************************************
 *
 * 파일명 : modal.js
 * 설  명 : 모달 기능을 처리하는 JavaScript
 *
 *    수정일          수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2015.07.13       장동한        1.0             최초생성
 *
 */
$(function() {
	
	$.fn.egovModal = function(oModal) {
		//Get the modal
		var modal = document.getElementById(oModal);

		// When the user clicks the button, open the modal
		$(this).click(function(){
			$(modal).css("display","block");
		});
		
		// When the user clicks on <span> (x), close the modal
		$(modal).find(".close").click(function(){
		   $(modal).css("display","none");
		});

		// When the user clicks anywhere outside of the modal, close it
		$(document).click(function(e){
			if(e.target == modal) {
				$(modal).css("display","none");
			}
		});

		//close key esc
		$(document).bind('keydown', function(event) {
			if($(modal).css("display") != "none" 
							&& event.keyCode == 27){
				$(modal).css("display","none");
			}
		});
		
    };
    
    $.fn.setEgovModalTitle = function(sTitle) {
    	$(this).find("#title").html("<h2>"+sTitle+"</h2>");
    };
    
    $.fn.setEgovModalBody = function(sBody) {
    	$(this).find("#body").html(sBody);
    };
    
    $.fn.setEgovModalfooter = function(sFooter) {
    	$(this).find("#footer").html(sFooter+"<span class='btn_style1 gray' id='btnModalClose'><a href='#'>close</a></span>").trigger("create");
    	//close  botton
    	$(this).find("#footer").find("#btnModalClose").click(function(){
    		$(this).parent().parent().parent().parent().css("display","none");
		});
    };
    
    $.fn.setEgovModalClose = function() {
    	$(this).css("display","none");
    };

})
