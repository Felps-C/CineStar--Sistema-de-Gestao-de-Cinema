package dao;

import db.DB;
import org.example.Cinema.Model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    public void insert(Produto obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO produto (Nome, Quantidade, Valor, Validade) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getQuantidade());
            ps.setDouble(3, obj.getValor());
            ps.setString(4, obj.getValidade()); // Tratado como String conforme seu modelo

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    // Preenche o ID gerado automaticamente pelo banco de dados
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

    public List<Produto> findAll() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT * FROM produto ORDER BY Idlanche";

            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                // Instancia usando o construtor customizado da sua classe Produto
                Produto produto = new Produto(
                        rs.getString("Nome"),
                        rs.getInt("Quantidade"),
                        rs.getDouble("Valor"),
                        rs.getString("Validade")
                );
                // Vincula o ID recuperado do banco
                produto.setId(rs.getInt("Id"));

                list.add(produto);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }

    public void update(Produto obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "UPDATE produto SET Nome = ?, Quantidade = ?, Valor = ?, Validade = ? WHERE Idlanche = ?"
            );
            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getQuantidade());
            ps.setDouble(3, obj.getValor());
            ps.setString(4, obj.getValidade());
            ps.setInt(5, obj.getId()); // Filtro do WHERE

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
            ps = conn.prepareStatement("DELETE FROM produto WHERE Idlanche = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
}