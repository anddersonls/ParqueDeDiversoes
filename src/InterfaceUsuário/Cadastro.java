package InterfaceUsuário;

import parque.ParqueDiversoes;
import parque.Visitante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private long cpf;

    public Cadastro(ParqueDiversoes parque) {
        this.parque = parque;
        this.buttonSize = new Dimension(400, 50);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Minha Interface Gráfica");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal = new JPanel();
        setContentPane(painelPrincipal);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        setLocationRelativeTo(null);
        telaCadastrarCliente();
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
                setVisible(false);
                LoginCliente loginCliente = new LoginCliente(parque);
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
                    JOptionPane.showMessageDialog(painelPrincipal, "Digite um valor válido.");
                } else {
                    if (verificarCadastro(nome, cpf, altura, idade, senha)) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Cadastro bem-sucedido!");
                        setVisible(false);
                        LoginCliente loginCliente = new LoginCliente(parque);
                    } else {
                        JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro");
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
        painelPrincipal.add(painel);
        setVisible(true);
    }

    public boolean verificarCadastro(String nome, String cpfDigitado, String alturaDigitada, String idadeDigitada, String senha){

        try{
            long CPF = Long.parseLong(cpfDigitado);
            float altura = Float.parseFloat(alturaDigitada);
            int idade = Integer.parseInt((idadeDigitada));

            Visitante novoVisitante = new Visitante(nome, CPF, idade, altura, senha, 0.0F);
            parque.addVisitante(novoVisitante);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro! Valor não numérico digitado em CPF, Altura ou Idade");
        }

        return false;
    }

}
