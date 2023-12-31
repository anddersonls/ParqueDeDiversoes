package ParqueDeDiversoes.parque;

import ParqueDeDiversoes.TelaBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equipe extends TelaBase {
    public Equipe(ParqueDiversoes parque){
        super(parque);
        tela();
    }
    public void tela(){
        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBackground(corDeFundo);
        painelPrincipal.setBackground(corDeFundo);

        JLabel iconAnderson = new JLabel(andersonIconRed);
        JLabel iconSabryna = new JLabel(sabrynaIconRed);
        JLabel iconVito = new JLabel(vitoIconRed);
        JLabel iconBonini = new JLabel(boniniIconRed);
        JPanel icones = new JPanel();
        icones.add(iconAnderson);
        icones.add(iconSabryna);
        icones.add(iconVito);
        icones.add(iconBonini);
        icones.setBackground(corDeFundo);

        JPanel labels = new JPanel();
        JLabel labelAnderson = new JLabel("Anderson                ");
        JLabel labelSabryna = new JLabel("Sabryna                     ");
        JLabel labelVito = new JLabel("Vito                     ");
        JLabel labelBonini = new JLabel("Bonini   ");
        labels.add(labelAnderson);
        labels.add(labelSabryna);
        labels.add(labelVito);
        labels.add(labelBonini);
        labels.setBackground(corDeFundo);

        JButton botaoVoltar = new JButton();
        botaoVoltar.setPreferredSize(buttonSize);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                InterfaceInicial interfaceInicial = new InterfaceInicial(parque);
            }
        });

        botaoVoltar.setIcon(voltarIconRed);
        botaoVoltar.setPreferredSize(buttonSize);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(corDeFundo);
        painelBotoes.add(botaoVoltar);
        JLabel equipe = new JLabel("                      <Equipe Desenvolvedora>");
        equipe.setFont(fontTitle);
        painel.add(equipe);
        painel.add(icones);
        painel.add(labels);
        painel.add(painelBotoes);
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(painel);

        setVisible(true);
    }
}
