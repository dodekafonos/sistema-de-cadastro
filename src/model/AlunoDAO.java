package model;

import database.DatabaseConnection;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
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
    public String getNomePeloCpf(String cpf) {
        String sql = "Select nome FROM aluno WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    return nome;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public String getAlturaPeloCpf(String cpf) {
        String sql = "Select altura FROM aluno WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String altura = resultSet.getString("altura");
                    return altura;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public String getPesoPeloCpf(String cpf) {
        String sql = "Select peso FROM aluno WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String peso = resultSet.getString("peso");
                    return peso;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public int getIdadePeloCpf(String cpf) {
        String sql = "Select dataNascimento FROM aluno WHERE cpf = (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String dn = resultSet.getString("dataNascimento");
                    LocalDate hoje = LocalDate.now();
                    LocalDate birthDate = LocalDate.parse(dn);
                    Period period = Period.between(birthDate, hoje);
                    return period.getYears();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void atualizarDados(String cpf, String nome, String dn, double peso, int altura) {
        dn = desformataData(dn);
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

    public double calculaIMC(String cpf) {
        String sql = "SELECT peso, altura FROM aluno WHERE cpf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double peso = resultSet.getDouble("peso");
                    double altura = resultSet.getInt("altura");
                    double imc = peso / ((altura / 100) * (altura / 100));
                    return Math.round(imc * 100.0) / 100.0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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

    // Parecer sobre IMC:
    public String parecerImc(double imc) {
        if (imc < 18.5) {
            return "magreza. \nRecomendamos uma dieta com mais carboidratos e proteínas e exercícios de hipertrofia para o ganho de massa magra.";
        } else if (imc < 24.9) {
            return "normalidade. \nVocê está saudável! Mantenha os bons hábitos.";
        } else if (imc < 29.9) {
            return "sobrepeso. \nRecomendamos uma dieta focada em fibras e proteína e exercícios aeróbicos.";
        } else if (imc < 39.9) {
            return "obesidade. \nRecomendamos que consulte um nutricionista e faça exercícios aeróbicos regularmente.";
        } else {
            return "obesidade grave. \nRecomendamos que consulte especialistas para melhorar sua saúde.";
        }
    }

    // Imprimir dados em um arquivo txt:
    public void imprimeDados(String cpf) {
        String caminho = System.getProperty("user.home") + "/Desktop";
        String nome = getNomePeloCpf(cpf);
        String data = formataData(String.valueOf(LocalDate.now()));
        String hoje = String.valueOf(LocalDate.now());
        String filePath = caminho + "/relatorio " + nome + " " + hoje + ".txt";
        String relatorio =  "RELATÓRIO AUTOMÁTICO DE PROGRESSO" + "\n" +
                            "==============================================================================" + "\n" +
                            "Nome: " + nome + "\n" +
                            "Idade: " + getIdadePeloCpf(cpf) + "\n" +
                            "Peso: " + getPesoPeloCpf(cpf) + "\n" +
                            "Altura: " + getAlturaPeloCpf(cpf) + "\n" +
                            "Índice de Massa Corporal (IMC): " + calculaIMC(cpf) + "\n" +
                            "Parecer sobre IMC: sua situação corpórea é de: " + parecerImc(calculaIMC(cpf)) + "\n" +
                            "==============================================================================" + "\n" +
                            "Emitido em: " + data;


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(relatorio);
            System.out.println("Arquivo salvo em: " + filePath);
            JOptionPane.showMessageDialog(null, "Relatório de " + nome + "salvo em: " + filePath);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar salvar o arquivo: " + e.getMessage());
        }
    }
}

