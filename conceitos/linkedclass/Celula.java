class Celula {
    public Celula prox;
    public int elemento;

    public Celula(){
        this.elemento = 0;
        this.prox = null;
    }

    public Celula(int x){
        this.elemento = x;
        this.prox = null;
    }

}