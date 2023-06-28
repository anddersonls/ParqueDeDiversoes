package parque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdmInterface extends Interface{
   private JPanel contentPane;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;

    public AdmInterface(){
        this.buttonSize = new Dimension(400, 50);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        contentPane = new JPanel();
        setContentPane(contentPane);
        telaLogin();
    }
@Override
    public void removeAllComponents(){
    contentPane.removeAll();
    contentPane.revalidate();
    contentPane.repaint();
}

    public void telaLogin(){
        setTitle("Administrador");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");

        JPanel painelCpf = new JPanel();
        JPanel painelSenha = new JPanel();
        JPanel painelBotoes = new JPanel();
        JLabel labelNome = new JLabel("CPF: ");
        JLabel labelSenha = new JLabel("Senha: ");
        JTextField textNome = new JTextField(20);
        JTextField textSenha = new JTextField(20);

        painelCpf.add(labelNome);
        painelCpf.add(textNome);
        painelSenha.add(labelSenha);
        painelSenha.add(textSenha);
        painelBotoes.add(botaoVoltar);
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

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                repaint();
                telaOpcoes();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(30));
        contentPane.add(painelCpf);
        contentPane.add(painelSenha);
        contentPane.add(painelBotoes);
        contentPane.add(Box.createVerticalStrut(15));
        //pack();
        setVisible(true);
    }

    public void telaOpcoes(){
        setTitle("Administrador");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botaoAdicionar = new JButton("Adicionar Atração");
        JButton botaoRemover = new JButton("Remover Atração");
        JButton botaoVoltar = new JButton("Voltar");

        JPanel painelBotoes = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelVoltar.add(botaoVoltar);

        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                //dispose();
                telaLogin();
            }
        });

        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //removeAllComponents();
                //dispose();
                //interfaceInicial();
            }
        });
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //removeAllComponents();
                //dispose();
                //interfaceInicial();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(50));
        contentPane.add(painelBotoes);
        contentPane.add(painelVoltar);
        //pack();
        setVisible(true);
    }
}
