package view;

import model.Aluno;
import model.AlunoDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class TelaBusca extends JFrame{

    public TelaBusca() {
        setContentPane(MainPane);
        setTitle("Sistema de Cadastro");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(Font.getFont("JetBrains Mono"));
//        getContentPane().setBackground(Color.decode("#658EA9"));
        setLocationRelativeTo(null);

        setVisible(true);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaCadastro().setVisible(true);
            }
        });
        // Botões antigos:
//        atualizarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                new TelaAtualizar().setVisible(true);
//            }
//        });
//        excluirButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                new TelaExcluir().setVisible(true);
//            }
//        });

        buscarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable();
            }
        });
        table1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            int selectedRow = table1.getSelectedRow();
                            if (selectedRow != -1) {
                                String cpf = table1.getValueAt(selectedRow, 0).toString();
                                System.out.println("Selected CPF: " + cpf);
                            }
                        }
                    }
                });
            }
        });
        atualizarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    String cpf = table1.getValueAt(selectedRow, 0).toString();
                    String nome = table1.getValueAt(selectedRow, 1).toString();
                    String dn = table1.getValueAt(selectedRow, 2).toString();
                    double peso = Double.parseDouble(table1.getValueAt(selectedRow, 3).toString());
                    int altura = Integer.parseInt(table1.getValueAt(selectedRow, 4).toString());
                    System.out.println("Selected CPF: " + cpf);
                    new AlunoDAO().atualizarDados(cpf, nome, dn, peso, altura);
                    JOptionPane.showMessageDialog(null, "Dados do aluno de CPF "+cpf+" atualizados com sucesso.");
                }
            }
        });
    }

    public void createTable() {
        String res = nomeTxt.getText();
        ArrayList<Aluno> alunos = new AlunoDAO().buscarAluno(res);

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[0][],
                new String[]{"CPF", "Nome", "Data de Nascimento", "Peso", "Altura"}
        );

        String[] header = {"CPF", "Nome", "Data Nasc.", "Peso", "Altura"};
        tableModel.addRow(header);
        for (Aluno aluno : alunos) {
            Object[] rowData = {aluno.getCpf(), aluno.getNome(), aluno.getDataNascimento(), aluno.getPeso(), aluno.getAltura()};
            tableModel.addRow(rowData);
        }

        table1.setModel(tableModel);
    }


    private JPanel MainPane;
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JButton buscarButton2;
    private JTable table1;
    private JTextField nomeTxt;
    private JButton atualizarDadosButton;
}
