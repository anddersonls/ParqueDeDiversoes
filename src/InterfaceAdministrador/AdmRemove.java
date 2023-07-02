package InterfaceAdministrador;

import parque.Alimentacao;
import parque.Brinquedos;
import parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AdmRemove extends JFrame{
    private JPanel painelPrincipal;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;
    private ParqueDiversoes parque;

    private HashMap<String, Float> cardapio;


    public AdmRemove(ParqueDiversoes parque) {
        this.parque = parque;
        cardapio = new HashMap<>();
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

    public void removerEstabelecimento() {

        JLabel texto = new JLabel("Selecione um estabelecimento");
        texto.setFont(fontTitle);

        JButton botaoRemover = new JButton("Remover");
        JButton botaoVoltar= new JButton("Voltar");
        botaoRemover.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painelTexto = new JPanel();
        JPanel painelRemover = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelTexto.add(texto);
        painelRemover.add(botaoRemover);
        painelVoltar.add(botaoVoltar);

        painelTexto.setLayout(new FlowLayout(FlowLayout.CENTER,5,25));
        painelRemover.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        //adicionando na lista o nome dos estabelecimentos
        ArrayList<Alimentacao> arrayEstabelecimentos = new ArrayList<>();
        arrayEstabelecimentos = parque.getEstabelecimentos();

        String[] estabelecimentos = new String[arrayEstabelecimentos.size()];
        for (int i=0;i<arrayEstabelecimentos.size();i++) {
            estabelecimentos[i] = arrayEstabelecimentos.get(i).getNome();
        }
        JList<String> listaEstabelecimentos = new JList<>(estabelecimentos);
        listaEstabelecimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuração do modo de seleção
        listaEstabelecimentos.setCellRenderer(new CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaEstabelecimentos);
        scrollPane.setMaximumSize(new Dimension(500, 90));  //definir o tamanho do painel que ficará a lista
        scrollPane.setMinimumSize (new Dimension (400,90));

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
            }
        });

        ArrayList<Alimentacao> finalArrayEstabelecimentos = arrayEstabelecimentos;
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = listaEstabelecimentos.getSelectedValue();
                int selectedIndex = listaEstabelecimentos.getSelectedIndex();

                if (selectedItem != null) {
                    //chama funcao de remover o estabelecimento do arraylist de estabelecimentos
                    if (verificarERemoverEstabelecimento(finalArrayEstabelecimentos,selectedItem)){
                        JOptionPane.showMessageDialog(painelPrincipal, selectedItem +" removido do Parque!", " Remoção de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
                    }else {
                        JOptionPane.showMessageDialog(painelPrincipal, "Problema ao Remover Item"," Remoção de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(painelPrincipal, "Nenhum item selecionado!"," Remoção de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        painelPrincipal.setLayout(new BoxLayout(painelPrincipal,BoxLayout.Y_AXIS));
        painelPrincipal.add(painelTexto);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(scrollPane , BorderLayout.CENTER);
        painelPrincipal.add(Box.createVerticalStrut(15));   //espaçamento entre a lista e o botao de remover
        painelPrincipal.add(painelRemover);
        painelPrincipal.add(painelVoltar);

        setVisible(true);
    }
    public void removerBrinquedo() {

        JLabel texto = new JLabel("Selecione um brinquedo");
        texto.setFont(fontTitle);

        JButton botaoRemover = new JButton("Remover");
        JButton botaoVoltar= new JButton("Voltar");
        botaoRemover.setPreferredSize(buttonSize);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painelTexto = new JPanel();
        JPanel painelRemover = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelTexto.add(texto);
        painelRemover.add(botaoRemover);
        painelVoltar.add(botaoVoltar);

        painelTexto.setLayout(new FlowLayout(FlowLayout.CENTER,5,25));
        painelRemover.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        //adicionando na lista o nome dos brinquedos
        HashMap<Brinquedos, Float> mapBrinquedos = new HashMap<>();
        mapBrinquedos = parque.getBrinquedos();
        String[] brinquedos = new String[mapBrinquedos.size()];

        int i=0;
        for (HashMap.Entry<Brinquedos, Float> entry : mapBrinquedos.entrySet()) {
            String chave = entry.getKey().getNome();
            brinquedos[i] = chave;
            i++;
        }
        JList<String> listaBrinquedos = new JList<>(brinquedos);
        listaBrinquedos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuração do modo de seleção
        listaBrinquedos.setCellRenderer(new CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaBrinquedos);
        scrollPane.setMaximumSize(new Dimension(500, 90));  //definir o tamanho do painel que ficará a lista
        scrollPane.setMinimumSize (new Dimension (400,90));


        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
            }
        });

        HashMap<Brinquedos, Float> finalMapBrinquedos = mapBrinquedos;
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = listaBrinquedos.getSelectedValue();
                if (selectedItem != null) {
                    //chama funcao de remover o brinquedo do hashmap de estabelecimentos
                    if (verificarERemoverBrinquedos(finalMapBrinquedos,selectedItem)){
                        JOptionPane.showMessageDialog(painelPrincipal, selectedItem +" removido do Parque!", " Remoção de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        OpcoesAdm opcoesAdm = new OpcoesAdm(parque);
                    }else {
                        JOptionPane.showMessageDialog(painelPrincipal, "Problema ao Remover Item"," Remoção de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(painelPrincipal, "Nenhum item selecionado!"," Remoção de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        painelPrincipal.setLayout(new BoxLayout(painelPrincipal,BoxLayout.Y_AXIS));
        painelPrincipal.add(painelTexto);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(scrollPane , BorderLayout.CENTER);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(painelRemover);
        painelPrincipal.add(painelVoltar);

        setVisible(true);
    }
    //Funcao para setar a cor de seleção dos itens
    public static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                Color selectionColor = Color.GRAY; // Cor da bolinha (pode ser alterada)
                renderer.setBackground(selectionColor);
                renderer.setForeground(list.getForeground());
            }

            return renderer;
        }
    }
    public boolean verificarERemoverEstabelecimento(ArrayList<Alimentacao> estabelecimentos,String selectedItem) {
        try {
            for (int i=0;i<estabelecimentos.size();i++) {
                if (Objects.equals(estabelecimentos.get(i).getNome(), selectedItem)) {
                    estabelecimentos.remove(i);
                    break;
                }
            }
            return  true;
        }catch(ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(painelPrincipal,"Problema ao Remover Estabelecimento!"+ e.getMessage(),"Remoção de Estabelecimento",JOptionPane.INFORMATION_MESSAGE);
        }
        return  false;

    }
    public boolean verificarERemoverBrinquedos(HashMap<Brinquedos,Float> brinquedos, String selectedItem) {
        try {
            for (HashMap.Entry<Brinquedos, Float> entry : brinquedos.entrySet()) {
                String chave = entry.getKey().getNome();
                Brinquedos key = entry.getKey();
                if (chave.equals(selectedItem)){
                    brinquedos.remove(key);
                    break;
                }
            }
            return  true;
        }catch(ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(painelPrincipal,"Problema ao Remover Brinquedo!"+ e.getMessage(),"Remoção de Brinquedo",JOptionPane.INFORMATION_MESSAGE);
        }
        return  false;
    }
    public void removeAllComponents() {
        painelPrincipal.removeAll();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

}
