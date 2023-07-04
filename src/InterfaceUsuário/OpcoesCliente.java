package InterfaceUsuário;

import InterfaceAdministrador.GerarRelatorio;
import parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcoesCliente extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private long cpf;
    public OpcoesCliente(ParqueDiversoes parque) {
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

        setLocationRelativeTo(null);
        opcoes();
    }
    public void opcoes() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 20, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        JButton botaoBrinquedo = new JButton("Comprar Ingresso para Brinquedos");
        JButton botaoEstabelecimento = new JButton("Comprar Comida em Estabelecimentos");
        JButton botaoCredito = new JButton("Colocar Crédito");
        JButton botaoRecibo = new JButton("Gerar Recibo");
        JButton botaoVoltar = new JButton("Voltar");

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

        panel.add(new JLabel());
        panel.add(botaoBrinquedo);
        panel.add(botaoEstabelecimento);
        panel.add(botaoCredito);
        panel.add(botaoRecibo);
        panel.add(new JLabel());
        panel.add(botaoVoltar);

        painelPrincipal.add(panel);

        //pack();
        setVisible(true);

    }
}
