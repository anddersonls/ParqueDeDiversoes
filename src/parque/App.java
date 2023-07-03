package parque;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        // Criar uma instância da interface gráfica
        ParqueDiversoes parque = new ParqueDiversoes();
        Visitante cliente = new Visitante("anderson", 123L, 10, 1.72F, "123", 100.0f);
        Brinquedos brinquedo1 = new Brinquedos(10, 3, 4, "roda");
        Brinquedos brinquedo2 = new Brinquedos(34, 6.12f, 4, "aaaaa");
        HashMap<String, Float> cardapio1 = new HashMap<>();
        cardapio1.put("arroz", 93F);
        cardapio1.put("batata frita", 12.3F);
        cardapio1.put("carne", 45.8F);
        Restaurante restaurante = new Restaurante(10, cardapio1, "ru");
        Quiosque quiosque = new Quiosque(10, cardapio1, "cidade olimpica");
        parque.addBrinquedo(brinquedo1, 10f);
        parque.addBrinquedo(brinquedo2, 23.1f);
        parque.addEstabelecimento(restaurante);
        parque.addEstabelecimento(quiosque);
        parque.addVisitante(cliente);
        InterfaceInicial gui = new InterfaceInicial(parque);
    }
}
