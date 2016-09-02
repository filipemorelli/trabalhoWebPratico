<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title><%= session.getAttribute("name")%> - Carona JaVai</title>
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="description" content="Materialize is a modern responsive CSS framework based on Material Design by Google. ">
        <%@include file="../layout/admin/configuracaoCss.jsp" %>
    </head>
    <body>
        <%@include file="../layout/sidebar.jsp" %>
        <main>      
            <div class="section no-pad-bot blue darken-2" id="index-banner">
                <div class="container">
                    <h1 class="header center-on-small-only">Bem-vindo ao JaVai!</h1>
                    <div class='row'>
                        <h4 class="header col s12 light">Somos um serviço de carona feito com Java e Google Design.</h4>
                    </div>
                    <div class="row center">
                        <a href="#" id="download-button" class="btn-large blue waves-effect waves-light">Get Started</a>
                    </div>

                    <br>

                </div>
                <!--<div class="github-commit">
                    <div class="container">
                        <div class="commit">
                            Latest Commit on Github:
                            &nbsp;&nbsp;
                            <a href="" class="sha"></a>
                            &nbsp;&nbsp;
                            <span class="date"></span>
                            <a id="github-button" href="https://github.com/Dogfalo/materialize" class="btn-flat right grey-text text-lighten-5 waves-effect waves-light hide-on-small-only">Github</a>
                        </div>
                    </div>
                </div>-->
            </div>

            <div class="container">
                <div class="section">

                    <div class="row">
                        <h3 class="col s12 light center header">texto.</h3>
                    </div>
                    
                </div>
            </div>
        </main>
        <%@include file="../layout/footer.jsp" %>
        <%@include file="../layout/admin/configuracaoJs.jsp" %>
    </body>
</html>