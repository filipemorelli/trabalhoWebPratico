<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URI"%>
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
                    <h1 class="header center-on-small-only">Bem-vindo ao JaVai!</h1>
                    <div class='row'>
                        <h4 class="header col s12 light">Somos um serviço de carona feito com Java e Google Design.</h4>
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
                        <h3 class="col s12 blue-text center header">Histórico de Carona.</h3>
                    </div>
                    <ul class="collection list-map">
                    <%
                        List<CaronaModel> caronas = UserModel.historicoCarona((String) request.getSession().getAttribute("id_social"));
                        if(caronas.isEmpty()){
                            String li = "<li class='collection-item center'>Nenhuma Carona Registrada!</li>";
                            out.print(li);
                        } else {
                            for (CaronaModel carona : caronas) {
                                String li = "<li class='collection-item avatar modal-trigger' data-target='map-route' href-map=\"https://www.google.com/maps/embed/v1/directions?key=AIzaSyCHMIcsEYQt1RoizBuH--1bWaWFNUcqM2I&origin=[endereco_saida_url]&destination=[endereco_chegada_url]&avoid=tolls|highways\"> <i class='material-icons circle [color]'>[icon]</i> <span class='title'>[consideracoes]</span> <p>De: [endereco_saida] <br> Para: [endereco_chegada]</p> <a href='#!' data-tipo='[tipo]' data-id='[id]' class='secondary-content'><i class='material-icons black-text'>close</i></a> </li>";

                                if (carona.getTipo_carona().equals("oferecer")) {
                                    li = li.replace("[icon]", "directions_car");
                                    li = li.replace("[color]", "blue");
                                } else {
                                    li = li.replace("[icon]", "event_seat");
                                    li = li.replace("[color]", "green");
                                }
                                li = li.replace("[tipo]", carona.getTipo_carona());
                                li = li.replace("[id]", carona.getId().toString());
                                li = li.replace("[consideracoes]", carona.getConsideracoes());
                                li = li.replace("[endereco_saida]", !carona.getEndereco_saida().getEndereco_completo().isEmpty() ? carona.getEndereco_saida().getEndereco_completo().toString() : "" ) ;
                                li = li.replace("[endereco_chegada]", !carona.getEndereco_chegada().getEndereco_completo().isEmpty() ? carona.getEndereco_chegada().getEndereco_completo().toString() : "" );
                                
                                li = li.replace("[endereco_saida_url]", !carona.getEndereco_saida().getEndereco_completo().isEmpty() ? URLEncoder.encode(carona.getEndereco_saida().getEndereco_completo(), "UTF-8") : "" ) ;
                                li = li.replace("[endereco_chegada_url]", !carona.getEndereco_chegada().getEndereco_completo().isEmpty() ? URLEncoder.encode(carona.getEndereco_chegada().getEndereco_completo(), "UTF-8") : "" );

                                out.print(li);
                            }
                        }
                        
                        
                        
                    %>
                    </ul>
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
        <script src="<c:url value='/themes/theme1/js/admin.js' />" charset="utf-8"></script>
    </body>
</html>