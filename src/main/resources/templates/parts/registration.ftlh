<#macro registration path>
    <form action=${path} method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> User Surname : <input type="text" name="surname"/> </label></div>
        <div><label> User Patronymic : <input type="text" name="patronymic"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>
