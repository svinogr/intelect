<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input class="btn btn-primary" type="submit" value="Выйти"/>
    </form>
</#macro>