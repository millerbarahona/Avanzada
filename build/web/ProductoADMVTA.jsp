<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" type="text/css" href="CSS/EstiloProductoVTA.css" media="screen">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Productos</h1>
        <div class="container" class="card col-sm-4">
            <div class="card-body">
                <form action="ProductoCTO?menu=Producto" method="POST" action="/action_page.php">
                    <div class="row">
                        <div class="col-25">
                            <label>Nombre producto</label>
                        </div>
                        <div class="col-75">
                            <input value="${producto.getNombre}" class="from-control" type="text" name="txt_nombre_pro" required>
                            <input type="hidden" value="${producto.getId()}" name="txt_id_pro">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label>Descripcion Producto</label>
                        </div>
                        <div class="col-75" >  
                            <input value="${producto.getDescripcion()}"class="from-control"type="text" name="txt_descripcion.pro">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label>Link De Imagen</label>
                        </div>
                        <div class="col-75" >  
                            <input value="${producto.getImagen()}"class="from-control"type="text" name="txt_imagen.pro">
                        </div>
                    </div>    
                    <div class="row">
                        <div class="col-25">
                            <label>Unidades </label>
                        </div>
                        <div class="col-75" >  
                            <input value="${producto.getUnidades()}"class="from-control"type="text" name="txt_unidades.pro">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label>Valor </label>
                        </div>
                        <div class="col-75" >  
                            <input value="${producto.getValor()}"class="from-control"type="text" name="txt_valor.pro">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label>Iva </label>
                        </div>
                        <div class="col-75" >  
                            <input value="${producto.getIva()}"class="from-control"type="text" name="txt_Iva.pro">
                        </div>
                    </div>    
                    
                    <div >
                        <input  class="btn btn-success" type="submit" name="accion" value="Agregar" >
                    </div>
                    <div>    
                        <input class="btn btn-outline-secondary" type="submit" name="accion" value="Actulizar">
                    </div>
                </form>
            </div>
        </div>
        <div>
            <table id="tabla">
                <thead>
                    <tr id="tabla_T">
                        <th>N° id</th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Unidades</th>
                        <th>Valor</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="objP" items="${lista_productos}">
                        <tr>
                            <td>${objP.getId_p()}</td>
                            <td>${objP.getNombre_p()}</td>
                            <td>${objP.getDescripcion_p()}</td>
                            <td>${objP.getCantidad_p()}</td>
                            <td>${objP.getValor_p()}</td>
                            <td>
                                <a class="btn btn-warning" href="ProductoCTO?menu=Producto&accion=Editar&id=${objP.getId_p()}">Editar</a>
                                <a class="btn btn-danger" href="ProductoCTO?menu=Producto&accion=Eliminar&id=${objP.getId_p()}">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
