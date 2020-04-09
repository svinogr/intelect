<#macro login path isRegistrForm>

<div class="container mt-5">
    <div class="row justify-content-center ">
    <form class="center" action="${path}" method="post">
        <div class="form-group ">
            <label>Логин</label>
            <input type="text" class="form-control" name="username"/>
        </div>

        <div class="form-group">
            <label>Пароль</label>
            <input class="form-control " type="password" name="password"/>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input class="btn btn-outline-primary form-control mt-4" type="submit" value="Войти"/>
    </form>
    </div>
</div>
</#macro>
