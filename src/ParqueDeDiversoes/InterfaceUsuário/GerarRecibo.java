package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.Atracoes;
import ParqueDeDiversoes.parque.Brinquedos;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Visitante;

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
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/ParqueDeDiversoes/Arquivos/acessoCliente.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            JTable tabela = new JTable();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Tipo Atração");
            tableModel.addColumn("Nome");
            tableModel.addColumn("Valor Gasto");
            String cpfArquivo = br.readLine();
            long cpf = Long.parseLong(cpfArquivo);
            ArrayList<Visitante> clientes = parque.getVisitantes();
            for(Visitante visitante: clientes){
                if(cpf == visitante.getCpf()) {
                    HashMap<Atracoes, Float> historico = visitante.getHistorico();
                    if (historico.isEmpty()) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Voce ainda nao visitou as atracoes do parque!");
                        OpcoesCliente opcao = new OpcoesCliente(parque);
                    } else {
                        for (Map.Entry<Atracoes, Float> entry : historico.entrySet()) {
                            if (entry.getKey() instanceof Brinquedos) {
                                tableModel.addRow(new Object[]{"Brinquedo", entry.getKey().getNome(), entry.getValue()});
                            } else {
                                tableModel.addRow(new Object[]{"Estabelecimento", entry.getKey().getNome(), entry.getValue()});
                            }
                        }
                        JButton botaoVoltar = new JButton("Voltar");
                        JPanel painelBotao = new JPanel();
                        painelBotao.add(botaoVoltar);
                        botaoVoltar.setPreferredSize(buttonSize);
                        botaoVoltar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                setVisible(false);
                                OpcoesCliente opcao = new OpcoesCliente(parque);
                            }
                        });
                        tabela.setModel(tableModel);
                        painelPrincipal.add(Box.createVerticalStrut(15));
                        painelPrincipal.add(new JScrollPane(tabela), BorderLayout.CENTER);
                        painelPrincipal.add(Box.createVerticalStrut(15));
                        painelPrincipal.add(painelBotao);
                        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
                        JPanel wrapperPanel = new JPanel(new BorderLayout());
                        wrapperPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
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