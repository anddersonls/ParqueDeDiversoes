package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.Brinquedos;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Cliente;

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

public class IngressoBrinquedo extends TelaBase {
    public IngressoBrinquedo(ParqueDiversoes parque) {
        super(parque);
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
                        String[] partes = texto.split(" - Valor: ");
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
                    }
                }
                if(verificaIdadeEAlturaa(brinquedosSelecionados)) {
                    if (descontaValor(valorTotal, brinquedosSelecionados)) {
                        setVisible(false);
                        OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
                    }
                }
            }
        });

        for (Brinquedos brinquedo : brinquedos.keySet()) {
            float value = brinquedos.get(brinquedo);
            JCheckBox checkBox = new JCheckBox(brinquedo.getNome() + " - Valor: " + value);
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

    public boolean descontaValor(float valor, ArrayList<Brinquedos> brinquedosSelecionados){
            String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/ParqueDeDiversoes/Arquivos/acessoCliente.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String cpfArquivo = br.readLine();
                long cpf = Long.parseLong(cpfArquivo);
                ArrayList<Cliente> clientes = parque.getVisitantes();
                for(Cliente cliente : clientes){
                    if(cpf == cliente.getCpf()){
                        if(cliente.descontarCredito(valor)){
                            for(Brinquedos brinquedo : brinquedosSelecionados){
                                cliente.addNoHistorico(brinquedo, valor);
                            }
                                String mensagem = "Compra finalizada!\n";
                                mensagem += "Valor total da compra: R$ " + valor;
                                JOptionPane.showMessageDialog(painelPrincipal, mensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui credito suficiente! Voce tem R$ "+ cliente.getCredito());
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar realizar a compra!");
            }
            return false;
    }

    public boolean verificaIdadeEAlturaa(ArrayList<Brinquedos> brinquedosSelecionados){
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/ParqueDeDiversoes/Arquivos/acessoCliente.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String cpfArquivo = br.readLine();
            long cpf = Long.parseLong(cpfArquivo);
            ArrayList<Cliente> clientes = parque.getVisitantes();
            for(Cliente cliente : clientes) {
                if (cpf == cliente.getCpf()) {
                    for (Brinquedos brinquedo : brinquedosSelecionados) {
                        if (brinquedo.getAlturaMin() > cliente.getAltura()) {
                            JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui a altura minima para ir nesse brinquedo!", "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        }
                        if (brinquedo.getIdadeMin() > cliente.getIdade()) {
                            JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui a idade minima para ir nesse brinquedo!", "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        }
                    }
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar realizar a compra!");
        }
        return false;
    }
}
