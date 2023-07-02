package parque;

import InterfaceAdministrador.LoginAdm;
import InterfaceUsuário.LoginCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceInicial extends JFrame {
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    public InterfaceInicial(ParqueDiversoes parque){
        this.parque = parque;
        this.buttonSize = new Dimension(400, 50);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Minha Interface Gráfica");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        setLocationRelativeTo(null);

        interfaceInicial(parque);
    }
    public void interfaceInicial(ParqueDiversoes parque) {
        JLabel label = new JLabel("Bem-Vindo ao sistema do Democracy Park!");

        JButton adm_button = new JButton("Administrador");
        JButton cliente_button = new JButton("Cliente");
        adm_button.setPreferredSize(new Dimension(200, 50));
        cliente_button.setPreferredSize(new Dimension(200, 50));
        adm_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginAdm admInterface = new LoginAdm(parque);
            }
        });
        cliente_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginCliente userInterface = new LoginCliente(parque);
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
        painelPrincipal.add(painel);

        label.setFont(fontTitle);
        adm_button.setFont(fontButton);
        cliente_button.setFont(fontButton);
        setVisible(true);
    }
}