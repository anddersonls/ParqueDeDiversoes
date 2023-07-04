package ParqueDeDiversoes.InterfaceAdministrador;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcoesAdm extends TelaBase {
    public OpcoesAdm(ParqueDiversoes parque){
        super(parque);
        telaOpcoes();
    }

    public void telaOpcoes() {
        String[] opcoes = {"Adicionar Atração", "Remover Atração", "Gerar Relatório", "Voltar"};
        JButton[] opcoesBotoes = new JButton[opcoes.length];

        opcoesBotoes = layoutDeOpcoes(opcoes);

        opcoesBotoes[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesDeCadastro();
            }
        });
        opcoesBotoes[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesRemover();
            }
        });
        opcoesBotoes[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GerarRelatorio relatorio = new GerarRelatorio(parque);
            }
        });
        opcoesBotoes[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginAdm loginAdm = new LoginAdm(parque);
            }
        });
    }

    public void opcoesDeCadastro() {
        String[] opcoes = {"Cadastrar Brinquedo", "Cadastrar Estabelecimento", "Voltar"};
        JButton[] opcoesBotoes = new JButton[opcoes.length];

        opcoesBotoes = layoutDeOpcoes(opcoes);

        opcoesBotoes[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdmCadastra admCadastra = new AdmCadastra(parque);
                admCadastra.telaCadastrarBrinquedo();
            }
        });
        opcoesBotoes[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdmCadastra admCadastra = new AdmCadastra(parque);
                admCadastra.telaCadastrarEstabelecimento();
            }
        });
        opcoesBotoes[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });
    }
    public void opcoesRemover () {
        String[] opcoes = {"Remover Brinquedo", "Remover Estabelecimento", "Voltar"};
        JButton[] opcoesBotoes = new JButton[opcoes.length];

        opcoesBotoes = layoutDeOpcoes(opcoes);

        opcoesBotoes[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdmRemove admRemove = new AdmRemove(parque);
                admRemove.removerBrinquedo();
            }
        });

        opcoesBotoes[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdmRemove admRemove = new AdmRemove(parque);
                admRemove.removerEstabelecimento();
            }
        });

        opcoesBotoes[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });
    }


    public JButton[] layoutDeOpcoes(String[] opcoes){
        int i;

        JPanel painel = new JPanel(new GridLayout(7, 1, 20, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

        JButton[] opcoesBotao = new JButton[opcoes.length];
        painel.add(new JLabel());
        for(i=0; i<opcoes.length-1; i++){
            JButton botao = new JButton(opcoes[i]);
            botao.setPreferredSize(new Dimension(250, 40));
            opcoesBotao[i] = botao;
            painel.add(botao);
        }

        JPanel painelVoltar = new JPanel();
        JButton botaoVoltar = new JButton(opcoes[i]);
        opcoesBotao[i] = botaoVoltar;
        painelVoltar.add(botaoVoltar);
        painel.add(painelVoltar);
        botaoVoltar.setPreferredSize(buttonSize);
        painelPrincipal.add(painel);

        setVisible(true);
        return opcoesBotao;
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
}
