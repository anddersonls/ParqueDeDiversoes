package parque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdmInterface extends Interface {
    private JPanel contentPane;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;

    public AdmInterface(ParqueDiversoes parque) {
        this.buttonSize = new Dimension(100, 25);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        contentPane = new JPanel();
        setContentPane(contentPane);
        telaLogin();
    }

    @Override
    public void removeAllComponents() {
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
    }
    public JButton[] layoutDeOpcoes(String[] opcoes){
        int i;

        JPanel painel = new JPanel(new GridLayout(7, 1, 20, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

        JButton[] opcoesBotao = new JButton[opcoes.length];
        painel.add(new JLabel());
        for(i=0; i<opcoes.length-1; i++){
            JButton botao = new JButton(opcoes[i]);
            opcoesBotao[i] = botao;
            painel.add(botao);
        }
        painel.add(new JLabel());

        JPanel painelVoltar = new JPanel();
        JButton botaoVoltar = new JButton(opcoes[i]);
        opcoesBotao[i] = botaoVoltar;
        painelVoltar.add(botaoVoltar);
        painel.add(painelVoltar);
        botaoVoltar.setPreferredSize(buttonSize);
        contentPane.add(painel);

        setVisible(true);
        return opcoesBotao;
    }
    public void telaLogin() {
        setTitle("Administrador");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoEntrar = new JButton("Entrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoEntrar.setPreferredSize(buttonSize);

        JPanel painelCpf = new JPanel();
        JPanel painelSenha = new JPanel();
        JPanel painelBotoes = new JPanel();
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

        painel.add(painelCpf);
        painel.add(painelSenha);
        painel.add(painelBotoes);

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                dispose();
                interfaceInicial();
            }
        });

        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpfDigitado = textCpf.getText();
                String senhaDigitada = textSenha.getText();

                if (verificarLogin(cpfDigitado, senhaDigitada)) {
                    JOptionPane.showMessageDialog(AdmInterface.this, "Login bem-sucedido!");
                    removeAllComponents();
                    telaOpcoes();
                } else {
                    JOptionPane.showMessageDialog(AdmInterface.this, "CPF ou senha incorretos!");
                    removeAllComponents();
                    telaLogin();
                }
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(30));
        contentPane.add(painel);
        contentPane.add(Box.createVerticalStrut(15));
        setVisible(true);
    }
    private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        String caminhoArquivo = "C:/Users/ander/Documents/Java_Projects/ParqueDeDiversoes/src/Arquivos/acessoAdm.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String cpfArquivo = br.readLine();
            String senhaArquivo = br.readLine();

            return cpfArquivo.equals(cpfDigitado) && senhaArquivo.equals(senhaDigitada);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return false;
    }

    public void telaOpcoes() {
        String[] opcoes = {"Adicionar Atração", "Remover Atração", "Voltar"};
        JButton[] opcoesBotoes = new JButton[opcoes.length];

        opcoesBotoes = layoutDeOpcoes(opcoes);

        opcoesBotoes[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesDeCadastro();
            }
        });
        opcoesBotoes[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerAtracoes();
            }
        });
        opcoesBotoes[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaLogin();
            }
        });
    }

    public void opcoesDeCadastro() {
        String[] opcoes = {"Cadastrar Brinquedo", "Cadastrar Estabelecimento", "Voltar"};
        JButton[] opcoesBotoes = new JButton[opcoes.length];

        opcoesBotoes = layoutDeOpcoes(opcoes);

        opcoesBotoes[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCadastrarBrinquedo();
            }
        });
        opcoesBotoes[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCadastrarEstabelecimento();
            }
        });
        opcoesBotoes[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });
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
                removeAllComponents();
                opcoesDeCadastro();
            }
        });

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                JOptionPane.showMessageDialog(contentPane, "Cadastro Realizado com Sucesso!", "Cadastro de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                telaOpcoes();
            }
        });

        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelCapacidade = new JLabel("Capacidade: ");
        JLabel labelAlturaMin = new JLabel("Altura Mínima: ");
        JLabel labelIdadeMin = new JLabel("Idade Mínima: ");

        JTextField textNome = new JTextField(20);
        JTextField textCapacidade = new JTextField(20);
        JTextField textAlturaMin = new JTextField(20);
        JTextField textIdadeMin = new JTextField(20);

        painel.add(labelNome);
        painel.add(textNome);
        painel.add(labelCapacidade);
        painel.add(textCapacidade);
        painel.add(labelAlturaMin);
        painel.add(textAlturaMin);
        painel.add(labelIdadeMin);
        painel.add(textIdadeMin);
        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);
        contentPane.add(painel);
        setVisible(true);
    }

    public void telaCadastrarEstabelecimento() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();

        JLabel labelCapacidade = new JLabel("Capacidade:");
        JTextField campoCapacidade = new JTextField();

        JLabel labelTipo = new JLabel("Tipo:");
        String[] opcoesEstabelecimento = {"Restaurante", "Quiosque", "Lanchonete"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(opcoesEstabelecimento);

        JLabel labelMenu = new JLabel("Menu:");

        JLabel labelAlimento = new JLabel("Alimento:");
        JTextField campoAlimento = new JTextField();

        JLabel labelValor = new JLabel("Valor:");
        JTextField campoValor = new JTextField();

        JButton botaoSalvar = new JButton("Adicionar Opcao ao Menu");
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                JOptionPane.showMessageDialog(contentPane, "Cadastro Realizado com Sucesso!", "Cadastro de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                opcoesDeCadastro();
            }
        });
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contentPane, "Item Adicionado ao Menu!", "Cadastro de Item do Menu", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesDeCadastro();
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
        contentPane.add(panel);


        //pack();
        setVisible(true);

    }

    public void removerAtracoes () {
        String[] opcoes = {"Remover Brinquedo", "Remover Estabelecimento", "Voltar"};
        JButton[] opcoesBotoes = new JButton[opcoes.length];

        opcoesBotoes = layoutDeOpcoes(opcoes);

        opcoesBotoes[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerBrinquedo();
            }
        });

        opcoesBotoes[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerEstabelecimento();
            }
        });

        opcoesBotoes[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });
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

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JList<String> listaEstabelecimentos = new JList<>(items);   //Aqui o parametro que o jList receberia seria o hashMap de estabelecimentos
        listaEstabelecimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuração do modo de seleção
        listaEstabelecimentos.setCellRenderer(new CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaEstabelecimentos);

        scrollPane.setMaximumSize(new Dimension(500, 90));  //definir o tamanho do painel que ficará a lista
        scrollPane.setMinimumSize (new Dimension (400,90));

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerAtracoes();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = listaEstabelecimentos.getSelectedValue();
                int selectedIndex = listaEstabelecimentos.getSelectedIndex();

                if (selectedItem != null) {
                    JOptionPane.showMessageDialog(contentPane, selectedItem +" removido do Parque!", " Remoção de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                    //aqui chamaria a funcao que iria remover o estabelecimento do hashmap de estabelecimentos

                } else {
                    JOptionPane.showMessageDialog(contentPane, "Nenhum item selecionado!"," Remoção de Estabelecimento", JOptionPane.INFORMATION_MESSAGE);
                }

                removeAllComponents();
                removerAtracoes();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(painelTexto);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(scrollPane , BorderLayout.CENTER);
        contentPane.add(Box.createVerticalStrut(15));   //espaçamento entre a lista e o botao de remover
        contentPane.add(painelRemover);
        contentPane.add(painelVoltar);

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

        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JList<String> listaBrinquedos = new JList<>(items);   //Aqui o parametro que o jList receberia seria o hashMap de estabelecimentos
        listaBrinquedos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Configuração do modo de seleção
        listaBrinquedos.setCellRenderer(new CustomListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(listaBrinquedos);
        scrollPane.setMaximumSize(new Dimension(500, 90));  //definir o tamanho do painel que ficará a lista
        scrollPane.setMinimumSize (new Dimension (400,90));


        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerAtracoes();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = listaBrinquedos.getSelectedValue();
                int selectedIndex = listaBrinquedos.getSelectedIndex();

                if (selectedItem != null) {
                    JOptionPane.showMessageDialog(contentPane, selectedItem +" removido do Parque!", " Remoção de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                    //aqui chamararia a funcao que iria remover o estabelecimento do hashmap de brinquedos

                } else {
                    JOptionPane.showMessageDialog(contentPane, "Nenhum item selecionado!"," Remoção de Brinquedo", JOptionPane.INFORMATION_MESSAGE);
                }

                removeAllComponents();
                removerAtracoes();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(painelTexto);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(scrollPane , BorderLayout.CENTER);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(painelRemover);
        contentPane.add(painelVoltar);

        setVisible(true);
    }
    //Funcao para setar a cor de seleção dos itens
    static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                // Adicionando uma bolinha colorida ao lado do item selecionado
                Color selectionColor = Color.GRAY; // Cor da bolinha (pode ser alterada)
                renderer.setBackground(selectionColor);
                renderer.setForeground(list.getForeground());
            }

            return renderer;
        }
    }
}


