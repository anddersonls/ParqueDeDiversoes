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
    protected Dimension buttonSize, bigButton;
    protected Font fontText, fontButton, fontTitle;
    protected Color corDeFundo;
    protected ImageIcon admIconRed, clienteIconRed, voltarIconRed, loginIconRed, lixeiraIconRed,
            adicionarIconRed, comprarIconRed, cadastrarIconRed, depositarIconRed, imagemGrupoRed,
            andersonIconRed, sabrynaIconRed, vitoIconRed;
    protected String acessoAdm, acessoCliente, acessoRelatorio;
        public TelaBase(ParqueDiversoes parque){
            this.parque = parque;
            //DEFINIÇÕES PADRÃO
            this.bigButton = new Dimension(200, 50);
            this.buttonSize = new Dimension(70, 30);
            this.fontText = new Font("Times New Roman", Font.BOLD, 15);
            this.fontTitle = new Font("Times New Roman", Font.BOLD, 20);
            this.fontButton = new Font("Times New Roman", Font.PLAIN, 18);
            this.corDeFundo = Color.LIGHT_GRAY;

            //ICONES
            ImageIcon icon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\icon.jpeg");
            setIconImage(icon.getImage());
            ImageIcon admIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\adm.png");
            this.admIconRed = new ImageIcon(admIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            ImageIcon clienteIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\cliente.png");
            this.clienteIconRed = new ImageIcon(clienteIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            ImageIcon voltarIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\voltar.png");
            this.voltarIconRed = new ImageIcon(voltarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon loginIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\login.png");
            this.loginIconRed = new ImageIcon(loginIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon lixeiraIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\lixeira.png");
            this.lixeiraIconRed = new ImageIcon(lixeiraIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon adicionarIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\adicionar.png");
            this.adicionarIconRed = new ImageIcon(adicionarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon comprarIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\comprar.png");
            this.comprarIconRed = new ImageIcon(comprarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon cadastrarIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\cadastrar.png");
            this.cadastrarIconRed = new ImageIcon(cadastrarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon depositarIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\deposito.png");
            this.depositarIconRed = new ImageIcon(depositarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            ImageIcon andersonIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\anderson.png");
            this.andersonIconRed = new ImageIcon(andersonIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            ImageIcon sabrynaIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\sabryna.png");
            this.sabrynaIconRed = new ImageIcon(sabrynaIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            ImageIcon vitoIcon = new ImageIcon("C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Imagens\\vito.png");
            this.vitoIconRed = new ImageIcon(vitoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

            //PATH ARQUIVOS
            this.acessoAdm = "C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Arquivos\\acessoAdm.txt";
            this.acessoCliente = "C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Arquivos\\acessoCliente.txt";
            this.acessoRelatorio = "C:\\Users\\ander\\Documents\\Java_Projects\\ParqueDeDiversoes\\src\\ParqueDeDiversoes\\Arquivos\\relatorio.txt";

            setTitle("PlayGround");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //JANELA PRINCIPAL
            painelPrincipal = new JPanel();
            setContentPane(painelPrincipal);
            setLocationRelativeTo(null);
        }
}
