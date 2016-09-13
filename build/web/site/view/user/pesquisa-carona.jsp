<%@page import="org.json.JSONObject"%>
<%@page import="model.CaronaModel"%>
<%@page import="java.lang.ProcessBuilder.Redirect.Type"%>
<%@page import="model.UserModel"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1 class="header center-on-small-only">Pesquisar carona</h1>
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

                        <form id="form-pesquisar-carona">
                            <div class="col s12 area-pesquisa">
                                <div class="input-field">
                                    <i class="material-icons prefix">search</i>
                                    <input id="pesquisar" type="text" class="validate" name="pesquisar">
                                    <label for="icon_prefix">Pesquisar carona</label>
                                </div>
                                <div class="col s12 m3">
                                    <input name="tipo" type="radio" id="pedir" class="with-gap" value="pedir" checked required/>
                                    <label for="pedir">Pedidos de Carona</label>
                                </div>
                                <div class="col s12 m3">
                                    <input name="tipo" type="radio" id="oferta" value="oferecer" class="with-gap" />
                                    <label for="oferta">Ofertas de Carona</label>
                                </div>
                            </div>

                            <div class="col s12 m3 ajust-button">
                                <button type="submit" class="btn blue btn-block waves-blue waves-effect"><i class="material-icons left">search</i>Pesquisar</button>
                            </div>
                        </form>


                        <div class="col s12">
                            <ul class="col s12 m12 collection result-search">
                                <li class="collection-item avatar">
                                    <img src="images/yuna.jpg" alt="" class="circle">
                                    <span class="title">Title</span>
                                    <p>First Line <br>
                                        Second Line
                                    </p>
                                    <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>
                                </li>
                            </ul>
                        </div>

                        <!--<div class="col s12 m6">
                            <div id="map-caronas" class="card-panel blue darken-2">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d83998.75769435287!2d2.277517069391249!3d48.85895068112713!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47e66e1f06e2b70f%3A0x40b82c3688c9460!2zUGFyaXMsIEZyYW7Dp2E!5e0!3m2!1spt-BR!2sbr!4v1473776412530" width="100%" height="350" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </div>
                        </div>-->

                    </div>
                </div>
            </div>

            <div id="map-route" class="modal modal-fixed-footer">
                <div class="modal-content">
                    <iframe width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
                <div class="modal-footer">
                    <a href="#!" class=" modal-action modal-close waves-effect waves-blue btn-flat">Fechar</a>
                </div>
            </div>

        </main>
        <%@include file="../layout/floatButtonAdmin.jsp" %>
        <%@include file="../layout/footer.jsp" %>
        <%@include file="../layout/admin/configuracaoJs.jsp" %>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCHMIcsEYQt1RoizBuH--1bWaWFNUcqM2I&signed_in=true&libraries=places&callback=PesquisarCarona.initAutocomplete" async defer></script>
        <script src="<c:url value='/themes/theme1/js/pesquisar-carona.js' />" charset="utf-8"></script>
    </body>
</html>