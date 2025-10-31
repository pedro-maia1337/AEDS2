class Principal {
    public static void main(String[] args) {
        LP listaPilha = new LP();

        listaPilha.inserirLista();
        listaPilha.inserirLista();
        listaPilha.inserirLista();

        listaPilha.inserirPilha(1, 1);
        listaPilha.inserirPilha(1, 2);
        listaPilha.inserirPilha(1, 3);
        listaPilha.inserirPilha(1, 4);

        listaPilha.inserirPilha(2, 5);
        listaPilha.inserirPilha(2, 6);
        listaPilha.inserirPilha(2, 7);

        listaPilha.inserirPilha(3, 8);
        listaPilha.inserirPilha(3, 9);
        listaPilha.inserirPilha(3, 10);
        listaPilha.inserirPilha(3, 11);
        listaPilha.inserirPilha(3, 12);

        listaPilha.mostrar();

        System.out.println();

        listaPilha.mostrarMaiorPilha();

        System.out.println();

    }   
}
