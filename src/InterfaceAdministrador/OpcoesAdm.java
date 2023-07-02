package InterfaceAdministrador;

import parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class OpcoesAdm extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private HashMap<Object, Object> cardapio;
    public OpcoesAdm(ParqueDiversoes parque){
        this.parque = parque;
        cardapio = new HashMap<>();
        this.buttonSize = new Dimension(100, 25);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        setTitle("Administrador");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        telaOpcoes();
    }

    public OpcoesAdm() {
    }

    public void telaOpcoes() {
        String[] opcoes = {"Adicionar Atração", "Remover Atração", "Voltar"};
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
        painel.add(new JLabel());

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
