package com.ejemplo.agenda.dao;

import com.ejemplo.agenda.model.Contacto;
import com.ejemplo.agenda.util.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    // Listar todos los contactos
    public List<Contacto> listarTodos() {
        List<Contacto> contactos = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM contactos ORDER BY id DESC");

            while (rs.next()) {
                Contacto c = new Contacto();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setTelefono(rs.getString("telefono"));
                c.setcorreo(rs.getString("correo"));
                contactos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.cerrar(conn, stmt, rs);
        }

        return contactos;
    }

    // Obtener contacto por ID
    public Contacto obtenerPorId(int id) {
        Contacto contacto = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM contactos WHERE id = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                contacto = new Contacto();
                contacto.setId(rs.getInt("id"));
                contacto.setNombre(rs.getString("nombre"));
                contacto.setApellido(rs.getString("apellido"));
                contacto.setTelefono(rs.getString("telefono"));
                contacto.setcorreo(rs.getString("correo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.cerrar(conn, pstmt, rs);
        }

        return contacto;
    }

    // Insertar nuevo contacto
    public boolean insertar(Contacto contacto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DB.getConnection();
            String sql = "INSERT INTO contactos (nombre, apellido, telefono, correo) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contacto.getNombre());
            pstmt.setString(2, contacto.getApellido());
            pstmt.setString(3, contacto.getTelefono());
            pstmt.setString(4, contacto.getcorreo());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DB.cerrar(conn, pstmt, null);
        }
    }

    // Actualizar contacto
    public boolean actualizar(Contacto contacto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DB.getConnection();
            String sql = "UPDATE contactos SET nombre=?, apellido=?, telefono=?, correo=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, contacto.getNombre());
            pstmt.setString(2, contacto.getApellido());
            pstmt.setString(3, contacto.getTelefono());
            pstmt.setString(4, contacto.getcorreo());
            pstmt.setInt(5, contacto.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DB.cerrar(conn, pstmt, null);
        }
    }

    // Eliminar contacto
    public boolean eliminar(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DB.getConnection();
            String sql = "DELETE FROM contactos WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DB.cerrar(conn, pstmt, null);
        }
    }
}