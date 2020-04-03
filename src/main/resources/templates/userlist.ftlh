<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container">
        <div class="row justify-content-end">
            <div class=""><a class="btn btn-outline-success" href="/registration">Добавить пользователя</a></div>
        </div>
    </div>

    <div class=" mt-5">
        <table class="table table-sm table-borderless">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Логин</th>
                <th scope="col">Пользователь</th>
                <th scope="col">Доступ</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
            <tr>
                <th scope="row">
                    ${user.username}</th>
                <th scope="row">
                    ${user.name} ${user.patronymic} ${user.surname}</th>
                <th scope="row"><#list user.roles as role>${role}<#sep>, </#list></th>

                <th scope="row" class="text-right">
                    <a class="btn btn-sm btn-outline-danger" href="users/delete/${user.id}">Удалить</a>
                    <a class="btn btn-sm btn-outline-success" href="/users/${user.id}">Редактировать</a>
                </th>

                </#list>
            </tr>
            </tbody>
        </table>
    </div>
</@c.page>