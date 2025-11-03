public class No {
    No prox;
    int elemento;

    No() {
        this(0);
    }

    No(int x) {
        elemento = x;
        prox = null;
    }
}
