package view;

import javax.swing.*;

public class TelaAtualizacao extends JFrame {

    public TelaAtualizacao () {
        setContentPane(MainPane);
        setTitle("Sistema de Cadastro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        getContentPane().setBackground(Color.decode("#658EA9"));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JPanel MainPane;
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JButton cadastrarButton1;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
}
