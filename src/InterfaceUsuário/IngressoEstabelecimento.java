package InterfaceUsuário;

import parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.buttonSize = new Dimension(400, 50);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Minha Interface Gráfica");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        setLocationRelativeTo(null);
        telaEscolherEstabelecimento();
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
    public void telaEscolherEstabelecimento() {

        JLabel texto = new JLabel("Selecione um estabelecimento");
        texto.setFont(fontTitle);

        JButton botaoEscolher = new JButton("Escolher");
        JButton botaoVoltar= new JButton("Voltar");
        botaoEscolher.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painelTexto = new JPanel();
        JPanel painelEscolher = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelTexto.add(texto);
        painelEscolher.add(botaoEscolher);
        painelVoltar.add(botaoVoltar);

        painelTexto.setLayout(new FlowLayout(FlowLayout.CENTER,5,25));
        painelEscolher.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JList<String> listaEstabelecimentos = new JList<>(items);   //Aqui o parametro que o jList receberia seria o hashMap de estabelecimentos
        listaEstabelecimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuração do modo de seleção
        //listaEstabelecimentos.setCellRenderer(new LoginAdm.CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaEstabelecimentos);



        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        botaoEscolher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Estabelecimento escohido!", " Escolha Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                removeAllComponents();
                telaComprarComida();
            }
        });

        painelPrincipal.setLayout(new BoxLayout(painelPrincipal,BoxLayout.Y_AXIS));
        painelPrincipal.add(painelTexto);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(scrollPane);
        painelPrincipal.add(painelEscolher);
        painelPrincipal.add(painelVoltar);

        setVisible(true);
    }

    //Escolher comida
    public void telaComprarComida() {
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
}
