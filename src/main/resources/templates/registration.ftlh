<#import "parts/common.ftlh" as c>
<#import "parts/registration.ftlh" as registration>

<@c.page>
    <#if message??>
    ${message}
    </#if>
    <@registration.registration "/registration" />
</@c.page>