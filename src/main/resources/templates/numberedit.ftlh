<#import "parts/common.ftlh" as c />
<#import "parts/numbereditForm.ftlh" as ef />
<@c.page>
    <#if page??>
        <@ef.editForm "/numbers?page=${page.getNumber()}&size=${page.getSize()}" />
    <#else>
        <@ef.editForm "/numbers" />
    </#if>
</@c.page>