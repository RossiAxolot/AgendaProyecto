<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agenda de Contactos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 3px solid #4CAF50;
            padding-bottom: 10px;
        }
        .btn {
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            display: inline-block;
            margin: 5px;
        }
        .btn-primary {
            background-color: #4CAF50;
            color: white;
        }
        .btn-warning {
            background-color: #ff9800;
            color: white;
        }
        .btn-danger {
            background-color: #f44336;
            color: white;
        }
        .btn:hover {
            opacity: 0.8;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .mensaje {
            padding: 10px;
            margin: 10px 0;
            border-radius: 4px;
        }
        .exito {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Agenda de Contactos</h1>

    <c:if test="${not empty sessionScope.mensaje}">
        <div class="mensaje exito">
                ${sessionScope.mensaje}
        </div>
        <c:remove var="mensaje" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.error}">
        <div class="mensaje error">
                ${sessionScope.error}
        </div>
        <c:remove var="error" scope="session"/>
    </c:if>

    <a href="contactos?accion=nuevo" class="btn btn-primary">Nuevo Contacto</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Teléfono</th>
            <th>correo</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contacto" items="${contactos}">
            <tr>
                <td>${contacto.id}</td>
                <td>${contacto.nombre}</td>
                <td>${contacto.apellido}</td>
                <td>${contacto.telefono}</td>
                <td>${contacto.correo}</td>
                <td>
                    <a href="contactos?accion=editar&id=${contacto.id}"
                       class="btn btn-warning">Editar</a>
                    <a href="contactos?accion=eliminar&id=${contacto.id}"
                       class="btn btn-danger"
                       onclick="return confirm('¿Estás seguro de eliminar este contacto?')">
                        Eliminar</a>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty contactos}">
            <tr>
                <td colspan="6" style="text-align: center; padding: 20px; color: #999;">
                    No hay contactos registrados. ¡Agrega tu primer contacto!
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
