$(document).ready(function(){

	
	$("#updatePwdFormValidate").validate({
		rules:{
			oldPassword:{
				required : true
			},
			newPassword:{
				required: true,
				rangelength : [6,20]
			},
			confirmNewPassword:{
				required:true,
				rangelength : [6,20],
				equalTo:"#newPassword"
			}
		},
        messages: {
        	oldPassword: {
          		required : "请输入原密码"
          	},
          	newPassword: {
          		required: "请输入新密码",
          		rangelength: $.validator.format("密码必须为{0}-{1}个字 符")
          	},
          	confirmNewPassword: {
          		required: "请输入确认密码",
          		rangelength: $.validator.format("密码必须为{0}-{1}个字 符"),
          		equalTo: "两次输入的密码不一致"
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