package InterfaceUsuário;

import parque.Alimentacao;
import parque.Brinquedos;
import parque.ParqueDiversoes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton botaoEscolher = new JButton("Comprar");
        JButton botaoSelecionar = new JButton("Selecionar");
        botaoEscolher.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);
        botaoSelecionar.setPreferredSize(buttonSize);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoEscolher);
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
        /*
        JLabel texto = new JLabel("Selecione um estabelecimento");
        texto.setFont(fontTitle);

        JButton botaoEscolher = new JButton("Escolher");
        JButton botaoVoltar = new JButton("Voltar");
        botaoEscolher.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painelTexto = new JPanel();
        JPanel painelEscolher = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelTexto.add(texto);
        painelEscolher.add(botaoEscolher);
        painelVoltar.add(botaoVoltar);

        painelTexto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        painelEscolher.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        DefaultListModel<Alimentacao> listModel = new DefaultListModel<>();

        for (Alimentacao estabelecimento : parque.getEstabelecimentos()) {
            listModel.addElement(estabelecimento);
        }

        JList<Alimentacao> listaEstabelecimentos = new JList<>(listModel);
        listaEstabelecimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaEstabelecimentos.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Alimentacao) {
                    Alimentacao estabelecimento = (Alimentacao) value;
                    setText(estabelecimento.getNome());
                }
                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(listaEstabelecimentos);

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });


        botaoEscolher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Alimentacao estabelecimentoSelecionado = listaEstabelecimentos.getSelectedValue();
                if (estabelecimentoSelecionado != null) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Estabelecimento escolhido!", "Escolha Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                    removeAllComponents();
                    telaComprarComida(estabelecimentoSelecionado);
                } else {
                    JOptionPane.showMessageDialog(painelPrincipal, "Selecione um estabelecimento!", "Escolha Estabelecimento", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.add(painelTexto);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(scrollPane);
        painelPrincipal.add(painelEscolher);
        painelPrincipal.add(painelVoltar);

        setVisible(true); */
    }
    //Escolher comida
    public void telaComprarComida(Alimentacao estabelecimentoSelecionado) {
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoFimCompra = new JButton("Comprar");
        botaoFimCompra.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoFimCompra);

        JLabel label = new JLabel("Selecione os Estabelecimento que você deseja:");
        JCheckBox checkBox1 = new JCheckBox("Comida 1");
        JCheckBox checkBox2 = new JCheckBox("Comida 2");
        JCheckBox checkBox3 = new JCheckBox("Comida 3");
        double valorTotal=0.00;
        String mensagem = "Compra finalizada!\n\n";
        mensagem += "Valor total da compra: R$ " + valorTotal;

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaEscolherEstabelecimento();
            }
        });

        String finalMensagem = mensagem;
        botaoFimCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(painelPrincipal, finalMensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        painel.add(label);
        painel.add(checkBox1);
        painel.add(checkBox2);
        painel.add(checkBox3);

        painel.add(painelBotoes);
        painelPrincipal.add(painel);
        painelPrincipal.add(Box.createVerticalStrut(15));
        setVisible(true);
    }
    public static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
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
        JButton botaoEscolher = new JButton("Comprar");
        botaoEscolher.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoEscolher);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaEscolherEstabelecimento();
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
}
