<#import "parts/common.ftlh" as c>
<#import "parts/registrationForm.ftlh" as registration>

<@c.page>
    <#if message??>
    ${message}
    </#if>
    <@registration.registration "/registration" />
</@c.page>