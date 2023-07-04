package InterfaceAdministrador;

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

public class GerarRelatorio extends JFrame{
    private ParqueDiversoes parque;
    private JPanel painelPrincipal;

    public GerarRelatorio(ParqueDiversoes parque) {
        this.parque = parque;
        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        telaGeraRecibo();
    }

    public void telaGeraRecibo() {
        ArrayList<Visitante> clientes = parque.getVisitantes();

        // Define o caminho do arquivo de saída
        String caminhoArquivo = "/home/joaovictor/Área de Trabalho/UFMA/3º Periodo/Linguagem de Programacao 2/LP2- TrabalhoFinal/Projeto/src/Arquivos/recibo.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));
            for(Visitante cliente: clientes) {
                HashMap<Atracoes, Float> historico = cliente.getHistorico();

                writer.write("Cliente: " + cliente.getNome());
                writer.newLine();
                writer.write("CPF: " + cliente.getCpf());
                writer.newLine();
                writer.write("Atrações Visitados e Valor Gasto:");
                writer.newLine();
                for (Map.Entry<Atracoes, Float> entry : historico.entrySet()) {
                    String nome = entry.getKey().getNome();
                    float valor = entry.getValue();
                    String linha = nome + ": " + valor;
                    System.out.println(linha);
                    writer.write(linha);
                    writer.newLine();

                }
                writer.newLine();
                writer.flush();
            }
                JOptionPane.showMessageDialog(painelPrincipal, "Relatório gerado!", "Recibo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(painelPrincipal, "Não foi possível gerar o recibo!", "Recibo", JOptionPane.INFORMATION_MESSAGE);
            }
    }
}