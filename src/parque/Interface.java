package parque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
        private JPanel contentPane;
        private Dimension buttonSize;
        private Font fontText;
        private Font fontButton;
        private Font fontTitle;
        private ParqueDiversoes parque;
        private AdmInterface admInterface;
        private UserInterface userInterface;
    public Interface(ParqueDiversoes parque){
            this.parque = parque;
            this.admInterface = new AdmInterface(parque);
            this.userInterface = new UserInterface(parque);
            interfaceInicial();
        }

    public Interface() {
    }

    public void interfaceInicial() {
            this.buttonSize = new Dimension(400, 50);
            this.fontText = new Font("Arial", Font.PLAIN, 14);
            this.fontTitle = new Font("Arial", Font.PLAIN, 20);
            this.fontButton = new Font("Arial", Font.PLAIN, 15);
            setTitle("Minha Interface Gráfica");
            setSize(600, 350);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            contentPane = new JPanel();
            setContentPane(contentPane);
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

            setLocationRelativeTo(null);

            JLabel label = new JLabel("Bem-Vindo ao sistema do Democracy Park!");

            JButton adm_button = new JButton("Administrador");
            JButton cliente_button = new JButton("Cliente");
            adm_button.setPreferredSize(new Dimension(200, 50));
            cliente_button.setPreferredSize(new Dimension(200, 50));
            adm_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    admInterface = new AdmInterface(parque);
                    admInterface.telaLogin();
                }
            });
            cliente_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    userInterface = new UserInterface(parque);
                    userInterface.telaLogin();
                }
            });

            adm_button.setAlignmentX(Component.CENTER_ALIGNMENT);
            cliente_button.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel painel = new JPanel();
            painel.add(Box.createVerticalStrut(100));
            painel.add(label);
            painel.add(Box.createVerticalStrut(15));
            painel.add(adm_button);
            painel.add(Box.createVerticalStrut(15));
            painel.add(cliente_button);
            contentPane.add(painel);

            label.setFont(fontTitle);
            adm_button.setFont(fontButton);
            cliente_button.setFont(fontButton);
            setVisible(true);
        }

    public void removeAllComponents() {
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
    }

}
