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
        public TelaBase(ParqueDiversoes parque){
            this.parque = parque;
            this.bigButton = new Dimension(200, 50);
            this.buttonSize = new Dimension(100, 25);
            this.fontText = new Font("Arial", Font.PLAIN, 14);
            this.fontTitle = new Font("Arial", Font.PLAIN, 20);
            this.fontButton = new Font("Arial", Font.PLAIN, 15);
            ImageIcon icon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\icon.jpeg");
            setIconImage(icon.getImage());
            setTitle("PlayGround");
            setSize(700, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            painelPrincipal = new JPanel();
            setContentPane(painelPrincipal);
            setLocationRelativeTo(null);
        }
}
