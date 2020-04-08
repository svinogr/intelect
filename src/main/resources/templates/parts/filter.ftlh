<#macro filtered filter >
        <form action="/search/filter" method="post">
            <div class="row form-group ">
                <div class="col col-md-4 pr-2">
                    <label>Фильтр</label>
                    <input type="text" class="form-control" value="<#if filter??>${filter}</#if>" name="filter"/>
                </div>
                <button class="btn btn-outline-primary align-self-end" type="submit">Найти</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
        </form>
</#macro>