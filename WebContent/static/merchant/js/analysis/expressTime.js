
$(document).ready(function(){
	
	var contextPath = ONEKM.contextPath;
	
	function makeLineChart(titleField, valueField, dataProvider){
		AmCharts.makeChart("chartdiv",
				{
					"type": "serial",
					"pathToImages": contextPath + "/static/plugin/amcharts/images/",
					"categoryField": "date",
					"dataDateFormat": "YYYY-MM-DD HH",
					"categoryAxis": {
						"minPeriod": "hh",
						"parseDates": true
					},
					"chartCursor": {
						"categoryBalloonDateFormat": "JJ:NN"
					},
					"chartScrollbar": {},
					"trendLines": [],
					"graphs": [
						{
							"bullet": "round",
							"id": "AmGraph-1",
							"title": "入站时间",
							"valueField": "column-1"
						},
						{
							"bullet": "square",
							"id": "AmGraph-2",
							"title": "提货时间",
							"valueField": "column-2"
						}
					],
					"guides": [],
					"valueAxes": [
						{
							"id": "ValueAxis-1",
							"title": "快递单数"
						}
					],
					"allLabels": [],
    				"exportConfig": {
    					"menuItemOutput"	: {
    						fileName		: '时间段分析',
    						format			: 'png',
    					},
    					"menuItems" : [ {
    						"icon" : contextPath + "/static/plugin/amcharts/images/export.png",
    						"iconTitle": '导出为图片'
    					} ]
    				},
					"balloon": {},
					"legend": {
						"useGraphSettings": true
					},
					"titles": [
						{
							"id": "Title-1",
							"size": 15,
							"text": "入站&提货时间段分析"
						}
					],
					"dataProvider": [
						{
							"column-1": 8,
							"column-2": 5,
							"date": "2014-03-01 08"
						},
						{
							"column-1": 6,
							"column-2": 7,
							"date": "2014-03-01 09"
						},
						{
							"column-1": 2,
							"column-2": 3,
							"date": "2014-03-01 10"
						},
						{
							"column-1": 1,
							"column-2": 3,
							"date": "2014-03-01 11"
						},
						{
							"column-1": 2,
							"column-2": 1,
							"date": "2014-03-01 12"
						},
						{
							"column-1": 3,
							"column-2": 2,
							"date": "2014-03-01 13"
						},
						{
							"column-1": 6,
							"column-2": 8,
							"date": "2014-03-01 14"
						}
					]
				}
			);
	}
	
	function initExpressTimeChart(){
		makeLineChart();
//		$.post(contextPath + "/admin/chart/analysisChildStation", function(json) {
//				var dataProvider = [];
//				$.each(json, function(index, item){
//					dataProvider.push({
//						"provinceName" : item.provinceName,
//						"provinceNumber" : item.provinceNumber
//					});
//				});
//				makePieChart("provinceName", "provinceNumber", dataProvider);
//			});
	}
	
	initExpressTimeChart();
	
	$.post(ONEKM.contextPath + "/merchant/analysis/expressTime", function(json){
		var inStationList = json.inStationList;
		var pickupList = json.pickupList;
		var ruzhan = {"label" : "入站时间", data : []};
		var ruzhanData = [];
		$.each(inStationList, function(index, item){
			ruzhanData.push(item.inStationTime, item.inStationNo);
		});
		ruzhan.data.push(ruzhanData);
		
		var tihuo = {"label" : "提货时间", data : []};
		var tihuoData = [];
		$.each(pickupList, function(index, item){
			ruzhanData.push(item.pickupTime, item.inPickupNo);
		});
		tihuo.data.push(tihuoData);
	});

});