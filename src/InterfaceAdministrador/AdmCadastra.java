package InterfaceAdministrador;

import parque.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AdmCadastra extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;

    public AdmCadastra(ParqueDiversoes parque) {
        this.parque = parque;
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
    }
    public void telaCadastrarBrinquedo() {
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoCadastrar.setPreferredSize(buttonSize);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
            }
        });

        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelCapacidade = new JLabel("Capacidade: ");
        JLabel labelAlturaMin = new JLabel("Altura Mínima: ");
        JLabel labelIdadeMin = new JLabel("Idade Mínima: ");
        JLabel labelValor = new JLabel("Valor: ");

        JTextField textNome = new JTextField(20);
        JTextField textCapacidade = new JTextField(20);
        JTextField textAlturaMin = new JTextField(20);
        JTextField textIdadeMin = new JTextField(20);
        JTextField textValor = new JTextField(20);

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nome = textNome.getText();
                String capacidade = textCapacidade.getText();
                String alturaMin = textAlturaMin.getText();
                String idadeMin = textIdadeMin.getText();
                String valor = textValor.getText();

                if (nome.trim().isEmpty() || capacidade.trim().isEmpty() || alturaMin.trim().isEmpty() || idadeMin.trim().isEmpty() || valor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Digite um valor válido.");
                } else {
                    if (verificarCadastro(nome, capacidade, alturaMin, idadeMin, valor)) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Cadastro bem-sucedido!");
                        setVisible(false);
                        OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
                    } else {
                        JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro");
                    }
                }
            }
        });

        painel.add(labelNome);
        painel.add(textNome);
        painel.add(labelCapacidade);
        painel.add(textCapacidade);
        painel.add(labelAlturaMin);
        painel.add(textAlturaMin);
        painel.add(labelIdadeMin);
        painel.add(textIdadeMin);
        painel.add(labelValor);
        painel.add(textValor);


        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);
        painelPrincipal.add(painel);
        setVisible(true);
    }

    public boolean verificarCadastro(String nome, String capacidadeDigitada, String alturaMinDigitada, String idadeMinDigitada, String valorDigitado){

        try{
            int capacidade = Integer.parseInt((capacidadeDigitada));
            float altura = Float.parseFloat(alturaMinDigitada);
            int idade = Integer.parseInt((idadeMinDigitada));
            float valor = Float.parseFloat(valorDigitado);

            Brinquedos novoBrinquedo = new Brinquedos(capacidade, altura, idade, nome);
            parque.addBrinquedo(novoBrinquedo, valor);

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro! Valor não numérico digitado em Capacidade!");
        }

        return false;
    }

    public void telaCadastrarEstabelecimento() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        HashMap<String, Float> cardapio = new HashMap<>();

        JLabel labelNome = new JLabel("Nome:");
        JLabel labelCapacidade = new JLabel("Capacidade:");
        JLabel labelMenu = new JLabel("Menu:");
        JLabel labelAlimento = new JLabel("Alimento:");
        JLabel labelValor = new JLabel("Valor:");

        JTextField campoNome = new JTextField(20);
        JTextField campoCapacidade = new JTextField(20);
        JTextField campoAlimento = new JTextField(20);
        JTextField campoValor = new JTextField(20);


        JLabel labelTipo = new JLabel("Tipo:");
        String[] opcoesEstabelecimento = {"Restaurante", "Quiosque", "Lanchonete"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(opcoesEstabelecimento);

        JButton botaoSalvar = new JButton("Salvar no Menu");
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");


        botaoSalvar.addActionListener(new ActionListener() {
            //Botar os alimentos no hashmap cardapio
            public void actionPerformed(ActionEvent e) {
                String alimento = campoAlimento.getText();
                String valor = campoValor.getText();

                if (alimento.trim().isEmpty() || valor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Digite um valor válido.");
                } else {
                    if (verificarCadastroCardapio(alimento,valor,cardapio)) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Cadastro de Alimento Realizado com Sucesso!", "Cadastro de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                        campoAlimento.setText("");
                        campoValor.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro");
                    }
                }
            }
        });


        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Cadastrar um novo estabelecimento e depois disso limpar o hashmap de cardapio

                String nome = campoNome.getText();
                String capacidade = campoCapacidade.getText();
                String tipoEstabelecimento = (String) comboBoxTipo.getSelectedItem();

                if (nome.trim().isEmpty() || capacidade.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Digite um valor válido.");
                } else {
                    if (verificarCadastroEstabelecimento(nome,capacidade,tipoEstabelecimento,cardapio)) {
                        //cardapio.clear();

                        JOptionPane.showMessageDialog(painelPrincipal, "Cadastro Realizado com Sucesso!", "Cadastro de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
                    }
                    else {
                        JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro");
                    }
                }
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
            }
        });

        panel.add(labelNome);
        panel.add(campoNome);
        panel.add(labelCapacidade);
        panel.add(campoCapacidade);
        panel.add(labelTipo);
        panel.add(comboBoxTipo);
        panel.add(labelMenu);
        panel.add(new JLabel());
        panel.add(labelAlimento);
        panel.add(campoAlimento);
        panel.add(labelValor);
        panel.add(campoValor);
        panel.add(new JLabel());
        panel.add(botaoSalvar);
        panel.add(botaoVoltar);
        panel.add(botaoCadastrar);
        painelPrincipal.add(panel);


        //pack();
        setVisible(true);
    }


    private boolean verificarCadastroEstabelecimento(String nome, String capacidadeDigitada, String tipoEstabelecimento,HashMap<String,Float> cardapio){

        try{
            int capacidade = Integer.parseInt((capacidadeDigitada));


            if (tipoEstabelecimento.equals("Restaurante")) {
                Restaurante novoEstabelecimento = new Restaurante(capacidade,cardapio,nome);
                parque.addEstabelecimento(novoEstabelecimento);
            }if (tipoEstabelecimento.equals("Quiosque")) {
                Quiosque novoEstabelecimento = new Quiosque(capacidade,cardapio,nome);
                parque.addEstabelecimento(novoEstabelecimento);
            }if (tipoEstabelecimento.equals("Lanchonete")) {
                Lanchonete novoEstabelecimento = new Lanchonete(capacidade,cardapio,nome);
                parque.addEstabelecimento(novoEstabelecimento);
            }

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro! Valor não numérico digitado em Capacidade");
        }
        return false;
    }

    private boolean verificarCadastroCardapio(String alimento, String preco, HashMap<String,Float> cardapio){

        try{
            float valor = Float.parseFloat((preco));
            cardapio.put(alimento,valor);

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro! Valor não numérico digitado em Valor!");
        }
        return false;
    }

}
