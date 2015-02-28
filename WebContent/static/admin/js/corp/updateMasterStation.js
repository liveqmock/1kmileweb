

$(document).ready(function(){
	
	$("#masterStationUpdateForm").validate({
		rules:{
			stationName:{
				required : true
			},
			stationShortName:{
				required: true
			},
			contractTime:{
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
				required : "请输入合作商"
			},
			stationShortName:{
				required: "请输入合作商简称"
			},
			contractTime:{
				required:"请选择签约时间"
			},
			cellphone:{
				required: "请输入手机号",
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
	
});