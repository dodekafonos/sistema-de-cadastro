package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class TelaCadastro extends JFrame {

    public TelaCadastro() {

        setContentPane(MainPane);
        setTitle("Sistema de Cadastro");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(Font.getFont("JetBrains Mono"));
//        getContentPane().setBackground(Color.decode("#658EA9"));
        setLocationRelativeTo(null);

        setVisible(true);

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaAtualizar().setVisible(true);
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
        diaTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                diaTxt.setText("");
            }
        });
        mesTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                mesTxt.setText("");
            }
        });
        anoTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                anoTxt.setText("");
            }
        });
        nomeTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                nomeTxt.setText("");
            }
        });
        cpfTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                cpfTxt.setText("");
            }
        });
        pesoTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                pesoTxt.setText("");
            }
        });
        alturaTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                alturaTxt.setText("");
            }
        });
    }

    public static void main(String[] args) {
        // Set the Look and Feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        new TelaCadastro();
    }
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JPanel MainPane;
    private JButton cadastrarButton1;
    private JTextField diaTxt;
    private JTextField mesTxt;
    private JTextField anoTxt;
    private JTextField nomeTxt;
    private JTextField cpfTxt;
    private JTextField pesoTxt;
    private JTextField alturaTxt;
}
