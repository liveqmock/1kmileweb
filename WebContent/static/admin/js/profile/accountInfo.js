$(document).ready(function(){

	
	$("#accountInfoFormValidate").validate({
		submitHandler:function(form){
			$.post(ONEKM.contextPath + "/admin/profile/accountInfo", 
					$("form").serialize(), 
					function(json) {
					if ("true" === json.flag) {
						layer.use('extend/layer.ext.js');
						layer.msg('更新成功', 3, {
							rate : 'top',
							type : 1,
							shade : false
						});
					} else {
						layer.use('extend/layer.ext.js');
						layer.msg('更新失败', 3, {
							rate : 'top',
							type : 3,
							shade : false
						});
					}
				});
        },
		rules:{
			realName:{
				required:true
			},
			email:{
				required:true,
				email: true
			},
			cellphone:{
				required:true,
				isMobile: true
			}
		},
        messages: {
			realName:{
				required : "请输入真实姓名"
			},
        	email: {
          		required : "请输入常用邮箱"
          	},
          	cellphone: {
          		required: "请输入手机号"
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