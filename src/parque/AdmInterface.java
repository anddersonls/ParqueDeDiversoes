package parque;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdmInterface extends Interface {
    private JPanel contentPane;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;

    public AdmInterface() {
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
                removeAllComponents();
                telaOpcoes();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(30));
        contentPane.add(painel);
        contentPane.add(Box.createVerticalStrut(15));
        setVisible(true);
    }

    public void telaOpcoes() {
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
                telaLogin();
            }
        });

        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                opcoesDeCadastro();
            }
        });
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerAtracoes();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(50));
        contentPane.add(painelBotoes);
        contentPane.add(painelVoltar);
        //pack();
        setVisible(true);
    }

    public void opcoesDeCadastro() {
        JButton botaoCadastroBrinquedo = new JButton("Cadastrar Brinquedo");
        JButton botaoCadastroEstabelecimento = new JButton("Cadastrar Estabelecimento");
        JButton botaoVoltar = new JButton("Voltar");

        JPanel painelBotoes = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelBotoes.add(botaoCadastroBrinquedo);
        painelBotoes.add(botaoCadastroEstabelecimento);
        painelVoltar.add(botaoVoltar);

        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });

        botaoCadastroBrinquedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCadastrarBrinquedo();
            }
        });
        botaoCadastroEstabelecimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaCadastrarEstabelecimento();
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(50));
        contentPane.add(painelBotoes);
        contentPane.add(painelVoltar);
        setVisible(true);
    }

    public void telaCadastrarBrinquedo() {
        JPanel painel = new JPanel(new GridLayout(7, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoVoltar.setPreferredSize(buttonSize);
        botaoCadastrar.setPreferredSize(buttonSize);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
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
        JButton botaoRemoverBrinquedo = new JButton("Remover Brinquedo");
        JButton botaoRemoverEstabelecimento = new JButton("Remover Estabelecimento");
        JButton botaoVoltar = new JButton("Voltar");

        JPanel painelBotoes = new JPanel();
        JPanel painelVoltar = new JPanel();

        painelBotoes.add(botaoRemoverEstabelecimento);
        painelBotoes.add(botaoRemoverBrinquedo);
        painelVoltar.add(botaoVoltar);

        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelVoltar.setLayout(new FlowLayout(FlowLayout.CENTER));

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });

        botaoRemoverEstabelecimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                removerEstabelecimento();
            }
        });

        botaoRemoverBrinquedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //carregar a proxima pagina
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalStrut(50));
        contentPane.add(painelBotoes);
        contentPane.add(painelVoltar);
        setVisible(true);

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



        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllComponents();
                telaOpcoes();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //carregar a proxima pagina
                //função para remover um estabelecimento do hashmap de estabelecimentos
            }
        });

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(painelTexto);
        contentPane.add(Box.createVerticalStrut(15));
        contentPane.add(scrollPane);
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


