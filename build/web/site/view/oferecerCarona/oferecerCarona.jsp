<%-- 
    Document   : perfil
    Created on : 05/09/2016, 14:45:51
    Author     : Filipe
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title><%= session.getAttribute("name")%> - Perfil</title>
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="description" content="Materialize is a modern responsive CSS framework based on Material Design by Google. ">
        <%@include file="../layout/admin/configuracaoCss.jsp" %>
    </head>
    <body>
        <%@include file="../layout/sidebar.jsp" %>
        <main>      
            <div class="section no-pad-bot blue darken-2" id="index-banner">
                <div class="container">
                    <h1 class="header center-on-small-only">Oferecer Carona</h1>
                    <div class='row'>
                        <h4 class="header col s12 light">Somos um serviço de carona feito com Java e Google Design.</h4>
                    </div>

                    <br>

                </div>
            </div>

            <div class="container">
                <div class="section">
                    <div class="row">
                        <form id="form-perfil" class="col s12" method="POST" action="pedidoCarona">
                            <div class="row">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">place</i>
                                    <input id="endereco-saida" name="endereco-saida" type="text" class="validate" value="<%= session.getAttribute("name")%>" required>
                                    <label for="icon_prefix">Endereço de saída</label>
                                    <form:errors path="user.nome" cssStyle="color:red" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">place</i>
                                    <input id="endereco-chegada" name="endereco-chegada" type="text" class="validate" value="<%= session.getAttribute("name")%>" required>
                                    <label for="icon_prefix">Endereço de chegada</label>
                                    <form:errors path="user.nome" cssStyle="color:red" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">create</i>
                                    <textarea class="materialize-textarea validate" placeholder="Informações para as pessoas que desejam lhe dar carona. Por exemplo: racho gasolina, ando até 5 quarteirões para encontrar, etc."></textarea>
                                    <label>Considerações</label>
                                    <form:errors path="user.sexo" cssStyle="color:red" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m3 offset-m9">
                                    <button type="submit" class="btn btn-block waves-effect waves-light blue darken-2"><i class="material-icons right">send</i>Oferecer Carona</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </main>
        <%@include file="../layout/floatButtonAdmin.jsp" %>
        <%@include file="../layout/footer.jsp" %>
        <%@include file="../layout/admin/configuracaoJs.jsp" %>
        <script>
            (function ($, Materialize) {
                $(document).ready(function () {
                    $("#form-perfil").on("submit", function () {

                        if ($("#nome").val().length === 0) {
                            Materialize.toast("Digite seu nome");
                            return false;
                        }

                        if ($("#telefone").val().length === 0) {
                            Materialize.toast("Digite seu telefone");
                            return false;
                        }

                        if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test($("#email").val().length)) {
                            Materialize.toast("Digite seu email corretamente");
                            return false;
                        }

                        if ($("#sexo").val() === "") {
                            Materialize.toast("Selecione o sexo!");
                            return false;
                        }

                        return true;
                    });
                });
            })(window.jQuery, Materialize);
        </script>
    </body>
</html>
