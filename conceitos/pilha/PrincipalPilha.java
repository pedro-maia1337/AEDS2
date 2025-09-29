/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class PrincipalPilha {
	public static void main(String[] args) {
		try {
			System.out.println(" ==== PILHA FLEXIVEL ====");
			Pilha pilha = new Pilha();
			int soma = 0;
			int max = 0;

			pilha.inserir(0);
			pilha.inserir(1);
			pilha.inserir(2);
			pilha.inserir(3);
			pilha.inserir(4);
			pilha.inserir(5);

			pilha.mostrar();

			soma = pilha.getSoma();
			max = pilha.getMaxRecursivo();

			System.out.println(max);
		}
		catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}
