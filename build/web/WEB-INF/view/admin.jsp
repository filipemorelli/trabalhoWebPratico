<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Template</title>
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="description" content="Materialize is a modern responsive CSS framework based on Material Design by Google. ">
        <%@include file="layout/admin/configuracaoCss.jsp" %>
    </head>
    <body>
        <header>
            <div class="container"><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
            <ul id="nav-mobile" class="side-nav fixed">
                <li class="logo"><a id="logo-container" href="index.htm" class="brand-logo">
                        <object id="front-page-logo" type="image/svg+xml" data="<c:url value='/themes/theme1/res/logo.svg' />">Your browser does not support SVG</object></a></li>
                <li class="search">
                    <div class="search-wrapper card">
                        <input id="search"><i class="material-icons">search</i>
                        <div class="search-results"></div>
                    </div>
                </li>
                <li class="bold"><a href="about.html" class="waves-effect waves-teal">About</a></li>
                <li class="bold"><a href="getting-started.html" class="waves-effect waves-teal">Getting Started</a></li>
                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="bold"><a class="collapsible-header  waves-effect waves-teal">CSS</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li><a href="color.html">Color</a></li>
                                    <li><a href="grid.html">Grid</a></li>
                                    <li><a href="helpers.html">Helpers</a></li>
                                    <li><a href="media-css.html">Media</a></li>
                                    <li><a href="sass.html">Sass</a></li>
                                    <li><a href="shadow.html">Shadow</a></li>
                                    <li><a href="table.html">Table</a></li>
                                    <li><a href="typography.html">Typography</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="bold"><a class="collapsible-header  waves-effect waves-teal">Components</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li><a href="badges.html">Badges</a></li>
                                    <li><a href="buttons.html">Buttons</a></li>
                                    <li><a href="breadcrumbs.html">Breadcrumbs</a></li>
                                    <li><a href="cards.html">Cards</a></li>
                                    <li><a href="chips.html">Chips</a></li>
                                    <li><a href="collections.html">Collections</a></li>
                                    <li><a href="footer.html">Footer</a></li>
                                    <li><a href="forms.html">Forms</a></li>
                                    <li><a href="icons.html">Icons</a></li>
                                    <li><a href="navbar.html">Navbar</a></li>
                                    <li><a href="pagination.html">Pagination</a></li>
                                    <li><a href="preloader.html">Preloader</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="bold"><a class="collapsible-header  waves-effect waves-teal">JavaScript</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li><a href="carousel.html">Carousel</a></li>
                                    <li><a href="collapsible.html">Collapsible</a></li>
                                    <li><a href="dialogs.html">Dialogs</a></li>
                                    <li><a href="dropdown.html">Dropdown</a></li>
                                    <li><a href="media.html">Media</a></li>
                                    <li><a href="modals.html">Modals</a></li>
                                    <li><a href="parallax.html">Parallax</a></li>
                                    <li><a href="pushpin.html">Pushpin</a></li>
                                    <li><a href="scrollfire.html">ScrollFire</a></li>
                                    <li><a href="scrollspy.html">Scrollspy</a></li>
                                    <li><a href="side-nav.html">SideNav</a></li>
                                    <li><a href="tabs.html">Tabs</a></li>
                                    <li><a href="transitions.html">Transitions</a></li>
                                    <li><a href="waves.html">Waves</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li class="bold"><a href="mobile.html" class="waves-effect waves-teal">Mobile</a></li>
                <li class="bold"><a href="showcase.html" class="waves-effect waves-teal">Showcase</a></li>
            </ul>
        </header>
        <main>      
            <div class="section no-pad-bot blue darken-2" id="index-banner">
                <div class="container">
                    <h1 class="header center-on-small-only">Materialize</h1>
                    <div class='row center'>
                        <h4 class="header col s12 light center">A modern responsive front-end framework based on Material Design</h4>
                    </div>
                    <div class="row center">
                        <a href="getting-started.html" id="download-button" class="btn-large blue waves-effect waves-light">Get Started</a>
                    </div>
                    <div class="row center"><a class="red-text text-lighten-4" href="https://github.com/Dogfalo/materialize">beta release v0.97.7</a></div>

                    <br>

                </div>
                <div class="github-commit">
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
                </div>
            </div>

            <div class="container">
                <div class="section">

                    <div class="row">
                        <div class="col s12 m8 offset-m2">
                            <br>
                            <img id="responsive-img" src="images/responsive.png">
                        </div>
                    </div>

                    <div class="row">
                        <h3 class="col s12 light center header">Materialize simplifies life for developers and the users they serve.</h3>
                    </div>

                    <!--   Promo Section   -->
                    <div class="row">
                        <div class="col s12 m4">
                            <div class="center promo">
                                <i class="material-icons">flash_on</i>
                                <p class="promo-caption">Speeds up development</p>
                                <p class="light center">We did most of the heavy lifting for you to provide a default stylings that incorporate our custom components. Additionally, we refined animations and transitions to provide a smoother experience for developers.</p>
                            </div>
                        </div>

                        <div class="col s12 m4">
                            <div class="center promo">
                                <i class="material-icons">group</i>
                                <p class="promo-caption">User Experience Focused</p>
                                <p class="light center">By utilizing elements and principles of Material Design, we were able to create a framework that incorporates components and animations that provide more feedback to users. Additionally, a single underlying responsive system across all platforms allow for a more unified user experience.</p>
                            </div>
                        </div>

                        <div class="col s12 m4">
                            <div class="center promo">
                                <i class="material-icons">settings</i>
                                <p class="promo-caption">Easy to work with</p>
                                <p class="light center">We have provided detailed documentation as well as specific code examples to help new users get started. We are also always open to feedback and can answer any questions a user may have about Materialize.</p>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="divider"></div>
                <div class="section">
                    <div class="row center">
                        <h3 class="light header">Materialize Showcase</h3>
                        <p class="col s8 offset-s2 caption">Checkout what people are creating with Materialize. Get inspired by these beautiful sites and you can even submit your own website to be showcased here.</p>
                        <a href="showcase.html" class="btn-large waves-effect waves-light">Explore our Showcase</a>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="layout/footer.jsp" %>
        <%@include file="layout/admin/configuracaoJs.jsp" %>
    </body>
</html>