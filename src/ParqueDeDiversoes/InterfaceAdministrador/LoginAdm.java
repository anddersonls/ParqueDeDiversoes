package ParqueDeDiversoes.InterfaceAdministrador;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.InterfaceInicial;
import ParqueDeDiversoes.parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginAdm extends TelaBase {

    public LoginAdm(ParqueDiversoes parque) {
        super(parque);
        telaLogin();
    }
    public void telaLogin() {
        JButton botaoVoltar = new JButton();
        JButton botaoEntrar = new JButton();
        botaoVoltar.setPreferredSize(buttonSize);
        botaoEntrar.setPreferredSize(buttonSize);

        JPanel painelCpf = new JPanel();
        JPanel painelSenha = new JPanel();
        JPanel painelBotoes = new JPanel();
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelBotoes.setBackground(corDeFundo);
        painelCpf.setBackground(corDeFundo);
        painelSenha.setBackground(corDeFundo);
        painel.setBackground(corDeFundo);
        painelPrincipal.setBackground(corDeFundo);

        JLabel labelCpf = new JLabel("CPF:    ");
        JLabel labelSenha = new JLabel("Senha: ");
        JTextField textCpf = new JTextField(20);
        JPasswordField textSenha = new JPasswordField(20);
        textCpf.setFont(fontButton);
        textSenha.setFont(fontButton);

        painelCpf.add(labelCpf);
        painelCpf.add(textCpf);
        painelSenha.add(labelSenha);
        painelSenha.add(textSenha);
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoEntrar);

        botaoVoltar.setIcon(voltarIconRed);
        painel.add(new JLabel());
        painel.add(new JLabel());
        painel.add(painelCpf);
        painel.add(painelSenha);
        painel.add(painelBotoes);

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
                    JOptionPane.showMessageDialog(LoginAdm.this, "Login bem sucedido!");
                    setVisible(false);
                    OpcoesAdm opcoes = new OpcoesAdm(parque);
                } else {
                    textCpf.setText("");
                    textSenha.setText("");
                    JOptionPane.showMessageDialog(LoginAdm.this, "CPF ou senha incorretos!", "Erro no Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        botaoVoltar.setIcon(voltarIconRed);
        botaoEntrar.setIcon(loginIconRed);

        labelCpf.setFont(fontButton);
        labelSenha.setFont(fontButton);
        Dimension textFieldSize = textCpf.getPreferredSize();
        textFieldSize.height = 30;
        textCpf.setPreferredSize(textFieldSize);
        textFieldSize = textSenha.getPreferredSize();
        textFieldSize.height = 30;
        textSenha.setPreferredSize(textFieldSize);

        painelPrincipal.add(painel);
        setVisible(true);
    }
    private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        try (BufferedReader br = new BufferedReader(new FileReader(acessoAdm))) {
            String cpfArquivo = br.readLine();
            String senhaArquivo = br.readLine();

            return cpfArquivo.equals(cpfDigitado) && senhaArquivo.equals(senhaDigitada);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return false;
    }
}
