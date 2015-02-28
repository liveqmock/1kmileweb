var key, lastValue = "", nodeList = [], fontCss = {};

function initTree(zNodes){
	var setting = {
			key: {
				title: "t"
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				fontCss: getFontCss
			},
			callback: {
				onClick: nodeOnClick
			}
		};
	$.fn.zTree.init($("#corpStructTree"), setting, zNodes);
	//初始化时展开所有节点
	var zTree = $.fn.zTree.getZTreeObj("corpStructTree");
	zTree.expandAll(true);
	//绑定展开和关闭事件
	$("#expandAllBtn").on("click", {type:"expandAll"}, expandNode);
	$("#collapseAllBtn").on("click", {type:"collapseAll"}, expandNode);
	key = $("#searchTreeByName");
	key.on("focus", focusKey)
	.on("blur", blurKey)
	.on("propertychange", searchNode)
	.on("input", searchNode);
}

function expandNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("corpStructTree"),
	type = e.data.type,
	nodes = zTree.getSelectedNodes();
	if (type.indexOf("All") < 0 && nodes.length == 0) {
		alert("请先选择一个父节点");
	}
	if (type == "expandAll") {
		zTree.expandAll(true);
	} else if (type == "collapseAll") {
		zTree.expandAll(false);
	} else {
		//var callbackFlag = $("#callbackTrigger").attr("checked");
		// expandNode 方法是否触发 callback
		var callbackFlag = false;
		for (var i=0, l=nodes.length; i<l; i++) {
			zTree.setting.view.fontCss = {};
			if (type == "expand") {
				zTree.expandNode(nodes[i], true, null, null, callbackFlag);
			} else if (type == "collapse") {
				zTree.expandNode(nodes[i], false, null, null, callbackFlag);
			} else if (type == "toggle") {
				zTree.expandNode(nodes[i], null, null, null, callbackFlag);
			} else if (type == "expandSon") {
				zTree.expandNode(nodes[i], true, true, null, callbackFlag);
			} else if (type == "collapseSon") {
				zTree.expandNode(nodes[i], false, true, null, callbackFlag);
			}
		}
	}
}

function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}

function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}

function searchNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("corpStructTree");
	var value = $.trim(key.get(0).value);
	if (key.hasClass("empty")) {
		value = "";
	}
	if (lastValue === value) {return;}
	lastValue = value;
	if (value === "") {return;}
	updateNodes(false);
	nodeList = zTree.getNodesByParamFuzzy("name", value);
	updateNodes(true);
}

function updateNodes(highlight) {
	var zTree = $.fn.zTree.getZTreeObj("corpStructTree");
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}

function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}


function listCorpStruct(){
	$.post(ONEKM.contextPath + "/admin/corp/listCorpStruct",function(json){
			var zNodes = [];
			$.each(json, function(index, item){
				var oneNode = {
					"id" : item.structId,
					"pId" : item.parentId,
					"name" : item.structName,
					"structType" : item.type,
					"structTypeDesc" : item.typeDesc,
					"structCreateTime" : item.createTime
				};
				if(0 === item.structId || "01" === item.type || "02" === item.type){
					oneNode.isParent = true;
				}
				zNodes.push(oneNode);
				initTree(zNodes);
			});
		});
	
}

/**
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag 1表示普通选中,0取消选中,2追加选中
 */
function nodeOnClick(event, treeId, treeNode, clickFlag) {
	if("00" != treeNode.structType){
		$("#structParentId").val(treeNode.id);
		$("#structParentType").val(treeNode.structType);
		$("#structParentName").val(treeNode.name);
	}
}

function formValidate(){
	$(".submit").on("click", function(){
		if(null == $("#structId").val() || "" == $.trim($("#structId").val())){
			alert("请选择上级节点");
			return;
		}
	});
	
	$("#childStationAddForm").validate({
		rules:{
			stationName:{
				required: true
			},
			provinceCode:{
				min: 0
			},
			cityCode:{
				min: 0
			},
			districtCode:{
				min: 0
			},
			detailAddress:{
				required: true
			},
			businessTime:{
				required:true
			},
			stationDesc:{
				required:true
			},
			linkman:{
				required:true
			},
			cellphone:{
				required:true,
				isMobile: true
			},
			fixphone:{
				required:true,
				isTel: true
			}
		},
        messages: {
			stationName:{
				required : "请输入站点名称"
			},
			provinceCode:{
				min: "请选择省"
			},
			cityCode:{
				min: "请选择市"
			},
			districtCode:{
				min: "请选择区"
			},
			detailAddress:{
				required: "请输入详细地址"
			},
			businessTime:{
				required:"请输入营业时间"
			},
			stationDesc:{
				required:"请输入站点描述"
			},
			linkman:{
				required:"请输入站点负责人"
			},
			cellphone:{
				required:"请输入手机号",
				isMobile: "手机号格式不正确"
			},
			fixphone:{
				required:"请输入固定电话",
				isTel: "固定电话格式不正确"
			}
        },
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
}

$(document).ready(function(){
	
	listCorpStruct();
	
	//绑定刷新树事件
	$("#refreshBtn").on("click", function(){
		listCorpStruct();
	});
	
	formValidate();
	
});