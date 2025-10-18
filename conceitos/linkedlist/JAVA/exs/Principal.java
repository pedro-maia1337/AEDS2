public class Principal {
    public static void main(String[] args) {
        Lista lista = new Lista();
        int maiorElemento = 0;
        int tamanho = 0;

        System.out.println("=== LISTA FLEXIVEL ===");

        lista.inserirInicio(30);
        lista.inserirInicio(10);
        lista.inserirInicio(20);

        lista.mostrar();

        maiorElemento = lista.maiorElemento();
        tamanho = lista.tamanho();

        System.out.println("maior dos elementos: " + maiorElemento);
        System.out.println(tamanho);

        
    } 
}
