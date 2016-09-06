<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Favicons-->
<link rel="apple-touch-icon-precomposed" href="<c:url value='/themes/theme1/images/favicon/logo_apple.png' />">
<meta name="msapplication-TileColor" content="#455a64">
<meta name="msapplication-TileImage" content="<c:url value='/themes/theme1/images/favicon/logo_apple.png' />">
<link rel="icon" href="<c:url value='/themes/theme1/images/favicon/logo.png' />" sizes="32x32">
<!--  Android 5 Chrome Color-->
<meta name="theme-color" content="#455a64">
<!-- CSS-->
<link href="<c:url value='/themes/theme1/css/prism.css' />" rel="stylesheet">
<link href="<c:url value='/themes/theme1/css/ghpages-materialize.css' />" type="text/css" rel="stylesheet" media="screen,projection">
<link href="http://fonts.googleapis.com/css?family=Inconsolata" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
    #loader{
        position: fixed;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        z-index: 99999999;
        background: #f5f5f5;
    }
    #loader .preloader-wrapper{
        left: 50%;
        top: 50%;
        margin-left: -50px;
        margin-top: -50px;
    }
    .btn-block{
        display: block;
        width: 100%;
    }
    .waves-effect.waves-blue .waves-ripple {
        background-color: rgba(33, 150, 243, 0.65)
    }
    #front-page-logo{
        border-radius: 50%;
    }
    footer.page-footer{
        padding-top: 0;
    }
    /* label color */
    .input-field label {
        color: #1976D2;
    }
    /* label focus color */
    .input-field input:focus + label {
        color: #1976D2;
    }
    /* label underline focus color */
    .input-field input:focus {
        border-bottom: 1px solid #1976D2;
        box-shadow: 0 1px 0 0 #1976D2;
    }
    /* valid color */
    .input-field input.valid {
        border-bottom: 1px solid #4caf50;
        box-shadow: 0 1px 0 0 #4caf50;
    }
    /* invalid color */
    .input-field input.invalid {
        border-bottom: 1px solid #f44336;
        box-shadow: 0 1px 0 0 #f44336;
    }
    /* icon prefix focus color */
    .input-field .prefix {
        color: #2196F3;
    }
    .input-field .prefix.active {
        color: #1976D2;
    }
</style>