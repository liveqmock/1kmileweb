$(document).ready(function(){

    //处理CKEDITOR的值,否则文本无法异步提交到后台
    function CKupdate() {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }
    
	$(".submit").on("click",function() {
				CKupdate(); //在提交表单前需要做以上处理
				$.post(ONEKM.contextPath + "/admin/article/update", 
					$("form").serialize(), 
					function(or) {
					if ("true" === or.flag) {
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
			});

})(jQuery);