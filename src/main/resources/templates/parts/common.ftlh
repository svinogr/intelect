<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>$Title$</title>

     <link type="text/css" href="webjars/bootstrap/4.4.1-1/css/bootstrap.min.css"
              rel="stylesheet">

        <script src="webjars/jquery/3.4.1/jquery.slim.min.js"></script>
        <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="webjars/bootstrap/4.4.1-1/js/bootstrap.min.js"></script>
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

    <#include "navbar.ftlh">
    <#include "modal.ftlh">
  <div class="container">
    <#nested>
    </div>
    </body>
    </html>
</#macro>