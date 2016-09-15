class Admin 
	constructor: (args) ->
		$(document).ready =>
			@visualizarRota()
			@btnRemoverCarona()
	btnRemoverCarona: () =>
		$this = @
		$(".secondary-content").on 'click', (e) ->
			e.preventDefault()
			e.stopPropagation();
			$this.removeCarona $(this).attr("data-tipo"), $(this).attr("data-id"), $(this)
	visualizarRota: () ->
		$(".list-map .collection-item").on 'click', ->
			$("#map-route iframe").attr 'src', $(this).attr 'href-map'
	toast: (msg) ->
        Materialize.toast msg , 3000
	removeCarona: (tipo, id, element) ->
		if tipo is 'pedir'
			url = 'excluir-pedido-carona'
		else
			url = 'excluir-oferta-carona'

		if confirm ("Deseja realmente exlcuir?")
			$.ajax
				url: url
				datatype: 'json'
				method: "POST"
				data:
					id: id
				success: (data) =>
					if data.status
						@toast data.msg
						element.parent().remove()
					else
						@toast "Ocorreu um erro inesperado no sistema!"
				
				
		

a = new Admin()