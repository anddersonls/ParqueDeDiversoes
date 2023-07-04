package InterfaceUsuário;

import parque.Atracoes;
import parque.ParqueDiversoes;
import parque.Visitante;

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

public class GerarRecibo extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    public GerarRecibo(ParqueDiversoes parque) {
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
        telaGeraRecibo();
    }

    public void telaGeraRecibo() {
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/Arquivos/acessoCliente.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            JTable tabela = new JTable();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Nome");
            tableModel.addColumn("Valor Gasto");
            String cpfArquivo = br.readLine();
            long cpf = Long.parseLong(cpfArquivo);
            ArrayList<Visitante> clientes = parque.getVisitantes();
            for(Visitante visitante: clientes){
                if(cpf == visitante.getCpf()){
                    HashMap<Atracoes, Float> historico = visitante.getHistorico();
                    for (Map.Entry<Atracoes, Float> entry : historico.entrySet()) {
                        tableModel.addRow(new Object[]{entry.getKey().getNome(), entry.getValue()});
                    }
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
        } catch (IOException e) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar gerar o recibo!");
        }
    }
}