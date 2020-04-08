<#import "parts/common.ftlh" as c />
<#import "parts/numbereditForm.ftlh" as ef />
<@c.page>

    <#if page??>

        <#if filter??>
        <@ef.editForm "/numbers?page=${page.getNumber()}&size=${page.getSize()}" />
         <#else>
             <@ef.editForm "/search?page=${page.getNumber()}&size=${page.getSize()}&filter=${filter}" />
            </#if>
    <#else>
        <@ef.editForm "/numbers" />
    </#if>
</@c.page>