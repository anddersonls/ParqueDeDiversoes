package ParqueDeDiversoes.InterfaceUsuário;

import ParqueDeDiversoes.TelaBase;
import ParqueDeDiversoes.parque.Atracoes;
import ParqueDeDiversoes.parque.Brinquedos;
import ParqueDeDiversoes.parque.ParqueDiversoes;
import ParqueDeDiversoes.parque.Cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngressoBrinquedo extends TelaBase {
    public IngressoBrinquedo(ParqueDiversoes parque) {
        super(parque);
        telaEscolherBrinquedo();
    }

    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
    public void telaEscolherBrinquedo() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        HashMap<Brinquedos, Float> brinquedos = parque.getBrinquedos();
        List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

        JButton botaoVoltar = new JButton();
        JButton botaoFimCompra = new JButton();
        botaoFimCompra.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoFimCompra);
        JLabel label = new JLabel("Selecione os brinquedos que você deseja:");
        label.setFont(fontButton);
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(label);
        painelPrincipal.add(Box.createVerticalStrut(15));
        botaoFimCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float valorTotal = 0.0f;
                ArrayList<Brinquedos> brinquedosSelecionados = new ArrayList<>();

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        String texto = checkBox.getText();
                        String[] partes = texto.split(" - Valor: ");
                        String nome = partes[0];
                        float valor = Float.parseFloat(partes[1]);
                        valorTotal += valor;

                        // Recupera o brinquedo associado ao checkbox selecionado
                        for (Map.Entry<Brinquedos, Float> item : brinquedos.entrySet()) {
                            Brinquedos objeto = item.getKey();
                            if (objeto.getNome().equals(nome)) {
                                brinquedosSelecionados.add(objeto);
                            }
                        }
                    }
                }
                if(verificaIdadeEAlturaa(brinquedosSelecionados)) {
                    if(verificaRestricao(brinquedosSelecionados)) {
                        if (descontaValor(valorTotal, brinquedosSelecionados)) {
                            setVisible(false);
                            OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
                        }
                    }
                }
            }
        });

        for (Brinquedos brinquedo : brinquedos.keySet()) {
            float value = brinquedos.get(brinquedo);
            JCheckBox checkBox = new JCheckBox(brinquedo.getNome() + " - Valor: " + value);
            checkBox.setFont(fontText);
            checkBoxes.add(checkBox);
            painelPrincipal.add(checkBox);
        }
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesCliente opcoesCliente = new OpcoesCliente(parque);
            }
        });

        botaoVoltar.setIcon(voltarIconRed);
        botaoFimCompra.setIcon(comprarIconRed);

        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(painel);

        painelPrincipal.add(Box.createVerticalStrut(15));

        painelPrincipal.add(painelBotoes);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        wrapperPanel.add(painelPrincipal, BorderLayout.CENTER);
        setContentPane(wrapperPanel);
        setVisible(true);
    }
    public long pegaCPF(){
        try (BufferedReader br = new BufferedReader(new FileReader(acessoCliente))) {
            String cpfArquivo = br.readLine();
            long cpf = Long.parseLong(cpfArquivo);
            return cpf;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(painelPrincipal, "Falha no acesso do cliente!");
        }
        return -1;
    }

    public boolean descontaValor(float valor, ArrayList<Brinquedos> brinquedosSelecionados){
        long cpf = pegaCPF();
        ArrayList<Cliente> clientes = parque.getVisitantes();
        for(Cliente cliente : clientes){
            if(cpf == cliente.getCpf()){
                if(cliente.descontarCredito(valor)){
                    for(Brinquedos brinquedo : brinquedosSelecionados){
                        cliente.addNoHistorico(brinquedo, valor);
                    }
                    String mensagem = "Compra finalizada!\n" + "Valor total da compra: R$ " + valor;
                    JOptionPane.showMessageDialog(painelPrincipal, mensagem, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                    }else{
                        JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui credito suficiente! Voce tem R$ "+ cliente.getCredito());
                    }
                }
            }
        return false;
    }

    public boolean verificaIdadeEAlturaa(ArrayList<Brinquedos> brinquedosSelecionados){
        long cpf = pegaCPF();
        ArrayList<Cliente> clientes = parque.getVisitantes();
        for(Cliente cliente : clientes) {
            if (cpf == cliente.getCpf()) {
                for (Brinquedos brinquedo : brinquedosSelecionados) {
                    if (brinquedo.getAlturaMin() > cliente.getAltura()) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui a altura minima para ir nesse brinquedo!", "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                    if (brinquedo.getIdadeMin() > cliente.getIdade()) {
                        JOptionPane.showMessageDialog(painelPrincipal, "Voce nao possui a idade minima para ir nesse brinquedo!", "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    public boolean verificaRestricao(ArrayList<Brinquedos> brinquedosSelecionados){
        long cpf = pegaCPF();
        String mensagemRestricao = "";
        int contador = brinquedosSelecionados.size();
        ArrayList<Cliente> clientes = parque.getVisitantes();
        for(Cliente cliente : clientes) {
            if (cpf == cliente.getCpf()) {
                for(Brinquedos brinquedo: brinquedosSelecionados) {
                    int aux = contador;
                    if(brinquedo.getRestricao().equals("Sem restrição")){
                        contador--;
                    }
                    for (Map.Entry<Atracoes, Float> item : cliente.getHistorico().entrySet()) {
                        Atracoes objeto = item.getKey();
                        if (objeto.getNome().equals(brinquedo.getRestricao())) {
                            contador--;
                            break;
                        }
                    }
                    if(aux == contador){
                        mensagemRestricao += "Atração: " + brinquedo.getNome() + " - " + "Restrição: " + brinquedo.getRestricao() + "\n";
                    }
                }
                if(contador==0) {
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(painelPrincipal, "Restrições não atendidas: \n" +mensagemRestricao, "Escolha de brinquedos", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }
}
