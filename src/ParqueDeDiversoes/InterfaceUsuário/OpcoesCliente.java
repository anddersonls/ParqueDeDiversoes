package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcoesCliente extends TelaBase {

    public OpcoesCliente(ParqueDiversoes parque) {
        super(parque);
        opcoes();
    }
    public void opcoes() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 20, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        panel.setBackground(corDeFundo);
        painelPrincipal.setBackground(corDeFundo);
        JButton botaoBrinquedo = new JButton("Comprar Ingresso para Brinquedos");
        JButton botaoEstabelecimento = new JButton("Comprar Comida em Estabelecimentos");
        JButton botaoCredito = new JButton("Colocar Crédito");
        JButton botaoRecibo = new JButton("Gerar Recibo");
        JButton botaoVoltar = new JButton();
        botaoBrinquedo.setFont(fontButton);
        botaoEstabelecimento.setFont(fontButton);
        botaoCredito.setFont(fontButton);
        botaoRecibo.setFont(fontButton);

        botaoBrinquedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                IngressoBrinquedo ingressoBrinquedo = new IngressoBrinquedo(parque);
            }
        });
        botaoEstabelecimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                IngressoEstabelecimento ingressoEstabelecimento = new IngressoEstabelecimento(parque);

            }
        });
        botaoCredito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Credito credito = new Credito(parque);
            }
        });
        botaoRecibo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GerarRecibo recibo = new GerarRecibo(parque);
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginCliente loginCliente = new LoginCliente(parque);
            }
        });
        botaoVoltar.setIcon(voltarIconRed);
        botaoVoltar.setPreferredSize(buttonSize);
        panel.add(new JLabel());
        panel.add(botaoBrinquedo);
        panel.add(botaoEstabelecimento);
        panel.add(botaoCredito);
        panel.add(botaoRecibo);
        panel.add(new JLabel());
        panel.add(new JPanel().add(botaoVoltar));

        painelPrincipal.add(panel);

        //pack();
        setVisible(true);

    }
}
