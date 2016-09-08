class Perfil 

    validadorEmail: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    formulario: $("#form-perfil")
    nome: $("#nome")
    telefone: $("#telefone")
    email: $("#email")
    sexo: $("#sexo")

    constructor: (args) ->
        @initialize()

    initialize: () ->
        $(document).ready =>
            @onSubmitForm()

    validaEmail: () ->
        not @validadorEmail.test @email.val() #caso for falso

    validaNome: () ->
        @nome.val().length is 0

    validaTelefone: () ->
        @telefone.val().length is 0

    validaSexo: () ->
        @sexo.val() is ""

    toast: (msg) ->
        Materialize.toast msg , 3000

    validacaoFormulario: () =>
        if @validaNome()
            @toast("Digite seu nome")
            @nome.focus()
            return false
        if @validaTelefone()
            @toast("Digite seu nome")
            @telefone.focus()
            return false
        if @validaEmail()
            @toast("Digite seu nome")
            @email.focus()
            return false
        if @validaSexo()
            @toast("Digite seu nome")
            @sexo.focus()
            return false
        true
    onSubmitForm: () ->
        @formulario.submit =>
            @validacaoFormulario()

perfil = new Perfil()