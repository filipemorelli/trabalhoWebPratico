<%-- 
    Document   : perfil
    Created on : 05/09/2016, 14:45:51
    Author     : Filipe
--%>
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
                        <form class="col s12" action="">
                            <div class="row">
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="icon_prefix" type="text" class="validate" value="<%= session.getAttribute("name")%>" required>
                                    <label for="icon_prefix">Nome</label>
                                </div>
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">phone</i>
                                    <input id="icon_prefix" type="tel" class="validate" value="<%= session.getAttribute("phone")%>" required>
                                    <label for="icon_prefix">Telefone</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">email</i>
                                    <input id="icon_prefix" type="email" class="validate" value="<%= session.getAttribute("email")%>" required>
                                    <label for="icon_prefix">Email</label>
                                </div>
                                <div class="input-field col s6">
                                    <i class="material-icons prefix">account_circle</i>
                                    <select required>
                                        <option value="" disabled>Escolha</option>
                                        <option value="male" <%= session.getAttribute("gender").equals("male") ? "selected" : ""%>>Masculino</option>
                                        <option value="fame" <%= session.getAttribute("gender").equals("fema") ? "selected" : ""%>>Feminino</option>
                                        <option value="outro" <%= !session.getAttribute("gender").equals("male") && !session.getAttribute("gender").equals("fema") ? "selected" : ""%>>Outro</option>
                                    </select>
                                    <label>Sexo</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12 m2 offset-m10">
                                    <button type="submit" class="btn btn-block blue darken-2"><i class="material-icons right">send</i>Enviar</button>
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
    </body>
</html>
