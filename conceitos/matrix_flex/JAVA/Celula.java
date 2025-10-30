class Celula {
    public Celula esq, dir, inf, sup;
    int elemento;

    Celula(){
        this(0);
    }

    public Celula(int elemento){
      this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
    }
}