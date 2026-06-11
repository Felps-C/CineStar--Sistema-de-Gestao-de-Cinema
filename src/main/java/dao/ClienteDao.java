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
                    "INSERT INTO cliente (CPF, Nome) VALUES (?, ?)"
            );
            ps.setString(1, obj.getCPF());
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
                // Instancia usando o construtor da sua classe: Cliente(nome, CPF)
                Clientes cliente = new Clientes(
                        rs.getString("Nome"),
                        rs.getString("Cpf")
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
            // Atualiza o nome baseado no CPF fornecido
            ps = conn.prepareStatement(
                    "UPDATE cliente SET Nome = ? WHERE Cpf = ?"
            );
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCPF()); // Filtro WHERE

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }

    // Alterado de "deleteById" para "deleteByCpf" para casar com o seu Model
    public void deleteByCpf(String cpf) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement("DELETE FROM cliente WHERE Cpf = ?");
            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
}