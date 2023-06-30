package parque;

import java.util.HashMap;
import java.util.Map;

public abstract class Alimentacao extends Atracoes implements InterfaceAlimentacao{
	private HashMap<String, Float> cardapio;

	
	public Alimentacao(int capacidade, HashMap<String, Float> cardapio, String nome){
		super(capacidade, nome);
		this.cardapio = cardapio;
	}
	
	@Override
	public void mostrarMenu() {
		for (Map.Entry<String, Float> entry : cardapio.entrySet()) {
            String chave = entry.getKey();
            Float valor = entry.getValue();
            System.out.println("Produto: " + chave + ", Valor: " + valor);
        }
	}

	/*private boolean verificarLogin(String cpfDigitado, String senhaDigitada) {
        String caminhoArquivo = "/home/joaovictor/Área de Trabalho/UFMA/3º Periodo/Linguagem de Programacao 2/LP2- TrabalhoFinal/Projeto/src/Arquivos/acessoAdm.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String cpfArquivo = br.readLine();
            String senhaArquivo = br.readLine();

            return cpfArquivo.equals(cpfDigitado) && senhaArquivo.equals(senhaDigitada);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return false;
    }*/

}
