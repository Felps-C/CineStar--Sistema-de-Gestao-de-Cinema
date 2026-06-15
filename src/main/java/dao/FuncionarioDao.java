package dao;

import db.DB;
import org.example.Cinema.Model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    public void insert(Funcionario obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO funcionario (Nome, Contato, Gênero, Cargo, Idade, Salario) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getContato());
            ps.setString(3, obj.getGênero());
            ps.setString(4, obj.getCargo());
            ps.setInt(5, obj.getIdade());
            ps.setDouble(6, obj.getSalario()); // Precisará do getSalario() no seu Model

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    // Vincula o ID auto-incrementado gerado pelo banco ao objeto
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
    public List<Funcionario> findAll() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT * FROM funcionario ORDER BY IdFuncionario";

            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Funcionario> list = new ArrayList<>();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getString("Nome"),
                        rs.getString("Contato"),
                        rs.getString("Genero"),
                        rs.getString("Cargo"),
                        rs.getInt("Idade"),
                        rs.getDouble("Salario")
                );
                funcionario.setId(rs.getInt("Id"));

                list.add(funcionario);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }
    public void update(Funcionario obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "UPDATE funcionario SET Nome = ?, Contato = ?, Genero = ?, Cargo = ?, Idade = ?, Salario = ? WHERE IdFuncionario = ?"
            );
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getContato());
            ps.setString(3, obj.getGênero());
            ps.setString(4, obj.getCargo());
            ps.setInt(5, obj.getIdade());
            ps.setDouble(6, obj.getSalario());
            ps.setInt(7, obj.getId()); // Filtro do WHERE
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
    public void deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement("DELETE FROM funcionario WHERE IdFunconario = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
}
