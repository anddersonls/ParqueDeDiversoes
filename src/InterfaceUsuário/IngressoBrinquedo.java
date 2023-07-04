package InterfaceUsuário;

import parque.Atracoes;
import parque.Brinquedos;
import parque.ParqueDiversoes;
import parque.Visitante;

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
import java.util.List;
import java.util.Map;

public class IngressoBrinquedo extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;

    public IngressoBrinquedo(ParqueDiversoes parque) {
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

        HashMap<Brinquedos, Float> brinquedos = parque.getBrinquedos();
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
                ArrayList<Brinquedos> brinquedosSelecionados = new ArrayList<>();

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        String texto = checkBox.getText();
                        String[] partes = texto.split("      - Valor: ");
                        System.out.println(partes[0] + "a");
                        System.out.println(partes[1]);
                        String nome = partes[0];
                        float valor = Float.parseFloat(partes[1]);

                        valorTotal += valor;

                        // Recupera o brinquedo associado ao checkbox selecionado
                        for (Map.Entry<Brinquedos, Float> item : brinquedos.entrySet()) {
                            Brinquedos objeto = item.getKey();
                            if (objeto.getNome().equals(nome)) {
                                brinquedosSelecionados.add(objeto);
                            }
                        }
                        System.out.println(brinquedosSelecionados);
                    }
                    }
                    if(descontaValor(valorTotal, brinquedosSelecionados)) {
                        setVisible(false);
                        OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
                }
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

    public boolean descontaValor(float valor, ArrayList<Brinquedos> brinquedosSelecionados){
            String caminhoArquivo = "/home/joaovictor/Área de Trabalho/UFMA/3º Periodo/Linguagem de Programacao 2/LP2- TrabalhoFinal/Projeto/src/Arquivos/acessoCliente.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String cpfArquivo = br.readLine();
                long cpf = Long.parseLong(cpfArquivo);
                ArrayList<Visitante> clientes = parque.getVisitantes();
                for(Visitante visitante: clientes){
                    if(cpf == visitante.getCpf()){
                        if(visitante.descontarCredito(valor)){
                            for(Brinquedos brinquedo : brinquedosSelecionados){
                                visitante.addNoHistorico(brinquedo, valor);
                            }
                                String mensagem = "Compra finalizada!\n";
                                mensagem += "Valor total da compra: R$ " + valor;
                                JOptionPane.showMessageDialog(painelPrincipal, mensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui credito suficiente! Voce tem R$ "+visitante.getCredito());
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar realizar a compra!");
            }
            return false;
    }
}
