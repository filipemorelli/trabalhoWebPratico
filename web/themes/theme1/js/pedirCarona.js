// Generated by CoffeeScript 1.10.0
(function() {
  var PedirCarona,
    bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  PedirCarona = (function() {
    PedirCarona.prototype.formulario = $("#form-pedir-carona");

    PedirCarona.prototype.consideracoes = $("#consideracoes");

    PedirCarona.prototype.enderecoSaida = $("#endereco-saida");

    PedirCarona.prototype.enderecoChegada = $("#endereco-chegada");

    PedirCarona.prototype.placeSearch = null;

    PedirCarona.prototype.autocompleteSaida = null;

    PedirCarona.prototype.autocompleteChegada = null;

    PedirCarona.prototype.PedirCaronaSaida = {};

    PedirCarona.prototype.PedirCaronaChegada = {};

    PedirCarona.prototype.componentForm = {
      street_number: 'short_name',
      route: 'long_name',
      sublocality_level_1: 'long_name',
      locality: 'long_name',
      administrative_area_level_1: 'short_name',
      country: 'long_name',
      postal_code: 'short_name'
    };

    function PedirCarona(args) {
      this.fillInAddressChegada = bind(this.fillInAddressChegada, this);
      this.fillInAddressSaida = bind(this.fillInAddressSaida, this);
      this.onSubmit();
    }

    PedirCarona.prototype.onSubmit = function() {
      return this.formulario.submit((function(_this) {
        return function() {
          if (_this.validaEnderecoSaida()) {
            _this.toast("Digite o endereço de saída corretamente ou selecione/clique no endereço que aparecer automaticamente");
            _this.enderecoSaida.focus();
            return false;
          }
          if (_this.validaEnderecoChegada()) {
            _this.toast("Digite o endereço de chegada corretamente ou selecione/clique no endereço que aparecer automaticamente");
            _this.enderecoChegada.focus();
            return false;
          }
          if (_this.validaConsideracoes()) {
            _this.toast("Digite suas considerações!");
            _this.consideracoes.focus();
            return false;
          }
          $.ajax({
            url: 'add-pedido-carona-ajax',
            method: 'POST',
            dataType: 'json',
            data: {
              consideracoes: _this.consideracoes.val(),
              endereco_saida_street_number: _this.PedirCaronaSaida.street_number,
              endereco_saida_neighborhood: _this.PedirCaronaSaida.sublocality_level_1,
              endereco_saida_route: _this.PedirCaronaSaida.route,
              endereco_saida_locality: _this.PedirCaronaSaida.locality,
              endereco_saida_administrative_area_level_1: _this.PedirCaronaSaida.administrative_area_level_1,
              endereco_saida_country: _this.PedirCaronaSaida.country,
              endereco_saida_postal_code: _this.PedirCaronaSaida.postal_code,
              endereco_saida_lat: _this.PedirCaronaSaida.lat,
              endereco_saida_lng: _this.PedirCaronaSaida.lng,
              endereco_chegada_street_number: _this.PedirCaronaChegada.street_number,
              endereco_chegada_neighborhood: _this.PedirCaronaChegada.sublocality_level_1,
              endereco_chegada_route: _this.PedirCaronaChegada.route,
              endereco_chegada_locality: _this.PedirCaronaChegada.locality,
              endereco_chegada_administrative_area_level_1: _this.PedirCaronaChegada.administrative_area_level_1,
              endereco_chegada_country: _this.PedirCaronaChegada.country,
              endereco_chegada_postal_code: _this.PedirCaronaChegada.postal_code,
              endereco_chegada_lat: _this.PedirCaronaChegada.lat,
              endereco_chegada_lng: _this.PedirCaronaChegada.lng
            },
            success: function(data) {
              if (data.status) {
                _this.toast(data.msg);
                _this.formulario[0].reset();
                _this.PedirCaronaChegada = {};
                return _this.PedirCaronaSaida = {};
              } else {
                return _this.toast("Preencha o formulário corretamente");
              }
            },
            error: function() {
              return _this.toast("Preencha o formulário corretamente");
            }
          });
          return false;
        };
      })(this));
    };

    PedirCarona.prototype.validaConsideracoes = function() {
      return this.consideracoes.val().length === 0;
    };

    PedirCarona.prototype.validaEnderecoSaida = function() {
      return this.enderecoSaida.val().length === 0;
    };

    PedirCarona.prototype.validaEnderecoChegada = function() {
      return this.enderecoChegada.val().length === 0;
    };

    PedirCarona.prototype.toast = function(msg) {
      return Materialize.toast(msg, 3000);
    };

    PedirCarona.prototype.initAutocomplete = function() {
      this.autocompleteSaida = new google.maps.places.Autocomplete(document.getElementById('endereco-saida', {
        types: ['geocode']
      }));
      this.autocompleteChegada = new google.maps.places.Autocomplete(document.getElementById('endereco-chegada', {
        types: ['geocode']
      }));
      this.autocompleteSaida.addListener('place_changed', this.fillInAddressSaida);
      return this.autocompleteChegada.addListener('place_changed', this.fillInAddressChegada);
    };

    PedirCarona.prototype.fillInAddressSaida = function() {
      var addressType, i, place, val;
      place = this.autocompleteSaida.getPlace();
      i = 0;
      while (i < place.address_components.length) {
        addressType = place.address_components[i].types[0];
        if (this.componentForm[addressType]) {
          val = place.address_components[i][this.componentForm[addressType]];
          this.PedirCaronaSaida[addressType] = val;
        }
        i++;
      }
      this.PedirCaronaSaida['lat'] = place.geometry.location.lat();
      return this.PedirCaronaSaida['lng'] = place.geometry.location.lng();
    };

    PedirCarona.prototype.fillInAddressChegada = function() {
      var addressType, i, place, val;
      place = this.autocompleteChegada.getPlace();
      i = 0;
      while (i < place.address_components.length) {
        addressType = place.address_components[i].types[0];
        if (this.componentForm[addressType]) {
          val = place.address_components[i][this.componentForm[addressType]];
          this.PedirCaronaChegada[addressType] = val;
        }
        i++;
      }
      this.PedirCaronaChegada['lat'] = place.geometry.location.lat();
      return this.PedirCaronaChegada['lng'] = place.geometry.location.lng();
    };

    PedirCarona.prototype.geolocate = function() {
      if (navigator.geolocation) {
        return navigator.geolocation.getCurrentPosition(function(position) {
          var circle, geolocation;
          geolocation = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          circle = new google.maps.Circle({
            center: geolocation,
            radius: position.coords.accuracy
          });
          return this.autocomplete.setBounds(circle.getBounds());
        });
      }
    };

    return PedirCarona;

  })();

  window.PedirCarona = new PedirCarona();

}).call(this);

//# sourceMappingURL=pedirCarona.js.map
