class Lista extends Celula{
    private Celula primeiro, ultimo;

    public Lista(){
        ultimo = primeiro = new Celula();
    }

    public void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    };

    public void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    };

    public int removerFim(){
        if(primeiro == ultimo) {
            return 0;
        }
        Celula i;
        for(i = primeiro.prox; i != ultimo.prox; i = i.prox);
        int elemento = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return elemento;

    };

    public int removerInicio(){
        if(primeiro == ultimo) {
            return 0;
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int elemento = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return elemento;
    };

    public void inserir(int x, int pos){
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho) { return;
        } else if (pos == 0) {       inserirInicio(x);
        } else if (pos == tamanho) { inserirFim(x);
        } else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }

    };

    public int remover(int pos) {
      int resp;
      int tamanho = tamanho();

	    if (primeiro == ultimo){
			return 0;
        } else if(pos < 0 || pos >= tamanho){
			return 0;
        } else if (pos == 0){
         resp = removerInicio();
        } else if (pos == tamanho - 1){
            resp = removerFim();
        } else {
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

		return resp;
	}


    public int tamanho() {
        int tamanho = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
    }

    public void mostrar(){
        System.out.println("[");
        for(Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento + " ");
        }
        System.out.println("]");
    };
}
