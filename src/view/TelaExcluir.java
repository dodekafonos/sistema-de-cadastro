package view;

import model.AlunoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaExcluir extends JFrame {

    public TelaExcluir() {
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
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaAtualizar().setVisible(true);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaBusca().setVisible(true);
            }
        });
        buscaExcluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busca = nomeOuCpfExcluirTxt.getText();
                String res = String.valueOf(new AlunoDAO().buscarAluno(busca));
                textPane1.setText(res);
            }
        });
    }
    private JPanel MainPane;
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JButton excluirButton1;
    private JTextPane textPane1;
    private JButton buscaExcluirBtn;
    private JTextField nomeOuCpfExcluirTxt;
}
