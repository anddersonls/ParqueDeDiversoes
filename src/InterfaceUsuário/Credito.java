package InterfaceUsuário;

import parque.ParqueDiversoes;
import parque.Visitante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Credito extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;
    private long cpf;

    public Credito(ParqueDiversoes parque){
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
        telaCredito();
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
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
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        botaoDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(Visitante visitante : parque.getVisitantes()){
                    if(visitante.getCpf() == cpf) {
                        colocaCredito(visitante, textSenha.getText(), textValor.getText());
                        System.out.println(visitante.getCredito());
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

    public void colocaCredito(Visitante cliente, String senhaDigitada, String valorDigitado){
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

}
