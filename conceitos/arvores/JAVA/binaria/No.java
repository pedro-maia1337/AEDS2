class No {
    public int elemento;
    public No esq, dir;

    No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }

    No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}