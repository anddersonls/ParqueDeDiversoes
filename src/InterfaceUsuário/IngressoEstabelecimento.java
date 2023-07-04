package InterfaceUsuário;

import parque.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IngressoEstabelecimento extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;

    public IngressoEstabelecimento(ParqueDiversoes parque) {
        this.parque = parque;
        this.buttonSize = new Dimension(100, 25);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Minha Interface Gráfica");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);

        setLocationRelativeTo(null);
        telaEscolherEstabelecimento();
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
    public void telaEscolherEstabelecimento() {
        JTable tabela = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");

        for (Alimentacao estabelecimento : parque.getEstabelecimentos()) {
            tableModel.addRow(new Object[]{estabelecimento.getNome()});
        }
        tabela.setModel(tableModel);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoSelecionar = new JButton("Selecionar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoSelecionar.setPreferredSize(buttonSize);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoSelecionar);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });
        botaoSelecionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                int colunaSelecionada = 0;
                if (linhaSelecionada != -1) {
                    Object valorCelula = tabela.getValueAt(linhaSelecionada, colunaSelecionada);
                    for (Alimentacao estabelecimento : parque.getEstabelecimentos()) {
                        if(estabelecimento.getNome().equals(valorCelula)){
                            removeAllComponents();
                            telaCardapio(estabelecimento);
                        }

                    }
                }else{
                    JOptionPane.showMessageDialog(painelPrincipal, "Selecione um item!");
                }
            }
        });

        JLabel label = new JLabel("Selecione o estabelecimento que você deseja olhar o menu:");

        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(label);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        wrapperPanel.add(painelPrincipal, BorderLayout.CENTER);
        setContentPane(wrapperPanel);
        setVisible(true);
    }

    public void telaCardapio(Alimentacao estabelecimento){
        JTable tabela = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Comida");
        tableModel.addColumn("Valor");

        HashMap<String, Float> cardapio = estabelecimento.getCardapio();

        for (String comida: cardapio.keySet()) {
            tableModel.addRow(new Object[]{comida, cardapio.get(comida)});
        }

        tabela.setModel(tableModel);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoComprar = new JButton("Selecionar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoComprar.setPreferredSize(buttonSize);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoComprar);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });
        botaoComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                int colunaSelecionada = 0;
                if (linhaSelecionada != -1) {
                    Object valorCelula = tabela.getValueAt(linhaSelecionada, colunaSelecionada);
                    for (String menu : cardapio.keySet()) {
                        if(menu.equals(valorCelula)){


                            float valor = cardapio.get(valorCelula);
                            descontaValor(valor, estabelecimento);
                            removeAllComponents();
                            telaEscolherEstabelecimento();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(painelPrincipal, "Selecione um item!");
                }
            }
        });

        JLabel label = new JLabel("Selecione do menu a comida que você deseja comprar:");

        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(label);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        wrapperPanel.add(painelPrincipal, BorderLayout.CENTER);
        setContentPane(wrapperPanel);
        setVisible(true);
    }

    public void descontaValor(float valor, Alimentacao estabelecimento){
         String caminhoArquivo = "/home/joaovictor/Área de Trabalho/UFMA/3º Periodo/Linguagem de Programacao 2/LP2- TrabalhoFinal/Projeto/src/Arquivos/acessoCliente.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String cpfArquivo = br.readLine();
                long cpf = Long.parseLong(cpfArquivo);
                ArrayList<Visitante> clientes = parque.getVisitantes();
                for(Visitante visitante: clientes){
                    if(cpf == visitante.getCpf()){
                        //criando dependencia - so pode comprar comida em estabelecimentos se ja tiver visitado brinquedos
                        boolean possuiBrinquedo = false;
                        for (Atracoes atracao : visitante.getHistorico().keySet()) {
                            if (atracao instanceof Brinquedos) {
                                possuiBrinquedo = true;
                                break;
                            }
                        }
                        if (possuiBrinquedo) {
                            if(visitante.descontarCredito(valor)){
                                visitante.addNoHistorico(estabelecimento, valor);
                                JOptionPane.showMessageDialog(painelPrincipal, "Compra realizada com sucesso!");
                                break;
                            }else{
                                JOptionPane.showMessageDialog(painelPrincipal, "Voce não possui credito suficiente! Voce tem R$ "+visitante.getCredito());
                            }
                        }else {
                            JOptionPane.showMessageDialog(painelPrincipal, "Falha na compra! Você ainda não frequentou brinquedos!");
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar realizar a compra!");
            }
        }
}
