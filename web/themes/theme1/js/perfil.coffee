class Perfil 

    validadorEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    formulario = $("#form-perfil")
    nome = $("#nome")
    telefone = $("#telefone")
    email = $("#email")
    sexo = $("#sexo")

    constructor: (args) ->
        @validacaoFormulario() #validacao formulario

    validaEmail: () ->
        not validadorEmail.test email.val() #caso for falso
    validaNome: () ->
        nome.val().length is 0
    validaTelefone: () ->
        telefone.val().length is 0
    validaSexo: () ->
        sexo.val().length is ""
    toast: (msg) ->
        Materialize.toast msg
    validacaoFormulario: () ->
        if @validaNome()
            @toast("Digite seu nome")
            false
        if @validaTelefone()
            @toast("Digite seu nome")
            false
        if @validaEmail()
            @toast("Digite seu nome")
            false
        if @validaSexo()
            @toast("Digite seu nome")
            false
        true
    onSubmitForm: () ->
        formulario.submit => 
            @validacaoFormulario()
    