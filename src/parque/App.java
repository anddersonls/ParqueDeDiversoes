package parque;

public class App {
    public static void main(String[] args) {
        // Criar uma instância da interface gráfica
        ParqueDiversoes parque = new ParqueDiversoes();
        Visitante cliente = new Visitante("anderson", 123L, 10, 1.72F, "123", 0.0f);
        parque.addVisitante(cliente);
        Interface gui = new Interface(parque);
    }
}
