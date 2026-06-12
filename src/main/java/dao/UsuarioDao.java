package dao;

import org.example.Cinema.Model.Usuario;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public Usuario autenticar(String email, String senha) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        // Pegamos a conexão da sua classe DB
        Connection conn = DB.getConnection();

        // Colocamos apenas o PreparedStatement e o ResultSet para fechar automaticamente
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("tipo")
                    );
                }
            }
        } // O PreparedStatement fecha aqui, mas a 'conn' continua viva no seu DB.java
        return null;
    }

    public void cadastrar(String email, String senha, String tipo) throws Exception {
        String sql = "INSERT INTO usuarios (email, senha, tipo) VALUES (?, ?, ?)";

        // Pegamos a conexão da sua classe DB
        Connection conn = DB.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);
            ps.setString(3, tipo);

            ps.executeUpdate();
        } // O PreparedStatement fecha aqui, mantendo a conexão segura
    }
}

