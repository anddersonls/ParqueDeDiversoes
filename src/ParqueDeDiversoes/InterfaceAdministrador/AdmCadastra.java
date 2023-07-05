package ParqueDeDiversoes.InterfaceAdministrador;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdmCadastra extends TelaBase {

    public AdmCadastra(ParqueDiversoes parque) {
        super(parque);
    }
    public void telaCadastrarBrinquedo() {
        JPanel painel = new JPanel(new GridLayout(7, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botaoVoltar = new JButton();
        JButton botaoCadastrar = new JButton();
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
        JLabel labelRestricao = new JLabel("Restrição: ");
        JLabel labelValor = new JLabel("Valor: ");

        JTextField textNome = new JTextField(20);
        JTextField textCapacidade = new JTextField(20);
        JTextField textAlturaMin = new JTextField(20);
        JTextField textIdadeMin = new JTextField(20);
        JTextField textValor = new JTextField(20);

         JComboBox<String> opcoesRestricoes= opcoesDeRestricao();
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nome = textNome.getText();
                String capacidade = textCapacidade.getText();
                String alturaMin = textAlturaMin.getText();
                String idadeMin = textIdadeMin.getText();
                String valor = textValor.getText();
                String restricao = (String) opcoesRestricoes.getSelectedItem();

                if (nome.trim().isEmpty() || capacidade.trim().isEmpty() || alturaMin.trim().isEmpty() || idadeMin.trim().isEmpty() || valor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Digite um valor válido.");
                } else {
                    if(verificaNome(nome)) {
                        if (verificarCadastro(nome, capacidade, alturaMin, idadeMin, valor, restricao)) {
                            JOptionPane.showMessageDialog(painelPrincipal, "Cadastro bem-sucedido!");
                            setVisible(false);
                            OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
                        } else {
                            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro");
                        }
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
        painel.add(labelRestricao);
        painel.add(opcoesRestricoes);
        painel.add(labelValor);
        painel.add(textValor);

        botaoVoltar.setIcon(voltarIconRed);
        botaoCadastrar.setIcon(adicionarIconRed);
        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);
        painelPrincipal.add(painel);
        setVisible(true);
    }
    public JComboBox<String> opcoesDeRestricao(){
        JComboBox<String> opcoesRestricao = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        comboBoxModel.addElement("Sem restrição");
        for (Map.Entry<Brinquedos, Float> item : parque.getBrinquedos().entrySet()) {
            Brinquedos brinquedo = item.getKey();
            String nome = brinquedo.getNome();
            comboBoxModel.addElement(nome);
        }
        for(Estabelecimento estabelecimento : parque.getEstabelecimentos()){
            String nome = estabelecimento.getNome();
            comboBoxModel.addElement(nome);
        }
        opcoesRestricao.setModel(comboBoxModel);

        return opcoesRestricao;
    }
    public boolean verificaNome(String nome){
        for(Estabelecimento item: parque.getEstabelecimentos()){
            if(nome.equals(item.getNome())){
                JOptionPane.showMessageDialog(painelPrincipal, "Já existe uma atração de mesmo nome!");
                return false;
            }
        }
        for (Map.Entry<Brinquedos, Float> item : parque.getBrinquedos().entrySet()) {
            Brinquedos brinquedo = item.getKey();
            if(nome.equals(brinquedo.getNome())){
                JOptionPane.showMessageDialog(painelPrincipal, "Já existe uma atração de mesmo nome!");
                return false;
            }
        }

        return true;
    }
    public boolean verificarCadastro(String nome, String capacidadeDigitada, String alturaMinDigitada, String idadeMinDigitada, String valorDigitado, String restricao){

        try{
            int capacidade = Integer.parseInt((capacidadeDigitada));
            float altura = Float.parseFloat(alturaMinDigitada);
            int idade = Integer.parseInt((idadeMinDigitada));
            float valor = Float.parseFloat(valorDigitado);

            Brinquedos novoBrinquedo = new Brinquedos(capacidade, altura, idade, nome, restricao);
            parque.addBrinquedo(novoBrinquedo, valor);

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro! Valor não numérico digitado em Capacidade!");
        }

        return false;
    }

    public void telaCadastrarEstabelecimento() {
        JPanel painel = new JPanel(new GridLayout(9, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        HashMap<String, Float> cardapio = new HashMap<>();

        JLabel labelNome = new JLabel("Nome:");
        JLabel labelCapacidade = new JLabel("Capacidade:");
        JLabel labelRestricao = new JLabel("Restrição: ");
        JLabel labelMenu = new JLabel("Menu:");
        JLabel labelAlimento = new JLabel("Alimento:");
        JLabel labelValor = new JLabel("Valor:");

        JTextField campoNome = new JTextField(20);
        JTextField campoCapacidade = new JTextField(20);
        JTextField campoAlimento = new JTextField(20);
        JTextField campoValor = new JTextField(20);

        JComboBox<String> opcoesRestricoes = opcoesDeRestricao();

        JLabel labelTipo = new JLabel("Tipo:");
        String[] opcoesEstabelecimento = {"Restaurante", "Quiosque", "Lanchonete"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(opcoesEstabelecimento);

        JButton botaoSalvar = new JButton("Salvar no Menu");
        JButton botaoVoltar = new JButton();
        JButton botaoCadastrar = new JButton();

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
                String restricao = (String) opcoesRestricoes.getSelectedItem();

                if (nome.trim().isEmpty() || capacidade.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Digite um valor válido.");
                } else {
                    if (verificarCadastroEstabelecimento(nome,capacidade,tipoEstabelecimento,cardapio, restricao) && cardapio.isEmpty()==false) {
                        //cardapio.clear();

                        JOptionPane.showMessageDialog(painelPrincipal, "Cadastro Realizado com Sucesso!", "Cadastro de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
                    }
                    else {
                        if(cardapio.isEmpty()){
                            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro! Cardapio nao pode estar vazio.");

                        }else {
                            JOptionPane.showMessageDialog(painelPrincipal, "Falha no Cadastro!");
                        }
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
        botaoCadastrar.setIcon(adicionarIconRed);
        botaoVoltar.setIcon(voltarIconRed);
        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelCapacidade);
        painel.add(campoCapacidade);
        painel.add(labelTipo);
        painel.add(comboBoxTipo);
        painel.add(labelRestricao);
        painel.add(opcoesRestricoes);
        painel.add(labelMenu);
        painel.add(new JLabel());
        painel.add(labelAlimento);
        painel.add(campoAlimento);
        painel.add(labelValor);
        painel.add(campoValor);
        painel.add(new JLabel());
        painel.add(botaoSalvar);
        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);
        painelPrincipal.add(painel);

        //pack();
        setVisible(true);
    }


    private boolean verificarCadastroEstabelecimento(String nome, String capacidadeDigitada, String tipoEstabelecimento,HashMap<String,Float> cardapio, String restricao){

        try{
            int capacidade = Integer.parseInt((capacidadeDigitada));

            if (tipoEstabelecimento.equals("Restaurante")) {
                Restaurante novoEstabelecimento = new Restaurante(capacidade, cardapio, nome, restricao);
                parque.addEstabelecimento(novoEstabelecimento);
            }if (tipoEstabelecimento.equals("Quiosque")) {
                Quiosque novoEstabelecimento = new Quiosque(capacidade,cardapio,nome, restricao);
                parque.addEstabelecimento(novoEstabelecimento);
            }if (tipoEstabelecimento.equals("Lanchonete")) {
                Lanchonete novoEstabelecimento = new Lanchonete(capacidade,cardapio,nome, restricao);
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
