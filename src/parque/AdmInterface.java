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

    public void telaLogin(){
        setTitle("Administrador");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");

        JPanel painelNome = new JPanel();
        JPanel painelSenha = new JPanel();
        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelSenha = new JLabel("Senha: ");
        JTextField textNome = new JTextField(20);
        JTextField textSenha = new JTextField(20);

        painelNome.add(labelNome);
        painelNome.add(textNome);
        painelSenha.add(labelSenha);
        painelSenha.add(textSenha);

        painelNome.setLayout(new FlowLayout());
        painelSenha.setLayout(new FlowLayout());

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
                dispose();
                interfaceInicial();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(0));
        contentPane.add(painelNome);
        contentPane.add(Box.createVerticalStrut(0));
        contentPane.add(painelSenha);
        contentPane.add(Box.createVerticalStrut(0));
        contentPane.add(botaoVoltar);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(botaoCadastrar);
        //pack();
        setVisible(true);
    }
}
