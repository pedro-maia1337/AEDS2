public class Principal {
    public static void main(String[] args) {
        Lista lista = new Lista();

        System.out.println("=== LISTA FLEXIVEL SIMPLESMENTE ENCADEADA ===");

        lista.inserirInicio(10);
        lista.inserirInicio(20);
        lista.inserirFim(30);
        lista.inserirFim(40);

        lista.mostrar();

        System.out.println(lista.tamanho());

        
    } 
}
