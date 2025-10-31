class CelulaPilha {
    CelulaPilha prox;
    int elemento;

    CelulaPilha() {
        this(0);
    }

    CelulaPilha(int x){
        elemento = x;
        prox = null;
    }
}
