<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>
<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getFio()
    isAdmin = user.isAdmin()
    valueBtn = "Разлогинится"
    path = "/logout"
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    valueBtn = "Залогиниться"
    path = "/login">
</#if>
