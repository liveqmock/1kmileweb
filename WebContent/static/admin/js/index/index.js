$(document).ready(function(){
	
	var contextPath = ONEKM.contextPath;
	
	function makePieChart(titleField, valueField, dataProvider){
    	AmCharts.makeChart("chartdiv",{
    				"type": "pie",
    				"balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
    				"titleField": titleField,
    				"valueField": valueField,
    				"borderAlpha": 0.46,
    				"color": "#E7E7E7",
    				"fontSize": 9,
    				"theme": "dark",
    				"allLabels": [],
    				"exportConfig": {
    					"menuItemOutput"	: {
    						backgroundColor	: '#282828',
    						fileName		: '站点分布图',
    						format			: 'png',
    					},
    					"menuItems" : [ {
    						"icon" : contextPath + "/static/plugin/amcharts/images/exportWhite.png",
    						"iconTitle": '导出为图片'
    					} ]
    				},
    				"balloon": {},
    				"legend": {
    					"align": "center",
    					"fontSize": 10,
    					"markerLabelGap": 4,
    					"markerSize": 14,
    					"markerType": "circle",
    					"position": "left",
    					"switchType": "v",
    					"valueAlign": "left",
    					"valueWidth": 49
    				},
    				"titles": [],
    				"dataProvider": dataProvider
    			}
    		);
	}
	
	function initChildStationChart(){
		$.post(contextPath + "/admin/chart/analysisChildStation", function(json) {
				var dataProvider = [];
				$.each(json, function(index, item){
					dataProvider.push({
						"provinceName" : item.provinceName,
						"provinceNumber" : item.provinceNumber
					});
				});
				makePieChart("provinceName", "provinceNumber", dataProvider);
			});
	}

	
	initChildStationChart();
});