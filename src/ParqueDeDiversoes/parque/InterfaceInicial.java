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
        JLabel label = new JLabel("    Seja Bem Vindo ao Sistema do Bonini's Park");
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel painelBotoes = new JPanel();
        JPanel agradecimento = new JPanel();
        JButton admButton = new JButton("Administrador");
        JButton clienteButton = new JButton("Cliente");
        JButton agradecimentosButton = new JButton("***");
        agradecimento.add(agradecimentosButton);
        admButton.setPreferredSize(bigButton);
        clienteButton.setPreferredSize(bigButton);
        agradecimentosButton.setPreferredSize(buttonSize);
        painelBotoes.add(admButton);
        painelBotoes.add(clienteButton);
        admButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginAdm admInterface = new LoginAdm(parque);
            }
        });
        clienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginCliente userInterface = new LoginCliente(parque);
            }
        });
        agradecimentosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Equipe equipe = new Equipe(parque);
            }
        });

        painel.add(new JLabel());
        painel.add(label);
        painel.add(painelBotoes);
        painel.add(agradecimento);

        painelBotoes.setBackground(corDeFundo);
        agradecimento.setBackground(corDeFundo);
        painel.setBackground(corDeFundo);
        painelPrincipal.setBackground(corDeFundo);

        clienteButton.setIcon(clienteIconRed);
        admButton.setIcon(admIconRed);

        painelPrincipal.add(painel, BorderLayout.CENTER);
        label.setFont(fontTitle);
        admButton.setFont(fontButton);
        clienteButton.setFont(fontButton);
        agradecimentosButton.setFont(fontButton);
        setVisible(true);
    }
}