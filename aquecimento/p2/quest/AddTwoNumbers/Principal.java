public class Principal {

    public static void main(String[] args) {
        Lista l1 = new Lista();
        Lista l2 = new Lista();
        Lista l3 = new Lista();

        

        l1.inserir(2);
        l1.inserir(4);
        l1.inserir(3);

        l2.inserir(5);
        l2.inserir(6);
        l2.inserir(4);

        l1.mostrar();
        System.out.println();
        l2.mostrar();
        System.out.println();

        l3.addTwoNumbers(l1, l2);
        l3.mostrar();
    }   
}
