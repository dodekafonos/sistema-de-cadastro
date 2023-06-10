package model;

import database.DatabaseConnection;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void atualizarDados(String cpf, String nome, String dn, double peso, int altura) {
        String sql = "UPDATE aluno SET nome = ?, dataNascimento = ?, peso = ?, altura = ? WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, dn);
            statement.setDouble(3, peso);
            statement.setInt(4, altura);
            statement.setString(5, cpf);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " colunas afetadas.");
            System.out.println("Dados do aluno de cpf " + cpf + " alterados com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String calculaIMC(String cpf) {
        String sql = "SELECT peso, altura FROM aluno WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double peso = resultSet.getDouble("peso");
                    double altura = resultSet.getInt("altura");
                    double imc = peso / ((altura / 100) * (altura / 100));

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    return decimalFormat.format(imc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Não foi possível calcular o IMC.";
    }

    // Converter data yyyy-mm-dd para dd/mm/yyyy:
    public static String formataData(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(outputFormatter);
    }
    // Converter data dd/mm/yyyy para yyyy-mm-dd:
    public static String desformataData(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return date.format(outputFormatter);
    }
}
