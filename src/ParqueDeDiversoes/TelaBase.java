package ParqueDeDiversoes;

import ParqueDeDiversoes.InterfaceAdministrador.LoginAdm;
import ParqueDeDiversoes.InterfaceUsuário.LoginCliente;
import ParqueDeDiversoes.parque.ParqueDiversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaBase extends JFrame{
    protected ParqueDiversoes parque;
    protected JPanel painelPrincipal;
    protected Dimension buttonSize;
    protected Dimension bigButton;
    protected Font fontText;
    protected Font fontButton;
    protected Font fontTitle;
    protected Color corDeFundo;
    protected ImageIcon admIconRed;
    protected ImageIcon clienteIconRed;
    protected ImageIcon voltarIconRed;
        public TelaBase(ParqueDiversoes parque){
            this.parque = parque;
            this.bigButton = new Dimension(200, 50);
            this.buttonSize = new Dimension(100, 25);
            this.fontText = new Font("Times New Roman", Font.PLAIN, 14);
            this.fontTitle = new Font("Times New Roman", Font.BOLD, 20);
            this.fontButton = new Font("Times New Roman", Font.PLAIN, 15);
            this.corDeFundo = Color.LIGHT_GRAY;

            ImageIcon icon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\icon.jpeg");
            setIconImage(icon.getImage());
            ImageIcon admIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\adm.png");
            ImageIcon clienteIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\cliente.png");
            admIconRed = new ImageIcon(admIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            clienteIconRed = new ImageIcon(clienteIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            ImageIcon voltarIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\voltar.png");
            voltarIconRed = new ImageIcon(voltarIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            setTitle("PlayGround");
            setSize(700, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            painelPrincipal = new JPanel();
            setContentPane(painelPrincipal);
            setLocationRelativeTo(null);
        }
}
