/**
* 易提站
*
*/
var snYTZ = snYTZ || {};
var isIE=!!window.ActiveXObject;
var isIE6=isIE&&!window.XMLHttpRequest;
snYTZ = {
	//导航
	menuAction:function(){
		var obj = $('.nav');
		obj.find('li').hover(
			function(){
				$(this).addClass('hover');
			},
			function(){
				$(this).removeClass('hover');
			}
		);
	},
	/*右侧浮动*/
	floatLink:function(){
		var obj = $('.floattip');
		var left = $('.mainbody ').offset().left + 990 + 10;
		var top = $(window).scrollTop()+200;
		obj.show().css({'left':left});
		if(isIE6){
			obj.css({'position':'absolute','left':left,'top':top});
			$(window).scroll(function(){
				top = $(window).scrollTop()+200;
				obj.css({'left':left,'top':top});
			});
		}
		$(window).resize(function(){
			left = $('.mainbody ').offset().left + 990 + 10;
			obj.css({'left':left});
		})
	},
	//input焦点事件,select事件
	inputEvent:function(){
		var ipt = $(':input')//.not('select');		
		ipt.each(function(){
			var val = '';
			$(this).focus(function(){
				if($(this).hasClass('gray')){
					$(this).removeClass('gray');
					val = $(this).val();
					$(this).val('')
				}
			}).blur(function(){			
				if($(this)[0].tagName !='SELECT'){
					if($(this).val()=='' || $(this).val() == val){
						$(this).addClass('gray');
						$(this).val(val)
					}
				}else{
					if($(this)[0].selectedIndex==0){
						$(this).addClass('gray');
					}
				}
			})
		});
	},
	//checkbox全选
	checkboxSelect:function(){
		var obj = $('.chk').find(':checkbox');
		obj.change(function(){
			var other = $(this).parents('table').find(':checkbox');
			if($(this).attr('checked')){
				other.attr('checked',$(this).attr('checked'));
			}else{
				other.removeAttr('checked');
			}
		})
	},
	//提货码
	thCode:function(){
		//此方法展示提货码错误时小弹窗出现时，需改变父级z-index值；
		var obj = $('.th-code');
		var time;
		obj.find('input').focus(
			function(){
				$(this).parent().css('z-index',100);
				$('.tippopu').hide();
				$(this).siblings('.tippopu').fadeIn(400);
				clearTimeout(time);
				time = setTimeout(function(){
					$('.tippopu').fadeOut(400);
				},1000);
			}
		).blur(
			function(){
				$(this).parent().css('z-index',1);
				$(this).siblings('.tippopu').hide(50);
			}
		);
	},
	//弹窗
	popuWin:function(opt){
		var def = {
			box:'',
			iconType:'warn',
			msg:'',
			closeBtn:$('.popuwin').find('.wintitle').find('a'),
			okBtn:'',
			cancelBtn:$('.popuwin').find('.btn-b'),
			callback:null 
		}
		$.extend(def,opt);
		var win = $(def.box);
		var closeBtn = def.closeBtn;
		var okBtn = def.okBtn;
		var cancelBtn = def.cancelBtn;
		var iconClass = def.iconType;
		var msg = def.msg;
		var mask = $('#allMask');
		var callback = def.callback;
		if(isIE6 && mask.find('iframe').length == 0){
			mask.html('<iframe></iframe>');
		};
		mask.show();
		win.show();
		win.find('.icon32').addClass(iconClass);
		if(msg !=''){
			win.find('.msg').text(msg);
		}
		var top;
		top = $(window).scrollTop()+ ($(window).height() - win.height())/2;
		win.css({'top':top});
		$(window).scroll(function(){
			top = $(window).scrollTop()+ ($(window).height() - win.height())/2;
			win.css({'top':top});
		});
		closeBtn.click(function(){
			win.hide();
			mask.hide();
		});
		cancelBtn.click(function(){
			win.hide();
			mask.hide();
		});	
		if(okBtn!=''){
			okBtn.unbind('click').bind('click',function(){
				//仅适用于点击确定有回调方法，并且窗口消失的情况
				//有其他状态切换的，在每个方法单独写，参考提货、还款
				if($(this).hasClass('btn-f')){
					return false;
				}
				if(callback){
					callback.call(this,win);//将win作为回调的参数
					win.hide();
					mask.hide();
				}
			})
		}
	},
	//弹窗操作展示
	//提货码丢失
	loseCodeAct:function(){
		var thBtn = $('.losegoodscode');
		var time;
		thBtn.each(function(){
			$(this).click(function(){
				snYTZ.popuWin({
					box:'#getTakeGoodCode'
				});
				//提取码
				clearInterval(time)
				$('#getTakeGoodCode').find('.sbtn').addClass('getcode').removeClass('btn-f').find('span').text('获取提货码');
				$('#getTakeGoodCode').find('.msg').html('发送提货码');
				$('.getcode').bind('click',getCode);
			})
		})
		function getCode(){
			$('#getTakeGoodCode').find('.getcode').unbind('click').removeClass('getcode').addClass('btn-f').find('span').text('重新获取');
			$('#getTakeGoodCode').find('.msg').html('发送提货码...<b>119</b>秒');
			timedown($('#getTakeGoodCode'),120);
		}
		function timedown(win,timecount){			
			win.find('.msg').find('b').text(timecount);
			clearInterval(time)
			time = setInterval(function(){
				timecount--;
				win.find('.msg').find('b').text(timecount);
				if(timecount==0){
					clearInterval(time);
					$('#getTakeGoodCode').find('.msg').html('未收到提货码？重新获取提货码');
					win.find('.btn-f').removeClass('btn-f').addClass('getcode').bind('click',getCode);
				}
			},1000);
		}
	},
	//还款
	repayment:function(){
		var obj = $('.repayment');
		obj.each(function(){
			$(this).click(function(){
				snYTZ.popuWin({
					box:'#singleRepayMent'
				});
			})
		})
		$('.repaymentall').click(function(){
			var len = $('.resultlist').find('table tr:gt(0)').find(':checked').length;
			if(len==0){
				alert('请选择');
				return false;
			}
			var listNum = '';			
			for (var i=1; i<=len;i++ )
			{
				if ($('.resultlist').find('table').find('tr').eq(i).find(':checked'))
				{
					var str = $('.resultlist').find('table').find('tr').eq(i).find('td').eq(1).html();
					if(i<len){
						listNum += str+', ';
					}else{
						listNum += str;
					}
				}
			}
			snYTZ.popuWin({
				box:'#allRepayMent'
			});
			$('#allRepayMent').find('.listnumarea').val(listNum);
		})
	},
	//退货
	rejectGood:function(){
		var obj = $('.cancelgood');
		obj.each(function(){
			$(this).click(function(){
				snYTZ.popuWin({
					box:'#rejectGood',
					okBtn:$('.rejectgoodbtn'),
					callback:tuihuo
				});
			})
		})
	},
	//超期提货
	exceedGood:function(){
		var obj = $('.exceed');
		obj.each(function(){
			$(this).click(function(){
				snYTZ.popuWin({
					box:'#TakeExceedGood',
					okBtn:$('.exceedBtn'),
					callback:chaoqitihuo
				});
			})
		})
	},
	//init
	init:function(){
		this.menuAction();
		this.floatLink();
		this.inputEvent();
		this.thCode();
		this.loseCodeAct();
		//this.thAct();
		this.repayment();
		this.checkboxSelect();
		this.rejectGood();
		this.exceedGood();
	}
}
$(function(){
	snYTZ.init();
})

function test1(win){
	alert("这个是点击确定之后执行的动作");
}
function tuihuo(){
	alert('退货成功')
}
function chaoqitihuo(){
	alert("超期提货成功")
}