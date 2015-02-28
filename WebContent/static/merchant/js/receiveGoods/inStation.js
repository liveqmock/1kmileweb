var contextPath = document.getElementById("contextPath").value;

function refreshTables(pageNumber, expressCompanyCode, receiveCellphone){
	$(".resultlist tbody").html("");
	$(".pagebox").html("");
	$.post(contextPath + "/merchant/receiveGoods/listWaitInStation", 
			{pageNumber : pageNumber, expressCompanyCode: expressCompanyCode, receiveCellphone : receiveCellphone},
			function(json){
				var trList = [];
				$.each(json.pageList, function(index, item){
					if(index % 2 === 0){
						trList.push("<tr class='even'>");
					}else{
						trList.push("<tr>");
					}
					trList.push("<input type='hidden' value='" + item.packageId + "'/>");
					trList.push("<td><input type='checkbox' /></td>");
					trList.push("<td>" + item.expressNo + "</td>");
					trList.push("<td>" + item.receiveName + "</td>");
					trList.push("<td>" + item.receiveCellphone + "</td>");
					trList.push("<td>" + item.expressCompanyCodeDesc + "</td>");
					trList.push("<td>" + item.packageStatusDesc + "</td>");
					trList.push("</tr>");
				});
				$(".resultlist tbody").append(trList.join(""));
				var page = {
						pageNumber : json.pageNumber,
						pageSize : json.pageSize,
						pageCount : json.pageCount
				};
				var pageStr = ONEKM.utils.pageStyle1(page, "/merchant/receiveGoods/listWaitInStation", ONEKM.contextPath, {}, 10);
				$(".pagebox").append(pageStr.join(""));
				$(".snPages a").unbind().bind("click", function(){
					refreshTables($(this).attr("pageNumber"));
				});
		});
}

function initTables(){
	refreshTables(1);
}

function inStationByExpressNo(){
	var expressNo = $("#expressNo");
	var expressNoVal = expressNo.val();
	if("" === $.trim(expressNoVal)){
		return;
	}
	$.post(contextPath + "/merchant/receiveGoods/inStation/" + expressNoVal, 
			function(or) {
				if ("true" == or.flag) {
					layer.use('extend/layer.ext.js');
					layer.msg(or.message, 5, {
						rate : 'top',
						type : 1,
						shade : false
					});
					//入站成功,刷新表格
					refreshTables(1);
					var sound = new Howl({  urls: [contextPath + '/static/merchant/audio/success.wav']}).play();
				} else {
					layer.use('extend/layer.ext.js');
					layer.msg(or.message, 5, {
						rate : 'top',
						type : 3,
						shade : false
					});
					var sound = new Howl({  urls: [contextPath + '/static/merchant/audio/failure.wav']}).play();
				}
			}
	);
	expressNo.val("");
	expressNo.focus();
}

function selectEpressCompany(){
	var selectedVal = $("#expressCompanyCode").children("option:selected").val();
	if(-1 == selectedVal){
		refreshTables(1);
	}else{
		refreshTables(1, selectedVal);
	}
}

function search(){
	var receiveCellphoneVal = $("#receiveCellphone").val();
	if(null != receiveCellphoneVal && "" != receiveCellphoneVal.trim()){
		var selectedVal = $("#expressCompanyCode").children("option:selected").val();
		if(-1 == selectedVal){
			refreshTables(1, "", receiveCellphoneVal);
		}else{
			refreshTables(1, selectedVal, receiveCellphoneVal);
		}
	}else{
		refreshTables(1);
	}
}


$(document).ready(function(){
	
	initTables();
	
	$("#expressNo").on("keydown", function(event) {
		if (event.keyCode === 13) {
			inStationByExpressNo();
		}
	});
	
	$("#handInStation").on("click", function(){
		inStationByExpressNo();
	});
	
	$("#expressCompanyCode").on("change", function(){
		selectEpressCompany();
	});
	
	$("#search").on("click", function(){
		search();
	});
	
	$("#receiveCellphone").on("keydown", function(event) {
		if (event.keyCode === 13) {
			search();
		}
	});
	
	
});