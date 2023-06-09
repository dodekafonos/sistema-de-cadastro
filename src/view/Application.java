package view;

import model.AlunoDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        // Set the Look and Feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }

        new TelaCadastro();
    }
}


