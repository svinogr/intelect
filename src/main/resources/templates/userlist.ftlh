<#import "parts/common.ftlh" as c>
<@c.page>
    <div mt-5>
    <table class="table table-sm table-borderless">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Логин</th>
            <th scope="col">Пользователь</th>
            <th scope="col">Доступ</th>
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
`
            </#list>
        </tr>
        </tbody>
    </table>
    </div>
</@c.page>