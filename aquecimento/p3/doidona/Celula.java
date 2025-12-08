class Celula {
    Celula prox;
    int elemento;
    
    Celula() {
        this(0);
    }
    
    Celula(int elemento) {
        this.elemento = elemento;
        prox = null;
    }
    
    Celula(int elemento, Celula prox) {
        this.elemento = elemento;
        this.prox = prox;
    }
}