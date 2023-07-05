package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GerarRecibo extends TelaBase {
    public GerarRecibo(ParqueDiversoes parque) {
        super(parque);
        telaGeraRecibo();
    }

    public void telaGeraRecibo() {
        try (BufferedReader br = new BufferedReader(new FileReader(acessoCliente))) {
            JTable tabela = new JTable();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Tipo Estabelecimento");
            tableModel.addColumn("Nome");
            tableModel.addColumn("Valor Gasto");
            String cpfArquivo = br.readLine();
            long cpf = Long.parseLong(cpfArquivo);
            ArrayList<Cliente> clientes = parque.getVisitantes();
            for(Cliente cliente : clientes){
                if(cpf == cliente.getCpf()) {
                    HashMap<Atracoes, Float> historico = cliente.getHistorico();
                    if (historico.isEmpty()) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Voce ainda nao visitou as atracoes do parque!");
                        OpcoesCliente opcao = new OpcoesCliente(parque);
                    } else {
                        for (Map.Entry<Atracoes, Float> atracao : historico.entrySet()) {
                            if (atracao.getKey() instanceof Brinquedos) {
                                tableModel.addRow(new Object[]{"Brinquedo", atracao.getKey().getNome(), atracao.getValue()});
                            } else {
                                if(atracao.getKey() instanceof Quiosque) {
                                    tableModel.addRow(new Object[]{"Quiosque", atracao.getKey().getNome(),atracao.getValue()});
                                }else if(atracao.getKey() instanceof Restaurante) {
                                    tableModel.addRow(new Object[]{"Restaurante", atracao.getKey().getNome(), atracao.getValue()});
                                }else{
                                    tableModel.addRow(new Object[]{"Lanchonete", atracao.getKey().getNome(), atracao.getValue()});
                                }
                            }
                        }
                        JButton botaoVoltar = new JButton();
                        JPanel painelBotao = new JPanel();
                        painelBotao.add(botaoVoltar);
                        botaoVoltar.setPreferredSize(buttonSize);
                        botaoVoltar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                setVisible(false);
                                OpcoesCliente opcao = new OpcoesCliente(parque);
                            }
                        });
                        botaoVoltar.setIcon(voltarIconRed);
                        tabela.setModel(tableModel);
                        tabela.setFont(fontText);
                        tabela.getTableHeader().setFont(fontText);
                        painelPrincipal.add(Box.createVerticalStrut(15));
                        painelPrincipal.add(new JScrollPane(tabela), BorderLayout.CENTER);
                        painelPrincipal.add(Box.createVerticalStrut(15));
                        painelPrincipal.add(painelBotao);
                        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
                        JPanel wrapperPanel = new JPanel(new BorderLayout());
                        wrapperPanel.setBorder(new EmptyBorder(0, 30, 0, 30));
                        wrapperPanel.add(painelPrincipal, BorderLayout.CENTER);
                        setContentPane(wrapperPanel);
                        setVisible(true);
                    }
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar gerar o recibo!");
        }
    }
}