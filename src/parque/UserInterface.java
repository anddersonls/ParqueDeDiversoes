package parque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UserInterface extends Interface {
	private JPanel contentPane;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;

    public UserInterface(ParqueDiversoes parque) {
        this.parque = parque;
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
                String cpfDigitado = textCpf.getText();
                String senhaDigitada = textSenha.getText();

                if (verificarLogin(cpfDigitado, senhaDigitada)) {
                    JOptionPane.showMessageDialog(UserInterface.this, "Login bem-sucedido!");
                    removeAllComponents();
                    opcoesCliente();
                } else {
                    JOptionPane.showMessageDialog(UserInterface.this, "CPF ou senha incorretos!");
                    removeAllComponents();
                    telaLogin();
                }
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCadastrarCliente();
            }
        });

        painel.add(Box.createVerticalStrut(30));
        painel.add(painelCpf);
        painel.add(painelSenha);
        painel.add(painelBotoes);
        contentPane.add(painel);
        painel.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        long cpf = Long.parseLong(cpfDigitado);
        for (Visitante visitante : parque.getVisitantes()) {
            if (visitante.getCpf() == cpf && visitante.getSenha().equals(senhaDigitada)) {
                return true;
            }
        }
        return false;
    }

    public void telaCadastrarCliente(){
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelCpf = new JLabel("CPF: ");
        JLabel labelAltura = new JLabel("Altura: ");
        JLabel labelIdade = new JLabel("Idade: ");
        JLabel labelSenha = new JLabel("Senha: ");

        JTextField textNome = new JTextField(20);
        JTextField textCpf = new JTextField(20);
        JTextField textAltura = new JTextField(20);
        JTextField textIdade = new JTextField(20);
        JTextField textSenha = new JTextField(20);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoCadastrar.setPreferredSize(buttonSize);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaLogin();
            }
        });

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText();
                String cpf = textCpf.getText();
                String altura = textAltura.getText();
                String idade = textIdade.getText();
                String senha = textSenha.getText();
                if (nome.trim().isEmpty() || cpf.trim().isEmpty() || altura.trim().isEmpty() || idade.trim().isEmpty() || senha.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Digite um valor válido.");
                    removeAllComponents();
                    telaCadastrarCliente();
                } else {
                    if (verificarCadastro(nome, cpf, altura, idade, senha)) {
                        JOptionPane.showMessageDialog(UserInterface.this, "Cadastro bem-sucedido!");
                        removeAllComponents();
                        telaLogin();
                    } else {
                        //JOptionPane.showMessageDialog(UserInterface.this, "Falha no Cadastro");
                        removeAllComponents();
                        telaCadastrarCliente();
                    }
                }

            }
        });

        painel.add(labelNome);
        painel.add(textNome);
        painel.add(labelCpf);
        painel.add(textCpf);
        painel.add(labelAltura);
        painel.add(textAltura);
        painel.add(labelIdade);
        painel.add(textIdade);
        painel.add(labelSenha);
        painel.add(textSenha);

        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);
        contentPane.add(painel);
        setVisible(true);
    }

    public boolean verificarCadastro(String nome, String cpfDigitado, String alturaDigitada, String idadeDigitada, String senha){

        try{
            long cpf = Long.parseLong(cpfDigitado);
            float altura = Float.parseFloat(alturaDigitada);
            int idade = Integer.parseInt((idadeDigitada));

            Visitante novoVisitante = new Visitante(nome, cpf, idade, altura, senha, 0.0F);
            parque.addVisitante(novoVisitante);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(UserInterface.this, "Falha no Cadastro! Valor não numérico digitado em CPF, Altura ou Idade" + ex.getMessage());
        }

        return false;
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
                telaEscolherBrinquedo();
            }
        });
        botaoEstabelecimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaEscolherEstabelecimento();
            }
        });
        botaoCredito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCredito();
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaLogin();
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
        JTextField textSenha = new JTextField(20);

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesCliente();
            }
        });

        botaoDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Crédito Depositado!", "Depósito de Crédito", JOptionPane.INFORMATION_MESSAGE);
                removeAllComponents();
                opcoesCliente();
            }
        });


        painel.add(labelValor);
        painel.add(textValor);
        painel.add(labelSenha);
        painel.add(textSenha);
        painel.add(painelBotoes);
        contentPane.add(painel);
        contentPane.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    public void telaEscolherBrinquedo() {
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoFimCompra = new JButton("Comprar");
        botaoFimCompra.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoFimCompra);

        JLabel label = new JLabel("Selecione os brinquedos que você deseja:");
        JCheckBox checkBox1 = new JCheckBox("Item 1");
        JCheckBox checkBox2 = new JCheckBox("Item 2");
        JCheckBox checkBox3 = new JCheckBox("Item 3");

        double valorTotal=0.00;
        String mensagem = "Compra finalizada!\n";
        mensagem += "Valor total da compra: R$ " + valorTotal;

        String finalMensagem = mensagem;
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesCliente();
            }
        });

        botaoFimCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, finalMensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                removeAllComponents();
                opcoesCliente();
            }
        });


        painel.add(label);
        painel.add(checkBox1);
        painel.add(checkBox2);
        painel.add(checkBox3);

        painel.add(painelBotoes);
        contentPane.add(painel);
        contentPane.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    //Tela para escolher lanchonete
    public void telaEscolherEstabelecimento() {

        JLabel texto = new JLabel("Selecione um estabelecimento");
        texto.setFont(fontTitle);

        JButton botaoEscolher = new JButton("Escolher");
        JButton botaoVoltar= new JButton("Voltar");
        botaoEscolher.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painelTexto = new JPanel();
        JPanel painelEscolher = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelTexto.add(texto);
        painelEscolher.add(botaoEscolher);
        painelVoltar.add(botaoVoltar);

        painelTexto.setLayout(new FlowLayout(FlowLayout.CENTER,5,25));
        painelEscolher.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JList<String> listaEstabelecimentos = new JList<>(items);   //Aqui o parametro que o jList receberia seria o hashMap de estabelecimentos
        listaEstabelecimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuração do modo de seleção
        listaEstabelecimentos.setCellRenderer(new AdmInterface.CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaEstabelecimentos);



        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesCliente();
            }
        });

        botaoEscolher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Estabelecimento escohido!", " Escolha Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                removeAllComponents();
                telaComprarComida();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(painelTexto);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(scrollPane);
        contentPane.add(painelEscolher);
        contentPane.add(painelVoltar);

        setVisible(true);
    }

    //Escolher comida
    public void telaComprarComida() {
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoFimCompra = new JButton("Comprar");
        botaoFimCompra.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoFimCompra);

        JLabel label = new JLabel("Selecione os Estabelecimento que você deseja:");
        JCheckBox checkBox1 = new JCheckBox("Comida 1");
        JCheckBox checkBox2 = new JCheckBox("Comida 2");
        JCheckBox checkBox3 = new JCheckBox("Comida 3");
        double valorTotal=0.00;
        String mensagem = "Compra finalizada!\n\n";
        mensagem += "Valor total da compra: R$ " + valorTotal;

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaEscolherEstabelecimento();
            }
        });

        String finalMensagem = mensagem;
        botaoFimCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, finalMensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                removeAllComponents();
                opcoesCliente();
            }
        });


        painel.add(label);
        painel.add(checkBox1);
        painel.add(checkBox2);
        painel.add(checkBox3);

        painel.add(painelBotoes);
        contentPane.add(painel);
        contentPane.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

}
