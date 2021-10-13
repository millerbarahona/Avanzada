

<%@page import="Control.Facade"%>
<%@page import="Modelo.DTO.SesionApp"%>
<%@page import="Modelo.DTO.Usuario"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<%
    HttpSession sesion = request.getSession();
    Facade obF = new Facade();
    SesionApp usuRegis = (SesionApp) sesion.getAttribute("login");
    Usuario aux = new Usuario(usuRegis.getUsuario());
    Usuario usua = obF.verUsuario(aux);
    if (sesion.getAttribute("login") == null) {
        response.sendRedirect("index.jsp");
    } else {
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Parcial Avanzada</title>
        <link rel="icon" type="image/png" href="favicon.png"> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="icon" type="image/png" href="favicon.png">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">            
            <div id="navbar-Nav" class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="btn btn-outline-light" Style="margin-left: 10px;border: none" href="#" id="pp2"><label id="pp2">Inicio</label></a>
                    </li >
                    <li class="nav-item">
                        <a class="btn btn-outline-light" Style="margin-left: 10px;border: none" href="VentaCTO?menu=Venta&accion=Listar" target="Frame" id="pp2">Ventas</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" Style="margin-left: 10px;border: none" href="DetalleCTO?menu=Producto&accion=Listar" target="Frame" id="pp2">Carrito</a>
                    </li>

                </ul>
            </div>
            <div class="dropdown">
                <a href="#" class="btn btn-outline-light dropdown-toggle" style="margin-right: 8vw; border:  none" data-toggle="dropdown">Mi cuenta</a>
                <div class="dropdown-menu text-center">
                    <a><%= usua.toString()%><br></a>
                    <a><%= usua.getNumero_docto()%><br></a>
                    <a><%= usua.getRol()%><br></a>
                    <div class="dropdown-divide"></div>
                    <a href="LoginControl?accion=salir&id=<%= usua.getId_usu()%>">Cerrar Sesion</a>
                </div>
            </div>
        </nav>
        <div style="height: 88vh;">
            <iframe name="Frame" style="height: 100%;width: 100%;border: 2px"></iframe>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
    <footer>
        <h2 class="h2">Miller Barahona 20182578039</h2>
    </footer>
</html>
<% }
%>
