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
        this.buttonSize = new Dimension(200, 50);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Minha Interface Gráfica");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        setLocationRelativeTo(null);

        interfaceInicial(parque);
    }
    public void interfaceInicial(ParqueDiversoes parque) {
        JLabel label = new JLabel("  Bem-Vindo ao sistema do Democracy Park!");
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel painelBotoes = new JPanel();

        JButton adm_button = new JButton("Administrador");
        JButton cliente_button = new JButton("Cliente");
        adm_button.setPreferredSize(buttonSize);
        cliente_button.setPreferredSize(buttonSize);
        painelBotoes.add(adm_button);
        painelBotoes.add(cliente_button);
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

        painel.add(new JLabel());
        painel.add(label);
        painel.add(painelBotoes);
        painelPrincipal.add(painel);

        label.setFont(fontTitle);
        adm_button.setFont(fontButton);
        cliente_button.setFont(fontButton);
        setVisible(true);
    }
}