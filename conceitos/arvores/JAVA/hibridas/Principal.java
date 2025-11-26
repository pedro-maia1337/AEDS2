public class Principal {
    public static void main(String[] args) {
        ArvoreArvore arvore = new ArvoreArvore();
        int res = 0;

        arvore.mostrar();

        arvore.inserirPalavras();

        System.out.println("\n=== PALAVRAS POR LETRA ESPECÍFICA ===");
        arvore.mostrarPalavrasPorLetra('D');

        res = arvore.contarPalavras("Dama");

        System.out.println(res);
    }
}
