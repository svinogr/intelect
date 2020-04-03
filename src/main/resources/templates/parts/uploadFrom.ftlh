<#macro upload>

    <form method="post" action="/upload" enctype="multipart/form-data">
        <div class="container mt-5">
            <div class="row justify-content-end">
                <div class="custom-file col-sm-4">
                    <input type="file" class="custom-file-input" name = "file" id="file" required>
                    <label class="custom-file-label" for="file">Выберите файл...</label>
                </div>
                <div class="">
                    <button class="btn btn-outline-success ml-2" type="submit" value="upload">Импорт из XLS</button>
                </div>
                <div class=""><a class="btn btn-outline-success ml-2" href="/numbers/add">Добавить номер</a></div>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>
<div class="invalid-feedback">
    Example invalid custom file feedback
</div>
