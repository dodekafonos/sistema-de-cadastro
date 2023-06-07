package view;

import database.DatabaseConnection;
import model.Aluno;
import model.AlunoDAO;

import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException {

        Aluno aluno_01 = new Aluno("131.013.936-98", "Jonas Alves Bueno", "1996-06-07", 58, 169);

        AlunoDAO alunoController = new AlunoDAO();

        alunoController.inserirAluno(aluno_01.getCpf(), aluno_01.getNome(), aluno_01.getDataNascimento(), aluno_01.getPeso(), aluno_01.getAltura());

        //alunoController.deletarAluno("123.123.123-12");
        alunoController.buscarAluno("Jonas");

    }

}
