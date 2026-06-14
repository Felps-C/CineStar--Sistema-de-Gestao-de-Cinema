package dao;

import db.DB;
import org.example.Cinema.Model.Sessao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoDao {

    public void insert(Sessao obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO sessao (filme_id, horario, sala) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, obj.getFilmeId());
            ps.setString(2, obj.getHorario());
            ps.setString(3, obj.getSala());

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

    public List<Sessao> buscarSessoesPorFilme(Integer idFilme) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT * FROM sessao WHERE filme_id = ? ORDER BY horario";
            st = conn.prepareStatement(sql);
            st.setInt(1, idFilme);
            rs = st.executeQuery();

            List<Sessao> list = new ArrayList<>();
            while (rs.next()) {
                Sessao sessao = new Sessao();
                sessao.setId(rs.getInt("Idsessao"));
                sessao.setFilmeId(rs.getInt("filme_id"));
                sessao.setHorario(rs.getString("horario"));
                sessao.setSala(rs.getString("sala"));

                list.add(sessao);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }

    public void update(Sessao obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "UPDATE sessao SET filme_id = ?, horario = ?, sala = ? WHERE Idsessao = ?"
            );
            ps.setInt(1, obj.getFilmeId());
            ps.setString(2, obj.getHorario());
            ps.setString(3, obj.getSala());
            ps.setInt(4, obj.getId());
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
            ps = conn.prepareStatement("DELETE FROM sessao WHERE Idsessao = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
        }
    }
}