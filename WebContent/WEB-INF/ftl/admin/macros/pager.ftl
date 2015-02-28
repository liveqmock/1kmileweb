<#-- 苏宁分页样式： 分页（Page对象、链接URL、参数Map、最大页码显示数） -->
<#macro pagerStyle1 page baseUrl parameterMap = {} maxShowPageCount = 6>

	<#local pageNumber = page.pageNumber />
	<#local pageSize = page.pageSize />
	<#local pageCount = page.pageCount />
	
	<#local parameter = "" />
	<#list parameterMap?keys as key>
		<#if parameterMap[key] != null && parameterMap[key] != "">
			<#local parameter = parameter + "&" + key + "=" + parameterMap[key] />
		</#if>
	</#list>
	
	<#if baseUrl?contains("?")>
		<#local baseUrl = baseUrl + "&" />
	<#else>
		<#local baseUrl = baseUrl + "?" />
	</#if>
	<#local firstPageUrl = baseUrl + "pageNumber=1" + parameter />
	<#local lastPageUrl = baseUrl + "pageNumber=" + pageCount + parameter />
	<#local prePageUrl = baseUrl + "pageNumber=" + (pageNumber - 1) + parameter />
	<#local nextPageUrl = baseUrl + "pageNumber=" + (pageNumber + 1) + parameter />

	<#if (pageCount > 1)>
		<div class="snPages">
		<#-- 首页 -->
		<#if (pageNumber > 1)>
			<a class="first" href="${contextPath}${firstPageUrl}">首页</a>
		<#else>
			<span class="first">首页</span>
		</#if>
		<#-- 上一页 -->
		<#if (pageNumber > 1)>
			<a class="prev" href="${contextPath}${prePageUrl}"><b></b> 上一页</a>
		<#else>
			<span class="prev"><b></b> 上一页</span>
		</#if>
		<#-- 下一页 -->
		<#if (pageNumber < pageCount)>
			<a class="next" href="${contextPath}${nextPageUrl}"><b></b> 下一页</a>
		<#else>
			<span class="next"><b></b> 下一页</span>
		</#if>
		<#-- 末页 -->
		<#if (pageNumber < pageCount)>
			<a class="last" href="${contextPath}${lastPageUrl}">末页</a>
		<#else>
			<span class="last">末页</span>
		</#if>
		</div>
	</#if>
</#macro>

<#-- bootstrap分页样式： 分页（Page对象、链接URL、参数Map、最大页码显示数） -->
<#macro pagerStyle2 page baseUrl parameterMap = {} maxShowPageCount = 6>

	<#local pageNumber = page.pageNumber />
	<#local pageSize = page.pageSize />
	<#local pageCount = page.pageCount />
	
	<#local parameter = "" />
	<#list parameterMap?keys as key>
		<#if parameterMap[key] != null && parameterMap[key] != "">
			<#local parameter = parameter + "&" + key + "=" + parameterMap[key] />
		</#if>
	</#list>
	
	<#if baseUrl?contains("?")>
		<#local baseUrl = baseUrl + "&" />
	<#else>
		<#local baseUrl = baseUrl + "?" />
	</#if>
	<#local firstPageUrl = baseUrl + "pageNumber=1" + parameter />
	<#local lastPageUrl = baseUrl + "pageNumber=" + pageCount + parameter />
	<#local prePageUrl = baseUrl + "pageNumber=" + (pageNumber - 1) + parameter />
	<#local nextPageUrl = baseUrl + "pageNumber=" + (pageNumber + 1) + parameter />

	<#if (pageCount > 1)>
		 <ul>
		<#-- 首页 -->
		<#if (pageNumber > 1)>
			<li><a href="${contextPath}${firstPageUrl}">首页</a></li>
		<#else>
			<li class="disabled"><span>首页</span></li>
		</#if>
		<#-- 上一页 -->
		<#if (pageNumber > 1)>
			<li><a href="${contextPath}${prePageUrl}">上一页</a></li>
		<#else>
			<li class="disabled"><span>上一页</span></li>
		</#if>
		<#-- 下一页 -->
		<#if (pageNumber < pageCount)>
			<li><a href="${contextPath}${nextPageUrl}">下一页</a></li>
		<#else>
			<li><span class="disabled">下一页</span></li>
		</#if>
		<#-- 末页 -->
		<#if (pageNumber < pageCount)>
			<li><a href="${contextPath}${lastPageUrl}">末页</a></li>
		<#else>
			<li><span class="disabled">末页</span></li>
		</#if>
		</ul>
	</#if>
</#macro>

