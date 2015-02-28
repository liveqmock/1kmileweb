$(document).ready(function(){
	
	var contextPath = document.getElementById("contextPath").value;
	
	$("#province").on("change", function(){
		var selectedProvinceCode = $(this).select2("val");
		if(null === selectedProvinceCode || "-1" === selectedProvinceCode){
			$("#city").html("<option value='-1'>请选择</option>");
			$("#city").select2("val", "-1");
			$("#district").html("<option value='-1'>请选择</option>");
			$("#district").select2("val", "-1");
		}else{
			$.post(contextPath + "/admin/address/listCitys/" + selectedProvinceCode, 
					function(json) {
						$("#city").html("");
						var cityOptions = [];
						cityOptions.push("<option value='-1'>请选择</option>");
						$.each(json, function(index, item){
							cityOptions.push("<option value='" + item.addressCode + "'>" + item.addressName + "</option>");
						});
						$("#city").html(cityOptions.join(""));
						$("#city").select2("val", "-1");
					}
			);
		}
	});
	
	$("#city").on("change", function(){
		var selectedCityCode = $(this).select2("val");
		if(null === selectedCityCode || "-1" === selectedCityCode){
			$("#district").html("<option value='-1'>请选择</option>");
			$("#district").select2("val", "-1");
		}else{
			$.post(contextPath + "/admin/address/listDistricts/" + selectedCityCode, 
					function(json) {
						$("#district").html("");
						var districtOptions = [];
						districtOptions.push("<option value='-1'>请选择</option>");
						$.each(json, function(index, item){
							districtOptions.push("<option value='" + item.addressCode + "'>" + item.addressName + "</option>");
						});
						$("#district").html(districtOptions.join(""));
						$("#district").select2("val", "-1");
					}
			);
		}
	});
	
});