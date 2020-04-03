<#include "security.ftlh">
<#import "logout.ftlh" as logout>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand">
            <img src="/static/img/ic.png" width="45" height="30" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link <#if numberActive??>active</#if>" href="/numbers">Номера</a>
                </li>
                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link <#if userActive??>active</#if>" href="/users">Админ</a>
                </li>
                </#if>

            </ul>
        </div>

        <div class="mr-sm-2">
            <#if name == "unknown">
           Залогинтесь!
            <#else>
            ${name}
            </#if>
        </div>
        <#if name != "unknown">
            <@logout.logout />
        <#else>


        </#if>
    </nav>
</div>
