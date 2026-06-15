package dao;

import org.example.Cinema.Model.Usuario;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public Usuario autenticar(String email, String senha) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE Gmail = ? AND Senha = ?";
        Connection conn = DB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("Gmail"),
                            rs.getString("Senha"),
                            rs.getString("Tipo")
                    );
                }
            }
        } return null;
    }
    public void cadastrar(String email, String senha, String tipo) throws Exception {
        String sql = "INSERT INTO usuarios (Gmail, Senha, Tipo) VALUES (?, ?, ?)";
        Connection conn = DB.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            ps.setString(3, tipo);
            ps.executeUpdate();
        }
    }
}

