<%-- 
    Document   : index
    Created on : 15/06/2020, 11:57:12 AM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" type="text/css" href="CSS/EstiloPrincipal.css" media="screen">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>INICIO DE SECION</title>
    </head>
    <body>
          <div class="wrapper fadeInDown">
            <div id="formContent">
                <!-- Tabs Titles -->

                <!-- Icon -->
                <div class="fadeIn first">
                    <img src="imagenes/avatar.png"  height="128" width="128" id="icon" alt="User Icon" />
                    <h1>Bienvenidos al sistema de venta</h1>
                </div>

                <!-- Login Form -->
                <form action="LoginControl?accion=validaLogin" method="POST">
                    <input  class="fadeIn second" type="text" name="txt_usuario" placeholder="ejemplo@ejemplo.com"  required>
                    <input type="password" id="password" class="fadeIn third " name="txt_clave" placeholder="password" required>
                    <input  class="fadeIn fourth" type="submit" name="btn_ingresar" value="Ingresar">
                </form>

                <!-- Remind Passowrd -->
                <div id="formFooter">
                    <form action="UsuarioCTO?&menu=Usuario" method="POST">
                    <a  class="underlineHover" href="UsuarioCTO?menu=Usuario&accion=Listar">RESGISTAR</a>
                    </form>
                </div>

            </div>
        </div>
        <%--
        <div class="modal-content">
            <div class="col-12">
                <br>
                <img src="imagenes/login1.png" height="100vw"/>
            </div>
            <form action="LoginControl?accion=validaLogin" method="POST">
                <div class="form-group text-center">
                    <p>
                        <strong>
                            Bienvenidos al sistema de Login
                        </strong>
                    </p>                            


                    <div class="form-group text-center col-12">
                        <label>Usuario</label>
                        <input type="text" name="txt_usuario" placeholder="ejemplo@ejemplo.com" class="form-control" required  >
                    </div>
                    <div class="form-group text-center col-12">
                        <label>Contraseña</label>
                        <input type="password" name="txt_clave" placeholder="password" class="form-control" required>                            
                    </div>
                    <input type="submit" name="btn_ingresar" value="Ingresar" class="btn btn-primary">

                    <br>
                </div>
            </form>
            

                <a class="btn btn-primary" Style="margin-left: 10px;border: none"  >Registrar</a>
            </form>
        </div>
        --%>
    </body>

</html>
