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

function initTree(zNodes){
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: nodeOnClick
			}
		};
	$.fn.zTree.init($("#corpStructTree"), setting, zNodes);
	//展开所有节点
	var zTree = $.fn.zTree.getZTreeObj("corpStructTree");
	zTree.expandAll(true);
	$("#expandAllBtn").on("click", {type:"expandAll"}, expandNode);
	$("#collapseAllBtn").on("click", {type:"collapseAll"}, expandNode);
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

function refreshTable(treeSearchData){
	$.post(ONEKM.contextPath + "/admin/corp/listChildStation",
		treeSearchData, function(page){
		var childStationList = page.pageList;
		//生成表格数据
		var trList = [];
		$.each(childStationList, function(index, item){
			trList.push("<tr>");
			trList.push("<td><input type='checkbox' /></td>");
			trList.push("<td>" + item.stationId + "</td>");
			trList.push("<td><a href=" + ONEKM.contextPath + "/admin/corp/updateChildStation?stationId=" + item.stationId + ">" + item.stationName + "</a></td>");
			trList.push("<td>" + item.wholeAddress + "</td>");
			trList.push("<td>" + item.createTime + "</td>");
			trList.push("<td>" + item.statusDesc + "</td>");
			trList.push("</tr>");
		});
		$("#tableChildStationList").html(trList.join(""));
//		var pageStr = ONEKM.utils.pageStyle2(page, "/admin/corp/listChildStation", ONEKM.contextPath, treeSearchData, 10);
//		$(".pagination").append(pageStr.join(""));
//		$(".pagination a").unbind().bind("click", function(){
//			treeSearchData.pageNumber = $(this).attr("pageNumber");
//			refreshTables(treeSearchData);
//		});
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
	var treeSearchData = {};
	if("00" == treeNode.structType){
		treeSearchData.structId = treeNode.id;
	}else{
		treeSearchData.structParentId = treeNode.id;
	}
//	console.log(treeSearchData);
	refreshTable(treeSearchData);
}


$(document).ready(function(){
	
	listCorpStruct();
	
	$("#refreshBtn").on("click", function(){
		listCorpStruct();
	});
	
});