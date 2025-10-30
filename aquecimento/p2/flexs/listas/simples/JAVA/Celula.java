class Celula {
    Celula prox;
    int elemento;

    Celula(){
        this(0);
    }

    Celula(int x) {
        elemento = x;
        prox = null;
    }
 }