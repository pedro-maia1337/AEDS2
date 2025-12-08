class No {
    int elemento;
    No esq, dir;
    boolean color;
    
    No(){
        this.elemento = 0;
        this.esq = null;
        this.dir = null;
        this.color = false;
    }
    
    No(int x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
        this.color = false;
    }
    
    No(int x, boolean color) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
        this.color = color;
    }
    
    No(int x, boolean color, No esq, No dir) {
        this.elemento = x;
        this.esq = esq;
        this.dir = dir;
        this.color = color;
    }
}
