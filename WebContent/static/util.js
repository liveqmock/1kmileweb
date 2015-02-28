
	/**
	 * @功能 格式化输出日期
	 * @param srcDate
	 * @returns {String}
	 */
	function formatDate(srcDate){
		if(undefined === srcDate || srcDate === null){
			srcDate = new Date();
		}
	    var year = srcDate.getFullYear(); 
	    var month = srcDate.getMonth() + 1;    
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    var day = srcDate.getDate(); 
	    var hours = srcDate.getHours(); 
	    if(hours >= 0 && hours <= 9){
	    	hours = "0" + hours;
	    }
	    var minutes = srcDate.getMinutes();
	    if(minutes >= 0 && minutes <= 9){
	    	minutes = "0" + minutes;
	    }
	    var seconds = srcDate.getSeconds();
	    if(seconds >= 0 && seconds <= 9){
	    	seconds = "0" + seconds;
	    }
		return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
	}

	/**
	 * 追加参数
	 * @param toUrl
	 * @param paramKey
	 * @param paramVal
	 */
	function appendParam(toUrl, paramKey, paramVal){
		var resultParam = "";
		if((null != paramVal) && ("" != $.trim(paramVal))){
			if(toUrl.indexOf("?") > -1){
				resultParam += "&";
			}else{
				resultParam += "?";
			}
			resultParam += paramKey + "=" + paramVal;
		}
		return resultParam;
	}

	function isPhone(p){//判断是不是手机号
		var s = p.toString().replace(/\s/g,'');
		var pattern=/^1[3458][0-9]{9}$/;
		return pattern.test(s);
	}

	function userAgent(){
	    var ua = navigator.userAgent;
	    ua = ua.toLowerCase();
	    var match = /(webkit)[ \/]([\w.]+)/.exec(ua) ||
	    /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) ||
	    /(msie) ([\w.]+)/.exec(ua) ||
	    !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) ||
	    [];
	    switch(match[1]){
	     case "msie":     
	    	 return "msie";
	    	 break;
	     case "webkit":     //safari or chrome
		   return "safari_or_chrome";
	      break;
	     case "opera":      //opera
		   return "opera";
	      break;
	     case "mozilla":    //Firefox
		   return "Firefox";
	      break;
	     default:
		  return false;
	    }
	}

	function pageStyle1(page, baseUrl, contextPath, parameterMap, maxShowPageCount){
		var pageNumber = page.pageNumber;
		var pageSize = page.pageSize;
		var pageCount = page.pageCount;
		if (baseUrl.indexOf("?") != -1){
			baseUrl = baseUrl + "&";
		}else{
			baseUrl = baseUrl + "?";
		}
		
		var parameter = "";
		$.each(parameterMap, function(index, item){
			if(item != null && item != ""){
				parameter = parameter + "&" + item + "=" + item[index];
			}
		});
		
		if(maxShowPageCount === null){
			maxShowPageCount = 10;
		}
		
//		var firstPageUrl = baseUrl + "pageNumber=1" + parameter;
//		var lastPageUrl = baseUrl + "pageNumber=" + pageCount + parameter;
//		var prePageUrl = baseUrl + "pageNumber=" + (pageNumber - 1) + parameter;
//		var nextPageUrl = baseUrl + "pageNumber=" + (pageNumber + 1) + parameter;
		
		var pageStr = [];
		if(pageCount > 1){
			pageStr.push("<div class='snPages'>");
			//首页
			if(pageNumber > 1){
				pageStr.push("<a class='first' href='javascript:;' pageNumber='1'>首页</a>");
			}else{
				pageStr.push("<span class='first'>首页</span>");
			}
			//上一页
			if (pageNumber > 1){
				pageStr.push("<a class='prev' href='javascript:;' pageNumber='" + (pageNumber - 1) +  "'><b></b> 上一页</a>");
			}else{
				pageStr.push("<span class='prev'><b></b> 上一页</span>");
			}
			//下一页
			if (pageNumber < pageCount){
				pageStr.push("<a class='next' href='javascript:;' pageNumber='" + (pageNumber + 1) +  "'><b></b> 下一页</a>");
			}else{
				pageStr.push("<span class='next'><b></b> 下一页</span>");
			}
			//末页
			if(pageNumber < pageCount){
				pageStr.push("<a class='last' href='javascript:;' pageNumber='" + pageCount +  "'>末页</a>");
			}else{
				pageStr.push("<span class='last'>末页</span>");
			}
			pageStr.join("");
		}
		return pageStr;
	}
	
	function pageStyle2(page, baseUrl, contextPath, parameterMap, maxShowPageCount){
		var pageNumber = page.pageNumber;
		var pageSize = page.pageSize;
		var pageCount = page.pageCount;
		if (baseUrl.indexOf("?") != -1){
			baseUrl = baseUrl + "&";
		}else{
			baseUrl = baseUrl + "?";
		}
		
		var parameter = "";
		$.each(parameterMap, function(index, item){
			if(item != null && item != ""){
				parameter = parameter + "&" + item + "=" + item[index];
			}
		});
		
		if(maxShowPageCount === null){
			maxShowPageCount = 10;
		}
		
//		var firstPageUrl = baseUrl + "pageNumber=1" + parameter;
//		var lastPageUrl = baseUrl + "pageNumber=" + pageCount + parameter;
//		var prePageUrl = baseUrl + "pageNumber=" + (pageNumber - 1) + parameter;
//		var nextPageUrl = baseUrl + "pageNumber=" + (pageNumber + 1) + parameter;
		
		var pageStr = [];
		if(pageCount > 1){
			pageStr.push("<ul>");
			//首页
			if(pageNumber > 1){
				pageStr.push("<li><a href='javascript:;' pageNumber='1'>首页</a></li>");
			}else{
				pageStr.push("<li class='disabled'><span>首页</span></li>");
			}
//			//上一页
//			if (pageNumber > 1){
//				pageStr.push("<a class='prev' href='javascript:;' pageNumber='" + (pageNumber - 1) +  "'><b></b> 上一页</a>");
//			}else{
//				pageStr.push("<span class='prev'><b></b> 上一页</span>");
//			}
//			//下一页
//			if (pageNumber < pageCount){
//				pageStr.push("<a class='next' href='javascript:;' pageNumber='" + (pageNumber + 1) +  "'><b></b> 下一页</a>");
//			}else{
//				pageStr.push("<span class='next'><b></b> 下一页</span>");
//			}
//			//末页
//			if(pageNumber < pageCount){
//				pageStr.push("<a class='last' href='javascript:;' pageNumber='" + pageCount +  "'>末页</a>");
//			}else{
//				pageStr.push("<span class='last'>末页</span>");
//			}
			pageStr.join("");
		}
		return pageStr;
	}

	var ONEKM = {};
	ONEKM.contextPath = document.getElementById("contextPath").value;
	ONEKM.utils = ONEKM.utils || {};
	ONEKM.utils.formatDate = formatDate;
	ONEKM.utils.appendParam = appendParam;
	ONEKM.utils.isPhone = isPhone;
	ONEKM.utils.userAgent = userAgent;
	ONEKM.utils.pageStyle1 = pageStyle1;
	ONEKM.utils.pageStyle2 = pageStyle2;

