<#macro registration path>
    <div class="container ">
        <div class="row justify-content-center">
            <form class="center" action="${path}" method="post">
                <div class="container">
                    <div class="row">
                        <div class="form-group col ">
                            <label>Имя</label>
                            <input class="form-control <#if (error.nameError)??>is-invalid</#if>" type="text"
                                   name="name"
                                   value="${user.name}">
                            <#if (error.nameError)??>
                                <div class="invalid-feedback col">${error.nameError}</div>
                            </#if>
                        </div>
                        <div class="form-group col">
                            <label>Отчество</label>
                            <input class="form-control <#if (error.patronymicError)??>is-invalid</#if>" type="text"
                                   name="patronymic" value="${user.patronymic}">
                            <#if (error.patronymicError)??>
                                <div class="invalid-feedback col">${error.patronymicError}</div>
                            </#if>
                        </div>
                        <div class="form-group col">
                            <label>Фамилия</label>
                            <input class="form-control <#if (error.surnameError)??>is-invalid</#if>" type="text"
                                   name="surname" value="${user.surname}">
                            <#if (error.surnameError)??>
                                <div class="invalid-feedback col">${error.surnameError}</div>
                            </#if>
                        </div>
                    </div>
                </div>

                <div class="container mt-5">
                    <div class="row justify-content-center">
                        <div class="form-group col">
                            <label>Логин</label>
                            <input class="form-control <#if (error.usernameError)??>is-invalid</#if>" type="text"
                                   name="username" value="${user.username}">
                            <#if (error.usernameError)??>
                                <div class="invalid-feedback col">${error.usernameError}</div>
                            </#if>
                        </div>
                        <div class="form-group col">
                            <label>Пароль</label>
                            <input class="form-control <#if (error.passwordError)??>is-invalid</#if>" type="password"
                                   name="password" value="${user.password}">
                            <#if (error.passwordError)??>
                                <div class="invalid-feedback col">${error.passwordError}</div>
                            </#if>
                        </div>
                        <div class="form-group col">
                            <label>Пароль</label>
                            <input class="form-control <#if (error.password2Error)??>is-invalid</#if>" type="password"
                                   name="password2" value="${user.password2}">
                            <#if (error.password2Error)??>
                                <div class="invalid-feedback col">${error.password2Error}</div>
                            </#if>
                        </div>
                    </div>
                </div>

                <div class="form-group col">
                    <label><input class="mr-1" disabled type="checkbox"
                                  name="USER" checked>USER
                    </label>
                </div>
                <div class="form-group col">
                    <label><input class="mr-1" type="checkbox"
                        <#list roles as role>

                            <#if role = "ADMIN">
                                checked

                            </#if>
                        </#list>


                                  name="ADMIN">ADMIN
                    </label>

                </div>
                <div class="form-group col">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="id" value="${user.id}">
                    <button class="btn btn-outline-primary form-control mt-4" type="submit">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</#macro>
