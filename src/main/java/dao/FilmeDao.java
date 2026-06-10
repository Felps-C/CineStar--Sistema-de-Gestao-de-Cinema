package dao;

import db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import org.example.Cinema.Model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
    /**
     * CLASSE DAO: VENDEDOR
     * * CONCEITO DIDÁTICO: Demonstra o cruzamento de dados (INNER JOIN) e a conversão de tipos complexos
     * de dados como Objetos de Data do Java (`java.util.Date`) para o formato SQL (`java.sql.Date`).
     */
    public class FilmeDao {

        public void insert(Filme obj) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = DB.getConnection();
                ps = conn.prepareStatement(
                        "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, obj.getNome());
                ps.setString(2, obj.getDiretor());
                ps.setString(3, obj.getDuracao());
                ps.setString(4, obj.getClassificacao());
                ps.setString(5, obj.getGenero());
                ps.setDouble(6, obj.getPreco());

                // CONVERSÃO DE DATA IMPORTANTE: O JDBC exige o formato 'java.sql.Date' para gravar no banco.
                // Pegamos o tempo em milissegundos da data utilitária e convertemos para a estrutura SQL.

                ps.setDouble(6, obj.getPreco());

                /* NAVEGAÇÃO DE CHAVE ESTRANGEIRA: Extrai o ID numérico de dentro do objeto Departamento associado
                ps.setInt(5, obj.getDepartment().getId());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        obj.setId(rs.getInt(1));
                    }
                    DB.closeResultSet(rs);
                }*/
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
                // INNER JOIN: Traz os dados do vendedor e também o nome do departamento correspondente
                // em uma única consulta ao banco, otimizando muito a performance do sistema.
                String sql = "SELECT seller.*, department.Name as DepName " +
                        "FROM seller INNER JOIN department " +
                        "ON seller.DepartmentId = department.Id " +
                        "ORDER BY seller.Id";

                st = conn.prepareStatement(sql);
                rs = st.executeQuery();

                List<Filme> list = new ArrayList<>();
                while (rs.next()) {
                    /* Instancia o Departamento associado com os dados retornados pelo INNER JOIN
                    Department dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));*/

                    // POPULAÇÃO DOS ATRIBUTOS UTILIZANDO OS MÉTODOS "SET" (Mais Didático e Seguro)
                    Filme filme = new Filme();
                    filme.setNome(rs.getString("Nome"));
                    filme.setDiretor(rs.getString("Diretor"));
                    filme.setDuracao(rs.getString("Duração")); // Puxa como data nativa
                    filme.setPreco(rs.getDouble("Preco"));

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
                        "UPDATE filme SET Nome = ?, Gênero = ?, Duração = ?, Classificação = ? WHERE Id = ?"
                );
                ps.setString(1, obj.getNome());
                ps.setString(2, obj.getGenero());
                ps.setString(3, obj.getDuracao());
                ps.setString(4, obj.getClassificacao());
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
                ps = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                DB.closeStatment(ps);
            }
        }
    }
