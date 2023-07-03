package InterfaceUsuário;

import parque.Alimentacao;
import parque.Brinquedos;
import parque.ParqueDiversoes;
import parque.Visitante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IngressoEstabelecimento extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private long cpf;

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
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        final HashMap<String, Float>[] cardapio = new HashMap[]{new HashMap<>()};
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

    public static class CustomListCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                // Adicionando uma bolinha colorida ao lado do item selecionado
                Color selectionColor = Color.GRAY; // Cor da bolinha (pode ser alterada)
                renderer.setBackground(selectionColor);
                renderer.setForeground(list.getForeground());
            }

            return renderer;
        }
    }

    public void telaCardapio(Alimentacao estabelecimento){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

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
                            descontaValor(valor);
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

    public void descontaValor(float valor){
         String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/Arquivos/acessoCliente.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String cpfArquivo = br.readLine();
                long cpf = Long.parseLong(cpfArquivo);
                ArrayList<Visitante> clientes = parque.getVisitantes();
                for(Visitante visitante: clientes){
                    if(cpf == visitante.getCpf()){
                        if(visitante.descontarCredito(valor)){
                            JOptionPane.showMessageDialog(painelPrincipal, "Compra realizada com sucesso!");
                            break;
                        }else{
                            JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui credito suficiente!");
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar realiza a compra!");
            }
        }
}
