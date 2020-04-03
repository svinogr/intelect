<#macro editForm path>
    <div class="container h-100">
        <div class="row justify-content-center align-items-center">
            <form class="center" action="${path}" method="post">
                <div class="container">
                    <div class="row form-group">
                        <label>Номер</label>
                        <input class="form-control" type="text" name="number" value="${number.number}">
                    </div>
                    <div class=" row form-group">
                        <label>Описание</label>
                        <input class="form-control" type="text" name="description" value="${number.description}"/>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="numberId" value="${number.id}">
                </div>
                <button class="btn btn-outline-primary form-control mt-4" type="submit">Сохранить</button>
            </form>
        </div>
    </div>
</#macro>