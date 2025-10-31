class CelulaLista {
    CelulaLista prox;
    CelulaPilha topo;

    CelulaLista() {
        prox = null;
        topo = new CelulaPilha();
    }

    CelulaLista(int x) {
        prox = null;
        topo = new CelulaPilha(x);
    }
}