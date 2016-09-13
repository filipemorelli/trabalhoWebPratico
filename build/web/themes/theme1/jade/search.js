(function ($) {
    $(document).ready(function () {
        window.index = lunr(function () {
            this.field('title', {boost: 10});
            this.field('body');
            this.ref('href');
        });
        window.index.pipeline.reset();

        window.index.add({
            href: 'perfil',
            title: 'Perfil',
            body: 'Perfil do usuario do javai.'
        });

        window.index.add({
            href: 'pesquisar-carona',
            title: 'Pesquisar Caronas',
            body: 'Pesquisar Pedidos de carona e ofertas de carona.'
        });

        window.index.add({
            href: 'admin',
            title: 'Histórico de Carona',
            body: 'Histórico de carona do usuário.'
        });

        window.index.add({
            href: 'pedir-carona',
            title: 'Pedir Carona',
            body: 'Pedir carona para outros usuários.'
        });

        window.index.add({
            href: 'oferecer-carona',
            title: 'Oferecer Carona',
            body: 'Oferecer carona para outros usuários.'
        });

        window.index.add({
            href: 'termos-de-uso',
            title: 'Termos de uso',
            body: 'Termos de uso dos usuários.'
        });

        window.index.add({
            href: 'politicas-de-privacidade',
            title: 'Politicas de Privacidade',
            body: 'Politicas de privacidade do usuário.'
        });

        window.index.add({
            href: 'logout',
            title: 'Sair',
            body: 'Sair do sistema javai.'
        });

        // icon click
        $('ul#nav-mobile li.search .search-wrapper i.material-icons').click(function () {
            if ($('.search-results .focused').length) {
                $('.search-results .focused').first()[0].click();
            } else if ($('.search-results').children().length) {
                $('.search-results').children().first()[0].click();
            }
        });

        var renderResults = function (results) {
            var resultsContainer = $('.search-results');
            resultsContainer.empty();
            Array.prototype.forEach.call(results, function (result) {
                console.log(result);
                var resultDiv = $('<a href=' + result[1] + '>' + result[0] + '</a>');
                resultsContainer.append(resultDiv);
            });
        };

        var debounce = function (fn) {
            var timeout;
            return function () {
                var args = Array.prototype.slice.call(arguments),
                        ctx = this;

                clearTimeout(timeout);
                timeout = setTimeout(function () {
                    fn.apply(ctx, args);
                }, 100);
            };
        };

        $('input#search').focus(function () {
            $(this).parent().addClass('focused');
        });
        $('input#search').blur(function () {
            if (!$(this).val()) {
                $(this).parent().removeClass('focused');
            }
        });

        $('input#search').bind('keyup', debounce(function (e) {
            if ($(this).val() < 2) {
                renderResults([]);
                return;
            }

            if (e.which === 38 || e.which === 40 || e.keyCode === 13)
                return;

            var query = $(this).val();
            var results = window.index.search(query).slice(0, 6).map(function (result) {
                var href = result.ref;
                return [href.charAt(0).toUpperCase() + href.slice(1), result.ref];
            });
            renderResults(results);
        }));


        $('input#search').bind('keydown', debounce(function (e) {
            // Escape.
            if (e.keyCode === 27) {
                $(this).val('');
                $(this).blur();
                renderResults([]);
                return;
            } else if (e.keyCode === 13) {
                // enter
                if ($('.search-results .focused').length) {
                    $('.search-results .focused').first()[0].click();
                } else if ($('.search-results').children().length) {
                    $('.search-results').children().first()[0].click();
                }
                return;
            }

            // Arrow keys.
            var focused;
            switch (e.which) {
                case 38: // up
                    if ($('.search-results .focused').length) {
                        focused = $('.search-results .focused');
                        focused.removeClass('focused');
                        focused.prev().addClass('focused');
                    }
                    break;

                case 40: // down
                    if (!$('.search-results .focused').length) {
                        focused = $('.search-results').children().first();
                        focused.addClass('focused');
                    } else {
                        focused = $('.search-results .focused');
                        if (focused.next().length) {
                            focused.removeClass('focused');
                            focused.next().addClass('focused');
                        }
                    }
                    break;

                default:
                    return; // exit this handler for other keys
            }
            e.preventDefault();
        }));



    });
}(jQuery));