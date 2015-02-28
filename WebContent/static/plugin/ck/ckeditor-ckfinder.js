$(document).ready(function(){
	var contextPath = ONEKM.contextPath;
	var ckEditor = CKEDITOR.replace('ckeditor');
	//必须设置,否则CKFiner无法连接CKEditor
	CKFinder.setupCKEditor(ckEditor, contextPath + '/static/plugin/ck/ckfinder/');   
// 通过修改服务器接收参数编码方式来解决乱码问题<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8"/>
	//	CKEDITOR.replace('ckeditor',{
//		filebrowserBrowseUrl : contextPath + '/static/plugin/ck/ckfinder/ckfinder.html',
//		filebrowserImageBrowseUrl : contextPath + '/static/plugin/ck/ckfinder/ckfinder.html?type=Images',
//		filebrowserFlashBrowseUrl : contextPath + '/static/plugin/ck/ckfinder/ckfinder.html?type=Flash',
//		filebrowserUploadUrl : contextPath + '/static/plugin/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
//		filebrowserImageUploadUrl : contextPath + '/static/plugin/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Image',
//		filebrowserFlashUploadUrl : contextPath + '/static/plugin/ck/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
//	});
})(jQuery);