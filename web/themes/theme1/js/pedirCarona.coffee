class PedirCarona 
    formulario: $("#form-pedir-carona")
    consideracoes: $("#consideracoes")
    enderecoSaida: $("#endereco-saida")
    enderecoChegada: $("#endereco-chegada")
    placeSearch: null
    autocompleteSaida: null
    autocompleteChegada: null
    PedirCaronaSaida: {}
    PedirCaronaChegada: {}
    componentForm:
        street_number: 'short_name', #numero
        route: 'long_name', #endereco
        neighborhood: 'long_name', #bairro
        locality: 'long_name', #cidade
        administrative_area_level_1: 'short_name', #estado sigla
        country: 'long_name', # pais
        postal_code: 'short_name' # cep
    constructor: (args) ->
        # body...
        @onSubmit()
    
    onSubmit: () ->
        @formulario.submit =>
            if @validaConsideracoes()
                @toast("Digite suas considerações!")
                false
            if @validaEnderecoSaida()
                @toast("Digite o endereço de saída corretamente ou selecione/clique no endereço que aparecer automaticamente")
                false
            if @validaEnderecoChegada()
                @toast("Digite o endereço de chegada corretamente ou selecione/clique no endereço que aparecer automaticamente")
                false
            
            $.ajax
                url: 'add-carona-ajax'
                method: 'POST'
                dataType: 'json'
                data:
                    consideracoes: @consideracoes.val()
                    endereco_saida_street_number: @PedirCaronaSaida.street_number
                    endereco_saida_neighborhood: @PedirCaronaSaida.neighborhood
                    endereco_saida_administrative_area_level_1: @PedirCaronaSaida.administrative_area_level_1
                    endereco_saida_locality: @PedirCaronaSaida.locality
                    endereco_saida_administrative_area_level_1: @PedirCaronaSaida.administrative_area_level_1
                    endereco_saida_country: @PedirCaronaSaida.country
                    endereco_saida_postal_code: @PedirCaronaSaida.postal_code
                    endereco_saida_lat: @PedirCaronaSaida.lat
                    endereco_saida_lng: @PedirCaronaSaida.lng

                    endereco_chegada_street_number: @PedirCaronaChegada.street_number
                    endereco_chegada_neighborhood: @PedirCaronaChegada.neighborhood
                    endereco_chegada_administrative_area_level_1: @PedirCaronaChegada.administrative_area_level_1
                    endereco_chegada_locality: @PedirCaronaChegada.locality
                    endereco_chegada_administrative_area_level_1: @PedirCaronaChegada.administrative_area_level_1
                    endereco_chegada_country: @PedirCaronaChegada.country
                    endereco_chegada_postal_code: @PedirCaronaChegada.postal_code
                    endereco_chegada_lat: @PedirCaronaChegada.lat
                    endereco_chegada_lng: @PedirCaronaChegada.lng

                success: (data) ->
                    console.log(data);
            false

    validaConsideracoes: () ->
        @consideracoes.val().length is 0
    validaEnderecoSaida: () ->
        @enderecoSaida.length is 0
    validaEnderecoChegada: () ->
        @enderecoChegada.length is 0

    toast: (msg) ->
        Materialize.toast msg

    initAutocomplete: () ->
        # Create the autocomplete object, restricting the search to geographical
        # location types.
        @autocompleteSaida = new google.maps.places.Autocomplete(
            document.getElementById 'endereco-saida',
            types: ['geocode']
        )
        @autocompleteChegada = new google.maps.places.Autocomplete(
            document.getElementById 'endereco-chegada',
            types: ['geocode']
        )

        
        # When the user selects an address from the dropdown, populate the address
        # fields in the form.
        @autocompleteSaida.addListener('place_changed', @fillInAddressSaida);
        @autocompleteChegada.addListener('place_changed', @fillInAddressChegada);

    fillInAddressSaida: () =>
        # Get the place details from the autocomplete object.
        place = @autocompleteSaida.getPlace();

        i = 0
        while i < place.address_components.length
            addressType = place.address_components[i].types[0]
            if @componentForm[addressType]
                val = place.address_components[i][@componentForm[addressType]]
                @PedirCaronaSaida[addressType] = val
            i++
        @PedirCaronaSaida['lat'] = place.geometry.location.lat();
        @PedirCaronaSaida['lng'] = place.geometry.location.lng();

    fillInAddressChegada: () =>
        # Get the place details from the autocomplete object.
        place = @autocompleteChegada.getPlace();

        i = 0
        while i < place.address_components.length
            addressType = place.address_components[i].types[0]
            if @componentForm[addressType]
                val = place.address_components[i][@componentForm[addressType]]
                @PedirCaronaChegada[addressType] = val
            i++
        @PedirCaronaChegada['lat'] = place.geometry.location.lat();
        @PedirCaronaChegada['lng'] = place.geometry.location.lng();
    
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

window.PedirCarona = new PedirCarona()