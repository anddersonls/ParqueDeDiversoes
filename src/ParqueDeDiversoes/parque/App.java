package ParqueDeDiversoes.parque;

import ParqueDeDiversoes.InterfaceUsuário.OpcoesCliente;
import ParqueDeDiversoes.TelaBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        // Criar uma instância da interface gráfica
        ParqueDiversoes parque = new ParqueDiversoes();
        Cliente cliente = new Cliente("anderson", 123L, 10, 10f, "123", 100.0f);
        Brinquedos brinquedo1 = new Brinquedos(10, 3f, 4, "Roda Gigante", "Restaurante Universitário");
        Brinquedos brinquedo2 = new Brinquedos(34, 6.12f, 4, "Carrossel", "Sem restrição");
        HashMap<String, Float> cardapio1 = new HashMap<>();
        cardapio1.put("Arroz", 93F);
        cardapio1.put("Batata frita", 12.3F);
        cardapio1.put("Carne", 45.8F);
        Restaurante restaurante = new Restaurante(10, cardapio1, "Restaurante Universitário", "Cidade Olimpica");
        Quiosque quiosque = new Quiosque(10, cardapio1, "Cidade Olimpica", "Sem restrição");
        parque.addBrinquedo(brinquedo1, 10f);
        parque.addBrinquedo(brinquedo2, 23.1f);
        parque.addEstabelecimento(restaurante);
        parque.addEstabelecimento(quiosque);
        parque.addCliente(cliente);
        InterfaceInicial gui = new InterfaceInicial(parque);
    }
}
