package InterfaceUsuário;

import parque.Atracoes;
import parque.ParqueDiversoes;
import parque.Visitante;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        telaGeraRecibo();
    }

    public void telaGeraRecibo() {
        ArrayList<Visitante> clientes = parque.getVisitantes();

        // Define o caminho do arquivo de saída
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/Arquivos/recibo.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true));
            for(Visitante cliente: clientes) {
                HashMap<Atracoes, Float> historico = cliente.getHistorico();
                // Cria um BufferedWriter para escrever no arquivo

                // Percorre o HashMap e escreve os dados no arquivo
                for (Map.Entry<Atracoes, Float> entry : historico.entrySet()) {
                    String nome = entry.getKey().getNome();
                    float valor = entry.getValue();
                    String linha = nome + ": " + valor;
                    System.out.println(linha);
                    writer.write(linha);
                    writer.newLine();
                    writer.flush();
                }
                // Fecha o BufferedWriter
            }
                JOptionPane.showMessageDialog(painelPrincipal, "Relatório gerado!", "Recibo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Não foi possível gerar o recibo!", "Recibo", JOptionPane.INFORMATION_MESSAGE);
            }
    }
}