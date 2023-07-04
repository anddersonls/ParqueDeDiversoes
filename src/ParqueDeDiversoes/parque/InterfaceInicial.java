package ParqueDeDiversoes.parque;

import ParqueDeDiversoes.InterfaceAdministrador.LoginAdm;
import ParqueDeDiversoes.InterfaceUsuário.LoginCliente;
import ParqueDeDiversoes.TelaBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceInicial extends TelaBase {
    public InterfaceInicial(ParqueDiversoes parque){
        super(parque);
        interfaceInicial();
    }
    public void interfaceInicial() {
        JLabel label = new JLabel("  Bem-Vindo ao sistema do Democracy Park!");
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel painelBotoes = new JPanel();
        JButton adm_button = new JButton("Administrador");
        JButton cliente_button = new JButton("Cliente");
        adm_button.setPreferredSize(bigButton);
        cliente_button.setPreferredSize(bigButton);
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