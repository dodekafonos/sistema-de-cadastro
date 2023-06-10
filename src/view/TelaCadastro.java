package view;

import model.AlunoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;


public class TelaCadastro extends JFrame {

    public TelaCadastro() {

        setContentPane(MainPane);
        setTitle("Sistema de Cadastro");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(Font.getFont("JetBrains Mono"));
        setLocationRelativeTo(null);

        setVisible(true);

        AlunoDAO alunoController = new AlunoDAO();

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaBusca().setVisible(true);
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaBusca().setVisible(true);
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
        cadastrarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomeTxt.getText().equals("Ruy Mauro Marini") ||
                    nomeTxt.getText().equals("") ||
                    cpfTxt.getText().equals("123.456.789-01") ||
                    cpfTxt.getText().equals("") ||
                    diaTxt.getText().equals("Dia") ||
                    diaTxt.getText().equals("") ||
                    mesTxt.getText().equals("Mês") ||
                    mesTxt.getText().equals("") ||
                    anoTxt.getText().equals("Ano") ||
                    anoTxt.getText().equals("") ||
                    pesoTxt.getText().equals("82.5") ||
                    alturaTxt.getText().equals("182") ||
                    alturaTxt.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Favor preencher todos os campos.");
                } else {
                    String nome = nomeTxt.getText();
                    String cpf = cpfTxt.getText();
                    String dn = anoTxt.getText() + "-" + mesTxt.getText() + "-" + diaTxt.getText();
                    double peso = Double.parseDouble(pesoTxt.getText());
                    int altura = Integer.parseInt(alturaTxt.getText());
                    alunoController.inserirAluno(cpf, nome, dn, peso, altura);
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    nomeTxt.setText("");
                    cpfTxt.setText("");
                    diaTxt.setText("");
                    mesTxt.setText("");
                    anoTxt.setText("");
                    pesoTxt.setText("");
                    alturaTxt.setText("");
                }
            }
        });
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
