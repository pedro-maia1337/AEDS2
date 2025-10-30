class Celula {
    public Celula prox;
    public Celula ant;
    public int elemento;

    Celula(){
        this(0);
    }

    Celula(int x) {
        this.prox = this.ant = null;
        this.elemento = x;
    }
}