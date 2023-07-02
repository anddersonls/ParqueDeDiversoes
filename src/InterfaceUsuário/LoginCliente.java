package InterfaceUsuário;

import parque.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginCliente extends JFrame{
	private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private long cpf;

    public LoginCliente(ParqueDiversoes parque) {
        this.parque = parque;
        this.buttonSize = new Dimension(100, 25);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Cliente");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);

        setLocationRelativeTo(null);
        telaLogin();
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
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
        JTextField textSenha = new JTextField(20);

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
                    opcoesCliente.opcoes();
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
        painel.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        try{
            long CPF = Long.parseLong(cpfDigitado);
            if(parque.getVisitantes()!=null) {
                for (Visitante visitante : parque.getVisitantes()) {
                    if (visitante.getCpf() == CPF && visitante.getSenha().equals(senhaDigitada)) {
                        this.cpf = CPF;
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
}
