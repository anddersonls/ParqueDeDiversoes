package InterfaceAdministrador;

import parque.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoginAdm extends JFrame {
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;

    private HashMap<String, Float> cardapio;


    public LoginAdm(ParqueDiversoes parque) {
        this.parque = parque;
        cardapio = new HashMap<>();
        this.buttonSize = new Dimension(100, 25);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        setTitle("Administrador");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        telaLogin();
    }
    public void telaLogin() {
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoEntrar = new JButton("Entrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoEntrar.setPreferredSize(buttonSize);

        JPanel painelCpf = new JPanel();
        JPanel painelSenha = new JPanel();
        JPanel painelBotoes = new JPanel();
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelCpf = new JLabel("CPF:     ");
        JLabel labelSenha = new JLabel("Senha: ");
        JTextField textCpf = new JTextField(20);
        JTextField textSenha = new JTextField(20);

        painelCpf.add(labelCpf);
        painelCpf.add(textCpf);
        painelSenha.add(labelSenha);
        painelSenha.add(textSenha);
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoEntrar);

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
                    JOptionPane.showMessageDialog(LoginAdm.this, "Login bem-sucedido!");
                    setVisible(false);
                    OpcoesAdm opcoes = new OpcoesAdm(parque);
                } else {
                    JOptionPane.showMessageDialog(LoginAdm.this, "CPF ou senha incorretos!");
                }
            }
        });

        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(painel);
        painelPrincipal.add(Box.createVerticalStrut(15));
        setVisible(true);
    }
    private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/Arquivos/acessoAdm.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String cpfArquivo = br.readLine();
            String senhaArquivo = br.readLine();

            return cpfArquivo.equals(cpfDigitado) && senhaArquivo.equals(senhaDigitada);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return false;
    }
}
