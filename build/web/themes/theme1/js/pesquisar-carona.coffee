class PesquisarCarona 
    formulario: $("#form-pesquisar-carona")
    campoPesquisa: $("#pesquisar")
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
                        @toast data.msg
                        @formulario[0].reset()
                        @OferecerCarona = {}
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