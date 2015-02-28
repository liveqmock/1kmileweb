<#-- 显示请求path -->
<#macro link path>
${contextPath}${path}
</#macro>
<#macro l path>
<@link path/>
</#macro>

<#-- 格式化输出手机号 -->
<#macro cellphoneFormat cellphone>
<#if cellphone?? && cellphone?length gt 6>
${cellphone?substring(0,3)}-${cellphone?substring(3,7)}-${cellphone?substring(7)}
<#else>
${cellphone}
</#if>
</#macro>

<#-- 对手机号进行掩码 -->
<#macro cellphoneMask cellphone>
<#if cellphone?? && cellphone?length gt 6>
${cellphone?substring(0,3)} **** ${cellphone?substring(7)}
<#else>
${cellphone}
</#if>
</#macro>

<#macro ifHasError path>
<@spring.bind "${path}"/>
<#if (spring.status.errorMessages?size > 0)>
<#nested>
</#if>
</#macro>

<#macro subContent content>
<#if content?? && content?length lt 30>
${content}
<#else>
${content?substring(0,30)}...
</#if>
</#macro>