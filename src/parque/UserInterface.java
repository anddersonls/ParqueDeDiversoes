package parque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserInterface extends Interface {
	private JPanel contentPane;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;

    public UserInterface() {
        this.buttonSize = new Dimension(100, 25);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        contentPane = new JPanel();
        setContentPane(contentPane);
        telaLogin();
    }

    public void removeAllComponents() {
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
    }
    public void telaLogin() {
        setTitle("Cliente");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoCadastrar = new JButton("Cadastrar Cliente");

        JPanel painelCpf = new JPanel();
        JPanel painelSenha = new JPanel();
        JPanel painelBotoes = new JPanel();
        JLabel labelCpf = new JLabel("CPF:    ");
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

        painelCpf.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelSenha.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                dispose();
                interfaceInicial();
            }
        });

        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesCliente();
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCadastrarCliente();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(30));
        contentPane.add(painelCpf);
        contentPane.add(painelSenha);
        contentPane.add(painelBotoes);
        contentPane.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    public void telaCadastrarCliente(){
        JPanel painel = new JPanel(new GridLayout(7, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoCadastrar.setPreferredSize(buttonSize);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                dispose();
                telaLogin();
            }
        });

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                JOptionPane.showMessageDialog(contentPane, "Cadastro Realizado com Sucesso!", "Cadastro de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                telaLogin();
            }
        });

        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelCpf = new JLabel("CPF: ");
        JLabel labelAltura = new JLabel("Altura: ");
        JLabel labelSenha = new JLabel("Senha: ");

        JTextField textNome = new JTextField(20);
        JTextField textCpf = new JTextField(20);
        JTextField textAltura = new JTextField(20);
        JTextField textSenha = new JTextField(20);

        painel.add(labelNome);
        painel.add(textNome);
        painel.add(labelCpf);
        painel.add(textCpf);
        painel.add(labelAltura);
        painel.add(textAltura);
        painel.add(labelSenha);
        painel.add(textSenha);

        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);
        contentPane.add(painel);
        setVisible(true);
    }

    public void opcoesCliente() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 20, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        JButton botaoBrinquedo = new JButton("Comprar Ingresso para Brinquedos");
        JButton botaoEstabelecimento = new JButton("Comprar Comida em Estabelecimentos");
        JButton botaoCredito = new JButton("Colocar Crédito");
        JButton botaoVoltar = new JButton("Voltar");

        botaoBrinquedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                JOptionPane.showMessageDialog(contentPane, "Cadastro Realizado com Sucesso!", "Cadastro de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                //opcoesDeCadastro();
            }
        });
        botaoEstabelecimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Item Adicionado ao Menu!", "Cadastro de Item do Menu", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaLogin();
                //opcoesDeCadastro();
            }
        });

        panel.add(new JLabel());
        panel.add(botaoBrinquedo);
        panel.add(botaoEstabelecimento);
        panel.add(botaoCredito);
        panel.add(new JLabel());
        panel.add(botaoVoltar);

        contentPane.add(panel);


        //pack();
        setVisible(true);

    }
}
