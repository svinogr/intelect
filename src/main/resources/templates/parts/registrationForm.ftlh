<#macro registration path>
    <div class="container h-100">
        <div class="row justify-content-center align-items-center">
            <form class="center" action="${path}" method="post">
                <div class="container">
                    <div class="row">
                        <div class="form-group mr-1">
                            <label>Имя</label>
                            <input class="form-control <#if nameError??>is-invalid</#if>" type="text" name="name"
                                   value="${user.name}">
                            <#if nameError??>
                                <div class="invalid-feedback">Example invalid feedback text</div>
                            </#if>
                        </div>
                        <div class="form-group mr-1 ml-1">
                            <label>Отчество</label>
                            <input class="form-control " type="text" name="patronymic" value="${user.patronymic}"/>
                        </div>
                        <div class="form-group ml-1">
                            <label>Фамилия</label>
                            <input class="form-control" type="text" name="surname" value="${user.surname}">
                        </div>
                    </div>
                </div>

                <div class="container mt-5">
                    <div class="row">
                        <div class="form-group mr-1">
                            <label>Логин</label>
                            <input class="form-control" type="text" name="username" value="${user.username}">
                        </div>
                        <div class="form-group mr-1 ml-1">
                            <label>Пароль</label>
                            <input class="form-control" type="password" name="password" value="${user.password}">
                        </div>
                        <div class="form-group  ml-1">
                            <label>Пароль</label>
                            <input class="form-control" type="password" name="password2" value="${user.password2}">
                        </div>
                    </div>
                </div>

                <div>
                    <label><input class="mr-1" disabled type="checkbox"
                                  name="USER" checked>USER
                    </label>
                </div>
                <div>
                    <label><input class="mr-1" type="checkbox"
                                  name="ADMIN" <#if roles.ADMIN??>
                            <#if roles.ADMIN = "checked"> checked
                            </#if>


                        </#if>>ADMIN
                    </label>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="hidden" name="userId" value="${user.id}">
                <button class="btn btn-outline-primary form-control mt-4" type="submit">Сохранить</button>
            </form>
        </div>
    </div>
</#macro>
