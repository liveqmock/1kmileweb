/**
 * @license Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.html or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	config.language = 'zh-cn';  //语言
	config.skin = 'moono';   //皮肤
    config.toolbarCanCollapse = true;     //工具栏是否可以被收缩
	config.font_names = '正文/正文;宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;' + config.font_names;  //字体
	config.fontSize_sizes = '10/10px;11/11px;12/12px;13/13px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px'; //字体大小
   // config.toolbar = 'Basic';  	//工具栏（基础'Basic'、全能'Full'、自定义）
    config.toolbar = 'Full'; 
	config.toolbar_Full = [
			[ 'Font', 'FontSize', 'Table', 'HorizontalRule' ],
			[ 'NumberedList', 'BulletedList', 'Outdent', 'Indent' ],
			[ 'Anchor', 'PageBreak', 'SpecialChar', 'SelectAll', 'Paste',
					'PasteText', 'PasteFromWord' ],
			[ 'Blockquote', 'ShowBlocks', 'Replace' ],
			'/',
			[ 'Bold', 'Italic', 'Underline', 'TextColor', 'BGColor', 'Link',
					'Unlink', 'RemoveFormat' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
			[ 'Image', 'Flash' ],
			[ 'Strike', 'Subscript', 'Superscript', 'Undo', 'Redo', 'Cut',
					'Copy', 'Source' ],
			[ 'Maximize' ]		
			];
	
};
