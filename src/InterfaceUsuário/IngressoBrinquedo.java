package InterfaceUsuário;

import parque.Brinquedos;
import parque.ParqueDiversoes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IngressoBrinquedo extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private long cpf;

    public IngressoBrinquedo(ParqueDiversoes parque) {
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

        telaEscolherBrinquedo();
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
    public void telaEscolherBrinquedo() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        Map<Brinquedos, Float> brinquedos = parque.getBrinquedos();
        List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
        //JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        //JCheckBox checkBox = new JCheckBox();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Valor");
        tableModel.addColumn("Selecionado");


        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoFimCompra = new JButton("Comprar");
        botaoFimCompra.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoFimCompra);
        JLabel label = new JLabel("Selecione os brinquedos que você deseja:");
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(label);
        painelPrincipal.add(Box.createVerticalStrut(15));
        botaoFimCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float valorTotal = 0.0f;
                List<Brinquedos> brinquedosSelecionados = new ArrayList<>();

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        String texto = checkBox.getText();
                        String[] partes = texto.split(" - Valor: ");
                        String nome = partes[0];
                        float valor = Float.parseFloat(partes[1]);

                        valorTotal += valor;

                        // Recupera o brinquedo associado ao checkbox selecionado
                        Brinquedos brinquedo = findBrinquedoByNome(brinquedos, nome);
                        if (brinquedo != null) {
                            brinquedosSelecionados.add(brinquedo);
                        }
                    }
                }

                // Realize as ações necessárias com os brinquedos selecionados

                String mensagem = "Compra finalizada!\n";
                mensagem += "Valor total da compra: R$ " + valorTotal;
                JOptionPane.showMessageDialog(painelPrincipal, mensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);

                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        for (Brinquedos brinquedo : brinquedos.keySet()) {
            float value = brinquedos.get(brinquedo);
            JCheckBox checkBox = new JCheckBox(brinquedo.getNome() + "      - Valor: " + value);
            checkBoxes.add(checkBox);
            painelPrincipal.add(checkBox);
        }
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(painel);

        painelPrincipal.add(Box.createVerticalStrut(15));
        //painelPrincipal.add(new JScrollPane(table), BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        wrapperPanel.add(painelPrincipal, BorderLayout.CENTER);
        setContentPane(wrapperPanel);
        setVisible(true);
    }
    private Brinquedos findBrinquedoByNome(Map<Brinquedos, Float> brinquedos, String nome) {
        for (Brinquedos brinquedo : brinquedos.keySet()) {
            if (brinquedo.getNome().equals(nome)) {
                return brinquedo;
            }
        }
        return null;
    }
}
