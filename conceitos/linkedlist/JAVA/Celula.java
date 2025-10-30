class Celula {
    //Atributos
    Celula prox;  //ponteiro
    int elemento; //elemento dentro da celula

    //Construtor "vazio"
    Celula() {
        this(0);
    }

    //Construtor com parametro
    Celula(int elemento) {
        prox = null; //aponta para nulo
        this.elemento = elemento; //recebe elemento 
    }
}

