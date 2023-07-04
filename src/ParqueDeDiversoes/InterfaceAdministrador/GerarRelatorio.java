package ParqueDeDiversoes.InterfaceAdministrador;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.Atracoes;
import ParqueDeDiversoes.parque.Brinquedos;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Visitante;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GerarRelatorio extends TelaBase {

    public GerarRelatorio(ParqueDiversoes parque) {
        super(parque);
        telaGeraRecibo();
    }

    public void telaGeraRecibo() {
        ArrayList<Visitante> clientes = parque.getVisitantes();

        // Define o caminho do arquivo de saída
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/ParqueDeDiversoes/Arquivos/recibo.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));
            for(Visitante cliente: clientes) {
                HashMap<Atracoes, Float> historico = cliente.getHistorico();

                writer.write("Cliente: " + cliente.getNome());
                writer.newLine();
                writer.write("CPF: " + cliente.getCpf());
                writer.newLine();
                writer.write("Atrações Visitadas e Valor Gasto:");
                writer.newLine();
                for (Map.Entry<Atracoes, Float> entry : historico.entrySet()) {
                    String tipoAtracao = "";
                    if (entry.getKey() instanceof Brinquedos) {
                        tipoAtracao = "Brinquedo";
                    } else {
                        tipoAtracao = "Estabelecimento";
                    }
                    String nome = entry.getKey().getNome();
                    float valor = entry.getValue();
                    String linha = tipoAtracao + ": " +nome + " - " + valor;
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