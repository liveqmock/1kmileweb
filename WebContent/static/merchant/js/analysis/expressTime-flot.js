
$(document).ready(function(){
	
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
		
		var datasets = {
				"ruzhan" : ruzhan,
				"tihuo" : tihuo
		};
		console.log(datasets);
		
//		//数据从服务器端通过Ajax获取
//	    var datasets = {
//	            "ruzhan": {
//	                label: "入站时间",
//	                data:  [[1291770000000, 20.2], [1291770000000, 12.7], [1291773600000, 13.5], [1291777200000, 14.5], 
//
//	                [1291780800000, 24.3], [1291784400000, 12.3], [1291788000000, 22.5], [1291791600000, 15.5], 
//
//	                [1291795200000, 22.5], [1291798800000, 17.3], [1291802400000, 10.9], [1291806000000, 16.6], 
//
//	                [1291809600000, 20.2], [1291813200000, 12.7], [1291816800000, 13.5], [1291820400000, 14.5], 
//
//	                [1291824000000, 24.3], [1291827600000, 12.3], [1291831200000, 22.5], [1291834800000, 15.5], 
//
//	                [1291838400000, 22.5], [1291842000000, 17.3], [1291845600000, 10.9], [1291849200000, 16.6]]
//	            },        
//	            "tihuo": {
//	                label: "提货时间",
//	                data: [
//					[1291766400000, 20.2], [1291770000000, 12.7], [1291773600000, 13.5], [1291777200000, 14.5], 
//					
//					[1291780800000, 24.3], [1291784400000, 12.3], [1291788000000, 22.5], [1291791600000, 15.5], 
//					
//					[1291795200000, 22.5], [1291798800000, 17.3], [1291802400000, 10.9], [1291806000000, 16.6], 
//					
//					[1291809600000, 20.2], [1291813200000, 12.7], [1291816800000, 13.5], [1291820400000, 14.5], 
//					
//					[1291824000000, 24.3], [1291827600000, 12.3], [1291831200000, 22.5], [1291834800000, 15.5], 
//					
//					[1291838400000, 22.5], [1291842000000, 17.3], [1291845600000, 10.9], [1291849200000, 16.6] 
//	                       ]
//	            }
//	        };

	        // hard-code color indices to prevent them from shifting as choices are turned on/off
	        var i = 0;
	        $.each(datasets, function(key, val) {
	            val.color = i;
	            ++i;
	        });
	        
	        // insert checkboxes 
	        var choiceContainer = $("#choices");
	        $.each(datasets, function(key, val) {
	            choiceContainer.append('<input type="checkbox" name="' + key +
	                                   '" checked="checked" id="id' + key + '">' +
	                                   '<label for="id' + key + '">'
	                                    + val.label + '</label>');
	        });
	        choiceContainer.find("input").click(plotAccordingToChoices);
	        
	        function plotAccordingToChoices() {
	            var data = [];
	            choiceContainer.find("input:checked").each(function () {
	                var key = $(this).attr("name");
	                if (key && datasets[key])
	                    data.push(datasets[key]);
	            });
	            if (data.length > 0){
	                $.plot($("#expressTime"), data, {
	                    yaxis: { min: 0 },
	                    xaxis: {
	                    	mode: "time",
	                        timeformat: "%H:%M"
	                    }
	                });
	            }
	        }

	        plotAccordingToChoices();
		
		
	});

});