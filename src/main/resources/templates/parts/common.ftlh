<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>$Title$</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
                crossorigin="anonymous"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                // result приходит с сервака как булеан в случае успеха операции
                var flag = '<#if result??>true</#if>';
                console.log(flag);
                if (flag === 'true') {
                    $('#bd-example-modal-lg').modal('show');
                    console.log("dsdsd");
                }
            })
        </script>

    </head>
    <body>
    <script type="text/javascript">
        $(document).ready(function () {
            // result приходит с сервака как булеан в случае успеха операции
            var flag = '<#if result??>true</#if>';
            console.log(flag);
            if (flag === 'true') {
                $('#bd-example-modal-lg').modal('show');
                console.log("dsdsd");
            }
        })
    </script>
    <#include "navbar.ftlh">
    <#include "modal.ftlh">
  <div class="container">
    <#nested>
    </div>
    </body>
    </html>
</#macro>