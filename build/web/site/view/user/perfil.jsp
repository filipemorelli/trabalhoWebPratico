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
                    <h1 class="header center-on-small-only">Perfil - <%= session.getAttribute("name")%></h1>
                    <div class='row'>
                        <h4 class="header col s12 light">Somos um serviço de carona feito com Java e Google Design.</h4>
                    </div>

                    <br>

                </div>
            </div>

            <div class="container">
                <div class="section">

                    <div class="row">
                        <div class="col s12">
                            <h4>Informações do seu perfil <%= session.getAttribute("name")%> </h4>
                        </div>
                    </div>

                    <div class="row">
                        <form id="form-perfil" class="col s12" method="POST" action="atualiza-perfil">
                            <div class="row">
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="nome" name="nome" type="text" class="validate" value="<%= session.getAttribute("name")%>" required>
                                    <label for="icon_prefix">Nome</label>
                                    <form:errors path="user.nome" cssStyle="color:red" />
                                </div>
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">phone</i>
                                    <input id="telefone" name="telefone" type="tel" class="validate" value="<%= session.getAttribute("phone")%>" required>
                                    <label for="icon_prefix">Telefone</label>
                                    <form:errors path="user.telefone" cssStyle="color:red"  />
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">email</i>
                                    <input id="email" name="email" type="email" class="validate" value="<%= session.getAttribute("email")%>" required>
                                    <label for="icon_prefix">Email</label>
                                    <form:errors path="user.email" cssStyle="color:red" />
                                </div>
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <select id="sexo" name="sexo" required>
                                        <option value="" disabled>Escolha</option>
                                        <option value="male" <%= session.getAttribute("gender").equals("male") ? "selected" : ""%>>Masculino</option>
                                        <option value="fame" <%= session.getAttribute("gender").equals("fema") ? "selected" : ""%>>Feminino</option>
                                        <option value="outro" <%= !session.getAttribute("gender").equals("male") && !session.getAttribute("gender").equals("fema") ? "selected" : ""%>>Outro</option>
                                    </select>
                                    <label>Sexo</label>
                                    <form:errors path="user.sexo" cssStyle="color:red" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m2 offset-m10">
                                    <button type="submit" class="btn btn-block waves-effect waves-light blue darken-2"><i class="material-icons right">send</i>Enviar</button>
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
