package parque;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.Box;


public class UserInterface extends Interface {
	private JPanel contentPane;
    private Dimension buttonSize;
    private Font fontText;
    private Font fontButton;
    private Font fontTitle;

    public UserInterface() {
        this.buttonSize = new Dimension(400, 50);
        this.fontText = new Font("Arial", Font.PLAIN, 14);
        this.fontTitle = new Font("Arial", Font.PLAIN, 20);
        this.fontButton = new Font("Arial", Font.PLAIN, 15);
        setTitle("Minha Interface Gráfica");
        setSize(1080, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        setLocationRelativeTo(null);

        interfaceInicial();
    }

    void novaInterface() {
         setTitle("Cliente");
         setSize(1080, 700);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocationRelativeTo(null);
         JButton voltar_button = new JButton("Voltar");
         JLabel novoLabel = new JLabel("SOCORRO");
         contentPane.add(novoLabel);
         contentPane.add(voltar_button);
         voltar_button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             	removeAllComponents();
                interfaceInicial(); 
             }
         });

         novoLabel.setFont(fontTitle);
         setVisible(true);
    }
}
