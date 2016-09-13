class PesquisarCarona 

    constructor:() ->
        @eventWindowResize()
    
    eventScrollMap: () ->
        $(window).scroll ->
            if $(this).scrollTop() >= 148
                $("#map-caronas").addClass("map-caronas-fixed").css("width", $(".container .area-pesquisa").width())
            else
                $("#map-caronas").removeClass("map-caronas-fixed").removeAttr("style");

    eventWindowResize: () =>
        @eventWindow()
        $(window).resize =>
            @eventWindow()

    eventWindow: () =>
        $(window).off "scroll"
        @eventScrollMap() if $(window).width() > 600
            
p = new PesquisarCarona();