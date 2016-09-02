<header>
    <div class="container"><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
    <ul id="nav-mobile" class="side-nav fixed">
        <li class="logo">
            <a id="logo-container" href="index.htm" class="brand-logo tooltipped" data-position="bottom" data-delay="50" data-tooltip="<%= request.getSession().getAttribute("name")%>" title="<%= request.getSession().getAttribute("name") %>">
                <%
                    if (request.getSession().getAttribute("name") == null) {
                        out.print("<object id=\"front-page-logo\" type=\"image/svg+xml\" data=\"<c:url value=\'/themes/theme1/res/logo.svg\' />\">Your browser does not support SVG</object>");
                    } else {
                        out.print("<img id='front-page-logo' src='" + request.getSession().getAttribute("picture") + "' alt='Imagem de " + request.getSession().getAttribute("name") + "' />");
                    }
                %>
            </a>
        </li>
        <li class="search">
            <div class="search-wrapper card">
                <input id="search" autocomplete="off"><i class="material-icons">search</i>
                <div class="search-results"></div>
            </div>
        </li>
        <li class="bold"><a href="perfil" class="waves-effect waves-blue">Perfil</a></li>
        <li class="bold"><a href="admin" class="waves-effect waves-blue">Histórico pessoal</a></li>
        <li class="bold"><a href="#" class="waves-effect waves-blue">Pedir Carona</a></li>
        <li class="bold"><a href="" class="waves-effect waves-blue">Oferecer Carona</a></li>
        <li class="bold"><a href="sobre" class="waves-effect waves-blue">Sobre</a></li>
        <li class="bold"><a href="termos-de-uso" class="waves-effect waves-blue">Termos de uso</a></li>
        <li class="bold"><a href="politicas-de-privacidade" class="waves-effect waves-blue">Politicas de privacidade</a></li>
        <li class="bold"><a href="logout" class="waves-effect waves-blue">Sair</a></li>
    </ul>
</header>