var contextPath = document.getElementById("contextPath").value;

/**
 * 按条件搜索包裹
 */
function searchByCondition(){
	$("#searchForm").submit();
	
}

/**
 * 点击提货按钮时进行的操作
 */
function thAct(){
	var obj = $('.takegoods');
	obj.each(function(){
		$(this).click(function(){
			pickupGoods(this);
			$('#TakeGood').find('.takegoodsucc').find('.ok-btn').click(function(){
				$(this).parents('.popuwin').hide();
				$('#allMask').hide();
				window.location.href = contextPath + "/merchant/receiveGoods/pickupGoods";
			});
			$('#TakeGood').find('.takegoodfailed').find('.ok-btn').click(function(){
				$(this).parents('.popuwin').hide();
				$('#allMask').hide();
			});
			
		})
	})
}

/**
 * 顾客提货回调方法
 */
function pickupGoods(takegoodsButton){
	var packageIdVal = $(takegoodsButton).parent().parent().siblings(".packageId").val();
	var pickupCodeVal = $(takegoodsButton).parent().parent().prev().find(".pickupCode").val();
	//TODO 需要验证提货码非空
	if(null == pickupCodeVal || "" == pickupCodeVal){
		return;
	}
	$.ajax({
		type : "POST",
		url : contextPath + "/merchant/receiveGoods/pickupGoods",
		data : {packageId : packageIdVal, pickupCode : pickupCodeVal},
		dateType : "json",
		success : function(json){
			snYTZ.popuWin({
				box:'#TakeGood'
			});
			if(json.flag == "true"){
				$('#TakeGood').find('.takegoodfailed').hide();
				$('#TakeGood').find('.takegoodsucc').show();
			}else{
				$('#TakeGood').find('.takegoodsucc').hide();
				$('#TakeGood').find('.takegoodfailed').show();
			}
		}
	});
}

$(document).ready(function(){
	
	$("#expressNo").focus();
	
	$("#expressNo,#receiveName,#receiveCellphone").on("keydown", function(event) {
		if (event.keyCode === 13) {
			searchByCondition();
		}
	});
	$("#buttonSearch").on("click", function(){
		searchByCondition();
	});
	
	thAct();
});