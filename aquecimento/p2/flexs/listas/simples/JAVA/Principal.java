public class Principal {
    public static void main(String[] args) {
        Lista lista = new Lista();
        
        lista.inserirInicio(0);
        lista.inserirInicio(6);
        lista.inserirInicio(5);
        lista.inserirInicio(8);
        lista.mostrar();

        lista.inverter();

        lista.mostrar();

        System.out.println();
    }
}
