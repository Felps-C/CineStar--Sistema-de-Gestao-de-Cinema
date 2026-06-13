package dao;
import db.DB;
import org.example.Cinema.Model.Filme;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao {

    public void insert(Filme obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO filme (Nome, Classificacao, Genero, Duracao, Preco) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getClassificacao());
            ps.setString(3, obj.getGenero());
            ps.setString(4, obj.getDuracao());
            ps.setDouble(5, obj.getPreco());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
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
    public List<Filme> findAll() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT * FROM filme ORDER BY Idfilme";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            List<Filme> list = new ArrayList<>();
            while (rs.next()) {
                Filme filme = new Filme(
                        rs.getString("Nome"),
                        rs.getString("Classificacao"),
                        rs.getString("Genero"),
                        rs.getString("Duracao"),
                        rs.getDouble("Preco")
                );
                filme.setId(rs.getInt("Idfilme"));

                list.add(filme);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }
    public void update(Filme obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "UPDATE filme SET Nome = ?, Classificacao = ?, Genero = ?, Duracao = ?, Preco=? WHERE Idfilme = ?"
            );
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getClassificacao());
            ps.setString(3, obj.getGenero());
            ps.setString(4, obj.getDuracao());
            ps.setDouble(5, obj.getPreco());
            ps.setInt(6, obj.getId());
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
            ps = conn.prepareStatement("DELETE FROM filme WHERE Idfilme = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
}