<%-- 
    Document   : DetallePedidoVTA
    Created on : 24/08/2020, 03:51:14 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>Carro</title>
    </head>
    <body>
        
        <div class="container">
            <h2 class="">Lista de Carro</h2>
            <div class="row">
                
                <div class="card col-sm-4 col-md-5 col-lg-7">
                    <table class="table table-hover table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Imegen</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">valor</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="objP" items="${lista}">
                                <tr>
                                    <td><img src="${objP.getImagen()}" height="40vw" width="75vw"></img></td>
                                    <td>${objP.getNombre()}</td>
                                    <td>${objP.getDescripcion()}</td>
                                    <td>${objP.getValor()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card col-sm-5">
                    <table class="table table-hover table-dark">
                        <thead>
                            <tr>
                                <th scope="col">unidades</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="deta" items="${detalle}"> 
                                <tr>
                                    <td>${deta.getCant_und()}</td>
                                    <td>
                                        <form action="DetalleCTO?menu=Producto" method="POST">

                                            <input class="from-control" type="text" name="cantidades"style="width: 4vw" placeholder="Unidades:">                                            
                                            <input value="${deta.getProductoId()}" class="from-control" type="hidden" name="idPro">     
                                            <input value="${deta.getId_pedido()}" class="from-control" type="hidden" name="idPed">
                                            <input type="submit" name="accion" value="Editar" class="btn btn-warning">
                                            <a  href="DetalleCTO?menu=Producto&accion=Eliminar&idPed=${deta.getId_pedido()}&idPro=${deta.getProductoId()}"><img src="imagenes/X.png" height="30vw"/></a>
                                        </form>                                 
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
            <div class="row">
                <div class="card col-sm-4 col-md-5 col-lg-12 text-center">
                    <form action="DetalleCTO?menu=Producto" method="POST">
                        <label>Observaciones</label>
                        <input class="from-control" type="text" name="observaciones"style="width: 14vw" placeholder="Observaciones:">
                        <br><label>El total de la Compra es</label>
                        <input  name="total" value="<c:out value="${total1}"/>" disabled>
                        <br><img src="imagenes/carrito.png" height="30vw"/><input type="submit" name="accion" value="Comprar" class="btn btn-warning">
                    </form>  
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
