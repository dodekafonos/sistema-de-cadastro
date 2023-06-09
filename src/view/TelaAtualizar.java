package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAtualizar extends JFrame {
    public TelaAtualizar() {
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
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaExcluir().setVisible(true);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaBusca().setVisible(true);
            }
        });
    }

    private JPanel MainPane;
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JButton atualizarButton1;
}
