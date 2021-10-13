<%@page import="Control.VentaCTO"%>
<%@page import="Modelo.DTO.SesionApp"%>
<%@page import="Control.Facade"%>
<%@page import="Modelo.DTO.Usuario"%>
<%@page import="jdk.nashorn.internal.runtime.ListAdapter"%>
<%@ taglib  prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>

<%
    /*HttpSession sesion = request.getSession();
    Facade obF = new Facade();
    SesionApp usuRegis = (SesionApp) sesion.getAttribute("login");
    Usuario aux = new Usuario(usuRegis.getUsuario());
    Usuario usua = obF.verUsuario(aux);
    if (sesion.getAttribute("login") == null) {
        response.sendRedirect("index.jsp");
    } else {*/
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body>
        <%int i = 0;%>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 >Productos</h1>
                </div>
            </div>
            <div class="row">

                <div class="card col-sm-6 col-md-6 col-lg-8">

                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Imagen</th>
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Valor</th>     
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="objP" items="${lista}">
                                <tr>
                                    <td>${objP.getId()}</td>
                                    <td><img src="${objP.getImagen()}" height="60vw" width="100vw"></img></td>
                                    <td>${objP.getNombre()}</td>
                                    <td>${objP.getDescripcion()}</td>
                                    <td>${objP.getValor()}</td>

                                    <td>
                                        <form action="VentaCTO?menu=Venta" method="POST">
                                            <input class="from-control" type="text" name="cantidades"style="width: 3vw" >
                                            <input value="${objP.getId()}" class="from-control" type="hidden" name="idPro">                                            
                                            <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                                        </form>
                                    </td>

                                </tr>
                                </form>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
<%%>







