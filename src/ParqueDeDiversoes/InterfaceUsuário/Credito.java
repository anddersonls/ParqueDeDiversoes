package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Credito extends TelaBase {
    public Credito(ParqueDiversoes parque){
        super(parque);
        telaCredito();
    }
    public void telaCredito() {
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoDepositar = new JButton("Depositar");
        botaoDepositar.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoDepositar);

        JLabel labelValor = new JLabel("Valor: ");
        JLabel labelSenha = new JLabel("Senha: ");
        JTextField textValor = new JTextField(20);
        JPasswordField textSenha = new JPasswordField(20);

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        botaoDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Cliente cliente : parque.getVisitantes()){
                    if(cliente.getCpf() == pegaCpf()) {
                        colocaCredito(cliente, textSenha.getText(), textValor.getText());
                        break;
                    }
                }

            }
        });

        painel.add(labelValor);
        painel.add(textValor);
        painel.add(labelSenha);
        painel.add(textSenha);
        painel.add(painelBotoes);
        painelPrincipal.add(painel);
        painelPrincipal.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    public void colocaCredito(Cliente cliente, String senhaDigitada, String valorDigitado){
        String senha = "";
        senha = cliente.getSenha();
        if(senhaDigitada.equals(senha)) {
            try {
                float valor = Float.parseFloat(valorDigitado);
                JOptionPane.showMessageDialog(painelPrincipal, "Crédito Depositado!", "Depósito de Crédito", JOptionPane.INFORMATION_MESSAGE);
                cliente.depositarCredito(valor);
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(painelPrincipal, "Falha no Deposito! Valor inválido digitado no campo valor.");
            }
        }else{
            JOptionPane.showMessageDialog(painelPrincipal, "Senha Inválida!", "Senha Incorreta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public long pegaCpf() {
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/ParqueDeDiversoes/Arquivos/acessoCliente.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String cpfArquivo = br.readLine();
            long cpf = Long.parseLong(cpfArquivo);
            return cpf;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha ao tentar colocar credito!");
        }

        return -1;
    }

}
