package com.ejemplo.agenda.web;

import com.ejemplo.agenda.dao.ContactoDAO;
import com.ejemplo.agenda.model.Contacto;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/contactos")
public class ContactoServlet extends HttpServlet {

    private ContactoDAO contactoDAO;

    @Override
    public void init() throws ServletException {
        contactoDAO = new ContactoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                listarContactos(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminarContacto(request, response);
                break;
            default:
                listarContactos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {
            insertarContacto(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarContacto(request, response);
        }
    }

    // Listar todos los contactos
    private void listarContactos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Contacto> contactos = contactoDAO.listarTodos();
        request.setAttribute("contactos", contactos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/lista.jsp");
        dispatcher.forward(request, response);
    }

    // Mostrar formulario para nuevo contacto
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/form.jsp");
        dispatcher.forward(request, response);
    }

    // Mostrar formulario para editar contacto
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Contacto contacto = contactoDAO.obtenerPorId(id);
        request.setAttribute("contacto", contacto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/form.jsp");
        dispatcher.forward(request, response);
    }

    // Insertar nuevo contacto
    private void insertarContacto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Contacto contacto = new Contacto(0, nombre, apellido, telefono, correo);
        boolean exito = contactoDAO.insertar(contacto);

        if (exito) {
            request.getSession().setAttribute("mensaje", "Contacto agregado exitosamente");
        } else {
            request.getSession().setAttribute("error", "Error al agregar contacto");
        }

        response.sendRedirect("contactos");
    }

    // Actualizar contacto existente
    private void actualizarContacto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Contacto contacto = new Contacto(id, nombre, apellido, telefono, correo);
        boolean exito = contactoDAO.actualizar(contacto);

        if (exito) {
            request.getSession().setAttribute("mensaje", "Contacto actualizado exitosamente");
        } else {
            request.getSession().setAttribute("error", "Error al actualizar contacto");
        }

        response.sendRedirect("contactos");
    }

    // Eliminar contacto
    private void eliminarContacto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        boolean exito = contactoDAO.eliminar(id);

        if (exito) {
            request.getSession().setAttribute("mensaje", "Contacto eliminado exitosamente");
        } else {
            request.getSession().setAttribute("error", "Error al eliminar contacto");
        }

        response.sendRedirect("contactos");
    }
}