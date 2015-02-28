
function expandNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
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

/**
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag 1表示普通选中,0取消选中,2追加选中
 */
function onClick(event, treeId, treeNode, clickFlag) {
	$("#structId").val(treeNode.id);
	$("#structName").val(treeNode.name);
	$("#structType").val(treeNode.structType);
}

function initTree(zNodes){
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
	$("#expandAllBtn").on("click", {type:"expandAll"}, expandNode);
	$("#collapseAllBtn").on("click", {type:"collapseAll"}, expandNode);
}

function listCorpMaster(){
	$.post(ONEKM.contextPath + "/admin/corp/listCorpStruct",function(json){
			var zNodes = [];
			$.each(json, function(index, item){
				var oneNode = {
					"id" : item.structId,
					"pId" : item.parentId,
					"name" : item.structName,
					"structType" : item.type,
					"structCreateTime" : item.createTime
				};
				if(0 === oneNode.pId){
					oneNode.isParent = true;
				}
				zNodes.push(oneNode);
				initTree(zNodes);
			});
		});
	
}

function listCorpMasterByParentId(){
	
}



$(document).ready(function(){
	listCorpMaster();
});