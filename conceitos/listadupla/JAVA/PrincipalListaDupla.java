public class PrincipalListaDupla {

    public static void main(String [] args) {
        ListaDupla list = new ListaDupla();

        list.inserirInicio(3);

        list.inserirFim(5);
        list.inserirFim(7);
        list.inserirFim(10);

        list.mostrar();

        list.inverter();

        list.mostrar();

    }
    

    
}
