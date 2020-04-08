<#import "parts/common.ftlh" as c>
<#import "parts/uploadFrom.ftlh" as u>
<#import "parts/modal.ftlh" as m>
<#import "parts/pager.ftlh" as p>
<@c.page>
    <@u.upload/>
    <div class="mt-5">
        <form action="/numbers/filter" method="post">
            <div class="row form-group ">
                <div class="col col-md-4 pr-2">
                    <label>Фильтр</label>
                    <input type="text" class="form-control" value="<#if filter??>${filter}</#if>" name="filter"/>
                </div>
                <button class="btn btn-outline-primary align-self-end" type="submit">Найти</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
        </form>

        <@p.pager "" page />

        <table class="table table-sm table-borderless">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Номер</th>
                <th scope="col">Описание</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as number>
            <tr>
                <th scope="row">
                    ${number.number}</th>
                <th scope="row">
                    ${number.description}</th>
                <th scope="row" class="text-right">
                    <div class="container">
                        <div class="row justify-content-end">
                            <div class="form-group ">
                                <div class="col col-md-4 pr-2">
                                    <form class="" method="post" action="/numbers/delete/${number.id}?page=${page.getNumber()}&size=${page.getSize()}">
                                        <input type="hidden" class="form-control" value="<#if filter??>${filter}</#if>"
                                               name="filter"/>
                                        <button class="btn btn-sm btn-outline-danger" type="submit">Удалить</button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    </form>
                                </div>
                            </div>

                            <div class="row form-group ">
                                <div class="col col-md-4">
                                    <form class="" method="post" action="/numbers/edit/${number.id}?page=${page.getNumber()}&size=${page.getSize()}">
                                        <input type="hidden" class="form-control" value="<#if filter??>${filter}</#if>"
                                               name="filter"/>
                                        <button class="btn btn-sm btn-outline-success" type="submit">Редактировать
                                        </button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </th>
                </#list>
            </tr>
            </tbody>
        </table>
    </div>
</@c.page>