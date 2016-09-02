<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Login</title>
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="description" content="Materialize is a modern responsive CSS framework based on Material Design by Google. ">
        <%@include file="../layout/login/configuracaoCss.jsp" %>

    </head>
    <body class="blue darken-4">
        <div class="container">
            <div class="row">
                <div class="col m6 s12 offset-m3 login-ajust">
                    <div class="card white">
                        <div class="card-content black-text">
                            <span class="card-title">Login</span>
                            <p>I am a very simple card. I am good at containing small bits of information.</p>
                        </div>
                        <div class="card-action">
                            <a href="loginfb" class="btn btn-block blue darken-2 tooltipped" data-position="bottom" data-delay="50" data-tooltip="Logar usando o facebook" title="Entrar usando o facebook">
                                Facebook
                            </a>
                        </div>
                    </div>
                    <p class="white-text termos">
                        <a href="termos-de-uso" class="white-text">Termos de Uso</a>&nbsp;|&nbsp;<a href="politicas-de-privacidade" class="white-text">Politicas de privacidade</a>
                    </p>
                </div>
            </div>
        </div>
        <%@include file="../layout/login/configuracaoJs.jsp" %>
    </body>
</html>