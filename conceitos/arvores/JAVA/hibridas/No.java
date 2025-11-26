public class No {
    No esq;
    No dir;
    char elemento;
    No2 outro;

    No(char x) {
        esq = null;
        dir = null;
        elemento = x;
        outro = null;
    }

    No(char x, No esq, No dir) {
        this.esq = esq;
        this.dir = dir;
        this.elemento = x;
        this.outro = null;
    }
}
