
<%@ taglib  prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body onload="todo()">
        <div class="container">
            <div class="row">
                <div class="col-sm-14">
                    <h1 >Usuarios</h1>
                </div>
            </div>
            <div class="row">
                <div class="card col-sm-12">
                    <div class="card-body">
                        <h2 >Ingresar Usuario</h2>
                        <form action ="UsuarioCTO?menu=Usuario" method="POST">
                            <div class="form-group">
                                <label class="inp" for="txt_nombre1">Nombres</label>
                                <input type="hidden" value="${usuario.getId()}" name="txt_id">
                                <input type="text" class="form-control" name="txt_nombres" required value="${usuario.getNombre1()}" placeholder="Paco">
                            </div>

                            <div class="form-group">
                                <label class="inp" for="txt_apellido1">Apellidos</label>
                                <input type="text" class="form-control" name="txt_apellidos" required value="${usuario.getApellido1()}" placeholder="Rodriguez">
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_tipoD">Tipo de Documento</label>
                                <input id="help1" type="text" hidden="true" class="form-control" name="txt_tipo" value="${usuario.getSexo()}"> <%--este input lo utilio como ayuda para editar el combobox--%>
                                <select name="txt_sexo" class="form-control" id="sexo" required value="${usuario.getSexo()}">
                                    <option value="" hidden>Elija una opcion</option>
                                    <option value="Hombre">Cedula</option>
                                    <option value="Mujer">Tarjeta de Identidad</option>                                    
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_n_iden">Numero de identificacion</label>
                                <input type="text" class="form-control" name="txt_n_iden" required value="${usuario.getN_identificacion()}" placeholder="1111111111">
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_correo">Correo</label>
                                <input type="email" class="form-control" name="txt_correo" required value="${usuario.getCorreo()}" placeholder="pacopaco@ejemplo.com">
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_clave">Clave</label>
                                <input type="password" class="form-control" name="txt_clave" required value="${usuario.getClave()}">
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_clave">Ciudad</label>
                                <input type="text" class="form-control" name="txt_ciudad" required value="${usuario.getClave()}">
                            </div>
                            <div class="form-group">
                                <label class="inp" for="txt_clave">Direccion</label>
                                <input type="text" class="form-control" name="txt_direccion" required value="${usuario.getClave()}">
                            </div>
                            <input type="submit" class="btn btn-success" name="accion" value="Registrar">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%--Este script es para cambiar la seleccion en los combobox al editar un usuario, ya que con Java no pude realizarlo--%>
        <script>
            function todo() {
                cambiar();
                cambiar1();
                cambiar2();
            }
            function cambiar() {
                document.getElementById("est").value = document.getElementById("help").value; //toma el valor del elemento con el id combobox y lo cambia por el value del input que utilice com ayuda

            }
            function cambiar1() {
                document.getElementById("sexo").value = document.getElementById("help1").value; //toma el valor del elemento con el id combobox y lo cambia por el value del input que utilice com ayuda
            }
            function cambiar2() { //funcion para cambiar seleccion de un grupo de radio buttons
                var estado = document.getElementById("help").value;
                if (estado == document.getElementById("est1").value) {
                    document.getElementById("est1").checked = true;
                }
                if (estado == document.getElementById("est2").value) {
                    document.getElementById("est2").checked = true;
                } else {

                }
            }
        </script>                           
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>









