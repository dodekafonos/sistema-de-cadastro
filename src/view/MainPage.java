package view;

import javax.swing.*;
import java.awt.Color;


public class MainPage extends JFrame {

    public MainPage() {
            initComponents();
            getContentPane().setBackground(Color.decode("#658EA9"));
            setLocationRelativeTo(null);

        }

        private void initComponents() {
            JButton cadastrarButton = new JButton("Cadastrar");
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPage frame = new MainPage();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack(); // Adjusts the frame size based on the components
            frame.setVisible(true); // Make the frame visible
        });
    }
    private JButton cadastrarButton;
    private JButton buscarButton;
    private JButton atualizarButton;
    private JButton excluirButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton cadastrarButton1;
}
