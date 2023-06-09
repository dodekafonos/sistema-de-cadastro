package model;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {

    public Connection connection;

    public AlunoDAO() {
        this.connection = DatabaseConnection.getConnectionMySQL();
    }

    // inserir novo aluno no BD:
    public void inserirAluno(String cpf, String nome, String dataNascimento, double peso, int altura) {
        String sql = "INSERT INTO aluno (cpf, nome, dataNascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            statement.setString(2, nome);
            statement.setString(3, dataNascimento);
            statement.setDouble(4, peso);
            statement.setInt(5, altura);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> buscarAluno(String nome) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String cpf = resultSet.getString("cpf");
                    String nome2 = resultSet.getString("nome");
                    String dn = resultSet.getString("dataNascimento");
                    double peso = resultSet.getDouble("peso");
                    int altura = resultSet.getInt("altura");
                    Aluno novo = new Aluno(cpf, nome2, dn, peso, altura);
                    alunos.add(novo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }
    public void atualizarNome(String cpf, String nome) {
        String sql = "UPDATE aluno SET nome = (?) WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " colunas afetadas.");
            System.out.println("Peso do aluno de cpf " + cpf + " alterada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarPeso(String cpf, double peso) {
        String sql = "UPDATE aluno SET peso = (?) WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " colunas afetadas.");
            System.out.println("Peso do aluno de cpf " + cpf + " alterada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void atualizarAltura(String cpf, int altura) {
        String sql = "UPDATE aluno SET altura = (?) WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " colunas afetadas.");
            System.out.println("Altura do aluno de cpf " + cpf + " alterada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletarAluno(String cpf) {
        String sql = "DELETE FROM aluno WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " colunas afetadas.");
            System.out.println("Aluno de cpf " + cpf + " excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCpfPeloNome(String nome) {
        String sql = "Select cpf FROM aluno WHERE nome = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " colunas afetadas.");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String cpf = resultSet.getString("cpf");
                    return cpf;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
