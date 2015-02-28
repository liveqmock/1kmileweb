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
	$("#structName").val(treeNode.name);
	$("#structTypeDesc").val(treeNode.structTypeDesc);
	$("#structCreateTime").val(treeNode.structCreateTime);
	$("#updateCorpStruct").attr("href", ONEKM.contextPath + "/admin/corp/updateCorpStruct?structId=" + treeNode.id);
}

var ExpressTransaction = function(){
    // theme layout color set
    var layoutColorCodes = {
        'blue': '#4b8df8',
        'red': '#e02222',
        'green': '#35aa47',
        'purple': '#852b99',
        'grey': '#555555',
        'light-grey': '#fafafa',
        'yellow': '#ffb848'
    };
	return {
         getLayoutColorCode: function (name) {
            if (layoutColorCodes[name]) {
                return layoutColorCodes[name];
            } else {
                return '';
            }
         },
		 initMiniCharts: function () {
	            $('.easy-pie-chart .number.transactions').easyPieChart({
	                animate: 1000,
	                size: 75,
	                lineWidth: 3,
	                barColor: ExpressTransaction.getLayoutColorCode('yellow')
	            });
	            $('.easy-pie-chart .number.visits').easyPieChart({
	                animate: 1000,
	                size: 75,
	                lineWidth: 3,
	                barColor: ExpressTransaction.getLayoutColorCode('green')
	            });
	            $('.easy-pie-chart .number.bounce').easyPieChart({
	                animate: 1000,
	                size: 75,
	                lineWidth: 3,
	                barColor: ExpressTransaction.getLayoutColorCode('red')
	            });
	            $('.easy-pie-chart-reload').click(function(){
	                $('.easy-pie-chart .number').each(function() {
	                    var newValue = Math.floor(100*Math.random());
	                    $(this).data('easyPieChart').update(newValue);
	                    $('span', this).text(newValue);
	                });
	            });
//	            $("#sparkline_bar").sparkline([8,9,10,11,10,10,12,10,10,11,9,12,11,10,9,11,13,13,12], {
//	                type: 'bar',
//	                width: '100',
//	                barWidth: 5,
//	                height: '55',
//	                barColor: '#35aa47',
//	                negBarColor: '#e02222'}
//	            );
//	            $("#sparkline_bar2").sparkline([9,11,12,13,12,13,10,14,13,11,11,12,11,11,10,12,11,10], {
//	                type: 'bar',
//	                width: '100',
//	                barWidth: 5,
//	                height: '55',
//	                barColor: '#ffb848',
//	                negBarColor: '#e02222'}
//	            );
//	            $("#sparkline_line").sparkline([9,10,9,10,10,11,12,10,10,11,11,12,11,10,12,11,10,12], {
//	                type: 'line',
//	                width: '100',
//	                height: '55',
//	                lineColor: '#ffb848'
//	            });

	        }
	};
}();

$(document).ready(function(){
	
	listCorpStruct();
	
	$("#refreshBtn").on("click", function(){
		listCorpStruct();
	});
	
	ExpressTransaction.initMiniCharts();
});