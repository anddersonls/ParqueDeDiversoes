package ParqueDeDiversoes.InterfaceAdministrador;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.Atracoes;
import ParqueDeDiversoes.parque.Brinquedos;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Cliente;

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
        gerarRelatorio();
    }

    public void gerarRelatorio() {
        ArrayList<Cliente> clientes = parque.getVisitantes();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(acessoRelatorio));
            for(Cliente cliente: clientes) {
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