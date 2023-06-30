package parque;

public class App {
    public static void main(String[] args) {
        // Criar uma instância da interface gráfica
        ParqueDiversoes parque = new ParqueDiversoes();
        Visitante cliente = new Visitante("anderson", 123L, 10, 1.72F, "123", 0.0f);
        Brinquedos brinquedo1 = new Brinquedos(10, 3, 4, "roda");
        Brinquedos brinquedo2 = new Brinquedos(34, 6.12f, 4, "aaaaa");
        parque.addBrinquedo(brinquedo1, 10f);
        parque.addBrinquedo(brinquedo2, 23.1f);
        parque.addVisitante(cliente);
        Interface gui = new Interface(parque);
    }
}
