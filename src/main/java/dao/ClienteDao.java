package dao;

import db.DB;
import org.example.Cinema.Model.Clientes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public void insert(Clientes obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO cliente (Id, Nome) VALUES (?, ?)");
            ps.setInt(1, obj.getID());
            ps.setString(2, obj.getNome());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
    public List<Clientes> findAll() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT * FROM cliente ORDER BY Nome";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            List<Clientes> list = new ArrayList<>();
            while (rs.next()) {
                Clientes cliente = new Clientes(
                        rs.getString("Nome"),
                        rs.getString("Id")
                );
                list.add(cliente);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }
    public void update(Clientes obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "UPDATE cliente SET Nome = ? WHERE Id = ?");
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
    public void deleteByCpf(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement("DELETE FROM cliente WHERE Id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
}