package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.InterfaceInicial;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class LoginCliente extends TelaBase {
    public LoginCliente(ParqueDiversoes parque) {
        super(parque);
        telaLogin();
    }
    public void telaLogin() {
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoEntrar.setPreferredSize(buttonSize);
        botaoCadastrar.setPreferredSize(buttonSize);

        JPanel painelBotoes = new JPanel();
        JPanel painelCpf = new JPanel();
        JPanel painelSenha = new JPanel();
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelCpf = new JLabel("CPF:     ");
        JLabel labelSenha = new JLabel("Senha: ");
        JTextField textCpf = new JTextField(20);
        JPasswordField textSenha = new JPasswordField(20);

        painelCpf.add(labelCpf);
        painelCpf.add(textCpf);
        painelSenha.add(labelSenha);
        painelSenha.add(textSenha);
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoCadastrar);

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                InterfaceInicial interfaceInicial = new InterfaceInicial(parque);
            }
        });

        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpfDigitado = textCpf.getText();
                String senhaDigitada = textSenha.getText();

                if (verificarLogin(cpfDigitado, senhaDigitada)) {
                    JOptionPane.showMessageDialog(LoginCliente.this, "Login bem-sucedido!");
                    setVisible(false);
                    OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
                }
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Cadastro cadastro = new Cadastro(parque);
            }
        });

        painel.add(Box.createVerticalStrut(30));
        painel.add(painelCpf);
        painel.add(painelSenha);
        painel.add(painelBotoes);
        painelPrincipal.add(painel);
        setVisible(true);
    }

    private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        try{
            long CPF = Long.parseLong(cpfDigitado);
            if(parque.getVisitantes()!=null) {
                for (Cliente cliente : parque.getVisitantes()) {
                    if (cliente.getCpf() == CPF && cliente.getSenha().equals(senhaDigitada)) {
                        salvaCPF(cpfDigitado);
                        return true;
                    }
                }
                JOptionPane.showMessageDialog(LoginCliente.this, "CPF ou senha incorreto(s)!");
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(LoginCliente.this, "Valor de CPF inválido!");
        }

        return false;
    }

    public void salvaCPF(String CPF){
        String nomeArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/ParqueDeDiversoes/Arquivos/acessoCliente.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(CPF);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
