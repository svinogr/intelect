<#macro editForm path>
    <div class="container mt-5">
        <div class="row justify-content-center">

            <form class="" action="${path}" method="post">

                <style>
                    .form-control.is-invalid, .form-control.is-valid {
                        max-width: 193px;
                    }

                </style>

                <div class="form-group">

                    <label>Номер</label>
                    <input class="form-control <#if (error.numberError)??>is-invalid</#if>" type="text"
                           name="number" value="${number.number}">
                    <#if (error.numberError)??>
                        <div class="invalid-feedback">${error.numberError}</div>
                    </#if>

                </div>

                <div class="form-group">

                    <label>Описание</label>
                    <input class="form-control <#if (error.descriptionError)??>is-invalid </#if>" type="text"
                           name="description" value="${number.description}"/>
                    <#if (error.descriptionError)??>
                        <div class="invalid-feedback">${error.descriptionError}</div>
                    </#if>
                </div>
                <input type="hidden" class="form-control" value="<#if filter??>${filter}</#if>" name="filter"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="hidden" name="numberId" value="${number.id}">
                <button class="btn btn-outline-primary form-control mt-4" type="submit">Сохранить</button>

            </form>
        </div>
    </div>
</#macro>