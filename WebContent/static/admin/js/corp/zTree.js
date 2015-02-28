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

