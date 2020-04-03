<#import "parts/common.ftlh" as c>
<#import "parts/uploadFrom.ftlh" as u>
<#import "parts/modal.ftlh" as m>
<@c.page>
    <@u.upload/>
    <div class=" mt-5">
        <table class="table table-sm table-borderless">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Номер</th>
                <th scope="col">Описание</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#list numbers as number>
            <tr>
                <th scope="row">
                    ${number.number}</th>
                <th scope="row">
                    ${number.description}</th>
                <th scope="row" class="text-right">
                    <a class="btn btn-sm btn-outline-danger" href="/numbers/delete/${number.id}">Удалить</a>
                    <a class="btn btn-sm btn-outline-success" href="/numbers/${number.id}">Редактировать</a>
                </th>
                </#list>
            </tr>
            </tbody>
        </table>
    </div>
</@c.page>