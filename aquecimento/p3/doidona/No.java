class No {
    No esq, dir;
    int elemento;
    
    No() {
        this(0);
    }
    
    No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }
    
    No(int elemento, No dir, No esq) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}