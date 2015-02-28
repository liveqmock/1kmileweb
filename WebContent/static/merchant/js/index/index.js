	

$(document).ready(function(){
	
	var contextPath = ONEKM.contextPath;
	
	function init(){
		$.post(contextPath + "/merchant/transaction/expressCount", 
				{"packageStatus" : "00"},
				function(json) {
					$(".waitInStation").html("待入站 (" + json + " )个");
			});
		$.post(contextPath + "/merchant/transaction/expressCount", 
				{"packageStatus" : "01"},
				function(json) {
					$(".waitPickup").html("待提货 (" + json + " )个");
				});
	}
	
	init();
});