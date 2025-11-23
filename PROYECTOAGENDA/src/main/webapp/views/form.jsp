<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty contacto ? 'Nuevo' : 'Editar'} Contacto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 3px solid #4CAF50;
            padding-bottom: 10px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        input[type="text"],
        input[type="tel"],
        input[type="correo"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input:focus {
            outline: none;
            border-color: #4CAF50;
        }
        .btn {
            padding: 12px 25px;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-right: 10px;
        }
        .btn-primary {
            background-color: #4CAF50;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .btn:hover {
            opacity: 0.8;
        }
        .botones {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>${empty contacto ? 'Nuevo Contacto' : 'Editar Contacto'}</h1>

    <form method="post" action="contactos">
        <input type="hidden" name="accion"
               value="${empty contacto ? 'insertar' : 'actualizar'}">

        <c:if test="${not empty contacto}">
            <input type="hidden" name="id" value="${contacto.id}">
        </c:if>

        <div class="form-group">
            <label for="nombre">Nombre *</label>
            <input type="text" id="nombre" name="nombre"
                   value="${contacto.nombre}" required
                   placeholder="Ingresa el nombre">
        </div>

        <div class="form-group">
            <label for="apellido">Apellido *</label>
            <input type="text" id="apellido" name="apellido"
                   value="${contacto.apellido}" required
                   placeholder="Ingresa el apellido">
        </div>

        <div class="form-group">
            <label for="telefono">Teléfono *</label>
            <input type="tel" id="telefono" name="telefono"
                   value="${contacto.telefono}" required
                   placeholder="Ej: 81-1234-5678">
        </div>

        <div class="form-group">
            <label for="correo">correo *</label>
            <input type="correo" id="correo" name="correo"
                   value="${contacto.correo}" required
                   placeholder="ejemplo@correo.com">
        </div>

        <div class="botones">
            <button type="submit" class="btn btn-primary">
                ${empty contacto ? 'Guardar' : 'Actualizar'}
            </button>
            <a href="contactos" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>