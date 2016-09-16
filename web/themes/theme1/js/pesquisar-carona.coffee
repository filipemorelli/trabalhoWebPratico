class PesquisarCarona 
    formulario: $("#form-pesquisar-carona")
    campoPesquisa: $("#pesquisar")
    campoResultado: $(".result-search")
    placeSearch: null
    autocomplete: null
    OferecerCarona: {}
    componentForm:
        street_number: 'short_name', #numero
        route: 'long_name', #endereco
        sublocality_level_1: 'long_name', #bairro
        locality: 'long_name', #cidade
        administrative_area_level_1: 'short_name', #estado sigla
        country: 'long_name', # pais
        postal_code: 'short_name' # cep
    constructor:() ->
        @eventWindowResize()
        @onSubmit()
    init: () =>
      @initAutocomplete()
    
    onSubmit: () ->
        @formulario.submit =>
            if @validaPesquisa()
                @toast("Digite o endereço de saída corretamente ou selecione/clique no endereço que aparecer automaticamente")
                @campoPesquisa.focus()
                return false
            if @validaTipo()
                @toast("Selecione o tipo de carona")
                return false
            
            $.ajax
                url: 'pesquisar-carona-ajax'
                method: 'POST'
                cache: false
                dataType: 'json'
                data:
                    tipo: $("input[type=radio][name=tipo]:checked").val()

                    numero: @OferecerCarona.street_number
                    bairro: @OferecerCarona.sublocality_level_1
                    campoPesquisa: @OferecerCarona.route
                    cidade: @OferecerCarona.locality
                    estado: @OferecerCarona.administrative_area_level_1
                    pais: @OferecerCarona.country
                    cep: @OferecerCarona.postal_code
                    latitude: @OferecerCarona.lat
                    longitude: @OferecerCarona.lng

                success: (data) =>
                    if data.status
                        @formulario[0].reset()
                        @OferecerCarona = {}
                        d = JSON.parse data.msg
                        
                        @campoResultado.html(""); # limpa o que tiver
                        for value in d
                          #list = '<li class="collection-item avatar modal-trigger" data-target="map-route" href-map="https://www.google.com/maps/embed?pb=!1m25!1m12!1m3!1d15004.089086165857!2d-43.95328392071172!3d-19.923466285716867!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m10!3e6!4m3!3m2!1d[lat_saida]!2d[lng_saida]!4m4!2s[lat_chegada]%2C [lng_chegada]!3m2!1d[lat_chegada]!2d[lng_chegada]!5e0!3m2!1spt-BR!2sbr!4v1473694568701"> <img src="[url_imagem]" alt="[nome]" title="[nome]" class="circle"> <span class="title">[consideracoes]</span> <p>[endereco_saida] <br>[endereco_chegada] </p><a href="[url_social]" target="_blank" data-position="bottom" data-delay="50" data-tooltip="Ver perfil" class="secondary-content"><i class="material-icons">public</i></a> </li>'
                          list = '<li class="collection-item avatar modal-trigger" data-target="map-route" href-map="https://www.google.com/maps/embed/v1/directions?key=AIzaSyCHMIcsEYQt1RoizBuH--1bWaWFNUcqM2I&origin=[endereco_saida_completo]&destination=[endereco_chegada_completo]&avoid=tolls|highways"> <img src="[url_imagem]" alt="[nome]" title="[nome]" class="circle"> <span class="title">[consideracoes]</span> <p>[endereco_saida] <br>[endereco_chegada] </p><a href="[url_social]" target="_blank" data-position="bottom" data-delay="50" data-tooltip="Ver perfil" class="secondary-content"><i class="material-icons">public</i></a> </li>'
                          list = list.replace "[nome]", value.user.nome
                          list = list.replace "[nome]", value.user.nome
                          list = list.replace "[url_social]", value.user.url_social
                          list = list.replace "[url_imagem]", value.user.url_imagem
                          list = list.replace "[consideracoes]", value.consideracoes
                          list = list.replace "[endereco_chegada]", value.endereco_chegada.endereco_completo
                          list = list.replace "[endereco_saida]", value.endereco_saida.endereco_completo
                          list = list.replace "[endereco_chegada_completo]", encodeURI(value.endereco_chegada.endereco_completo)
                          list = list.replace "[endereco_saida_completo]", encodeURI(value.endereco_saida.endereco_completo)
                          list = list.replace "[lat_saida]", value.endereco_saida.latitude
                          list = list.replace "[lng_saida]", value.endereco_saida.longitude
                          list = list.replace "[lat_chegada]", value.endereco_chegada.latitude
                          list = list.replace "[lng_chegada]", value.endereco_chegada.longitude
                          
                          list = $(list)
                          list.leanModal();
                          list.find('.secondary-content').tooltip()
                          list.find('.secondary-content').on 'click', (e) ->
                              e.stopPropagation();
                          list.on 'click', ->
                            $("#map-route iframe").attr 'src', $(this).attr 'href-map'
                          @campoResultado.append list
                        
                    else
                        @toast data.msg
                error: () =>
                    @toast "Preencha o formulário corretamente"

            false

    validaTipo: () ->
        not ($("input[type=radio][name=tipo]:checked").val() is "pedir" or $("input[type=radio][name=tipo]:checked").val() is "oferecer")
    validaPesquisa: () ->
        @campoPesquisa.val().length is 0

    toast: (msg) ->
        Materialize.toast msg , 3000

    initAutocomplete: () ->
        # Create the autocomplete object, restricting the search to geographical
        # location types.
        @autocomplete = new google.maps.places.Autocomplete(
            document.getElementById 'pesquisar',
            types: ['geocode']
        )

        
        # When the user selects an address from the dropdown, populate the address
        # fields in the form.
        @autocomplete.addListener('place_changed', @fillInAddress);

    fillInAddress: () =>
        # Get the place details from the autocomplete object.
        place = @autocomplete.getPlace();

        i = 0
        while i < place.address_components.length
            addressType = place.address_components[i].types[0]
            if @componentForm[addressType]
                val = place.address_components[i][@componentForm[addressType]]
                @OferecerCarona[addressType] = val
            i++
        @OferecerCarona['lat'] = place.geometry.location.lat();
        @OferecerCarona['lng'] = place.geometry.location.lng();
      
    geolocate: ()->
        if navigator.geolocation
            navigator.geolocation.getCurrentPosition (position) ->
                geolocation = 
                    lat: position.coords.latitude
                    lng: position.coords.longitude

                circle = new google.maps.Circle
                    center: geolocation
                    radius: position.coords.accuracy
                
                @autocomplete.setBounds circle.getBounds()
    
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
            
window.PesquisarCarona = new PesquisarCarona();