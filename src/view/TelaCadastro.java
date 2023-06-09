package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaCadastro extends JFrame {

    public TelaCadastro() {
        setContentPane(MainPane);
        setTitle("Sistema de Cadastro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(Font.getFont("JetBrains Mono"));
//        getContentPane().setBackground(Color.decode("#658EA9"));
        setLocationRelativeTo(null);
        setVisible(true);

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaAtualizacao().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new TelaCadastro();
    }
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JPanel MainPane;
    private JButton cadastrarButton1;
}
