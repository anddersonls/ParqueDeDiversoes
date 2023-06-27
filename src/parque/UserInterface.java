package parque;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class UserInterface extends JFrame {
	private JPanel contentPane; 
	
    public UserInterface() {
    	interfaceInicial();
    }
    
    public void interfaceInicial() {
    	setTitle("Minha Interface Gráfica");
        setSize(1080, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Dimension size = new Dimension(200, 100);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
        
        JLabel label = new JLabel("Bem-Vindo ao sistema do Democracy Park!");
        
        JButton adm_button = new JButton("Administrador");
        JButton cliente_button = new JButton("Cliente");
        adm_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removeAllComponents();
                novaInterface(); 
            }
        });
        
        cliente_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removeAllComponents();
                novaInterface(); 
            }
        });


        contentPane.add(label);
        contentPane.add(adm_button);
        contentPane.add(cliente_button);


        contentPane.setLayout(new FlowLayout());

        setVisible(true);

    }
    
    private void removeAllComponents() {
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
    }

    void novaInterface() {
         setTitle("Nova Interface");
         setSize(1080, 700);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocationRelativeTo(null);
         JButton voltar_button = new JButton("Voltar");
         JLabel novoLabel = new JLabel("Será se dá certo agora?");
         contentPane.add(novoLabel);
         contentPane.add(voltar_button);
         voltar_button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             	removeAllComponents();
                interfaceInicial(); 
             }
         });

         Font font = new Font(novoLabel.getFont().getName(), Font.PLAIN, 20);
         novoLabel.setFont(font);
         setVisible(true);
    }
}
